
package com.monthly.expenses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ResourceNotFoundException.
 * 
 * @author G Lokesh
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8173671732569868538L;

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param objectGroupId
	 *            the object group id
	 */
	public ResourceNotFoundException(Long objectGroupId) {
		super("Could not find resource with id: " + objectGroupId);
	}

	/**
	 * Instantiates a new resource not found exception.
	 *
	 * @param message
	 *            the message
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
