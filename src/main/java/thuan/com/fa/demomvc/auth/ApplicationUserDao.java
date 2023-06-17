package thuan.com.fa.demomvc.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

public interface ApplicationUserDao {
	public Optional<UserDetails> selectApplicationUserByUsername(String username);
}
