/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.TraditionalCharacter;

/**
 * Service - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface TraditionalCharacterService extends BaseService<TraditionalCharacter, Long> {

	void save(List<TraditionalCharacter> traditionalCharacters);


}