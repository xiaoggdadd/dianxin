var map;
var geocoder;
var markers = [];
var infowindows = [];
$(document).ready(function(){
	/*var myLatlng = new google.maps.LatLng(35.200252,117.887614);
	var myOptions = { 
		zoom:7,    
		center: myLatlng,  
		disableDefaultUI: true,
		mapTypeId: google.maps.MapTypeId.ROADMAP  
	}
	geocoder = new google.maps.Geocoder();
	map = new google.maps.Map($("#map_canvas").get(0), myOptions);*/
	buildMap(35.200252,117.887614,7);
});

    function buildMap(x,y,zoom){
      var myLatlng = new google.maps.LatLng(x,y);
      
	  var myOptions = { 
		zoom:zoom,    
		center: myLatlng,  
		disableDefaultUI: true,
		mapTypeId: google.maps.MapTypeId.ROADMAP 
      }
	 geocoder = new google.maps.Geocoder();
	 map = new google.maps.Map($("#map_canvas").get(0), myOptions);
   }

    //��������
	var preIndex = -1;
	function displayPoint(marker, index){
		map.setCenter(marker.getPosition());
		var latlng = new google.maps.LatLng(marker.getPosition().Ka,marker.getPosition().La);
		     geocoder.geocode({'latLng': latlng}, function(results, status) {
	      if ("OK" == google.maps.GeocoderStatus.OK) {
	          //infowindows[index].setContent(results[1].formatted_address);
	      } else {
	          alert("No results found");
	        }
	    });
		if (preIndex != -1) {
	 	    markers[preIndex].setIcon('../zhandianMap/red.png');
	 	    markers[preIndex].setZIndex(1);
	 	    infowindows[preIndex].close();  
	    }
		preIndex = index;
		marker.setIcon('../zhandianMap/green.png');
		marker.setZIndex(100);
		infowindows[index].open(marker.getMap(),marker);  
	}




