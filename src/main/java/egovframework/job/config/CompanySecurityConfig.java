package egovframework.job.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import egovframework.job.service.CompanyDetailsService;

@Configuration
@EnableWebSecurity
@Order(2)
public class CompanySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CompanyDetailsService companyDetailsService;
	
	@Bean
    public PasswordEncoder companyPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider companyAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(companyDetailsService);
        provider.setPasswordEncoder(companyPasswordEncoder());
        return provider;
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
    	.authenticationProvider(companyAuthenticationProvider())
        .authorizeRequests()
        	.antMatchers("/company/home.do").authenticated()
        	.antMatchers("/company/info.do/**").authenticated()
        	.and()
        .formLogin()
        	.loginPage("/company/login.do")
        	.defaultSuccessUrl("/company/home.do")
        	.permitAll()
        	.usernameParameter("id")
        	.and()
        .logout()
        	.permitAll();
    }
	
}