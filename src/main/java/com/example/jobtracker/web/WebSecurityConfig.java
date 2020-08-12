package com.example.jobtracker.web;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(
        value="oauth.enabled",
        havingValue = "true")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/jobs")
                .fullyAuthenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/","/getcode").fullyAuthenticated()
                .and()
                .authorizeRequests()
                .antMatchers( "/login**","/code**","/do_logout**").permitAll()
                .and()
                .oauth2Login()
                .and()
                .oauth2ResourceServer()
                .opaqueToken()

        ;

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }


}
