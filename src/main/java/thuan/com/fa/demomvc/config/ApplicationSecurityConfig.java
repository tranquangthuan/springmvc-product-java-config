package thuan.com.fa.demomvc.config;

import static thuan.com.fa.demomvc.config.UserRole.ADMIN;
import static thuan.com.fa.demomvc.config.UserRole.ADMIN_TRAINEE;
import static thuan.com.fa.demomvc.config.UserRole.STUDENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity()
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	// private final ApplicationUserService applicationUserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
			.antMatchers("/", "/index", "/static/**", "/css/**", "/js/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true);
		// @formatter:on
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails anna = User.builder().username("anna").password(passwordEncoder.encode("123456"))
				.roles(STUDENT.name()).authorities(STUDENT.grantedAuthorities()).build();

		UserDetails linda = User.builder().username("linda").password(passwordEncoder.encode("123456"))
				.roles(ADMIN.name()).authorities(ADMIN.grantedAuthorities()).build();

		UserDetails tom = User.builder().username("tom").password(passwordEncoder.encode("123456"))
				.roles(ADMIN_TRAINEE.name()).authorities(ADMIN_TRAINEE.grantedAuthorities()).build();
		return new InMemoryUserDetailsManager(anna, linda, tom);
	}
}
