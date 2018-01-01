
package com.monthly.expenses.service.impl;

import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.monthly.expenses.model.EmailDTO;

/**
 * The Class EmailSender.
 *
 * @author G Lokesh
 */
@Service
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.from}")
    private String from;

    /**
     * Send plain text.
     *
     * @param to
     *            the to
     * @param cc
     *            the cc
     * @param subject
     *            the subject
     * @param text
     *            the text
     * @return the boolean
     */
    public Boolean sendPlainText(String to, String cc, String bcc, String subject, String text) {
        return send(to, cc, bcc, subject, text, false);
    }

    /**
     * Send html.
     *
     * @param to
     *            the to
     * @param cc
     *            the cc
     * @param cc
     *            the bcc
     * @param subject
     *            the subject
     * @param htmlBody
     *            the html body
     * @return the boolean
     */
    public Boolean sendHtmlWithCCAndBCC(String to, String cc, String bcc, String subject, String htmlBody) {
        return send(to, cc, bcc, subject, htmlBody, true);
    }

    /**
     * Send html.
     *
     * @param to
     *            the to
     * @param bcc
     *            the bcc
     * @param subject
     *            the subject
     * @param htmlBody
     *            the html body
     * @return the boolean
     */
    public Boolean sendHtmlWithBCC(String to, String bcc, String subject, String htmlBody) {
        return send(to, "", bcc, subject, htmlBody, true);
    }

    /**
     * Send html.
     *
     * @param to
     *            the to
     * @param cc
     *            the cc
     * @param subject
     *            the subject
     * @param htmlBody
     *            the html body
     * @return the boolean
     */
    public Boolean sendHtmlWithCC(String to, String cc, String subject, String htmlBody) {
        return send(to, cc, "", subject, htmlBody, true);
    }

    /**
     * Send html.
     *
     * @param to
     *            the to
     * @param subject
     *            the subject
     * @param htmlBody
     *            the html body
     * @return the boolean
     */
    public Boolean sendHtml(String to, String subject, String htmlBody) {
        return send(to, "", "", subject, htmlBody, true);
    }

    private Boolean send(String to, String cc, String bcc, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
            helper.setTo(to);
            if (!StringUtils.isEmpty(cc) && !cc.startsWith("@")) {
                helper.setCc(cc);
            }
            if (!StringUtils.isEmpty(bcc) && !bcc.startsWith("@")) {
                helper.setBcc(bcc);
            }
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean send(List<EmailDTO> emailDTOs) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
            for (EmailDTO emailDTO : emailDTOs) {
                helper.setTo(emailDTO.getTo());
                if (!StringUtils.isEmpty(emailDTO.getCc()) && !emailDTO.getCc().startsWith("@")) {
                    helper.setCc(emailDTO.getCc());
                }
                if (!StringUtils.isEmpty(emailDTO.getBcc()) && !emailDTO.getBcc().startsWith("@")) {
                    helper.setBcc(emailDTO.getBcc());
                }
                helper.setFrom(from);
                helper.setSubject(emailDTO.getSubject());
                helper.setText(emailDTO.getText(), emailDTO.getIsHtml());
                javaMailSender.send(mail);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean sendHtmlWithBCCWithCCC(String email, List<String> adminEmails, String adminEmail,String messageByLocale, String body) {
        return UserRegistrationEmail(email,adminEmails,adminEmail,messageByLocale,body,true);
    }

    private Boolean UserRegistrationEmail(String to, List<String> cc, String bcc, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
            helper.setTo(to);
            InternetAddress[] bccAddress = new InternetAddress[cc.size()];

            // To get the array of bccaddresses
            for( int i = 0; i < cc.size(); i++ ) {
                bccAddress[i] = new InternetAddress(cc.get(i));
            }

            // Set bcc: header field of the header.
            for( int i = 0; i < bccAddress.length; i++) {
                mail.addRecipient(Message.RecipientType.CC, bccAddress[i]);
            }


            if (!StringUtils.isEmpty(bcc) && !bcc.startsWith("@")) {
                helper.setBcc(bcc);
            }
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
