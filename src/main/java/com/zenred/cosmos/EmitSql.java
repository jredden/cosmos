package com.zenred.cosmos;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.List;

import com.zenred.util.OrderedArrayListCollection;
import com.zenred.util.OrderedListCollection;
import com.zenred.util.AnonBlock;


/**
 * EmitSql.java
 *
 *
 * Created: Mon Aug 12 07:51:00 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class EmitSql implements EmitSqlIF{

    private static boolean DEBUG = true;
    // private static boolean DEBUG = false;
    private static final String SQLFILE = "load_starsys";
    private static final String INFINITY = "Infinity";
    private String u_coord = "";
    private String v_coord = "";
    private String u_count = "";
    private String v_count = "";
    private String density = "";

    private FileWriter sqlfile = null;
    private PrintWriter printwriter = null;
    private EmitSqlStars emitsqlstars = new EmitSqlStars();
    private int count;

    public EmitSql (){   
    }
    
     public static String getSqlGo(){return "\n";}

    public void establishFileWriter(){
        if(sqlfile == null){
        	String _file_name = SQLFILE + "_"+ u_coord + "_"+ v_coord + "_"+ u_count + "_"+ v_count + "_"+ density + ".sql";
            try{
                new File(_file_name).createNewFile();
                sqlfile = new FileWriter(_file_name);
                printwriter = new PrintWriter(sqlfile);
            }
            catch (IOException ioe){
                System.err.println(ioe.toString());
            }
        }
    }

    public void stopPrintWriter(){
        printwriter.flush();
        printwriter.close();
         sqlfile = null;
    }

    private void emitSqlToFile(SystemDisplayNode systemdisplaynode){
        String _system_name = systemdisplaynode.getSystemName();
        establishFileWriter();
        String _insert_system_rep = MessageFormat.format(INSERT_SYSTEMREP,
                                                         new Object[] {
                                                             "'" + _system_name + "'", 
                                                             ""+systemdisplaynode.getDistanceToGalacticCentre(),
                                                             ""+systemdisplaynode.getUCoord(),
                                                             ""+systemdisplaynode.getVCoord()}
                                                         );
        printwriter.print(_insert_system_rep + "\n");
        printwriter.print(getSqlGo() + "\n");

        if(systemdisplaynode.isItPassive()){return;}

        String _insert_cluster_rep = "";

		count = 0;
		while (systemdisplaynode.isThereNext()) {
			String _cluster_name = _system_name + "_" + count;
			System.out.println("cluster count on emit:"+count+":"+_system_name);
			ClusterRep _clusterrep = null;
			if (0 == count) {
				_clusterrep = (ClusterRep) systemdisplaynode.getFirst();
			} else {
				_clusterrep = (ClusterRep) systemdisplaynode.getNext();
			}
			++count;
	    double _d_dist_to_virt_centre = _clusterrep.getDistanceToVirtCentre();
            String _dist_to_virt_centre = "" + _d_dist_to_virt_centre;
            _insert_cluster_rep = MessageFormat.format(INSERT_CLUSTERREP,
                                                              new Object[] {
                                                             "'" + _system_name + "'", 
                                                             Double.isInfinite(_d_dist_to_virt_centre)? "0.0": _dist_to_virt_centre,
                                                             "'" +( _clusterrep.arePlanetsInSystem()? "T" : "F") + "'",
                                                             "'" + _cluster_name + "'", 
                                                             "" + _clusterrep.getStartAngleFromVirtCentreInRadians(),
                                                             "'" + _clusterrep.getClusterDescriptor()+ "'",
                                                             "" + _clusterrep.getNumberStarsInSystem()}
                                                              );
	    
	    _insert_cluster_rep =  _insert_cluster_rep.replaceAll(
								  INFINITY,
								  new Double(Double.MAX_VALUE).doubleValue()+""
								  );
	    
            if ( _clusterrep.areThereStarsInSystem()){
                emitsqlstars.emitTheStars(_clusterrep.getStarReps(), 
                                          printwriter,
                                          _system_name,
                                          _cluster_name); 
            }

            printwriter.print(_insert_cluster_rep + "\n");
            
                                                             
        }
        printwriter.print(getSqlGo() + "\n");
        
        
    }

    /**
     *
     */
    public void emitStarSys(List<SystemDisplayNode>starcollection){
    	for(SystemDisplayNode node : starcollection){
    		nextStarEmit(node);
    	}
    }

    private void nextStarEmit(SystemDisplayNode systemdisplaynode){
        if(DEBUG)System.out.println(((SystemDisplayNode)systemdisplaynode).toString());
        emitSqlToFile((SystemDisplayNode)systemdisplaynode);
    }

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getU_coord() {
		return u_coord;
	}

	public void setU_coord(String u_coord) {
		this.u_coord = u_coord;
	}

	public String getU_count() {
		return u_count;
	}

	public void setU_count(String u_count) {
		this.u_count = u_count;
	}

	public String getV_coord() {
		return v_coord;
	}

	public void setV_coord(String v_coord) {
		this.v_coord = v_coord;
	}

	public String getV_count() {
		return v_count;
	}

	public void setV_count(String v_count) {
		this.v_count = v_count;
	}
}// EmitSql
