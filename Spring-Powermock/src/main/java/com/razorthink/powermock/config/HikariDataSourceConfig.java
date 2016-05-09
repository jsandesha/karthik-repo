/****/
package com.razorthink.powermock.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.razorthink.powermock.util.StringHelper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/***
 * 
 * @author shams
 **/
@Configuration
public class HikariDataSourceConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource()
	{
		HikariConfig config = new HikariConfig();
		int poolSize = 20;
		if( !StringHelper.isNullOrNullString(env.getProperty("spring.datasource.pool.size")) )
		{
			poolSize = Integer.parseInt(env.getProperty("spring.datasource.pool.size"));
		}
		config.setMaximumPoolSize(poolSize);
		config.setDataSourceClassName(env.getProperty("spring.datasource.driver-class-name"));
		config.addDataSourceProperty("url", env.getProperty("spring.datasource.url"));
		config.addDataSourceProperty("user", env.getProperty("spring.datasource.username"));
		config.addDataSourceProperty("password", env.getProperty("spring.datasource.password"));
		return new HikariDataSource(config);
	}
}
