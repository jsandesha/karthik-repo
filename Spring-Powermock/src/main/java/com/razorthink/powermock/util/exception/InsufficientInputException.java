package com.razorthink.powermock.util.exception;

import java.util.HashMap;
import java.util.Map;
import com.razorthink.powermock.util.StringHelper;

/**
 * This exception will be thrown in conditions where the error is a recoverable
 * one i.e. the application does not need to be frozen
 * 
 * @author karthik
 */
public class InsufficientInputException extends RuntimeException {

	private static final long serialVersionUID = -9140142714858056994L;

	Map<String, String> fields = new HashMap<String, String>();

	public InsufficientInputException( String inputField, String errorMessage )
	{
		super(errorMessage);
		if( StringHelper.isNullOrNullString(errorMessage) )
		{
			errorMessage = "Can not be blank";
		}
		fields.put(inputField, errorMessage);
	}

	public InsufficientInputException( Map<String, String> fields )
	{
		super(fields.toString());
		this.fields = fields;
	}

	public Map<String, String> getFields()
	{
		return fields;
	}

	public void setFields( Map<String, String> fields )
	{
		this.fields = fields;
	}

}
