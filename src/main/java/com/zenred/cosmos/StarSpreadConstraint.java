package com.zenred.cosmos;

import java.util.ArrayList;
import java.util.List;
import com.zenred.util.OrderedLinkedListCollection;
import com.zenred.util.AnonBlock;

public class StarSpreadConstraint implements InSystemConstraintsIF {

	private StarSpread starspread;
	private ArrayList list_of_angle_measures;
	private static final String CN = "Cluster Number: ";
	private static final boolean DEBUG = true;
	// private static final boolean DEBUG = false;

	List<ClusterRep> listofclusers;

	public interface _bunch_of_angles {
		public static final int MAXDIST = 0;
		public static final int THISANGLE = MAXDIST + 1;
		public static final int NEXTANGLE = THISANGLE + 1;
		public static final int COUNT = NEXTANGLE + 1;
		public static final double BOA[] = new double[COUNT];
	}

	/**
	 * construction
	 */
	public StarSpreadConstraint() {
	}

	/**
	 * @return the number of stars in a cluster, in most cases ... one.
	 */
	private int getNumberStarsInCluster(int cluster_number) {
		if (DEBUG)
			System.out.println("getNumber Clusters:" + cluster_number);
		if (0 == cluster_number) {
			return starspread.getNumberStarsInFirstCluster();
		} else {
			if (1 == cluster_number) {
				return starspread.getNumberStarsInSecondCluster();
			} else {
				return 1;
			}
		}
	}

	/**
	 * @return true if there are planets in cluster
	 */
	private boolean areThereNoPlanetsInCluster(int cluster_number) {
		return !areTherePlanetsInCluster(cluster_number);
	}

	private boolean areTherePlanetsInCluster(int cluster_number) {
		if (0 == cluster_number) {
			return starspread.doesFirstClusterHavePlanets();
		} else {
			if (1 == cluster_number) {
				return starspread.doesSecondClusterHavePlanets();
			} else {
				return false;
			}
		}
	}

	/**
	 * forces a random interval in the outer 50% for systems with a centre
	 * constraint
	 */
	private double getDistanceFromClusterCentreToSystemCentre() {
		double _dres = 0.0;
		if (starspread.getStarConfigHasClusterVirtCentreConstraint()) {
			_dres = InSystemConstraintsIF.HALFPSEC * 1.0
					/ ((double) DrawRolls.Instance().getDraw(50.0))
					+ (InSystemConstraintsIF.HALFPSEC / 2.0);
		} else {
			_dres = InSystemConstraintsIF.HALFPSEC
					/ ((double) DrawRolls.Instance().getDraw(99.0));
		}
		if (_dres == Double.POSITIVE_INFINITY
				|| _dres == Double.NEGATIVE_INFINITY) {
			_dres = 0.0;
		}
		return _dres;
	}

	/**
	 * for constraint bound: next_angle - cur_angle = positive direction 359 -
	 * 15 = 344 or 344 degrees away in the positive direction 15-359 = -344 ;
	 * 360 -344 = 16 or degrees in negative direction.
	 */
	private double getRandom360Angle(
			OrderedLinkedListCollection listofclusters, boolean constraint) {
		if (constraint) {
			list_of_angle_measures = new ArrayList();

			_bunch_of_angles _boa = new _bunch_of_angles() {
			};
			_boa.BOA[_bunch_of_angles.MAXDIST] = -1.0;
			_boa.BOA[_bunch_of_angles.THISANGLE] = 0.0;
			_boa.BOA[_bunch_of_angles.NEXTANGLE] = 0.0;

			iterList(listofclusters);
			Object[] _list_of_angle_measures = list_of_angle_measures.toArray();
			if (_list_of_angle_measures.length == 0) {
				return ((double) DrawRolls.Instance().getDraw(360.0));
			} else {
				for (int idex = 0; idex < _list_of_angle_measures.length; idex++) {
					int nextidex = (idex >= _list_of_angle_measures.length) ? 0
							: idex - 1;
					int previdex = (idex - 1 < 0) ? _list_of_angle_measures.length - 1
							: idex - 1;
					double cur_angle = ((Double) _list_of_angle_measures[idex])
							.doubleValue();
					double pre_angle = ((Double) _list_of_angle_measures[previdex])
							.doubleValue();
					nextidex = (nextidex < 0) ? 0 : nextidex;
					System.out.println(":nextidex:" + nextidex + ":" + idex
							+ ":" + _list_of_angle_measures.length + ":");

					/* fail here */double next_angle = ((Double) _list_of_angle_measures[nextidex])
							.doubleValue();
					double positive_direction = next_angle - cur_angle;
					positive_direction = positive_direction < 0 ? 360.0 - positive_direction
							: positive_direction;

					double negative_direction = cur_angle - pre_angle;
					negative_direction = negative_direction < 0 ? 360.0 - negative_direction
							: negative_direction;

					if (positive_direction > _boa.BOA[_bunch_of_angles.MAXDIST]) {
						_boa.BOA[_bunch_of_angles.MAXDIST] = positive_direction;
						_boa.BOA[_bunch_of_angles.THISANGLE] = cur_angle;
						_boa.BOA[_bunch_of_angles.NEXTANGLE] = next_angle;
					} else if (negative_direction > _boa.BOA[_bunch_of_angles.MAXDIST]) {
						_boa.BOA[_bunch_of_angles.MAXDIST] = negative_direction;
						_boa.BOA[_bunch_of_angles.THISANGLE] = pre_angle;
						_boa.BOA[_bunch_of_angles.NEXTANGLE] = cur_angle;
					}
				}
				double _draw = _boa.BOA[_bunch_of_angles.THISANGLE]
						+ ((double) DrawRolls.Instance().getDraw(
								_boa.BOA[_bunch_of_angles.MAXDIST] * 0.9));
				return _draw > 360.0 ? _draw - 360.0 : _draw;
			}
		} else {
			return ((double) DrawRolls.Instance().getDraw(360.0));
		}
	}

