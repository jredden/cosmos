<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<html>
<head><title>Cosmos Star Systems</title></head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<style type="text/css"> @import "http://www.cosmos.com/css/cosmos.css";</style>
</head>
<script type="text/javascript" src="http://www.cosmos.com/js/wz_jsgraphics3.js"></script>
<script src="http://www.cosmos.com/js/jquery-1.6.min.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Stars.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Clusters.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/RequestVariables.js" type="text/javascript"></script>
<body>
<div id="container">
<section>
<div id="site">
<h1>Cosmos Star Systems</h1>
<nav>
  <button id="pozV">Positive Shift V Dimension</button>
  <button id="negV">Negative Shift V Dimension</button>
  <button id="pozU">Positive Shift U Dimension</button>
  <button id="negU">Negative Shift U Dimension</button>
</nav>

<script>
			const SYSTEM_ID = 0;
			const CLUSTER_DESCRIPTION = 1;
			const DISTANCE_VIRTUAL_CENTRE = 2;
			const NUMBER_STARS_IN_CLUSTER = 3;
			const PLANETS_ALLOWED = 4;
			const ANGLE_IN_RADIANS = 5;
			
			const STAR_ID = 0;
			const PARENT_SYSTEM_ID = 1;
			const CLUSTER_ID = 2;
			const DISTANCE_TO_CLUSTER_VIRT_CENTRE = 3;
			const LUMINOSITY_ID = 4;
			const NO_PLANETS_ALLOWED = 5;
			const STAR_ANGLE_IN_RADIANS = 6;
			const STAR_COLOR = 7;
			const STAR_TYPE = 8;
			const STAR_SIZE = 9;

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
    	getClusterDistances: function(key){
    		var distances = new Array();
    	    if (clusters.hasOwnProperty(key)) {
 		   		for(var idex = 0; idex < clusters[key].length; idex++){
 		   			distances.push(clusters[key][idex].distVirtCentre);
 		   		}
    		}
    		return distances;
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
    	},
    	isSystemInCache: function(id){
    		var answer = false;
    		for(scounter in systems){
    			if(systems[scounter].systemId = id){
    				answer == true;
    				break;
    			}
    		}
    		return answer;
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
	
	var uextent;
	var vextent
	
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
		},
		setU: function(u_val){
			current_u = u_val;
		},
		setV: function(v_val){
			current_v = v_val;
		},
		setUextent: function(u_x){
			uextent = parseInt(u_x);
		},
		setVextent: function(v_x){
			vextent = parseInt(v_x);
		},
		getUextent: function(){
			return uextent;
		},
		getVextent: function(){
			return vextent;
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
	var clusterScale = 10;
	var clusterSize = 5;
	var clusterScaleX = 75;
	var clusterScaleY = 75;
		
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
	function getclustscale(){
		return clusterScale;
	}
	function getclustsize(){
		return clusterSize;
	}
	function getclusterscaleX(){
		return clusterScaleX;
	}
	function getclusterscaleY(){
		return clusterScaleY;
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
		},
		getClusterScale: function scale(){
			return getclustscale();
		},
		getClusterSize: function size(){
			return getclustsize();
		},
		getClusterScaleX: function cscalex(){
			return getclusterscaleX();
		},
		getClusterScaleY: function cscaley(){
			return getclusterscaleY();
		}
	};
}());

