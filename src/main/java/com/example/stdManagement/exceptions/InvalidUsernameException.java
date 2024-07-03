package com.example.stdManagement.exceptions;



public class InvalidUsernameException extends RuntimeException{
   public InvalidUsernameException(String message) {
	   super(message);
   }
}
