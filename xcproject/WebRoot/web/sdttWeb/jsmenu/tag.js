// tab


function doClick2(o){
	 o.className="titlec";
	 var k;
	 var id;
	 var e;
	 for(var i=1;i<=6;i++){
	   id ="ee"+i;
	   k = document.getElementById(id);
	   e = document.getElementById("e"+i);
	   if(id != o.id){
	   	 k.className="title3d";
	   	 e.style.display = "none";
	   }else{
			e.style.display = "block";
	   }
	 }
}

