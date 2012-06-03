package org.shout2me.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * 
 * @author abelardo
 * 
 * @param <E>
 *            Tipo da entidade a ser controlada pelo DAO
 */
public abstract class GenericDAO<E> implements GenericDAOInterface<E> {

	private static final String SHOUT2_ME = "Shout2Me";
	private EntityManager manager = null;
	private Class<E> classe = null;

	public GenericDAO(Class<E> classe) {
		this.classe = classe;
	}

	protected EntityManager getManager() {
		if (manager == null) {
			manager = Persistence.createEntityManagerFactory(SHOUT2_ME)
					.createEntityManager();
		}
		return manager;
	}

	protected Session getSession() {
		return (Session) this.getManager().getDelegate();
	}

	@Override
	public E find(Long id) {
		E entity = this.getManager().find(this.classe, id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		Criteria criteria = this.getSession().createCriteria(classe);
		return (List<E>) criteria.list();
	}

	@Override
	public void create(E entity) {
		this.getManager().getTransaction().begin();
		this.getManager().persist(entity);
		this.getManager().getTransaction().commit();
	}

	@Override
	public void remove(Long id) {
		E entity = this.find(id);
		if (entity == null) {
			return;
		}
		this.getManager().getTransaction().begin();
		this.getManager().remove(entity);
		this.getManager().getTransaction().commit();
	}

	@Override
	public void update(E entity) {
		this.getManager().getTransaction().begin();
		this.getManager().merge(entity);
		this.getManager().getTransaction().commit();
	}

}
