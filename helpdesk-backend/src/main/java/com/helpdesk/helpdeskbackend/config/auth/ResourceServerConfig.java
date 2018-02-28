package com.helpdesk.helpdeskbackend.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and()
        		.headers().frameOptions().disable()
        		.and()
                .authorizeRequests()
                .antMatchers("/","/login", "/oauth/**").permitAll()
                .antMatchers("/users/**", "/roles/**").hasRole("ADMIN")
                .antMatchers("/logouts", "/user", "/tasks/**").authenticated();
    	}
}