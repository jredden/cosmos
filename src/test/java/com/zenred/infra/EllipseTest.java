package com.zenred.infra;



import org.junit.Test;


public class EllipseTest {

	@Test
	public void test() {
		double C_x = 10+40, C_y = 20+40, w = 40, h = 50;
		for(double t = 0; t <=2*Math.PI; t+=0.01)
		{
		   double x = C_x+(w/2)*Math.cos(t);
		   double y = C_y+(h/2)*Math.sin(t);
		   System.out.println("X:"+x+" Y:"+y);
		}
	}

	@Test
	public void test2(){
		double radians = Math.toRadians(45.0);
		double C_x = 10, C_y = 20, w = 40, h = 50;

		double x = C_x + (w / 2) * Math.cos(radians);
		double y = C_y + (h / 2) * Math.sin(radians);
		System.out.println("45X:" + x + " 45Y:" + y);
	}
}
