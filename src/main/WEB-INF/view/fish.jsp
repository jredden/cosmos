<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
  <style>
  body { background:yellow; }
  button { font-size:12px; margin:2px; }
  p { width:150px; border:1px red solid; }
  div { color:red; font-weight:bold; }
  </style>
  <script src="http://www.cosmos.com/js/jquery-1.6.min.js" type="text/javascript"></script>

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

</script>

</body>
</html>