//图片滚动列表 mengjia 070816
var Speed = 10; // 速度(毫秒)
var Space = 10; // 每次移动(px)
var PageWidth = 255; // 翻页宽度
var fill = 0; // 整体移位
var MoveLock = false;
var MoveTimeObj;
var Comp = 0;
var AutoPlayObj = null;
GetObj("List2").innerHTML = GetObj("List1").innerHTML;
GetObj('ISL_Cont').scrollLeft = fill;
GetObj("ISL_Cont").onmouseover = function() {
	clearInterval(AutoPlayObj);
};
GetObj("ISL_Cont").onmouseout = function() {
	AutoPlay();
};
AutoPlay();
function GetObj(objName) {
	if (document.getElementById) {
		return eval('document.getElementById("' + objName + '")');
	} else {
		return eval

		('document.all.' + objName);
	}
}
function AutoPlay() { // 自动滚动
	clearInterval(AutoPlayObj);
	AutoPlayObj = setInterval('ISL_GoDown();ISL_StopDown();', 3000); // 间隔时间
}
function ISL_GoUp() { // 上翻开始
	if (MoveLock)
		return;
	clearInterval(AutoPlayObj);
	MoveLock = true;
	MoveTimeObj = setInterval('ISL_ScrUp();', Speed);
}
function ISL_StopUp() { // 上翻停止
	clearInterval(MoveTimeObj);
	if (GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0) {
		Comp = fill - (GetObj('ISL_Cont').scrollLeft % PageWidth);
		CompScr();
	} else {
		MoveLock = false;
	}
	AutoPlay();
}
function ISL_ScrUp() { // 上翻动作
	if (GetObj('ISL_Cont').scrollLeft <= 0) {
		GetObj('ISL_Cont').scrollLeft = GetObj

		('ISL_Cont').scrollLeft + GetObj('List1').offsetWidth;
	}
	GetObj('ISL_Cont').scrollLeft -= Space;
}
function ISL_GoDown() { // 下翻
	clearInterval(MoveTimeObj);
	if (MoveLock)
		return;
	clearInterval(AutoPlayObj);
	MoveLock = true;
	ISL_ScrDown();
	MoveTimeObj = setInterval('ISL_ScrDown()', Speed);
}
function ISL_StopDown() { // 下翻停止
	clearInterval(MoveTimeObj);
	if (GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0) {
		Comp = PageWidth - GetObj('ISL_Cont').scrollLeft % PageWidth + fill;
		CompScr();
	} else {
		MoveLock = false;
	}
	AutoPlay();
}
function ISL_ScrDown() { // 下翻动作
	if (GetObj('ISL_Cont').scrollLeft >= GetObj('List1').scrollWidth) {
		GetObj('ISL_Cont').scrollLeft =

		GetObj('ISL_Cont').scrollLeft - GetObj('List1').scrollWidth;
	}
	GetObj('ISL_Cont').scrollLeft += Space;
}
function CompScr() {
	var num;
	if (Comp == 0) {
		MoveLock = false;
		return;
	}
	if (Comp < 0) { // 上翻
		if (Comp < -Space) {
			Comp += Space;
			num = Space;
		} else {
			num = -Comp;
			Comp = 0;
		}
		GetObj('ISL_Cont').scrollLeft -= num;
		setTimeout('CompScr()', Speed);
	} else { // 下翻
		if (Comp > Space) {
			Comp -= Space;
			num = Space;
		} else {
			num = Comp;
			Comp = 0;
		}
		GetObj('ISL_Cont').scrollLeft += num;
		setTimeout('CompScr()', Speed);
	}
}

//图片滚动列表 mengjia 070816
var Speed2 = 10; // 速度(毫秒)
var Space2 = 10; // 每次移动(px)
var PageWidth2 = 255; // 翻页宽度
var fill2 = 0; // 整体移位
var MoveLock2 = false;
var MoveTimeObj2;
var Comp2 = 0;
var AutoPlayObj2 = null;
GetObj("List4").innerHTML = GetObj("List3").innerHTML;
GetObj('ISL_Cont2').scrollLeft = fill2;
GetObj("ISL_Cont2").onmouseover = function() {
	clearInterval(AutoPlayObj2);
};
GetObj("ISL_Cont2").onmouseout = function() {
	AutoPlay2();
};
AutoPlay2();

function AutoPlay2() { // 自动滚动
	clearInterval(AutoPlayObj2);
	AutoPlayObj2 = setInterval('ISL_GoDown2();ISL_StopDown2();', 3000); // 间隔时间
}
function ISL_GoUp2() { // 上翻开始
	if (MoveLock2)
		return;
	clearInterval(AutoPlayObj2);
	MoveLock2 = true;
	MoveTimeObj2 = setInterval('ISL_ScrUp2();', Speed2);
}
function ISL_StopUp2() { // 上翻停止
	clearInterval(MoveTimeObj2);
	if (GetObj('ISL_Cont2').scrollLeft % PageWidth2 - fill2 != 0) {
		Comp2 = fill2 - (GetObj('ISL_Cont2').scrollLeft % PageWidth2);
		CompScr2();
	} else {
		MoveLock2 = false;
	}
	AutoPlay2();
}
function ISL_ScrUp2() { // 上翻动作
	if (GetObj('ISL_Cont2').scrollLeft <= 0) {
		GetObj('ISL_Cont2').scrollLeft = GetObj

		('ISL_Cont2').scrollLeft + GetObj('List3').offsetWidth;
	}
	GetObj('ISL_Cont2').scrollLeft -= Space2;
}
function ISL_GoDown2() { // 下翻
	clearInterval(MoveTimeObj2);
	if (MoveLock2)
		return;
	clearInterval(AutoPlayObj2);
	MoveLock2 = true;
	ISL_ScrDown2();
	MoveTimeObj2 = setInterval('ISL_ScrDown2()', Speed2);
}
function ISL_StopDown2() { // 下翻停止
	clearInterval(MoveTimeObj2);
	if (GetObj('ISL_Cont2').scrollLeft % PageWidth2 - fill2 != 0) {
		Comp2 = PageWidth2 - GetObj('ISL_Cont2').scrollLeft % PageWidth2
				+ fill2;
		CompScr2();
	} else {
		MoveLock2 = false;
	}
	AutoPlay2();
}
function ISL_ScrDown2() { // 下翻动作
	if (GetObj('ISL_Cont2').scrollLeft >= GetObj('List3').scrollWidth) {
		GetObj('ISL_Cont2').scrollLeft =

		GetObj('ISL_Cont2').scrollLeft - GetObj('List3').scrollWidth;
	}
	GetObj('ISL_Cont2').scrollLeft += Space2;
}
function CompScr2() {
	var num2;
	if (Comp2 == 0) {
		MoveLock2 = false;
		return;
	}
	if (Comp2 < 0) { // 上翻
		if (Comp2 < -Space2) {
			Comp2 += Space2;
			num2 = Space2;
		} else {
			num2 = -Comp2;
			Comp2 = 0;
		}
		GetObj('ISL_Cont2').scrollLeft -= num2;
		setTimeout('CompScr2()', Speed2);
	} else { // 下翻
		if (Comp2 > Space2) {
			Comp2 -= Space2;
			num2 = Space2;
		} else {
			num2 = Comp2;
			Comp2 = 0;
		}
		GetObj('ISL_Cont2').scrollLeft += num2;
		setTimeout('CompScr2()', Speed2);
	}
}