function getdl(money,costID,showID){
		var cost = costID;
		
		var cost = document.getElementById(cost).value;
		var je = money;

		var money = (je*1)/(cost*1);
		var num = new Number(money).toFixed(2);
		
		var show = showID;
		document.getElementById(show).value = num;
}
/**
 * 住：此JS适用于合同单的周期和超省标超市标计算。
 * hiddcsb，hiddcity是用于存放数值的超标数额的隐藏域。如果调用此方法，一定要创建者两个隐藏域。
 * 如果修改此JS，请先将新合同单（mainBargainBill.jsp）此功能下的增加和修改的超省标超市标计算方式优化，同时，存储数据的后台也将要获取hiddcsb，hiddcity的两个值。
 * jszq是结算周期，在前台请定义一个此ID的元素接收
 * @autor Rock
 * @param {Object} dlID
 * @param {Object} showShengID
 * @param {Object} showShiID
 * @param {Object} beginTimeID
 * @param {Object} endTimeID
 * @param {Object} shengID
 * @param {Object} shiID
 * @return {TypeName} 
 */
function getShengDB(dlID,showShengID,showShiID,beginTimeID,endTimeID,shengID,shiID){
	var dl = document.getElementById(dlID).value;
	var showsheng = document.getElementById(showShengID).value;
	var showshi = document.getElementById(showShiID).value;
	var beginTime = document.getElementById(beginTimeID).value;
	var endTime = document.getElementById(endTimeID).value;
	var sheng = document.getElementById(shengID).value;
	var shi = document.getElementById(shiID).value;
	
	var bol_sheng = true;
	var bol_shi = true;
	//对有无全省定标电量、有无额定耗电量做判断
	if(sheng==""||sheng==null){
		document.getElementById(showShengID).value="数据库中未查到全省定标电量";
		bol_sheng = false;
	}
	
	if(shi==null||shi==""){
		document.getElementById(showShiID).value="数据库中未查到额定耗电量";
		bol_shi = false;
	}
	
	//如果取值无错，开始计算
	var qssj = beginTime + "-01";
	var jssj = endTime;//将起始时间和结束时间末尾都加上日期
	
	var year = jssj.substr(0,4);
	var mon = jssj.substr(5,2);
	
	var day;
	switch(mon*1){
		case 1:
			day="-31";
			break;
		case 2:
			if(year*1%4==0){
				day="-29";
			}else{
				day="-28";
			}
			break;
		case 3:
			day="-31";
			break;
		case 4:
			day="-30";
			break;
		case 5:
			day="-31";
			break;
		case 6:
			day="-30";
			break;
		case 7:
			day="-31";
			break;
		case 8:
			day="-31";
			break;
		case 9:
			day="-30";
			break;
		case 10:
			day="-31";
			break;
		case 11:
			day="-30";
			break;
		case 12:
			day="-31";
			break;
	}
	
	var LastJssj = jssj+day;
	dt1 = qssj.replace(/-/g,"/");
	dt2 = LastJssj.replace(/-/g,"/");
	var day = (new Date(dt2)-new Date(dt1))/(1000*60*60*24)
	var zq = day*1+1*1;

	if(beginTime==""||endTime==""){
		
	}else{
		document.getElementById("jszq").value=zq;
	}
	
	if(zq<1){
		alert("起始时间和结束时间输入有误！");
		return
	}else{
		if(bol_sheng==true){
			var csb = (dl*1-sheng*zq)/(sheng*zq)*100;
			var h_csb = (dl*1-sheng*zq)/(sheng*zq);//为隐藏域赋值。
			
			var numsheng = new Number(csb).toFixed(2);
			var h_sheng = new Number(h_csb).toFixed(4);//为隐藏域赋值。
			if(numsheng=='NaN'){
				numsheng="";
			}
			document.getElementById(showShengID).value="超省标"+numsheng+"%";
			
		}
		
		if(bol_shi==true){
			var cshib = (dl*1-shi*zq)/(shi*zq)*100;
			var h_cshib = (dl*1-shi*zq)/(shi*zq);//为隐藏域赋值
			
			var numshi = new Number(cshib).toFixed(2);
			var h_shi = new Number(h_cshib).toFixed(4);//为隐藏域赋值。
			if(numshi=="NaN"){
				numshi="";
			}
			document.getElementById(showShiID).value="超市标"+numshi+"%";
		}
		
	}
}
