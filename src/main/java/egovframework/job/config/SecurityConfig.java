/*
 * package egovframework.job.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 * import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.method.configuration.
 * EnableGlobalMethodSecurity; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import
 * org.springframework.security.config.annotation.web.builders.WebSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * import egovframework.job.service.MemberService;
 * 
 * 
 * @Configuration
 * 
 * @EnableWebSecurity
 * 
 * @EnableGlobalMethodSecurity(prePostEnabled = true) public class
 * SecurityConfig extends WebSecurityConfigurerAdapter {
 * 
 * @Autowired private MemberService memberService;
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.authorizeRequests() .antMatchers("/").permitAll()
 * .antMatchers("/login").permitAll() .antMatchers("/register").permitAll()
 * .anyRequest().authenticated() .and() .formLogin()
 * .loginPage("/login").permitAll() .defaultSuccessUrl("/") .and() .logout()
 * .logoutUrl("/logout") .logoutSuccessUrl("/") .invalidateHttpSession(true); }
 * 
 * @Override public void configure(WebSecurity web) throws Exception {
 * web.ignoring().antMatchers("/css/**", "/js/**", "/img/**"); }
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.authenticationProvider(authenticationProvider()); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Bean public DaoAuthenticationProvider authenticationProvider() {
 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
 * authProvider.setMemberDetailsService(memberService);
 * authProvider.setPasswordEncoder(passwordEncoder()); return authProvider; }
 * 
 * @Bean
 * 
 * @Override public AuthenticationManager authenticationManagerBean() throws
 * Exception { return super.authenticationManagerBean(); } }
 */