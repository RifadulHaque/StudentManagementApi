package com.springproject.StudentManagementApi.Config;

import com.springproject.StudentManagementApi.Security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

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

        //this is an alternate way to have custom user to access the service
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());//will provide authentication to the user who was registered

        //this also works (2nd way)
//        auth.inMemoryAuthentication()
//            .withUser("Dev1").password("dev1").authorities("admin")
//            .and()
//            .withUser("Test1").password("test1").authorities("user")
//            .and()
//            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();//this is used to save the actual password
        return new BCryptPasswordEncoder();//this is used to Encrypt the password and then store it in the database
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}
