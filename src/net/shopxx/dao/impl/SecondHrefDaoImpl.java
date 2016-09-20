/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import net.shopxx.dao.SecondHrefDao;
import net.shopxx.entity.SecondHref;

import org.springframework.stereotype.Repository;

/**
 * Dao - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("secondHrefDaoImpl")
public class SecondHrefDaoImpl extends BaseDaoImpl<SecondHref, Long> implements SecondHrefDao {

	public List<SecondHref> findLeft() {
		String jpql = "select s from  SecondHref s  where s.unicode not in(select t.unicode from TraditionalCharacter t)";	
//		CriteriaQuery<SecondHref> entityManager.createQuery(jpql, SecondHref.class);
		TypedQuery<SecondHref> query = entityManager.createQuery(jpql, SecondHref.class).setFlushMode(FlushModeType.COMMIT);
		query.setFirstResult(0);
        query.setMaxResults(1000);
        return query.getResultList();
//		Pageable pageable;
//		return super.findPage(entityManager.createQuery(jpql, SecondHref.class), pageable);
//		return entityManager.createQuery(jpql, SecondHref.class).setFlushMode(FlushModeType.COMMIT).getResultList();
	}


}