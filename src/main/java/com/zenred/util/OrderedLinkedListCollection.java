
package com.zenred.util;
import java.util.LinkedList;
import java.util.Iterator;
/**
 *
 */
public class OrderedLinkedListCollection extends LinkedList implements OrderedListCollection {

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
