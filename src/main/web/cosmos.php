<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<html>

<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<style type="text/css"> @import "cosmos.css";</style>
</head>

<body lang="en-us" dir="ltr">
<div id="content">


<?php 
require_once('classes/DataBaseConnection.php');
require_once('classes/SqlQuerries.php');
require_once('classes/VizStarColor.php');

$instance = DataBaseConnection::instance("cosmos");
$instance->query(SqlQuerries::SELECT_UNIVERSE);	 
$instance->close();
$result = $instance->fetchResult();

?>

<?php
for ($idex = 0; $idex < $result->size(); $idex++) {
   $next_array = $result->fetch();
   echo '<div id="content2"><table><tr>';
   for ($idex2 = 0; $idex2 < $next_array->size(); $idex2++) {
    if($idex2 % 4 == 0){
      echo '</tr><tr>';
    }
//   	printf ("<td width=0> %s </td>\n", $next_array->fetch());
	VizStarColor::visualize($next_array->fetch(), $idex2);
   }
   echo '</tr></table><br/><br/></div>';
}

?>
</table>
</div>
</body>
</html>
