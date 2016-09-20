/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.shopxx.dao.InterpersonalSocialDao;
import net.shopxx.entity.InterpersonalSocial;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Dao - 文章分类
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("interpersonalSocialDaoImpl")
public class InterpersonalSocialDaoImpl extends BaseDaoImpl<InterpersonalSocial, Long> implements InterpersonalSocialDao {

	public List<InterpersonalSocial> findRoots(Integer count) {
		String jpql = "select interpersonalSocial from InterpersonalSocial interpersonalSocial where interpersonalSocial.parent is null order by interpersonalSocial.order asc";
		TypedQuery<InterpersonalSocial> query = entityManager.createQuery(jpql, InterpersonalSocial.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<InterpersonalSocial> findParents(InterpersonalSocial interpersonalSocial, Integer count) {
		if (interpersonalSocial == null || interpersonalSocial.getParent() == null) {
			return Collections.<InterpersonalSocial> emptyList();
		}
		String jpql = "select interpersonalSocial from InterpersonalSocial interpersonalSocial where interpersonalSocial.id in (:ids) order by interpersonalSocial.grade asc";
		TypedQuery<InterpersonalSocial> query = entityManager.createQuery(jpql, InterpersonalSocial.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", interpersonalSocial.getTreePaths());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<InterpersonalSocial> findChildren(InterpersonalSocial interpersonalSocial, Integer count) {
		TypedQuery<InterpersonalSocial> query;
		if (interpersonalSocial != null) {
			String jpql = "select interpersonalSocial from InterpersonalSocial interpersonalSocial where interpersonalSocial.treePath like :treePath order by interpersonalSocial.order asc";
			query = entityManager.createQuery(jpql, InterpersonalSocial.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + InterpersonalSocial.TREE_PATH_SEPARATOR + interpersonalSocial.getId() + InterpersonalSocial.TREE_PATH_SEPARATOR + "%");
		} else {
			String jpql = "select interpersonalSocial from InterpersonalSocial interpersonalSocial order by interpersonalSocial.order asc";
			query = entityManager.createQuery(jpql, InterpersonalSocial.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), interpersonalSocial);
	}

	/**
	 * 设置treePath、grade并保存
	 * 
	 * @param interpersonalSocial
	 *            文章分类
	 */
	@Override
	public void persist(InterpersonalSocial interpersonalSocial) {
		Assert.notNull(interpersonalSocial);
		setValue(interpersonalSocial);
		super.persist(interpersonalSocial);
	}

	/**
	 * 设置treePath、grade并更新
	 * 
	 * @param interpersonalSocial
	 *            文章分类
	 * @return 文章分类
	 */
	@Override
	public InterpersonalSocial merge(InterpersonalSocial interpersonalSocial) {
		Assert.notNull(interpersonalSocial);
		setValue(interpersonalSocial);
		for (InterpersonalSocial category : findChildren(interpersonalSocial, null)) {
			setValue(category);
		}
		return super.merge(interpersonalSocial);
	}

	/**
	 * 排序文章分类
	 * 
	 * @param articleCategories
	 *            文章分类
	 * @param parent
	 *            上级文章分类
	 * @return 文章分类
	 */
	private List<InterpersonalSocial> sort(List<InterpersonalSocial> articleCategories, InterpersonalSocial parent) {
		List<InterpersonalSocial> result = new ArrayList<InterpersonalSocial>();
		if (articleCategories != null) {
			for (InterpersonalSocial interpersonalSocial : articleCategories) {
				if ((interpersonalSocial.getParent() != null && interpersonalSocial.getParent().equals(parent)) || (interpersonalSocial.getParent() == null && parent == null)) {
					result.add(interpersonalSocial);
					result.addAll(sort(articleCategories, interpersonalSocial));
				}
			}
		}
		return result;
	}

	/**
	 * 设置值
	 * 
	 * @param interpersonalSocial
	 *            文章分类
	 */
	private void setValue(InterpersonalSocial interpersonalSocial) {
		if (interpersonalSocial == null) {
			return;
		}
		InterpersonalSocial parent = interpersonalSocial.getParent();
		if (parent != null) {
			interpersonalSocial.setTreePath(parent.getTreePath() + parent.getId() + InterpersonalSocial.TREE_PATH_SEPARATOR);
		} else {
			interpersonalSocial.setTreePath(InterpersonalSocial.TREE_PATH_SEPARATOR);
		}
		interpersonalSocial.setGrade(interpersonalSocial.getTreePaths().size());
	}

	public InterpersonalSocial findByStrokes(Integer strokes) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<InterpersonalSocial> criteriaQuery = criteriaBuilder.createQuery(InterpersonalSocial.class);
		Root<InterpersonalSocial> root = criteriaQuery.from(InterpersonalSocial.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (strokes != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNull(root.get("parent")));
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("strokes"), strokes));
		}
		criteriaQuery.where(restrictions);
		return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
	}

}