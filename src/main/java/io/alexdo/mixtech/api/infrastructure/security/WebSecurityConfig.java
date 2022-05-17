
/**
 * This is a class for future user authentication implementation. Currently,
 * the program authenticates user through route interceptor. In the future, we
 * will use this class, configuring Spring Security for user authentication.
 */


//package com.example.cs564.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/search/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // authorize "/", "/search" to all users
//        http.authorizeRequests()
//                .antMatchers("/", "/search", "/user/*").permitAll()
//                // need authorization for any other url
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                // login page
//                .loginPage("/user/login")
//                // jump to page "/match" if succeeded
//                .defaultSuccessUrl("/match")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/user/logout")
//                .logoutSuccessUrl("/user/login")
//                .permitAll();
//        http.csrf().disable();
//    }
//}
