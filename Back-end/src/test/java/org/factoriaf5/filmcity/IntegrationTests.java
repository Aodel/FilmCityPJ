package org.factoriaf5.filmcity;

import org.factoriaf5.filmcity.domain.Movie;
import org.factoriaf5.filmcity.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MovieRepository movieRepository;


    @BeforeEach
    void setUp() {
        movieRepository.deleteAll();
    }

    @Test
    void returnsTheExistingMovies() throws Exception {

        addSampleMovies();

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].title", equalTo("Jurassic Park")))
                .andExpect(jsonPath("$[0].coverImage", equalTo("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg")))
                .andExpect(jsonPath("$[0].director", equalTo("Steven Spielberg")))
                .andExpect(jsonPath("$[0].year", equalTo(1993)))
                .andExpect(jsonPath("$[0].synopsis", equalTo("A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA.")))
                .andExpect(jsonPath("$[0].renter", equalTo(null)))
                .andExpect(jsonPath("$[0].booked", equalTo(false)))
                .andExpect(jsonPath("$[1].title", equalTo("Ratatouille")))
                .andExpect(jsonPath("$[1].coverImage", equalTo("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/npHNjldbeTHdKKw28bJKs7lzqzj.jpg")))
                .andExpect(jsonPath("$[1].director", equalTo("Brad Bird")))
                .andExpect(jsonPath("$[1].year", equalTo(2007)))
                .andExpect(jsonPath("$[1].synopsis", equalTo("Remy, a resident of Paris, appreciates good food and has quite a sophisticated palate. He would love to become a chef so he can create and enjoy culinary masterpieces to his heart's delight. The only problem is, Remy is a rat.")))
                .andExpect(jsonPath("$[1].renter", equalTo(null)))
                .andExpect(jsonPath("$[1].booked", equalTo(false)))
                .andDo(print());
    }

    //RETURNS THE SAMPLE MOVIE TO TEST RETURN LIST

    public void addSampleMovies() {
        List<Movie> movies = List.of(
                new Movie("Jurassic Park",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg",
                        "Steven Spielberg",
                        1993,
                        "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA.",
                        null,
                        false,
                        0),
                new Movie("Ratatouille",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/npHNjldbeTHdKKw28bJKs7lzqzj.jpg",
                        "Brad Bird",
                        2007,
                        "Remy, a resident of Paris, appreciates good food and has quite a sophisticated palate. He would love to become a chef so he can create and enjoy culinary masterpieces to his heart's delight. The only problem is, Remy is a rat.",
                        null,
                        false,
                        0)
        );

        movieRepository.saveAll(movies);
    }

    @Test
    void allowsToAddANewMovie() throws Exception {
        mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"title\": \"Jurassic Park 3\", " +
                        "\"director\": \"Steven Aberkani\", " +
                        "\"year\":  "+1995+" , " +
                        "\"synopsis\": \"Java\"}")
        ).andExpect(status().isOk())
        .andDo(print());

        List<Movie> movies = movieRepository.findAll();
        assertThat(movies, contains(allOf(
                hasProperty("title", is("Jurassic Park 3")),
                hasProperty("director", is("Steven Aberkani")),
                hasProperty("year", is(1995)),
                hasProperty("synopsis", is("Java"))
        )));

    }

    @Test
    void deleteMovieById() throws Exception {
        Movie movie = movieRepository.save(new Movie("Jurassic Park", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg", "Steven Spielberg", 1993, "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA.", null, false,0));
        mockMvc.perform(delete("/movies/"+ movie.getId()))
                .andExpect(status().isOk());
        List<Movie> movies = movieRepository.findAll();
        assertThat(movies, not(contains(allOf(
                hasProperty("title", is("Jurassic Park")),
                hasProperty("coverImage", is("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg"))
        ))));
    }

    @Test
    void allowsToModifyAMovie() throws Exception {
        Movie movie = movieRepository.save(new Movie("Jurassic Park", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg", "Steven Spielberg", 1993, "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA.", null, false,0));

        mockMvc.perform(put("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\": \"" + movie.getId() + "\", " +
                        "\"title\": \"Jurassic Park\", " +
                        "\"coverImage\": \"https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg\" }")
        ).andExpect(status().isOk());

        List<Movie> movies = movieRepository.findAll();

        assertThat(movies, hasSize(1));
        assertThat(movies.get(0).getTitle(), equalTo("Jurassic Park"));
        assertThat(movies.get(0).getCoverImage(), equalTo("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg"));
    }
    @Test
   /* void setMovieRenterById() throws Exception {
        Movie movie = movieRepository.save(new Movie("Jurassic Park", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg", "Steven Spielberg", 1993, "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA.", null, false,0));
        mockMvc.perform(put("/movies/"+ movie.getId()+ "/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" + "\"renter\": \"Pepe Goyeles\" ")
        ).andExpect(status().isOk());
        List<Movie> movies = movieRepository.findAll();
        assertThat(movies, (contains(allOf(
                hasProperty("renter", is("Pepe Goyeles"))
        ))));
    }
    @Test
    boolean setMovieBookedById() throws Exception {
        Movie movie = movieRepository.save(new Movie("Jurassic Park", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg", "Steven Spielberg", 1993, "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA.", null, false,0));
        mockMvc.perform(put("/movies/"+ movie.getId()+ "/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" + "\"booked\": "+true+" }")
        ).andExpect(status().isOk());
        List<Movie> movies = movieRepository.findAll();
        assertThat(movies, (contains(allOf(
                hasProperty("booked", is(true))
        ))));
        return false;
    }*/

    @Test
    void setMovieScoreById() throws Exception {
        Movie movie = movieRepository.save(new Movie("Jurassic Park", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg", "Steven Spielberg", 1993, "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA.", null, false,0));
        mockMvc.perform(put("/movies/"+ movie.getId()+ "/rating")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" + "\"score\":  "+3+" }")
        ).andExpect(status().isOk());
        List<Movie> movies = movieRepository.findAll();
        assertThat(movies, (contains(allOf(
                hasProperty("score", is(3))
        ))));
    }

}