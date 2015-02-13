package com.exception;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * Runtime Exception used in struts actions to avoid try {} catch {} blocks
 * 
 * @author Rachid RAMI
 * 
 */
public class ApplicationException extends NestableRuntimeException {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception cause
	 */
	Throwable exceptionCause = null;

	/**
	 * Create an instance of ApplicationException
	 * @param msg : String
	 */
	public ApplicationException(String msg) {
		super(msg);
	}

	/**
	 * Create an instance of ApplicationException with an argument of type
	 * Throwable
	 * @param exception : Throwable
	 */
	public ApplicationException(Throwable exception) {
		super(exception);
		this.exceptionCause = exception;
	}

	/**
	 * Create an instance of ApplicationException with two arguments
	 * @param msg : String
	 * @param exception : Throwable
	 */
	public ApplicationException(String msg, Throwable exception) {
		super(msg, exception);
		this.exceptionCause = exception;
	}
}