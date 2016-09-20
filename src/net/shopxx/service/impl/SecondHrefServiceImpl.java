/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.SecondHrefDao;
import net.shopxx.entity.SecondHref;
import net.shopxx.service.SecondHrefService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("secondHrefServiceImpl")
public class SecondHrefServiceImpl extends BaseServiceImpl<SecondHref, Long> implements SecondHrefService {

	@Resource(name = "secondHrefDaoImpl")
	private SecondHrefDao secondHrefDao;

	@Resource(name = "secondHrefDaoImpl")
	public void setBaseDao(SecondHrefDao secondHrefDao) {
		super.setBaseDao(secondHrefDao);
	}

	@Transactional
	public void save(List<SecondHref> secondHrefs) {
		for (SecondHref secondHref : secondHrefs) {
			this.save(secondHref);
		}
	}

	public List<SecondHref> findLeft() {
		return secondHrefDao.findLeft();
	}



}