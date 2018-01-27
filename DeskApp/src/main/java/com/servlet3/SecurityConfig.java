package com.servlet3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("deskAppAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	http.authorizeRequests().antMatchers("/", "/favicon.ico", "/resources/**","/resources/js/**","/resources/css/**")
    	.permitAll();
    	
    	http
                .csrf()
                .disable()
                .headers()
                .frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/app/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/users/**")
                .hasAnyRole("ADMIN", "USER_MANAGER")
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .permitAll()
                .loginProcessingUrl("/authenticate")
                .usernameParameter("j_username")
                .passwordParameter("j_password").defaultSuccessUrl("/app/index").and().logout()
                .logoutUrl("/app/logout").logoutRequestMatcher(new AntPathRequestMatcher("/app/logout")).clearAuthentication(true).invalidateHttpSession(true)
                .logoutSuccessUrl("/app/logoutsuccessfully").permitAll()
                .and().exceptionHandling().accessDeniedPage("/app/linkedin");
    }

    @Override
    @Bean(name = "authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
