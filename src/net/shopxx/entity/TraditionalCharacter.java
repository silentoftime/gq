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
@Table(name = "xx_traditional_character")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_traditional_character_sequence")
public class TraditionalCharacter extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8494527153035280665L;
	
	
	private Integer kangxiStrokes;
	
	/** 简体 */
	private String simplified;
	
	private String traditional;
	
	private String pinYin;
	
	private String zhuYin;
	
	private String buShou;
	
	private String buWai;
	
	
	private Integer totalStrokes;
	
	
	private String fontAnalyse;
	
	private String variantCharacter;
	
	private String unicode;
	
	private String wuBi;
	
	private String cangJie;//仓颉 传说中创造汉字的人
	
	private String zhengCode;
	
	private String fourConer;
	
	
	private String strokeOrders;
	
	private String basicMeaning;
	
	private String kangXiDictionary;
	
	private String explainCharacter;
	
	private String dialects;
	
	private String source;
	
	private String discuss;

	public Integer getKangxiStrokes() {
		return kangxiStrokes;
	}

	public void setKangxiStrokes(Integer kangxiStrokes) {
		this.kangxiStrokes = kangxiStrokes;
	}

	public String getSimplified() {
		return simplified;
	}

	public void setSimplified(String simplified) {
		this.simplified = simplified;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String getZhuYin() {
		return zhuYin;
	}

	public void setZhuYin(String zhuYin) {
		this.zhuYin = zhuYin;
	}

	public String getBuShou() {
		return buShou;
	}

	public void setBuShou(String buShou) {
		this.buShou = buShou;
	}

	public String getBuWai() {
		return buWai;
	}

	public void setBuWai(String buWai) {
		this.buWai = buWai;
	}

	public Integer getTotalStrokes() {
		return totalStrokes;
	}

	public void setTotalStrokes(Integer totalStrokes) {
		this.totalStrokes = totalStrokes;
	}

	public String getFontAnalyse() {
		return fontAnalyse;
	}

	public void setFontAnalyse(String fontAnalyse) {
		this.fontAnalyse = fontAnalyse;
	}

	public String getVariantCharacter() {
		return variantCharacter;
	}

	public void setVariantCharacter(String variantCharacter) {
		this.variantCharacter = variantCharacter;
	}

	public String getUnicode() {
		return unicode;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	public String getWuBi() {
		return wuBi;
	}

	public void setWuBi(String wuBi) {
		this.wuBi = wuBi;
	}

	public String getCangJie() {
		return cangJie;
	}

	public void setCangJie(String cangJie) {
		this.cangJie = cangJie;
	}

	public String getZhengCode() {
		return zhengCode;
	}

	public void setZhengCode(String zhengCode) {
		this.zhengCode = zhengCode;
	}

	public String getFourConer() {
		return fourConer;
	}

	public void setFourConer(String fourConer) {
		this.fourConer = fourConer;
	}

	public String getStrokeOrders() {
		return strokeOrders;
	}

	public void setStrokeOrders(String strokeOrders) {
		this.strokeOrders = strokeOrders;
	}

	@Lob
	public String getBasicMeaning() {
		return basicMeaning;
	}

	public void setBasicMeaning(String basicMeaning) {
		this.basicMeaning = basicMeaning;
	}
	
	@Lob
	public String getKangXiDictionary() {
		return kangXiDictionary;
	}

	public void setKangXiDictionary(String kangXiDictionary) {
		this.kangXiDictionary = kangXiDictionary;
	}

	@Lob
	public String getExplainCharacter() {
		return explainCharacter;
	}

	public void setExplainCharacter(String explainCharacter) {
		this.explainCharacter = explainCharacter;
	}

	@Lob
	public String getDialects() {
		return dialects;
	}

	public void setDialects(String dialects) {
		this.dialects = dialects;
	}

	@Lob
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDiscuss() {
		return discuss;
	}

	public void setDiscuss(String discuss) {
		this.discuss = discuss;
	}

	public String getTraditional() {
		return traditional;
	}

	public void setTraditional(String traditional) {
		this.traditional = traditional;
	}

	@Override
	public String toString() {
		return "TraditionalCharacter [kangxiStrokes=" + kangxiStrokes
				+ ", simplified=" + simplified + ", traditional=" + traditional
				+ ", pinYin=" + pinYin + ", zhuYin=" + zhuYin + ", buShou="
				+ buShou + ", buWai=" + buWai + ", totalStrokes="
				+ totalStrokes + ", fontAnalyse=" + fontAnalyse
				+ ", variantCharacter=" + variantCharacter + ", unicode="
				+ unicode + ", wuBi=" + wuBi + ", cangJie=" + cangJie
				+ ", zhengCode=" + zhengCode + ", fourConer=" + fourConer
				+ ", strokeOrders=" + strokeOrders + ", basicMeaning="
				+ basicMeaning + ", kangXiDictionary=" + kangXiDictionary
				+ ", explainCharacter=" + explainCharacter + ", dialects="
				+ dialects + ", source=" + source + ", discuss=" + discuss
				+ "]";
	}
	
	

}