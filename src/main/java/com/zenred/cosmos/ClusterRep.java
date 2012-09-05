package com.zenred.cosmos;
import com.zenred.util.OrderedArrayListCollection;
/**
 * ClusterRep.java
 *
 *
 * Created: Fri Apr 05 17:08:52 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class ClusterRep {

    private double distance_to_system_virt_centre =0.0;
	private boolean no_planets_allowed; // impossible in this system
	private String cluster_id = null;
    private String system_id = null;
	private double angle_in_radians = 0.0; // to system centre .... 
    private String cluster_descriptor;
    private int number_stars_in_cluster;

    private StarRep [] star_rep_set;
    private int starrep_cur = -1;

    public ClusterRep (){
        
    }
    public double getDistanceToVirtCentre(){return distance_to_system_virt_centre;}
    public boolean hasNoPlanets(){return no_planets_allowed;}
    public boolean arePlanetsInSystem(){return !no_planets_allowed;}
    public String getEnclosingClusterId(){ return cluster_id;}
    public String getEnclosingSystemId(){ return system_id;}
    public double getStartAngleFromVirtCentreInRadians(){return angle_in_radians;}
    public int getNumberStarsInSystem(){return star_rep_set.length;}
    public boolean areThereStarsInSystem(){return getNumberStarsInSystem() == 0? false : true;}
    public OrderedArrayListCollection getStarReps(){
        OrderedArrayListCollection _starlist = new OrderedArrayListCollection();
        for (int _idex = 0; _idex < star_rep_set.length; _idex++){
            _starlist.add(star_rep_set[_idex]);
        }
        return _starlist;
    }

    public void setDistanceToVirtCentre(double distance_to_system_virt_centre){ this.distance_to_system_virt_centre = distance_to_system_virt_centre;}

    public void setHasNoPlanets(boolean no_planets_allowed){ this.no_planets_allowed = no_planets_allowed;}

    public void setClusterId(String cluster_id){ this.cluster_id = cluster_id;}

    public void setSystemId(String system_id){ this.system_id = system_id;}

    public void setStartAngleFromVirtCentreInRadians(double angle_in_radians){ this.angle_in_radians = angle_in_radians; }

    public void setNumberStarsInCluster(int number_stars_in_cluster){ 
        this.number_stars_in_cluster = number_stars_in_cluster;
        star_rep_set = new StarRep[number_stars_in_cluster];
        for(int _idex = 0; _idex < number_stars_in_cluster; _idex++){
            star_rep_set[_idex] = new StarRep();
        }
    }

    public void setClusterDescriptor(String cluster_descriptor){
        this.cluster_descriptor = cluster_descriptor;
    }

    public StarRep getFirstStarInCluster(){
        resetCurrentOfStarCluster();
        return getNextStarInCluster(); 
    }

    public void resetCurrentOfStarCluster(){starrep_cur = 0;}

    public StarRep getNextStarInCluster(){
        return star_rep_set.length == starrep_cur? null : star_rep_set[starrep_cur++];
    }

    public double getClusterAngle(){return angle_in_radians;}

    public String getClusterDescriptor(){return cluster_descriptor;}

    private String getIterStarRepSet(){
        String _sr = "";
        for (int _idex = 0 ; _idex < star_rep_set.length ; _idex++){
            _sr +=  star_rep_set[_idex].toString();
        }
        return _sr;
    }


    public String toString(){
        return "::cluster distance_to_system_virt_centre:" + distance_to_system_virt_centre + 
            "::no_planets_allowed:" + no_planets_allowed +
            "::cluster_id:" + cluster_id + 
            "::system_id:" + system_id + 
            "::cluster angle_in_radians:" + angle_in_radians + 
            "::cluster_descriptor:" + cluster_descriptor + 
            "::getIterStarRepSet():" + getIterStarRepSet() +
            "::starrep_cur:" + starrep_cur +
            ":::";
    }

}// ClusterRep
