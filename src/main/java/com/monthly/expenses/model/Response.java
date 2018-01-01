
package com.monthly.expenses.model;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The Class Response.
 * 
 * @author G Lokesh
 */
public class Response implements Serializable {

	private static final long serialVersionUID = 550083014421155676L;

	/**
	 * The timestamp.
	 */
	public Long timestamp;

	/**
	 * The status.
	 */
	public Integer status;

	/**
	 * The error.
	 */
	public String error;

	/**
	 * The exception.
	 */
	public String exception;

	/**
	 * The message.
	 */
	public String message;

	/**
	 * The path.
	 */
	public String path;

	/**
	 * Instantiates a new response.
	 *
	 * @param message
	 *            the message
	 * @param status
	 *            the status
	 */
	public Response(String message, Integer status) {
		this.message = message;
		this.status = status;
		HttpStatus httpStatus = HttpStatus.valueOf(status);
		if (httpStatus.is4xxClientError() || httpStatus.is5xxServerError()) {
			this.error = httpStatus.getReasonPhrase();
		}
		this.timestamp = new Date().getTime();
	}

	public static Response success(String message) {
		return new Response(message, HttpStatus.OK.value());
	}

	public static Response serverError(String message) {
		return new Response(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	public static Response error(String message, Integer status, String exception) {
		Response response = new Response(message, status);
		response.exception = exception;
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		response.path = request.getRequestURI();
		return response;
	}

}