var drawSystems = (function(){

	// private
	
	var flipFlop = 0;
	
	function isSystemOnPage(systemObject){
		var uDelta = pageSpace.currentU() + pageSpace.getNumberStarSystemsX();
		var vDelta = pageSpace.currentV() + pageSpace.getNumberStarSystemsY();
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
		jsGraphics.fillRect(xdim, ydim, scalingConstants.getSubMainX(), scalingConstants.getSubScale());
		var yy = ydim + scalingConstants.getSubScale();
		jsGraphics.fillRect(xdim, yy, scalingConstants.getSubMainX(), scalingConstants.getSubMainY());
		console.log("xdim:" + xdim + " ydim:" + ydim + " udelta:" + udelta + " vdelta:" + vdelta + " systemId:" + dims.systemId + " SubMainX:" + scalingConstants.getSubMainX() + " getSubScale:" + scalingConstants.getSubScale() + " yy: " + yy + " getSubMainY:" + scalingConstants.getSubMainY());
		jsGraphics.setColor("#ADD8E6");
		jsGraphics.drawString('<a href="./systems_detail_cluster2.htm?systemId=U'+ucoord+'V'+vcoord
			+'&startu='+pageSpace.currentU() 
			+'&startv='+pageSpace.currentV() 
			+ '">'+""+ucoord+":"+vcoord+"</a>", xdim, yy);
		
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
			
		var clusterArray = systemPlusModule.getCluster(dims.systemId);
		var clusterApi = new clusterDrawAPI();
		for (ccounter in clusterArray){
			clusterApi.cons(jsGraphics, xdim+scalingConstants.getClusterScaleX(), ydim+scalingConstants.getClusterScaleY(), clusterArray[ccounter].distVirtCentre, scalingConstants.getClusterScale(), scalingConstants.getClusterSize(), clusterArray[ccounter].angle);
			clusterAttributes.drawOneCluster(clusterApi, ccounter, clusterAttributes.largest(systemPlusModule.getClusterDistances(dims.systemId)));
		}
		
		
		jsGraphics.paint();
		$(dims.systemId).show();
	}
	
	// public
	
	return {
		drawOneSystem: function draw1(systemObject){
			drawSystem(systemObject, systemObject.ucoord, systemObject.vcoord);
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
		},
		scanVisualSystems: function(){
			pageSpace.graphicsInvisible();
			flipFlop = 0;
			for (var index = 0; index < systemPlusModule.numberSystems(); index++ ){
				var systemObject = systemPlusModule.getSystems(index);
				var extentU = pageSpace.currentU() + pageSpace.getUextent();
				var extentV = pageSpace.currentV() + pageSpace.getVextent();
				if(systemObject.ucoord >= pageSpace.currentU() && systemObject.ucoord <= extentU
					&& systemObject.vcoord >= pageSpace.currentV() && systemObject.vcoord  <= extentV){
					drawSystem(systemObject, systemObject.ucoord, systemObject.vcoord);
				}
			}
		}
	};

}());

