package com.zenred.cosmos;
/**
 * EmitSqlIF.java
 *
 *
 * Created: Wed Aug 14 07:00:39 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public interface EmitSqlIF {

    public static final String INSERT_SYSTEMREP = "INSERT INTO SystemRep" + 
        " (System_id, distance_to_galaxy_centre, ucoordinate, vcoordinate)" +
        " VALUES ({0}, {1}, {2}, {3});";

    public static final String INSERT_STARREP = "INSERT INTO StarRep" + 
        " (" +
        "System_id," +
        "cluster_id," +
        "distance_clust_virt_centre," +
        "luminosity," +
        "no_planets_allowed," +
        "angle_in_radians_s," +
        "star_color," +
        "star_type," +
        "star_size" +
        ") " +
        " VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8});";
    
    public static final String INSERT_CLUSTERREP = "INSERT INTO ClusterRep" + 
        " (" +
        "System_id," +
        "distance_sys_virt_centre," +
        "planetsAllowed," +
        "cluster_id," +
        "angle_in_radians," +
        "cluster_description," +
        "number_stars_in_cluster" +
        ") " +
        " VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6});";
    
    public static final String INSERT_PLANETOID_REP = "INSERT INTO PlanetoidRep" +
    " (" +
    "System_id" +
    ",cluster_id" +
    ",planetoid_id" +
    ") " +
    " VALUES ( {0}, {1}, {2});"
    	;
    
    public static final String INSERT_PLANETOID= "INSERT INTO Planetoid" +
        " (" +
        "planetoid_id" +
        ",Radius" +
        ",DistanceToPimary" +
        ",Degree" +
        ",Temperature" +
        ",PercentWater" +
        ",Period" +
        ",DistanceAUs" +
        ") " +
        " VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7} );"
   	;
    
    public static final String INSERT_PLANETOID_ATMOSPHERE = "INSERT INTO PlanetoidAtmosphere" +
        " (" +
        "planetoid_id" +
        ",chem_name" +
        ",percentage" +
        ") " +
        " VALUES ({0}, {1}, {2});"
    	;
        
    
}// EmitSqlIF
