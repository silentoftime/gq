/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.shopxx.dao.MatheMaticalDao;
import net.shopxx.entity.MatheMatical;

import org.springframework.stereotype.Repository;

/**
 * Dao - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("matheMaticalDaoImpl")
public class MatheMaticalDaoImpl extends BaseDaoImpl<MatheMatical, Long> implements MatheMaticalDao {

	public MatheMatical findByStrokes(Integer firstLuckStrokes) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MatheMatical> criteriaQuery = criteriaBuilder.createQuery(MatheMatical.class);
		Root<MatheMatical> root = criteriaQuery.from(MatheMatical.class);
		criteriaQuery.select(root);
		if (firstLuckStrokes != null) {
			criteriaQuery.where(criteriaBuilder.equal(root.get("strokes"), firstLuckStrokes));
		}
		return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();

	}


}