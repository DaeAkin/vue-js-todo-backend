package com.vuejs.backend.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@Configuration
public class Oauth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/todos").permitAll()
                .antMatchers(HttpMethod.GET, "/api/todos/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/todos/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/todos/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/todos").permitAll()
                .antMatchers(HttpMethod.POST, "/api/todos/upload").permitAll()
                .antMatchers(HttpMethod.POST, "/oauth/token").permitAll()
                .anyRequest().access("#oauth2.hasScope('USER')");
    }
}
