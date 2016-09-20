/*
 * Copyright 2005-2014 nbcyl.net. All rights reserved.
 * Support: http://www.nbcyl.net
 * License: http://www.nbcyl.net/license
 */
package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.DataDict;

/**
 * Service - 数据字典
 * 
 * @author 	刘赛
 * @version 2014-10-21 新建</BR>
 */
public interface DataDictService extends BaseService<DataDict, Long> {

	/**
	 * 查找数据字典列表
	 * 
	 * @return 数据字典列表
	 */
	List<DataDict> findDataDictTree();
	
	/**
	 * 查找下级数据字典
	 * 
	 * @param productCategory
	 *            商品分类
	 * @return 下级商品分类
	 */
	List<DataDict> findChildren(DataDict dataDict);
	
	/**
	 * 判断字典编号是否唯一
	 * 
	 * @param previousSn
	 *            修改前字典编号(忽略大小写)
	 * @param currentSn
	 *            当前字典编号(忽略大小写)
	 * @return 字典编号是否唯一
	 */
	boolean snUnique(String previousCode, String currentCode);

}
