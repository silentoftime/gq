/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Filter;
import net.shopxx.entity.LuckEntity.FiveElement;
import net.shopxx.entity.TraditionalCharacter;
import net.shopxx.service.TraditionalCharacterService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.spreada.utils.chinese.ZHConverter;

/**
 * Utils - Web
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller
public final class ChConverter {
	
//	@Resource(name = "traditionalCharacterServiceImpl")
//	private TraditionalCharacterService traditionalCharacterService;
	
	
	 private static TraditionalCharacterService myTraditionalCharacterService;
     
	 TraditionalCharacterService getTraditionalCharacterService() {
	        return myTraditionalCharacterService;
	    }
	 
	     
	    //核心代码 
	    @Resource(name = "traditionalCharacterServiceImpl")  
	    public void setTraditionalCharacterService(TraditionalCharacterService traditionalCharacterService) {  
	    	ChConverter.myTraditionalCharacterService = traditionalCharacterService;  
	    } 
	
	public static String surName[] = {  
            "赵","钱","孙","李","周","吴","郑","王","冯","陈","楮","卫","蒋","沈","韩","杨",  
            "朱","秦","尤","许","何","吕","施","张","孔","曹","严","华","金","魏","陶","姜",  
            "戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎",  
            "鲁","韦","昌","马","苗","凤","花","方","俞","任","袁","柳","酆","鲍","史","唐",  
            "费","廉","岑","薛","雷","贺","倪","汤","滕","殷","罗","毕","郝","邬","安","常",  
            "乐","于","时","傅","皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄",  
            "和","穆","萧","尹","姚","邵","湛","汪","祁","毛","禹","狄","米","贝","明","臧",  
            "计","伏","成","戴","谈","宋","茅","庞","熊","纪","舒","屈","项","祝","董","梁",  
            "杜","阮","蓝","闽","席","季","麻","强","贾","路","娄","危","江","童","颜","郭",  
            "梅","盛","林","刁","锺","徐","丘","骆","高","夏","蔡","田","樊","胡","凌","霍",  
            "虞","万","支","柯","昝","管","卢","莫","经","房","裘","缪","干","解","应","宗",  
            "丁","宣","贲","邓","郁","单","杭","洪","包","诸","左","石","崔","吉","钮","龚",  
            "程","嵇","邢","滑","裴","陆","荣","翁","荀","羊","於","惠","甄","麹","家","封",  
            "芮","羿","储","靳","汲","邴","糜","松","井","段","富","巫","乌","焦","巴","弓",  
            "牧","隗","山","谷","车","侯","宓","蓬","全","郗","班","仰","秋","仲","伊","宫",  
            "宁","仇","栾","暴","甘","斜","厉","戎","祖","武","符","刘","景","詹","束","龙",  
            "叶","幸","司","韶","郜","黎","蓟","薄","印","宿","白","怀","蒲","邰","从","鄂",  
            "索","咸","籍","赖","卓","蔺","屠","蒙","池","乔","阴","郁","胥","能","苍","双",  
            "闻","莘","党","翟","谭","贡","劳","逄","姬","申","扶","堵","冉","宰","郦","雍",  
            "郤","璩","桑","桂","濮","牛","寿","通","边","扈","燕","冀","郏","浦","尚","农",  
            "温","别","庄","晏","柴","瞿","阎","充","慕","连","茹","习","宦","艾","鱼","容",  
            "向","古","易","慎","戈","廖","庾","终","暨","居","衡","步","都","耿","满","弘",  
            "匡","国","文","寇","广","禄","阙","东","欧","殳","沃","利","蔚","越","夔","隆",  
            "师","巩","厍","聂","晁","勾","敖","融","冷","訾","辛","阚","那","简","饶","空",  
            "曾","毋","沙","乜","养","鞠","须","丰","巢","关","蒯","相","查","后","荆","红",  
            "游","竺","权","逑","盖","益","桓","公","晋","楚","阎","法","汝","鄢","涂","钦",  
            "岳","帅","缑","亢","况","后","有","琴","商","牟","佘","佴","伯","赏","墨","哈",  
            "谯","笪","年","爱","阳","佟"};  
      
	public static String doubleSurName[] = {"万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方",  
            "赫连","皇甫","尉迟","公羊","澹台","公冶","宗政","濮阳","淳于","单于","太叔","申屠",  
            "公孙","仲孙","轩辕","令狐","锺离","宇文","长孙","慕容","鲜于","闾丘","司徒","司空",  
            "丌官","司寇","仉","督","子车","颛孙","端木","巫马","公西","漆雕","乐正","壤驷","公良",  
            "拓拔","夹谷","宰父","谷梁","段干","百里","东郭","南门","呼延","归","海","羊舌","微生",  
            "梁丘","左丘","东门","西门","南宫"};  
	

	private static ArrayList<MetaLibItem> libs = new ArrayList<MetaLibItem>();  
	
	
	
	
	static {
		ChConverter.libs.add(new MetaLibItem(1, 2, "一乙"));  
        ChConverter.libs.add(new MetaLibItem(2, 0, "九几"));  
        ChConverter.libs.add(new MetaLibItem(2, 1, "二丁了力乃刁"));  
        ChConverter.libs.add(new MetaLibItem(2, 2, "又"));  
        ChConverter.libs.add(new MetaLibItem(2, 3, "七人十入刀"));  
        ChConverter.libs.add(new MetaLibItem(2, 4, "卜八乜"));  
        ChConverter.libs.add(new MetaLibItem(3, 0, "久工口乞干弓广"));  
        ChConverter.libs.add(new MetaLibItem(3, 1, "大女巳孒"));  
        ChConverter.libs.add(new MetaLibItem(3, 2, "也于丸己土山个"));  
        ChConverter.libs.add(new MetaLibItem(3, 3, "三小千士川上才寸刃夕尸"));  
        ChConverter.libs.add(new MetaLibItem(3, 4, "万下亡凡子"));  
        ChConverter.libs.add(new MetaLibItem(4, 0, "介牛五公月元及孔今亢卞欠犬斤牙勾勻木气"));  
        ChConverter.libs.add(new MetaLibItem(4, 1, "天六中日丑斗内太屯丹井弔支止之午尺火从丰"));  
        ChConverter.libs.add(new MetaLibItem(4, 2, "尹允王友引尤厄曰毋切予"));  
        ChConverter.libs.add(new MetaLibItem(4, 3, "仁升氏心什仇手仍少殳戈爪双"));  
        ChConverter.libs.add(new MetaLibItem(4, 4, "夫匹巴文方互分比勿反歹户毛父幻不仆化云毋壬水片币仆"));  
        ChConverter.libs.add(new MetaLibItem(5, 0, "古加卉玉甲可功瓜甘丘句外去巧札术本卯未"));  
        ChConverter.libs.add(new MetaLibItem(5, 1, "代旦只冬立田叮他令召台尼奴冉左丙另叹"));  
        ChConverter.libs.add(new MetaLibItem(5, 2, "右央由用永以仔瓦幼叶戊"));  
        ChConverter.libs.add(new MetaLibItem(5, 3, "生史仙占册申仕四世市司出仟且示石失尻刊正主矢"));  
        ChConverter.libs.add(new MetaLibItem(5, 4, "民弘布平皮丕付玄弁白必目包禾半兄北弗末皿匆母穴疋氷夯"));  
        ChConverter.libs.add(new MetaLibItem(6, 0, "交旭价吉仰曲共伎伉企各戎机考件朽竹朵匡朱"));  
        ChConverter.libs.add(new MetaLibItem(6, 1, "亘打老至自尖仲灯匠多耳宅虫同年劣全光弛兆旨肉礼"));  
        ChConverter.libs.add(new MetaLibItem(6, 2, "亦宇夷因衣有羊伊安圭伍艮地吐圳充羽戌灰优"));  
        ChConverter.libs.add(new MetaLibItem(6, 3, "字次守如在而西舟再存夙色早式先冲丞吏寺旬死庄此任州臣曳列舌众"));  
        ChConverter.libs.add(new MetaLibItem(6, 4, "好休伏行仿合刑血名后回亥妃百米冰并牟伐牝兇凶危汀印收向后"));  
        ChConverter.libs.add(new MetaLibItem(7, 0, "言见見更克角角君吟吾旱改谷究岑我估妓局劫告杆攻却忌困杖杜吴吳杌肖村材杞何杏启"));  
        ChConverter.libs.add(new MetaLibItem(7, 1, "良甸男佟里牢廷吕呂住志呈佃伶灶体灸利足町李豆吞弄努低彤但妥卵弟佗求杉巫免采卤听"));  
        ChConverter.libs.add(new MetaLibItem(7, 2, "邑佑完位秃禿役余似延均坑岐冶坍辰坟坊坂牡医"));  
        ChConverter.libs.add(new MetaLibItem(7, 3, "成助车車作伸身孜佐秀伺坐束宋序赤妆妝私妊壮壯串些吹声忍走辛巡初吢佘系兑酉判系"));  
        ChConverter.libs.add(new MetaLibItem(7, 4, "希孚孝宏妙伴甫伯兵尾呆步每妣亨贝貝别妤况形忘佛忙含妨否罕坔江戒池汝冷尨妍"));  
        ChConverter.libs.add(new MetaLibItem(8, 0, "狂抗技快玖佳供佶枝析姑炆炘宜奇欣居岳固其果劻季卷斧昂纠糾京空孤官肯券屈卧臥乖穹祁林杵杻东東竺枝具佼杼松羌析杷杭板杯枚昏忽柿"));  
        ChConverter.libs.add(new MetaLibItem(8, 1, "两兩肋投侗佻侍侈例岱征妮姐典卓直来來到长長定知忠制争爭冽帖店忝底念的妾弩乳政徂戽折狄決昆易炊咎帑炎旻罔籴昊隶丽炉"));  
        ChConverter.libs.add(new MetaLibItem(8, 2, "抑依侑往夜委奄昀宛旺于於亚亞盂艾坤岩坦坡坪忧岭"));  
        ChConverter.libs.add(new MetaLibItem(8, 3, "忱沖抄侃使孥姗姓昇尚宗社受虱事承始昌宙舍青叔昔取刹刷儿兒妻所凭垂刺妬社祀姒金庚刻周卒"));  
        ChConverter.libs.add(new MetaLibItem(8, 4, "扶沐汾版抛没汶侔佩彼妹武朋孟秉命享和府门門奉明岸沛房放并並味幸咊或帛服氓効弦物虎盲呼非卹阜把昊汽沅岡卦沂汲沚宓沃雨协協汪沁沙沈牧状狀冼庙奋弥怀"));  
        ChConverter.libs.add(new MetaLibItem(9, 0, "侠俠俄拘拒柑柯科纪紀祈皆军軍彦彥契姱羿禺轨軌建癸冠客玩肝哄架界劲勁姜剋看竿故急姣虹姬斫肖柱柳相柘柴芒柄柏面"));  
        ChConverter.libs.add(new MetaLibItem(9, 1, "侣侶俊待律沺招抽拐纣紂祉重贞貞炤昭亮耐帝南亭盾段泰奏炭致突订訂怠怒赴柬怨昱勅姞祉映歪炳炫厘类邓"));  
        ChConverter.libs.add(new MetaLibItem(9, 2, "衩耶押约約怡威勇音要禹囿屋畏姻哀幽宥昶韦韋姚奎垣型盆"));  
        ChConverter.libs.add(new MetaLibItem(9, 3, "衫信俗促侵咨拆拙秋是首则則哉前省思帅帥宣叙页頁俞春星食性砂削查柔施姿室昨甚牲峙俟昝厍厙枯怪度姹姝"));  
        ChConverter.libs.add(new MetaLibItem(9, 4, "侯保便係衍後河法泊波泓泯抱拍秒红紅飞飛厚屏香美风風品盈巷盼盃勃眇眉炫昧皇表封负負某扁玫毗甭宦羑哈咸奔狐泔计計炬染治注沼拜泥沮油況沿泳泑泱泉宪"));  
        ChConverter.libs.add(new MetaLibItem(10, 0, "兼俱纭紜矩倚径徑屐桂格根栓娱娛娟粉家起记記肩芸衿珈括尅恭库庫豈哥拳躬笈氣缺虔贡貢鬼恢骨高耕衾皋奚芝桃桐株芷芯桑栗桌核校桓芬芙芽芳花笏倏样芦"));  
        ChConverter.libs.add(new MetaLibItem(10, 1, "恬衲冻凍纳納伦倫倒倬值徒秩娣娜娘娌唐朔烈哲庭珍凌玲玳朕晁展晋晉恕料旃岛島特畜耄讨討留恥疾娲旅斗鬥耿晃烟恣秦祝芮夏烜烘虑敌达积烛玺党恋栾"));  
        ChConverter.libs.add(new MetaLibItem(10, 2, "准容员員祐晏益肴恩殷宴案翁袁烟盎乌烏育個原峡峽峨砧轩軒城埋破砲"));  
        ChConverter.libs.add(new MetaLibItem(10, 3, "纯純纸紙修倩借徐持娠时時宰孙孫差峻宸财財拾狩指珊师師仓倉钊釗弱素书書租乘射席殉祠座息殊栽神笑讪訕针針闪閃酌索烝隼眞真邛倪刚剛宫宮罡祖晁钉釘祚剖曹斋耸迁蚕"));  
        ChConverter.libs.add(new MetaLibItem(10, 4, "娑纹紋纺紡纷紛倍倖候俵俸俯倣秘秤娥派洽肥肪恨珀恒圃训訓峰畝效埋畔旁冥併臭蚊豹配釜马馬勉峰航害眠病疲耘耗恤祜洸洨纽紐洲洞洛流洪洋活纱紗洗津洒酒洼洁"));  
        ChConverter.libs.add(new MetaLibItem(11, 0, "卿救规規顷頃健偶偕御械梧悍悟娸康国國教基崑启啟乾区區坚堅苦珪寄崔晤近贯貫眼毬皎眷研竟翊袈寇圈苟邢梯苕笠笞笛救苡英苑圉彬梢梓桶若许許笙笥苫啟枭梟梗梅茂符苻茅盖"));  
        ChConverter.libs.add(new MetaLibItem(11, 1, "胎振崙朗侦偵條從埜累将將章翎梨带帶那动動执執犁张張敕斛顶頂鹿梁悌略粒聊豚袋狷软軟聆羚帐帳聃娄婁鹵烷烽戚珣晟袜断离"));  
        ChConverter.libs.add(new MetaLibItem(11, 2, "浴涎胃偃伟偉悠移婉英胡庸寅唯庵尉欲迎窕眺翌野异異敖域基崎堀堂崩鸟鳥峥培崩运"));  
        ChConverter.libs.add(new MetaLibItem(11, 3, "袖绅紳组組绍紹细細终終侧側得匙处處焌参參紫专專晨祥船常崧崇趾彩钏唱勘阡商问問寂庶彫旌巢宿悉旋叙敘敍斜爽珠悦率产產祭窓粗羞设設船钗釵责責馗赦雀讼訟紮绝絕胙胥邢钓釣眾紬书書副珮钒剩"));  
        ChConverter.libs.add(new MetaLibItem(11, 4, "袍胞浦浲海浮斌绊絆培偏徘货貨婚妇婦胖娬背麦麥习習鱼魚匾望望敏凰苗婆务務密閈闬彪败敗晦毫捕晚粕访訪曼邦贩販被麻票班讹訛悖邠毕畢扈浩涓匏浪涕涂浴涎浚消浸浙雪涉習习范还"));  
        ChConverter.libs.add(new MetaLibItem(12, 0, "雁絖给給绞絞结結络絡草棋椅棍堦迭掘掛球荃皖捲琹稀期景开開皓尧堯蛟敢傑幾嵌贵貴凯凱欺雅割寓筐棨乔喬窘街强款硬邱栖统統栋棟植筌答茶筑筒棠椒绛絳筳庾茹栈棧森茜茸筍策荏茱最桌荀棚棒棉棼筏荒阮筑茧"));  
        ChConverter.libs.add(new MetaLibItem(12, 1, "盗盜诊診场場琉捷探理琅凉涼程敦婷喨軼贴貼登智轴軸痛劳勞量能单單粧短巽凌淩衕闰轸軫循幅迢就寻尋媛邰屠娲硫炀煬晴晶焦焙惇焱欻证"));  
        ChConverter.libs.add(new MetaLibItem(12, 2, "詠恶惡围圍为為翔翕蛙黑奠壹贻貽堰越惟勋黄黃砚硯堤堪岚嵐堡费費邮阴阳"));  
        ChConverter.libs.add(new MetaLibItem(12, 3, "丝絲绒絨钞鈔絢绚註词詞诏詔诉訴授措推琇净淨税稍竦创創须須絮残殘替尊窗疎胜勝兹黍象裁斯视視超然曾殖掌甦贰貳舜甥善喻顺順舒剩盛竣脂情惜散奢枣棗粟述邵丧喪犀钧鈞钦欽割觇覘问問辜钮鈕钝鈍钠鈉接粟童剀剴甯朝堵鈄钭壶壺邹"));  
        ChConverter.libs.add(new MetaLibItem(12, 4, "闷悶冯馮评評迫排虚防媚发發粥弼賁贲徨喜惠斐閒闵閔帽报報备備蛤买買雄寒云雲富復贺賀普贸貿扉悲唤喚傍脉脈博无無雰傅邯邳邴邶彭猛番淇涯淦混顸頇荆现現淋添淡淘幅淹液涴淑清深淞项項渊淵猎蛮"));  
        ChConverter.libs.add(new MetaLibItem(13, 0, "琴绢絹经經倾傾楷裙揭暇睽誇勤祺诘詰莞嫁廉献羣舅跪义義感禁解业業鼓极極筠港减減琦琪琨郊窟颂頌愚干幹靴敬畸靳楠莉莨琳莒荻筱椰杨楊预預蛾媵莎莠椿楫榆苋莧莘枫楓楣莆茕煢莽"));  
        ChConverter.libs.add(new MetaLibItem(13, 1, "粮传傳楠廊提稔稜暖睦煜炼煉脱煖当當鼎跡督塔农農雉殿追琢退电電虞驰馳煎裹顿頓路寗零詹媸禄祿艇照碖郅偻炀煬扬揚旸暘烟煙炜煒琰徭煊焕煥煤煇煌逄毓"));  
        ChConverter.libs.add(new MetaLibItem(13, 2, "筳傭阿犹猶握稚暗话話衙矮意碗奥雍附琬爱愛裔圆圓爷爺饮飲园園嫈恽惲块塊圣聖碑"));  
        ChConverter.libs.add(new MetaLibItem(13, 3, "辞送迺绣愉债債催羡捷暄诗詩询詢裕详詳试試荘驯馴伤傷新势勢岁歲歆蜀想斟资資琮琛楚轼軾肆蜃裟载載靖嗣暑装鼠愈剿凖愁酬勣郄禽睢钜钰鈺饬飭铃鈴钲鉦钿鈿邾铅鉛絺触"));  
        ChConverter.libs.add(new MetaLibItem(13, 4, "补補雹绥綏粳鈵钵鉢琵换換挥揮晖暉湍琶聘颁頒号號瓶郁陂惶湣浑渾会會蜂微妈媽鸠鳩盟饭飯较較熙莫裘港媲贾賈涂塗雷汤湯渡浈湞渭游湖湛湶测測渝湘溆湫渠渻郇孵募"));  
        ChConverter.libs.add(new MetaLibItem(14, 0, "綺绮纲綱魁紧緊降诰誥诫誡语語郡菊槐侨僑侥僥伪偽瑟斳赫旗箇轻輕嘉箕歌犒肇愿管疑郏郜兢菱构構戬戩榴榔莱菅榕莞荣榮算尝嘗箒粽翠笺箋菜菁菑萌幕梦夢榜裴枪槍"));  
        ChConverter.libs.add(new MetaLibItem(14, 1, "尽盡寧宁绿綠纶綸绰綽绫綾通连連逐透誌种種郎菱溜嫩嫡对對团團裳奖獎态態端恺愷彰尔爾尘塵畅暢辣獃叹嘆歎领領廖臺台赵趙嫘滕僚摇枪槍榭制製绫綾蜡"));  
        ChConverter.libs.add(new MetaLibItem(14, 2, "维維诞誕温溫斲瑛玮瑋瑕顼頊與与嫣境翟硕碩墀"));  
        ChConverter.libs.add(new MetaLibItem(14, 3, "绶綬综綜慈绸綢造速认認诵誦说說诚誠称稱僧僖像衔銜阀閥精聚甄饰飾寿壽誓酸赈賑粹实實瑜瑄瑞署察齐齊狮獅綮慎银銀韶铨銓铜銅瑒玚划劃臧铭銘郝郤"));  
        ChConverter.libs.add(new MetaLibItem(14, 4, "網网绵綿逢溥诲誨铭銘福僕嫚貌萍窪洼闻聞饲飼飒颯辅輔碧华華饱飽蜜魂凤鳳熊鸣鳴鼻陌宾賓豪阁閣髦熏闽閩舞滑寡源溪灭滅阁閣嘏溶瑚溢沧滄滋滏溲滇郗涤滌"));  
        ChConverter.libs.add(new MetaLibItem(15, 0, "葵慷慨廣枢樞萼稼俭儉價仪儀鞏课課驾駕毅靠穀郭箴庆慶宽寬娇嬌谊誼颉頡麫麪驹駒概稽葛樟楼樓稻樑箭竖豎蒂稷樣莹瑩樊耦萱槽箱葫葱葆标標模篇筅筅樗贤賢築药"));  
        ChConverter.libs.add(new MetaLibItem(15, 1, "缎緞缇緹练練董缔締缓緩腰徵彻徹德阵陣瑭刘劉谅諒乐樂调調慮弹彈谈談层層轮輪暟敵论論进進頫蝶瑯除萳摘暂暫驻駐践踐质質寮鲁魯鼐儋厉厲黎褚逯郯歎糊热熱僻"));  
        ChConverter.libs.add(new MetaLibItem(15, 2, "嫺亿億院阅閱养養逸慰欧歐鞍影頞豌蝴憂蝯葉郵卫衛嶔娴嫻确確逵增墩磋磁磐"));  
        ChConverter.libs.add(new MetaLibItem(15, 3, "节節绪緒线線锄鋤锈衝陞升婵嬋赏賞审審帜幟锐銳熟瑲玱趣赐数數蚀蝕媭谆諄请請靓靚敷辤驷駟剧劇剑劍肠腸摯挚闾閭缄緘锋鋒铺鋪劈"));  
        ChConverter.libs.add(new MetaLibItem(15, 4, "编編翩暴範廟魄嬉霈蝦蝠饼餅辉輝慧暮漫摸兴興部滹漠漂满滿墨瞑卖賣辈輩虢醇摩履盘盤赔賠箲蝙赋賦萬汉漢缗緡幣漪浒滸滚涨漲霆滺渔漁演霄渍漬漩震漕褒慕"));  
        ChConverter.libs.add(new MetaLibItem(16, 0, "遇过過裤褲机機桥橋橘谐諧谚諺颊頰黔圜县縣笃篤器龟龜哙噲蓋蒯蓄蓁麇萤螢蓉颖穎鄂阎閻树樹苍蒼冀亲親整横桦樺义義褰蒲"));  
        ChConverter.libs.add(new MetaLibItem(16, 1, "道谘諮遂達錄录燐積灯燈燑瑾燉燄璋陵陈陳陶螣谛諦臻琏璉赖賴历曆靛蹄头頭导導歴龙龍卢盧俦儔璃糖鸯鴦都颓頹骆駱陆陸晓曉璇燃炽熾烧燒熹"));  
        ChConverter.libs.add(new MetaLibItem(16, 2, "遐遊運陰谓謂燕衡鸭鴨餘鸳鴛豫融歙嬴瓯甌郓鄆坛壇壁磨勋勳"));  
        ChConverter.libs.add(new MetaLibItem(16, 3, "锤錘锥錐锡錫钱錢错錯谕諭縢诸諸辑輯静嫱醒儒蒨侪儕战戰叡雕锦錦钢鋼錡锜锯鋸谒謁谖諼铮錚锭錠撮赪赬穆"));  
        ChConverter.libs.add(new MetaLibItem(16, 4, "遍熺烨燁潢谋謀陪学學霏奮颔頷缚縛默憲潘蒙鲍鮑潔浇澆溃潰靦腼霍潭潮润潤澄澂霖寰澍霑霓愤憤"));  
        ChConverter.libs.add(new MetaLibItem(17, 0, "颗谦謙玑璣检檢嶽恳懇擎击擊举舉糠艰艱讲講懋罄鞠觊覬赚賺黚矫矯莲蓮联聯檀蒋蔚营營蔡蔓篷豁蓬"));  
        ChConverter.libs.add(new MetaLibItem(17, 1, "队隊绩績麋隆赯儡励勵瞳嬭奶瞭辗輾纵縱螳临臨螺擂择擇燧炼煉黛隸襄蒋謄誊灿燦燭燥黏燮繆缪"));  
        ChConverter.libs.add(new MetaLibItem(17, 2, "優应應远遠壑陽擁拥醖酝婴嬰邬鄔嶺矶磯壕"));  
        ChConverter.libs.add(new MetaLibItem(17, 3, "操赛賽钟鐘齋聲骏駿聳偿償縻禅禪糟徽瞬缩縮聪聰总總逊遜锹鍬谢謝饯餞鲜鮮蹇鄒隋键鍵锅鍋谿锻鍛镀鍍氈舆輿赜"));  
        ChConverter.libs.add(new MetaLibItem(17, 4, "鄉鸿鴻繁霞璠璜璟禧賸嫔嬪镁鎂韩韓浓濃潞亵泽澤彌澹霜"));  
        ChConverter.libs.add(new MetaLibItem(18, 0, "翘翹谨謹绕繞骐騏槛檻搁擱擬璩归歸睑瞼简簡颜顏骑騎鹃鵑黠觐覲魏瞽蕊簪瞿隗鬆获獲鹄鵠萧蕭旧舊"));  
        ChConverter.libs.add(new MetaLibItem(18, 1, "烬燼禮斷戴適爵焘燾糧瞻转轉职職蟲鲤鯉题題釐曜豐燻"));  
        ChConverter.libs.add(new MetaLibItem(18, 2, "陨隕醫讴謳鄢壘礎璧"));  
        ChConverter.libs.add(new MetaLibItem(18, 3, "翼翱雙织織颛顓璨缮繕蝉蟬觞觴锁鎖秽穢鎗謦聂聶环環阙闕镇鎮鎚鎚储儲铠鎧镒鎰镕鎔迟遲"));  
        ChConverter.libs.add(new MetaLibItem(18, 4, "繐馥谟謨滨濱濮闖闯覆蹒蹣鞭涛濤济濟濯濙湿濕杂雜"));  
        ChConverter.libs.add(new MetaLibItem(19, 0, "关關麒攀曠鲸鯨遗遺撷願愿蚁蟻蟹繫薊蓟难難签簽繭薛麴薇麓竞競萨薩"));  
        ChConverter.libs.add(new MetaLibItem(19, 1, "谭譚韬韜離璽辙轍赠贈證际際麗類绎繹譔韲郑鄭鄧蠊庞龐"));  
        ChConverter.libs.add(new MetaLibItem(19, 2, "嬿稳穩"));  
        ChConverter.libs.add(new MetaLibItem(19, 3, "选選绣繡遷颠顛赞贊兽獸祷禱锵鏘遵畴疇鹊鵲识識辭绳繩鲭鲰鯫谯譙镜鏡繫链鏈镠鏐鏖镖鏢"));  
        ChConverter.libs.add(new MetaLibItem(19, 4, "鵰谱譜鹏鵬簿绘繪穫羹霪薄獵靡"));  
        ChConverter.libs.add(new MetaLibItem(20, 0, "琼瓊议議劝勸继繼觉覺警悬懸舰艦严嚴邀牺犧阚闞郐鄶藏纂篮籃籍筹籌骞騫獻薰麵"));  
        ChConverter.libs.add(new MetaLibItem(20, 1, "龄齡罗羅腾騰赡贍獭獺阐闡胧朧黨窦竇爐耀宝寶飘飄"));  
        ChConverter.libs.add(new MetaLibItem(20, 2, "孆耀矿礦砾礫壤"));  
        ChConverter.libs.add(new MetaLibItem(20, 3, "释釋钟鐘馨繻译譯触觸黥齣锏鐧镪鏹鐔镡镫鐙"));  
        ChConverter.libs.add(new MetaLibItem(20, 4, "還懷颟顢露"));  
        ChConverter.libs.add(new MetaLibItem(21, 0, "艺藝顾顧龈齦驱驅饒鷄嚣囂颢顥藤藥莺鶯樱櫻饶饒"));  
        ChConverter.libs.add(new MetaLibItem(21, 1, "跻躋蠟鳎鰨馔饌缠纏"));  
        ChConverter.libs.add(new MetaLibItem(21, 2, "趯蠡誉譽跃躍巍"));  
        ChConverter.libs.add(new MetaLibItem(21, 3, "随隨镌鐫续續铎鐸属屬镰鐮铁鐵鉴鑒"));  
        ChConverter.libs.add(new MetaLibItem(21, 4, "护護鹤鶴轟轰霹辩辯霸黯飜藩瀾澜灌"));  
        ChConverter.libs.add(new MetaLibItem(22, 0, "权權龢俨儼笼籠蘆鬻藷蘇苏龚龔"));  
        ChConverter.libs.add(new MetaLibItem(22, 1, "叠疊读讀摄攝聽躒跞龛龕颤顫赎贖囊览覽"));  
        ChConverter.libs.add(new MetaLibItem(22, 2, "璎瓔隐隱懿鬻窃巅巔"));  
        ChConverter.libs.add(new MetaLibItem(22, 3, "衬襯铸鑄鬚癣癬袭襲鑑"));  
        ChConverter.libs.add(new MetaLibItem(22, 4, "骅驊穰響响鳗鰻欢歡边邊沣灃霽霁灑"));  
        ChConverter.libs.add(new MetaLibItem(23, 0, "驿驛验驗兰蘭籖鱖鳜鹰鷹"));  
        ChConverter.libs.add(new MetaLibItem(23, 1, "兰蘭戀麟鳞鱗欒显顯"));  
        ChConverter.libs.add(new MetaLibItem(23, 2, "缨纓巖鑛矿鑢"));  
        ChConverter.libs.add(new MetaLibItem(23, 3, "攒攢霉铄鑠"));  
        ChConverter.libs.add(new MetaLibItem(23, 4, "囏变變黴霉"));  
        ChConverter.libs.add(new MetaLibItem(24, 0, "赣搅攪醸霭靄"));  
        ChConverter.libs.add(new MetaLibItem(24, 1, "灵靈雳靂鹭鷺篱籬鱣鳣"));  
        ChConverter.libs.add(new MetaLibItem(24, 2, "艳艷盐鹽罐"));  
        ChConverter.libs.add(new MetaLibItem(24, 3, "鑫蠶"));  
        ChConverter.libs.add(new MetaLibItem(24, 4, "鸂"));  
        ChConverter.libs.add(new MetaLibItem(25, 0, "观觀缵纘羁羈"));  
        ChConverter.libs.add(new MetaLibItem(25, 2, "叆靉"));  
        ChConverter.libs.add(new MetaLibItem(25, 4, "蠻灏灝酆"));  
        ChConverter.libs.add(new MetaLibItem(26, 0, ""));  
        ChConverter.libs.add(new MetaLibItem(26, 1, "逻邏郦酈"));  
        ChConverter.libs.add(new MetaLibItem(26, 3, "骥驥讚赞"));  
        ChConverter.libs.add(new MetaLibItem(26, 4, "湾灣"));  
        ChConverter.libs.add(new MetaLibItem(27, 1, "骧驤缆纜銮鑾"));  
        ChConverter.libs.add(new MetaLibItem(27, 3, "钻鑽锣鑼"));  
        ChConverter.libs.add(new MetaLibItem(27, 4, "滦灤"));  
        ChConverter.libs.add(new MetaLibItem(28, 0, "戆"));  
        
	}
	/**
	 * 不可实例化
	 */
	private ChConverter() {
	}
	
	/**
	 * 获取笔画
	 * @param item
	 * @return
	 */
   public static int getStrokes(char item) {  
	   
	   List<Filter> filters = new ArrayList<Filter>();
	   filters.add(Filter.eq("unicode", Integer.toHexString(item).toUpperCase()));
		List<TraditionalCharacter> findList = myTraditionalCharacterService.findList(null, filters , null);
		if (findList.size() > 0) {
			TraditionalCharacter traditionalCharacter = findList.get(0);
			return traditionalCharacter.getKangxiStrokes();
		}
//	   for (MetaLibItem metaLibItem : libs) {
//		   if (metaLibItem.IfStringexist(item)) {
//			   return metaLibItem.getBh();
//		   }
//	   }
        return -1;  
    }  
   
   /**
    * 获取笔画
    * @param item
    * @return
    */
   public int getStrokes(String item) {  
	   
	   for (MetaLibItem metaLibItem : libs) {
		   if (metaLibItem.IfStringexist(item)) {
			   return metaLibItem.getBh();
		   }
	   }
	   return -1;  
   }  
	
	/**
	 * 简体转繁体
	 * 
	 * @param simpStr
	 *            简体字符串
	 * @return 繁体字符串
	 */
	public static String SimToTra(String simpStr) {
		ZHConverter converter = ZHConverter
				.getInstance(ZHConverter.TRADITIONAL);
		String traditionalStr = null;
		traditionalStr = converter.convert(simpStr);
		return traditionalStr;
	}
	/**
	 * 繁体转简体
	 * 
	 * @param tradStr
	 *            繁体字符串
	 * @return 简体字符串
	 */
	public static String TraToSim(String tradStr) {
		ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
		String simplifiedStr = converter.convert("間單");
		return simplifiedStr;
	}
	
	public static Boolean isDoubleSurName(String name) {
		if (StringUtils.isEmpty(name)) {
			return false;
		}
		if (name.length() <= 1) {
			return false;
		}
		String substring = name.substring(0, 2);
		int length = doubleSurName.length;
		for (int i = 0; i < length; i++) {
			if (doubleSurName[i].equals(substring)) {
				return true;
			}
		}
		return false;
	}
	
	public static String getFirstName(String name) {
		if (isDoubleSurName(name)) {
			return name.substring(0, 2);
		} else {
			return name.substring(0, 1);
		}
	}
	public static String getLastName(String name) {
		if (isDoubleSurName(name)) {
			return name.substring(2);
		} else {
			return name.substring(1);
		}
	}
	
	public static String getMainName(String name) {
		if (isDoubleSurName(name)) {
			return name.substring(1, 3);
		} else {
			return name.substring(0, 2);
		}
	}
	public static String getFrontName(String name) {
		if (isDoubleSurName(name)) {
			return name.substring(2);
		} else {
			return name.substring(1);
		}
	}
	
	public static Integer getFirstLuckStrokes(String name) {
		String firstName = getFirstName(name);
		String simToTra = SimToTra(firstName);
		int length = simToTra.length();
		int strokeCount = 0;
		if (length <= 1) {
			
			strokeCount = getStrokes(simToTra.charAt(0)) + 1;
		} else {
			for (int i = 0; i < length; i++) {
				strokeCount += getStrokes(simToTra.charAt(i));
			}
		}
		
		System.out.println("getFirstLuckStrokes:"+simToTra+ "  ====  strokeCount:"+ strokeCount );
		return strokeCount;
	}
	
	public static Integer getMainLuckStrokes(String name) {
		int strokeCount = 0;
		String mainName = getMainName(name);
		String simToTra = SimToTra(mainName);
		int length = simToTra.length();
		for (int i = 0; i < length; i++) {
			strokeCount += getStrokes(simToTra.charAt(i));
		}
		System.out.println("getMainLuckStrokes:"+simToTra+ "  ====  strokeCount:"+ strokeCount );
		return strokeCount;
	}
	
	public static Integer getFrontLuckStrokes(String name) {
		int strokeCount = 0;
		String mainName = getFrontName(name);
		String simToTra = SimToTra(mainName);
		int length = simToTra.length();
		if (length <= 1) {
			strokeCount = getStrokes(simToTra.charAt(0)) + 1;
		} else {
			for (int i = 0; i < length; i++) {
				strokeCount += getStrokes(simToTra.charAt(i));
			}
		}
		System.out.println("getFrontLuckStrokes:"+simToTra+ "  ====  strokeCount:"+ strokeCount );
		return strokeCount;
	}
	
	public static Integer getBackLuckStrokes(String name) {
		int strokeCount = 0;
		String simToTra = SimToTra(name);
		int length = simToTra.length();
		for (int i = 0; i < length; i++) {
			strokeCount += getStrokes(simToTra.charAt(i));
		}
		System.out.println("getBackLuckStrokes:"+simToTra+ "  ====  strokeCount:"+ strokeCount );
		return strokeCount;
	}
	
	public static Integer getSpiritLuckStrokes(String name) {
		int strokeCount = 0;
		strokeCount = getBackLuckStrokes(name) - getMainLuckStrokes(name);
		String simToTra = SimToTra(name);
		if (isDoubleSurName(name)) {
			if (name.substring(2).length() <= 1) {
				strokeCount += 1;
			}
		} else {
			strokeCount += 1;
			if (name.substring(1).length() <= 1) {
				strokeCount += 1;
			}
		}
		System.out.println("                      getSpiritLuckStrokes:"+simToTra+ "  ====  strokeCount:"+ strokeCount );
		return strokeCount;
	}
	
	public static FiveElement getFiveElment(int nStrokes) {
		switch (getUnit(nStrokes)) {
		case 1: case 2:
			nStrokes = 0;
			break;
		case 3: case 4:
			nStrokes = 1;
		break;
		case 5: case 6:
			nStrokes = 2;
		break;
		case 7: case 8:
			nStrokes = 3;
		case 9: case 10:
			nStrokes = 3;
		break;

		default:
			break;
		}
		FiveElement[] values = FiveElement.values();
		for (FiveElement fiveElement : values) {
			int ordinal = fiveElement.ordinal();
			if (ordinal == nStrokes) {
				return fiveElement;
			}
		}
		return null;
	}
	public static Integer getUnit(int nStrokes) {
		return nStrokes%10 == 0 ? 10:nStrokes%10;
	}

	public static void main(String[] args) {
		getFirstLuckStrokes("司马相如");
		getFirstLuckStrokes("司马光");
		getFirstLuckStrokes("林则徐");
		getFirstLuckStrokes("林森");
		getFirstLuckStrokes("李文智");
		
		getMainLuckStrokes("司马相如");
		getMainLuckStrokes("司马光");
		getMainLuckStrokes("林则徐");
		getMainLuckStrokes("林森");
		getMainLuckStrokes("李文智");
		
		getFrontLuckStrokes("司马相如");
		getFrontLuckStrokes("司马光");
		getFrontLuckStrokes("林则徐");
		getFrontLuckStrokes("林森");
		getFrontLuckStrokes("李文智");
		
		getBackLuckStrokes("司马相如");
		getBackLuckStrokes("司马光");
		getBackLuckStrokes("林则徐");
		getBackLuckStrokes("林森");
		getBackLuckStrokes("李文智");
		
		getSpiritLuckStrokes("司马相如");
		getSpiritLuckStrokes("司马光");
		getSpiritLuckStrokes("林则徐");
		getSpiritLuckStrokes("林森");
		getSpiritLuckStrokes("李文智");
	}
	


}