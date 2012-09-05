package com.zenred.cosmos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;

public class BuildSystemsMatrix extends javax.swing.JFrame implements Runnable {

    // private static final boolean DEBUG = true;
    private static final boolean DEBUG = false;
    private GridLayout m_grid;
    private Container container;
    private static int Udim = 20;
    private static int Vdim = 10;

    private DrawRolls drawrolls = new DrawRolls();
    private static int density = 35; 

    private ArrayList _marker = new ArrayList();

    public void run(){
	container = this.getContentPane();
	m_grid = new GridLayout(Udim, Vdim);
	container.setLayout(m_grid);
	setSize(400, 300);
	for (int idex = 0; idex < Udim; idex++){
	    for (int idex2 = 0; idex2 < Vdim; idex2++){
            String this_sector = ":" + idex + ":" + idex2 + ":";
            if(drawrolls.Instance().getD100() > density){
                if(DEBUG)System.out.println("star gen sched:" + this_sector);
                _marker.add(new Boolean(true));
                
            }
            else{
                _marker.add(new Boolean(false));
            }
            container.add(new JTextField(this_sector));
                
	    }
	}
	show();
    /*
    for (int idex3 = 0; idex3 < _marker.size(); idex3++){
        Boolean _bl = (Boolean)_marker.get(idex3);
        if(_bl.booleanValue()){
            List _sl = star.genStars();
        }    
    }
    */
    
    }

    public BuildSystemsMatrix(String s_u, String s_v){
	super();
    Udim = new Integer(s_u.trim()).intValue();
    Vdim = new Integer(s_v.trim()).intValue();
    }

}
