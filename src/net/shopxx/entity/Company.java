/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity - 公司
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Entity
@Table(name = "xx_company")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_company_sequence")
public class Company extends LuckEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4779946152360875212L;
	
	/** 笔画 */
	public Integer strokes;
	

	public Integer getStrokes() {
		return strokes;
	}

	public void setStrokes(Integer strokes) {
		this.strokes = strokes;
	}

	
//	/** 先运 */
//	public Integer firstLuck;
//	
//	/** 主运 */
//	public Integer mainLuck;
//	
//	/** 前运 */
//	public Integer frontLuck;
//	
//	/** 后运 */
//	public Integer backLuck;
//	
//	/** 灵运 */
//	public Integer spiritLuck;
	
	

}