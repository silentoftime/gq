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
 * Entity - 数理
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Entity
@Table(name = "xx_math_matical")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_math_matical_sequence")
public class MatheMatical extends LuckEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1883491873370469032L;

	
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