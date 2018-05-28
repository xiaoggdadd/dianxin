var longitude = "undefined";
var latitude = "undefined";
/**
 * 获取当前经纬度的方法
 * 基于H5的经纬度查询方法
 */
window.onload = function() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition);
	} else {
		document.getElementById("demo").innerHTML = "该浏览器不支持获取地理位置。";
	}
};
function showPosition(position) {
	longitude = position.coords.longitude;
	latitude = position.coords.latitude;
}
/**
 * 手机的图片拍照时的读取以及初步压缩，控制规格
 */
function getBase64Image(img) {
	var canvas = document.createElement("canvas");
	canvas.width = img.width;
	canvas.height = img.height;
	var ctx = canvas.getContext("2d");
	ctx.drawImage(img, 0, 0, img.width, img.height);
	var ext = img.src.substring(img.src.lastIndexOf(".") + 1).toLowerCase();
	var dataURL = canvas.toDataURL("image/" + ext);
	return dataURL;
}
function sendImage(imgStr, path) {
	var bodys = "{\"image\":    \""
			+ imgStr
			+ "\", \"configure\":{\"min_size\" : 10,    \"output_prob\" : true }}";
	var json_bodys = bodys;
	$.ajax({
		url : 'http://tysbgpu.market.alicloudapi.com/api/predict/ocr_general',
		contentType : 'application/json; charset=UTF-8',
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Authorization",
					"APPCODE 0c3f727d0897439fb5746a9c3c3e12fd");
		},
		data : bodys,
		type : 'POST',
		dataType : 'json',
		success : function(data, textStatus, httpRequest) {
			var success = data.success;
			if (success) {
				var value_array = data.ret;
				if (value_array != null && value_array != undefined) {
					var wordStr;
					for ( var i = 0; i < value_array.length; i++) {
						var word = value_array[i].word;
						if (word != undefined) {
							wordStr += word;
						}
					}
					// 添加一个字符串的修改方案
					var num = wordStr.replace(/[^0-9]/ig, "");

					if (path == 'form-field-3') {
						$('#form-field-3').val(num);
					} else if (path == 'form-field-4') {
						$('#form-field-4').val(num);
					} else {
						$('#form-field-5').val(num);
					}
				}

			}
		}
	});
}

// 判断是否存在画布
function isCanvasSupported() {
	var elem = document.createElement('canvas');
	return !!(elem.getContext && elem.getContext('2d'));
}

// 压缩方法
function compress(file, callback) {
	if (typeof (FileReader) === 'undefined') {
		alert("不支持压缩文件");
		console.log("当前浏览器内核不支持base64图标压缩");
		// 调用上传方式 不压缩
	} else {
		try {
			if (!/image\/\w+/.test(file.type)) {
				alert("请确保文件为图像类型");
				return false;
			}
			var reader = new FileReader();
			reader.onload = function(e) {
				var image = $('<img/>');
				image.load(function() {
					console.log("开始压缩");
					var square = 700;
					var canvas = document.createElement('canvas');
					canvas.width = square;
					canvas.height = square;
					var context = canvas.getContext('2d');
					context.clearRect(0, 0, square, square);
					var imageWidth;
					var imageHeight;
					var offsetX = 0;
					var offsetY = 0;
					if (this.width > this.height) {
						imageWidth = Math.round(square * this.width
								/ this.height);
						imageHeight = square;
						offsetX = -Math.round((imageWidth - square) / 2);
					} else {
						imageHeight = Math.round(square * this.height
								/ this.width);
						imageWidth = square;
						offsetY = -Math.round((imageHeight - square) / 2);
					}
					context.drawImage(this, offsetX, offsetY, imageWidth,
							imageHeight);
					var data = canvas.toDataURL('image/jpeg');
					// 压缩完成执行回调
					callback(data);
				});
				image.attr('src', e.target.result);
			};
			reader.readAsDataURL(file);
		} catch (e) {
			console.log("压缩失败!");
			// 调用上传方式 不压缩
		}
	}
}

// 再次查询，执行对站点的位置的查询功能
function doSearchZhandian() {
	var userid = $('#userid').val();
	var zdname  = $('#form-field-select-3').val();//取得名字
	/**
	 * 新查id
	 */
	$.ajax({
		url : '../CBUserServlet',
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		type : 'POST',
		data : {
			'method' : 'searchZDID',
			'userid' : userid,
			'zdname' : zdname
		},
		async : true,
		dataType : 'json',
		success : function(data, textStatus, httpRequest) {
			var flag = data.flag;
			var zdid = data.zdid;
			doSearchZhandianour(zdid);
			}
		});
	
}
/**
 * 原来的步骤
 */
