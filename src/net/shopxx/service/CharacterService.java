/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service;

import net.shopxx.entity.Character;

/**
 * Service - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface CharacterService extends BaseService<Character, Long> {

	/**
	 * 
	 * @param mainLuckStrokes
	 * @return
	 */
	Character findByStrokes(Integer mainLuckStrokes);


}