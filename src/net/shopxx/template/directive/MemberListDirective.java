/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.entity.Member;
import net.shopxx.service.MemberService;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 会员列表
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Component("memberListDirective")
public class MemberListDirective extends BaseDirective {

	/** 变量名称 */
	private static final String VARIABLE_NAME = "members";

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<Member> members;
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		Integer count = getCount(params);
		List<Filter> filters = getFilters(params, Member.class);
		List<Order> orders = getOrders(params);
		if (useCache) {
			members = memberService.findList(count, filters, orders);
		} else {
			members = memberService.findList(count, filters, orders);
		}
		setLocalVariable(VARIABLE_NAME, members, env, body);
	}

}