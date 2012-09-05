<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<title>Systems Detail</title>
<style type="text/css"> @import "http://www.cosmos.com/css/cosmos.css";</style>
</head>
<script type="text/javascript" src="http://www.cosmos.com/js/wz_jsgraphics.js"></script>
<script src="http://www.cosmos.com/js/jquery-1.3.2.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Stars.js" type="text/javascript"></script>

<%
java.util.List<cosmos.hibernate.ClusterRep> _cluster_list = (java.util.List<cosmos.hibernate.ClusterRep>)request.getAttribute("cluster_list");
java.util.List<cosmos.hibernate.StarRep> _star_list = (java.util.List<cosmos.hibernate.StarRep>)request.getAttribute("star_list");
java.util.List<com.zenred.servlet.PlanetoidPlusAtmosphere> _planetoid_list = (java.util.List<com.zenred.servlet.PlanetoidPlusAtmosphere>)request.getAttribute("planetoid_list");
java.util.List<com.zenred.visualization.ClusterPlusSomeDetails> _cluster_plus_details_list = (java.util.List<com.zenred.visualization.ClusterPlusSomeDetails>)request.getAttribute("cluster_plus_list");

java.util.Iterator _cluster_iter = _cluster_list.iterator();
java.util.Iterator _star_iter = _star_list.iterator();
java.util.Iterator _planetoid_iter = _planetoid_list.iterator();
java.util.Iterator _cluster_plus_list_iter = _cluster_plus_details_list.iterator();
%>
<body>

<div id="site">
<h1><%=request.getParameter("systemId")%> System</h1>

<span>
<a href="<c:url value="./index.htm"></c:url>">
return to systems</a>
</span>

<script>
var cnv = document.getElementById("star-map");
var jg = new jsGraphics(cnv);
var trailing_x = 0;
var cl_index = 0;
var clusterColors = ["#DAA520","#BA55D3","#FF6347","#7FFF00","#70DBDB"];
var clusterArray = new Array();

var cps_cluster_index = 0;
var cps_star_index = 0;
var clusterPlusStars = new Array();
var stars = new Array();

function resetClusterPlusStars(){
	cps_cluster_index = 0;
	cps_star_index = 0;
	clusterPlusStars = new Array();
	stars = new Array();
}

function resetCluster(){
	clusterArray = new Array();
	cl_index = 0;
}

function starDim(color, arcSize){
	this.color=function(){return color;}
	this.arcSize=function(){return arcSize;}
}

function oneCluster(distance_to_cl_centre, angle, starArray){
	this.distance_to_cl_centre=function(){return distance_to_cl_centre;}
	this.angle=function(){return angle;}
	this.starArray=function(){return starArray;}
}


function pageDim(px, py){
	this.px=function(){return px;}
	this.py=function(){return py;}
	}


function drawCluster(){
	var x=120
	var y=100;
	
	jg.setColor("#000080");
	jg.fillRect(x, y, 1000, 800);
	var count = 0;
	
	for (idex in clusterArray){
		var px = parseInt(x)+parseInt(clusterArray[idex].px());
		var py = parseInt(y)+parseInt(clusterArray[idex].py());
		var xdim = px+400;
		var ydim = py+400;
		var xdim2 = px;
		var ydim2 = py+300;
		if(ydim < 100){ ydim = 100;}
		if(ydim > 820){ ydim = 820;}
		if(ydim2 < 120){ ydim2 = 120+count;}
		if(ydim2 > 400){ ydim2 = 400-count;}
		if(xdim2 < 5){ xdim2 = 5+count;}
		if(xdim2 > 600){ xdim2 = 600-count;}
		count+=5;

		jg.setColor("#ADD8E6");
		jg.drawString('<div id="clusterspot"'+xdim2+':'+ydim2+'>', xdim2, ydim2);
		jg.drawString('<span  style="color:'+clusterColors[idex]+'"> cluster.'+xdim+'.'+ydim+'</span>', xdim2, ydim2);		jg.drawString('</div>', xdim2, ydim2);
		jg.setColor(clusterColors[idex]);
		jg.fillArc(xdim, ydim, 10,10,0,360);
	}
	jg.paint();
	if (typeof idex  != "undefined") {
		trailing_x += x+501+200+clusterArray[idex].px();
	}
	else{
		trailing_x += x+501+200;
	}
}

</script>

