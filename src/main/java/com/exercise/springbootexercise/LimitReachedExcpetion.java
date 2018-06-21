package com.exercise.springbootexercise;

public class LimitReachedExcpetion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1857609495360789705L;

	public LimitReachedExcpetion(String message) {
		super(message);
	}

}
