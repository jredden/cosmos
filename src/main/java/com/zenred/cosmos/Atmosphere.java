//Source file: C:/VisualCafe/JAVA/LIB/cosmos/Atmosphere.java

package com.zenred.cosmos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.HashMap;

import com.zenred.data_access.Planetoid_Atmosphere;
import com.zenred.util.TwoDMatrix;

/*
 # *   encoding sequence for planetary atmoshpheres
 # *                                          temp       temp
 # *                                          solid      gaseous
 # *
 # *            1 = methane       ch(4)       90.6       109.0
 # *            2 = sulfuric acid h[1]so(4)
 # *            3 = carbon dioxide co[1]      194.5      216.5
 # *            4 = nitrogen      n[1]        64.0       78.0
 # *            5 = ammonia       nh(3)       195.3     239.6
 # *            6 = nitrogen dioxide no[1]    261.8     294.2
 # *            7 = oxygen        o[1]         54.6     90.0
 # *            8 = water         h[1]o       273.0     373.0
 # *            9 = neon          ne           24.6      27.1
 # *            10 = hydrogen sulfide h[1]s   187.5     212.3
 # *            11 = sulfer dioxide so[1]     200.8     263.0
 # *            12 = chlorine     cl[1]       171.9     238.4
 # *            13 = argon        ar           83.8      87.4
 # *            14 = helium       he            5.0
 # *            15 = hydrochloric acid hcl
 # *            16 = carbon monoxide co        67.9      83.0
 */
public class Atmosphere {
	
	private static Map<String,String> atmosphere_name_map = new HashMap<String,String>();
	static{
		atmosphere_name_map.put("0", "residue");
		atmosphere_name_map.put("1", "sulfuric_acid_h[1]so[4]");
		atmosphere_name_map.put("2", "methane_ch(4)");
		atmosphere_name_map.put("3", "carbon_dioxide_co[1]");
		atmosphere_name_map.put("4", "nitrogen_n[1]");
		atmosphere_name_map.put("5", "ammonia_nh[3]");
		atmosphere_name_map.put("6", "nitrogen_dioxide_no[1]");
		atmosphere_name_map.put("7", "oxygen_o[1]");
		atmosphere_name_map.put("8", "water_h[2]o");
		atmosphere_name_map.put("9", "neon_ne");
		atmosphere_name_map.put("10", "hydrogen_sulfide_h[1]s");
		atmosphere_name_map.put("11", "sulfer_dioxide_so[1]");
		atmosphere_name_map.put("12", "chlorine_cl[1]");
		atmosphere_name_map.put("13", "argon_ar");
		atmosphere_name_map.put("14", "helium_he");
		atmosphere_name_map.put("15", "hydrochloric_acid hcl");
		atmosphere_name_map.put("16", "carbon_monoxide_co");

	}
	
	public static String getWaterIndex(){return "8";}

	private static double psize[] = { 3, 5, 1, 2, 5, 5, 4, 0, 3, 6, 6, 99.9, 5,
			17, 99.9, 8 };

	private static double habund[] = { 8.0, 4.5, 7.0, 8.0, 8.0, 2.0, 7.0, 6.9,
			99.9, 8.0, 3.0, 00.0, 99.0, 70.0, 0.0, 4.0 };

	private static double habund2[] = { 3.0, 5.0, 4.5, 4.0, 3.0, 5.2, 4.0, 4.0, 
		0.0, 3.0, 5.2, 99.0, 0.0, 0.0, 99.0, 4.5 };

	private static double uvflux[] = { 3.0, 6.0, 4.0, 6.0, 3.0, 7.0, 6.0, 4.0, 
		7.0, 3.5, 6.5, 0.0, 7.0, 3.0, 0.0, 3.5 };

	private static double uvflux2[] = { 0.0, 3.5, 1.0, 0.0, 0.5, 4.5, 1.0, 1.0, 
		0.0, 1.0, 3.5, 99.9, 0.0, 1.0, 99.9, 2.5 };

	private static double stemp[] = { 200.0, 500.0, 400.0, 390.0, 320.0, 550.0,
			330.0, 330.0, 500.0, 200.0, 600.0, 330.0, 500.0, 400.0, 330.0,
			280.0 };

	private static double sd[] = { 100.0, 75.0, 50.0, 25.0, 16.0, 10.0, 8.0,
			5.0, 3.0, 2.0, 1.0, 0.0, -1.0 };

