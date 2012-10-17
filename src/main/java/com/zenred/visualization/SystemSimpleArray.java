package com.zenred.visualization;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.StarRep;
@XStreamAlias("someDetails")
public class SystemSimpleArray {
	
	private String theMessage;
	private String _systemId;
	private String _distanceToGalaxyCentre;
	private String _ucoordinate;
	private String _vcoordinate;
	private List<List<?>> clusterRepList;
	private List<List<?>> starRepList;

/*	
 * Cluster indexes for JSON
 * 
	_angleInRadians: 4.642575810304916
	_clusterDescription: "Cluster Number: 0 FOURSTAR_SPREAD"
	_clusterId: "U99998V100002_0"
	_datestamp: Object
	_distanceSysVirtCentre: 68413793103.44827
	_numberStarsInCluster: 4
	_planetsAllowed: "F"
	_systemId: "U99998V100002"
*/
	private static final int ANGLE_IN_RADIANS = 0;
	private static final int SYSTEM_ID = 1;
	private static final int CLUSTER_DESCRIPTION = 2;
	private static final int DISTANCE_VIRTUAL_CENTRE = 3;
	private static final int NUMBER_STARS_IN_CLUSTER = 4;
	private static final int PLANETS_ALLOWED = 5;
	
	
	/**
	 * 
	 * _starId:252,"
	 * _systemId:"U99999V100000"
	 * _clusterId:"U99999V100000_0"
	 * _distanceClustVirtCentre:9.92E11
	 * _luminosity:2.2993541664570722
	 * _noPlanetsAllowed":"F"
	 * _angleInRadiansS":152
	 * _starColor":pyel.mains
	 * _starType:"f6
	 * _starSize:39.6}
	 */
	
	private static final int STAR_ID = 0;
	private static final int PARENT_SYSTEM_ID = 1;
	private static final int CLUSTER_ID = 2;
	private static final int DISTANCE_TO_CLUSTER_VIRT_CENTRE = 3;
	private static final int LUMINOSITY_ID = 4;
	private static final int NO_PLANETS_ALLOWED = 5;
	private static final int STAR_ANGLE_IN_RADIANS = 6;
	private static final int STAR_COLOR = 7;
	private static final int STAR_TYPE = 8;
	private static final int STAR_SIZE = 9;
	
	
	public SystemSimpleArray() {
		
	}
	
	public static SystemSimpleArray genSimpleArray(SystemPlusSomeDetails systemPlusSomeDetails){
		SystemSimpleArray systemsSimpleArray = new SystemSimpleArray();
		
		systemsSimpleArray.theMessage = systemPlusSomeDetails.getTheMessage();
		systemsSimpleArray._distanceToGalaxyCentre = systemPlusSomeDetails.get_distanceToGalaxyCentre();
		systemsSimpleArray._ucoordinate = systemPlusSomeDetails.get_ucoordinate();
		systemsSimpleArray._vcoordinate = systemPlusSomeDetails.get_vcoordinate();
		systemsSimpleArray._systemId = systemPlusSomeDetails.get_systemId();
		systemsSimpleArray.clusterRepList = new ArrayList<List<?>>();
		systemsSimpleArray.starRepList = new ArrayList<List<?>>();

		
		for (int index = 0; index < systemPlusSomeDetails.getClusterRepList().size(); index++ ){
			ClusterRep clusterRep = systemPlusSomeDetails.getClusterRepList().get(index);
			List<Object> aCluster = new ArrayList<Object>();
			aCluster.add(ANGLE_IN_RADIANS, clusterRep.getAngleInRadians());
			aCluster.add(SYSTEM_ID, clusterRep.get_systemId());
			aCluster.add(CLUSTER_DESCRIPTION, clusterRep.get_clusterDescription());
			aCluster.add(DISTANCE_VIRTUAL_CENTRE, clusterRep.get_numberStarsInCluster());
			aCluster.add(NUMBER_STARS_IN_CLUSTER, clusterRep.get_numberStarsInCluster());
			aCluster.add(PLANETS_ALLOWED, clusterRep.get_planetsAllowed());
			systemsSimpleArray.clusterRepList.add(aCluster);
		}
		
		for (int index = 0; index < systemPlusSomeDetails.getStarRepList().size(); index++){
			StarRep starRep = systemPlusSomeDetails.getStarRepList().get(index);
			List<Object> aStar = new ArrayList<Object>();
			aStar.add(STAR_ID, starRep.getStarId());
			aStar.add(PARENT_SYSTEM_ID, starRep.getSystemId());
			aStar.add(CLUSTER_ID, starRep.getClusterId());
			aStar.add(DISTANCE_TO_CLUSTER_VIRT_CENTRE, starRep.getDistanceClustVirtCentre());
			aStar.add(LUMINOSITY_ID, starRep.getLuminosity());
			aStar.add(NO_PLANETS_ALLOWED, starRep.getNoPlanetsAllowed());
			aStar.add(STAR_ANGLE_IN_RADIANS, starRep.getAngleInRadiansS());
			aStar.add(STAR_COLOR, starRep.getStarColor());
			aStar.add(STAR_TYPE, starRep.getStarType());
			aStar.add(STAR_SIZE, starRep.getStarSize());
			systemsSimpleArray.starRepList.add(aStar);
			}
		return systemsSimpleArray;
	}
	/**
	 * @return the _systemId
	 */
	public String get_systemId() {
		return _systemId;
	}
	public String getTheMessage() {
		return theMessage;
	}
	public void setTheMessage(String theMessage) {
		this.theMessage = theMessage;
	}
	/**
	 * @param systemId the _systemId to set
	 */
	public void set_systemId(String systemId) {
		_systemId = systemId;
	}
	/**
	 * @return the _distanceToGalaxyCentre
	 */
	public String get_distanceToGalaxyCentre() {
		return _distanceToGalaxyCentre;
	}
	/**
	 * @param distanceToGalaxyCentre the _distanceToGalaxyCentre to set
	 */
	public void set_distanceToGalaxyCentre(String distanceToGalaxyCentre) {
		_distanceToGalaxyCentre = distanceToGalaxyCentre;
	}
	/**
	 * @return the _ucoordinate
	 */
	public String get_ucoordinate() {
		return _ucoordinate;
	}
	/**
	 * @param ucoordinate the _ucoordinate to set
	 */
	public void set_ucoordinate(String ucoordinate) {
		_ucoordinate = ucoordinate;
	}
	/**
	 * @return the _vcoordinate
	 */
	public String get_vcoordinate() {
		return _vcoordinate;
	}
	/**
	 * @param vcoordinate the _vcoordinate to set
	 */
	public void set_vcoordinate(String vcoordinate) {
		_vcoordinate = vcoordinate;
	}
	
	public List<List<?>> getClusterRepList() {
		return clusterRepList;
	}
	public void setClusterRepList(List<List<?>> clusterRepList) {
		this.clusterRepList = clusterRepList;
	}
	public List<List<?>> getStarRepList() {
		return starRepList;
	}
	public void setStarRepList(List<List<?>> starRepList) {
		this.starRepList = starRepList;
	}

}
