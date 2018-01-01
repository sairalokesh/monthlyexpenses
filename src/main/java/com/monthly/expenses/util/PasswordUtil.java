
package com.monthly.expenses.util;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Class PasswordUtil.
 * 
 * @author G Lokesh
 */
public class PasswordUtil {

	static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Checks if is matching.
	 *
	 * @param rawPassword
	 *            the raw password
	 * @param hashedPassword
	 *            the hashed password
	 * @return the boolean
	 */
	public static Boolean isMatching(String rawPassword, String hashedPassword) {
		return passwordEncoder.matches(rawPassword, hashedPassword);
	}

	/**
	 * Gets the password hash.
	 *
	 * @param rawPassword
	 *            the raw password
	 * @return the password hash
	 */
	public static String getPasswordHash(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	/**
	 * Sets the temp password.
	 *
	 * @param user
	 *            the user
	 * @return the string
	 */
	public static String getTempPassword() {
		int len = 6;
		final String allowedCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom random = new SecureRandom();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
		String password = sb.toString();
		return password;
	}
}