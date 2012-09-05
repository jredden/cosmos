var atmosphereAttributes = (function(){
	var atmosphere = {};
	atmosphere['residue']='#996666';
	atmosphere['water_h[2]o']='#3399FF';
	atmosphere['oxygen_o[1]']='#99FF33';
	atmosphere['carbon_dioxide_co[1]']='#FFCC33';
	atmosphere['nitrogen_dioxide_no[1]']='#4756A1';
	atmosphere['neon_ne']='#D9ADE2';
	atmosphere['methane_ch(4)']='#3FA568';
	atmosphere['nitrogen_n[1]']='#0CB4D6';
	atmosphere['chlorine_cl[1]']='#94EB6F';
	return {
		getAtmosphereColor: function( color){
			if(typeof atmosphere[color] != "undefined"){
				return atmosphere[color];
			}
			else{
				return 'brown';
			}
		}
	}
})();