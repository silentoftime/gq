/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao;

import net.shopxx.entity.MatheMatical;

/**
 * Dao - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface MatheMaticalDao extends BaseDao<MatheMatical, Long> {

	/**
	 * 
	 * @param firstLuckStrokes
	 * @return
	 */
	MatheMatical findByStrokes(Integer firstLuckStrokes);


}