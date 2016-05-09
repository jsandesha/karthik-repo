package com.razorthink.powermock.service.unit;

import static org.junit.Assert.assertEquals;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.razorthink.SpringPowermockApplication;
import com.razorthink.powermock.beans.NewUserBean;
import com.razorthink.powermock.domain.User;
import com.razorthink.powermock.repository.UserRepository;
import com.razorthink.powermock.service.UserManagementService;
import com.razorthink.powermock.util.PasswordHash;

@RunWith( PowerMockRunner.class )
@PowerMockRunnerDelegate( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = SpringPowermockApplication.class )
@WebAppConfiguration
@ActiveProfiles( "unittest" )
@PrepareForTest( { PasswordHash.class, UserRepository.class } )
public class UserManagementTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	@Autowired
	private UserManagementService userManagementService;

	@Before
	public void setUp() throws Exception
	{

	}

	@Test
	public void testAddUser() throws NoSuchAlgorithmException, InvalidKeySpecException
	{

		final String salt = "khgsdf32d";
		final String hash = "kuywejhrmwnefcSDCSJFuye8w3rewrfc4#@#sdfsdf";

		// Mock static methods of PasswordHash
		PowerMockito.mockStatic(PasswordHash.class);
		PowerMockito.when(PasswordHash.getSalt()).thenReturn(salt);
		PowerMockito.when(PasswordHash.createHash(Mockito.anyString(), Mockito.anyString())).thenReturn(hash);

		User user = new User();
		user.setId(1);
		user.setAddedDttm(Calendar.getInstance());
		user.setCompanyId(1);
		user.setEmail("testuser@gmail.com");
		user.setIsActive(true);
		user.setPassword("123456");

		NewUserBean userBean = new NewUserBean("test", "user", "testuser@kat.com", "9873847364", "123456");

		// Mock userRepository methods to return as expected
		PowerMockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		PowerMockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);

		User addedUser = userManagementService.addUser(userBean, 1);
		assertEquals(user.getId(), addedUser.getId());
		assertEquals(user.getCompanyId(), addedUser.getCompanyId());
	}

}
