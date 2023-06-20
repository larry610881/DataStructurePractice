package com.Larry;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> person = new ArrayList<>();
        person.add(new Person(10,"Jack"));
        person.add(null);
        person.add(new Person(12,"James"));
        person.add(null);
        person.add(new Person(15,"Rose"));
        person.add(null);
        System.out.println(person);
        System.out.println(person.indexOf(null));
        person.clear();

        //提醒JVM進行垃圾回收
        System.gc();
    }
    static void test(){
        // int -> Integer
        //所有的類，最終都繼承java.lang.Object
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Person> person = new ArrayList<>();
        person.add(new Person(10,"Jack"));
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        list.remove(3);
        list.add(0,1);
        list.add(list.size(),111);

        list.set(3,80);
        Asserts.test(list.get(3)==80);

        list.remove(0);
        Asserts.test(list.get(0)==11);
        Asserts.test(list.size()==4);

        //System.out.println(list)事實上是在做 list的toString 因此我們override toString
        System.out.println(list);
        System.out.println(person);
    }
}