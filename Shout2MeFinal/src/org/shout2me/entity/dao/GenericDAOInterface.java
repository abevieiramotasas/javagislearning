package org.shout2me.entity.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

/**
 * 
 * @author abelardo
 * 
 * @param <E>
 *            Tipo da entidade a ser controlada pelo DAO
 */
public interface GenericDAOInterface<E> {
	/**
	 * 
	 * @param id
	 * @return entidade com id = <code>id</code>
	 * @throws EntityNotFoundException
	 */
	public E find(Long id);

	/**
	 * 
	 * @return lista com entidades do tipo <code>E</code> lista vazia, caso não
	 *         existam entidades do tipo <code>E</code>
	 */
	public List<E> findAll();

	/**
	 * 
	 * @param entity
	 *            adiciona a entidade <code>entity</code> ao banco
	 */
	public void create(E entity);

	/**
	 * 
	 * @param id
	 *            remove a entidade com id <code>id</code> do banco
	 * @throws EntityNotFoundException
	 */
	public void remove(Long id);

	/**
	 * 
	 * @param entity
	 *            atualiza a entidade <code>entity</code> no banco
	 * @throws EntityNotFoundException
	 */
	public void update(E entity);
}
