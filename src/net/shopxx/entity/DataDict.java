/*
 * Copyright 2005-2014 www.nbcyl.com. All rights reserved.
 * Support: http://www.nbcyl.com
 * License: http://www.nbcyl.com/license
 */
package net.shopxx.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * Entity - 数据字典
 * 
 * @author   刘赛
 * @version  2014-10-21 新建</BR>
 */
@Entity
@Table(name = "xx_datadict")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_datadict_sequence")
public class DataDict  extends OrderEntity {
    	
	private static final long serialVersionUID = -2362600855390889915L;
	
	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	/** 访问路径前缀 */
	private static final String PATH_PREFIX = "/datadict/list";

	/** 访问路径后缀 */
	private static final String PATH_SUFFIX = ".jhtml";
	
	/** 星级 */
	public static final String STARLEVEL="STARLEVEL";
	
	/** 会员级别 */
	public static final String RANK="RANK";
	
	/** 会员大类 */
	public static final String BIGTYPE="BIGTYPE";
	
	/** 会员小类 */
	public static final String SMALLTYPE="SMALLTYPE";
	
	/** 名称 */
	private String name;
	
	/** 编码 */
	private String code;
	
	/** 类型 */
	private String type;
	
	/** 描述 */
	private String remark;
	
	/** 父ID */
	private Long pid;
	
	/** 树路径 */
	private String treePath;

	/** 层级 */
	private Integer grade;
	
	/** 父列表 */
	private DataDict parent;
	
	/** 子列表 */
	private Set<DataDict> child = new HashSet<DataDict>();
		
	/** 会员级别 */
//	private Set<Seller> rankSeller = new HashSet<Seller>();
//	
//	/** 会员星级 */
//	private Set<Seller> starLevelSeller = new HashSet<Seller>();
//	
//	/** 会员大类 */
//	private Set<Seller> bigTypeSeller = new HashSet<Seller>();
//	
//	/** 会员小类 */
//	private Set<Seller> smallTypeSeller = new HashSet<Seller>();
		
	
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false,length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Length(max = 30)	
	@Column(unique=true, length = 30)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NotEmpty
	@Length(max = 30)
	@Column(nullable = false,length = 30)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(max = 200)	
	@Column(length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(nullable = false)
	public Long getPid() {
		return pid;
	}
	
	public void setPid(Long pid) {
		this.pid = pid;
	}

//	@OneToMany(mappedBy = "rank", fetch = FetchType.LAZY)
//	public Set<Seller> getRankSeller() {
//		return rankSeller;
//	}
//
//	public void setRankSeller(Set<Seller> rankSeller) {
//		this.rankSeller = rankSeller;
//	}
//
//	@OneToMany(mappedBy = "starLevel", fetch = FetchType.LAZY)
//	public Set<Seller> getStarLevelSeller() {
//		return starLevelSeller;
//	}
//
//	public void setStarLevelSeller(Set<Seller> starLevelSeller) {
//		this.starLevelSeller = starLevelSeller;
//	}
//
//	@OneToMany(mappedBy = "bigType", fetch = FetchType.LAZY)
//	public Set<Seller> getBigTypeSeller() {
//		return bigTypeSeller;
//	}
//
//	
//	public void setBigTypeSeller(Set<Seller> bigTypeSeller) {
//		this.bigTypeSeller = bigTypeSeller;
//	}
//
//	@OneToMany(mappedBy = "smallType", fetch = FetchType.LAZY)
//	public Set<Seller> getSmallTypeSeller() {
//		return smallTypeSeller;
//	}
//
//	
//	public void setSmallTypeSeller(Set<Seller> smallTypeSeller) {
//		this.smallTypeSeller = smallTypeSeller;
//	}

	@ManyToOne(fetch = FetchType.LAZY)
	public DataDict getParent() {
		return parent;
	}

	public void setParent(DataDict parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("order asc")
	public Set<DataDict> getChild() {
		return child;
	}

	public void setChild(Set<DataDict> child) {
		this.child = child;
	}
	
	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Column(nullable = false)
	public String getTreePath() {
		return treePath;
	}

	/**
	 * 设置树路径
	 * 
	 * @param treePath
	 *            树路径
	 */
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	/**
	 * 获取层级
	 * 
	 * @return 层级
	 */
	@Column(nullable = false)
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 设置层级
	 * 
	 * @param grade
	 *            层级
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
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
	
	@PreRemove
	public void preRemove(){
//		Set<Seller> sellers = this.getBigTypeSeller();
//		if (sellers != null) {
//			for (Seller seller : sellers) {
//				seller.setBigType(null);
//			}
//		}
//		Set<Seller> smallSellers = this.getSmallTypeSeller();
//		if (sellers != null) {
//			for (Seller seller : smallSellers) {
//				seller.setSmallType(null);
//			}
//		}
//		Set<Seller> rankSellers = this.getRankSeller();
//		if (sellers != null) {
//			for (Seller seller : rankSellers) {
//				seller.setRank(null);
//			}
//		}
//		Set<Seller> startSellers = this.getStarLevelSeller();
//		if (sellers != null) {
//			for (Seller seller : startSellers) {
//				seller.setStarLevel(null);
//			}
//		}
		
	}
	
	
	
}
