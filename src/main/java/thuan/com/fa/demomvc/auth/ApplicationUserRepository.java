package thuan.com.fa.demomvc.auth;

import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import thuan.com.fa.demomvc.config.UserRole;
import thuan.com.fa.demomvc.entity.AppUser;

@Repository("applicationUserRepository")
public class ApplicationUserRepository implements ApplicationUserDao {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Optional<UserDetails> selectApplicationUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		AppUser userDB = session.find(AppUser.class, username);
		if (userDB != null) {
			String role = userDB.getRole();
			UserRole userRole = UserRole.valueOf(role.toUpperCase());
			UserDetails userDetails = User.builder().username(userDB.getUsername())
					.password(passwordEncoder.encode(userDB.getPassword())).authorities(userRole.grantedAuthorities())
					.build();
			return Optional.of(userDetails);
		}
		return Optional.empty();
	}

}
