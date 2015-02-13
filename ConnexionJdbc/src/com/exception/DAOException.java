package com.exception;

/**
 * Thrown when a data access object gets an exception
 * 
 * @author Rachid RAMI
 * 
 */
public class DAOException extends Exception {

	/**
	 * Serial Version number
	 */
	private static final long serialVersionUID = 1L;
    
    /**
     * Exception cause
     */
	Throwable exceptionCause = null;

	/**
	 * Create an instance of DAOException
	 * @param msg : String
	 */
	public DAOException(String msg) {
		super(msg);
	}

	/**
	 * Create an instance of DAOException with an argument of type Throwable
	 * @param exception : Throwable
	 */
	public DAOException(Throwable exception) {
		super(exception);
		this.exceptionCause = exception;
	}

	/**
	 * Create an instance of DAOException with two arguments
	 * @param msg : String
	 * @param exception : Throwable
	 */
	public DAOException(String msg, Throwable exception) {
		super(msg, exception);
		this.exceptionCause = exception;
	}
}