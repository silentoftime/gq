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

import net.shopxx.dao.CharacterDao;
import net.shopxx.entity.Character;

import org.springframework.stereotype.Repository;

/**
 * Dao - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("characterDaoImpl")
public class CharacterDaoImpl extends BaseDaoImpl<Character, Long> implements CharacterDao {

	public Character findByStrokes(Integer mainLuckStrokes) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Character> criteriaQuery = criteriaBuilder.createQuery(Character.class);
		Root<Character> root = criteriaQuery.from(Character.class);
		criteriaQuery.select(root);
		if (mainLuckStrokes != null) {
			criteriaQuery.where(criteriaBuilder.equal(root.get("strokes"), mainLuckStrokes));
		}
		return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();

	}


}