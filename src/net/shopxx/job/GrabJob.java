/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.job;

import java.net.MalformedURLException;

import javax.annotation.Resource;

import net.shopxx.service.SecondHrefService;
import net.shopxx.service.TraditionalCharacterService;
import net.shopxx.util.GrabUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;

/**
 * Job - 购物车
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Component("grabJob")
@Lazy(false)
public class GrabJob {

	@Resource(name = "traditionalCharacterServiceImpl")
	private TraditionalCharacterService traditionalCharacterService;
	
	@Resource(name = "secondHrefServiceImpl")
	private SecondHrefService secondHrefService;
	
	private Log log = LogFactory.getLog(GrabJob.class);

	/**
	 * 登录检测
	 */
	@Scheduled(fixedDelayString = "${job.grab.fixedDelay}")
	public void  grab() {
		log.info("begin grab ...");
		try {
//			  DomNodeList<DomElement> elementsByTagName;
//				elementsByTagName = GrabUtils.initPage();
////				List<TraditionalCharacter> traditionalCharacters = new ArrayList<TraditionalCharacter>();
//				int s = 0;
////				int n = 1;
////		        for (int n = 1; n <= 6; n++) {
////			        traditionalCharacters = setCharacterLine(traditionalCharacters, elementsByTagName, n);
//		        for (DomElement domElement : elementsByTagName) {
//		    		String asXml = domElement.asXml();
//		    		if (asXml.contains("shd("+1)) {
//			    		GrabUtils.setLeftHref( domElement, asXml, 1, secondHrefService, traditionalCharacterService);
//			    		s++;
//			    		log.info("grab :"+s+" word");
//		    		}
//		    	}
//		        for (DomElement domElement : elementsByTagName) {
//		    		String asXml = domElement.asXml();
//		    		if (asXml.contains("shd("+2)) {
//			    		GrabUtils.setLeftHref( domElement, asXml, 2, secondHrefService, traditionalCharacterService);
//			    		s++;
//			    		log.info("grab :"+s+" word");
//		    		}
//		    	}
//		        for (DomElement domElement : elementsByTagName) {
//		    		String asXml = domElement.asXml();
//		    		if (asXml.contains("shd("+3)) {
//			    		GrabUtils.setLeftHref( domElement, asXml, 3, secondHrefService, traditionalCharacterService);
//			    		s++;
//			    		log.info("grab :"+s+" word");
//		    		}
//		    	}
//		        for (DomElement domElement : elementsByTagName) {
//		    		String asXml = domElement.asXml();
//		    		if (asXml.contains("shd("+4)) {
//			    		GrabUtils.setLeftHref( domElement, asXml, 4, secondHrefService, traditionalCharacterService);
//			    		s++;
//			    		log.info("grab :"+s+" word");
//		    		}
//		    	}
//		        for (DomElement domElement : elementsByTagName) {
//		    		String asXml = domElement.asXml();
//		    		if (asXml.contains("shd("+5)) {
//			    		GrabUtils.setLeftHref( domElement, asXml, 5, secondHrefService, traditionalCharacterService);
//			    		s++;
//			    		log.info("grab :"+s+" word");
//		    		}
//		    	}
//		        for (DomElement domElement : elementsByTagName) {
//		    		String asXml = domElement.asXml();
//		    		if (asXml.contains("shd("+6)) {
//			    		GrabUtils.setLeftHref( domElement, asXml, 6, secondHrefService, traditionalCharacterService);
//			    		s++;
//			    		log.info("grab :"+s+" word");
//		    		}
//		    	}
			        
//		        }
			} catch (Exception e) {
				log.info(e.getMessage());
			}
//		GrabUtils.closeWebClient();
		log.info("end grab ...");
		
	}

}