package thuan.com.fa.demomvc.model;

public class EmailModel {
	private String subject;
	private String content;
	private String sendToEmail;

	public EmailModel() {
		super();
	}

	public EmailModel(String subject, String content, String sendToEmail) {
		super();
		this.subject = subject;
		this.content = content;
		this.sendToEmail = sendToEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendToEmail() {
		return sendToEmail;
	}

	public void setSendToEmail(String sendToEmail) {
		this.sendToEmail = sendToEmail;
	}

}
