package com.zenred.cosmos;
import com.zenred.util.OrderedListCollection;

public class StarSpread implements InSystemConstraintsIF{

    /*
     * defines a star draw
     */

    private int min;  // minimum range on random draw 
    private int max;	//max range on random draw
    private String spread_descriptor;
    private int number_stars;
    private int number_clusters;
    private int star_count_cluster1;
    private int star_count_cluster2;
    private boolean has_planets_1;
    private boolean has_planets_2;
    private boolean cluster_to_centre_constraint;

    private OrderedListCollection star_distances = null;

    public StarSpread(int number_stars, int max, int min, String descriptor){
	this.number_stars = number_stars;
	this.min = min;
	this.max = max;
	this.spread_descriptor = descriptor;
	
	star_count_cluster1 = StarSpreadProfiles.getStarConfigFirstCount(descriptor);
	star_count_cluster2 = StarSpreadProfiles.getStarConfigSecondCount(descriptor);
	has_planets_1 = StarSpreadProfiles.getStarConfigHasPlanetsOne(descriptor);
	has_planets_2 = StarSpreadProfiles.getStarConfigHasPlanetsTwo(descriptor);
    cluster_to_centre_constraint = StarSpreadProfiles.getStarConfigHasClusterVirtCentreConstrint(descriptor);
	number_clusters = star_count_cluster1 + star_count_cluster2;
    }

    /**
     * returns min range for draw out of number between 1 and 1000
     */ 
    public int getMinRangeOnDraw(){
	return min;
    }

    /**
     * returns max range for draw out of number between 1 and 1000
     */ 
     public int getMaxRangeOnDraw(){
	return max;
    }

    public int getNumberStars(){
	return number_stars;
    }

    public String getSpreadDescriptor(){
	return spread_descriptor;
    }
    
    public int getNumberClusters(){
	return number_clusters;
    }

    public int getNumberStarsInFirstCluster(){
        return star_count_cluster1;
    }
    public int getNumberStarsInSecondCluster(){
        return star_count_cluster2;
    }

    public boolean doesFirstClusterHavePlanets(){
        return has_planets_1;
    }   

    public boolean doesSecondClusterHavePlanets(){
        return has_planets_2;
    }   

    public boolean getStarConfigHasClusterVirtCentreConstraint(){
        return cluster_to_centre_constraint;
    }

    /**
     * given this profile, draw dist of star to Galaxy centre
     */
    public double getDistToGalaticCentre(){
        return 0.0;
    }

    public String toString(){
        return "min:" + min + 
            " max:" + max +
            " spread_descriptor:" + spread_descriptor +
            " number_stars:" + number_stars +
            " number_clusters:" + number_clusters +
            " star_count_cluster1:" + star_count_cluster1 +
            " star_count_cluster2:" + star_count_cluster2 +
            " has_planets_1:" + has_planets_1 +
            " has_planets_2:" + has_planets_2 +
            " cluster_to_centre_constraint:" + cluster_to_centre_constraint +
            " star_distances:" + star_distances +
            ":";
    }
}
