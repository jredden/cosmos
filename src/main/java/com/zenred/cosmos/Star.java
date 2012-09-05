//Source file: C:/VisualCafe/JAVA/LIB/com.zenred.cosmos/Star.java

package com.zenred.cosmos;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import com.zenred.util.OrderedListCollection;
import com.zenred.util.OrderedArrayListCollection;
import com.zenred.util.AnonBlock;

public class Star {

	private Enumeration type;
	private DrawRolls drawrolls;
	private ArrayList<StarSpread> drawlist;
	private static final boolean DEBUG = true;
	// private static final boolean DEBUG = false;

	// finals to bugger up anonymous class
	private StarSpread found_star_profile;
	private int draw;


	/**
	 * construction - no arguments
	 */
	public Star() {
		initStarList();
	}

	/**
	 * construction - avec seed
	 */
	public Star(long seed_random) {
		drawrolls = new DrawRolls(seed_random);
		initStarList();
	}

	/**
	 * construction extention
	 */
	private void initStarList() {
		drawlist = new ArrayList<StarSpread>();
		for (int idex = 0; idex < InSystemConstraintsIF.STARCONFIGS.length; idex++) {
			if (DEBUG)
				System.out.println("idex::" + idex + "::");
			drawlist.add(idex, new StarSpread(
					InSystemConstraintsIF.STARNUM[idex],
					InSystemConstraintsIF.STARMAX[idex],
					InSystemConstraintsIF.STARMIN[idex],
					InSystemConstraintsIF.STARCONFIGS[idex]));
		}
	}

	/**
	 * see if a range is matched
	 * 
	 * @param - range
	 */
	private boolean testDraw(Object starspread, int draw) {
		if (DEBUG)
			System.out
					.println("testDraw:" + starspread.toString() + ":" + draw);
		boolean _min = ((StarSpread) starspread).getMinRangeOnDraw() <= draw;
		boolean _max = ((StarSpread) starspread).getMaxRangeOnDraw() >= draw;
		return (_min && _max) ? true : false;
	}

	/**
	 * gen_star_prile draws a random number and
	 * 
	 * @return Star profile that matches draw
	 */
	private StarSpread get_star_profile() {
		for(StarSpread starSpread : drawlist){
			if (testDraw(starSpread, draw)) {
				found_star_profile = starSpread;
				break;
			}

		}
/*		
		final int draw = _draw;
		System.out.println("<<<draw:"+draw+">>>");
		found_star_profile[0] = null;
		drawlist.forEachDo(new AnonBlock() {

			public void exec(Object each) {
				if (testDraw(each, draw)) {
					found_star_profile[0] = (StarSpread) each;
					return;
				}
			}
		});
*/
		return found_star_profile;
	}

	/**
	 * genStars returns a list of cluster reps - where each clusterrep is a ClusterRep object
	 */
	public List<ClusterRep> genStars() {
		StarSpreadConstraint starspreadconstraint = new StarSpreadConstraint();
		if (DEBUG)
			System.out.println("Gen UnderConstraint");
		drawrolls = new DrawRolls();
		for(int idex = 0;idex < 2 ;idex++){
			drawrolls.getD1000();  // give it a kick, don't know why it gets non-random.
			//System.out.println("next:"+drawrolls.getD1000());
		}

		this.draw = drawrolls.getD1000();
		System.out.println("<<<draw:"+this.draw+">>>");

		starspreadconstraint.genUnderConstraint(get_star_profile());
		List<ClusterRep> _list = starspreadconstraint.getListOfClusters();
		return _list;
	}

	/**
	 * test
	 */
	public static void main(String[] Argv) {
		Star star = new Star();
		List _star_list = star.genStars();
		if (DEBUG)
			System.out.println("<" + _star_list.size() + ">");
	}
}
