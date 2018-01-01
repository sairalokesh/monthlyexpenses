
package com.monthly.expenses.service;

import com.monthly.expenses.domain.User;

/**
 * The Interface EmailService.
 *
 * @author G Lokesh
 */
public interface EmailService {

  

    /**
     * Send activation status.
     *
     * @param user
     *            the user
     * @return the boolean
     */
    Boolean sendActivationStatus(User user);

    /**
     * Send user registration.
     *
     * @param user
     *            the user
     * @param password
     *            the password
     * @return the boolean
     */
    Boolean sendUserRegistration(User user, String password);

    /**
     * Send forgot password.
     *
     * @param user
     *            the user
     * @param tempPassword
     *            the temp password
     * @return the boolean
     */
    Boolean sendForgotPassword(User user, String tempPassword);

}