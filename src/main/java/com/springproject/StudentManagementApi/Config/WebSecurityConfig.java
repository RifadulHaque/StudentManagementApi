package com.springproject.StudentManagementApi.Config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //this method is overriden from the WebSecurityConfiguration
    //csrf is disabled and only the matches url is permitted, any other request is authenticated
    //httpBasic() is used
    //to summarize this method authenticates users by using the matchers, it helps us to customize the authentication details and user details
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login", "/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

    //it helps us to configure multiple users
    //the inMemoryAuthentication is used to provide authentication to the users.
    //the passwordEncoder must be excluded as a user name and password is now used rather than the password that is encoded
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("Dev1").password("dev1").authorities("admin")
            .and()
            .withUser("Test1").password("test1").authorities("user")
            .and()
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
