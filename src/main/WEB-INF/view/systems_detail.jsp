<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<title>Systems Detail</title>
</head>
<style type="text/css"> @import "http://www.cosmos.com/css/cosmos.css";</style>
</head>
<script type="text/javascript" src="http://www.cosmos.com/js/wz_jsgraphics.js"></script>
<script src="http://www.cosmos.com/js/jquery-1.3.2.js" type="text/javascript"></script>


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
<h1><%=request.getParameter("systemId")%> System</h1>

<script>
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

var starColor = {};
starColor["blue.gi.i"]=new starDim("blue",20);
starColor["blue.gi.ii"]=new starDim("blue",17);
starColor["blue.sg.i"]=new starDim("blue",15);
starColor["blue.sg.ii"]=new starDim("blue",13);
starColor["blue.subgi"]=new starDim("blue",11);
starColor["blue.mains"]=new starDim("blue",10);
starColor["blue.dwarf"]=new starDim("blue",8);

starColor["ltbl.gi.i"]=new starDim("lightblue",20);
starColor["ltbl.gi.ii"]=new starDim("lightblue",17);
starColor["ltbl.sg.i"]=new starDim("lightblue",15);
starColor["ltbl.sg.ii"]=new starDim("lightblue",13);
starColor["ltbl.subgi"]=new starDim("lightblue",11);
starColor["ltbl.mains"]=new starDim("lightblue",10);
starColor["ltbl.dwarf"]=new starDim("lightblue",8);

starColor["orng.gi.i"]=new starDim("orange",20);
starColor["orng.gi.ii"]=new starDim("orange",17);
starColor["orng.sg.i"]=new starDim("orange",15);
starColor["orng.sg.ii"]=new starDim("orange",13);
starColor["orng.subgi"]=new starDim("orange",11);
starColor["orng.mains"]=new starDim("orange",10);
starColor["orng.dwarf"]=new starDim("orange",8);

starColor["pyel.gi.i"]=new starDim("paleyellow",20);
starColor["pyel.gi.ii"]=new starDim("paleyellow",17);
starColor["pyel.sg.i"]=new starDim("paleyellow",15);
starColor["pyel.sg.ii"]=new starDim("paleyellow",13);
starColor["pyel.subgi"]=new starDim("paleyellow",11);
starColor["pyel.mains"]=new starDim("paleyellow",10);
starColor["pyel.dwarf"]=new starDim("paleyellow",8);

starColor["red_.gi.i"]=new starDim("red",20);
starColor["red_.gi.ii"]=new starDim("red",17);
starColor["red_.sg.i"]=new starDim("red",15);
starColor["red_.sg.ii"]=new starDim("red",13);
starColor["red_.subgi"]=new starDim("red",11);
starColor["red_.mains"]=new starDim("red",10);
starColor["red_.dwarf"]=new starDim("red",8);

starColor["whit.gi.i"]=new starDim("white",20);
starColor["whit.gi.ii"]=new starDim("white",17);
starColor["whit.sg.i"]=new starDim("white",15);
starColor["whit.sg.ii"]=new starDim("white",13);
starColor["whit.subgi"]=new starDim("white",11);
starColor["whit.mains"]=new starDim("white",10);
starColor["whit.dwarf"]=new starDim("white",8);

starColor["yelo.gi.i"]=new starDim("yellow",20);
starColor["yelo.gi.ii"]=new starDim("yellow",17);
starColor["yelo.sg.i"]=new starDim("yellow",15);
starColor["yelo.sg.ii"]=new starDim("yellow",13);
starColor["yelo.subgi"]=new starDim("yellow",11);
starColor["yelo.mains"]=new starDim("yellow",10);
starColor["yelo.dwarf"]=new starDim("yellow",8);

function pageDim(px, py){
	this.px=function(){return px;}
	this.py=function(){return py;}
	}


function drawCluster(){
	var x=0
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
		trailing_x += x+501+400+clusterArray[idex].px();
	}
	else{
		trailing_x += x+501+400;
	}
}

</script>

<div id="cluster-map">
</div>

<div id="star-systems-gap">
&nbsp;
</div>

