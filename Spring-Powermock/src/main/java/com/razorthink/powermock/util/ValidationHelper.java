package com.razorthink.powermock.util;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.powermock.beans.NewUserBean;
import com.razorthink.powermock.util.exception.InsufficientInputException;
import com.razorthink.powermock.util.exception.InvalidInputException;

public class ValidationHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationHelper.class);

	/**
	 * Regular expression to match phone number
	 */
	private static final String phoneRegEx = "\\d{10}";

	private ValidationHelper()
	{
	}

	/**
	 * Validates all sort of field objects.
	 * 
	 * @param t
	 * @param fieldName
	 * @return Boolean
	 */
	final public static <T> Boolean validateField( T t, String fieldName )
	{
		if( t == null )
		{
			LOGGER.error("Invalid field " + fieldName);
			throw new IllegalArgumentException("Invalid input field - " + fieldName);
		}

		return true;
	}

	/**
	 * Validates all fields in new user bean and throws
	 * InsufficientInputException if the field value is null / empty
	 * 
	 * @param newAddressBean
	 * @return Boolean
	 */
	final public static Boolean validateUserBean( NewUserBean user )
	{
		validateField(user, "User data");

		Map<String, String> errorParams = new HashMap<String, String>();
		Map<String, String> invalidParams = new HashMap<String, String>();
		Boolean isSufficientInput = true;
		Boolean isValidInput = true;

		if( StringHelper.isNullOrNullString(user.getEmail()) )
		{
			isSufficientInput = false;
			errorParams.put("email id", "Email id is null/emplty");
			LOGGER.error("Email id is null/emplty");
		}
		else if( !StringHelper.validateEmail(user.getEmail()) )
		{
			LOGGER.error("Invalid email id");
			isValidInput = false;
			invalidParams.put("emailId", "Invalid email id");
		}

		if( StringHelper.isNullOrNullString(user.getFirstName()) )
		{
			isSufficientInput = false;
			errorParams.put("First name", "First name is null/emplty");
			LOGGER.error("First name is null/emplty");
		}

		if( StringHelper.isNullOrNullString(user.getLastName()) )
		{
			isSufficientInput = false;
			errorParams.put("Last name", "Last name is null/emplty");
			LOGGER.error("Last name is null/emplty");
		}

		if( StringHelper.isNullOrNullString(user.getPassword()) )
		{
			isSufficientInput = false;
			errorParams.put("password", "password is null/emplty");
			LOGGER.error("password is null/emplty");
		}

		if( !StringHelper.isNullOrNullString(user.getPhone()) && !validatePhone(user.getPhone()) )
		{
			isValidInput = false;
			invalidParams.put("phoneNumber", "Invalid phone");
			LOGGER.error("Invalid phone");
		}

		if( !isSufficientInput )
		{
			LOGGER.error("Insufficient inputs " + errorParams);
			throw new InsufficientInputException(errorParams);
		}

		if( !isValidInput )
		{
			LOGGER.error("Invalid inputs" + invalidParams);
			throw new InvalidInputException(invalidParams);
		}

		return true;
	}

	/**
	 * Validates user email field
	 * 
	 * @param email
	 * @return Boolean
	 */
	final public static Boolean validateEmailField( String email )
	{
		Boolean isValid = validateField(email, "Email");

		if( isValid && !StringHelper.validateEmail(email) )
		{
			LOGGER.error("Invalid email id");
			throw new InvalidInputException("emailId", "Invalid email id ");
		}

		return isValid;
	}

	/**
	 * Validates phone number
	 * 
	 * @param phone
	 * @return Boolean
	 */
	final private static Boolean validatePhone( String phone )
	{
		if( !StringHelper.isNullOrNullString(phone) && !phone.matches(phoneRegEx) )
		{
			return false;
		}

		return true;
	}

}
