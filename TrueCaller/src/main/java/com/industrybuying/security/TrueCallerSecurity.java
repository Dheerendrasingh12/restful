package com.industrybuying.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class TrueCallerSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select usn,pwd,enabled from MyUser where usn= ?")
				.authoritiesByUsernameQuery(" select usn,authority from myAuth where usn= ?");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().ignoringAntMatchers("/save/**").and().authorizeRequests().antMatchers("/save/**").permitAll()
				.antMatchers("/getAll").hasAnyRole("ADMIN").antMatchers("/fetchByName").hasAnyRole("ADMIN")
				.antMatchers("/fetchByNumber").hasAnyRole("ADMIN").and().formLogin();
		;
	}
}