<div id="cluster-map">
</div>

<div id="star-systems-gap">
&nbsp;
</div>



<script type="text/javascript">
<!--

var cnv = document.getElementById("cluster-map");
var jg = new jsGraphics(cnv);
//-->
</script> 
<%
while(_cluster_iter.hasNext()){
cosmos.hibernate.ClusterRep _clrep = ((cosmos.hibernate.ClusterRep)_cluster_iter.next());
%>
	
<script type="text/javascript">
<!--
clusterArray[cl_index++] = new pageDim(<%=_clrep.scaledX2()%>, <%=_clrep.scaledY2()%>);
//-->
</script>

<%
}
%>

<%
while(_cluster_plus_list_iter.hasNext()){
com.zenred.visualization.ClusterPlusSomeDetails _cpsd = ((com.zenred.visualization.ClusterPlusSomeDetails)_cluster_plus_list_iter.next());
	java.util.List<cosmos.hibernate.StarRep> _sr_list = _cpsd.getStarRepList();
	java.util.Iterator _sr_list_iter = _sr_list.iterator();
	while( _sr_list_iter.hasNext()){
		cosmos.hibernate.StarRep _star_rep = (cosmos.hibernate.StarRep)_sr_list_iter.next();
		System.out.println("starep:"+_star_rep.getStarColor());
	}
}
%>
<script type="text/javascript">
<!--

jg.paint();
drawCluster();
resetCluster();

resetClusterPlusStars();
$("#star-systems-gap").css("padding-top", trailing_x+"px");

//-->
</script> 
<script type="text/javascript">

function listProperties(obj) {
	   var propList = "";
	   for(var propName in obj) {
	      if(typeof(obj[propName]) != "undefined") {
	         propList += (propName  +", ");
	      }
	   }
	   alert(propList);
	}

$(document).ready(function() {		
	$('#stars').hide();
	loadClusters();
	$('#stars').show('slow');
});

var clusterColors2 = ['#DAA520','#BA55D3','#FF6347','#7FFF00','#70DBDB'];

function loadClusters() {
    $.getJSON("/cluster.htm", 
     function(json){           // callback
        var a_cluster = '';
        var idex = 0;
	var clusterID = new Array();
	var a_stars ='';

        $(json.list.cluster).each(function() {
	a_cluster = ''

	if(typeof this.clusterId  != "undefined"){
	
            a_cluster += '<div ><span ><a id="cid'+idex + '" href=/cluster_detail.htm?cluster='  + this.clusterId +' style="color:'+clusterColors2[idex]+'">'+ this.clusterId +'</a>' +'<span></div><br/>';
            a_cluster += '<div> Cluster Description: '+this.clusterDescription + '</div>';
            a_cluster += '<div> Kilometric Distance to Centre of the Sector: ' + this.distanceSysVirtCentre + '</div>';
            a_cluster += '<div> Angle From Sector Centre: ' + this.angleDegrees + '</div>' ;
            a_cluster += '<div> Are Planets Allowed: ' + this.planetsAllowed + '</div>';
            a_cluster += '<div> Number of Stars in this Cluster: ' + this.numberStarsInCluster + '</div><br/><br/>';
	    clusterID[idex] = this.clusterId;
	}
	else{
		a_cluster += '<div> No Stars in this System </div>';
	}
	if(0 == idex){
	        $("div[id^=clustersx0]").html(a_cluster);
	}
 	if(1 == idex){
	        $("div[id^=clustersx1]").html(a_cluster);
	}
 	if(2 == idex){
	        $("div[id^=clustersx2]").html(a_cluster);
	}
 	if(3 == idex){
	        $("div[id^=clustersx3]").html(a_cluster);
	}
 	if(4 == idex){
	        $("div[id^=clustersx4]").html(a_cluster);
	}

       ++idex;
	

       });
	$("div[id^=clustersx0]").bind("click", function(event) {
		$.getJSON('/cluster_detail.htm',{'cluster': clusterID[0]}, function(response){
			a_stars = '<div>';
			var idex2 = 0;
			var starArray = new Array();
			$(response.list.stars).each(function(){
				starArray[idex2] = this.starColor;
				a_stars += loadStars(this, 0, clusterID[0], idex2++);
			});
			vizualizeStars(a_stars+'</div>', starArray, 0);
			$('#stars').show('slow');
		});
 	       return false;
	});
	$("div[id^=clustersx1]").bind("click", function(event) {
		$.getJSON('/cluster_detail.htm',{'cluster': clusterID[1]}, function(response){
			a_stars = '<div>';
			var idex2 = 0;
			var starArray = new Array();
			$(response.list.stars).each(function(){
				starArray[idex2] = this.starColor;
				a_stars += loadStars(this, 1, clusterID[1], idex2++);
			});
			vizualizeStars(a_stars+'</div>', starArray, 1);
			$('#stars').show('slow');
		});
 	       return false;
	});
	$("div[id^=clustersx2]").bind("click", function(event) {
		$.getJSON('/cluster_detail.htm',{'cluster': clusterID[2]}, function(response){
			a_stars = '<div>';
			var idex2 = 0;
			var starArray = new Array();
			$(response.list.stars).each(function(){
				starArray[idex2] = this.starColor;
				a_stars +=loadStars(this, 2, clusterID[2], idex2++);
			});
			vizualizeStars(a_stars+'</div>', starArray, 2);
			$('#stars').show('slow');
		});
 	       return false;
	});
	$("div[id^=clustersx3]").bind("click", function(event) {
		$.getJSON('/cluster_detail.htm',{'cluster': clusterID[3]}, function(response){
			a_stars = '<div>';
			var idex2 = 0;
			var starArray = new Array();
			$(response.list.stars).each(function(){
				starArray[idex2] = this.starColor;
				a_stars += loadStars(this, 3, clusterID[3], idex2++);
			});
			vizualizeStars(a_stars+'</div>', starArray, 3);
			$('#stars').show('slow');

		});
 	       return false;
	});
	$("div[id^=clustersx4]").bind("click", function(event) {
		$.getJSON('/cluster_detail.htm',{'cluster': clusterID[4]}, function(response){
			a_stars = '<div>';
			var idex2 = 0;
			var starArray = new Array();
			$(response.list.stars).each(function(){
				starArray[idex2] = this.starColor;
				a_stars += loadStars(this, 4, clusterID[4], idex2++);
			});
			vizualizeStars(a_stars+'</div>', starArray, 4);
			$('#stars').show('slow');

		});
 	       return false;
	});
	
    }
)};

