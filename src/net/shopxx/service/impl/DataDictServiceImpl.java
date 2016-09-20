/*
 * Copyright 2005-2014 nbcyl.net. All rights reserved.
 * Support: http://www.nbcyl.net
 * License: http://www.nbcyl.net/license
 */
package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.shopxx.dao.DataDictDao;
import net.shopxx.entity.DataDict;
import net.shopxx.service.DataDictService;

/**
 * Service - 数据字典
 * 
 * @author  刘赛
 * @version 2014-10-21 新建</BR>
 */
@Service("dataDictServiceImpl")
public class DataDictServiceImpl extends BaseServiceImpl<DataDict, Long> implements DataDictService {

	@Resource(name = "dataDictDaoImpl")
	private DataDictDao dataDictDao;
	
	@Resource(name = "dataDictDaoImpl")
	public void setBaseDao(DataDictDao dataDictDao) {
		super.setBaseDao(dataDictDao);
	}

	@Transactional(readOnly = true)
	public List<DataDict> findDataDictTree() {
		return dataDictDao.findChildren(null, null);
	}
	
	@Transactional(readOnly = true)
	public List<DataDict> findChildren(DataDict dataDict) {
		return dataDictDao.findChildren(dataDict, null);
	}
	
	@Override
	@Transactional
	public void save(DataDict dataDict) {
		super.save(dataDict);
	}
	
	@Override
	@Transactional
	public DataDict update(DataDict dataDict, String... ignoreProperties) {
		return super.update(dataDict, ignoreProperties);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		super.delete(id);
	}

	@Transactional(readOnly = true)
	public boolean snUnique(String previousCode, String currentCode) {
		if (StringUtils.equalsIgnoreCase(previousCode, currentCode)) {
			return true;
		} else {
			if (dataDictDao.snExists(currentCode)) {
				return false;
			} else {
				return true;
			}
		}
	}
}
