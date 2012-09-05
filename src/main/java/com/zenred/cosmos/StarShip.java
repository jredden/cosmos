//Source file: C:/VisualCafe/JAVA/LIB/cosmos/StarShip.java

package com.zenred.cosmos;


public class StarShip 
{
   private HyperspaceCoord current_pos = new HyperspaceCoord(0.0, 0.0);
   private DistVect move = new DistVect(0.0, 0.0, 0.0);
   private DistVect preangle = new DistVect(0.0, 0.0, 0.0);
   private DistVect curangle = new DistVect(0.0, 0.0, 0.0);
   private DistVect prevolx = new DistVect(0.0, 0.0, 0.0);
   private DistVect prevoly = new DistVect(0.0, 0.0, 0.0);
   private DistVect volx = new DistVect(0.0, 0.0, 0.0);
   private DistVect voly = new DistVect(0.0, 0.0, 0.0);
   private DistVect pregees = new DistVect(0.0, 0.0, 0.0);
   private DistVect curgees = new DistVect(0.0, 0.0, 0.0);
    private DistVect premove = new DistVect(0.0, 0.0, 0.0);
   private Double ship_size = new Double(0.0);
   private Double tonnage = new Double(0.0);
   
   public StarShip() 
   {
   }
}