function doSearchZhandianour(zdid){
	var userid = $('#userid').val();
	$.ajax({
		url : '../CBUserServlet',
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		type : 'POST',
		data : {
			'method' : 'searchDb',
			'userid' : userid,
			'zdid' : zdid
		},
		async : true,
		dataType : 'json',
		success : function(data, textStatus, httpRequest) {
			var flag = data.flag;
			var dbList = data.dbListJson;
			var zd = data.zd;
			if (flag) {
				var _str = "<option value=\"\">请选择</option>";
				for ( var i = 0; i < dbList.length; i++) {
					var dbid = dbList[i].ID;
					var dbname = dbList[i].DBNAME;

					var str = "<option value=\"" + dbid + "\">" + dbname
							+ "</option>";
					_str += str;
				}
				$('#form-field-select-1').empty();
				$('#form-field-select-1').append(_str);

				$('#zdjing').val(zd.LONGITUDE);
				$('#zdwei').val(zd.LATITUDE);
				// document.getElementById("zdjing").value = zd;
				// document.getElementById("zdwei").value = zdwei;
			}
		}
	});
}

function doSearchDb() {
	document.getElementById("form-field-1").value="";
	document.getElementById("form-field-2").value="";
	var puttheshijian=0;
	// 查询点电表的位置然后显示电表一并获取位置的经纬度信息进行操作
	var dbid = $('#form-field-select-1').val();
	$('#userdbid').val(dbid);
	// 查询点电表的位置然后显示电表一并获取位置的经纬度信息进行操作
	$.ajax({
		url : '../CBUserServlet',
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		type : 'POST',
		data : {
			'method' : 'searchLastDbdl',
			'dbId' : dbid
		},
		async : true,
		dataType : 'json',
		success : function(data, textStatus, httpRequest) {
			var flag = data.flag;
			var dlObject = data.dljson;
			if (flag) {
				var degree = dlObject.THISDEGREE;
				var datetime = dlObject.THISDATETIME;
				var createdate = dlObject.CREATEDATE;
				var gsz = dlObject.BEILV;
				if(datetime==null||datetime==""){
					puttheshijian=1;
				}else{
					datetime = addDate(datetime, 1);
				}
				
				$('#createdate').val(createdate);
				$('#gsfieldgs').val(gsz);
				if (degree == "" || degree == null || degree == undefined
						|| degree == "0") {
					document.getElementById("form-field-2").readOnly = false;
				} else {
					$('#form-field-2').val(degree);
					document.getElementById("form-field-2").readOnly = true;
				}
				if ( puttheshijian == 1) {
					document.getElementById("form-field-1").readOnly = false;
					document.getElementById("form-field-1").type = "date";
				} else {
					$('#form-field-1').val(datetime);
					document.getElementById("form-field-1").readOnly = true;
				}
				
			} else {
				document.getElementById("form-field-2").readOnly = false;
				document.getElementById("form-field-1").readOnly = false;
				document.getElementById("form-field-1").type = "date";
			}
		}
	});
}

$(document).ready(function() {
	$("#capture1").change(function() {
		var file = this.files[0];
		if (window.FileReader) {
			var reader = new FileReader();
			reader.readAsDataURL(file);
			// 监听文件读取结束后事件

			reader.onloadend = function(e) {
				$("#imgPath1").attr("src", e.target.result); // e.target.result就是最后的路径地址
				var isCS = isCanvasSupported();
				if (isCS) {
					compress(file, function(base64Img) {
						var imgStr = base64Img.split(',')[1];

						sendImage(imgStr, 'form-field-3');
					});
				}
			};
		}
	});
/**
 * 估算值的计算
 */
	$("#form-field-1").change(function() {
            if ($('#form-field-1').val().trim() != "") {
            	
            	var date1 = $('#form-field-1').val();
            	$('#createdate').val(date1);
            var date = getNowFormatDate();
            var dates = DateDiff(date1,  date); //sDate1和sDate2是2006-12-18格式  
         	   var gs = $('#gsfieldgs').val();
         	   if(gs==1||gs==""){
         		  $('#form-field-gs').val("没有相应数据");
         	   }else {
         		  $('#form-field-gs').val(dates*gs);
         	   }
                
            }
	});
	 

	$("#capture3").change(function() {
		var file = this.files[0];
		if (window.FileReader) {
			var reader = new FileReader();
			reader.readAsDataURL(file);
			// 监听文件读取结束后事件
			reader.onloadend = function(e) {
				$("#imgPath3").attr("src", e.target.result); // e.target.result就是最后的路径地址
				sendImage(e.target.result, 'form-field-5');
			};
		}
	});
	// 设置判断抄表的位置判断不允许抄表的情况下刷新页面
	$('#sendForm').click(function() {
		var abc = YZCheck();
		if (abc == 1) {

			$('#longitude').val(longitude);

			$('#latitude').val(latitude);

			$('#form').submit();
		} else {
			alert('无法提交抄表！');
			location.reload([ bForceGet ]);
		}

	});

	$('#resetForm').click(function() {
		$('#form-field-1').val();
		$('#form-field-2').val();
	});
});

