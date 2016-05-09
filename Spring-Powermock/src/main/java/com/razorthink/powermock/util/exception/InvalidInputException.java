package com.razorthink.powermock.util.exception;

import java.util.Map;

/**
 * This exception will be thrown in conditions where the error is a recoverable
 * one i.e. the application does not need to be frozen
 * 
 * @author karthik
 */
public class InvalidInputException extends InsufficientInputException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5022221963166706011L;

	public InvalidInputException( String inputField, String errorMessage )
	{
		super(inputField, errorMessage);
	}

	public InvalidInputException( Map<String, String> fields )
	{
		super(fields);
	}

}
