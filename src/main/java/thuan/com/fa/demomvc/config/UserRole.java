package thuan.com.fa.demomvc.config;

public enum UserRole {
	// @formatter:off
	MEMBER, 
	ADMIN,
	OTHER;
	// @formatter:on

	public static UserRole of(String userRole) {

		for (UserRole role : UserRole.values()) {
			if (role.name().equalsIgnoreCase(userRole)) {
				return role;
			}
		}
		return UserRole.OTHER;
	}

}
