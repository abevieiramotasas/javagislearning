package org.shout2me.entity.dao;

import java.awt.Shape;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernatespatial.criterion.SpatialRestrictions;
import org.shout2me.entity.Island;

import com.vividsolutions.jts.geom.Point;

public class IslandDAO extends GenericDAO<Island> {

	public IslandDAO() {
		super(Island.class);
	}

	@Override
	public List<Island> findAll() {
		TypedQuery<Island> query = this.getManager().createNamedQuery("Island.findAll", Island.class);
		return query.getResultList();
	}
		
	/**
	 * Método retorna todas {@link Island} cujo {@link Shape} tenha interseção com o círculo de centro {@code location}
	 * 	e de raio {@code distance}, ordenados pelo {@link Island#rank}
	 * @param location
	 * 		representa o centro do círculo de busca por ilhas
	 * @param distance
	 * 		representa o raio do círculo de busca
	 * @param max_results
	 * 		representa o número máximo de resultados a serem buscados e retornados
	 * @return
	 * 		lista de {@link Island}
	 */
	@SuppressWarnings("unchecked")
	public List<Island> findAllNear(Point location, Double distance, Integer max_results) {
		Criteria criteria = this.getSession().createCriteria(Island.class);
        criteria.add(SpatialRestrictions.distanceWithin("shape", location, distance));
        criteria.addOrder(Order.desc("rank"));
        criteria.setMaxResults(max_results);
		return (List<Island>) criteria.list();
	}

}
