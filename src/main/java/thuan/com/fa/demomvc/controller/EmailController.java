package thuan.com.fa.demomvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import thuan.com.fa.demomvc.model.EmailModel;
import thuan.com.fa.demomvc.service.SendgridServiceImpl;

@Controller
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private SendgridServiceImpl sendgridServiceImpl;

	@GetMapping("/")
	public String showSendEmailForm(Model model) {
		model.addAttribute("emailForm", new EmailModel());
		return "/email/send";
	}

	@PostMapping("/send")
	public String sendEmail(Model model, @ModelAttribute(name = "emailForm") EmailModel emailModel) {
		sendgridServiceImpl.sendMail(emailModel.getSubject(), emailModel.getContent(),
				List.of(emailModel.getSendToEmail()), null, null);
		return "/email/success";
	}
}
