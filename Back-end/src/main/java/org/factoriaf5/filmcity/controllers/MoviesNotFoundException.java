package org.factoriaf5.filmcity.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Movie not found")
public class MoviesNotFoundException extends RuntimeException {
}
