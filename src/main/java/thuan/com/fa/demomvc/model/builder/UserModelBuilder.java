package thuan.com.fa.demomvc.model.builder;

public class UserModelBuilder {

	private String name;
	private int age;
	private String address;

	private UserModelBuilder(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.address = builder.address;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "UserModel [name=" + name + ", age=" + age + ", address=" + address + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String name;
		private int age;
		private String address;

		private Builder() {
			super();
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public UserModelBuilder build() {
			return new UserModelBuilder(this);
		}

	}

}
