<?php
class VizStarColor{
	const BLUESTAR = 'blue';
	const LIGHTBLUESTAR = 'ltbl';
	const ORANGESTAR = 'orng';
	const PALEYELLOWSTAR = 'pyel';
	const REDSTAR = 'red_';
	const WHITESTAR = 'whit';
	const YELLOWSTAR = 'yelo';
	
	const STARCOLOR_INDEX = 13;
		
	public static function visualize($something_to_viz, $index){
		$color_array = array(VizStarColor::BLUESTAR => '<div id=\'blue_star\'><b>blue star</b><td width=0> %s </td></div>', 
		VizStarColor::LIGHTBLUESTAR => '<div id=\'lightblue_star\'><b>lightblue star</b><td width=0> %s </td></div>',
		VizStarColor::ORANGESTAR => '<div id=\'orange_star\'star><b>orange star</b><td width=0> %s </td></div>',
		VizStarColor::PALEYELLOWSTAR => '<div id=\'paleyellow_star\'><b>paleyellow star</b><td width=0> %s </td></div>',
		VizStarColor::REDSTAR => '<div id=\'red_star\'><b>red star</b><td width=0> %s </td></div>',
		VizStarColor::WHITESTAR => '<div id=\'white_star\'><b>white star</b><td width=0> %s </td></div>',
		VizStarColor::YELLOWSTAR => '<div id=\'yellow_star\'><b>yellow star</b><td width=0> %s </td></div>'
		);
		if($index == 13){
			$color = explode('.', $something_to_viz);
			$_color = $color[0];
//			echo count($color_array);
//			echo "color:". array_search($_color, $color_array) . ":";
//			echo '['.$color_array[VizStarColor::YELLOWSTAR].']';
			printf($color_array[$_color], $something_to_viz);
		}
		else{
			printf ("<td width=0> %s </td>\n", $something_to_viz);
		}
	}
	
}
?>