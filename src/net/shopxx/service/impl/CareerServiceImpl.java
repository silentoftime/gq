/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.CareerDao;
import net.shopxx.entity.Career;
import net.shopxx.entity.LuckEntity.FiveElement;
import net.shopxx.service.CareerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 文章分类
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("careerServiceImpl")
public class CareerServiceImpl extends BaseServiceImpl<Career, Long> implements CareerService {

	@Resource(name = "careerDaoImpl")
	private CareerDao careerDao;

	@Resource(name = "careerDaoImpl")
	public void setBaseDao(CareerDao careerDao) {
		super.setBaseDao(careerDao);
	}

	@Transactional(readOnly = true)
	public List<Career> findRoots() {
		return careerDao.findRoots(null);
	}

	@Transactional(readOnly = true)
	public List<Career> findRoots(Integer count) {
		return careerDao.findRoots(count);
	}

	@Transactional(readOnly = true)
	public List<Career> findRoots(Integer count, String cacheRegion) {
		return careerDao.findRoots(count);
	}

	@Transactional(readOnly = true)
	public List<Career> findParents(Career career) {
		return careerDao.findParents(career, null);
	}

	@Transactional(readOnly = true)
	public List<Career> findParents(Career career, Integer count) {
		return careerDao.findParents(career, count);
	}

	@Transactional(readOnly = true)
	public List<Career> findParents(Career career, Integer count, String cacheRegion) {
		return careerDao.findParents(career, count);
	}

	@Transactional(readOnly = true)
	public List<Career> findTree() {
		return careerDao.findChildren(null, null);
	}

	@Transactional(readOnly = true)
	public List<Career> findChildren(Career career) {
		return careerDao.findChildren(career, null);
	}

	@Transactional(readOnly = true)
	public List<Career> findChildren(Career career, Integer count) {
		return careerDao.findChildren(career, count);
	}

	@Transactional(readOnly = true)
	public List<Career> findChildren(Career career, Integer count, String cacheRegion) {
		return careerDao.findChildren(career, count);
	}

	public Career findByElement(FiveElement fiveElement) {
		return careerDao.findByElement(fiveElement);
	}


}