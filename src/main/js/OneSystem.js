
var decodeSystem = (function(){
	
	// private
	
	const SYSTEM_ID = 0;
	const CLUSTER_DESCRIPTION = 1;
	const DISTANCE_VIRTUAL_CENTRE = 2;
	const NUMBER_STARS_IN_CLUSTER = 3;
	const PLANETS_ALLOWED = 4;
	const ANGLE_IN_RADIANS = 5;
	
	const STAR_ID = 0;
	const PARENT_SYSTEM_ID = 0;
	const CLUSTER_ID = 1;
	const DISTANCE_TO_CLUSTER_VIRT_CENTRE = 2;
	const LUMINOSITY_ID = 3;
	const NO_PLANETS_ALLOWED = 4;
	const STAR_ANGLE_IN_RADIANS = 5;
	const STAR_COLOR = 6;
	const STAR_TYPE = 7;
	const STAR_SIZE = 8;
	
	// public
	
	return {
		systemId: function systemid(){
			return SYSTEM_ID;
		},
		clusterDescription: function clusterdescription(){
			return CLUSTER_DESCRIPTION;
		},
		distanceVirtualCentre: function distancevirtualcentre(){
			return DISTANCE_VIRTUAL_CENTRE;
		},
		numberStarsInCluster: function numberstarsincluster(){
			return NUMBER_STARS_IN_CLUSTER;
		},
		planetsAllowed: function planetsallowed(){
			return PLANETS_ALLOWED;
		},
		angleInRadians: function angleinradians(){
			return ANGLE_IN_RADIANS;
		},
		starId: function starid(){
			return STAR_ID;
		},
		parentSystemId: function parentsystemid(){
			return PARENT_SYSTEM_ID;
		},
		clusterId: function clusterid(){
			return CLUSTER_ID;
		},
		distanceToClusterVirtCentre: function distancetoclustervirtcentre(){
			return DISTANCE_TO_CLUSTER_VIRT_CENTRE;
		},
		luminosityId: function luminosityid(){
			return LUMINOSITY_ID;
		},
		noPlanetsAllowed: function noplanetsallowed(){
			return NO_PLANETS_ALLOWED;
		},
		starAngleInRadians: function starangleinradians(){
			return STAR_ANGLE_IN_RADIANS;
		},
		starColor: function starcolor(){
			return STAR_COLOR;
		},
		starType: function startype(){
			return STAR_TYPE;
		},
		starSize: function starsize(){
			return STAR_SIZE;
		},
		starsInCluster: function starsincluster(clusterId, starList){
			var starsInCluster = new Array();
			
		}
	};
	
})();