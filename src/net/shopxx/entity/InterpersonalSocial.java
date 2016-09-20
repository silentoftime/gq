/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.shopxx.entity.LuckEntity.FiveElement;

import org.apache.commons.lang.StringUtils;

/**
 * Entity - 人际关系和社交能力
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Entity
@Table(name = "xx_interpersonal_social")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_interpersonal_social_sequence")
public class InterpersonalSocial extends LuckOrderEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3983048974690814643L;

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	/** 访问路径前缀 */
	private static final String PATH_PREFIX = "/article/list";

	/** 访问路径后缀 */
	private static final String PATH_SUFFIX = ".jhtml";
	
	private FiveElement element;

	/** 笔画 */
	public Integer strokes;

	/** 树路径 */
	private String treePath;

	/** 层级 */
	private Integer grade;

	/** 上级分类 */
	private InterpersonalSocial parent;

	/** 下级分类 */
	private Set<InterpersonalSocial> children = new HashSet<InterpersonalSocial>();

	public Integer getStrokes() {
		return strokes;
	}

	public void setStrokes(Integer strokes) {
		this.strokes = strokes;
	}
	
	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public InterpersonalSocial getParent() {
		return parent;
	}

	public void setParent(InterpersonalSocial parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("order asc")
	public Set<InterpersonalSocial> getChildren() {
		return children;
	}

	public void setChildren(Set<InterpersonalSocial> children) {
		this.children = children;
	}

	public FiveElement getElement() {
		return element;
	}

	public void setElement(FiveElement element) {
		this.element = element;
	}
	
	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Transient
	public List<Long> getTreePaths() {
		List<Long> treePaths = new ArrayList<Long>();
		String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		if (ids != null) {
			for (String id : ids) {
				treePaths.add(Long.valueOf(id));
			}
		}
		return treePaths;
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