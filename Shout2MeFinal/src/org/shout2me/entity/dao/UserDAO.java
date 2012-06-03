package org.shout2me.entity.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.shout2me.entity.User;



public class UserDAO extends GenericDAO<User> {

	public UserDAO() {
		super(User.class);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = this.getManager().createNamedQuery(
				"User.findAll", User.class);
		return query.getResultList();
	}

	/**
	 * Método que verifica se o {@code mail} já é utilizado por algum {@link User} do sistema.
	 * @param mail
	 * @return
	 * 	{@code true} caso o email já esteja cadastrado no sistema.
	 */
	public Boolean existsMail(String mail) {
		TypedQuery<Long> query = this.getManager().createNamedQuery(
				"User.existsMail", Long.class);
		query.setParameter("user_mail", mail);
		return query.getSingleResult() == 1L;
	}

	/**
	 * Método que busca um {@link User} no sistema, dado seu {@code mail}
	 * @param mail
	 * @return
	 * 	{@link User} caso o mail esteja cadastrado
	 *  {@code null} caso contrário
	 */
	public User findByMail(String mail) {
		TypedQuery<User> query = this.getManager().createNamedQuery(
				"User.findByMail", User.class);
		query.setParameter("user_mail", mail);
		User u = null;
		try {
			u = query.getSingleResult();
		} catch (NoResultException e) {
		}
		return u;
	}

}
