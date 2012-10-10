<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<html>
<head><title>Cosmos Star Systems</title></head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<style type="text/css"> @import "http://www.cosmos.com/css/cosmos.css";</style>
</head>
<script type="text/javascript" src="http://www.cosmos.com/js/wz_jsgraphics3.js"></script>
<script src="http://www.cosmos.com/js/jquery-1.6.min.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Stars.js" type="text/javascript"></script>
<body>

<div id="site">
<h1>Cosmos Star Systems</h1>

  <button id="pozV">Positive Shift V Dimension</button>
  <button id="negV">Negative Shift V Dimension</button>
  <button id="pozU">Positive Shift U Dimension</button>
  <button id="negU">Negative Shift U Dimension</button>


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
    		if (clusters.hasOwnProperty(key)) {
    			var clusterArray = clusters[key];
    			clusterArray.push(cluster);
    		}
    		else{
    			var clusterArray = new Array();
    			clusterArray.push(cluster);
    			clusters[key] = clusterArray;
    		
    		}
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
    		if (stars.hasOwnProperty(key)) {
    			var starArray = stars[key];
    			starArray.push(star);
    		}
    		else{
    			var starArray = new Array();
    			starArray.push(star);
    			stars[key] = starArray;
    		}
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
	
	var graphics = new Object();
	var numGraphics = 0;
	
	// public
	
	return {
		addGraphic: function(dims){
			$('<div id="' + dims +'"></div>').appendTo('body');
			graphics[dims] = new jsGraphics(dims);
			++numGraphics;
			$(dims).hide();
		},
		graphicsInvisible: function(){
			for(var dims in graphics) {
				$(dims).hide();
				graphics[dims].clear();
			}
		},
		getGraphic: function(dims){
			return graphics[dims];
		},
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
			numberStarsystems_y = current_height / scalingConstants.starSystemPageSpaceY();
		},
		getNumberStarSystemsX: function(){
			return parseInt(numberStarsystems_x);
		},
		getNumberStarSystemsY: function(){
			return parseInt(numberStarsystems_y);
		},
		getNumberGraphics: function(){
			return parseInt(numGraphics);
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
	var subScale = 10;
	var yConstant = 150;
	var xConstant = 40;
	
	
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
	function getsubscale(){
		return subScale;
	}
	function getyconstant(){
		return yConstant;
	}
	function getxconstant(){
		return xConstant;
	}
	
	// public
	
	return {
		starSystemPageSpaceX: function getX(){
			return getmainX() + getxdimOffset();
		},
		starSystemPageSpaceY: function getY(){
			return getmainY() + getydimOffset();
		},
		getSubScale: function subScale(){
			return getsubscale();
		},
		getSubMainY: function subMainY(){
			return getmainY() - getmarginY() - getydimOffset();
		},
		getSubMainX: function mainX(){
			return getmainX();
		},
		getMarginY: function marginY(){
			return getmarginY();
		},
		getYConstant: function yconst(){
			return getyconstant();
		},
		getXConstant: function xconst(){
			return getxconstant();
		}
	};
}());

var drawSystems = (function(){

	// private
	
	var flipFlop = 0;
	
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
	
	function drawSystem(dims, ucoord, vcoord){
		var jsGraphics = pageSpace.getGraphic(dims.systemId);
		flipFlop^=1;
		if(flipFlop == 1){
			jsGraphics.setColor("#000080"); 
		}
		else{
			jsGraphics.setColor("#0000CD"); 
		}
		var currentU = parseInt(pageSpace.currentU());
		var currentV = parseInt(pageSpace.currentV());
		var udelta = ucoord - currentU;
		var vdelta = vcoord - currentV;
		var xdim = udelta * scalingConstants.starSystemPageSpaceX() + scalingConstants.getXConstant();
		var ydim = vdelta * scalingConstants.starSystemPageSpaceY() + scalingConstants.getYConstant();
		console.log("xdim:" + xdim + " ydim:" + ydim + " udelta:" + udelta + " vdelta:" + vdelta + " systemId:" + dims.systemId);
		jsGraphics.fillRect(xdim, ydim, scalingConstants.getSubMainX(), scalingConstants.getSubScale());
		var yy = ydim + scalingConstants.getSubScale();
		jsGraphics.fillRect(xdim, yy, scalingConstants.getSubMainX(), scalingConstants.getSubMainY());
		jsGraphics.setColor("#ADD8E6");
		jsGraphics.drawString('<a href="./systems_detail_cluster.htm?systemId=U'+ucoord+'V'+vcoord+'">'+""+ucoord+":"+vcoord+"</a>", xdim, yy);
		
		var yyy = ydim + scalingConstants.getSubMainY();
		jsGraphics.setColor("midnightblue");
		jsGraphics.fillRect(xdim, yyy, scalingConstants.getSubMainX(), scalingConstants.getMarginY());
		
		var starArray = systemPlusModule.getStar(dims.systemId);
		var starXDim = xdim;
		for (scounter in starArray){
			var scolor = starArray[scounter].starColor;
			var starDim = starAttributes.getStarColor(1, scolor);
			jsGraphics.setColor(starDim.color());
			jsGraphics.fillArc(starXDim, yyy, starDim.arcSize(), starDim.arcSize(),0,360);
			starXDim += starDim.arcSize();
		}
		
		jsGraphics.paint();
		$(dims.systemId).show();
	}
	
	// public
	
	return {
		drawOneSystem: function draw1(systemObject){
			drawSystem(systemObject.systemId, systemObject.ucoord, systemObject.vcoord);
		},
		scanSystems: function scan(){
			pageSpace.graphicsInvisible();
			flipFlop = 0;
			for (var index = 0; index < systemPlusModule.numberSystems(); index++ ){
				var systemObject = systemPlusModule.getSystems(index);
				var isItInPage = isSystemOnPage(systemObject);
				if(isItInPage){
					drawSystem(systemObject, systemObject.ucoord, systemObject.vcoord);
				}
			}
		}
	};

}());

