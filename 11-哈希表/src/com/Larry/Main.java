package com.Larry;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(10,1.67f,"jack");
        Person p2 = new Person(10,1.67f,"jack");
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

        Map<Object,Object> map =new HashMap<>();
        map.put(p1,"abc");
        map.put("test","ccc");
        map.put(p2,"bcd");
        System.out.println(map.size());
    }

}