package com.razorthink.powermock.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.razorthink.powermock.beans.NewUserBean;
import com.razorthink.powermock.domain.User;
import com.razorthink.powermock.repository.UserRepository;
import com.razorthink.powermock.service.UserManagementService;
import com.razorthink.powermock.util.PasswordHash;

@Service( "userManagementService" )
@Transactional( rollbackFor = Exception.class )
public class UserManagementServiceImpl implements UserManagementService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementService.class);

	UserRepository userRepository;

	@Autowired
	public UserManagementServiceImpl( UserRepository userRepository )
	{
		this.userRepository = userRepository;
	}

	public UserRepository getUserRepository()
	{
		return userRepository;
	}

	public void setUserRepository( UserRepository userRepository )
	{
		this.userRepository = userRepository;
	}

	@Override
	public User addUser( NewUserBean userToAdd, Integer companyId )
	{
		User u = userRepository.findByEmail(userToAdd.getEmail());

		if( u != null )
		{
			LOGGER.error("Duplicate user " + userToAdd.getEmail());
			throw new IllegalArgumentException("Duplicate user " + userToAdd.getEmail());
		}

		try
		{
			if( null != userToAdd.getPassword() && !userToAdd.getPassword().trim().isEmpty() )
			{
				userToAdd.setPassword(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 7));
			}

			String salt = PasswordHash.getSalt();
			String securePassword = PasswordHash.createHash(userToAdd.getPassword(), salt);

			u = new User(userToAdd.getFirstName(), userToAdd.getLastName(), userToAdd.getEmail(), userToAdd.getPhone(),
					securePassword, salt, companyId);
			u = userRepository.save(u);

			LOGGER.info("Successfully added " + userToAdd.getEmail());
		}
		catch( NoSuchAlgorithmException | InvalidKeySpecException e )
		{
			LOGGER.error("Something went wrong", e);
			throw new IllegalArgumentException(e);
		}

		return u;
	}

	@Override
	public User getUserById( Integer userId )
	{
		return this.userRepository.findOne(userId);
	}

	@Override
	public User getUserByEmailId( String userName )
	{
		if( null != userName && !userName.trim().isEmpty() )
		{
			return userRepository.findByEmail(userName);
		}
		return null;
	}

	@Override
	public boolean deleteUser( String emailId )
	{
		if( null != emailId && !emailId.trim().isEmpty() )
		{
			User u = userRepository.findByEmail(emailId);
			if( u != null )
			{
				Long count = userRepository.deleteByEmail(emailId);
				if( count > 0 )
				{
					return true;
				}
			}
		}
		return false;
	}
}
