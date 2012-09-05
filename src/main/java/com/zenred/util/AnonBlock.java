package com.zenred.util;
/**
 * an anonymous block that has a single method - exec 
 */
public interface AnonBlock {
    /**
     * used to perform an iteration operation of somekind
     * @param any non primitive
     */
    public void exec(Object each);
}