	private static double td[] = { 9999.9, 380.0, 320.0, 280.0, 260.0, 220.0,
			200.0, 150.0, 100.0, -1.0 };

	private static double tempm[][] = {
			{ 0.10, 0.10, 0.20, 0.90, 2.15, 30.0, 50.0, 50.0, 50.0, 50.0, 50.0 },
			{ 60.0, 10.0, 0.01, 0.01, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00 },
			{ 20.0, 2.00, 0.10, 0.01, 0.01, 20.0, 50.0, 30.0, 30.0, 30.0, 30.0 },
			{ 1.00, 50.0, 99.0, 99.0, 60.0, 60.0, 60.0, 60.0, 60.0, 60.0, 60.0 },
			{ 0.00, 0.01, 0.01, 0.10, 5.00, 10.0, 60.0, 60.0, 60.0, 60.0, 60.0 },
			{ 50.0, 20.0, 0.01, 0.01, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00 },
			{ 1.00, 10.0, 35.0, 25.0, 1.00, 0.10, 0.00, 0.00, 0.00, 0.00, 0.00 },
			{ 0.01, 5.00, 50.0, 50.0, 50.0, 60.0, 70.0, 70.0, 50.0, 50.0, 50.0 },
			{ 5.00, 2.00, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01 },
			{ 0.00, 0.00, 0.00, 1.00, 1.00, 30.0, 15.0, 0.01, 0.01, 0.01, 0.01 },
			{ 60.0, 10.0, 0.10, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00 },
			{ 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00 },
			{ 5.00, 2.00, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01 },
			{ 0.01, 0.01, 0.10, 0.10, 20.0, 30.0, 20.0, 10.0, 10.0, 10.0, 10.0 },
			{ 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00 },
			{ 0.01, 0.10, 5.00, 5.00, 0.10, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00 } };

	private static double sizm[][] = {
			{ 60.0, 99.0, 92.0, 30.0, 2.15, 0.90, 0.10, 21.5, 60.0, 60.0, 60.0,
					60.0 },
			{ 0.00, 0.00, 0.00, 70.0, 30.0, 10.0, 1.00, 0.01, 0.00, 0.00, 0.00,
					0.00 },
			{ 0.00, 0.00, 0.01, 1.00, 1.00, 50.0, 20.0, 20.0, 60.0, 60.0, 30.0,
					30.0 },
			{ 0.01, 0.01, 0.01, 1.00, 20.0, 50.0, 70.0, 70.0, 1.00, 0.01, 0.00,
					0.00 },
			{ 50.0, 50.0, 5.00, 1.00, 0.10, 0.01, 0.01, 5.00, 10.0, 60.0, 60.0,
					60.0 },
			{ 0.00, 0.00, 0.00, 0.01, 50.0, 10.0, 0.10, 0.01, 0.00, 0.00, 0.00,
					0.00 },
			{ 0.01, 0.01, 0.01, 1.00, 20.0, 25.0, 20.0, 1.00, 0.01, 0.00, 0.00,
					0.00 },
			{ 0.01, 0.01, 0.01, 1.00, 30.0, 50.0, 30.0, 1.00, 1.00, 30.0, 80.0,
					80.0 },
			{ 0.01, 0.01, 0.01, 0.10, 0.10, 0.10, 0.10, 0.01, 0.00, 0.00, 0.00,
					0.00 },
			{ 0.00, 0.10, 0.10, 0.20, 1.50, 0.10, 0.00, 0.00, 0.00, 0.00, 0.00,
					0.00 },
			{ 0.00, 0.00, 0.01, 5.00, 30.0, 5.00, 0.10, 0.01, 0.00, 0.00, 0.00,
					0.00 },
			{ 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00,
					0.00 },
			{ 0.01, 0.01, 0.01, 0.20, 0.30, 0.30, 0.01, 0.00, 0.00, 0.00, 0.00,
					0.00 },
			{ 60.0, 40.0, 5.00, 0.10, 0.01, 0.01, 0.01, 0.00, 0.00, 0.00, 0.00,
					0.00 },
			{ 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00,
					0.00 },
			{ 0.00, 0.00, 0.00, 1.00, 25.0, 1.00, 0.10, 0.00, 0.00, 0.00, 0.00,
					0.00 } };

