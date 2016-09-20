/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity - 繁体字
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Entity
@Table(name = "xx_second_href")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_second_href_sequence")
public class SecondHref extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2455026644072239193L;

	private Integer kangxiStrokes;
	
	private String href;
	
	private String unicode;
	
	private String traditional;

	public Integer getKangxiStrokes() {
		return kangxiStrokes;
	}

	public void setKangxiStrokes(Integer kangxiStrokes) {
		this.kangxiStrokes = kangxiStrokes;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getUnicode() {
		return unicode;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	public String getTraditional() {
		return traditional;
	}

	public void setTraditional(String traditional) {
		this.traditional = traditional;
	}

	@Override
	public String toString() {
		return "SecondHref [kangxiStrokes=" + kangxiStrokes + ", href=" + href
				+ ", unicode=" + unicode + ", traditional=" + traditional + "]";
	}
	
	

}