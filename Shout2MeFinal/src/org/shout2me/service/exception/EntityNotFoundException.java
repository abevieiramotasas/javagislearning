package org.shout2me.service.exception;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -705888226813290849L;
	
	public EntityNotFoundException(String message) {
		super(message);
	}

}
