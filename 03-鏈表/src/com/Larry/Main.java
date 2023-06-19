package com.Larry;

public class Main {
    public static void main(String[] args) {

        List<Integer> list =new ArrayList<>();
        list.add(20);
        list.add(0,10);
        list.add(30);
        list.add(list.size(),40);

        list.remove(1);
        System.out.println(list);

        List<Integer> list2 =new LinkList2<>();
        list2.add(20);
        list2.add(0,10);
        list2.add(30);
        list2.add(list.size(),40);

        list2.remove(1);
        System.out.println(list2);
    }
}