<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<title>Systems Detail</title>
<style type="text/css"> @import "http://www.cosmos.com/css/cosmos.css";</style>
</head>
<script type="text/javascript" src="http://www.cosmos.com/js/wz_jsgraphics.js"></script>
<script src="http://www.cosmos.com/js/jquery-1.3.2.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Stars.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Clusters.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/OneSystem.js" type="text/javascript"></script>


<div id="container">
<div id="cluster">

<script>
	$(document).ready(function() {
		var uvDim = "${systemId}";
		var uvArray = uvDim.split('V');
		var udim = uvArray[0].slice(1, uvArray[0].length);
		var vdim = uvArray[1];
		console.log("uvDim:" + udim + ':' + vdim);
		var xdim = $('#cluster').position().left;
		var ydim = $('#cluster').position().top;
		console.log("xyDim:" + xdim + ':' + ydim);
		$.getJSON("/oneCluster.htm?udim="+udim+"&vdim="+vdim,
		function(json){
				console.log("json0:" + json.someDetails.theMessage);
				console.log("json1:" + json.someDetails._systemId);
				console.log("json2:" + json.someDetails._distanceToGalaxyCentre);
				console.log("json3:" + json.someDetails._ucoordinate);
				console.log("json4:" + json.someDetails._vcoordinate);
				console.log("json5:" + json.someDetails.clusterRepList);
				console.log("json6:" + json.someDetails.starRepList);
				
				$(json.someDetails.clusterRepList).each(function() {
					var idex = 0; 
					var jsGraphicsCluster = new jsGraphics("cluster"); 
					var distArray = new Array();
					$(this.list).each(function(){
						console.log("cluster:" + this.string);
						console.log("geomety:" + $("#cluster").width() + "::" + $("#cluster").height());
						var clusterArray = (""+this.string).split(',');
						
						var distVirtCentre = clusterArray[decodeSystem.distanceVirtualCentre()];
						distArray[idex] = distVirtCentre;
						var angle =  this.double;
						var planetsAllowed = clusterArray[decodeSystem.planetsAllowed()];
						var clusterId = clusterArray[decodeSystem.systemId()];
						var description = clusterArray[decodeSystem.clusterDescription()];
						var numberStars = clusterArray[decodeSystem.numberStarsInCluster()];
						
						var nindex = idex % 4;
						scale = 100;
						size = 25;
						var clusterApi = new clusterDrawAPI();
						clusterApi.cons(jsGraphicsCluster, $("#cluster").width() / 2, $("#cluster").height() / 2, distVirtCentre, scale, size, angle);
						clusterAttributes.drawOneCluster(clusterApi, nindex, clusterAttributes.largest(distArray));
						++idex;		
					});
				});
		
		});
		
		
	});

</script>

</div> 	 <!-- cluster -->
</div>   <!-- container -->
</body>
</html>