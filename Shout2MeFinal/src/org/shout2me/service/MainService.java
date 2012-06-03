package org.shout2me.service;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.shout2me.encrypt.EncryptUtil;
import org.shout2me.entity.User;
import org.shout2me.service.exception.ValidationException;
import org.shout2me.service.util.ValidationErrors;
import org.shout2me.service.util.ValidationFields;
import org.shout2me.service.util.ValidationUtil;

@Path("/")
public class MainService {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/login")
	public Response login(@FormParam("mail") String mail,
			@FormParam("password") String password,
			@Context HttpServletResponse response) {
		User u = ValidationUtil.validatesExistsMail(mail);
		if (EncryptUtil.verifyPassowrd(password, u.getPassword())) {
			return Response
					.status(200)
					.entity(EncryptUtil.makeHashed(String.valueOf(u.getId()),
							u.getSalt())).build();
		} else {
			throw new ValidationException(ValidationErrors.INVALID,
					ValidationFields.PASSWORD);
		}
	}

}
