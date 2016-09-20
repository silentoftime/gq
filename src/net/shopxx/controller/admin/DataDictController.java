/*
 * Copyright 2005-2014 nbcyl.net. All rights reserved.
 * Support: http://www.nbcyl.net
 * License: http://www.nbcyl.net/license
 */
package net.shopxx.controller.admin;



import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.entity.DataDict;
import net.shopxx.service.DataDictService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Controller - 数据字典
 * 
 * @author  刘赛
 * @version 2014-10-21 新建</BR>
 */
@Controller("adminDataDictController")
@RequestMapping("/admin/data_dict")
public class DataDictController extends BaseController {
	
	@Resource(name = "dataDictServiceImpl")
	private DataDictService dataDictService;

	/**
	 * 检查编号是否唯一
	 */
	@RequestMapping(value = "/check_code", method = RequestMethod.GET)
	public @ResponseBody
	boolean check_code(String previousCode, String code) {
		if (StringUtils.isEmpty(code)) {
			return false;
		}
		if (dataDictService.snUnique(previousCode, code)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("dataDict", dataDictService.findDataDictTree());
		return "/admin/datadict/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(DataDict dataDict, Long pid, RedirectAttributes redirectAttributes) {
		dataDict.setParent(dataDictService.find(pid));
		if (!isValid(dataDict)) {
			return ERROR_VIEW;
		}
		dataDict.setPid(pid);
		dataDict.setGrade(null);
		dataDict.setTreePath(null);
//		dataDict.setRankSeller(null);
//		dataDict.setStarLevelSeller(null);
//		dataDict.setBigTypeSeller(null);
//		dataDict.setSmallTypeSeller(null);
		//字典子列表会自动赋值
		dataDict.setChild(null);
		dataDictService.save(dataDict);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		//根据字典ID查询
		DataDict dataDict = dataDictService.find(id);
		//查询全部字典
		model.addAttribute("dataDictAll",dataDictService.findDataDictTree());
		//当前数据字典对象
		model.addAttribute("dataDict",dataDict);
		//查询父ID相同并且同级
		model.addAttribute("children",dataDictService.findChildren(dataDict));
		return "/admin/datadict/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(DataDict dataDict, Long pid, RedirectAttributes redirectAttributes) {
		dataDict.setParent(dataDictService.find(pid));
		if (!isValid(dataDict)) {
			return ERROR_VIEW;
		}
		if (dataDict.getParent() != null) {
			//得到父级数据字典列表
			DataDict parent = dataDict.getParent();
			if (parent.equals(dataDict)) {
				return ERROR_VIEW;
			}
			List<DataDict> children = dataDictService.findChildren(parent);
			if (children != null && children.contains(parent)) {
				return ERROR_VIEW;
			}
		}
		dataDictService.update(dataDict, "treePath", "grade", "children", "rankSeller", "starLevelSeller", "bigTypeSeller", "smallTypeSeller");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model) {
		model.addAttribute("dataDict", dataDictService.findDataDictTree());
		return "/admin/datadict/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long id) {
		DataDict dataDict = dataDictService.find(id);
		if (dataDict == null) {
			return ERROR_MESSAGE;
		}
		Set<DataDict> children = dataDict.getChild();
		if (children != null && !children.isEmpty()) {
			return Message.error("admin.productCategory.deleteExistChildrenNotAllowed");
		}
		dataDictService.delete(id);
		return SUCCESS_MESSAGE;
	}
	
}
