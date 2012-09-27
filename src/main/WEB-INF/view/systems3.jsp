<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<html>
<head><title>Cosmos Star Systems</title></head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<style type="text/css"> @import "http://www.cosmos.com/css/cosmos.css";</style>
</head>
<script type="text/javascript" src="http://www.cosmos.com/js/wz_jsgraphics.js"></script>
<script src="http://www.cosmos.com/js/jquery-1.6.min.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Stars.js" type="text/javascript"></script>
<body>

<div id="site">
<h1>Cosmos Star Systems</h1>

<script>

var systemPlusModule = (function () {
	// private
	
	var systems = [];
	var clusters = new Object();
	var stars = new Object();
	var numClusters = 0;
	var numStars = 0;
	
	// public 
	
	return {
	
	    addSystem: function( system ) {
      		systems.push(system);
    	},
    	
    	fetchSystem: function(){
    		return systems.shift();
    	},
    	
    	getSystems: function(index){
    		return systems[index];
    	},
    	
    	numberSystems: function(){
    		return systems.length;
    	},
    	
    	addCluster: function(key, cluster) {
    		var a_key = key;
    		clusters[a_key] = cluster;
    		++numClusters;
    	},
    	
    	getCluster: function(key){
    	    if (clusters.hasOwnProperty(key)) {
 		   		return clusters[key];
    		}
    	},

    	numberClusters: function(){
    		return numClusters;
    	},
    	
    	addStar: function(key, star){
    		var a_key = key;
    		stars[a_key] = star;
    		++numStars;
    	},
    	
    	getStar: function(key){
    	    if (stars.hasOwnProperty(key)) {
 		   		return stars[key];
    		}
    	},
    	
    	numberStars: function(){
    		return numStars;
    	}
	};
}());

var pageSpace = (function () {

	// private
	var min_u = Number.MAX_VALUE;
	var min_v = Number.MAX_VALUE;
	var current_u;
	var current_v;
	var current_height;
	var current_width;
	var numberStarsystems_x;
	var numberStarsystems_y;
	
	// public
	
	return {
		testSmallestU: function(u_dim){
			min_u = Math.min(u_dim, min_u);
			current_u = min_u;
		},
		testSmallestV: function(v_dim){
			min_v = Math.min(v_dim, min_v);
			current_v = min_v;
		},
		smallestU: function(){
			return min_u;
		},
		smallestV: function(){
			return min_v;
		},
		currentU: function(){
			return current_u;
		},
		currentV: function(){
			return current_v;
		},
		incrementCurrentU: function(){
			++current_u;
		},
		incrementCurrentV: function(){
			++current_v;
		},
		decrementCurrentU: function(){
			--current_u;
		},
		decrementCurrentV: function(){
			--current_v;
		},
		heightWidth: function(height, width){
			current_height = height;
			current_width = width;
		},
		numberStarsSystemsX: function(){
//			console.log("XX:"+scalingConstants.starSystemPageSpaceX());
			numberStarsystems_x = current_width / scalingConstants.starSystemPageSpaceX();
		},
		numberStarsSystemsY: function(){
			numberStarsystems_y = current_width / scalingConstants.starSystemPageSpaceY();
		},
		getNumberStarSystemsX: function(){
			return parseInt(numberStarsystems_x);
		},
		getNumberStarSystemsY: function(){
			return parseInt(numberStarsystems_y);
		}
		
	};
	
}());

var scalingConstants = (function(){
	// private
	var mainY = 160;
	var mainX = 149;
	var marginY = 20;
	var xdimOffset = 2;
	var ydimOffset = 1;

	
	function getmainY () {
		return mainY;
	}
	function getmainX () {
		return mainX;
	}
	function getmarginY () {
		return marginY;
	}
	function getxdimOffset() {
		return xdimOffset;
	}
	function getydimOffset() {
		return ydimOffset;
	}
	// public
	return {
		starSystemPageSpaceX: function getX(){
			return getmainX() + getxdimOffset();
		},
		starSystemPageSpaceY: function getY(){
			return getmainY() + getydimOffset() + getmarginY();
		}
	};
}());

