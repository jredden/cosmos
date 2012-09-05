//Source file: C:/VisualCafe/JAVA/LIB/cosmos/Planettoid.java

package com.zenred.cosmos;

import java.lang.reflect.Array;

public class Planettoid 
{
   private Double radius;
   private Double density;
   private Double orbit;
   private Array orbit_focus = null;
    private Star m_star;
   
   public Planettoid() 
   {
   }
   
   /**
   @roseuid 391615E102BC
   */
   public void addStar(Star star) 
   {
       m_star = star;
   }
   
   /**
   @roseuid 391616A603DE
   */
   public Star getStar() 
   {
       return m_star;
   }

public Double getDensity() {
	return density;
}

public void setDensity(Double density) {
	this.density = density;
}

public Double getOrbit() {
	return orbit;
}

public void setOrbit(Double orbit) {
	this.orbit = orbit;
}

public Array getOrbit_focus() {
	return orbit_focus;
}

public void setOrbit_focus(Array orbit_focus) {
	this.orbit_focus = orbit_focus;
}

public Double getRadius() {
	return radius;
}

public void setRadius(Double radius) {
	this.radius = radius;
}
}
