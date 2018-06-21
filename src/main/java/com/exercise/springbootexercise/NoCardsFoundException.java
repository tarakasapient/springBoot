package com.exercise.springbootexercise;

public class NoCardsFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8780432025651376611L;

	public NoCardsFoundException(String message) {
		super(message);
	}
	
}
