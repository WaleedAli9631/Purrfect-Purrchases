package com.revature.project2.service;

public class WrongDateException extends Exception{
    public WrongDateException() {
    }

    public WrongDateException(String message) {
        super(message);
    }
}
