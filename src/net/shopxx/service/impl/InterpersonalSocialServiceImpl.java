/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.InterpersonalSocialDao;
import net.shopxx.entity.InterpersonalSocial;
import net.shopxx.service.InterpersonalSocialService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 文章分类
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("interpersonalSocialServiceImpl")
public class InterpersonalSocialServiceImpl extends BaseServiceImpl<InterpersonalSocial, Long> implements InterpersonalSocialService {

	@Resource(name = "interpersonalSocialDaoImpl")
	private InterpersonalSocialDao interpersonalSocialDao;

	@Resource(name = "interpersonalSocialDaoImpl")
	public void setBaseDao(InterpersonalSocialDao interpersonalSocialDao) {
		super.setBaseDao(interpersonalSocialDao);
	}

	@Transactional(readOnly = true)
	public List<InterpersonalSocial> findRoots() {
		return interpersonalSocialDao.findRoots(null);
	}

	@Transactional(readOnly = true)
	public List<InterpersonalSocial> findRoots(Integer count) {
		return interpersonalSocialDao.findRoots(count);
	}

	@Transactional(readOnly = true)
	public List<InterpersonalSocial> findRoots(Integer count, String cacheRegion) {
		return interpersonalSocialDao.findRoots(count);
	}

	@Transactional(readOnly = true)
	public List<InterpersonalSocial> findParents(InterpersonalSocial interpersonalSocial) {
		return interpersonalSocialDao.findParents(interpersonalSocial, null);
	}

	@Transactional(readOnly = true)
	public List<InterpersonalSocial> findParents(InterpersonalSocial interpersonalSocial, Integer count) {
		return interpersonalSocialDao.findParents(interpersonalSocial, count);
	}

	@Transactional(readOnly = true)
	public List<InterpersonalSocial> findParents(InterpersonalSocial interpersonalSocial, Integer count, String cacheRegion) {
		return interpersonalSocialDao.findParents(interpersonalSocial, count);
	}

	@Transactional(readOnly = true)
	public List<InterpersonalSocial> findTree() {
		return interpersonalSocialDao.findChildren(null, null);
	}

	@Transactional(readOnly = true)
	public List<InterpersonalSocial> findChildren(InterpersonalSocial interpersonalSocial) {
		return interpersonalSocialDao.findChildren(interpersonalSocial, null);
	}

	@Transactional(readOnly = true)
	public List<InterpersonalSocial> findChildren(InterpersonalSocial interpersonalSocial, Integer count) {
		return interpersonalSocialDao.findChildren(interpersonalSocial, count);
	}

	@Transactional(readOnly = true)
	public List<InterpersonalSocial> findChildren(InterpersonalSocial interpersonalSocial, Integer count, String cacheRegion) {
		return interpersonalSocialDao.findChildren(interpersonalSocial, count);
	}

	public InterpersonalSocial findByStrokes(Integer strokes) {
		return interpersonalSocialDao.findByStrokes(strokes);
	}


}