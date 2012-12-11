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
<script src="http://www.cosmos.com/js/RequestVariables.js" type="text/javascript"></script>
<body>



<div id="container">

<h1><script>$.getUrlVar('planetName')</script> Planet <script>$.getUrlVar('planetNumber')</script></h1>

<button id="returnSystems">Return to Cosmos Clusters</button>

<div id="planets">

<script type="text/javascript">

$(document).ready(function() {	
	returnParams.setSystemId($.getUrlVar('systemId'));
	returnParams.setStartU($.getUrlVar('startu'));	
	returnParams.setStartV($.getUrlVar('startv'));	
	returnParams.setCluster($.getUrlVar('cluster'));	
	returnParams.setStarNumber($.getUrlVar('starNumber'));	
	returnParams.setStarColor($.getUrlVar('starColor'));	
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

var returnParams = (function () {
	// private
	var startu;
	var startv;
	var cluster;
	var starNumber;
	var starColor;
	var systemId;
	
	return{
		setSystemId: function(s_systemId){
			systemId = s_systemId;
		},
		setStartU: function(s_startu){
			startu = s_startu;
		},
		setStartV: function(s_startv){
			startv = s_startv;
		},
		setCluster: function(s_cluster){
			cluster = s_cluster;
		},
		setStarNumber: function(s_starNumber){
			starNumber = s_starNumber;
		},
		setStarColor: function(s_starColor){
			starColor = s_starColor;
		},
		getSystemId: function(){
			return systemId;
		},
		getStartU: function(){
			return startu;
		},
		getStartV: function(){
			return startv;
		},
		getCluster: function(){
			return cluster;
		},
		getStarNumber: function(){
			return starNumber;
		},
		getStarColor: function(){
			return starColor;
		}
	}
	
}());

$("#returnSystems").click(function () { 
 	var lastU = $.getUrlVar('startu');
	var lastV = $.getUrlVar('startv');
 	var url = "http://www.cosmos.com/systems_detail_cluster2.htm?"
 		+"startu="+returnParams.getStartU()
 		+"&startv="+returnParams.getStartV()
 		+"&cluster="+returnParams.getCluster()
 		+"&starNumber="+returnParams.getStarNumber()
		+"&starColor="+returnParams.getStarColor()
		+"&systemId="+returnParams.getSystemId()
 		;    
	$(location).attr('href',url);
});

</script>

<div id="planet">
</div>

<div id="atmosphere">
</div>

</div> 	 <!-- planets -->  
</div>   <!-- container --> 
</body>
</html>