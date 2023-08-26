package thuan.com.fa.demomvc.config;

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
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import thuan.com.fa.demomvc.auth.ApplicationUserService;
import thuan.com.fa.demomvc.filter.EncodingFilter;

@Configuration
@EnableWebSecurity()
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ApplicationUserService applicationUserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);
		http
			.authorizeRequests()
			.antMatchers("/", "/index", "/static/**", "/resources/**", "/js/**")
			.permitAll()
			.antMatchers("/product/**")
			.hasAnyRole(UserRole.ADMIN.name())
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.and()
			.exceptionHandling()
			.accessDeniedPage("/login/403");;
		// @formatter:on
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(applicationUserService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("user").password(passwordEncoder.encode("123456"))
				.roles(UserRole.MEMBER.name()).build();

		UserDetails admin = User.builder().username("admin").password(passwordEncoder.encode("123456"))
				.roles(UserRole.ADMIN.name()).build();
		return new InMemoryUserDetailsManager(user, admin);
	}
}