</script> 
</div>  <!-- site -->
</section>
<section>
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
	
				
		var lastU = $.getUrlVar('startu');
		var lastV = $.getUrlVar('startv');
		var uextent = $.getUrlVar('uextent');
		var vextent = $.getUrlVar('vextent');
		
		if(uextent == undefined){
			uextent = localStorage.uextent;
		}
		else{
			localStorage.uextent = uextent;
		}
		if(vextent == undefined){
			vextent = localStorage.vextent;
		}
		else{
			localStorage.vextent = vextent;
		}
		
		pageSpace.setUextent(uextent);
		pageSpace.setVextent(vextent);
		
		if(lastU != undefined){
			pageSpace.setU(lastU);
		}
		if(lastV != undefined){
			pageSpace.setV(lastV);
		}
	
		$(document).dblclick(function(event){
		
		
			console.log("pageX:"+event.pageX +" pageY:"+event.pageY);
			var currentU = parseInt(pageSpace.currentU());
			var currentV = parseInt(pageSpace.currentV());
			var pageX = event.pageX;
			var pageY = event.pageY;
			var ucoord = Math.floor(currentU + ((pageX - scalingConstants.getXConstant()) / scalingConstants.starSystemPageSpaceX()));
			var vcoord = Math.floor(currentV + ((pageY - scalingConstants.getYConstant()) / scalingConstants.starSystemPageSpaceX()));
			
			console.log("newU:"+ ucoord +" newV:"+ vcoord + " currentU:" + currentU + " currentV:" + currentV + " scaleX:" + scalingConstants.starSystemPageSpaceX() + " scaleY:" + scalingConstants.starSystemPageSpaceY() );
			alert('ucoord:'+ucoord + ': vcoord:' + vcoord);
			$.getJSON("/generate_system2.htm?udim="+ucoord+"&vdim="+vcoord+"&systemId=U"+ucoord+"V"+vcoord,
			function(json){           // callback
				console.log("json0:" + json.someDetails.theMessage);
				console.log("json1:" + json.someDetails._systemId);
				console.log("json2:" + json.someDetails._distanceToGalaxyCentre);
				console.log("json3:" + json.someDetails._ucoordinate);
				console.log("json4:" + json.someDetails._vcoordinate);
				console.log("json5:" + json.someDetails.clusterRepList);
				console.log("json6:" + json.someDetails.starRepList);
				
				// cannot re-create a system already 
				if(json.someDetails.theMessage.indexOf("already exists") != -1){
					alert(json.someDetails.theMessage);
					return;
				}
				systemPlusModule.addSystem({
							ucoord: json.someDetails._ucoordinate,
							vcoord: json.someDetails._vcoordinate,
							systemId: json.someDetails._systemId,
							galacticCentre: json.someDetails._distanceToGalaxyCentre
							});
				$(json.someDetails.clusterRepList).each(function() {
					$(this.list).each(function(){
						console.log("cluster:" + this.string);
						var clusterArray = (""+this.string).split(',');
						systemPlusModule.addCluster(json.someDetails._systemId,{
							distVirtCentre: clusterArray[DISTANCE_VIRTUAL_CENTRE],
							angle: this.double,
							planetsAllowed: clusterArray[PLANETS_ALLOWED],
							clusterId: clusterArray[SYSTEM_ID],
							description: clusterArray[CLUSTER_DESCRIPTION],
							numberStars: clusterArray[NUMBER_STARS_IN_CLUSTER],
							});			
					});
				});
				$(json.someDetails.starRepList).each(function() {
					$(this.list).each(function(){
						console.log("star:" + this.string);
						var starArray = (""+this.string).split(',');
							systemPlusModule.addStar(json.someDetails._systemId,{
								starId: starArray[STAR_ID],
								clusterId: starArray[CLUSTER_ID], 
								distClusterVirtCentre: starArray[DISTANCE_TO_CLUSTER_VIRT_CENTRE],
								luminosity: starArray[LUMINOSITY_ID],
								noPlanets: starArray[NO_PLANETS_ALLOWED],
								angle: starArray[STAR_ANGLE_IN_RADIANS],
								starColor: starArray[STAR_COLOR],
								starType: starArray[STAR_TYPE],
								starSize: starArray[STAR_SIZE]
				});
						
					});
				});
				
				var systemObject = systemPlusModule.getSystems(systemPlusModule.numberSystems()-1);
				// last system just added
				pageSpace.addGraphic(json.someDetails._systemId);
				drawSystems.drawOneSystem(systemObject);
			}
			);		
		});
		$("#pozV").click(function () { 
       		pageSpace.incrementCurrentV();
       		var curV = pageSpace.currentV() + pageSpace.getVextent();
       		for(var count = 0; count <= pageSpace.getUextent(); count++){
     			var curU = parseInt(pageSpace.currentU()) + count;
       			console.log("curU:" + curU + " curV:" + curV);
       			addToCache.jsonCall(curU, curV);
       		}
       		drawSystems.scanVisualSystems();
 	    });
    	$("#negV").click(function () { 
       		pageSpace.decrementCurrentV();
       		for(var count = 0;  count <= pageSpace.getUextent();count++){
     			var curU = parseInt(pageSpace.currentU()) + count;
       			console.log("curU:" + curU + " currentV:" + pageSpace.currentV());
       			addToCache.jsonCall(curU, pageSpace.currentV());
       		}
       		drawSystems.scanVisualSystems();
     	});
    	$("#pozU").click(function () { 
       		pageSpace.incrementCurrentU();
       		var curU = pageSpace.currentU() + pageSpace.getUextent();
       		for(var count = 0; count <= pageSpace.getVextent(); count++ ){
     			var curV = parseInt(pageSpace.currentV()) + count;
       			console.log("curU:" + curU + " curV:" + curV);
       			addToCache.jsonCall(curU, curV);
       		}
       		drawSystems.scanVisualSystems();
    	});
    	$("#negU").click(function () { 
       		pageSpace.decrementCurrentU();
       		for(var count = 0; count <= pageSpace.getVextent(); count++){
       			var curV = parseInt(pageSpace.currentV()) + count;
       			console.log("currentU:" + pageSpace.currentU() + " curV:" + curV);
       			addToCache.jsonCall(pageSpace.currentU(), curV);
       		}
       		drawSystems.scanVisualSystems();
    	});    	
    	
	
		pageSpace.heightWidth($(document).height(), $(document).width());
		pageSpace.numberStarsSystemsX();
		pageSpace.numberStarsSystemsY();
		console.log("number star systems x:" + pageSpace.getNumberStarSystemsX());
		console.log("number star systems y:" + pageSpace.getNumberStarSystemsY());
		console.log("current u:" + pageSpace.currentU());
		console.log("current v:" + pageSpace.currentV());
		drawSystems.scanSystems();
		console.log("number graphic contexts:" + pageSpace.getNumberGraphics());
		
	});


	var addToCache = (function(){
	
	// public
		return{
			jsonCall: function(ucoord, vcoord){
				$.getJSON("/pageSystem.htm?udim="+ucoord+"&vdim="+vcoord,
				function(json){           // callback
					console.log("MESSAGE:"+json.someDetails.theMessage);
					if(json.someDetails.theMessage.indexOf("cons") != -1){
						addToCache.aSystem(json);
					return;
					}
				
				});
			},
			aSystem: function(json){
				console.log("json0:" + json.someDetails.theMessage);
				console.log("json1:" + json.someDetails._systemId);
				console.log("json2:" + json.someDetails._distanceToGalaxyCentre);
				console.log("json3:" + json.someDetails._ucoordinate);
				console.log("json4:" + json.someDetails._vcoordinate);
				console.log("json5:" + json.someDetails.clusterRepList);
				console.log("json6:" + json.someDetails.starRepList);
				
				if(systemPlusModule.isSystemInCache(json.someDetails._systemId)){
					//  done, it's already in the cache
					console.log("System " + json.someDetails._systemId + " is already in the cache");
					return;
				}
				
				systemPlusModule.addSystem({
							ucoord: json.someDetails._ucoordinate,
							vcoord: json.someDetails._vcoordinate,
							systemId: json.someDetails._systemId,
							galacticCentre: json.someDetails._distanceToGalaxyCentre
							});
				pageSpace.addGraphic(json.someDetails._systemId);	
				
				$(json.someDetails.clusterRepList).each(function() {
					$(this.list).each(function(){
						console.log("cluster:" + this.string);
						var clusterArray = (""+this.string).split(',');
						systemPlusModule.addCluster(json.someDetails._systemId,{
							distVirtCentre: clusterArray[DISTANCE_VIRTUAL_CENTRE],
							angle: this.double,
							planetsAllowed: clusterArray[PLANETS_ALLOWED],
							clusterId: clusterArray[SYSTEM_ID],
							description: clusterArray[CLUSTER_DESCRIPTION],
							numberStars: clusterArray[NUMBER_STARS_IN_CLUSTER],
							});			
					});
				});
				$(json.someDetails.starRepList).each(function() {
					$(this.list).each(function(){
						console.log("star:" + this.string);
						var starArray = (""+this.string).split(',');
							systemPlusModule.addStar(json.someDetails._systemId,{
								starId: starArray[STAR_ID],
								clusterId: starArray[CLUSTER_ID], 
								distClusterVirtCentre: starArray[DISTANCE_TO_CLUSTER_VIRT_CENTRE],
								luminosity: starArray[LUMINOSITY_ID],
								noPlanets: starArray[NO_PLANETS_ALLOWED],
								angle: starArray[STAR_ANGLE_IN_RADIANS],
								starColor: starArray[STAR_COLOR],
								starType: starArray[STAR_TYPE],
								starSize: starArray[STAR_SIZE]
								});
					});
				});
				var systemObject = systemPlusModule.getSystems(systemPlusModule.numberSystems()-1);
				// last system just added
				pageSpace.addGraphic(json.someDetails._systemId);
				drawSystems.drawOneSystem(systemObject);
			}
		};
	}());
	
</script>

</div> <!-- region -->  
</section>
</div>  <!-- container -->
</body>
</html>