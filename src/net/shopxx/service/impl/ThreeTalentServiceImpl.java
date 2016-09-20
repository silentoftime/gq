/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.dao.ThreeTalentDao;
import net.shopxx.entity.ThreeTalent;
import net.shopxx.service.ThreeTalentService;

import org.springframework.stereotype.Service;

/**
 * Service - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("threeTalentServiceImpl")
public class ThreeTalentServiceImpl extends BaseServiceImpl<ThreeTalent, Long> implements ThreeTalentService {

	@Resource(name = "threeTalentDaoImpl")
	private ThreeTalentDao threeTalentDao;

	@Resource(name = "threeTalentDaoImpl")
	public void setBaseDao(ThreeTalentDao threeTalentDao) {
		super.setBaseDao(threeTalentDao);
	}

	public ThreeTalent findByThreeElement(String threeElement) {
		return threeTalentDao.findByThreeElement(threeElement);
	}


}