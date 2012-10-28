<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
  <style>
  body { background:MidnightBlue; }
  button { font-size:20px; margin:4px; }
  p { width:150px; border:1px red solid; }
  #container { color:red; font-weight:bold; }
  #content {
	float: left;
	width: 80%;
	}
	#sidebar {
	float: right;
	width: 20%;
	}
  </style>
  <script src="http://www.cosmos.com/js/jquery-1.8.1.min.js" type="text/javascript"></script>

</head>
<body>

  <div>&nbsp;</div>
  
  <div id="container">
    <div id="header">
    <button id="getp">Get Paragraph Height</button>
	<button id="getd">Get Document Height</button>
	<button id="getw">Get Window Height</button>
	<button id="getc">Get Content Height</button>
	<button id="gets">Get Sidebar Height</button>
	<button id="getpw">Get Paragraph Width</button>
	<button id="getdw">Get Document Width</button>
	<button id="getww">Get Window Width</button>
	<button id="getcw">Get Content Width</button>
	<button id="getsw">Get Sidebar Width</button>
    
		<p>
			Sample paragraph to test height and width
		</p>
    
    </div>
    <div id="content">  
    </div>
    <div id="sidebar">  
    </div>
    
    <div id="message">&nbsp;</div>
    
</div> <!-- container -->

<script>

    function showHeight(ele, h) {
      $("#message").text("The height for the " + ele + 
                    " is " + h + "px.");
    }
    
    $("#content").text("abc");
    $("#sidebar").text("xyz");
                    
    $("#getp").click(function () { 
      showHeight("paragraph", $("p").height()); 
    });
    $("#getd").click(function () { 
      showHeight("document", $(document).height()); 
    });
    $("#getw").click(function () { 
      showHeight("window", $(window).height()); 
    });
    $("#getc").click(function () { 
      showHeight("content", $("#content").height()); 
    });
    $("#gets").click(function () { 
      showHeight("sidebar", $("#sidebar").height()); 
    });
    
	function showWidth(ele, w) {
	  $("#message").text("The width for the " + ele + 
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
    $("#getcw").click(function () { 
      showWidth("content", $("#content").width()); 
    });
    $("#getsw").click(function () { 
      showWidth("sidebar", $("#sidebar").width()); 
    });
	


</script>

</body>
</html>