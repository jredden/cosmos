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
	atmosphere['sulfuric_acid_h[1]so[4]']='#7345F1';
	atmosphere['nitrogen_dioxide_no[1]']='#D3F18A';
	atmosphere['hydrogen_sulfide_h[1]s']='#B28D5F';
	atmosphere['sulfer_dioxide_so[1]']='#0F73A3';
	atmosphere['argon_ar[1]']="#1C83AA";
	atmosphere['helium_he[1]']='#293ADD';
	atmosphere['hydrogen_h[1]']='#DAB775';
	atmosphere['hydrocloric_acid_h[1]cl[1]']='#DEADBE';
	atmosphere['carbon_monoxide_co[1]']='#BEDEAD';
	atmosphere['sodium_na[1]']='#76576E';
	atmosphere['potassium_k[1]']='#60AA16';
	atmosphere['sulfer_s[1]']='#11E29E';
	atmosphere['carbon_c[1]']='#C0C0C0';
	atmosphere['ozone_o[3]']='#0F0F0F';
	atmosphere['silicon_si[1]']='#960800';
	atmosphere['ethane_c[2]h[6]']='#00C9A6';
	atmosphere['acetylene_c[2]h[2]']='#852ED3';
	atmosphere['dicetylene_c[4]h[2]']='#91C564';
	atmosphere['methacetylene_ch[3]c=ch']='#F5837B';
	atmosphere['cyanoacetylene_c[3]h[1]n[1]']='#0C24E8';
	atmosphere['hydrogen_cyanide_h[1]c[1]n[1]']='#DEADED';
	
/*
 *     
    HydrogenCyanide("hydrogen_cyanide", "h[1]c[1]n[1]", 260.0, 269.0)

 */	
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