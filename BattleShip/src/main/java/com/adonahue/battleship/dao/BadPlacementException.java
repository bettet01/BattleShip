package com.adonahue.battleship.dao;

/**
 *
 * @author allison
 */
public class BadPlacementException extends Exception {

    public BadPlacementException(String message) {
        super(message);
    }

    public BadPlacementException(String message, Throwable cause) {
        super(message, cause);
    }
}