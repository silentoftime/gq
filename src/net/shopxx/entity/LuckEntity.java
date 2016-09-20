/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.entity;

import javax.persistence.MappedSuperclass;


/**
 * Entity - 运基类
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@MappedSuperclass
public  class LuckEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1394459537482487393L;

	public enum Level {
		/** 大吉  */
		perfect("大吉"),
		/** 吉  */
		great("吉"),
		/** 半吉  */
		good("半吉"),
		/** 凶 */
		bad("凶"),
		/** 大凶  */
		terrible("大凶");
		
		private String levelName;
		
		Level() {
			
		}
		
		Level(String levelName) {
			this.levelName = levelName;
		}
		
		public String getLevelName() {
			return this.levelName;
		}
	}
	
	
	public enum FiveElement {
		wood("木"), 
		fire("火"), 
		earth("土"),
		metal("金"), 
		water("水");
		private String elementName;
		FiveElement() {
			
		}
		FiveElement(String elementName) {
			this.elementName = elementName;
		}
		public String getElementName() {
			return this.elementName;
		}
	} 
	
	/** 吉凶 */
	public Level level;
	
	/** 备注 */
	public String remark;
	
	/** 含义  */
	public String meaning;
	
	/** 分数 */
	public Float score;
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
}
