
package com.zenred.util;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 */
public class OrderedArrayListCollection extends ArrayList implements OrderedListCollection {

    public OrderedArrayListCollection(Collection collection){
	super(collection);
    }

    public OrderedArrayListCollection(){
	super();
    }
	
    /**
     * 
     */
    public void forEachDo(AnonBlock anonblock){
	Iterator iterator = iterator();
	while (iterator.hasNext()){
	    anonblock.exec(iterator.next());
	}
    }
}
