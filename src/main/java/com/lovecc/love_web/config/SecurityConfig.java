package com.lovecc.love_web.config;

import com.lovecc.love_web.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new MyPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index","/webjars/**","/authImg","/","/login","/register","/registerHas","/registerUI","/registerHasUI","/css/**","/img/**","/js/**","/jedate1/**","/user/index","/druid/*","/checkOnlyId").permitAll()
                .antMatchers("/**").authenticated()
                //.antMatchers("/**").permitAll()
                .and()
                .formLogin().loginPage("/login").successForwardUrl("/user/index");
        http.logout().logoutSuccessUrl("/");
        http.csrf().disable();
    }
}
