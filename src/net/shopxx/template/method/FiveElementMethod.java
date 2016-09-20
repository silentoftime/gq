/*
 * Copyright 2005-2013 nbcyl.com. All rights reserved.
 * Support: http://www.nbcyl.com
 * License: http://www.nbcyl.com/license
 */
package net.shopxx.template.method;

import java.util.List;

import net.shopxx.util.ChConverter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 模板方法 - 文章组装静态path路径,前台不可直接调用article.path. 必须要通过此方法进行解析 args[1]:文章ID args[0]：文章某个分类ID
 * 
 * @author nbcyl Team
 * @version 3.0
 */
@Component("fiveElementMethod")
public class FiveElementMethod implements TemplateMethodModel {

	
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString())) {
			Object[] args = arguments.toArray();
			int parseInt = 0;
			try {
				parseInt = Integer.parseInt(args[0].toString());
			} catch (Exception e) {
			}
			String path = ChConverter.getFiveElment(parseInt).getElementName();
			return new SimpleScalar(path);
		}
		return null;
	}

}