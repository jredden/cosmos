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
<h1>Cosmos Clusters</h1>
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
				var starRepList = json.someDetails.starRepList;
				$(json.someDetails.clusterRepList).each(function() {
					var idex = 0; 
					var jsGraphicsCluster = new jsGraphics("cluster"); 
					var distArray = new Array();
					console.log("geomety:" + $("#cluster").width() + "::" + $("#cluster").height());
					$(this.list).each(function(){
						if(this.string == undefined){
							return false;	// garbage in the array
						}
						var clusterArray = (""+this.string).split(',');
						var distVirtCentre = clusterArray[decodeSystem.distanceVirtualCentre()];
						distArray[idex] = distVirtCentre;  // look for greatest delta first
						++idex;
					});
					idex = 0;
					$(this.list).each(function(){
						
						if(this.string == undefined){
							return false;	// garbage in the array
						}
						var clusterArray = (""+this.string).split(',');
						console.log("cluster:" + this.string + "::" + clusterArray[decodeSystem.systemId()]);
						
						var distVirtCentre = clusterArray[decodeSystem.distanceVirtualCentre()];
						var angle =  this.double;
						var planetsAllowed = clusterArray[decodeSystem.planetsAllowed()];
						var clusterId = clusterArray[decodeSystem.systemId()];
						var description = clusterArray[decodeSystem.clusterDescription()];
						var numberStars = clusterArray[decodeSystem.numberStarsInCluster()];
						
						var nindex = idex % 4;
						scale = 40;
						size = 25;
						var clusterApi = new clusterDrawAPI();
						clusterApi.cons(jsGraphicsCluster, $("#cluster").position().left + $("#cluster").width() / 2, $("#cluster").position().top + $("#cluster").height() / 2, distVirtCentre, scale, size, angle);
						clusterAttributes.addName(clusterApi, nindex, clusterAttributes.largest(distArray), clusterId, idex);
						var clusterColor = clusterAttributes.getClusterColor(nindex);
						var cid = clusterId+'_'+idex;
						DisplayClusterStars.addGraphic(cid);
						$('<div id="' + cid +'"></div>').appendTo('#starcontent');
						$("#"+cid) 
							.append('<input type="button" value=' + cid +'>')
							.click(function(){ 
								var starCount = 0;
								if(starRepList == undefined || starRepList.list == undefined){
									return false;	// garbage in the array
								}
								
								for(;starCount < starRepList.list.length;starCount++) {
									var reflector = new Reflector(starRepList.list);
									console.log("starRepList:"+starRepList.list[starCount]+"::"+reflector.getProperties());
									var starArray = (""+starRepList.list[starCount].string).split(',');
									console.log("starArray.length:"+starArray.length);
									var arrayOfStars = new Array();
										DisplayClusterStars.graphicsInvisible();
									
										var oneStar = new OneStar();
										oneStar.setStarId(starArray[decodeSystem.starId()]);
										oneStar.setParentSystemId(starArray[decodeSystem.parentSystemId()]);
										oneStar.setClusterId(starArray[decodeSystem.clusterId()]);
										oneStar.setDistanceToClusterVirtCentre(starArray[decodeSystem.distanceToClusterVirtCentre()]);
										oneStar.setLuminosity(starArray[decodeSystem.luminosityId()]);
										oneStar.setNoPlanetsAllowed(starArray[decodeSystem.noPlanetsAllowed()]);
										oneStar.setStarAngleInRadians(starArray[decodeSystem.starAngleInRadians()]);
										oneStar.setStarColor(starArray[decodeSystem.starColor()]);
										oneStar.setStarType(starArray[decodeSystem.starType()]);
										oneStar.setStarSize(starArray[decodeSystem.starSize()]);
										arrayOfStars.push(oneStar);
										
										var a_cluster = '';
		            					a_cluster += '<div id="ccopy0"> Cluster Description: '+ description + '</div>';
		            					a_cluster += '<div id="ccopy0"> Kilometric Distance to Centre of the Sector: ' + distVirtCentre + '</div>';
		            					a_cluster += '<div id="ccopy0"> Angle From Sector Centre: ' + angle + '</div>' ;
		            					a_cluster += '<div id="ccopy0"> Are Planets Allowed: ' + planetsAllowed + '</div>';
		            					a_cluster += '<div id="ccopy0"> Number of Stars in this Cluster: ' + numberStars + '</div>';
										DrawStars.drawStarsInCluster(arrayOfStars, 
											DisplayClusterStars.getGraphic(cid), 
											a_cluster,
											$("#starcontent").position().left + $("#starcontent").width() / 4, 
											$("#starcontent").position().top + $("#starcontent").height() / 5,
											10);

								};
								return false;
							}); 
						$(cid).show();
						++idex;		
					});
				});
		
		});
		
		
	});

	var DisplayClusterStars = (function () {
		// private
	
		// refactor, violates D.R.Y. principle
	
		var graphics = new Object();
		var numGraphics = 0;
	
		// public 
		
		return {
			addGraphic: function(dims){
				if(graphics[dims] == undefined){
					$('<div id="' + dims +'"></div>').appendTo('body');
					graphics[dims] = new jsGraphics(dims);
					++numGraphics;
					$(dims).hide();
				}
			},
			graphicsInvisible: function(){
				for(var dims in graphics) {
					$(dims).hide();
					graphics[dims].clear();
				}
			},
			getGraphic: function(dims){
				return graphics[dims];
			}
		}
	}());


var Reflector = function(obj) {
  this.getProperties = function() {
    var properties = [];
    for (var prop in obj) {
      if (typeof obj[prop] != 'function') {
        properties.push(prop);
      }
    }
    return properties;
  };
}
</script>

</div> 	 <!-- cluster -->
<div id="starcontent">
</div>	 <!-- starcontent -->
</div>   <!-- container -->
</body>
</html>