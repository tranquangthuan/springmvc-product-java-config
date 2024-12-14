package thuan.com.fa.demomvc.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

@Service
public class SendgridServiceImpl {
	private static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

	private static final String SEND_GRID_ENDPOINT_SEND_EMAIL = "mail/send";

	// @Value("${send_grid.api_key}")
	private String sendGridApiKey;

	// @Value("${send_grid.from_email}")
	private String sendGridFromEmail;

	// @Value("${send_grid.from_name}")
	private String sendGridFromName;

	public void sendMail(String subject, String content, List<String> sendToEmails, List<String> ccEmails,
			List<String> bccEmails) {
		Mail mail = buildMailToSend(subject, content, sendToEmails, ccEmails, bccEmails);
		send(mail);
	}

	private void send(Mail mail) {
		SendGrid sg = new SendGrid(sendGridApiKey);
		// sg.addRequestHeader(KEY_X_MOCK, VALUE_TRUE);

		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint(SEND_GRID_ENDPOINT_SEND_EMAIL);
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private Mail buildMailToSend(String subject, String contentStr, List<String> sendToEmails, List<String> ccEmails,
			List<String> bccEmails) {
		Mail mail = new Mail();

		Email fromEmail = new Email();
		fromEmail.setName(sendGridFromName);
		fromEmail.setEmail(sendGridFromEmail);

		mail.setFrom(fromEmail);

		mail.setSubject(subject);

		Personalization personalization = new Personalization();

		// Add sendToEmails
		if (sendToEmails != null) {
			for (String email : sendToEmails) {
				Email to = new Email();
				to.setEmail(email);
				personalization.addTo(to);
			}
		}

		// Add ccEmail
		if (ccEmails != null) {
			for (String email : ccEmails) {
				Email cc = new Email();
				cc.setEmail(email);
				personalization.addCc(cc);
			}
		}

		// Add bccEmail
		if (bccEmails != null) {
			for (String email : bccEmails) {
				Email bcc = new Email();
				bcc.setEmail(email);
				personalization.addBcc(bcc);
			}
		}
		mail.addPersonalization(personalization);

		Content content = new Content();
		content.setType(CONTENT_TYPE_TEXT_PLAIN);
		content.setValue(contentStr);
		mail.addContent(content);
		return mail;
	}
}
