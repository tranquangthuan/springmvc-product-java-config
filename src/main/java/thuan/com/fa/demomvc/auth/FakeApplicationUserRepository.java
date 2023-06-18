package thuan.com.fa.demomvc.auth;

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
		UserDetails user = User.builder().username("user").password(passwordEncoder.encode("123456"))
				.roles(UserRole.MEMBER.name()).build();

		UserDetails admin = User.builder().username("admin").password(passwordEncoder.encode("123456"))
				.roles(UserRole.ADMIN.name()).build();

		List<UserDetails> applicationUsers = List.of(user, admin);

		return applicationUsers;
	}
}
