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
<div>
<form name="Show">
<span><input type="text" name="MouseX" value="0" size="4"> X</span>
<span><input type="text" name="MouseY" value="0" size="4"> Y</span>
</form>
</div>

<div id="site">
<h1>Cosmos Star Systems</h1>

<script>

$(document).ready(function() {	
	$('#site').dblclick(function(event){
		console.log("pageX:"+event.pageX +" pageY:"+event.pageY);

		var udim = pageSpaceToDomainU(event.pageX);
		var vdim = pageSpaceToDomainV(event.pageY);

		var xudim = pageSpaceToNormalizedU(event.pageX);
		var xvdim = pageSpaceToNormalizedV(event.pageY);

		console.log("xudim:"+xudim +" xvdim:"+xvdim);
		console.log("xupage:"+domainToPageSpaceU(xudim) +" xvpage:"+domainToPageSpaceV(xvdim));

		alert('udim:'+xudim + ': vdim:' + xvdim);
		$.getJSON("/generate_system.htm?udim="+udim+"&vdim="+vdim+"&vdim="+vdim+"&systemId=U"+udim+"V"+vdim,
			function(json){           // callback
				// alert(json.basicMesage.theMessage);
				pageDimArray = new Array();
				starArray = new Array();
				if(undefined != json.basicMesage.scaledX.int && undefined != json.basicMesage.scaledX.int){
					pd_index = 0;
					for(count = 0; count < json.basicMesage.scaledX.int.length; count++){
						pageDimArray[pd_index++] = new pageDim(json.basicMesage.scaledX.int[count], json.basicMesage.scaledY.int[count]);
					}

					sd_index = 0;
					for(count = 0; count < json.basicMesage.starColors.string.length; count++){
						starArray[sd_index++] = 
						starAttributes.getStarColor(1, json.basicMesage.starColors.string[count]);
					}
				}
				drawSystem(udim, vdim);
				pageDimArray = new Array();
				starArray = new Array();
				pd_index = 0;
				sd_index = 0;
			}
		);
		return false;
		}	
	);
});

var scalingConstants = (function(){
	var mainY = 160;
	var mainX = 149;
	var marginY = 20;
	var sectorConstantX = 100000-10;
	var sectorConstantY = 100000-10;
	var xdimOffset = 2;
	var ydimOffset = 1;

	function setsectorConstantX(sector_Constant){
		sectorConstantX = sector_Constant;
	}
	function setsectorConstantY(sector_Constant){
		sectorConstantY = sector_Constant;
	}
	function getsectorConstantX () {
		return sectorConstantX;
	}
	function getsectorConstantY () {
		return sectorConstantY;
	}
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
	return {
	        setSectorConstantX: function(sector_Constant){setsectorConstantX(sector_Constant);},
	        getSectorConstantX: getsectorConstantX,
	        setSectorConstantY: function(sector_Constant){setsectorConstantY(sector_Constant);},
	        getSectorConstantY: getsectorConstantY,
		getMainX: getmainX,
		getMainY: getmainY,
		getMarginY: getmarginY,
		getXDimOffset: getxdimOffset,
		getYDimOffset: getydimOffset
	};
}());

function pageSpaceToNormalizedU(xdim_page){
	var xdim = (xdim_page /scalingConstants.getMainX()) -1 + scalingConstants.getSectorConstantX();
	return Math.round(xdim);
}

function pageSpaceToNormalizedV(ydim_page){
	var ydim = (ydim_page - scalingConstants.getMainY() - 
			scalingConstants.getMarginY())/	(scalingConstants.getMainY() + scalingConstants.getMarginY())
			+scalingConstants.getSectorConstantY();
	return Math.round(ydim);
}

function pageSpaceToDomainU(xdim_page){
	var udim = (xdim_page / scalingConstants.getMainX())
	+scalingConstants.getSectorConstantX();
	udim += scalingConstants.getXDimOffset();
	return Math.round(udim);
}

function pageSpaceToDomainV(ydim_page){
	var vdim = (ydim_page / (scalingConstants.getMainY() 
		+ scalingConstants.getMarginY()))+scalingConstants.getSectorConstantY();
	vdim += scalingConstants.getYDimOffset();
	return Math.round(vdim);
}

function domainToPageSpaceU(udim_domain){
	return scalingConstants.getMainX() * (udim_domain + 1 - scalingConstants.getSectorConstantX());
}

function domainToPageSpaceV(vdim_domain){
	return (scalingConstants.getMainY() + scalingConstants.getMarginY()) * (vdim_domain - 					scalingConstants.getSectorConstantX()) 	+ 
				scalingConstants.getMainY() + scalingConstants.getMarginY();
}


