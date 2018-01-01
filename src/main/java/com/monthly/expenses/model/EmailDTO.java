/**
 * 
 */
package com.monthly.expenses.model;

/**
 * @author G Lokesh
 *
 */
public class EmailDTO {
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String text;
	private Boolean isHtml;

	public EmailDTO() {
	}

	public EmailDTO(String to, String cc, String bcc, String subject, String text, Boolean isHtml) {
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.text = text;
		this.isHtml = isHtml;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIsHtml() {
		return isHtml;
	}

	public void setIsHtml(Boolean isHtml) {
		this.isHtml = isHtml;
	}

}