</script> 
<div id="region">
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
	pageSpace.addGraphic("${systemPlusSomeDetails._systemId}");							
</script>
	<c:forEach var="clusterRepList" items="${systemPlusSomeDetails.clusterRepList}" >
<script>
	systemPlusModule.addCluster("${systemPlusSomeDetails._systemId}",{
								distVirtCentre: ${clusterRepList._distanceSysVirtCentre},
								angle: ${clusterRepList._angleInRadians},
								planetsAllowed: "${clusterRepList._planetsAllowed}",
								clusterId: "${clusterRepList._clusterId}",
								description: "${clusterRepList._clusterDescription}",
								numberStars: ${clusterRepList._numberStarsInCluster},
								scaledX: ${clusterRepList.scaledX},
								scaledY: ${clusterRepList.scaledY}
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
	console.log("star:" + systemPlusModule.getStar("${systemPlusSomeDetails._systemId}")[0].starColor);
</script>		
	</c:forEach>
	
</c:forEach>
<script>
	console.log("number Clusters:" + systemPlusModule.numberClusters());
	console.log("number Stars:" + systemPlusModule.numberStars());
	console.log("smallest u:" + pageSpace.smallestU());
	console.log("smallest v:" + pageSpace.smallestV());
	
	$(document).ready(function() {
		$(document).dblclick(function(event){
			console.log("pageX:"+event.pageX +" pageY:"+event.pageY);
			var currentU = parseInt(pageSpace.currentU());
			var currentV = parseInt(pageSpace.currentV());
			var pageX = event.pageX;
			var pageY = event.pageY;
			var ucoord = currentU + pageX / (scalingConstants.starSystemPageSpaceX());
			var vcoord = currentV + pageY / (scalingConstants.starSystemPageSpaceY());
			console.log("newU:"+ ucoord +" newV:"+ vcoord + " currentU:" + currentU + " currentV:" + currentV + " scaleX:" + scalingConstants.starSystemPageSpaceX() + " scaleY:" + scalingConstants.starSystemPageSpaceY() );
		});
		$("#pozV").click(function () { 
       		pageSpace.incrementCurrentV();
       		drawSystems.scanSystems();
	    });
    	$("#negV").click(function () { 
       		pageSpace.decrementCurrentV();
       		drawSystems.scanSystems();
    	});
    	$("#pozU").click(function () { 
       		pageSpace.incrementCurrentU();
       		drawSystems.scanSystems();
    	});
    	$("#negU").click(function () { 
       		pageSpace.decrementCurrentU();
       		drawSystems.scanSystems();
    	});    	
    	
	
		pageSpace.heightWidth($(document).height(), $(document).width());
		pageSpace.numberStarsSystemsX();
		pageSpace.numberStarsSystemsY();
		console.log("number star systems x:" + pageSpace.getNumberStarSystemsX());
		console.log("number star systems y:" + pageSpace.getNumberStarSystemsY());
		drawSystems.scanSystems();
		console.log("number graphic contexts:" + pageSpace.getNumberGraphics());
		
	});


	
</script>

</div> <!-- region -->  
</div>  <!-- site -->
</body>
</html>