package thuan.com.fa.demomvc.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import thuan.com.fa.demomvc.model.User;

@Controller
@RequestMapping(value = "/login")
@SessionAttributes(names = "user")
public class LoginController {

	@Value("${message1}")
	private String message1;

	@Value("#{'${id1}'}")
	private String id1;

	@ModelAttribute(name = "user")
	public User getUser() {
		return new User();
	}

	@GetMapping()
	public String login() {
		System.out.println("message1=" + message1);
		System.out.println("id1=" + id1);
		return "login";
	}

	@GetMapping(value = "/403")
	public String accesssDenied(Principal user, Model model) {

		if (user != null) {
			model.addAttribute("msg", "Hi " + user.getName() + ", You can not access this page!");
		} else {
			model.addAttribute("msg", "You can not access this page!");
		}

		return "accessDenied";
	}

	@PostMapping()
	public String doLogin(@ModelAttribute(value = "user") User user) {
		System.out.println("Login Controller");
		if (user != null) {
			System.out.println("user Name = " + user.getUserName());
			System.out.println("Password = " + user.getPassword());
		} else {
			System.out.println("user from session is null");
		}
		return "welcome";
	}

}
