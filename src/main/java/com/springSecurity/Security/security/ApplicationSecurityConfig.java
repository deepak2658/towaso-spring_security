package com.springSecurity.Security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.springSecurity.Security.security.ApplicationUserPermission.*;
import static com.springSecurity.Security.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .antMatchers(HttpMethod.GET,"/management/**").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.DELETE,"/management/**").hasAuthority(COURSE_WRITE.name())
//                .antMatchers(HttpMethod.POST,"/management/**").hasAuthority(COURSE_WRITE.name())
//                .antMatchers(HttpMethod.PUT,"/management/**").hasAuthority(COURSE_WRITE.name())
////                .antMatchers("/management/**")
                .anyRequest().authenticated().and().formLogin();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmithUser = User.builder()
                .username("annasmith").password(passwordEncoder.encode("password"))
                .roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();  //Role student

        UserDetails lindaUser = User.builder()
                .username("linda").password(passwordEncoder.encode("password"))
                .roles(ADMIN.name())
 //               .authorities(ADMIN.getGrantedAuthorities())
                .build();   //ROLE_ADMIN
        UserDetails tomUser = User.builder()
                .username("tom").password(passwordEncoder.encode("password"))
//                .roles(ADMINTRAINEE.name())
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(annaSmithUser,lindaUser,tomUser);
    }
}
