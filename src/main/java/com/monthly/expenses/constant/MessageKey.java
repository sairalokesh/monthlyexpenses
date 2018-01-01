
package com.monthly.expenses.constant;

/**
 * The Interface AppConstants.
 *
 * @author G Lokesh
 */
public interface MessageKey {
	
    String MAIL_USER_ACTIVATION_SUBJECT = "spring.mail.user.activation";
    String MAIL_USER_REGISTRATION_SUBJECT = "spring.mail.user.registration";
    String MAIL_FORGOT_PASSWORD_SUBJECT = "spring.mail.user.forgotPassword";
    String ADMIN_EMAIL = "${spring.mail.sales.admin}";
    String EXCEPTION_USER_DUPLICATE_EMAIL = "exception.user.duplicate.email";
}
