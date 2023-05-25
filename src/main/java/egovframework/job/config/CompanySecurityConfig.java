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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
	
	@Bean
	public AuthenticationSuccessHandler companyAuthenticationSuccessHandler() {
	    return new CompanyAuthenticationSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler companyAuthenticationFailureHandler() {
	    return new CompanyAuthenticationFailureHandler();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		
		http.cors();
		
    	http.csrf().disable()
    	.authenticationProvider(companyAuthenticationProvider())
        .authorizeRequests()
        	.antMatchers("/company/login").permitAll() // 로그인 URL에 대해 권한 필요 없음
        	.antMatchers("/company/signup").permitAll() // 회원가입 URL에 대해 권한 필요 없음
        	.and()
        .formLogin()
        	.loginPage("/company/login")
        	.permitAll()
            .usernameParameter("id")
        	.successHandler(companyAuthenticationSuccessHandler())
        	.failureHandler(companyAuthenticationFailureHandler())
            .and()
        .logout()
        	.permitAll();
    }
}