
var starAttributes = (function(){
	// private 
	
	var starColor = {};
	function starDim(color, arcSize){
		this.color=function(){return color;}
		this.arcSize=function(){return arcSize;}
	}
	
	function gen(scale){
		starColor["blue.gi.i"]=new starDim("blue",20*scale);
		starColor["blue.gi.ii"]=new starDim("blue",17*scale);
		starColor["blue.sg.i"]=new starDim("blue",15*scale);
		starColor["blue.sg.ii"]=new starDim("blue",13*scale);
		starColor["blue.subgi"]=new starDim("blue",11*scale);
		starColor["blue.mains"]=new starDim("blue",10*scale);
		starColor["blue.dwarf"]=new starDim("blue",8*scale);

		starColor["ltbl.gi.i"]=new starDim("lightblue",20*scale);
		starColor["ltbl.gi.ii"]=new starDim("lightblue",17*scale);
		starColor["ltbl.sg.i"]=new starDim("lightblue",15*scale);
		starColor["ltbl.sg.ii"]=new starDim("lightblue",13*scale);
		starColor["ltbl.subgi"]=new starDim("lightblue",11*scale);
		starColor["ltbl.mains"]=new starDim("lightblue",10*scale);
		starColor["ltbl.dwarf"]=new starDim("lightblue",8*scale);

		starColor["orng.gi.i"]=new starDim("orange",20*scale);
		starColor["orng.gi.ii"]=new starDim("orange",17*scale);
		starColor["orng.sg.i"]=new starDim("orange",15*scale);
		starColor["orng.sg.ii"]=new starDim("orange",13*scale);
		starColor["orng.subgi"]=new starDim("orange",11*scale);
		starColor["orng.mains"]=new starDim("orange",10*scale);
		starColor["orng.dwarf"]=new starDim("orange",8*scale);

		starColor["pyel.gi.i"]=new starDim("#FFF380",20*scale);
		starColor["pyel.gi.ii"]=new starDim("#FFF380",17*scale);
		starColor["pyel.sg.i"]=new starDim("#FFF380",15*scale);
		starColor["pyel.sg.ii"]=new starDim("#FFF380",13*scale);
		starColor["pyel.subgi"]=new starDim("#FFF380",11*scale);
		starColor["pyel.mains"]=new starDim("#FFF380",10*scale);
		starColor["pyel.dwarf"]=new starDim("#FFF380",8*scale);

		starColor["red_.gi.i"]=new starDim("red",20*scale);
		starColor["red_.gi.ii"]=new starDim("red",17*scale);
		starColor["red_.sg.i"]=new starDim("red",15*scale);
		starColor["red_.sg.ii"]=new starDim("red",13*scale);
		starColor["red_.subgi"]=new starDim("red",11*scale);
		starColor["red_.mains"]=new starDim("red",10*scale);
		starColor["red_.dwarf"]=new starDim("red",8*scale);

		starColor["whit.gi.i"]=new starDim("white",20*scale);
		starColor["whit.gi.ii"]=new starDim("white",17*scale);
		starColor["whit.sg.i"]=new starDim("white",15*scale);
		starColor["whit.sg.ii"]=new starDim("white",13*scale);
		starColor["whit.subgi"]=new starDim("white",11*scale);
		starColor["whit.mains"]=new starDim("white",10*scale);
		starColor["whit.dwarf"]=new starDim("white",8*scale);

		starColor["yelo.gi.i"]=new starDim("yellow",20*scale);
		starColor["yelo.gi.ii"]=new starDim("yellow",17*scale);
		starColor["yelo.sg.i"]=new starDim("yellow",15*scale);
		starColor["yelo.sg.ii"]=new starDim("yellow",13*scale);
		starColor["yelo.subgi"]=new starDim("yellow",11*scale);
		starColor["yelo.mains"]=new starDim("yellow",10*scale);
		starColor["yelo.dwarf"]=new starDim("yellow",8*scale);
	}
	
	return {
		//public methods
		getStarColor: function(scale, color){
			gen(scale);
			if(typeof starColor[color] != "undefined"){
				return starColor[color];
			}
			else{
				return new starDim("brown",8*scale);
			}
		}
	}
})();

var OneStar = (function(){
	// private 
	
	var starId;
	var parentSystemId;
	var clusterId;
	var distanceToClusterVirtCentre;
	var luminosity;
	var noPlanetsAllowed;
	var starAngleInRadians;
	var starColor;
	var starType;
	var starSize;
	
	// public
	
	return {
		setStarId: function setstarid(starid){
			starId = starid;
		},
		getStarId: function getstarid(){
			return starId;
		},
		setParentSystemId: function setparentsystemid(parentsystemid){
			parentSystemId = parentsystemid;
		},
		getParentSystemId: function getparentsystemid(){
			return parentSystemId;
		},
		setClusterId: function setclusterid(clusterid){
			clusterId = clusterid;
		},
		getClusterId: function getclusterid(){
			return clusterid;
		},
		setDistanceToClusterVirtCentre: function setdistancetoclustervirtcentre (distancetoclustervirtcentre){
			DistanceToClusterVirtCentre = distancetoclustervirtcentre;
		},
		getDistanceToClusterVirtCentre: function getdistancetoclustervirtcentre(){
			return DistanceToClusterVirtCentre;
		},
		setLuminosity: function setluminosity(lumens){
			luminosity = lumens;
		},
		getLuminosity: function getluminosity(){
			return luminosity;
		},
		setNoPlanetsAllowed: function setnoplanetsallowed(noplanetsallowed){
			noPlanetsAllowed = noplanetsallowed;
		},
		getNoPlanetsAllowed: function getnoplanetsallowed(){
			return noPlanetsAllowed;
		},
		setStarAngleInRadians: function setstarangleinradians(starangleinradians){
			starAngleInRadians = starangleinradians;
		},
		getStarAngleInRadians: function getstarangleinradians(){
			return starangleinradians;
		},
		setStarColor: function setstarcolor(starcolor){
			starColor = starcolor;
		},
		getStarColor: function getstarcolor(){
			return starcolor;
		},
		setStarType: function setstartype(startype){
			starType = startype;
		},
		getStarType: function getstartype(){
			return startype;
		},
		setStarSize: function setstarsize(starsize){
			starSize = starsize;
		},
		getStarSize: function getstarsize(){
			return starSize;
		}
	};
});