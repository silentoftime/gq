/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.controller.shop;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.entity.Career;
import net.shopxx.entity.Career.Type;
import net.shopxx.entity.Character;
import net.shopxx.entity.InterpersonalSocial;
import net.shopxx.entity.MatheMatical;
import net.shopxx.entity.ThreeTalent;
import net.shopxx.service.CareerService;
import net.shopxx.service.CharacterService;
import net.shopxx.service.InterpersonalSocialService;
import net.shopxx.service.MatheMaticalService;
import net.shopxx.service.ThreeTalentService;
import net.shopxx.util.ChConverter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller - 姓名预测
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("shopNameLuckController")
@RequestMapping("/nameLuck")
public class NameLuckController extends BaseController {

	@Resource(name = "matheMaticalServiceImpl")
	private MatheMaticalService matheMaticalService;
	
	@Resource(name = "characterServiceImpl")
	private CharacterService characterService;
	
	@Resource(name = "threeTalentServiceImpl")
	private ThreeTalentService threeTalentService;
	
	@Resource(name = "interpersonalSocialServiceImpl")
	private InterpersonalSocialService interpersonalSocialService;
	
	@Resource(name = "careerServiceImpl")
	private CareerService careerService;
	
	private static Integer SCORE_MATHE_MATICAL = 5;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String list(String name, ModelMap model) {
		if (StringUtils.isEmpty(name)) {
			return ERROR_VIEW;
		}
		model.addAttribute("name", ChConverter.SimToTra(name));
		model.addAttribute("firstName", ChConverter.SimToTra(ChConverter.getFirstName(name)));
		model.addAttribute("lastName", ChConverter.SimToTra(ChConverter.getLastName(name)));
		
		
		Integer firstLuckStrokes = ChConverter.getFirstLuckStrokes(name);
		
		Integer mainLuckStrokes = ChConverter.getMainLuckStrokes(name);
		
		Integer frontLuckStrokes = ChConverter.getFrontLuckStrokes(name);
		
		Integer backLuckStrokes = ChConverter.getBackLuckStrokes(name);
		
		Integer spiritLuckStrokes = ChConverter.getSpiritLuckStrokes(name);
		
		MatheMatical firstLuckMatheMatical = matheMaticalService.findByStrokes(firstLuckStrokes);
		MatheMatical mainLuckMatheMatical = matheMaticalService.findByStrokes(mainLuckStrokes);
		MatheMatical frontLuckMatheMatical = matheMaticalService.findByStrokes(frontLuckStrokes);
		MatheMatical backLuckMatheMatical = matheMaticalService.findByStrokes(backLuckStrokes);
		MatheMatical spiritLuckMatheMatical = matheMaticalService.findByStrokes(spiritLuckStrokes);
		
		int firstLuckScore = SCORE_MATHE_MATICAL - firstLuckMatheMatical.getLevel().ordinal();
		int mainLuckScore = (SCORE_MATHE_MATICAL - mainLuckMatheMatical.getLevel().ordinal())*2;
		int frontLuckScore = (SCORE_MATHE_MATICAL - frontLuckMatheMatical.getLevel().ordinal())*2;
		int backLuckScore = (SCORE_MATHE_MATICAL - backLuckMatheMatical.getLevel().ordinal())*2;
		int spiritLuckScore = SCORE_MATHE_MATICAL - spiritLuckMatheMatical.getLevel().ordinal();
		
		model.addAttribute("firstLuckMatheMatical", firstLuckMatheMatical);
		model.addAttribute("mainLuckMatheMatical", mainLuckMatheMatical);
		model.addAttribute("frontLuckMatheMatical", frontLuckMatheMatical);
		model.addAttribute("backLuckMatheMatical",	backLuckMatheMatical);
		model.addAttribute("spiritLuckMatheMatical", spiritLuckMatheMatical);
		
		model.addAttribute("firstLuckScore", firstLuckScore);
		model.addAttribute("mainLuckScore", mainLuckScore);
		model.addAttribute("frontLuckScore", frontLuckScore);
		model.addAttribute("backLuckScore",	backLuckScore);
		model.addAttribute("spiritLuckScore", spiritLuckScore);
		
		
		String threeElement = ChConverter.getFiveElment(firstLuckStrokes).getElementName()
							+ ChConverter.getFiveElment(mainLuckStrokes).getElementName()
							+ ChConverter.getFiveElment(frontLuckStrokes).getElementName();
		ThreeTalent threeTalent = threeTalentService.findByThreeElement(threeElement);
		int threeTalentScore = 40 - threeTalent.getLevel().ordinal()*10;
		model.addAttribute("threeTalent", threeTalent);
		model.addAttribute("threeTalentScore", threeTalentScore);
		
		Character character = characterService.findByStrokes(ChConverter.getUnit(mainLuckStrokes));
		model.addAttribute("character", character);
		
		List<Career> findChildren = careerService.findChildren(careerService.findByElement(ChConverter.getFiveElment(mainLuckStrokes)));
		Career successCareer = null;
		Career basicCareer = null;
		for (Career career : findChildren) {
			if (Type.success.equals(career.getType()) && ChConverter.getFiveElment(firstLuckStrokes).equals(career.getElement())) {
				successCareer = career;
			}
			if (Type.basic.equals(career.getType()) && ChConverter.getFiveElment(frontLuckStrokes).equals(career.getElement())) {
				basicCareer = career;
			}
		}
		float successCareerScore = (float) (12.5 - ((float)successCareer.getLevel().ordinal()*2.5));
		float basicCareerScore = (float) (12.5 - ((float)basicCareer.getLevel().ordinal()*2.5));
		model.addAttribute("successCareer", successCareer);
		model.addAttribute("basicCareer", basicCareer);
		model.addAttribute("successCareerScore", successCareerScore);
		model.addAttribute("basicCareerScore", basicCareerScore);
		
		List<InterpersonalSocial> interpersonalSocials = interpersonalSocialService.findChildren(interpersonalSocialService.findByStrokes(ChConverter.getUnit(mainLuckStrokes)));
		InterpersonalSocial interpersonalSocial = null;
		for (InterpersonalSocial interpersonal : interpersonalSocials) {
			if (ChConverter.getUnit(spiritLuckStrokes).equals(interpersonal.getStrokes())) {
				interpersonalSocial = interpersonal;
				break;
			}
		}
		
		float interpersonalSocialScore = (float) (12.5 - ((float)interpersonalSocial.getLevel().ordinal()*2.5));
		model.addAttribute("interpersonalSocial", interpersonalSocial);
		model.addAttribute("interpersonalSocialScore", interpersonalSocialScore);
		
		model.addAttribute("finalScore",firstLuckScore 
				+ mainLuckScore 
				+ frontLuckScore 
				+ backLuckScore 
				+ spiritLuckScore
				+ threeTalentScore
				+ successCareerScore
				+ basicCareerScore
				+ interpersonalSocialScore
				);
		
		return "/shop/nameLuck/content";
	}
	
	/**
	 * 找回密码提交
	 */
	@RequestMapping(value = "/simToTra", method = RequestMethod.POST)
	public @ResponseBody
	Message simToTra(String name) {
		return Message.success(ChConverter.SimToTra(name));
	}
	
	
}