
package com.minbrat.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringConfigure extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // TODO Auto-generated method stub
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select usn,pwd,enabled from MyUser where usn= ?")
				.authoritiesByUsernameQuery(" select usn,authority from MyAuth where usn= ?");
	}

	@Bean
	public PasswordEncoder getPassword() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { //

		http.authorizeRequests().antMatchers("/get").hasAnyRole("ADMIN").antMatchers("/get").hasAnyRole("USER", "ADMIN")
				.antMatchers("/get").permitAll().and().formLogin();
	}
}
