/*
 * Copyright 2005-2014 nbcyl.net. All rights reserved.
 * Support: http://www.nbcyl.net
 * License: http://www.nbcyl.net/license
 */
package net.shopxx.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import net.shopxx.dao.DataDictDao;
import net.shopxx.entity.DataDict;
import net.shopxx.entity.ProductCategory;

/**
 * Dao - 数据字典
 * 
 * @author  刘赛
 * @version 2014-10-21 新建</BR>
 */
@Repository("dataDictDaoImpl")
public class DataDictDaoImpl extends BaseDaoImpl<DataDict, Long> implements DataDictDao {

	public List<DataDict> findChildren(DataDict dataDict, Integer count) {
		TypedQuery<DataDict> query;
		if(dataDict!=null){
			String jpql = "select dataDict from DataDict dataDict where dataDict.treePath like :treePath order by dataDict.order asc";
			query = entityManager.createQuery(jpql, DataDict.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + DataDict.TREE_PATH_SEPARATOR + dataDict.getId() + ProductCategory.TREE_PATH_SEPARATOR + "%");
		}else{
			String jpql = "select dataDict from DataDict dataDict order by dataDict.order asc";
			query = entityManager.createQuery(jpql, DataDict.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), dataDict);
	}

	/**
	 * 设置treePath、grade并保存
	 * 
	 * @param productCategory
	 *            数据字典
	 */
	@Override
	public void persist(DataDict dataDict) {
		Assert.notNull(dataDict);
		setValue(dataDict);
		super.persist(dataDict);
	}
	
	/**
	 * 设置treePath、grade并更新
	 * 
	 * @param productCategory
	 *            数据字典
	 * @return 数据字典
	 */
	@Override
	public DataDict merge(DataDict dataDict) {
		Assert.notNull(dataDict);
		setValue(dataDict);
		for (DataDict dict : findChildren(dataDict, null)) {
			setValue(dict);
		}
		return super.merge(dataDict);
	}
	
	/**
	 * 清除数据字典属性并删除
	 * 
	 * @param productCategory
	 *            商品分类
	 */
	@Override
	public void remove(DataDict dataDict) {
		if (dataDict != null) {
			super.remove(dataDict);
		}
	}
	
	/**
	 * 设置值
	 * 
	 * @param productCategory
	 *            数据字典
	 */
	private void setValue(DataDict dataDict) {
		if (dataDict == null) {
			return;
		}
		DataDict parent = dataDict.getParent();
		if (parent != null) {
			dataDict.setTreePath(parent.getTreePath() + parent.getId() + ProductCategory.TREE_PATH_SEPARATOR);
		} else {
			dataDict.setTreePath(ProductCategory.TREE_PATH_SEPARATOR);
		}
		dataDict.setGrade(dataDict.getTreePaths().size());
	}
	
	/**
	 * 排序数据字典
	 * 
	 * @param productCategories
	 *            数据字典
	 * @param parent
	 *            上级数据字典
	 * @return 数据字典
	 */
	private List<DataDict> sort(List<DataDict> dataDicts, DataDict parent) {
		List<DataDict> result = new ArrayList<DataDict>();
		if (dataDicts != null) {
			for (DataDict dataDict : dataDicts) {
				if ((dataDict.getParent() != null && dataDict.getParent().equals(parent)) || (dataDict.getParent() == null && parent == null)) {
					result.add(dataDict);
					result.addAll(sort(dataDicts, dataDict));
				}
			}
		}
		return result;
	}

	public boolean snExists(String currentCode) {
		if (currentCode == null) {
			return false;
		}
		String jpql = "select count(*) from DataDict dataDict where lower(dataDict.code) = lower(:currentCode)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("currentCode", currentCode).getSingleResult();
		return count > 0;
	}
}
