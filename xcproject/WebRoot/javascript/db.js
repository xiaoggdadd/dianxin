/*电表操作相关js*/

/*打开供应商信息窗口*/
function openSupplier(){
	window.open("Supplier.jsp", "newwindowSupp", "height=550, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}
/*打开成本中心信息窗口*/
function openCostCenter(obj){
	var gszt = document.form1.gszt.value;
	if(gszt=="0"){
		alert("请选择公司主体");
		return;
	}
	var tr = $(obj).parent().parent();
	var trId = tr.attr('id');
	var costWin = window.open("costCenter.jsp?gszt="+gszt+"&trId="+trId, "newwindowCost", "height=550, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no"); 
}

function addTr(){
    var tr = $("#costTab tr").eq(1).clone();
    tr.attr('id', new Date().getTime());
    var aHTML = '<a href="#" onclick="delTr(this)">删除</a>';
    tr.find("td:last").append(aHTML);
    tr.appendTo("#costTab");
}  
function delTr(obj){
	$(obj).closest('tr').remove();
}  






//================================


function changeGszt(){
	//[A015-上市, B015-存续]
	var gszt = $("select[name='gszt']");
	var tr = $("#costTab tr");
	if(gszt.val() == "0"){
		for(var i=1; i<tr.length; i++){
			var costObj = $(tr[i]).children('td').eq(0).children();//成本中心
			var dutyObj = $(tr[i]).children('td').eq(3).children();//预算责任中心
			costObj.val('0');
			dutyObj.val('0');
		}
	}else{
		$.ajax({
			type: 'post',
			url: path + '/servlet/commonBeanServlet?action=costCenter',
			cache: false,
			data: {
			   gszt: gszt.val()
			},
			dataType: 'json',
			success: function(data){
				if(data){
					var code="",name="";
					var count = data[0];
					var sel = [];
					sel[0] = "<option value='0'>请选择</option>";
					var k=1;
					for(var j=count; j< data.length-1; j+=count){
						code = data[j + data.indexOf("CODE")];
						name = data[j + data.indexOf("NAME")];
						sel[k++] = "<option value='" + code + "'>" + name + "</option>";
					}
					for(var i=1; i<tr.length; i++){
						var costObj = $(tr[i]).children('td').eq(0).children('select');//成本中心
						var dutyObj = $(tr[i]).children('td').eq(3).children('select');//预算责任中心
						costObj.html(sel);
					}
				}
			},error: function(){
				return;
			}
		});
	}
}
/*供应商信息*/
function changeSupplier(){
	
	var codeReq = document.form1.gysmc.value;
	$.ajax({
		type: 'post',
		url: path + '/servlet/commonBeanServlet?action=gys',
		cache:false,
		data: {
			code: codeReq
		},
		dataType: 'json',
		success: function(data){
			if(data){
				var count = data[0];
				var code="",payeeName="",bankAccount="",subjectBank="",openingBank="",province="",city="";
				if(data.length > count + 1){
					code = data[count + data.indexOf("CODE")];
					payeeName = data[count + data.indexOf("PAYEE_NAME")];
					bankAccount = data[count + data.indexOf("BANK_ACCOUNT")];
					subjectBank = data[count + data.indexOf("SUBJECT_BANK")];
					openingBank = data[count + data.indexOf("OPENING_BANK")];
					province = data[count + data.indexOf("PROVINCE")];
					city = data[count + data.indexOf("CITY")];
				}
				document.form1.gysbm.value = code;
				document.form1.skfmc.value = payeeName
				document.form1.yhzh.value = bankAccount
				document.form1.ssyh.value = subjectBank
				document.form1.khyh.value = openingBank
				document.form1.zhsss.value = province
				document.form1.zhssshi.value = city
			}
			
		},
		error: function(){
			return;
		}
	});
}

function changeCostCenter(obj){
	var $TR = $(obj).parent().parent();
	var dutyObj = $TR.children('td').eq(3).children();//[select,input]
	var costObj = $(obj).parent().children();
	if ($(obj).val() == '0') {
		dutyObj.val('0');//请选择
		costObj.val('0');
		return;
	}else{
		$.ajax({
			type: 'post',
			url: path + '/servlet/commonBeanServlet?action=dutyCenter',
			cache: false,
			data: {
			   costCode: $(obj).val()
			},
			dataType: 'json',
			success: function(data){
				if(data){
					var code="",name="";
					var count = data[0];
					if(data.length > count + 1){
						code = data[count + data.indexOf("CODE")];
						name = data[count + data.indexOf("NAME")];
						costObj.val($(obj).val());
						dutyObj.val(code);
					}
					
				}
			},
			error: function(){
				return;
			}
		});
	}
}