	private static HashMap color_index = new HashMap();
	static {
		color_index.put("o0", new Integer(1));
		color_index.put("o1", new Integer(1));
		color_index.put("o2", new Integer(1));
		color_index.put("o3", new Integer(1));
		color_index.put("o4", new Integer(1));
		color_index.put("o5", new Integer(1));
		color_index.put("o6", new Integer(1));
		color_index.put("o7", new Integer(1));
		color_index.put("o8", new Integer(1));
		color_index.put("o9", new Integer(1));

		color_index.put("b0", new Integer(2));
		color_index.put("b1", new Integer(2));
		color_index.put("b2", new Integer(2));
		color_index.put("b3", new Integer(2));
		color_index.put("b4", new Integer(2));
		color_index.put("b5", new Integer(2));
		color_index.put("b6", new Integer(2));
		color_index.put("b7", new Integer(2));
		color_index.put("b8", new Integer(2));
		color_index.put("b9", new Integer(2));

		color_index.put("a0", new Integer(3));
		color_index.put("a1", new Integer(3));
		color_index.put("a2", new Integer(3));
		color_index.put("a3", new Integer(3));
		color_index.put("a4", new Integer(3));
		color_index.put("a5", new Integer(3));
		color_index.put("a6", new Integer(3));
		color_index.put("a7", new Integer(3));
		color_index.put("a8", new Integer(3));
		color_index.put("a9", new Integer(3));

		color_index.put("f0", new Integer(4));
		color_index.put("f1", new Integer(4));
		color_index.put("f2", new Integer(4));
		color_index.put("f3", new Integer(4));
		color_index.put("f4", new Integer(4));
		color_index.put("f5", new Integer(4));
		color_index.put("f6", new Integer(4));
		color_index.put("f7", new Integer(4));
		color_index.put("f8", new Integer(4));
		color_index.put("f9", new Integer(4));

		color_index.put("g0", new Integer(5));
		color_index.put("g1", new Integer(5));
		color_index.put("g2", new Integer(5));
		color_index.put("g3", new Integer(5));
		color_index.put("g4", new Integer(5));
		color_index.put("g5", new Integer(5));
		color_index.put("g6", new Integer(5));
		color_index.put("g7", new Integer(5));
		color_index.put("g8", new Integer(5));
		color_index.put("g9", new Integer(5));

		color_index.put("k0", new Integer(6));
		color_index.put("k1", new Integer(6));
		color_index.put("k2", new Integer(6));
		color_index.put("k3", new Integer(6));
		color_index.put("k4", new Integer(6));
		color_index.put("k5", new Integer(6));
		color_index.put("k6", new Integer(6));
		color_index.put("k7", new Integer(6));
		color_index.put("k8", new Integer(6));
		color_index.put("k9", new Integer(6));

		color_index.put("m0", new Integer(7));
		color_index.put("m1", new Integer(7));
		color_index.put("m2", new Integer(7));
		color_index.put("m3", new Integer(7));
		color_index.put("m4", new Integer(7));
		color_index.put("m5", new Integer(7));
		color_index.put("m6", new Integer(7));
		color_index.put("m7", new Integer(7));
		color_index.put("m8", new Integer(7));
		color_index.put("m9", new Integer(7));

		color_index.put("do", new Integer(1));
		color_index.put("db", new Integer(2));
		color_index.put("da", new Integer(3));
		color_index.put("df", new Integer(4));
		color_index.put("dg", new Integer(5));
		color_index.put("dk", new Integer(6));
		color_index.put("dm", new Integer(7));
	}

	private Vector gas;

	private String sterotype;

	/**
	 * ctor - no arg
	 */
	public Atmosphere() {
	}

	/**
	 * 
	 */
	private static double hydrogenIndex(String star_color_type) {

		double _hdex = 0.0;
		Integer _color_idex = (Integer) color_index.get(star_color_type);
		_hdex = _color_idex.doubleValue();
		_hdex += (DrawRolls.Instance().draw_rand() - 0.5);
		return _hdex;
	}

	/**
	 * 
	 */
	private static double ultraVioletIndex(String star_color_type) {
		double _uvdex = 0.0;
		Integer _color_idex = (Integer) color_index.get(star_color_type);
		_uvdex = 8.0 - _color_idex.doubleValue();
		_uvdex *= (1.0 + (DrawRolls.Instance().draw_rand() - 0.5) * 0.3);
		return _uvdex;
	}

	/**
	 * 
	 */
	private static double radiusIndex(double planet_radius) {
		double _dex = planet_radius / 1000.0;
		return _dex;
	}

