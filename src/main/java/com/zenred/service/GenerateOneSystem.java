package com.zenred.service;

import java.util.Iterator;
import java.util.List;

import com.zenred.cosmos.ClusterRep;
import com.zenred.cosmos.DrawRolls;
import com.zenred.cosmos.Star;
import com.zenred.cosmos.StarRep;
import com.zenred.cosmos.SystemDisplayNode;
import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.data_access.MarshallSystems;
import com.zenred.util.OrderedArrayListCollection;

import cosmos.hibernate.Planetoid;

public class GenerateOneSystem {

	private MarshallSystems marshallSystems;
	private MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem;
	private GeneratePlanets generatePlanets;

	public void setMarshallSystems(MarshallSystems marshallSystems) {
		this.marshallSystems = marshallSystems;
	}


	public void setMarshallClustersAndStarsAndPlanetsInOneSystem(
			MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem) {
		this.marshallClustersAndStarsAndPlanetsInOneSystem = marshallClustersAndStarsAndPlanetsInOneSystem;
	}

	public void setGeneratePlanets(GeneratePlanets generatePlanets) {
		this.generatePlanets = generatePlanets;
	}

	public boolean doesSystemAllReadyExist(String udim, String vdim) {
		boolean answer = false;
		// if(null == marshallSystems){marshallSystems = new MarshallSystems();}
		// //?
		String system = marshallSystems.getSystemRep(udim, vdim);
		if (null != system) {
			answer = true;
		}
		return answer;
	}

	public void generateSystem(String udim, String vdim) {
		SystemDisplayNode systemdisplaynode = new SystemDisplayNode();
		systemdisplaynode.setUdim(udim);
		systemdisplaynode.setVdim(vdim);
		systemdisplaynode.setStartUdim("0");
		systemdisplaynode.setStartVdim("0");
		systemdisplaynode.setMyUdim(new Integer(udim));
		systemdisplaynode.setMyVdim(new Integer(vdim));
		int density = DrawRolls.Instance().getD100();
		int draw = DrawRolls.Instance().getD100();
		String this_sector = ":" + udim + ":" + vdim + ":";
		if (draw > density) {
			System.out.println("star gen sched:" + this_sector);
			Star star = new Star();
			List<ClusterRep> _sl = star.genStars();
			System.out.println("add gen rep");
			systemdisplaynode.setStarRep(_sl);
			systemdisplaynode.setActive();

		}
		System.out.println("add node:" + systemdisplaynode.getKey() + ":"
				+ draw + ":" + density);
		marshallSystems.writeSystemRep(systemdisplaynode);

		if (systemdisplaynode.isItPassive()) {
			return;
		} // nothing here ... for now ...

		int count = 0;
		while (systemdisplaynode.isThereNext()) {
			String _cluster_name = systemdisplaynode.getSystemName() + "_"
					+ count;
			System.out.println("cluster count on emit:" + count + ":"
					+ systemdisplaynode.getSystemName());
			ClusterRep _clusterrep = null;
			if (0 == count) {
				_clusterrep = (ClusterRep) systemdisplaynode.getFirst();
			} else {
				_clusterrep = (ClusterRep) systemdisplaynode.getNext();
			}
			++count;
			double _d_dist_to_virt_centre = _clusterrep
					.getDistanceToVirtCentre();
			String _dist_to_virt_centre = "" + _d_dist_to_virt_centre;

			cosmos.hibernate.ClusterRep clusterep = new cosmos.hibernate.ClusterRep();
			clusterep.setAngleInRadians(new Double(_clusterrep
					.getClusterAngle()));
			clusterep.setClusterDescription(_clusterrep.getClusterDescriptor());
			clusterep.setClusterId(_cluster_name);
			Double distanceVirtCentre = new Double(
					_clusterrep.getDistanceToVirtCentre());
			clusterep.setDistanceSysVirtCentre(Double
					.isInfinite(distanceVirtCentre) ? 0.0 : distanceVirtCentre);
			clusterep.setNumberStarsInCluster((short) _clusterrep
					.getNumberStarsInSystem());
			clusterep.setSystemId(systemdisplaynode.getSystemName());
			String tOrF = _clusterrep.arePlanetsInSystem() ? "T" : "F";
			clusterep.setPlanetsAllowed(tOrF);
			marshallClustersAndStarsAndPlanetsInOneSystem
					.writeClusterRep(clusterep);

			if (_clusterrep.areThereStarsInSystem()) {
				emitTheStars(_clusterrep.getStarReps(),
						systemdisplaynode.getSystemName(), _cluster_name,
						_clusterrep.arePlanetsInSystem(), count);
			}
		}
	}

	public void emitTheStars(OrderedArrayListCollection starlist,
			String system_name, String cluster_name, boolean planetsAllowed, int clusterCount) {
		Iterator<StarRep> starRepIter = starlist.iterator();
		int starCount = 0;
		while (starRepIter.hasNext()) {
			StarRep _star_rep = starRepIter.next();
			double _dis = _star_rep.getDistanceToVirtCentre();
			Double _ddis = new Double(_dis);
			if (_ddis.isInfinite()) {
				_dis = 0.0;
			}
			cosmos.hibernate.StarRep starRep = new cosmos.hibernate.StarRep();
			starRep.setAngleInRadiansS(_star_rep.getStartAngleFromVirtCentreInRadians());
			starRep.setClusterId(cluster_name);
			Double distanceCentre = _star_rep.getDistanceToVirtCentre();
			if(distanceCentre.isInfinite()){
				distanceCentre = new Double(DrawRolls.Instance().getD1000());
			}
			starRep.setDistanceClustVirtCentre(distanceCentre);
			starRep.setLuminosity(_star_rep.getLuminosity());
			starRep.setNoPlanetsAllowed(_star_rep.hasNoPlanets());
			starRep.setStarColor(_star_rep.getStarColor());
			starRep.setStarSize(_star_rep.getStarSize());
			starRep.setStarType(_star_rep.getStarType());
			starRep.setSystemId(system_name);
			marshallClustersAndStarsAndPlanetsInOneSystem.writeStarRep(starRep);
			if(planetsAllowed){
				Constraint constraint = new Constraint();
				constraint.setType("NONE");
				generatePlanets.generateSomePlanets(constraint, starRep, starCount, clusterCount);
			}
			++starCount;
		}
		
	}
	
}
