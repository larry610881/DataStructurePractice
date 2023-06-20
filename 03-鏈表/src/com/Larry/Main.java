package com.Larry;

import com.Larry.Single.SingleLinkList2;

public class Main {
    static void testList(List<Integer> list){
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0,55); //[55,11,22,33,44]
        list.add(2,66);//[55,11,66,22,33,44]
        list.add(list.size(),77);//[55,11,66,22,33,44,77]

        list.remove(0);//[11,66,22,33,44,77]
        list.remove(2);//[11,66,33,44,77]
        list.remove(list.size()-1);//[11,66,33,44]

        Asserts.test(list.indexOf(44)==3);
        Asserts.test(list.indexOf(22)==List.ELEMENT_NOT_FOUND);
        Asserts.test(list.contains(33));
        Asserts.test(list.get(0)==11);
        Asserts.test(list.get(1)==66);
        Asserts.test(list.get(list.size()-1)==44);
        System.out.println(list);
    }
    public static void main(String[] args) {
        testList(new ArrayList<>());
        testList(new LinkList<>());

    }

    static  void  test(){
        List<Integer> list =new ArrayList<>();
        list.add(20);
        list.add(0,10);
        list.add(30);
        list.add(list.size(),40);

        list.remove(1);
        System.out.println(list);

        List<Integer> list2 =new SingleLinkList2<>();
        list2.add(20);
        list2.add(0,10);
        list2.add(30);
        list2.add(list.size(),40);

        list2.remove(1);
        System.out.println(list2);

        List<Integer> array2 = new ArrayList2<>();
        for(int i=0;i<50;i++){
            array2.add(1);
        }
        for(int i=0;i<50;i++){
            array2.remove(0);
        }
        System.out.println(array2);
    }
}