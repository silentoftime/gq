/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.dao.MatheMaticalDao;
import net.shopxx.entity.MatheMatical;
import net.shopxx.service.MatheMaticalService;

import org.springframework.stereotype.Service;

/**
 * Service - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("matheMaticalServiceImpl")
public class MatheMaticalServiceImpl extends BaseServiceImpl<MatheMatical, Long> implements MatheMaticalService {

	@Resource(name = "matheMaticalDaoImpl")
	private MatheMaticalDao matheMaticalDao;

	@Resource(name = "matheMaticalDaoImpl")
	public void setBaseDao(MatheMaticalDao matheMaticalDao) {
		super.setBaseDao(matheMaticalDao);
	}

	public MatheMatical findByStrokes(Integer firstLuckStrokes) {
		return matheMaticalDao.findByStrokes(firstLuckStrokes);
	}


}