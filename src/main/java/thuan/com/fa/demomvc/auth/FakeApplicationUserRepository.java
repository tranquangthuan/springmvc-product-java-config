package thuan.com.fa.demomvc.auth;

import static thuan.com.fa.demomvc.config.UserRole.STUDENT;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import thuan.com.fa.demomvc.config.UserRole;

@Repository("fakeApplicationUserRepository")
public class FakeApplicationUserRepository implements ApplicationUserDao {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Optional<UserDetails> selectApplicationUserByUsername(String username) {
		return getApplicationUsers().stream().filter(appUser -> appUser.getUsername().equals(username)).findFirst();
	}

	private List<UserDetails> getApplicationUsers() {
		UserDetails anna = User.builder().username("anna").password(passwordEncoder.encode("123456"))
				.authorities(STUDENT.grantedAuthorities()).build();

		UserDetails tom = User.builder().username("tom").password(passwordEncoder.encode("123456"))
				.authorities(UserRole.ADMIN_TRAINEE.grantedAuthorities()).build();

		UserDetails linda = User.builder().username("linda").password(passwordEncoder.encode("123456"))
				.authorities(UserRole.ADMIN.grantedAuthorities()).build();

		List<UserDetails> applicationUsers = List.of(anna, tom, linda);

		return applicationUsers;
	}
}
