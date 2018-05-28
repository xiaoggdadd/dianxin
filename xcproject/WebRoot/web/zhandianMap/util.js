var publicPath = getPublicPath();
function getPublicPath(){
   var path = document.location.pathname;
	var arraypath=path.split("/");
	var mapath="/";
 	if(arraypath[1]==undefined){
	 arraypath[1]="";
	 mapath="";
 	}
  path=mapath+arraypath[1];
  return path;
}
