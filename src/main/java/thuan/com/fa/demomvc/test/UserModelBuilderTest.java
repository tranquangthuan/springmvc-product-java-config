package thuan.com.fa.demomvc.test;

import thuan.com.fa.demomvc.model.builder.ResponseBuilder;
import thuan.com.fa.demomvc.model.builder.UserModelBuilder;

public class UserModelBuilderTest {

	public static void main(String[] args) {
		UserModelBuilder userModel = UserModelBuilder.builder().name("thuan").address("DN").build();

		ResponseBuilder<UserModelBuilder> reponse = new ResponseBuilder<UserModelBuilder>().builder().result(userModel)
				.build();

		System.out.println(reponse);

	}

}
