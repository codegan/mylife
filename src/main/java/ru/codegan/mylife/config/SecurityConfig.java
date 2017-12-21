package ru.codegan.mylife.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import ru.codegan.mylife.authentication.CustomAuthenticationEntryPoint;
import ru.codegan.mylife.authentication.CustomSavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.codegan.mylife.authentication")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private CustomAuthenticationEntryPoint restAuthenticationEntryPoint;
 
    @Autowired
    private CustomSavedRequestAwareAuthenticationSuccessHandler
      authenticationSuccessHandler;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
  
        auth.inMemoryAuthentication()
          .withUser("admin").password("admin").roles("ADMIN")
          .and()
          .withUser("user").password("userPass").roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
    	http.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .authorizeRequests()
        .antMatchers("/books/**").hasRole("ADMIN")
        .and()
        .formLogin()
        .successHandler(authenticationSuccessHandler)
        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        .and()
        .logout();
    }
 
    @Bean
    public CustomSavedRequestAwareAuthenticationSuccessHandler mySuccessHandler(){
        return new CustomSavedRequestAwareAuthenticationSuccessHandler();
    }
    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }
}
