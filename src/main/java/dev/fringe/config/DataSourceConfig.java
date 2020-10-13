package dev.fringe.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;

@Configuration
@PropertySource("app.properties")
@ComponentScan
@EnableAutoConfiguration
@MapperScan("dev.fringe.mapper")
@EnableDiscoveryClient
public class DataSourceConfig {

	@Value("${app.jdbc.driver}") String driver;
	@Value("${app.jdbc.url}") String url;	
	@Value("${app.jdbc.user}") String user;	
	@Value("${app.jdbc.password}") String password;	
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
        ds.setPassword(password);
		Log4jdbcProxyDataSource fds = new Log4jdbcProxyDataSource(ds);
		Log4JdbcCustomFormatter format = new Log4JdbcCustomFormatter();
		format.setLoggingType(LoggingType.MULTI_LINE);
		format.setSqlPrefix("SQL         :  ");
		fds.setLogFormatter(format);        
		return fds;        
	}
}
