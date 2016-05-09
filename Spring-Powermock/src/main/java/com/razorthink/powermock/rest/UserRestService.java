package com.razorthink.powermock.rest;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.razorthink.powermock.beans.APIResponse;
import com.razorthink.powermock.beans.NewUserBean;
import com.razorthink.powermock.domain.User;
import com.razorthink.powermock.service.UserManagementService;
import com.razorthink.powermock.util.ValidationHelper;

@RestController
@RequestMapping( "/rest/user" )
public class UserRestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestService.class);

	@Autowired
	private UserManagementService userManagementService;

	private final static int COMPANY_ID = 1;

	public UserManagementService getUserManagementService()
	{
		return userManagementService;
	}

	public void setUserManagementService( UserManagementService userManagementService )
	{
		this.userManagementService = userManagementService;
	}

	@RequestMapping( value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<APIResponse> addUser( @RequestBody( required = true ) NewUserBean user,
			HttpServletRequest servletRequest )
	{
		LOGGER.debug("Adding new user - {}", user.getEmail());

		APIResponse response = new APIResponse();

		try
		{
			ValidationHelper.validateUserBean(user);

			userManagementService.addUser(user, COMPANY_ID);

			response.setData(true);

		}
		catch( Exception e )
		{
			response.setData(null);
			response.setErrorCode(e.getMessage());
			LOGGER.error("Something went wrong while adding user ", e);
		}

		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@RequestMapping( value = "/get/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<APIResponse> getUser( @PathVariable( "userId" ) Integer userId,
			HttpServletRequest servletRequest )
	{
		LOGGER.debug("Get user");

		APIResponse response = new APIResponse();

		try
		{
			ValidationHelper.validateField(userId, "userId");

			User u = userManagementService.getUserById(userId);
			response.setData(u);
		}
		catch( Exception e )
		{
			response.setData(null);
			response.setErrorCode(e.getMessage());
			LOGGER.error("Something went wrong while getting user ", e);
		}
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}

	@RequestMapping( value = "/deleteUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<APIResponse> deleteUser( @RequestParam( value = "emailId", required = true ) String emailId,
			HttpServletRequest servletRequest )
	{
		LOGGER.debug("Delete user");

		APIResponse response = new APIResponse();

		try
		{
			ValidationHelper.validateEmailField(emailId);

			boolean isDeleted = userManagementService.deleteUser(emailId);
			response.setData(isDeleted);
		}
		catch( Exception e )
		{
			response.setData(null);
			response.setErrorCode(e.getMessage());
			LOGGER.error("Something went wrong while getting user ", e);
		}
		return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
	}
}
