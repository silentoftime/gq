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
 * Entity - 三才配置
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Entity
@Table(name = "xx_three_talent")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_three_talent_sequence")
public class ThreeTalent extends LuckEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3318847447601147950L;
	
	private String threeElement;
	
	private FiveElement firstElement;
	
	private FiveElement mainElement;
	
	private FiveElement frontElement;

	public FiveElement getFirstElement() {
		return firstElement;
	}

	public void setFirstElement(FiveElement firstElement) {
		this.firstElement = firstElement;
	}

	public FiveElement getMainElement() {
		return mainElement;
	}

	public void setMainElement(FiveElement mainElement) {
		this.mainElement = mainElement;
	}

	public FiveElement getFrontElement() {
		return frontElement;
	}

	public void setFrontElement(FiveElement frontElement) {
		this.frontElement = frontElement;
	}

	public String getThreeElement() {
		return threeElement;
	}

	public void setThreeElement(String threeElement) {
		this.threeElement = threeElement;
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