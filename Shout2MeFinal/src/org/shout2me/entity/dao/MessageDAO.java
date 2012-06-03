package org.shout2me.entity.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.shout2me.entity.Message;


public class MessageDAO extends GenericDAO<Message> {

	public MessageDAO() {
		super(Message.class);
	}

	@Override
	public List<Message> findAll() {
		TypedQuery<Message> query = this.getManager().createNamedQuery(
				"Message.findAll", Message.class);
		return query.getResultList();
	}

	/**
	 * Busca um total máximo de mensagens {@code limit} a partir(exclusive) de
	 * {@code id_base} da ilha de id {@code island_id}. Tras mensagens de
	 * {@code id} maior que {@code id_base} se {@code direction} for
	 * {@code True}, mensagens de {@code id} menor, caso contrário.
	 * @NamedQuery(name = "Message.findLimitedGreater", query = "SELECT m FROM Message m WHERE m.id > :id_base AND m.destination.id = :island_id "),
	 * 
	 * @param destination_id
	 * @param base_id
	 * @param direction
	 * @param max_results
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Message> findAllLimited(Long destination_id, Long base_id,
			Boolean direction, Integer max_results) {
		Criteria criteria = this.getSession().createCriteria(Message.class);
		if(direction) {
			criteria.add(Restrictions.gt("id", base_id));
		} else {
			criteria.add(Restrictions.lt("id", base_id));
		}
		criteria.add(Restrictions.eq("destination.id", destination_id));
		criteria.setMaxResults(max_results);
		return (List<Message>) criteria.list();
		
		
//		TypedQuery<Message> query = null;
//		query = direction ? this.getManager().createNamedQuery(
//				"Message.findLimitedGreater", Message.class) : this
//				.getManager().createNamedQuery("Message.findLimitedLower",
//						Message.class);
//		query.setParameter("id_base", base_id);
//		query.setParameter("island_id", destination_id);
//		query.setMaxResults(max_results);
//		return query.getResultList();
	}

}
