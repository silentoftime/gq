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

import net.shopxx.dao.ThreeTalentDao;
import net.shopxx.entity.ThreeTalent;

import org.springframework.stereotype.Repository;

/**
 * Dao - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("threeTalentDaoImpl")
public class ThreeTalentDaoImpl extends BaseDaoImpl<ThreeTalent, Long> implements ThreeTalentDao {

	public ThreeTalent findByThreeElement(String threeElement) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ThreeTalent> criteriaQuery = criteriaBuilder.createQuery(ThreeTalent.class);
		Root<ThreeTalent> root = criteriaQuery.from(ThreeTalent.class);
		criteriaQuery.select(root);
		if (threeElement != null) {
			criteriaQuery.where(criteriaBuilder.equal(root.get("threeElement"), threeElement));
		}
		return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();

	}


}