
package com.monthly.expenses.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monthly.expenses.domain.User;

/**
 * The Class UserDTO.
 * 
 * @author G Lokesh
 */
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -5488291851737060509L;

	private User user;
	private String termsAndConditions;
	// need to remove
	private String token;
	@JsonIgnore
	private String newPassword;

	/**
	 * Instantiates a new user DTO.
	 */
	public UserDTO() {
		super();
	}

	/**
	 * Instantiates a new user DTO.
	 *
	 * @param user
	 *            the user
	 * @param termsAndConditions
	 *            the terms and conditions
	 * @param token
	 *            the token
	 */
	public UserDTO(User user, String termsAndConditions, String token) {
		super();
		this.user = user;
		this.termsAndConditions = termsAndConditions;
		this.token = token;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the terms and conditions.
	 *
	 * @return the terms and conditions
	 */
	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	/**
	 * Sets the terms and conditions.
	 *
	 * @param termsAndConditions
	 *            the new terms and conditions
	 */
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token
	 *            the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the new password.
	 *
	 * @return the new password
	 */
	@JsonIgnore
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the new password.
	 *
	 * @param newPassword
	 *            the new new password
	 */
	@JsonProperty
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
