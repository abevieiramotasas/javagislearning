package org.shout2me.service.exception;

import org.shout2me.service.util.ValidationErrors;
import org.shout2me.service.util.ValidationFields;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -3116508969541266961L;
	private ValidationErrors validationError;
	private ValidationFields validationField;

	public ValidationException(ValidationErrors validationError,
			ValidationFields validationField) {
		this.validationError = validationError;
		this.validationField = validationField;
	}

	public ValidationErrors getValidationError() {
		return validationError;
	}

	public void setValidationField(ValidationFields validationField) {
		this.validationField = validationField;
	}

	public ValidationFields getValidationField() {
		return validationField;
	}

}
