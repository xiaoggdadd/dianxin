<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=request.getContextPath()%>/javascript/LodopFuncs.js"></script>

<object  id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>

<script type="text/javascript">
var LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));

function setColumn(column,style){
	var columns = column;
	if(typeof(column) == "string" && column == "all"){
		var columnNum = $($("#tableInfo table tr")[0]).find("td").size();
		columns = new Array(columnNum);
		
		for(i=0;i<columnNum;i++)
			columns[i] = i;
	}

	var str = "";
	str += style;
	
	var tableInfo = "<table border=1>";
	var tr = $("#tableInfo table tr");
	$.each(tr,function(i){
		var isThead = false;
		if($(this).attr("class") == "thead")
			isThead = true;
			
		if(isThead)
			tableInfo += "<thead><tr style='font-size:16px;font-weight:900'>";
		else
			tableInfo += "<tr>";
			
		for(i in columns){
			var td = $($(this).find("td")[columns[i]]).text();
			tableInfo += "<td>"+td+"</td>";
		}
		
		if(isThead)
			tableInfo += "</tr></thead>"; 
		else
			tableInfo += "</tr>";
	});
	tableInfo += "</table>";
	
	str += "<body>"+tableInfo+"</body>";
	
	return str;
}

function create(title,postSetColumn){
	LODOP.PRINT_INIT(title);
	setLayout(title,postSetColumn);
//   	LODOP.PRINT_SETUP();
   	LODOP.PREVIEW();
};
</script>
