/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.SecondHref;

/**
 * Dao - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface SecondHrefDao extends BaseDao<SecondHref, Long> {

	List<SecondHref> findLeft();


}