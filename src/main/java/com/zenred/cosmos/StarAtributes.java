package com.zenred.cosmos;

/**
 * StarAtributes.java
 *
 *
 * Created: Mon Jul 22 07:06:30 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class StarAtributes implements StarAtributesIF, DrawRollsIF {

    // private static boolean DEBUG = true;
    private static boolean DEBUG = false;
    private static int bsindx;
    private static int lbsindx; 
    private double star_luminosity;
    private String mstype = "";
    private String lscolor = ""; 
    private double star_size;

    /**
     * construction
     */
    public StarAtributes (){
        
    }

    public void genStarAtributes(){
        int _istar = DrawRolls.Instance().getD4500();
        bsindx = 0;
        lbsindx = stardex.length;
        while(true){
            int _mid = (bsindx+lbsindx)/2;
            
            if(_istar<stardex[_mid]){
                lbsindx = _mid - 1;
                if(DEBUG)System.out.println("look on small side:" + lbsindx + ":" + _mid + ":" + _istar + ":" +  stardex[_mid]);
            }
            else{
                if(_istar > stardex[_mid] && _istar > stardex[_mid+1]){
                    bsindx = _mid + 1;
                    if(DEBUG)System.out.println("look on big side:" + lbsindx + ":" + _mid + ":" + _istar + ":" +  stardex[_mid]);
                }
                else {
                    if(DEBUG)System.out.println("found:" + _mid);
                    break;
                }
            }
        }

        int _idir = DrawRolls.Instance().getCoinFlip();
        if(bsindx==0){
            _idir=1; 
        }
        else if(bsindx==stardex.length){
            _idir=0;
        }

        double _r1 = DrawRolls.Instance().draw_rand();
        double _diff = 0;
        double _astarlum = 0;

        if(_idir == TAILS){
            if(starlum[bsindx] > starlum[bsindx]){
                _diff=(_r1*.05)*(starlum[bsindx]-starlum[bsindx]);
                _astarlum=starlum[bsindx] + _diff;
            }
            else{
                _diff=(_r1*.05)*(starlum[bsindx]-starlum[bsindx]);
                _astarlum=starlum[bsindx] - _diff;
            }
        }
        else{
            if(starlum[bsindx+1] < starlum[bsindx]){
                _diff=(_r1*.05)*(starlum[bsindx]-starlum[bsindx+1]);
                _astarlum=starlum[bsindx] - _diff;
            }
            else{
                _diff=(_r1*.05)*(starlum[bsindx+1]-starlum[bsindx]);
                _astarlum=starlum[bsindx] + _diff;
            }
        }

        star_luminosity = _astarlum;

        /* determine name of star type   */
        /* and write it out   */

        if(bsindx < SUBDWARF){
            int mseqn = bsindx % COLUMNMODULUS;
            int mseqc = (bsindx % 70) / COLUMNMODULUS;
  
            if(mseqc == 0){
                mseqn = 7;
            }
            else{
                --mseqn;
            }

            mstype += starseq[mseqc];
            mstype += (mseqn + 1);
        }
        else{
            int _sdidex = (bsindx - SUBDWARF)/COLUMNMODULUS;
            System.out.println("_sdidex:" + _sdidex + ",bsindx:" + bsindx );
            mstype += (stardwarf[_sdidex]);
        }

        lscolor += starcolor[bsindx/COLUMNMODULUS];
        
        /* determine approx star mass   */

      int sizei=(bsindx/80)*3+3;
      int sizeii=bsindx % 80;
      
      double r1= new Double("" + sizeii).doubleValue();
      if(sizeii<11){
        star_size=starmass[sizei-2]*2-((starmass[sizei-2]-starmass[sizei-1]*(r1/10.0)));
      }
      else{
        if(sizeii>40){
          star_size=starmass[sizei-1]-((starmass[sizei-1]-starmass[sizei]*(r1/30.0)));
        }
        else{
          star_size=starmass[sizei-2]-((starmass[sizei-2]-starmass[sizei-1]*(r1/30.0)));
        }
      }
      
    }

    public double getDistanceFromStarCentreToClusterCentre(){
        return (InSystemConstraintsIF.HALFPSEC / 2.0) / ((double)DrawRolls.Instance().getDraw(99.0));
    }

    public double getAngle(){
        return ((double)DrawRolls.Instance().getDraw(360.0));
    }

    /**
     *
     */
    public String toString(){
        return "star_luminosity:" + star_luminosity + " mstype:" + mstype + " lscolor:" + lscolor + " star_size:" + star_size + ":";
    }

    /**
     *
     */
    public double getStarLuminosity(){return star_luminosity;}
    public String getStarType(){return mstype;}
    public String getStarColor(){return lscolor;}
    public double getStarSize(){return star_size;}

    /**
     *
     */
    public static void main(String [] argv){
        StarAtributes staratributes = new StarAtributes();
        staratributes.genStarAtributes();
        System.out.println(staratributes.toString());
        System.out.println("2nd");
        staratributes.genStarAtributes();
        System.out.println(staratributes.toString());
    }

}// StarAtributes
