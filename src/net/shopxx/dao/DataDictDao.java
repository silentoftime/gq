/*
 * Copyright 2005-2014 nbcyl.net. All rights reserved.
 * Support: http://www.nbcyl.net
 * License: http://www.nbcyl.net/license
 */
package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.DataDict;

/**
 * Dao - 数据字典
 * 
 * @author  刘赛
 * @version 2014-10-21 新建</BR>
 */
public interface DataDictDao extends BaseDao<DataDict, Long> {

	/**
	 * 查找数据字典列表
	 * 
	 * @param dataDict
	 *            数据字典
	 * @param count
	 *            数量
	 * @return 数据字典
	 */
	List<DataDict> findChildren(DataDict dataDict, Integer count);

	/**
	 * 判断字典编号是否存在
	 * 
	 * @param sn
	 *            字典编号(忽略大小写)
	 * @return 字典编号是否存在
	 */
	boolean snExists(String currentCode);
}
