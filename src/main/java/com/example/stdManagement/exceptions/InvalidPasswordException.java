package com.example.stdManagement.exceptions;

public class InvalidPasswordException extends RuntimeException{

	public InvalidPasswordException(String message) {
		super(message);
	}
}