	/*
	 * 
	 */
	private static double temperature(double star_luminosity,
			double distance_primary_au_s) {
		return Math
				.pow(
						295.0 * (star_luminosity / (distance_primary_au_s * distance_primary_au_s)),
						0.25);
	}

	/**
	 * 
	 */
	private static int sizeDimensionIndex(double radius_index) {
		int _idex = 0;
		for (; _idex < sd.length; _idex++) {
			if (sd[_idex] > radius_index && sd[_idex + 1] < radius_index)
				break;
		}
		return _idex;
	}

	/**
	 * 
	 */
	private static int temperatureDimensionIndex(double temperature) {
		int _idex = 0;
		for (; _idex < td.length; _idex++) {
			if (td[_idex] > temperature && td[_idex + 1] < temperature)
				break;
		}
		return _idex == td.length - 1 ? _idex - 1 : _idex;
	}

	/**
	 * 
	 */
	private static void resolvePercentageProfile(int temp_dim_index,
			int size_dimen_index, TwoDMatrix percentages) {
		double delta;
		double temperature;
		for (int _idex = 0; _idex < sizm[0].length - 1; _idex++) {
			if (sizm[temp_dim_index][_idex] <= tempm[size_dimen_index][_idex]) {
				delta = tempm[size_dimen_index][_idex]
						- sizm[temp_dim_index][_idex];
				temperature = sizm[temp_dim_index][_idex];
			} else {
				delta = sizm[temp_dim_index][_idex]
						- tempm[size_dimen_index][_idex];
				temperature = tempm[size_dimen_index][_idex];
			}

			if (delta > 50.0) {
				percentages.setEntry(_idex, new Double(temperature));
			} else {
				percentages.setEntry(_idex, new Double(
						sizm[temp_dim_index][_idex]
								+ tempm[size_dimen_index][_idex]));
			}

		}
	}

	private static String atmosphereString(TwoDMatrix w_percen,
			TwoDMatrix percentages, TwoDMatrix intmat) {
		StringBuffer _plna = new StringBuffer("");
		double percentage_sum = 0.0;
		intmat = BldmatOps.normMatrix(intmat);
		int _idex0 = intmat.firstZeroSkip();
		if (_idex0 == 0) {
			return "0.0";
		}

		for (int _idex = 0; _idex < _idex0; _idex++) {
			w_percen.setEntry(_idex, percentages.getEntry(intmat
					.getEntry(_idex).intValue()));
		}
		for (int _idex = 0; _idex < _idex0; _idex++) {
			percentage_sum += w_percen.getEntry(_idex);
		}

		if (0.0 == percentage_sum) {
			return "0.0";
		}

		double scalar = 100.0 / percentage_sum;
		for (int _idex = 0; _idex < _idex0; _idex++) {
			if(0 != _idex){_plna.append(',');}
			w_percen.setEntry(_idex, percentages.getEntry(intmat
					.getEntry(_idex).intValue())
					* scalar + .005);
			_plna.append(w_percen.getEntry(_idex));
//			_plna.append('.');
//			_plna.append(w_percen.getEntry(_idex));
			_plna.append(',');
			_plna.append(intmat.getEntry(_idex).intValue());
		}
		return _plna.toString();
	}

