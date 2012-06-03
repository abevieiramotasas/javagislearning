package org.shout2me.service.util;

public enum ValidationErrors {
	// TODO refactor

	INVALID("invalid"), EMPTY("empty"), NOTFOUND("not found"), ALREADY_EXISTS(
			"alread exists"), OUT_OF_RANGE("out of range");

	private String message;

	private ValidationErrors(String message) {
		this.setMessage(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
