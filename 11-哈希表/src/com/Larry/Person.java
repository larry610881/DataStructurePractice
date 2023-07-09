package com.Larry;

public class Person {
    private int age;
    private float height;
    private String name;

    public Person(int age,float height,String name){
        this.age =age;
        this.height =height;
        this.name = name;
    }

    @Override
    public int hashCode() {
        //假設需求是看Object 內容決定哈希值 而不是原來利用內存的方式
        int hashCode =Integer.hashCode(age);
        hashCode =hashCode*31+Float.hashCode(height);
        hashCode =hashCode*31+name!=null ?name.hashCode():0;
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if(this ==obj) return true;//比內存 就是自己
        if(obj==null|| obj.getClass()!=getClass()) return false;//這句 子類就會不等於 要視同對象才可以
        //if(obj ==null || !(obj instanceof Person)) return false; 這句 子類也可以等於
        //比較成員變量
        Person person =(Person) obj;
        return person.age ==age
                && person.height ==height
                && (person.name==null?name==null:person.name.equals(name));
    }
}
