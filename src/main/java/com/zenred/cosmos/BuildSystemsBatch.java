package com.zenred.cosmos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import com.zenred.util.OrderedArrayListCollection;

/*
 * BuildSystemsBatch.java
 *
 * Created on April 6, 2001, 8:59 PM
 */



/**
 *
 * @author  jredden
 * @version 
 */
public class BuildSystemsBatch  {
    
    private String  no_systems_u;
    private String no_systems_v;
    private String start_u;
    private String start_v;
    private String label_start_u;
    private String label_start_v;


    private Star star; 
    private static int density;
    private DrawRolls drawrolls = new DrawRolls();
    private Map<String, SystemDisplayNode> totaldraw = new HashMap<String,SystemDisplayNode>();
    SystemDisplayNode systemdisplaynodetop = null;
    
    /**
     *
     */
    public void setNumberSystemsAndCoordinates(String [] argv){

        no_systems_u = argv[0];
        no_systems_v = argv[1];
        start_u = argv[2];
        start_v = argv[3];
        density = Integer.parseInt(argv[4]);
    }
    
    
    /** Creates new form BuildSystemsBatch */
    public BuildSystemsBatch() {
    }   
    
    public void actionPerformed(){

        systemdisplaynodetop = new SystemDisplayNode();
        systemdisplaynodetop.setUdim(no_systems_u);
        systemdisplaynodetop.setVdim(no_systems_v);
            
        for (int idex = 0; idex < systemdisplaynodetop.getUdim(); idex++){
            for (int idex2 = 0; idex2 < systemdisplaynodetop.getVdim(); idex2++){

                SystemDisplayNode systemdisplaynode = new SystemDisplayNode();
                systemdisplaynode.setUdim(no_systems_u);
                systemdisplaynode.setVdim(no_systems_v);
                systemdisplaynode.setStartUdim(start_u);
                systemdisplaynode.setStartVdim(start_v);
                systemdisplaynode.setMyUdim( idex );
                systemdisplaynode.setMyVdim( idex2 );
                
                String this_sector = ":" + idex + ":" + idex2 + ":";
                if(drawrolls.Instance().getD100() > density){
                    System.out.println("star gen sched:" + this_sector);
                    star = new Star();
                    List<ClusterRep> _sl = star.genStars();
                    System.out.println("add gen rep");
                    systemdisplaynode.setStarRep(_sl);
                    systemdisplaynode.setActive();    

                    }
                System.out.println("add node:"+systemdisplaynode.getKey()+":");
                totaldraw.put(systemdisplaynode.getKey(), 
                                  systemdisplaynode);
                }
            }
        

        Collection _collectionview = totaldraw.values();
        List<SystemDisplayNode>worktotaldraw = new ArrayList<SystemDisplayNode>();
        worktotaldraw.addAll(_collectionview);
        EmitSql emitsql =  new EmitSql();
        emitsql.setDensity(density + "");
        emitsql.setU_coord(start_u);
        emitsql.setV_coord(start_v);
        emitsql.setU_count(no_systems_u);
        emitsql.setV_count(no_systems_v);
        emitsql.emitStarSys(worktotaldraw);
        emitsql.stopPrintWriter();
    }
    public static void main(String [] argv){
        BuildSystemsBatch bs = new BuildSystemsBatch();
        bs.setNumberSystemsAndCoordinates(argv);
        bs.actionPerformed();
        System.out.println("BuildSystemsBatch finished ... ");
    }
}
