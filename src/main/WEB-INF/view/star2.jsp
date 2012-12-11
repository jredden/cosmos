<%@ page import="java.net.URLEncoder" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="pr" class="com.zenred.visualization.PlanetoidResponse" />
<html>
<head>
<title>Stars Detail</title>
</head>
<style type="text/css"> @import "http://www.cosmos.com/css/cosmos.css";</style>
<script type="text/javascript" src="http://www.cosmos.com/js/wz_jsgraphics.js"></script>
<script src="http://www.cosmos.com/js/jquery-1.3.2.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Stars.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/RequestVariables.js" type="text/javascript"></script>

<button id="returnClusters">Return to Cosmos Clusters</button>

<script>

var ellipsePosition = (function(){
	var xstartPos = 600;
	var ystartPos = 500;
	var aScalar = 50;
	var bScalar = 25;
	var i_distance;
	var x_point;
	var y_point;

	return {
		//public methods

		computeX: function(distance){
			i_distance = parseInt(distance*(aScalar*2));
			x_point = xstartPos + (aScalar - (i_distance/2));
			console.log('x_point:'+x_point+':'+i_distance);
			return x_point;
		},
		computeY: function(distance){
			i_distance = parseInt(distance*(bScalar*2));
			y_point = ystartPos + (bScalar - (i_distance/2));
			console.log('y_point:'+y_point+':'+i_distance);
			return y_point;
		},
		computeA: function(distance){
			console.log('computeA:'+parseInt((distance)*(aScalar*2)));
			return parseInt((distance)*(aScalar*2));
		},
		computeB: function(distance){
			console.log('computeB:'+parseInt((distance)*(bScalar*2)));
			return parseInt((distance)*(bScalar*2));
		},
		xStart: function(){
			return xstartPos+aScalar;
		},
		yStart: function(){
			return ystartPos+bScalar;
		},
		xPos: function(xscalar, distance){
			var xpos = (distance * (aScalar * 2))/2 * xscalar;
			console.log('xposS:' + xpos + ':' + distance + ':' + aScalar + ':' + xscalar);
			return parseInt(xstartPos + xpos+ aScalar);
		},
		yPos: function(yscalar, distance){
			var ypos = (distance * (bScalar * 2))/2 * yscalar;
			console.log('yposS:' + ypos + ':' + distance + ':' + bScalar + ':' + yscalar);
			return parseInt(ystartPos + ypos + bScalar);
		}
	}
})();


var cnv = document.getElementById("star");
var jg = new jsGraphics(cnv);


</script>


<body>
<c:out value="${param.cluster}"/>
<div id="star">
<br/>

<!-- ${root} -->
<br/>
<br/>
<br/>
<br/>

<script type="text/javascript">

 var stardim = starAttributes.getStarColor(1, "${starColor}");
jg.setColor(stardim.color());
jg.fillArc(ellipsePosition.xStart(), ellipsePosition.yStart(), stardim.arcSize(), stardim.arcSize(),0,360);
jg.paint();
var p_count = 0;
</script>
<c:out value="${starColor}"/>
<c:forEach var="pr" items="${root}" > 
  <div id="starSystemCopy"><span><a href=/planet_detail2.htm?systemId=${systemId}&planet=${pr.planetoidId}&cluster=${param.cluster}&starColor=${starColor}&starNumber=${param.starNumber}&startu=${param.startu}&startv=${param.startv} id="planet">Planetoid Id:</span><span>${pr.planetoidId}</a></span></div>
  <br/>
  <div id="starSystemCopy"><span>Distance to Primary:</span><span>${pr.distanceToPimary}</span></div>
  <div id="starSystemCopy"><span>Position:</span><span>${pr.degree}</span></div>
  <div id="starSystemCopy"><span>Temperature:</span><span>${pr.temperature}</span></div>
  <br/>

	<script type="text/javascript">

	jg.setStroke(2);
	jg.setColor("white");
	jg.drawEllipse(ellipsePosition.computeX(${pr.distanceToPimary}),
			ellipsePosition.computeY(${pr.distanceToPimary}),
			ellipsePosition.computeA(${pr.distanceToPimary}),
			ellipsePosition.computeB(${pr.distanceToPimary})
			);
	var xpos = ellipsePosition.xPos(${pr.planetPosX}, ${pr.distanceToPimary});
	var ypos = ellipsePosition.yPos(${pr.planetPosY}, ${pr.distanceToPimary});
	console.log('xpos:' + xpos);
	console.log('ypos:' + ypos);
	jg.fillArc(xpos, ypos, 15, 15,0,360);
	jg.paint();
	jg.setColor("black");
	jg.setFont("arial","15px",Font.ITALIC_BOLD);
	jg.drawString(""+p_count++, xpos, ypos); 	
	jg.paint();
	</script>
</c:forEach>

<script>
$("#returnClusters").click(function () { 
 	var lastU = $.getUrlVar('startu');
	var lastV = $.getUrlVar('startv');
 	var url = "./systems_detail_cluster2.htm?systemId=${systemId}" + "&startu="+lastU+"&startv="+lastV;    
	$(location).attr('href',url);
});
</script>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
</div> <!-- star -->
</body>
</html>