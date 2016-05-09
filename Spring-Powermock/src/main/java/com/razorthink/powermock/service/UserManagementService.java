package com.razorthink.powermock.service;

import com.razorthink.powermock.beans.NewUserBean;
import com.razorthink.powermock.domain.User;

/**
 * Services for managing user
 * 
 * @author karthikb
 *
 */
public interface UserManagementService {

	/**
	 * Given user ID, get the User object for that user ID
	 * 
	 * @param userId
	 * @return User
	 */
	User getUserById( final Integer userId );

	/**
	 * Adds a user into the the system Sends out email after adding into to the
	 * system about add user success process.
	 * 
	 * @param user
	 * @param companyId
	 * @param loggedinUser
	 * @return User
	 */
	User addUser( final NewUserBean user, final Integer companyId );

	/**
	 * Get user by email id
	 * 
	 * @param userName
	 * @return
	 */
	User getUserByEmailId( String userName );

	/**
	 * Delete user
	 * 
	 * @param emailId
	 * @return
	 */
	boolean deleteUser( String emailId );

}
