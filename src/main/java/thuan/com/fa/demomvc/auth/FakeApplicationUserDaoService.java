package thuan.com.fa.demomvc.auth;

import static thuan.com.fa.demomvc.config.UserRole.ADMIN;
import static thuan.com.fa.demomvc.config.UserRole.ADMIN_TRAINEE;
import static thuan.com.fa.demomvc.config.UserRole.STUDENT;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers().stream().filter(appUser -> appUser.getUsername().equals(username)).findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {
		List<ApplicationUser> applicationUsers = List.of(
				new ApplicationUser("anna", passwordEncoder.encode("123456"), STUDENT.grantedAuthorities(), true, true,
						true, true),
				new ApplicationUser("tom", passwordEncoder.encode("123456"), ADMIN_TRAINEE.grantedAuthorities(), true,
						true, true, true),
				new ApplicationUser("linda", passwordEncoder.encode("123456"), ADMIN.grantedAuthorities(), true, true,
						true, true));

		return applicationUsers;
	}
}
