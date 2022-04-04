# FilmCity

FilmCity is our latest REST API based on the Java framework Spring Boot.

## Get all the movies

Returns all the movies at the movies repository

### Request

``GET http://localhost:8080/movies``

### Response

```
[
    {
        "id": 1,
        "title": "Jurassic Park",
        "coverImage": "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg",
        "director": "Steven Spielberg",
        "year": 1993,
        "booked": false,
        "renter": null,
        "rating": 0,
        "synopsis": "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA."
    },
    {
        "id": 2,
        "title": "Ratatouille",
        "coverImage": "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/npHNjldbeTHdKKw28bJKs7lzqzj.jpg",
        "director": "Brad Bird",
        "year": 2007,
        "booked": false,
        "renter": null,
        "rating": 0,
        "synopsis": "Remy, a resident of Paris, appreciates good food and has quite a sophisticated palate. He would love to become a chef so he can create and enjoy culinary masterpieces to his heart's delight. The only problem is, Remy is a rat."
    },
    {
        "id": 3,
        "title": "Cruella",
        "coverImage": "https://pics.filmaffinity.com/Cruella-196211257-large.jpg",
        "director": "Craig Gillespie",
        "year": 2021,
        "booked": false,
        "renter": null,
        "rating": 0,
        "synopsis": "Set in London during the punk rock movement of the 1970s, the film revolves around Estella Miller, an aspiring fashion designer, as she explores the path that will lead her to become a notorious up-and-coming fashion designer known as Cruella de Vil."
    },
    {
        "id": 4,
        "title": "Mean Girls",
        "coverImage": "https://images-na.ssl-images-amazon.com/images/I/71eQtET-kmL._RI_.jpg",
        "director": "Mark Waters",
        "year": 2004,
        "booked": false,
        "renter": null,
        "rating": 0,
        "synopsis": "Lindsay Lohan stars as Cady Heron, a 16 year old homeschooled girl who not only makes the mistake of falling for Aaron Samuels (Jonathan Bennett), the ex-boyfriend of queenbee Regina George (Rachel McAdams), but also unintentionally joins The Plastics, led by Regina herself."
    },
    {
        "id": 5,
        "title": "Lady Bird",
        "coverImage": "https://pics.filmaffinity.com/Lady_Bird-546261513-large.jpg",
        "director": "Greta Gerwig",
        "year": 2017,
        "booked": false,
        "renter": null,
        "rating": 0,
        "synopsis": "Christine 'Lady Bird' McPherson (Saoirse Ronan) is a senior at a Catholic high school in Sacramento, California in 2002. She longs to attend a prestigious college in 'a city with culture'."
    },
    {
        "id": 6,
        "title": "Suffragette",
        "coverImage": "https://musicart.xboxlive.com/7/b81f2600-0000-0000-0000-000000000002/504/image.jpg?w=1920&h=1080",
        "director": "Sarah Gavron",
        "year": 2015,
        "booked": false,
        "renter": null,
        "rating": 0,
        "synopsis": "Inspired by true events, Suffragette movingly explores the passion and heartbreak of those who risked all they had for women's right to vote – their jobs, their homes, their children, and even their lives."
    },
    {
        "id": 7,
        "title": "On the basis of sex",
        "coverImage": "https://m.media-amazon.com/images/I/71TuUvNkS4L._SL1500_.jpg",
        "director": "Mimi Leder",
        "year": 2018,
        "booked": false,
        "renter": null,
        "rating": 0,
        "synopsis": "On the Basis of Sex is inspired by the true story of a young Ruth Bader Ginsburg – then a struggling attorney and new mother – who faces adversity and numerous obstacles in her fight for equal rights throughout her career."
    }
]
```

## Add a movie

Adds a new movie to the movies repository

### Request

``POST http://localhost:8080/movies``

### Response

```
{
    "id": 8,
    "title": "Matrix",
    "coverImage": "https://www.themoviedb.org/movie/603-the-matrix?language=es#",
    "director": "Lilly Wachowski",
    "year": 1999,
    "booked": false,
    "renter": null,
    "rating": 0,
    "synopsis": "Thomas Anderson lleva una doble vida: por el día es programador en una importante empresa de software, y por la noche un hacker informático llamado Neo. Su vida no volverá a ser igual cuando unos misteriosos personajes le inviten a descubrir la respuesta a la pregunta que no le deja dormir: ¿qué es Matrix?"
}
```

## Update a movie

Updates the data of a movie

## Request

``PUT http://localhost:8080/movies``

## Response

```
{
    "id": 8,
    "title": "Matrix",
    "coverImage": "https://www.themoviedb.org/movie/603-the-matrix?language=es#",
    "director": "Lana Wachowski",
    "year": 1999,
    "booked": false,
    "renter": null,
    "rating": 0,
    "synopsis": "Thomas Anderson lleva una doble vida: por el día es programador en una importante empresa de software, y por la noche un hacker informático llamado Neo. Su vida no volverá a ser igual cuando unos misteriosos personajes le inviten a descubrir la respuesta a la pregunta que no le deja dormir: ¿qué es Matrix?"
}
```

## Deletes a movie

Finds the movie by ID in the movies repository

```DELETE http://localhost:8080/movies/{id}```

## Sets the movie as rented

Set the movie as rented

```PUT http://localhost:8080/movies/{id}/book?renter={name}```


## Returns the movie and sets it as available to rent

Set the movie as rented

```PUT http://localhost:8080/movies/{id}/return```

## Rates the movie

Set the movie score

```PUT http://localhost:8080/movies/{id}/rating```