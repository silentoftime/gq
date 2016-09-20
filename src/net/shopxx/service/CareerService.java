/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.Career;
import net.shopxx.entity.LuckEntity.FiveElement;

/**
 * Service - 文章分类
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface CareerService extends BaseService<Career, Long> {

	/**
	 * 查找顶级文章分类
	 * 
	 * @return 顶级文章分类
	 */
	List<Career> findRoots();

	/**
	 * 查找顶级文章分类
	 * 
	 * @param count
	 *            数量
	 * @return 顶级文章分类
	 */
	List<Career> findRoots(Integer count);

	/**
	 * 查找顶级文章分类(缓存)
	 * 
	 * @param count
	 *            数量
	 * @param cacheRegion
	 *            缓存区域
	 * @return 顶级文章分类(缓存)
	 */
	List<Career> findRoots(Integer count, String cacheRegion);

	/**
	 * 查找上级文章分类
	 * 
	 * @param career
	 *            文章分类
	 * @return 上级文章分类
	 */
	List<Career> findParents(Career career);

	/**
	 * 查找上级文章分类
	 * 
	 * @param career
	 *            文章分类
	 * @param count
	 *            数量
	 * @return 上级文章分类
	 */
	List<Career> findParents(Career career, Integer count);

	/**
	 * 查找上级文章分类(缓存)
	 * 
	 * @param career
	 *            文章分类
	 * @param count
	 *            数量
	 * @param cacheRegion
	 *            缓存区域
	 * @return 上级文章分类(缓存)
	 */
	List<Career> findParents(Career career, Integer count, String cacheRegion);

	/**
	 * 查找文章分类树
	 * 
	 * @return 文章分类树
	 */
	List<Career> findTree();

	/**
	 * 查找下级文章分类
	 * 
	 * @param career
	 *            文章分类
	 * @return 下级文章分类
	 */
	List<Career> findChildren(Career career);

	/**
	 * 查找下级文章分类
	 * 
	 * @param career
	 *            文章分类
	 * @param count
	 *            数量
	 * @return 下级文章分类
	 */
	List<Career> findChildren(Career career, Integer count);

	/**
	 * 查找下级文章分类(缓存)
	 * 
	 * @param career
	 *            文章分类
	 * @param count
	 *            数量
	 * @param cacheRegion
	 *            缓存区域
	 * @return 下级文章分类(缓存)
	 */
	List<Career> findChildren(Career career, Integer count, String cacheRegion);

	/**
	 * 
	 * @param fiveElement
	 * @return
	 */
	Career findByElement(FiveElement fiveElement);

}