package com.razorthink.powermock.config;

import javax.sql.DataSource;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import com.razorthink.powermock.config.embedded.EmbeddedMysqlDatabaseBuilder;
import com.razorthink.powermock.repository.UserRepository;

/**
 * Config class that defines all the required beans that are specific to test
 * case. <b>EmbeddedMySqlConfig will be used only for active profile with name
 * specified in @profile.</b>
 * 
 * @author karthikb
 *
 */
@Configuration
@Profile( "unittest" )
public class EmbeddedMySqlConfig {

	@Bean( destroyMethod = "shutdown" )
	public DataSource dataSource()
	{
		return new EmbeddedMysqlDatabaseBuilder().build();
	}

	@Bean
	@Primary
	public UserRepository userRepository()
	{
		return PowerMockito.mock(UserRepository.class);
	}
}
