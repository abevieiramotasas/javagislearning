package org.shout2me.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.shout2me.entity.Island;
import org.shout2me.entity.User;
import org.shout2me.entity.dao.IslandDAO;
import org.shout2me.entity.to.IslandTO;
import org.shout2me.postgis.util.PostGISUtil;
import org.shout2me.service.exception.ValidationException;
import org.shout2me.service.util.LoggedUtil;
import org.shout2me.service.util.ValidationErrors;
import org.shout2me.service.util.ValidationFields;
import org.shout2me.service.util.ValidationUtil;

import com.vividsolutions.jts.io.ParseException;

@Path("/island")
public class IslandService {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{key}/create")
	public Response create(@FormParam("name") String name,
			@FormParam("longitude") Double longitude,
			@FormParam("latitude") Double latitude,
			@FormParam("description") String description,
			@PathParam("key") String key) {
		// validations
		ValidationUtil.validatesIslandName(name);
		ValidationUtil.validatesLongitude(longitude);
		ValidationUtil.validatesLatitude(latitude);
		ValidationUtil.validatesDescription(description);		
		User loggedUser = LoggedUtil.getLogged(key);
		// persist
		Island i = new Island();
		try {
			i.setLocation((PostGISUtil.getPoint(longitude, latitude)));
		} catch (ParseException e) {
			throw new ValidationException(ValidationErrors.OUT_OF_RANGE,
					ValidationFields.LAT_LON);
		}
		i.setName(name);
		i.setDescription(description);
		i.setRank(0.0);
		i.setOwner(loggedUser);
		IslandDAO i_dao = new IslandDAO();
		i_dao.create(i);
		return Response.status(200).entity(String.valueOf(i.getId())).build();
	}

	@GET
	@Path("/getall")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<IslandTO> getByDistance(
			@QueryParam("longitude") Double longitude,
			@QueryParam("latitude") Double latitude,
			@QueryParam("distance") Double distance,
			@QueryParam("max_results") Integer max_results) {
		// validations
		ValidationUtil.validatesLongitude(longitude);
		ValidationUtil.validatesLatitude(latitude);
		ValidationUtil.validatesDistance(distance);
		// get
		IslandDAO i_dao = new IslandDAO();
		List<Island> islands = null;
		try {
			islands = i_dao.findAllNear(
					PostGISUtil.getPoint(longitude, latitude), distance,
					max_results);
		} catch (ParseException e) {
			throw new ValidationException(ValidationErrors.OUT_OF_RANGE,
					ValidationFields.LAT_LON);
		}
		List<IslandTO> result = new ArrayList<IslandTO>();
		for (Island i : islands) {
			result.add(new IslandTO(i));
		}
		return result;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public IslandTO getById(@QueryParam("id") Long id) {
		IslandDAO i_dao = new IslandDAO();
		Island i = i_dao.find(id);
		return (new IslandTO(i));
	}

}
