
package com.monthly.expenses.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.monthly.expenses.constant.AppConstants;
import com.monthly.expenses.constant.MessageKey;
import com.monthly.expenses.domain.User;
import com.monthly.expenses.service.EmailService;
import com.monthly.expenses.util.LocaleUtil;

/**
 * The Class EmailServiceImpl.
 *
 * @author G Lokesh
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Value(MessageKey.ADMIN_EMAIL)
    private String adminEmail;

    @Autowired
    private EmailSender emailSender;

    private static final String USER_ACTIVATION = "email/userActivation";
    private static final String USER_REGISTRATION = "email/userRegistration";
    private static final String USER_FPRGOT_PASSWORD = "email/forgotPassword";


    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    MessageLocaleService messageLocaleService;



    /*
     * (non-Javadoc)
     *
     * @see com.springboot.angular.service.impl.EmailService#
     * sendUserRegistration(com.springboot.angular.domain.User,
     * java.lang.String)
     */
    @Override
    public Boolean sendUserRegistration(User user, String password) {
        Context context = new Context();
        context.setVariable(AppConstants.USER, user);
        context.setVariable(AppConstants.PASSWORD, password);
        LocaleUtil.setUserLocaleForEmail(context, user);
        String body = templateEngine.process(USER_REGISTRATION, context);
        String[] emailSubjectArgs = new String[]{" "};
        return emailSender.sendHtmlWithBCC(user.getEmail(), adminEmail, messageLocaleService.getMessageByLocale(MessageKey.MAIL_USER_REGISTRATION_SUBJECT, emailSubjectArgs, context.getLocale()), body);
    }


    /*
     * (non-Javadoc)
     *
     * @see com.springboot.angular.service.impl.EmailService#
     * sendForgotPassword(com.springboot.angular.domain.User,
     * java.lang.String)
     */
    @Override
    public Boolean sendForgotPassword(User user, String tempPassword) {
        Context context = new Context();
        context.setVariable(AppConstants.USER, user);
        context.setVariable(AppConstants.PASSWORD, tempPassword);
        LocaleUtil.setUserLocaleForEmail(context, user);
        String body = templateEngine.process(USER_FPRGOT_PASSWORD, context);
        return emailSender.sendHtml(user.getEmail(),messageLocaleService.getMessageByLocale(MessageKey.MAIL_FORGOT_PASSWORD_SUBJECT, context.getLocale()),body);
    }


    /*
     * (non-Javadoc)
     *
     * @see com.springboot.angular.service.impl.EmailService#
     * sendActivationStatus(com.springboot.angular.domain.User)
     */
    @Override
    public Boolean sendActivationStatus(User user) {
        Context context = new Context();
        context.setVariable(AppConstants.USER, user);
        LocaleUtil.setUserLocaleForEmail(context, user);
        String body = templateEngine.process(USER_ACTIVATION, context);
        return emailSender.sendHtml(user.getEmail(),messageLocaleService.getMessageByLocale(MessageKey.MAIL_USER_ACTIVATION_SUBJECT, context.getLocale()),body);
    }
}
