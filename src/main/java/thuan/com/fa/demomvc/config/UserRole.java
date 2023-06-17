package thuan.com.fa.demomvc.config;

import static thuan.com.fa.demomvc.config.UserPermissions.COURSE_READ;
import static thuan.com.fa.demomvc.config.UserPermissions.COURSE_WRITE;
import static thuan.com.fa.demomvc.config.UserPermissions.STUDENT_READ;
import static thuan.com.fa.demomvc.config.UserPermissions.STUDENT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
	// @formatter:off
	STUDENT(Set.of(STUDENT_WRITE, STUDENT_READ)), 
	ADMIN_TRAINEE(Set.of(COURSE_READ, STUDENT_READ)),
	ADMIN(Set.of(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));
	// @formatter:on

	private final Set<UserPermissions> permissions;

	UserRole(Set<UserPermissions> permissions) {
		this.permissions = permissions;
	}

	public Set<UserPermissions> getPermissions() {
		return permissions;
	}

	public Set<SimpleGrantedAuthority> grantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

		return permissions;
	}

	public UserRole of(String userRole) {

		for (UserRole role : UserRole.values()) {
			if (role.name().equalsIgnoreCase(userRole)) {
				return role;
			}
		}
		throw new IllegalArgumentException(String.format("%S not exist", userRole));
	}

}
