package com.zenred.cosmos;

/**
 * StarRep.java
 *
 *
 * Created: Wed Apr 03 08:59:40 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class StarRep {

    private double distance_to_cluster_virt_centre =0.0;
    private double luminosity = 0.0;
	private boolean no_planets_allowed; // impossible in this system
	private String cluster_id = null;
	private double angle_in_radians = 0.0; // to cluster centre ... 
    private String star_color;
    private String star_type;
    private StarAtributes staratributes;
    private double star_size = 0.0;

    public StarRep (){
        staratributes = new StarAtributes();
        staratributes.genStarAtributes();
        setLuminosity(staratributes.getStarLuminosity());
        setStarColor(staratributes.getStarColor());
        setStarType(staratributes.getStarType());
        setStartAngleFromVirtCentreInRadians(staratributes.getAngle());
        setDistanceToVirtCentre(staratributes.getDistanceFromStarCentreToClusterCentre());
        setStarSize(staratributes.getStarSize());
    }

    public double getDistanceToVirtCentre(){return distance_to_cluster_virt_centre;}
    public double getLuminosity(){return luminosity;}
    public String hasNoPlanets(){return no_planets_allowed? "T": "F";}
    public String getEnclosingClusterId(){ return cluster_id;}
    public double getStartAngleFromVirtCentreInRadians(){return angle_in_radians;}
    public String getStarColor(){return star_color;}
    public String getStarType(){return star_type;}
    public double getStarSize(){return star_size;}

    public void setDistanceToVirtCentre(double distance_to_cluster_virt_centre){ this.distance_to_cluster_virt_centre = distance_to_cluster_virt_centre;}

    private void setLuminosity(double luminosity){ this.luminosity = luminosity;}
    private void setStarColor(String star_color){ this.star_color = star_color;}
    private void setStarType(String star_type){ this.star_type = star_type;}

    public void setHasNoPlanets(boolean no_planets_allowed){ this.no_planets_allowed = no_planets_allowed;}

    public void setClusterId(String cluster_id){ this.cluster_id = cluster_id;}

    public void setStartAngleFromVirtCentreInRadians(double angle_in_radians){ this.angle_in_radians = angle_in_radians; }
    public void setStarSize(double star_size){this.star_size = star_size;}

    public String toString(){
        return "staratributes<" + staratributes.toString() + 
            " star_type:" + star_type +
            " star_color:" + star_color + 
            " angle_in_radians:" + angle_in_radians + 
            " star_size:" + star_size + 
            " cluster_id:" + cluster_id + 
            " no_planets_allowed:" + no_planets_allowed + 
            " luminosity:" + luminosity +
            " distance_to_cluster_virt_centre:" + distance_to_cluster_virt_centre + ">";
    }
}// StarRep
