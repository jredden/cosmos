package com.zenred.cosmos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Collection;
import com.zenred.util.OrderedArrayListCollection;

/*
 * BuildSystems.java
 *
 * Created on April 6, 2001, 8:59 PM
 */



/**
 *
 * @author  jredden
 * @version 
 */
public class BuildSystems extends javax.swing.JFrame {
    
    private Container container;
    private JTextField no_systems_u;
    private JTextField no_systems_v;
    private JTextField start_u;
    private JTextField start_v;
    private JLabel label_start_u;
    private JLabel label_start_v;

    private JButton go_now;
    private static boolean single_copy = false;
    private BuildSystemsMatrix buildsystemsmatrix = null;

    private Star star = new Star();
    private static int density = 35;
    private DrawRolls drawrolls = new DrawRolls();
    private Hashtable totaldraw = new Hashtable();
    SystemDisplayNode systemdisplaynodetop = null;
    
    /**
     *
     */
    public void _init(){
        
        if(single_copy)return; // only to this once.
        single_copy = true;
        System.out.println("in init");
        
        // handle of applets content pane
        container = this.getContentPane();
        
        // vertical box layout
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        
        no_systems_u = new JTextField("20", 10);
        no_systems_v = new JTextField("10", 10);
        start_u = new JTextField(10);
        start_v = new JTextField(10);
        label_start_u = new JLabel("start u coordinate:");
        label_start_v = new JLabel("start v coordinate:");
        
        
        go_now = new JButton("GO");
        no_systems_u.addActionListener(new TextFieldListenerU());
        no_systems_v.addActionListener(new TextFieldListenerV());
        no_systems_u.addKeyListener(new KeyListenU());
        no_systems_v.addKeyListener(new KeyListenV());
        start_u.addActionListener(new StartTextFieldListenerU());
        start_v.addActionListener(new StartTextFieldListenerV());
        start_u.addKeyListener(new StartKeyListenU());
        start_v.addKeyListener(new StartKeyListenV());
        go_now.addActionListener(new GoNow());
        container.add(no_systems_u);
        container.add(no_systems_v);
        container.add(label_start_u);
        container.add(start_u);
        container.add(label_start_v);
        container.add(start_v);
        container.add(go_now);
    }
    
    
    /** Creates new form BuildSystems */
    public BuildSystems() {
        initComponents ();
    }   
    
    /** 
     */
    private void initComponents () {
        _init();
        setSize(400, 300);
        show(); 
        
    }
    
    /**
     *
     */
    class  StartTextFieldListenerU implements ActionListener {
        public void actionPerformed(ActionEvent AcEv){
            start_u.setText(AcEv.getActionCommand());
            System.out.println("<B> start U </B>" + start_u.getText());
        }   
    }   
    class  StartTextFieldListenerV implements ActionListener {
        public void actionPerformed(ActionEvent AcEv){
            start_v.setText(AcEv.getActionCommand());
            System.out.println("<B> start V </B>" + start_v.getText());
        }
    }   
        
    class StartKeyListenU extends KeyAdapter{
        public void keyTyped(KeyEvent KeEv){
        }
    }
    class StartKeyListenV extends KeyAdapter{
        public void keyTyped(KeyEvent KeEv){
        }
    }
    
    
    /**
     *
     */
    class  TextFieldListenerU implements ActionListener {
        public void actionPerformed(ActionEvent AcEv){
            no_systems_u.setText(AcEv.getActionCommand());
            System.out.println("<B> in U </B>" + no_systems_u.getText());
        }   
    }   
    class  TextFieldListenerV implements ActionListener {
        public void actionPerformed(ActionEvent AcEv){
            no_systems_v.setText(AcEv.getActionCommand());
            System.out.println("<B> in V </B>" + no_systems_v.getText());
        }
    }   
        
    class KeyListenU extends KeyAdapter{
        public void keyTyped(KeyEvent KeEv){
        }
    }
    class KeyListenV extends KeyAdapter{
        public void keyTyped(KeyEvent KeEv){
        }
    }
    
    
    class GoNow implements ActionListener {
        public void actionPerformed(ActionEvent AcEv){

	    String _startu = start_u.getText();
	    String _startv = start_v.getText();
            System.out.println("<B> GO " + 
                               no_systems_u.getText() + ":" + 
                               no_systems_v.getText() + ":" + 
                               _startu + ":" +
                               _startv + ":" +
                               "</B>");

            systemdisplaynodetop = new SystemDisplayNode();
            systemdisplaynodetop.setUdim(no_systems_u.getText());
            systemdisplaynodetop.setVdim(no_systems_v.getText());
            systemdisplaynodetop.setStartUdim(_startu);
            systemdisplaynodetop.setStartVdim(_startv);
            
            for (int idex = 0; idex < systemdisplaynodetop.getUdim(); idex++){
                for (int idex2 = 0; idex2 < systemdisplaynodetop.getVdim(); idex2++){

                    SystemDisplayNode systemdisplaynode = new SystemDisplayNode();
                    systemdisplaynode.setUdim(no_systems_u.getText());
                    systemdisplaynode.setVdim(no_systems_v.getText());
                    systemdisplaynode.setStartUdim(_startu);
                    systemdisplaynode.setStartVdim(_startv);
                    systemdisplaynode.setMyUdim( idex );
                    systemdisplaynode.setMyVdim( idex2 );

                    String this_sector = ":" + idex + ":" + idex2 + ":";
                    if(drawrolls.Instance().getD100() < density){
                        System.out.println("star gen sched:" + this_sector);
                        //                       List _sl = star.genStars();
                        systemdisplaynode.setStarRep(star.genStars());
                        systemdisplaynode.setActive();    
                    }
                    totaldraw.put(systemdisplaynode.getKey(), 
                                  systemdisplaynode);
                    if(systemdisplaynode.isItActive()){
                        System.out.println(systemdisplaynode.getSystemName());
                    }
                }
            }

            BuildSystemsMatrix bsm = new BuildSystemsMatrix(no_systems_u.getText(), no_systems_v.getText());
            Thread buildsystems = new Thread(bsm);
            buildsystems.start();
            System.out.println("End Build System Matrix");

            Collection _collectionview = totaldraw.values();
            OrderedArrayListCollection worktotaldraw = new OrderedArrayListCollection();
            worktotaldraw.addAll(_collectionview);
            EmitSql emitsql =  new EmitSql();
            emitsql.emitStarSys(worktotaldraw);
            emitsql.stopPrintWriter();
        }  
        
    }  
    

    public void showDrawSize(){
        int _us = 0;
        int _vs = 0;
        if(systemdisplaynodetop != null){
            _us = systemdisplaynodetop.getUdim();
            _vs = systemdisplaynodetop.getVdim();
        }
        System.out.print("::"+ totaldraw.size()+"::"+_us+"::"+_vs+"::");
    }


    public void setDensity(String density){ this.density = Integer.parseInt(density);}

    public static void main(String [] argv){
        BuildSystems bs = new BuildSystems();
        if(argv.length == 1){
            bs.setDensity(argv[0]);
        }
        System.out.println("End BuildSystems.main");
    }
}
