package egovframework.job.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import egovframework.job.service.MemberDetailsService;

@Configuration
@EnableWebSecurity
@Order(1) 
public class MemberSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MemberDetailsService memberDetailsService;
	
	@Bean
    public PasswordEncoder memberPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider memberAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(memberDetailsService);
        provider.setPasswordEncoder(memberPasswordEncoder());
        return provider;
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		
    	http.csrf().disable()
    	.authenticationProvider(memberAuthenticationProvider())
        .authorizeRequests()
        	.antMatchers("/member/**").hasRole("MEMBER")
        	.antMatchers("/member/info/**").hasRole("MEMBER")
        	.and()
        .formLogin()
            .permitAll()
            .usernameParameter("id")
            .successHandler((request, response, authentication) -> {
                response.setContentType("application/json");
                response.getWriter().write("{\"message\":\"로그인이 성공하였습니다.\"}");
            })
            .and()
        .logout()
        	.permitAll();
    }
	
	
}