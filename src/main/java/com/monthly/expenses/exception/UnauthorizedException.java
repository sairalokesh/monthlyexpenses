
package com.monthly.expenses.exception;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class UnauthorizedException.
 * 
 * @author G Lokesh
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 7363042762491142285L;
	protected static MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	/**
	 * Instantiates a new Unauthorized exception.
	 *
	 * @param objectGroupId
	 *            the object group id
	 */
	public UnauthorizedException() {
		super(messages.getMessage("AbstractAccessDecisionManager.accessDenied", "Access is denied"));
	}

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message
	 *            the message
	 */
	public UnauthorizedException(String message) {
		super(message);
	}

}
