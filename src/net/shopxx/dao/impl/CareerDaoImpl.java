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

import net.shopxx.dao.CareerDao;
import net.shopxx.entity.Career;
import net.shopxx.entity.LuckEntity.FiveElement;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Dao - 文章分类
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("careerDaoImpl")
public class CareerDaoImpl extends BaseDaoImpl<Career, Long> implements CareerDao {

	public List<Career> findRoots(Integer count) {
		String jpql = "select career from Career career where career.parent is null order by career.order asc";
		TypedQuery<Career> query = entityManager.createQuery(jpql, Career.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<Career> findParents(Career career, Integer count) {
		if (career == null || career.getParent() == null) {
			return Collections.<Career> emptyList();
		}
		String jpql = "select career from Career career where career.id in (:ids) order by career.grade asc";
		TypedQuery<Career> query = entityManager.createQuery(jpql, Career.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", career.getTreePaths());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<Career> findChildren(Career career, Integer count) {
		TypedQuery<Career> query;
		if (career != null) {
			String jpql = "select career from Career career where career.treePath like :treePath order by career.order asc";
			query = entityManager.createQuery(jpql, Career.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + Career.TREE_PATH_SEPARATOR + career.getId() + Career.TREE_PATH_SEPARATOR + "%");
		} else {
			String jpql = "select career from Career career order by career.order asc";
			query = entityManager.createQuery(jpql, Career.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), career);
	}

	/**
	 * 设置treePath、grade并保存
	 * 
	 * @param career
	 *            文章分类
	 */
	@Override
	public void persist(Career career) {
		Assert.notNull(career);
		setValue(career);
		super.persist(career);
	}

	/**
	 * 设置treePath、grade并更新
	 * 
	 * @param career
	 *            文章分类
	 * @return 文章分类
	 */
	@Override
	public Career merge(Career career) {
		Assert.notNull(career);
		setValue(career);
		for (Career category : findChildren(career, null)) {
			setValue(category);
		}
		return super.merge(career);
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
	private List<Career> sort(List<Career> articleCategories, Career parent) {
		List<Career> result = new ArrayList<Career>();
		if (articleCategories != null) {
			for (Career career : articleCategories) {
				if ((career.getParent() != null && career.getParent().equals(parent)) || (career.getParent() == null && parent == null)) {
					result.add(career);
					result.addAll(sort(articleCategories, career));
				}
			}
		}
		return result;
	}

	/**
	 * 设置值
	 * 
	 * @param career
	 *            文章分类
	 */
	private void setValue(Career career) {
		if (career == null) {
			return;
		}
		Career parent = career.getParent();
		if (parent != null) {
			career.setTreePath(parent.getTreePath() + parent.getId() + Career.TREE_PATH_SEPARATOR);
		} else {
			career.setTreePath(Career.TREE_PATH_SEPARATOR);
		}
		career.setGrade(career.getTreePaths().size());
	}

	public Career findByElement(FiveElement fiveElement) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Career> criteriaQuery = criteriaBuilder.createQuery(Career.class);
		Root<Career> root = criteriaQuery.from(Career.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (fiveElement != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNull(root.get("parent")));
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("element"), fiveElement));
		}
		criteriaQuery.where(restrictions);
		return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
	}

}