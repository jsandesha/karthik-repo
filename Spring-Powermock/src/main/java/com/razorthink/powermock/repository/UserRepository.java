package com.razorthink.powermock.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.razorthink.powermock.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Finds list of the user entity by last name
	 * 
	 * @param lastName
	 * @return List<User>
	 */
	List<User> findByLastName( String lastname );

	/**
	 * Find the user entity by email
	 * 
	 * @param email
	 * @return User
	 */
	User findByEmail( String email );

	/**
	 * Delete user by email
	 * 
	 * @param emailId
	 * @return
	 */
	Long deleteByEmail( String email );
}
