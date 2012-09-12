<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
  <style>
  body { background:MidnightBlue; }
  button { font-size:12px; margin:2px; }
  p { width:150px; border:1px red solid; }
  div { color:red; font-weight:bold; }
  </style>
  <script src="http://www.cosmos.com/js/jquery-1.6.min.js" type="text/javascript"></script>
  <script type="text/javascript" src="http://www.cosmos.com/js/wz_jsgraphics3.js"></script>

</head>
<body>
  <button id="getp">Get Paragraph Height</button>
  <button id="getd">Get Document Height</button>
  <button id="getw">Get Window Height</button>
  <button id="getpw">Get Paragraph Width</button>
  <button id="getdw">Get Document Width</button>
  <button id="getww">Get Window Width</button>
  

  <div>&nbsp;</div>
  <p>
    Sample paragraph to test height and width
  </p>
<script>
    function showHeight(ele, h) {
      $("div").text("The height for the " + ele + 
                    " is " + h + "px.");
    }
    $("#getp").click(function () { 
      showHeight("paragraph", $("p").height()); 
    });
    $("#getd").click(function () { 
      showHeight("document", $(document).height()); 
    });
    $("#getw").click(function () { 
      showHeight("window", $(window).height()); 
    });
	function showWidth(ele, w) {
	  $("div").text("The width for the " + ele + 
                " is " + w + "px.");
	}
	$("#getpw").click(function () { 
  		showWidth("paragraph", $("p").width()); 
	});
	$("#getdw").click(function () { 
  		showWidth("document", $(document).width()); 
	});
	$("#getww").click(function () { 
  		showWidth("window", $(window).width()); 
	});
	
	function draw(jg, xdim, ydim){
		jg.setColor("#000080"); 
		jg.fillRect(xdim, ydim, 149, 10);
		jg.paint();
	}

</script>

<script>
$(document).ready(function() {
var jg100 = new jsGraphics("u100-v100");
var jg101 = new jsGraphics("u101-v101");
var x1 = $(document).width();
var y1 = $(document).height();
var x2 = x1;
var y2 = y1;
x1 /= 4;
x2 /= 2;
y1 /= 2;
y2 /= 3;
draw(jg100, x1, y1);
draw(jg101, x2, y2);
});
</script>

<div id="u100-v100">
</div>
<div id="u101-v101">
</div>


</body>
</html>