var drawSystems = (function(){
	// private
	function isSystemOnPage(systemObject){
		var uDelta = pageSpace.currentU() + pageSpace.getNumberStarSystemsX();
		var vDelta = pageSpace.currentV() + pageSpace.getNumberStarSystemsY();
//		console.log("uDelta vDelta:" + pageSpace.currentU() + "::" + pageSpace.currentV() + pageSpace.numberStarsSystemsX() + "::" + pageSpace.numberStarsSystemsY());
		if(systemObject.ucoord >= pageSpace.currentU() && systemObject.ucoord <= uDelta 
			&&
			systemObject.vcoord >= pageSpace.currentV() && systemObject.vcoord <= vDelta){
				console.log("vcoord, ucoord in page:" + systemObject.vcoord + "::" + systemObject.ucoord);
				return true;
			}
		else{
				console.log("vcoord, ucoord not in page:" + systemObject.vcoord+ "::" + systemObject.ucoord);
				return false;
		}
	}
	
	// public
	return {
		scanSystems: function scan(){
			for (var index = 0; index < systemPlusModule.numberSystems(); index++ ){
				var systemObject = systemPlusModule.getSystems(index);
				var isItInPage = isSystemOnPage(systemObject);
			}
		}
	};

}());

</script> 

<c:forEach var="systemPlusSomeDetails" items="${systems_list}" >
<script>
	systemPlusModule.addSystem({
								ucoord: ${systemPlusSomeDetails._ucoordinate},
								vcoord: ${systemPlusSomeDetails._vcoordinate},
								systemId: "${systemPlusSomeDetails._systemId}",
								galacticCentre: ${systemPlusSomeDetails._distanceToGalaxyCentre}
								});
	pageSpace.testSmallestU(${systemPlusSomeDetails._ucoordinate});
	pageSpace.testSmallestV(${systemPlusSomeDetails._vcoordinate});								
</script>
	<c:forEach var="clusterRepList" items="${systemPlusSomeDetails.clusterRepList}" >
<script>
	systemPlusModule.addCluster("${systemPlusSomeDetails._systemId}",{
								distVirtCentre: ${clusterRepList._distanceSysVirtCentre},
								angle: ${clusterRepList._angleInRadians},
								planetsAllowed: "${clusterRepList._planetsAllowed}",
								clusterId: "${clusterRepList._clusterId}",
								description: "${clusterRepList._clusterDescription}",
								numberStars: ${clusterRepList._numberStarsInCluster}
								});								
</script>	
	</c:forEach>
	<c:forEach var="starRepList" items="${systemPlusSomeDetails.starRepList}" >
<script>
	systemPlusModule.addStar("${systemPlusSomeDetails._systemId}",{
		starId: ${starRepList.starId},
		clusterId: "${starRepList.clusterId}", 
		distClusterVirtCentre: ${starRepList.distanceClustVirtCentre},
		luminosity: ${starRepList.luminosity},
		noPlanets: "${starRepList.noPlanetsAllowed}",
		angle: ${starRepList.angleInRadiansS},
		starColor: "${starRepList.starColor}",
		starType: "${starRepList.starType}",
		starSize: "${starRepList.starSize}"
	});
	console.log("star:" + systemPlusModule.getStar("${systemPlusSomeDetails._systemId}").starColor);
</script>		
	</c:forEach>
	
</c:forEach>
<script>
	console.log("number Clusters:" + systemPlusModule.numberClusters());
	console.log("number Stars:" + systemPlusModule.numberStars());
	console.log("smallest u:" + pageSpace.smallestU());
	console.log("smallest v:" + pageSpace.smallestV());
	
	$(document).ready(function() {
		pageSpace.heightWidth($(document).height(), $(document).width());
		pageSpace.numberStarsSystemsX();
		pageSpace.numberStarsSystemsY();
		console.log("number star systems x:" + pageSpace.getNumberStarSystemsX());
		console.log("number star systems y:" + pageSpace.getNumberStarSystemsY());
		drawSystems.scanSystems();
	});
	
</script>
</body>
</html>