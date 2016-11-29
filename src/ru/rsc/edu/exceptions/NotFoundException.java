package ru.rsc.edu.exceptions;

public class NotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String msg) {
		super(msg);
	}
	
	public NotFoundException(String entityName, String propertyName, Object desiredValue) {
		super("There's no " + entityName + " with " + propertyName + " " + desiredValue.toString());
	}
}
