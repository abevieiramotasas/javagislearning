package org.shout2me.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.shout2me.entity.Island;
import org.shout2me.entity.Message;
import org.shout2me.entity.User;
import org.shout2me.entity.dao.IslandDAO;
import org.shout2me.entity.dao.MessageDAO;
import org.shout2me.entity.to.MessageTO;
import org.shout2me.service.exception.EntityNotFoundException;
import org.shout2me.service.util.LoggedUtil;
import org.shout2me.service.util.ValidationUtil;

@Path("/message")
public class MessageService {

	private static final String ISLAND_NOT_FOUND = "island not found";

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/create")
	public Response create(@FormParam("text") String text,
			@FormParam("topic") String topic,
			@FormParam("destination_id") Long destination_id,
			@FormParam("key") String key) {
		// validations
		ValidationUtil.validatesText(text);
		ValidationUtil.validatesTopic(topic);
		// persist
		User author = LoggedUtil.getLogged(key);
		Message m = new Message();
		m.setText(text);
		m.setTopic(topic);
		m.setAuthor(author);
		IslandDAO i_dao = new IslandDAO();
		Island destination = i_dao.find(destination_id);
		if(destination == null) {
			throw new EntityNotFoundException(ISLAND_NOT_FOUND);
		}
		m.setDestination(destination);
		MessageDAO m_dao = new MessageDAO();
		m_dao.create(m);
		return Response.status(200).build();
	}


	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/getall")
	public List<MessageTO> getAllByIsland(
			@QueryParam("destination_id") Long destination_id,
			@QueryParam("limit") Integer limit,
			@QueryParam("base_id") Long base_id,
			@QueryParam("up") Boolean up) {
		MessageDAO m_dao = new MessageDAO();
		List<Message> messages = m_dao.findAllLimited(destination_id, base_id, up, limit);
		List<MessageTO> result = new ArrayList<MessageTO>();
		for (Message m : messages) {
			result.add(new MessageTO(m));
		}
		return result;
	}
}
