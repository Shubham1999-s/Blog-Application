package com.Intellijspringproject.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
    private HttpStatus status;//It give status code
    private String message;


    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BlogApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
