package com.zenred.cosmos;
import java.io.PrintWriter;
import java.text.MessageFormat;

import com.zenred.util.OrderedArrayListCollection;
import com.zenred.util.OrderedListCollection;
import com.zenred.util.AnonBlock;

/**
 * EmitSqlStars.java
 *
 *
 * Created: Thu Aug 22 07:05:04 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class EmitSqlStars implements EmitSqlIF{

    private PrintWriter writer;
    private String system_name;
    private String cluster_name;

    public EmitSqlStars (){
        
    }

    public void emitTheStars(OrderedArrayListCollection starlist, 
                             PrintWriter writer,
                             String system_name,
                             String cluster_name){
        this.writer = writer;
        this.system_name = system_name;
        this.cluster_name = cluster_name;

        starlist.forEachDo(new AnonBlock(){
                public void exec (Object each){
                    nextStarEmit(each);
                }
            });
    }

    private void nextStarEmit(Object starrep){
        StarRep _star_rep = (StarRep)starrep;
        double _dis= _star_rep.getDistanceToVirtCentre();
        Double _ddis = new Double(_dis);
       if(_ddis.isInfinite()){
    	   _dis = 0.0;
       }
        String _insert_star_rep = MessageFormat.format(INSERT_STARREP,
                                                       new Object[] {
                                                           "'" + system_name + "'",
                                                           "'" + cluster_name + "'",
                                                           "" + _dis,
                                                           "" + _star_rep.getLuminosity(),
                                                           "'" + _star_rep.hasNoPlanets() + "'",
                                                           "" + _star_rep.getStartAngleFromVirtCentreInRadians(),
                                                           "'" + _star_rep.getStarColor() + "'",
                                                           "'" + _star_rep.getStarType() + "'",
                                                           "" + _star_rep.getStarSize()}
                                                       );
        writer.print(_insert_star_rep + "\n");
        writer.print(EmitSql.getSqlGo() + "\n");

    }
}// EmitSqlStars
