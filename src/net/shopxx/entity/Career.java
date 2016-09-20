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
 * Entity - 事业
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Entity
@Table(name = "xx_career")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_career_sequence")
public class Career extends LuckOrderEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1314370194780021968L;

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	/** 访问路径前缀 */
	private static final String PATH_PREFIX = "/article/list";

	/** 访问路径后缀 */
	private static final String PATH_SUFFIX = ".jhtml";
	
	public enum Type {
		success("成功运"), 
		basic("基础运");
		private String typeName;
		Type() {
			
		}
		Type(String typeName) {
			this.typeName = typeName;
		}
		public String getTypeName() {
			return this.typeName;
		}
	} 
	
	private Type type;
	
	private FiveElement element;

	/** 树路径 */
	private String treePath;

	/** 层级 */
	private Integer grade;

	/** 上级分类 */
	private Career parent;

	/** 下级分类 */
	private Set<Career> children = new HashSet<Career>();

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
	public Career getParent() {
		return parent;
	}

	public void setParent(Career parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("order asc")
	public Set<Career> getChildren() {
		return children;
	}

	public void setChildren(Set<Career> children) {
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