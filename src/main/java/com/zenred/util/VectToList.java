// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VectToList.java

package com.zenred.util;

import java.io.PrintStream;
import java.util.*;

// Referenced classes of package com.zenred.util:
//            OrderedArrayListCollection, AnonBlock

public class VectToList
{

    public VectToList()
    {
    }

    public static List conv(Vector obj)
    {
        List _list = new ArrayList();
        for(Iterator _iter = obj.iterator(); _iter.hasNext(); _list.add(_iter.next()));
        return _list;
    }

    public static void print(Vector obj)
    {
        print(conv(obj));
    }

    public static void print(List obj)
    {
        OrderedArrayListCollection _o_a_list = new OrderedArrayListCollection();
        _o_a_list.addAll(obj);
        _o_a_list.forEachDo(new AnonBlock() {

            public void exec(Object each)
            {
                VectToList.printTheEntry(each);
            }

        });
    }

    private static void printTheEntry(Object each)
    {
        System.out.println(each.getClass().getName() + "::" + each.toString());
    }

}
