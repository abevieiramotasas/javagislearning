package org.shout2me.service.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.shout2me.service.exception.ValidationException;

@Provider
public class ValidationExceptionMapper implements
		ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException e) {
		return Response
				.status(400)
				.entity(e.getValidationError().getMessage() + " "
						+ e.getValidationField().getMessage())
				.type("text/plain").build();
	}

}