<div id="clusters">

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
	System Id:
	<%=_clrep.getSystemId()%>
	<br/>
	Distance Centre:
	<%=_clrep.getDistanceSysVirtCentre()%>
	<br/>
	Cluster Description:(
	<%=_clrep.getClusterDescription()%>
	)
	<br/>
	Cluster Id:	
	<%=_clrep.getClusterId()%>
	<br/>
	Planets Allowed:
	<%=_clrep.getPlanetsAllowed()%>
	<br/>
	Angle in Radians:	
	<%=_clrep.getAngleInRadians()%>
	<br/>
	Number Stars In Cluster:	
	<%=_clrep.getNumberStarsInCluster()%>
	<br/><br/>
	
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
drawCluster();
resetCluster();

resetClusterPlusStars();
$("#star-systems-gap").css("padding-top", trailing_x+"px");

//-->
</script> 

</div>

<br/>
<br/>
<div id="stars">
<%
while(_star_iter.hasNext()){
cosmos.hibernate.StarRep _strep = ((cosmos.hibernate.StarRep)_star_iter.next());
%>
System Id:<%=_strep.getSystemId()%>
<br/>
Distance to cluster Centre:<%=_strep.getDistanceClustVirtCentre()%>
<br/>
Cluster Id:<%=_strep.getClusterId()%>
<br/>
Star Type:<%=_strep.getStarType()%>
<br/>
Star Size:<%=_strep.getStarSize()%>
<br/>
Star Luminosity:<%=_strep.getLuminosity()%>
<br/>
Star cannot have planets:<%=_strep.getNoPlanetsAllowed()%>
<br/>
Angle in radians in Cluster:<%=_strep.getAngleInRadiansS()%>
<br/>
Star Color:<%=_strep.getStarColor()%>
</div>
<div id="planetoid">
<br/>
<br/>
planetoids
<br/>
<br/>
<%
}

while(_planetoid_iter.hasNext()){
com.zenred.servlet.PlanetoidPlusAtmosphere _pltpla = ((com.zenred.servlet.PlanetoidPlusAtmosphere)_planetoid_iter.next());
%>
<%=_pltpla.getPlanetoid_rep().getPlanetoidId()%>
<br/>
<%
	java.util.List<cosmos.hibernate.Planetoid> _planetoid_rep_list = (java.util.List<cosmos.hibernate.Planetoid>)_pltpla.getPlanetoid();
	java.util.List<cosmos.hibernate.PlanetoidAtmosphereRep> _planetoidatmos_list = (java.util.List<cosmos.hibernate.PlanetoidAtmosphereRep>)_pltpla.getPlanetoid_atmosphere_rep();
	java.util.Iterator _planetoid_rep_iter = _planetoid_rep_list.iterator();
	java.util.Iterator _planetoidatmos_iter = _planetoidatmos_list.iterator();
	while(_planetoid_rep_iter.hasNext()){
		cosmos.hibernate.Planetoid _planetoid = ((cosmos.hibernate.Planetoid)_planetoid_rep_iter.next());
%>
		<%=_planetoid.getPlanetoidId()%>
		<br/>
		<%=_planetoid.getRadius()%>
		<br/>
		<%=_planetoid.getDistanceToPimary()%>
		<br/>
		<%=_planetoid.getDegree()%>
		<br/>
		<%=_planetoid.getTemperature()%>
		<br/>
		<%=_planetoid.getPercentWater()%>
		<br/>
		<br/>
<%
	}
	while(_planetoidatmos_iter.hasNext()){
		cosmos.hibernate.PlanetoidAtmosphereRep _planetoid_atmos_rep = ((cosmos.hibernate.PlanetoidAtmosphereRep)_planetoidatmos_iter.next());
%>
		<%=_planetoid_atmos_rep.getChem_name()%>
		<br/>
		<%=_planetoid_atmos_rep.getPercentage()%>
		<br/>
<%
	}	
%>
<br/>
<%		
}
%>
</div>
<br/>
<br/>
<a href="<c:url value="./index.htm"></c:url>">
return to systems</a>
<br/>

</body>
</html>