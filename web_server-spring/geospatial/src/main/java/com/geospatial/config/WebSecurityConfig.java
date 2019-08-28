package com.geospatial.config;

import com.geospatial.entities.Client;
import com.geospatial.handlers.CustomAuthenticationSuccessHandler;
import com.geospatial.services.ClientService;
import com.geospatial.services.impl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomAuthenticationSuccessHandler loginSuccessHandler; 

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/static/**", "/", "/success").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin().successHandler(this.loginSuccessHandler)
                .loginPage("/login").permitAll()
            .and()
                .logout().permitAll();
                // .logoutUrl("/logout")
                // .logoutSuccessUrl("/login")
                // .invalidateHttpSession(true)
                // .deleteCookies("JSESSIONID")
                // .logoutSuccessHandler(this.logoutSuccessHandler);
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
        Client manager = new Client("guest", "guest@email.com", "pass123");
            manager.setRoles("CLIENT");
        try {
            this.clientService.registerClient(manager);
        } catch (Exception e){
            System.out.println("manager failed or already exists...");
        }
        auth.userDetailsService(this.userDetailsService);

    }
   
    @Override
    public void configure(WebSecurity web) throws Exception {
        //Web resources
     
            web.ignoring().antMatchers("/css/**");
            web.ignoring().antMatchers("/js/**");
            web.ignoring().antMatchers("/images/**");
        
       
    }


    

}

