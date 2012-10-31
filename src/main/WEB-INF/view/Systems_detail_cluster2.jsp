<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<title>Systems Detail</title>
<style type="text/css"> @import "http://www.cosmos.com/css/cosmos.css";</style>
</head>
<script type="text/javascript" src="http://www.cosmos.com/js/wz_jsgraphics.js"></script>
<script src="http://www.cosmos.com/js/jquery-1.3.2.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Stars.js" type="text/javascript"></script>
<script src="http://www.cosmos.com/js/Clusters.js" type="text/javascript"></script>


<div id="container">
<div id="cluster">

<script>
	$(document).ready(function() {
		var uvDim = "${systemId}";
		var uvArray = uvDim.split('V');
		var udim = uvArray[0].slice(1, uvArray[0].length);
		var vdim = uvArray[1];
		console.log("uvDim:" + udim + ':' + vdim);
		var xdim = $('#cluster').position().left;
		var ydim = $('#cluster').position().top;
		console.log("xyDim:" + xdim + ':' + ydim);
	});

</script>

</div> 	 <!-- cluster -->
</div>   <!-- container -->
</body>
</html>