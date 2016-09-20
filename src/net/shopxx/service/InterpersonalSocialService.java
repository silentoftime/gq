/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.InterpersonalSocial;

/**
 * Service - 文章分类
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface InterpersonalSocialService extends BaseService<InterpersonalSocial, Long> {

	/**
	 * 查找顶级文章分类
	 * 
	 * @return 顶级文章分类
	 */
	List<InterpersonalSocial> findRoots();

	/**
	 * 查找顶级文章分类
	 * 
	 * @param count
	 *            数量
	 * @return 顶级文章分类
	 */
	List<InterpersonalSocial> findRoots(Integer count);

	/**
	 * 查找顶级文章分类(缓存)
	 * 
	 * @param count
	 *            数量
	 * @param cacheRegion
	 *            缓存区域
	 * @return 顶级文章分类(缓存)
	 */
	List<InterpersonalSocial> findRoots(Integer count, String cacheRegion);

	/**
	 * 查找上级文章分类
	 * 
	 * @param interpersonalSocial
	 *            文章分类
	 * @return 上级文章分类
	 */
	List<InterpersonalSocial> findParents(InterpersonalSocial interpersonalSocial);

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
	 * 查找上级文章分类(缓存)
	 * 
	 * @param interpersonalSocial
	 *            文章分类
	 * @param count
	 *            数量
	 * @param cacheRegion
	 *            缓存区域
	 * @return 上级文章分类(缓存)
	 */
	List<InterpersonalSocial> findParents(InterpersonalSocial interpersonalSocial, Integer count, String cacheRegion);

	/**
	 * 查找文章分类树
	 * 
	 * @return 文章分类树
	 */
	List<InterpersonalSocial> findTree();

	/**
	 * 查找下级文章分类
	 * 
	 * @param interpersonalSocial
	 *            文章分类
	 * @return 下级文章分类
	 */
	List<InterpersonalSocial> findChildren(InterpersonalSocial interpersonalSocial);

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
	 * 查找下级文章分类(缓存)
	 * 
	 * @param interpersonalSocial
	 *            文章分类
	 * @param count
	 *            数量
	 * @param cacheRegion
	 *            缓存区域
	 * @return 下级文章分类(缓存)
	 */
	List<InterpersonalSocial> findChildren(InterpersonalSocial interpersonalSocial, Integer count, String cacheRegion);

	/**
	 * 
	 * @param strokes
	 * @return
	 */
	InterpersonalSocial findByStrokes(Integer strokes);

}