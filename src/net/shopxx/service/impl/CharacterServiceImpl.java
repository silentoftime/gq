/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.dao.CharacterDao;
import net.shopxx.entity.Character;
import net.shopxx.service.CharacterService;

import org.springframework.stereotype.Service;

/**
 * Service - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("characterServiceImpl")
public class CharacterServiceImpl extends BaseServiceImpl<Character, Long> implements CharacterService {

	@Resource(name = "characterDaoImpl")
	private CharacterDao characterDao;

	@Resource(name = "characterDaoImpl")
	public void setBaseDao(CharacterDao characterDao) {
		super.setBaseDao(characterDao);
	}

	public Character findByStrokes(Integer mainLuckStrokes) {
		return characterDao.findByStrokes(mainLuckStrokes);
	}


}