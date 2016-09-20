/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.controller.shop;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.shopxx.Message;
import net.shopxx.Principal;
import net.shopxx.Setting;
import net.shopxx.Setting.AccountLockType;
import net.shopxx.Setting.CaptchaType;
import net.shopxx.entity.Cart;
import net.shopxx.entity.Member;
import net.shopxx.entity.SecondHref;
import net.shopxx.entity.TraditionalCharacter;
import net.shopxx.service.CaptchaService;
import net.shopxx.service.CartService;
import net.shopxx.service.MemberService;
import net.shopxx.service.RSAService;
import net.shopxx.service.SecondHrefService;
import net.shopxx.service.TraditionalCharacterService;
import net.shopxx.util.GrabUtils;
import net.shopxx.util.SettingUtils;
import net.shopxx.util.WebUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Controller - 会员登录
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("shopLoginController")
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "cartServiceImpl")
	private CartService cartService;
	
	@Resource(name = "traditionalCharacterServiceImpl")
	private TraditionalCharacterService traditionalCharacterService;
	
	@Resource(name = "secondHrefServiceImpl")
	private SecondHrefService secondHrefService;

	/**
	 * 登录检测
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody
	Boolean check() {
		return memberService.isAuthenticated();
	}
	
	/**
	 * 登录检测
	 */
	@RequestMapping(value = "/grab", method = RequestMethod.GET)
	public @ResponseBody
	Boolean grab(Integer lineNumber) {
		try {
////			 DomNodeList<DomElement> elementsByTagName;
////				elementsByTagName = GrabUtils.initPage();
////				int s = 0;
////			for (DomElement domElement : elementsByTagName) {
////	    		String asXml = domElement.asXml();
////	    		if (asXml.contains("shd("+lineNumber)) {
////		    		GrabUtils.setHref( domElement, asXml, lineNumber, secondHrefService);
////		    		s++;
////	    		}
////	    	}
//			List<SecondHref> findAll = secondHrefService.findAll();
//			
////			List<SecondHref> findLeft = secondHrefService.findLeft();
////			List<TraditionalCharacter> traditionalCharacters = traditionalCharacterService.findAll();
////			for (TraditionalCharacter traditionalCharacter : traditionalCharacters) {
////				for (SecondHref secondHref : findAll) {
////					if (secondHref.getUnicode().equals(traditionalCharacter.getUnicode())) {
////						findAll.remove(secondHref);
////						break;
////					}
////				}
////			}
////			for (SecondHref secondHref : findLeft) {
////				System.out.println(secondHref);
////				TraditionalCharacter setTraditionalCharacter = GrabUtils.setTraditionalCharacter(secondHref);
//////				System.out.println(setTraditionalCharacter);
////				if (setTraditionalCharacter != null) {
////					traditionalCharacterService.save(setTraditionalCharacter);
////				}
//////				Thread.sleep(1000);
////			}
////			findLeft = secondHrefService.findLeft();
//////			List<TraditionalCharacter> traditionalCharacters = traditionalCharacterService.findAll();
//////			for (TraditionalCharacter traditionalCharacter : traditionalCharacters) {
//////				for (SecondHref secondHref : findAll) {
//////					if (secondHref.getUnicode().equals(traditionalCharacter.getUnicode())) {
//////						findAll.remove(secondHref);
//////						break;
//////					}
//////				}
//////			}
//			for (SecondHref secondHref : findAll) {
//				System.out.println(secondHref);
//				TraditionalCharacter setTraditionalCharacter = GrabUtils.setTraditionalCharacter(secondHref);
////				System.out.println(setTraditionalCharacter);
//				if (setTraditionalCharacter != null) {
//					traditionalCharacterService.save(setTraditionalCharacter);
//					secondHrefService.delete(secondHref);
//				}
////				Thread.sleep(1000);
//			}
		} catch (Exception e) {
			e.getStackTrace();
			e.printStackTrace();
		}
		return memberService.isAuthenticated();
	}
	

	/**
	 * 登录页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(String redirectUrl, HttpServletRequest request, ModelMap model) {
		Setting setting = SettingUtils.get();
		if (redirectUrl != null && !redirectUrl.equalsIgnoreCase(setting.getSiteUrl()) && !redirectUrl.startsWith(request.getContextPath() + "/") && !redirectUrl.startsWith(setting.getSiteUrl() + "/")) {
			redirectUrl = null;
		}
		model.addAttribute("redirectUrl", redirectUrl);
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		
		
		return "/shop/login/index";
	}

	/**
	 * 登录提交
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody
	Message submit(String captchaId, String captcha, String username, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String password = rsaService.decryptParameter("enPassword", request);
		rsaService.removePrivateKey(request);

		if (!captchaService.isValid(CaptchaType.memberLogin, captchaId, captcha)) {
			return Message.error("shop.captcha.invalid");
		}
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return Message.error("shop.common.invalid");
		}
		Member member;
		Setting setting = SettingUtils.get();
		if (setting.getIsEmailLogin() && username.contains("@")) {
			List<Member> members = memberService.findListByEmail(username);
			if (members.isEmpty()) {
				member = null;
			} else if (members.size() == 1) {
				member = members.get(0);
			} else {
				return Message.error("shop.login.unsupportedAccount");
			}
		} else {
			member = memberService.findByUsername(username);
		}
		if (member == null) {
			return Message.error("shop.login.unknownAccount");
		}
		if (!member.getIsEnabled()) {
			return Message.error("shop.login.disabledAccount");
		}
		if (member.getIsLocked()) {
			if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member)) {
				int loginFailureLockTime = setting.getAccountLockTime();
				if (loginFailureLockTime == 0) {
					return Message.error("shop.login.lockedAccount");
				}
				Date lockedDate = member.getLockedDate();
				Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
				if (new Date().after(unlockDate)) {
					member.setLoginFailureCount(0);
					member.setIsLocked(false);
					member.setLockedDate(null);
					memberService.update(member);
				} else {
					return Message.error("shop.login.lockedAccount");
				}
			} else {
				member.setLoginFailureCount(0);
				member.setIsLocked(false);
				member.setLockedDate(null);
				memberService.update(member);
			}
		}

		if (!DigestUtils.md5Hex(password).equals(member.getPassword())) {
			int loginFailureCount = member.getLoginFailureCount() + 1;
			if (loginFailureCount >= setting.getAccountLockCount()) {
				member.setIsLocked(true);
				member.setLockedDate(new Date());
			}
			member.setLoginFailureCount(loginFailureCount);
			memberService.update(member);
			if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member)) {
				return Message.error("shop.login.accountLockCount", setting.getAccountLockCount());
			} else {
				return Message.error("shop.login.incorrectCredentials");
			}
		}
		member.setLoginIp(request.getRemoteAddr());
		member.setLoginDate(new Date());
		member.setLoginFailureCount(0);
		memberService.update(member);

		Cart cart = cartService.getCurrent();
		if (cart != null) {
			if (cart.getMember() == null) {
				cartService.merge(member, cart);
				WebUtils.removeCookie(request, response, Cart.ID_COOKIE_NAME);
				WebUtils.removeCookie(request, response, Cart.KEY_COOKIE_NAME);
			}
		}

		Map<String, Object> attributes = new HashMap<String, Object>();
		Enumeration<?> keys = session.getAttributeNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			attributes.put(key, session.getAttribute(key));
		}
		session.invalidate();
		session = request.getSession();
		for (Entry<String, Object> entry : attributes.entrySet()) {
			session.setAttribute(entry.getKey(), entry.getValue());
		}

		session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), username));
		WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getUsername());

		return SUCCESS_MESSAGE;
	}

}