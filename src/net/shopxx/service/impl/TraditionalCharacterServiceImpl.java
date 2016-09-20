/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.TraditionalCharacterDao;
import net.shopxx.entity.TraditionalCharacter;
import net.shopxx.service.TraditionalCharacterService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("traditionalCharacterServiceImpl")
public class TraditionalCharacterServiceImpl extends BaseServiceImpl<TraditionalCharacter, Long> implements TraditionalCharacterService {

	@Resource(name = "traditionalCharacterDaoImpl")
	private TraditionalCharacterDao traditionalCharacterDao;

	@Resource(name = "traditionalCharacterDaoImpl")
	public void setBaseDao(TraditionalCharacterDao traditionalCharacterDao) {
		super.setBaseDao(traditionalCharacterDao);
	}

	@Transactional
	public void save(List<TraditionalCharacter> traditionalCharacters) {
		for (TraditionalCharacter traditionalCharacter : traditionalCharacters) {
			this.save(traditionalCharacter);
		}
	}



}