</script>

<br/>
<script>

var xor = 0;
var pageDimArray = [];

var pd_index = 0;
var sd_index = 0;
var clusterColors = ["#DAA520","#BA55D3","#FF6347","#7FFF00","#70DBDB", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF"];

function starDim(color, arcSize){
this.color=function(){return color;}
this.arcSize=function(){return arcSize;}
}

function pageDim(px, py){
this.px=function(){return px;}
this.py=function(){return py;}
}

var starArray = new Array();
function drawSystem(u,v){
	
	var x=(u-scalingConstants.getSectorConstantX())*150;
	var y=(v-scalingConstants.getSectorConstantY())*180;
	x-=425;
	y-=480;

	xor^=1;
	if(xor == 1){
		jg.setColor("#000080"); 
	}
	else{
		jg.setColor("#0000CD"); 
	}
	// alert('x:'+x+' y:' +y);
	jg.fillRect(x, y, 149, 10);
	var yy = y + 10;
	jg.fillRect(x, yy, 149, 149);
	jg.paint();

	jg.setColor("#ADD8E6");
	jg.drawString('<a href="./systems_detail_cluster.htm?systemId=U'+u+'V'+v+'">'+""+u+":"+v+"</a>", x, y);
	for (idex in pageDimArray){
		console.log("idex:"+idex+":"+clusterColors[idex]+":"+pageDimArray.length+" x:"+x+" y:"+y + " yy:"+yy);
		if(idex > 4){break;}
		jg.setColor(clusterColors[idex]);
		jg.fillArc(x+74+pageDimArray[idex].px(), yy+74+pageDimArray[idex].py(),10,10,0,360);
	}

	var yyy = yy +150;
	jg.setColor("midnightblue");
	jg.fillRect(x, yyy, 149, 20);
	for (scounter in starArray){
		console.log("scounter:"+scounter+"::"+scounter.length+ " yyy:"+yyy);
		if(typeof starArray[scounter] == "undefined"){
		// alert(':'+starArray[scounter]+':'+scounter+':'+typeof(starArray[scounter]+':'));
			jg.setColor("gray");
			jg.fillArc(x, yyy, 15, 15,0,360);
			x+=17;
		}
		else{	
		// alert(':'+starArray[scounter].color()+':'+scounter+':'+starArray[scounter].arcSize()+':');
		var scolor = starArray[scounter].color();
	
		jg.setColor(scolor);
		jg.fillArc(x, yyy, starArray[scounter].arcSize(), starArray[scounter].arcSize(),0,360);
		x+=starArray[scounter].arcSize();
//		jg.paint();	

//		jg.setColor("orange");
//		jg.fillArc(x, yyy, 15, 15,0,360);
//		x+=15;

		x+=2;
		}
		
	}
	jg.paint();

}

var cosmosExtent = (function(){
	var maxu;
	var maxv;
	var minu;
	var minv;
	return {
		init : function (udim, vdim){ 
				maxu = udim; 
				maxv = vdim;
				minu = udim; 
				minv = vdim;
		},

		compare : function (udim, vdim){
			if(udim > maxu){maxu = udim;}
			if(vdim > maxv){maxv = vdim;}
			if(udim < minu){minu = udim;}
			if(vdim < minv){minv = vdim;}
		},
	maxdimu : function(){return maxu;},
	maxdimv : function(){return maxv;},
	mindimu : function(){return maxu;},
	mindimv : function(){return maxv;}
	};
})();



</script>
<div id="star-map">
</div>
<div id="star-systems-gap">
&nbsp;
<c:forEach var="systemPlusSomeDetails" items="${systems_list}" > 
  <br/>${systemPlusSomeDetails._systemId}
	<br/>Ucoord&nbsp;${systemPlusSomeDetails._ucoordinate}
	<br/>Vcoord&nbsp;${systemPlusSomeDetails._vcoordinate}
<script type="text/javascript">
<!--
console.log('PageU ' + domainToPageSpaceU(${systemPlusSomeDetails._ucoordinate}));
console.log('PageV ' + domainToPageSpaceV(${systemPlusSomeDetails._vcoordinate}));
console.log('DomainU ' + pageSpaceToDomainU(domainToPageSpaceU(${systemPlusSomeDetails._ucoordinate})));
console.log('DomainV ' + pageSpaceToDomainV(domainToPageSpaceV(${systemPlusSomeDetails._vcoordinate})));
-->
</script>
	<c:set var="_rep" value="${systemPlusSomeDetails._systemId}" />
	<c:forEach var="clusterRepList" items="${systemPlusSomeDetails.clusterRepList}" >
		<br/>scaledX&nbsp;${clusterRepList.scaledX}
		<br/>scaledY&nbsp;${clusterRepList.scaledY}
<script type="text/javascript">
<!--
var onetime = 0;
		if(onetime == 0){
			cosmosExtent.init(${systemPlusSomeDetails._ucoordinate}, ${systemPlusSomeDetails._vcoordinate});
			onetime = 1;
		}
		else{
			cosmosExtent.compare(${systemPlusSomeDetails._ucoordinate}, ${systemPlusSomeDetails._vcoordinate});
		}
		pageDimArray[pd_index++] = new pageDim(${clusterRepList.scaledX}, ${clusterRepList.scaledY});
-->
</script>
	</c:forEach>
	<c:forEach var="starRepList" items="${systemPlusSomeDetails.starRepList}" >
		${starRepList.systemId}&nbsp;${starRepList.starColor}
	</c:forEach>
</c:forEach>
</div>
<%
java.util.List<com.zenred.visualization.SystemPlusSomeDetails> _list = (java.util.List<com.zenred.visualization.SystemPlusSomeDetails>)request.getAttribute("systems_list");
java.util.Iterator _iter = _list.iterator();
while(_iter.hasNext()){
com.zenred.visualization.SystemPlusSomeDetails SystemPlusSomeDetails = (com.zenred.visualization.SystemPlusSomeDetails)_iter.next();
String _rep = SystemPlusSomeDetails.get_systemId();
%>


<!-- iteration through star rep -->
<%
java.util.List<cosmos.hibernate.StarRep> starRepList0 = SystemPlusSomeDetails.getStarRepList();
java.util.Iterator<cosmos.hibernate.StarRep> sr_iter0 = starRepList0.iterator();
while(sr_iter0.hasNext()){
cosmos.hibernate.StarRep starRep = sr_iter0.next();
%>

<script type="text/javascript">
<!-- 
var stardim = starAttributes.getStarColor(1, '<%=starRep.getStarColor()%>');
starArray[sd_index++] = stardim;
//-->
</script>
<%
}
%>


<div id="star-systems">

<script type="text/javascript">
<!--

pd_index = 0;
sd_index = 0;
var cnv = document.getElementById("star-map");
var jg = new jsGraphics(cnv);

drawSystem(<%=SystemPlusSomeDetails.get_ucoordinate()%>, <%=SystemPlusSomeDetails.get_vcoordinate()%> );
pd_index = 0;
sd_index = 0;
pageDimArray = [];
starArray = new Array();
//-->

document.captureEvents(Event.MOUSEMOVE);
// Set-up to use getMouseXY function onMouseMove
document.onmousemove = getMouseXY;
// Temporary variables to hold mouse x-y pos.s
var tempX = 0;
var tempY = 0;

// Main function to retrieve mouse x-y pos.s
function getMouseXY(event) {
    tempX = event.pageX;
    tempY = event.pageY;
  document.Show.MouseX.value = tempX
  document.Show.MouseY.value = tempY
  return true
}


</script> 

<table>
<br/>
<tr>
<a href="./systems_detail_cluster.htm?systemId=${_rep}">
System Name:&nbsp;${_rep}
</a>
</tr>
<br/>
<br/>
<tr>
Distance to Galactic Centre in Parsecs: <%=SystemPlusSomeDetails.get_distanceToGalaxyCentre()%>
</tr>
U Dimension:<%=SystemPlusSomeDetails.get_ucoordinate()%> 
<tr>
V Dimension:<%=SystemPlusSomeDetails.get_vcoordinate()%> 
</tr></table><br/><br/></div>
<br/>
<%
java.util.List<cosmos.hibernate.ClusterRep> clusterRepList = SystemPlusSomeDetails.getClusterRepList();
java.util.Iterator<cosmos.hibernate.ClusterRep> cr_iter = clusterRepList.iterator();
while(cr_iter.hasNext()){
cosmos.hibernate.ClusterRep clusterRep = cr_iter.next();
%>
<div id="clusters"><table><tr>
Cluster Name:<%=clusterRep.getClusterId()%>
</tr>
<tr>
Cluster Description:<%=clusterRep.getClusterDescription()%>
</tr>
<tr>
Number Stars in Cluster:<%=clusterRep.getNumberStarsInCluster()%>
</tr>
<tr>
Are Planets Possible:<%=clusterRep.getPlanetsAllowed()%>
</tr>
<tr>
page space x:<%=clusterRep.scaledX()%>
</tr>
<tr>
page space y:<%=clusterRep.scaledY()%>
</tr>

<br/>
</tr></table><br/><br/></div>

<%
}
}
%>

<br/>
</div>


</body>
</html>