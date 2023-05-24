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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
	
	@Bean
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
	    return new CustomAuthenticationSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler() {
	    return new CustomAuthenticationFailureHandler();
	}
	
	@Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		
		http.cors();
		
    	http.csrf().disable()
    	.authenticationProvider(memberAuthenticationProvider())
        .authorizeRequests()
        	.antMatchers("/member/login").permitAll() // 로그인 URL에 대해 권한 필요 없음
        	.antMatchers("/member/signup").permitAll() // 회원가입 URL에 대해 권한 필요 없음
        	.and()
        .formLogin()
        	.loginPage("/member/login")
        	.successHandler(customAuthenticationSuccessHandler())
        	.failureHandler(customAuthenticationFailureHandler())
            .permitAll()
            .usernameParameter("id")
            .and()
        .logout()
        	.permitAll();
    }
}