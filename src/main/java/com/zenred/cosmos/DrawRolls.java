//Source file: C:/VisualCafe/JAVA/LIB/com.zenred.cosmos/DrawRolls.java

package com.zenred.cosmos;
import java.util.Random;

/**
 * draws random rolls based on imputs
 */ 

public class DrawRolls 
{
    private StarCalendar calendar;
    private long seed_random;
    private static Random random;

    private static DrawRolls instance = new DrawRolls();

    /**
     *
     */
    public static DrawRolls Instance(){
        return instance;
    }
    
    
    /**
     * construction
     */
    public DrawRolls(){
        calendar = new StarCalendar();
        this.seed_random = calendar.getStarTimeInMillis();
        random = new Random(this.seed_random);
    }
    /**
     * construction with seed
     * @param - long seed 
     */
    public DrawRolls(long seed_random){
        this.seed_random = seed_random;
        random = new Random(this.seed_random);
    }
    
    /**
     * @param ip_int
     * @return int between 0 and ip_real
     */

    public int getDraw(double ip_real){
	return (int)(random.nextDouble() * ip_real);
    }

    /**
     *
     */
    public double draw_rand(){return random.nextDouble();}

    /**
     *
     * 
     * @return - integer between 0 and 1
     */
    public int getCoinFlip(){
        return getDraw(2.0);
    }

    /**
     *
     * 
     * @return - integer between 0 and 99
     */
    public int getD100(){
        return getDraw(100.0);
    }

    /**
     *
     * 
     * @return - integer between 0 and 999
     */
    public int getD1000(){
        return getDraw(1000.0);
    }

    /**
     *
     * 
     * @return - integer between 0 and 4499
     */
    public int getD4500(){
        return getDraw(4500.0);
    }
    
    public int getD100000(){
    	return getDraw(100000.0);
    }

    /**
     * @return - integer between 0 an 3
     */
    public int getD3(){return getDraw(3.0);}
    
    /**
     * numbers between 1 and n
     */
    public int get_D3(){return getDraw(3.0) + 1;}
    public int get_D2(){return getCoinFlip() + 1;}
    public int get_D4(){return getDraw(4.0) + 1;}
    public int get_D6(){return getDraw(6.0) + 1;}
    public int get_D8(){return getDraw(8.0) + 1;}
    public int get_D10(){return getDraw(10.0) + 1;}
    public int get_D18(){return getDraw(18.0) + 1;}


}
