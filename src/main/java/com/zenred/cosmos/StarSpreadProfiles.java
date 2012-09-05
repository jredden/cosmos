package com.zenred.cosmos;
import java.util.HashMap;

public class StarSpreadProfiles{
    
    private static HashMap ssprofs = new HashMap();
    private static StarSpreadProfiles  starspreadprofiles = null; 
    public  class ClusterCounts{
	private String descriptor;
	private int first_count;
	private int second_count;
	private boolean hasplanets_one;
	private boolean hasplanets_two;
        private boolean cluster_to_centre_constraint;

	public ClusterCounts(String descriptor, 
                         int first_count,
                         int second_found,
                         boolean hasplanets_one,
                         boolean hasplanets_two
                         ){
	    this(descriptor, 
             first_count,
             second_found,
             hasplanets_one,
             hasplanets_two,
             false);
	}
	public ClusterCounts(String descriptor, 
                         int first_count,
                         int second_found,
                         boolean hasplanets_one,
                         boolean hasplanets_two,
                         boolean cluster_to_centre_constraint
                         ){
	    this.descriptor = descriptor;
	    this.first_count = first_count;
	    this.second_count = second_count;
	    this.hasplanets_one = hasplanets_one;
	    this.hasplanets_two = hasplanets_two;
        this.cluster_to_centre_constraint = cluster_to_centre_constraint;
	}
	
    }

    private StarSpreadProfiles(){
	ClusterCounts _clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.ISINGLESTAR], 1, 0, true, false);
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.ISINGLESTAR], 
		    _clustercounts);
	_clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IDOUBLESTAR_BINARY], 2, 0, true, false);
    
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IDOUBLESTAR_BINARY], 
		    _clustercounts);
	_clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IDOUBLESTAR_SPREAD], 1, 1, true, true, true);
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IDOUBLESTAR_SPREAD], 
		    _clustercounts);
	_clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.ITHREESTAR_TRINARY], 3, 0, false, false);
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.ITHREESTAR_TRINARY], 
		    _clustercounts);
	_clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.ITHREESTAR_BINARYPLUSONE], 2, 1, true, true, true);
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.ITHREESTAR_BINARYPLUSONE], 
		    _clustercounts);

	_clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.ITHREESTAR_SPREAD], 2, 1, true, true);
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.ITHREESTAR_SPREAD], 
		    _clustercounts);

	_clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IFOURSTAR_TRINARYPLUSONE], 3, 1, false, true, true);
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IFOURSTAR_TRINARYPLUSONE], 
		    _clustercounts);
	_clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IFOURSTAR_2BINARIES], 2, 2, true, true, true);
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IFOURSTAR_2BINARIES], 
		    _clustercounts);
	_clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IFOURSTAR_SPREAD], 4, 0, false, false);
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IFOURSTAR_SPREAD], 
		    _clustercounts);
	_clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IFIVESTAR_FOURSTARSPREADPLUSONE], 4, 1, false, true, true);
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IFIVESTAR_FOURSTARSPREADPLUSONE], 
		    _clustercounts);
	
	_clustercounts = new ClusterCounts(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IFIVESTAR_SPREAD], 5, 0, false, false);
	ssprofs.put(InSystemConstraintsIF.STARCONFIGS[InSystemConstraintsIF.IFIVESTAR_SPREAD], 
		    _clustercounts);
	
    }

    private static HashMap getStarSpreadProfiles(){
	if(starspreadprofiles == null){
	    starspreadprofiles = new StarSpreadProfiles();
	}
	
	return ssprofs;
    }

    public static String getStarConfigDescriptor(String key){
	ClusterCounts _clustercounts = (ClusterCounts)getStarSpreadProfiles().get(key);
	return _clustercounts.descriptor;
    }
    public static int getStarConfigFirstCount(String key){
	ClusterCounts _clustercounts = (ClusterCounts)getStarSpreadProfiles().get(key);
	return _clustercounts.first_count;
    }
    public static int getStarConfigSecondCount(String key){
	ClusterCounts _clustercounts = (ClusterCounts)getStarSpreadProfiles().get(key);
	return _clustercounts.second_count;
    }
    public static boolean getStarConfigHasPlanetsOne(String key){
	ClusterCounts _clustercounts = (ClusterCounts)getStarSpreadProfiles().get(key);
	return _clustercounts.hasplanets_one;
    }
    public static boolean getStarConfigHasPlanetsTwo(String key){
	ClusterCounts _clustercounts = (ClusterCounts)getStarSpreadProfiles().get(key);
	return _clustercounts.hasplanets_two;
    }

    public static boolean getStarConfigHasClusterVirtCentreConstrint(String key){
        ClusterCounts _clustercounts = (ClusterCounts)getStarSpreadProfiles().get(key);
        return _clustercounts.cluster_to_centre_constraint;
    }
}
