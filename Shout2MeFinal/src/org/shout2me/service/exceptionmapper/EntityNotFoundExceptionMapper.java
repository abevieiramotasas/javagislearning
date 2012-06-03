package org.shout2me.service.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.shout2me.service.exception.EntityNotFoundException;

@Provider
public class EntityNotFoundExceptionMapper implements
		ExceptionMapper<EntityNotFoundException> {

	@Override
	public Response toResponse(EntityNotFoundException e) {
		return Response.status(404).entity(e.getMessage())
				.type("text/plain").build();
	}

}