function loadStars(star, clusterNumber, clusterId, starNumber){
	if(typeof star.starColor  != "undefined"){
	
	var a_star = '<div ><span ><a id="sid'+starNumber + '" href=/star_detail.htm?cluster='  + clusterId 
	+'&starNumber='+star.star_id
	+'&starColor='+star.starColor
	+' style="color:'+clusterColors2[clusterNumber]+'">'+ 'Cluster Star ' + starNumber +'</a>' +'<span></div><br/><br/><br/>';
	a_star+= '<div> Star Color: '+star.starColor + '</div>';
	a_star+= '<div> Star Luminosity: '+star.luminosity + '</div>';
	a_star+= '<div> Star Angle In Degrees to Cluster Centre: '+star.angleInDegreesS + '</div>';
	a_star+= '<div> Star Size in Solar Units: '+star.starSize + '</div>';
	a_star+= '<div> Star Type: '+star.starType + '</div>';
//	a_star+= '<div> Possible Planets? : '+star.noPlanetsAllowed + '</div>';
//	alert(a_star);
	}
	else{
		a_star = '<div> cluster without a star </div>';
	}
	return a_star;
}

function vizualizeStars(a_stars, color, dim){
	$("div[id^=stars]").empty();
	$("div[id^=stars]").html(a_stars);
	// jg.clear();

	var xdim = 1000+(dim*100);
	var ydim = 600+(dim*100);
	for (var idex = 0; idex < color.length; idex += 1) {
		//alert(starColor[color[idex]].color());
		
		var stardim = starAttributes.getStarColor(10, color[idex]);
		jg.setColor(stardim.color());
		// jg.setColor(starColor[color[idex]].color());
		jg.fillArc(ydim, xdim, stardim.arcSize(), stardim.arcSize(), 0, 360);
		xdim += stardim.arcSize()*2;
		
		
	}
	jg.paint();
}
</script> 

<div id="clustersx0"> 
</div>
<div id="clustersx1">
</div>
<div id="clustersx2">
</div>
<div id="clustersx3">
</div>
<div id="clustersx4">
</div>

<div id="stars">
</div>




</div>   
</body>
</html>