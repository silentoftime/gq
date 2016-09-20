/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.InterpersonalSocial;

/**
 * Dao - 文章分类
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface InterpersonalSocialDao extends BaseDao<InterpersonalSocial, Long> {

	/**
	 * 查找顶级文章分类
	 * 
	 * @param count
	 *            数量
	 * @return 顶级文章分类
	 */
	List<InterpersonalSocial> findRoots(Integer count);

	/**
	 * 查找上级文章分类
	 * 
	 * @param interpersonalSocial
	 *            文章分类
	 * @param count
	 *            数量
	 * @return 上级文章分类
	 */
	List<InterpersonalSocial> findParents(InterpersonalSocial interpersonalSocial, Integer count);

	/**
	 * 查找下级文章分类
	 * 
	 * @param interpersonalSocial
	 *            文章分类
	 * @param count
	 *            数量
	 * @return 下级文章分类
	 */
	List<InterpersonalSocial> findChildren(InterpersonalSocial interpersonalSocial, Integer count);

	/**
	 * 
	 * @param strokes
	 * @return
	 */
	InterpersonalSocial findByStrokes(Integer strokes);

}