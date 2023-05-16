package egovframework.job.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import egovframework.job.service.MemberDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MemberDetailsService memberDetailService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
        .authorizeRequests()
        	.antMatchers("/member/**.do").permitAll()
        	.and()
        .formLogin()
        	.loginPage("/member/login.do")
        	.defaultSuccessUrl("/member/home.do")
        	.permitAll()
        	.usernameParameter("id")
        	.and()
        .logout()
        	.permitAll();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(memberDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}