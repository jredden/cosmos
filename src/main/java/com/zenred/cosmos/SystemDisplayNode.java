package com.zenred.cosmos;
import java.util.List;
/**
 * SystemDisplayNode.java
 *
 *
 * Created: Tue Jul 30 07:07:23 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class SystemDisplayNode implements InSystemConstraintsIF, SystemRepIF {

    private int Udim;
    private int Vdim;
    private int startUdim;
    private int startVdim;
    private int my_udim;
    private int my_vdim;
    private List<ClusterRep> star_rep;  // named incorrectly
    private int star_rep_size;
    private int list_idex;
    private boolean active;
    private int system_u_coord;
    private int system_v_coord;

    private static boolean DEBUG = true;
    // private static boolean DEBUG = false;

    public SystemDisplayNode (){
        active = false;
    }

    public boolean isItActive(){return active;}
    public boolean isItPassive(){return !active;}
    public void setActive(){active = true;}

    public void setUdim(String sudim){
        Udim = new Integer(sudim.trim()).intValue();
    }

    public void setVdim(String svdim){
        Vdim = new Integer(svdim.trim()).intValue();
    }
    public void setStartUdim(String sudim){
        startUdim = new Integer(sudim.trim()).intValue();

    }

    public void setStartVdim(String svdim){
        startVdim = new Integer(svdim.trim()).intValue();
    }

    public String getKey(){return "" + my_udim + ":" + my_vdim;}

    public void setMyUdim(int my_udim){
        this.my_udim = my_udim;
        system_u_coord = startUdim + my_udim;
    }

    public void setMyVdim(int my_vdim){
        this.my_vdim = my_vdim;
        system_v_coord = startVdim + my_vdim;
    }

    public int getUdim(){return Udim;}
    public int getVdim(){return Vdim;}
    public int getMyUdim(){return my_udim;}
    public int getMyVdim(){return my_vdim;}
    public double getUCoord(){return system_u_coord;}
    public double getVCoord(){return system_v_coord;}

    public void setStarRep(List<ClusterRep> star_rep){
        this.star_rep = star_rep;
        star_rep_size = star_rep == null ? 0 : star_rep.size();
        System.out.println("cluster_rep_size:"+star_rep_size+":");
        toFirst();
    }

    public void toFirst(){list_idex = 0;}

    public ClusterRep getFirst(){
        toFirst();
        return getNext();
    }

    public ClusterRep getNext(){
        return star_rep.get(list_idex++);
    }

    public boolean isThereNext(){return list_idex < star_rep.size();}

    public String getSystemName(){return "U" + system_u_coord + "V" + system_v_coord;}

    public double getDistanceToGalacticCentreU(){
        double _dret = -1;
        try{
            _dret = Double.parseDouble("" + system_u_coord) * PARSEC;
        }
        catch(NumberFormatException nfe){
            System.err.println(nfe.getMessage());
        }
        return _dret;
    }
    public double getDistanceToGalacticCentreV(){
        double _dret = -1;
         try{
            _dret = Double.parseDouble("" + system_v_coord) * PARSEC;
        }
        catch(NumberFormatException nfe){
            System.err.println(nfe.getMessage());
        }
        return _dret;
    }

    public double getDistanceToGalacticCentre(){
        double _u2 = getDistanceToGalacticCentreU();
        double _v2 = getDistanceToGalacticCentreV();
        _u2 *= _u2;
        _v2 *= _v2;
        return StrictMath.pow(_u2 + _v2, 0.5);
    }

    public String toString(){
        String _star_rep = star_rep == null ? "NULL" : star_rep.toString();
        String _sres = ":" + getSystemName() + 
                             "Udim:" +  Udim  +
                                "Vdim:" +  Vdim  +    
                                "startUdim:" +  startUdim  +
                                "startVdim:" +  startVdim  +
                                 "my_udim:" +  my_udim  +
                                 "my_vdim:" +  my_vdim  +
                                 "_star_rep:" +  _star_rep + "\n" +
                                "list_idex:" + list_idex + 
                                "active:" + active + ":";
        return _sres;
    }
}// SystemDisplayNode
