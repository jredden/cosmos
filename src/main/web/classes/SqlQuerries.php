<?php
/*
 * Created on Jan 9, 2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
	class SqlQuerries{
	 const SELECT_UNIVERSE = 'Select SystemRep.System_id, ClusterRep.cluster_id, StarRep.cluster_id, SystemRep.distance_to_galaxy_centre, SystemRep.ucoordinate, SystemRep.vcoordinate, ClusterRep.cluster_id, ClusterRep.distance_sys_virt_centre, ClusterRep.angle_in_radians, ClusterRep.cluster_description, StarRep.distance_clust_virt_centre, StarRep.luminosity, StarRep.angle_in_radians_s, StarRep.star_color, StarRep.star_type, StarRep.star_size From SystemRep, ClusterRep, StarRep Where SystemRep.System_id = ClusterRep.System_id and SystemRep.System_id = StarRep.System_id ORDER BY star_color';
	}
?>
