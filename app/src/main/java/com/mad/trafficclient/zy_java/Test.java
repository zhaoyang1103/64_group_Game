package com.mad.trafficclient.zy_java;

import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Hashtable map = new Hashtable();
        map.put("123", "456sdafasdf");
        map.put("12", "456sdafasdf");
        map.put("321", "456sdafasdf");
        map.put("124563", "456sdafasdf");
        Hashtable hashtable = new Hashtable();
//        hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");

        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            System.out.println("next = " + key);
        }
    }
}
