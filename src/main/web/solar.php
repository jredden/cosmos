<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<html>

<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<style type="text/css"> @import "cosmos.css";</style>
</head>
<body>
Star Cluster Systems
<br/>
<%
require_once('classes/cosmos_viz_setup.php');

$cosmos_viz_setup = new cosmos_viz_setup($request);
foreach($cosmos_viz_setup->getSystems() as $system_rep):
	echo '<a href=./systems_detail.htm?systemId="' . $system_rep->getSystemId() . '">' . $system_rep->getSystemId().'</a></br>';
endforeach;
%>

</body>
</html>