	/**
	 * 
	 */
	public static Atmosphere genAtmosphere(double star_luminosity,
			double distance_primary_au_s, double planet_radius,
			String star_color_type) {

		TwoDMatrix gasmat1 = new TwoDMatrix();
		TwoDMatrix gasmat2 = new TwoDMatrix();
		TwoDMatrix gasmat3 = new TwoDMatrix();
		TwoDMatrix gasmat4 = new TwoDMatrix();
		TwoDMatrix gasmat5 = new TwoDMatrix();
		TwoDMatrix gasmat6 = new TwoDMatrix();

		TwoDMatrix intmat1 = new TwoDMatrix();
		TwoDMatrix intmat2 = new TwoDMatrix();
		TwoDMatrix intmat3 = new TwoDMatrix();
		TwoDMatrix intmat4 = new TwoDMatrix();
		TwoDMatrix intmat5 = new TwoDMatrix();

		TwoDMatrix chemical_code = new TwoDMatrix();
		TwoDMatrix w_percen = new TwoDMatrix();
		TwoDMatrix percentages = new TwoDMatrix();

		TwoDMatrix planet_size = new TwoDMatrix(psize);
		TwoDMatrix hydrogen_abundance = new TwoDMatrix(habund);
		TwoDMatrix hydrogen_abundance2 = new TwoDMatrix(habund2);
		TwoDMatrix ultraviolet_flux = new TwoDMatrix(uvflux);
		TwoDMatrix ultraviolet_flux2 = new TwoDMatrix(uvflux2);
		TwoDMatrix temperatures = new TwoDMatrix(stemp);
		String planet_atmosphere_profile;
		TwoDMatrix.selfMatrix(chemical_code);

		gasmat1 = BldmatOps.bldMatrix(planet_size, Atmosphere
				.radiusIndex(planet_radius), chemical_code);
		gasmat2 = BldmatOps.bldMatrix1(hydrogen_abundance, Atmosphere
				.hydrogenIndex(star_color_type), chemical_code);
		gasmat3 = BldmatOps.bldMatrix1(ultraviolet_flux, Atmosphere
				.ultraVioletIndex(star_color_type), chemical_code);
		gasmat4 = BldmatOps.bldMatrix1(temperatures, Atmosphere.temperature(
				star_luminosity, distance_primary_au_s), chemical_code);
		gasmat5 = BldmatOps.bldMatrix(hydrogen_abundance2, Atmosphere
				.hydrogenIndex(star_color_type), chemical_code);
		gasmat6 = BldmatOps.bldMatrix(ultraviolet_flux2, Atmosphere
				.ultraVioletIndex(star_color_type), chemical_code);
		gasmat1 = BldmatOps.normMatrix(gasmat1);
		gasmat2 = BldmatOps.normMatrix(gasmat2);
		gasmat3 = BldmatOps.normMatrix(gasmat3);
		gasmat4 = BldmatOps.normMatrix(gasmat4);
		gasmat5 = BldmatOps.normMatrix(gasmat5);
		gasmat6 = BldmatOps.normMatrix(gasmat6);

		/* intersect uv and hdex first */
		/* intersect size and temp */
		intmat1 = BldmatOps.intersectMatrix(gasmat1, gasmat4);
		intmat1 = BldmatOps.normMatrix(intmat1);
		intmat2 = BldmatOps.intersectMatrix(gasmat2, gasmat3);
		intmat2 = BldmatOps.normMatrix(intmat2);
		intmat5 = BldmatOps.intersectMatrix(intmat1, intmat2);
		intmat5 = BldmatOps.normMatrix(intmat5);
		intmat4 = BldmatOps.intersectMatrix(gasmat5, gasmat6);
		intmat4 = BldmatOps.normMatrix(intmat4);
		intmat3 = BldmatOps.intersectMatrix(intmat5, intmat4);
		intmat3 = BldmatOps.normMatrix(intmat3);

		int size_index = Atmosphere.sizeDimensionIndex(Atmosphere
				.radiusIndex(planet_radius));
		int temperature_index = Atmosphere.temperatureDimensionIndex(Atmosphere
				.temperature(star_luminosity, distance_primary_au_s));

		resolvePercentageProfile(temperature_index, size_index, percentages);
		planet_atmosphere_profile = atmosphereString(w_percen, percentages,
				intmat3);
		Atmosphere _atmos = new Atmosphere();
		_atmos.setAtmosphereProfile(planet_atmosphere_profile);
		return _atmos;
	}

	private String atmosphere_profile;

	public String getAtmophereProfile() {
		return atmosphere_profile;
	}

	public void setAtmosphereProfile(String atmosphere_profile) {
		this.atmosphere_profile = atmosphere_profile;
	}

	public String toString() {
		return atmosphere_profile;
	}
	
	public static List<Planetoid_Atmosphere> genList(String profile, String planetoid_id ){
		List<Planetoid_Atmosphere> _atmos_list = new ArrayList<Planetoid_Atmosphere>();
		String [] _atmosphere_list = profile.split(",");
		int _xor_switch = 0;
		Planetoid_Atmosphere _planet_atmosphere = null;
		for(String _next :_atmosphere_list ){
			if(0 == _xor_switch){
				_planet_atmosphere = new Planetoid_Atmosphere();
				_planet_atmosphere.setPlanetoidId(planetoid_id);
				_planet_atmosphere.setPercentage(new Double(_next));
				_xor_switch = 1;
			}
			else{
				_planet_atmosphere.setChem_name(atmosphere_name_map.get(_next));
				_atmos_list.add(_planet_atmosphere);
				_xor_switch = 0;
			}
			// could have been written _xor_switch ^= 1;
		}
		return _atmos_list;
	}
}
