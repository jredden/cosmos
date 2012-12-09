<%@ page import="java.net.URLEncoder" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %><html>
<head>
<title>Planet Detail</title>
<style type="text/css"> @import "http://www.cosmos.com/css/cosmos.css";</style>
</head>
<script type="text/javascript" src="http://www.cosmos.com/js/wz_jsgraphics.js"></script>
<script src="http://www.cosmos.com/js/jquery-1.3.2.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Stars.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Atmosphere.js" type="text/javascript"></script>
<body>



<div id="site">
<c:out value="${param.starColor}"/>
<c:set var="cluster" value="${param.cluster}" />
<span ><a href="<c:url value="./star_detail.htm?cluster=${cluster}&starColor=${param.starColor}&starNumber=${param.starNumber}"></c:url>" id="planet">
return to star detail</a></span>

<h1>${requestScope.planetName} Planet ${requestScope.planetNumber}</h1>

<script type="text/javascript">

$(document).ready(function() {		
	$('#planet').hide();
	loadPlanet();
	$('#planet').show('slow');
});

function loadPlanet(){
	$.getJSON("/planet_detail_builder.htm?planetName=${requestScope.planetName}&planetNumber=${requestScope.planetNumber}&starId=${requestScope.starId}", 
     function(json){ 
	
	var i_distance = parseInt(json.planetDetail.distance+.5 * json.planetDetail.starSize);
	var stardim = starAttributes.getStarColor(i_distance, json.planetDetail.starColor);
	//alert(stardim.color() + ':' + stardim.arcSize());
	drawStar(stardim.arcSize(), stardim.color());
	
	// only use the first two atmosphere colors for now
	var colors = [];
	var percentages = [];

	for(var a_count = 0; a_count < json.planetDetail.chemName.string.length ; a_count++){
		colors[a_count] = json.planetDetail.chemName.string[a_count];
		// alert(colors[a_count]);
	}
	drawPlanet(colors, json.planetDetail.radius);
	for(var a_count = 0; a_count < json.planetDetail.percentage.string.length ; a_count++){
		percentages[a_count] = json.planetDetail.percentage.string[a_count];
		// alert(percentages[a_count]);
	}
	drawAtmosphere(colors, percentages);
     });
}

function drawStar(arcSize, color){
	if(10 > arcSize){arcSize = randomFromTo(10, 15);} 
	var cnv = document.getElementById("planet");
	var jg = new jsGraphics(cnv);
	jg.setColor("#000080");
	jg.fillRect(20, 120, 1200, 1500);
	jg.paint();
	jg.setColor(color);
	jg.fillArc(500, 300, arcSize, arcSize, 0, 360);
	jg.paint();
}

function drawPlanet(colors, radius){
	var cnv = document.getElementById("planet");
	var jg = new jsGraphics(cnv);
	if(0 == radius){radius = randomFromTo(1000, 5000);} // kludge ... fix later
	if(1000 > radius){radius = randomFromTo(1000, 5000);} // kludge ... fix later
	var arcSize = parseInt(radius) /200;
	var jg = new jsGraphics(cnv);
	var color;
	if(colors.length > 1){
		color = atmosphereAttributes.getAtmosphereColor(colors[0]);
		jg.setColor(color);
		jg.fillArc(500, 500, arcSize, arcSize, 0, 180);
		color = atmosphereAttributes.getAtmosphereColor(colors[1]);
		jg.setColor(color);
		jg.fillArc(500, 500, arcSize, arcSize, 180, 0);
	}
	else{
		color = atmosphereAttributes.getAtmosphereColor(colors[0]);
		jg.setColor(color);
		jg.fillArc(500, 500, arcSize, arcSize, 0, 360);
	}
	jg.paint();
}

function drawAtmosphere(colors, atmospheres){
	var cnv = document.getElementById("atmosphere");
	var jg = new jsGraphics(cnv);
	var xdim = 1000;
	var ydim = 300;
	for(var count = 0; count < atmospheres.length ; count++){
		color = atmosphereAttributes.getAtmosphereColor(colors[count]);
		jg.setColor(color);
		jg.fillRect(xdim, ydim, 160, 70);
		jg.setColor("yellow");
		jg.setFont("arial","15px",Font.ITALIC_BOLD);
		jg.drawString( " " + colors[count] + " " + atmospheres[count]+ " % ", xdim-200, ydim);
		ydim+= 90;
	}
	jg.paint();
}

function randomFromTo(from, to){
       return Math.floor(Math.random() * (to - from + 1) + from);
}

</script>

<div id="planet">
</div>

<div id="atmosphere">
</div>

</div>   
</body>
</html>