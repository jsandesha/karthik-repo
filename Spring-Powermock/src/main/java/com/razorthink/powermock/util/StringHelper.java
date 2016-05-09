package com.razorthink.powermock.util;

import java.util.regex.Pattern;

/**
 * This gives utility methods related to String object
 * 
 * @author karthik
 *
 */
public class StringHelper {

	private StringHelper()
	{
		super();
	}

	/**
	 * checks for null or null string
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isNullOrNullString( String str )
	{
		return null == str || "".equals(str.trim()) || "null".equals(str);
	}

	/**
	 * Validate email
	 * 
	 * @param email
	 * @return
	 */
	public static Boolean validateEmail( String email )
	{
		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		return Pattern.matches(emailreg, email);
	}

}
