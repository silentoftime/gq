<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<title>姓名测试[#if systemShowPowered] - Powered By SHOP++[/#if]</title>
<meta name="author" content="SHOP++ Team" />
<meta name="copyright" content="SHOP++" />
	

<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/article.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {


});
</script>
</head>
<body>
	[#include "/shop/include/header.ftl" /]
	<div class="container articleContent">
		<div class="span6">
			<div class="hotArticleCategory">
				<div class="title">${message("shop.article.articleCategory")}</div>
				[@article_category_root_list count = 10]
					[#list articleCategories as category]
						<dl[#if !category_has_next] class="last"[/#if]>
							<dt>
								<a href="${base}${category.path}">${category.name}</a>
							</dt>
							[#list category.children as articleCategory]
								[#if articleCategory_index == 6]
									[#break /]
								[/#if]
								<dd>
									<a href="${base}${articleCategory.path}">${articleCategory.name}</a>
								</dd>
							[/#list]
						</dl>
					[/#list]
				[/@article_category_root_list]
			</div>
			<div class="hotArticle">
				<div class="title">${message("shop.article.hotArticle")}</div>
				<ul>
				</ul>
			</div>
		</div>
		<div class="span18 last">
			<div class="path">
				<ul>
					<li>
						<a href="${base}/">${message("shop.path.home")}</a>
					</li>
					<li>
					</li>
				</ul>
			</div>
			<div class="main">
				<div class="info">
					<div class="spiritLuck">
						<div class="spiritContent">
							灵运 &nbsp;${spiritLuckMatheMatical.strokes}&nbsp;（${fiveElement(spiritLuckMatheMatical.strokes)}）
						</div>
						<div class ="backLuck">
							后运 &nbsp;${backLuckMatheMatical.strokes}&nbsp;（${fiveElement(backLuckMatheMatical.strokes)}）
						</div>
					</div>
					
					<div class="firstName">
						${firstName}
						<div class="lastName">
							${lastName}
						</div>
					</div>
					<div class ="firstLuck">
						先运 &nbsp;${firstLuckMatheMatical.strokes}&nbsp;（${fiveElement(firstLuckMatheMatical.strokes)}）
						<div class ="mainLuck">
							主运 &nbsp;${mainLuckMatheMatical.strokes}&nbsp;（${fiveElement(mainLuckMatheMatical.strokes)}）
						</div>
						<div class ="frontLuck">
							前运 &nbsp;${frontLuckMatheMatical.strokes}&nbsp;（${fiveElement(frontLuckMatheMatical.strokes)}）
						</div>
					</div>
				
				</div>
				<div class="content">
					
					<ul>
						<li><h5>一.数理：</h5></li>
						<li style="padding-left:20px">
							<div class="leftdiv">
								先运&nbsp;${firstLuckMatheMatical.strokes}&nbsp;
							</div>
							<div class="rightdiv">
								${firstLuckMatheMatical.meaning}(${firstLuckMatheMatical.level.levelName})
							</div>
						</li>
						<li style="padding-left:20px">
							<div class="leftdiv">
								主运&nbsp;${mainLuckMatheMatical.strokes}&nbsp;
							</div>
							<div class="rightdiv">
								${mainLuckMatheMatical.meaning}(${mainLuckMatheMatical.level.levelName})
							</div>
						</li>
						<li style="padding-left:20px">
							<div class="leftdiv">
								前运&nbsp;${frontLuckMatheMatical.strokes}&nbsp;
							</div>
							<div class="rightdiv">
								${frontLuckMatheMatical.meaning}(${frontLuckMatheMatical.level.levelName})
							</div>
							
						</li>
						<li style="padding-left:20px">
							<div class="leftdiv">
								后运&nbsp;${backLuckMatheMatical.strokes}&nbsp;
							</div>
							<div class="rightdiv">
								${backLuckMatheMatical.meaning}(${backLuckMatheMatical.level.levelName})
							</div>
						</li>
						<li style="padding-left:20px">
							<div class="leftdiv">
								灵运&nbsp;${spiritLuckMatheMatical.strokes}&nbsp;
							</div>
							<div class="rightdiv">
								${spiritLuckMatheMatical.meaning}(${spiritLuckMatheMatical.level.levelName})
							</div>
						</li>
						<li><h5>二.三才配置:</h5></li>
						<li style="padding-left:20px">
							<div class="leftdiv">
								${threeTalent.threeElement}&nbsp;
							</div>
							<div class="rightdiv">
								${threeTalent.meaning}(${threeTalent.level.levelName})
							</div>
						</li>
						
						<li>
							<div class="leftdiv">
								<h5>三.性格:</h5>&nbsp;
							</div>
							<div class="rightdiv" style="padding-left:20px">
								${character.meaning}
							</div>
						</li>
						
						<li>
							<div class="leftdiv">
								<h5>四.事业:</h5>
							</div>
							<div class="rightdiv" style="padding-left:20px">
								<div class="leftdiv">
									（${successCareerScore}）&nbsp;${successCareer.type.typeName}&nbsp;
								</div>
								<div class="rightdiv">
									${successCareer.meaning}(${successCareer.level.levelName})
								</div>
								<div class="leftdiv">
									（${basicCareerScore}）&nbsp;${basicCareer.type.typeName}&nbsp;
								</div>
								<div class="rightdiv">
									${basicCareer.meaning}(${basicCareer.level.levelName})
								</div>
							</div>
						</li>
						<li><h5>五.人际关系和社交能力:</h5>&nbsp;<li>
						<li style="padding-left:20px">
							<div class="leftdiv">
								
							</div>
							<div class="rightdiv" style="padding-left:20px">
								${interpersonalSocial.meaning}&nbsp;（${interpersonalSocial.level.levelName}）
							</div>
						</li>
						
						<li><h3>姓名:总评分(${finalScore})</h3></li>
						
					</ul>
				
				</div>
			</div>
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>