	private double getRandom360Angle(List<ClusterRep> listofclusters,
			boolean constraint) {
		if (constraint) {
			list_of_angle_measures = new ArrayList();

			_bunch_of_angles _boa = new _bunch_of_angles() {
			};
			_boa.BOA[_bunch_of_angles.MAXDIST] = -1.0;
			_boa.BOA[_bunch_of_angles.THISANGLE] = 0.0;
			_boa.BOA[_bunch_of_angles.NEXTANGLE] = 0.0;

			iterListCluster(listofclusters);
			Object[] _list_of_angle_measures = list_of_angle_measures.toArray();
			if (_list_of_angle_measures.length == 0) {
				return ((double) DrawRolls.Instance().getDraw(360.0));
			} else {
				for (int idex = 0; idex < _list_of_angle_measures.length; idex++) {
					int nextidex = (idex >= _list_of_angle_measures.length) ? 0
							: idex - 1;
					int previdex = (idex - 1 < 0) ? _list_of_angle_measures.length - 1
							: idex - 1;
					double cur_angle = ((Double) _list_of_angle_measures[idex])
							.doubleValue();
					double pre_angle = ((Double) _list_of_angle_measures[previdex])
							.doubleValue();
					nextidex = (nextidex < 0) ? 0 : nextidex;
					System.out.println(":nextidex:" + nextidex + ":" + idex
							+ ":" + _list_of_angle_measures.length + ":");

					/* fail here */double next_angle = ((Double) _list_of_angle_measures[nextidex])
							.doubleValue();
					double positive_direction = next_angle - cur_angle;
					positive_direction = positive_direction < 0 ? 360.0 - positive_direction
							: positive_direction;

					double negative_direction = cur_angle - pre_angle;
					negative_direction = negative_direction < 0 ? 360.0 - negative_direction
							: negative_direction;

					if (positive_direction > _boa.BOA[_bunch_of_angles.MAXDIST]) {
						_boa.BOA[_bunch_of_angles.MAXDIST] = positive_direction;
						_boa.BOA[_bunch_of_angles.THISANGLE] = cur_angle;
						_boa.BOA[_bunch_of_angles.NEXTANGLE] = next_angle;
					} else if (negative_direction > _boa.BOA[_bunch_of_angles.MAXDIST]) {
						_boa.BOA[_bunch_of_angles.MAXDIST] = negative_direction;
						_boa.BOA[_bunch_of_angles.THISANGLE] = pre_angle;
						_boa.BOA[_bunch_of_angles.NEXTANGLE] = cur_angle;
					}
				}
				double _draw = _boa.BOA[_bunch_of_angles.THISANGLE]
						+ ((double) DrawRolls.Instance().getDraw(
								_boa.BOA[_bunch_of_angles.MAXDIST] * 0.9));
				return _draw > 360.0 ? _draw - 360.0 : _draw;
			}
		} else {
			return ((double) DrawRolls.Instance().getDraw(360.0));
		}
	}

	/**
	 * operator iterates through ordered list collection
	 */
	private void iterList(OrderedLinkedListCollection listofclusters) {
		listofclusters.forEachDo(new AnonBlock() {
			public void exec(Object each) {
				nextMeasure(each);
			}
		});
	}
	
	private void iterListCluster(List<ClusterRep> list){
		for(ClusterRep clusterrep : list){
			nextMeasure(clusterrep);
		}
	}

	/**
     * 
     */
	private void nextMeasure(Object clusterrep) {
		list_of_angle_measures.add(new Double(((ClusterRep) clusterrep)
				.getClusterAngle()));
	}

	/**
	 * generates clusters and stars within the clusters
	 */
	public void genUnderConstraint(StarSpread starspread) {

		if (DEBUG)
			System.out.println("starspread:" + starspread.toString());

		listofclusers = new ArrayList<ClusterRep>();
		this.starspread = starspread;
		if (DEBUG)
			System.out.println("starspread.getNumberClusters():"
					+ starspread.getNumberClusters() + ":");

		for (int _idex = 0; _idex < starspread.getNumberClusters(); _idex++) {
			ClusterRep _clusterrep = new ClusterRep();
			if (DEBUG)
				System.out.println("Start ClusterRep setters._idex" + _idex + ":");
			_clusterrep.setNumberStarsInCluster(getNumberStarsInCluster(_idex));
			if (DEBUG)
				System.out.println("Set Clusster distance from Virt Centre");
			_clusterrep
					.setDistanceToVirtCentre(getDistanceFromClusterCentreToSystemCentre());
			_clusterrep.setHasNoPlanets(areThereNoPlanetsInCluster(_idex));
			_clusterrep.setClusterDescriptor(CN + _idex + " "
					+ starspread.getSpreadDescriptor());
			if (DEBUG)
				System.out.println("Cluster Angle from Virt Centre");
			_clusterrep.setStartAngleFromVirtCentreInRadians(Math
					.toRadians(getRandom360Angle(listofclusers, starspread
							.getStarConfigHasClusterVirtCentreConstraint())));
			if (DEBUG)
				System.out.println(_clusterrep.toString());
			listofclusers.add(_clusterrep);

		}

	}

	public String toString() {
		return "starspread:" + starspread.toString()
				+ " list_of_angle_measures:"
				+ list_of_angle_measures.toString();
	}

	
	/**
	 * get List of clusters
	 * 
	 * @return list
	 */
	public List<ClusterRep> getListOfClusters() {
		return listofclusers;
	}
}