function adddianbiaonew(lat1, lng1, lat2, lng2) {
	var f = getRad((lat1 + lat2) / 2);
	var g = getRad((lat1 - lat2) / 2);
	var l = getRad((lng1 - lng2) / 2);

	var sg = Math.sin(g);
	var sl = Math.sin(l);
	var sf = Math.sin(f);

	var s, c, w, r, d, h1, h2;
	var a = EARTH_RADIUS;
	var fl = 1 / 298.257;

	sg = sg * sg;
	sl = sl * sl;
	sf = sf * sf;

	s = sg * (1 - sl) + (1 - sf) * sl;
	c = (1 - sg) * (1 - sl) + sf * sl;

	w = Math.atan(Math.sqrt(s / c));
	r = Math.sqrt(s * c) / w;
	d = 2 * w * a;
	h1 = (3 * r - 1) / 2 / c;
	h2 = (3 * r + 1) / 2 / s;

	return d * (1 + fl * (h1 * sf * (1 - sg) - h2 * (1 - sf) * sg));
}

function rad(d) {
	return d * Math.PI / 180.0;
}
//日期处理的一个方法（加一天）
function addDate(date, days) {
	if (days == undefined || days == '') {
		days = 1;
	}
	var date = new Date(date);
	date.setDate(date.getDate() + days);
	var month = date.getMonth() + 1;
	var day = date.getDate();
	return date.getFullYear() + '-' + getFormatDate(month) + '-'
			+ getFormatDate(day);
}

// 日期月份/天的显示，如果是1位数，则在前面加上'0'
function getFormatDate(arg) {
	if (arg == undefined || arg == '') {
		return '';
	}

	var re = arg + '';
	if (re.length < 2) {
		re = '0' + re;
	}

	return re;
}
/**
 * js判断两个时间的差值
 */
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
    var  aDate,  oDate1,  oDate2,  iDays;
    aDate  =  sDate1.split("-"); 
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]); //转换为12-18-2006格式  
    aDate  =  sDate2.split("-");  
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);  
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24); //把相差的毫秒数转换为天数  
    return  iDays;  
}   
/**
 * js获取当前时间
 */
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
/**
 * 整体的对字段以及图片，站点电表的信息判断
 */
function YZCheck() {
	var abc = 1;
	
	var a = document.getElementById("form-field-3").value; 
	var b = document.getElementById("form-field-2").value; 
	
	var c3 = a*1 - b*1;   // a*1将自动转化为数字类型
	// 判断不能为空
	if (document.getElementById('form-field-select-3').value == '') {
		alert('请选择抄表站点！');
		// 提示后返回焦点
		document.getElementById('form-field-select-3').focus();
		return false;
	} else if (document.getElementById('form-field-select-1').value == '') {
		alert('请选择抄表电表！');
		// 提示后返回焦点
		document.getElementById('form-field-select-1').focus();
		return false;
	} else if (document.getElementById('form-field-1').value == '') {
		alert('上次抄表时间不能为空！');
		// 提示后返回焦点
		document.getElementById('form-field-1').focus();
		return false;
	} else if (document.getElementById('form-field-2').value == '') {
		alert('上次抄表读数不能为空！');
		// 提示后返回焦点
		document.getElementById('form-field-2').focus();
		return false;
	} else if (document.getElementById('form-field-3').value == '') {
		alert('本次抄表读数不能为空！');
		// 提示后返回焦点
		document.getElementById('form-field-3').focus();
		return false;
	}else if(c3<0){
		alert('本次抄表读数不能小于上期抄表读数！');
		// 提示后返回焦点
		document.getElementById('form-field-3').focus();
		return false;
	}  else if (document.getElementById('form-field-5').value == '') {
		alert('电表串号不能为空！');
		// 提示后返回焦点
		document.getElementById('form-field-5').focus();
		return false;
	}  else {
		return abc;
	}
}
