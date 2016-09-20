/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import net.shopxx.Filter;
import net.shopxx.entity.SecondHref;
import net.shopxx.entity.TraditionalCharacter;
import net.shopxx.service.SecondHrefService;
import net.shopxx.service.TraditionalCharacterService;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * Utils - Web
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public final class GrabUtils {
	
	public static String URL = "http://www.zdic.net/z/kxzd/zbh/";

	
//	@Resource(name = "traditionalCharacterServiceImpl")
//	private TraditionalCharacterService traditionalCharacterService;
	
	private static int timeout = 2000;
	
	private static String  userAgent = "jsoup";
	
	private static String cookie1 = "auth";
	
	private static String cookie2 = "token";
	
	private static WebClient wc;
	
//	public static List<TraditionalCharacter> traditionalCharacters = new ArrayList<TraditionalCharacter>();
	
	public static List<TraditionalCharacter> grab(List<TraditionalCharacter> traditionalCharacters) throws Exception {
		try {
			
			Map<Integer,String> map = new  HashMap<Integer, String>();
			
//			Document doc =Jsoup.connect(URL) 
//					  .userAgent(userAgent) // 设置 User-Agent 
//					  .cookie(cookie1,cookie2) // 设置 cookie 
//					  .timeout(timeout)           // 设置连接超时时间
//					  .get();
		
			//http://www.zdic.net/z/kxzd/zbh/js/prototype.js
			  /**HtmlUnit请求web页面*/  
	        DomNodeList<DomElement> elementsByTagName = initPage();
	        for (int n = 1; n <= 6; n++) {
		        setCharacterLine(traditionalCharacters, elementsByTagName, n);
	        }
	        	
//	        	elementsByTagName.get(0).click(); 
	        	
		} catch (IOException e) {
//			e.printStackTrace();
		}
		
		return traditionalCharacters;
		
	}
	public static HtmlPage initWebClient(String url) {
		WebClient wc = new WebClient(); 
		wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
		
		wc.getOptions().setCssEnabled(false); //禁用css支持  
		wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
		
		
		wc.getOptions().setJavaScriptEnabled(true);  
		wc.getOptions().setActiveXNative(false);  
		wc.getOptions().setCssEnabled(false);  
		wc.getOptions().setThrowExceptionOnScriptError(false);  
		wc.waitForBackgroundJavaScript(600*1000);  
		wc.setAjaxController(new NicelyResynchronizingAjaxController()); 
		
		
		wc.getOptions().setTimeout(10000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
		HtmlPage page = null;
		try {
			page = wc.getPage(url);
		} catch (FailingHttpStatusCodeException e) {
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}  
//		wc.close();
		return page;
	}
	
	public static void closeWebClient() {
		wc.close();
	}

	public static DomNodeList<DomElement> initPage() throws IOException,
			MalformedURLException {
		WebClient wc = new WebClient();  
		wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
		
		wc.getOptions().setCssEnabled(false); //禁用css支持  
		wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
		wc.getOptions().setTimeout(10000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
		HtmlPage page = wc.getPage(URL);  
		
		
//		wc.getOptions().setJavaScriptEnabled(true);  
//		wc.getOptions().setActiveXNative(false);  
//		wc.getOptions().setCssEnabled(false);  
//		wc.getOptions().setThrowExceptionOnScriptError(false);  
//		wc.waitForBackgroundJavaScript(600*1000);  
//		wc.setAjaxController(new NicelyResynchronizingAjaxController()); 
		
		
		String pageXml = page.asXml(); //以xml的形式获取响应文本  
		
		//System.out.println(pageXml);
		
		/**jsoup解析文档*/  
//	        doc = Jsoup.parse(pageXml, URL);
			//System.out.println(doc.html());
      
//	        System.out.println(textContent);
		Integer strokes = 0;
		DomNodeList<DomElement> elementsByTagName = page.getElementsByTagName("a");
		
		return elementsByTagName;
	}


	public static List<TraditionalCharacter> setCharacterLine(
		List<TraditionalCharacter> traditionalCharacters,
		DomNodeList<DomElement> elementsByTagName, int n) throws IOException,
		InterruptedException {
	for (DomElement domElement : elementsByTagName) {
		String asXml = domElement.asXml();
	
	
	
//		setCharacters(traditionalCharacters, domElement, asXml, n);
	
	}
	return traditionalCharacters;
}

	public static void setHref(
			DomElement domElement, String asXml, int n, SecondHrefService secondHrefService) throws IOException,
			InterruptedException {
		Integer strokes;
		
		strokes = Integer.valueOf(domElement.getTextContent());
		String content = "";
		System.out.println(asXml);
		String href = domElement.getAttribute("href");
		
		HtmlPage click = domElement.click();
		
		loadPage(n, click);
		HtmlDivision div = click.getHtmlElementById("kxbhdiv"+n);
		
		DomNodeList<HtmlElement> htmlElements = div.getElementsByTagName("li");
		for (HtmlElement htmlElement : htmlElements) {
			DomNodeList<HtmlElement> a = htmlElement.getElementsByTagName("a");
			SecondHref secondHref = setSecondHref(strokes, a);
			
			secondHrefService.save(secondHref);
		}
		Iterable<DomElement> childElements = div.getChildElements();
		
		for (DomElement domElement2 : childElements) {
			System.out.println(domElement2.getTextContent());
		}
		
		List<HtmlDivision> pageDiv = (List<HtmlDivision>) click.getByXPath("//div[@class='Pages']");
		if (pageDiv == null || pageDiv.size() <= 0) {
			return ;
		}
		DomNodeList<HtmlElement> pageElements = pageDiv.get(0).getElementsByTagName("a");
		Boolean isNextPage = true;
		while (isNextPage) {
			Boolean temp = false;
			HtmlPage click2 = null;
			for (HtmlElement htmlElement : pageElements) {
				if ("下一頁".equals(htmlElement.getTextContent())) {
					click2 = htmlElement.click();
					temp = true;
					loadPage(n,click2);
					HtmlDivision div2 = click2.getHtmlElementById("kxbhdiv"+n);
					
					DomNodeList<HtmlElement> htmlElements2 = div2.getElementsByTagName("li");
					for (HtmlElement htmlElement2 : htmlElements2) {
						DomNodeList<HtmlElement> a = htmlElement2.getElementsByTagName("a");
						SecondHref secondHref = setSecondHref(strokes, a);
						
						secondHrefService.save(secondHref);
					}
					
					
				}
			}
			if (!temp) {
				isNextPage = false;
			} else {
				pageDiv = (List<HtmlDivision>) click2.getByXPath("//div[@class='Pages']");
				pageElements = pageDiv.get(0).getElementsByTagName("a");
			}
		}
		
	}
	public static void setLeftHref(
			DomElement domElement, String asXml, int n, SecondHrefService secondHrefService, TraditionalCharacterService traditionalCharacterService) throws IOException,
			InterruptedException {
		Integer strokes;
		
			strokes = Integer.valueOf(domElement.getTextContent());
			String content = "";
			System.out.println(asXml);
			String href = domElement.getAttribute("href");
			
			HtmlPage click = domElement.click();
			
			loadPage(n, click);
			HtmlDivision div = click.getHtmlElementById("kxbhdiv"+n);
			
			DomNodeList<HtmlElement> htmlElements = div.getElementsByTagName("li");
			for (HtmlElement htmlElement : htmlElements) {
				DomNodeList<HtmlElement> a = htmlElement.getElementsByTagName("a");
				SecondHref secondHref = setTraditionalCharacter(strokes, a, traditionalCharacterService);
				if (secondHref != null)
				secondHrefService.save(secondHref);
			}
			Iterable<DomElement> childElements = div.getChildElements();
			
			for (DomElement domElement2 : childElements) {
				System.out.println(domElement2.getTextContent());
			}
			
			List<HtmlDivision> pageDiv = (List<HtmlDivision>) click.getByXPath("//div[@class='Pages']");
			if (pageDiv == null || pageDiv.size() <= 0) {
				return ;
			}
			DomNodeList<HtmlElement> pageElements = pageDiv.get(0).getElementsByTagName("a");
			Boolean isNextPage = true;
			while (isNextPage) {
				Boolean temp = false;
				HtmlPage click2 = null;
				for (HtmlElement htmlElement : pageElements) {
					if ("下一頁".equals(htmlElement.getTextContent())) {
						click2 = htmlElement.click();
						temp = true;
						loadPage(n,click2);
						HtmlDivision div2 = click2.getHtmlElementById("kxbhdiv"+n);
			    		
			    		DomNodeList<HtmlElement> htmlElements2 = div2.getElementsByTagName("li");
			    		for (HtmlElement htmlElement2 : htmlElements2) {
			    			DomNodeList<HtmlElement> a = htmlElement2.getElementsByTagName("a");
			    			SecondHref secondHref = setTraditionalCharacter(strokes, a, traditionalCharacterService);
			    			if (secondHref != null)
			    			secondHrefService.save(secondHref);
						}
			    		
			    		
					}
				}
				if (!temp) {
					isNextPage = false;
				} else {
					pageDiv = (List<HtmlDivision>) click2.getByXPath("//div[@class='Pages']");
					pageElements = pageDiv.get(0).getElementsByTagName("a");
				}
			}
			
	}
	private static SecondHref setSecondHref(Integer strokes,
			DomNodeList<HtmlElement> a) {
		HtmlElement htmlElement2 = a.get(0);
		SecondHref secondHref = new SecondHref();
		String attribute = htmlElement2.getAttribute("href");
		String unicode = attribute.substring(attribute.lastIndexOf('/') + 1, attribute.indexOf(".htm"));
		secondHref.setHref(attribute);
		secondHref.setTraditional(htmlElement2.getTextContent());
		secondHref.setKangxiStrokes(strokes);
		secondHref.setUnicode(unicode);
		return secondHref;
	}
	private static SecondHref setTraditionalCharacter(Integer strokes,
			DomNodeList<HtmlElement> a, TraditionalCharacterService traditionalCharacterService) {
		HtmlElement htmlElement2 = a.get(0);
		SecondHref secondHref = new SecondHref();
		String attribute = htmlElement2.getAttribute("href");
		String unicode = attribute.substring(attribute.lastIndexOf('/') + 1, attribute.indexOf(".htm"));
		
		List<Filter> filters = new ArrayList<Filter>();
		boolean exists = traditionalCharacterService.exists(Filter.eq("unicode", unicode));
		if (exists) {
			return null;
		}
		secondHref.setHref(attribute);
		secondHref.setTraditional(htmlElement2.getTextContent());
		secondHref.setKangxiStrokes(strokes);
		secondHref.setUnicode(unicode);
		return secondHref;
	}


	public static void setCharacters(
			DomElement domElement, String asXml, int n, TraditionalCharacterService traditionalCharacterService) throws IOException,
			InterruptedException {
		Integer strokes;
		
			strokes = Integer.valueOf(domElement.getTextContent());
			String content = "";
			System.out.println(asXml);
			String href = domElement.getAttribute("href");
			
//			HtmlPage click = initWebClient(href);
			HtmlPage click = domElement.click();
			
//	        		System.out.println(click.getByXPath("//span[@id='kxbhdivLoading1']"));
//	        		break;
			loadPage(n, click);
			HtmlDivision div = click.getHtmlElementById("kxbhdiv"+n);
			
			DomNodeList<HtmlElement> htmlElements = div.getElementsByTagName("li");
			for (HtmlElement htmlElement : htmlElements) {
				DomNodeList<HtmlElement> a = htmlElement.getElementsByTagName("a");
				
//		        			content += a.get(0).getTextContent();
//				traditionalCharacterService.save(setTraditionalCharacter(a.get(0), a.get(0).getTextContent(), strokes));
//				traditionalCharacters.add(setTraditionalCharacter(a.get(0), a.get(0).getTextContent(), strokes));
			}
			Iterable<DomElement> childElements = div.getChildElements();
			
			for (DomElement domElement2 : childElements) {
				System.out.println(domElement2.getTextContent());
			}
			
//	        		System.out.println(click.asXml());
			List<HtmlDivision> pageDiv = (List<HtmlDivision>) click.getByXPath("//div[@class='Pages']");
			if (pageDiv == null || pageDiv.size() <= 0) {
//				return traditionalCharacters;
				return ;
			}
			DomNodeList<HtmlElement> pageElements = pageDiv.get(0).getElementsByTagName("a");
			for (HtmlElement htmlElement : pageElements) {
				if ("下一頁".equals(htmlElement.getTextContent())) {
					HtmlPage click2 = htmlElement.click();
					
//					HtmlPage click2 = initWebClient(htmlElement.getAttribute("href"));
					loadPage(n,click2);
					HtmlDivision div2 = click2.getHtmlElementById("kxbhdiv"+n);
		    		
		    		DomNodeList<HtmlElement> htmlElements2 = div2.getElementsByTagName("li");
		    		for (HtmlElement htmlElement2 : htmlElements2) {
		    			DomNodeList<HtmlElement> a = htmlElement2.getElementsByTagName("a");
//		    	        			content += a.get(0).getTextContent();
//		    			traditionalCharacterService.save(setTraditionalCharacter(a.get(0), a.get(0).getTextContent(), strokes));
//		    			traditionalCharacters.add(setTraditionalCharacter(a.get(0), a.get(0).getTextContent(), strokes));
					}
				}
			}
			
//		        		map.put(strokes, content);
//		        		System.out.println(map);
//		        		System.out.println(content);
			
//		        		break;
			//doc = Jsoup.parse(click.asXml(), URL);
//			return traditionalCharacters;
			
	}


	public static void loadPage(int n, HtmlPage click)
			throws InterruptedException {
		for (int i = 0; i < 20; i++) {
			DomElement elementById = click.getElementById("kxbhdivLoading"+n);
			if (elementById != null) {
				String textContent = elementById.getTextContent();
				if (!"正在加载...".equals(textContent)) {
					break;
				}
			}
			synchronized (click) {
					click.wait(1000);
			}
		}
	}
	
	
	public static TraditionalCharacter setTraditionalCharacter(SecondHref secondHref) {
		try {
			//http://www.zdic.net/z/22/kx/81FA.htm
			
			System.out.println("set begin...");
			TraditionalCharacter tc = new TraditionalCharacter();
			String traditionStr = secondHref.getTraditional();
			Integer kangXiStrokes = secondHref.getKangxiStrokes();
			tc.setKangxiStrokes(kangXiStrokes);
			//unicode
			String attribute = secondHref.getHref();// htmlElement.getAttribute("href");
			String unicode = attribute.substring(attribute.lastIndexOf('/') + 1, attribute.indexOf(".htm"));
			tc.setUnicode(unicode);
			
//			HtmlPage page = htmlElement.click();
//			HtmlPage page = initWebClient("http://www.zdic.net/"+attribute);
			String url ="http://www.zdic.net"+attribute;
			WebClient wc = new WebClient(BrowserVersion.CHROME);  
			wc.getOptions().setJavaScriptEnabled(false); //启用JS解释器，默认为true  
			
			wc.getOptions().setCssEnabled(false); //禁用css支持  
			wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
			wc.getOptions().setTimeout(5000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
			HtmlPage page = wc.getPage(url);  
			
			for (int i = 0; i < 20; i++) {
				DomElement kangXiDictionary = page.getElementById("kxnr");
				
				DomNodeList<HtmlElement> kangXiDictionarys = kangXiDictionary.getElementsByTagName("p");
    			if (kangXiDictionarys != null) {
        			String textContent = kangXiDictionarys.get(0).getTextContent();
        			if (StringUtils.isNotEmpty(textContent)) {
        				break;
        			}
    			}
    			synchronized (page) {
    				page.wait(1000);
    			}
    		}
			try {
				//繁体
				DomElement traditional = page.getElementById("U"+unicode);
				if (traditional != null) {
				String text = traditional.getTextContent().substring(0, 1);
				byte[] buff=text.getBytes("utf-8");
				int f=buff.length;
				if (f >= 4) {
					text = "U"+unicode;
				}
					tc.setTraditional(text);
				} else {
					tc.setTraditional(traditionStr);
				}
			} catch (Exception e) {
			}

			try {
				
				//简体
				DomElement jt = page.getElementById("jt");
				if (jt != null) {
					Iterable<DomElement> jtChild = jt.getChildElements();
					
					for (DomElement jte : jtChild) {
						String textContent = jte.getTextContent().substring(0, 1);
						byte[] buff=textContent.getBytes("utf-8");
						int f=buff.length;
						if (f >= 4) {
							textContent = "U"+unicode;
						}
						tc.setSimplified(textContent);
					}
				} else {
					tc.setSimplified(traditionStr);
				}
			} catch (Exception e) {
			}

			try {
				
			//拼音
				List<DomElement> pinYinZhuyin = (List<DomElement>) page.getByXPath("//td[@class='z_i_t2_py']");
				if (pinYinZhuyin != null) {
					String pinyinContent ="";
					String zhuyinContent ="";
					Iterable<DomElement> pinyins = pinYinZhuyin.get(0).getChildElements();
					for (DomElement pinyin : pinyins) {
						
						pinyinContent += pinyin.getElementsByTagName("a").get(0).getTextContent()+",";
					}
					pinyinContent=pinyinContent.substring(0, pinyinContent.length()-1);
					tc.setPinYin(pinyinContent);
					//注音
					Iterable<DomElement> zhuyins = pinYinZhuyin.get(1).getChildElements();
					for (DomElement zhuyin : zhuyins) {
						
						zhuyinContent += zhuyin.getElementsByTagName("a").get(0).getTextContent()+",";
					}
					zhuyinContent=zhuyinContent.substring(0, zhuyinContent.length()-1);
					tc.setZhuYin(zhuyinContent);
				}
			} catch (Exception e) {
			}

			try {
					
				//部首 部外 总笔画
				List<DomElement> bushou = (List<DomElement>) page.getByXPath("//div[@class='z_it2_jbs']");
				tc.setBuShou(bushou.get(0).getElementsByTagName("a").get(0).getTextContent());
				List<DomElement> buwai = (List<DomElement>) page.getByXPath("//div[@class='z_it2_jbh']");
				tc.setBuWai(buwai.get(0).getElementsByTagName("a").get(0).getTextContent());
				List<DomElement> totalStrokes = (List<DomElement>) page.getByXPath("//div[@class='z_it2_jzbh']");
				tc.setTotalStrokes(Integer.valueOf(totalStrokes.get(0).getElementsByTagName("a").get(0).getTextContent()));
			} catch (Exception e) {
			}

			try {
				
			//字形
			List<DomElement> fontAnalyse = (List<DomElement>) page.getByXPath("//td[@class='z_i_t2']/center");
			tc.setFontAnalyse(fontAnalyse.get(0).getElementsByTagName("a").get(0).getTextContent());
			} catch (Exception e) {
			}

			try {
				
			//异体字
				List<DomElement> variantCharacter = (List<DomElement>) page.getByXPath("//td[@class='z_i_t2_ytz']");
				DomNodeList<HtmlElement> elementsByTagName = variantCharacter.get(0).getElementsByTagName("a");
				String content = "";
				for (HtmlElement htmlElement2 : elementsByTagName) {
					String href = htmlElement2.getAttribute("href");
					content += href.substring(href.lastIndexOf('/') + 1,href.lastIndexOf(".htm"))+",";
				}
				content = content.substring(0,content.length()-1);
				tc.setVariantCharacter(content);
			} catch (Exception e) {
			}

			try {
				
			//五笔 仓颉 郑码 四角 笔顺
				List<DomElement> down = (List<DomElement>) page.getByXPath("//td[@class='z_i_t4']");
				int size = down.size();
				tc.setWuBi(down.get(0).getElementsByTagName("span").get(0).getTextContent());
				tc.setCangJie(down.get(1).getElementsByTagName("span").get(0).getTextContent());
				tc.setZhengCode(down.get(2).getElementsByTagName("span").get(0).getTextContent());
				if (size < 5) {
					tc.setStrokeOrders(down.get(3).getElementsByTagName("span").get(0).getTextContent());
				} else {
					tc.setFourConer(down.get(3).getElementsByTagName("span").get(0).getTextContent());
					tc.setStrokeOrders(down.get(4).getElementsByTagName("span").get(0).getTextContent());
				}
			} catch (Exception e) {
			}
				

			try {
				DomElement kangXiDictionary = page.getElementById("kxnr");
				
				DomNodeList<HtmlElement> kangXiDictionarys = kangXiDictionary.getElementsByTagName("p");
				String kangxiContent = "";
				for (HtmlElement htmlElement2 : kangXiDictionarys) {
					kangxiContent += htmlElement2.getTextContent()+"$$$$";
				}
				kangxiContent=kangxiContent.substring(0, kangxiContent.length()-"$$$$".length());
				tc.setKangXiDictionary(kangxiContent);
				
			} catch (Exception e) {
			}
			System.out.println(tc);
			wc.close();
			System.out.println("close wc ...");
			return tc;
		} catch (Exception e) {
			e.getStackTrace();
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	
	
//	@Test
//	public void dd(){
//		try {
////			grab(traditionalCharacters);
//			   DomNodeList<DomElement> elementsByTagName = initPage();
//		        for (int n = 1; n <= 6; n++) {
////			        traditionalCharacters = setCharacterLine(traditionalCharacters, elementsByTagName, n);
//			        for (DomElement domElement : elementsByTagName) {
//			    		String asXml = domElement.asXml();
//			    		if (asXml.contains("shd("+n)) {
//				    		List<TraditionalCharacter> traditionalCharacters = new ArrayList<TraditionalCharacter>();
//				    		traditionalCharacters = setCharacters(traditionalCharacters, domElement, asXml, n);
//				    		traditionalCharacterService.save(traditionalCharacters );
//			    		}
//			    	}
//			        
//		        }
//			
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) {
		try {
//			grab();
//			System.out.println(traditionalCharacters);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}