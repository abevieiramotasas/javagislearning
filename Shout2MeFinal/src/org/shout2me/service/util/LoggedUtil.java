package org.shout2me.service.util;

import org.shout2me.encrypt.EncryptUtil;
import org.shout2me.entity.User;
import org.shout2me.entity.dao.UserDAO;
import org.shout2me.service.exception.ValidationException;

public class LoggedUtil {

	public static User getLogged(String key) {
		String[] values = ValidationUtil.validatesKey(key);
		UserDAO u_dao = new UserDAO();
		User u = u_dao.find(Long.parseLong(values[0]));
		if (u == null) {
			throw new ValidationException(ValidationErrors.INVALID,
					ValidationFields.KEY);
		}
		if (!key.equals(EncryptUtil.makeHashed(String.valueOf(u.getId()),
				u.getSalt()))) {
			throw new ValidationException(ValidationErrors.INVALID,
					ValidationFields.KEY);
		}
		return u;
	}

}
