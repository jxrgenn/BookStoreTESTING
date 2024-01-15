package main;

public class NoBooksException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBooksException() {
	super ("This book is out of stock!");
	}
}
