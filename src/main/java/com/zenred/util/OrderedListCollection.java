
package com.zenred.util;
import java.util.List;
import java.util.Iterator;
/**
 *
 */
public interface OrderedListCollection extends List {
    public void forEachDo(AnonBlock anonblock);
}
