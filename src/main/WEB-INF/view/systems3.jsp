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
	var clusters = [];
	
	return {
	
	    addSystem: function( system ) {
      		systems.push(system);
    	},
    	
    	fetchSystem: function(){
    		return systems.shift();
    	},
    	
    	numberSystems: function(){
    		return systems.length();
    	},
    	
    	addCluster: function(key, cluster) {
    		cluster[key] = cluster;
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
</script>
	<c:forEach var="clusterRepList" items="${systemPlusSomeDetails.clusterRepList}" >
<script>
	systemPlusModule.addCluster("${systemPlusSomeDetails._systemId}",{
								distVirtCentre: ${clusterRepList._distanceSysVirtCentre},
								angle: ${clusterRepList._angleInRadians}
								});								
</script>	
	</c:forEach>
</c:forEach>

</body>
</html>