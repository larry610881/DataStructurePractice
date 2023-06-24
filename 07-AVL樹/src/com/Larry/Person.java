package com.Larry;

public class Person implements Comparable<Person> {
    private int age;
    public int getAge(){
        return age;
    }
    public Person(int age){
        this.age =age;
    }

    @Override
    public int compareTo(Person e) {
        return age-e.age;
    }

    @Override
    public String toString() {
        return "age="+ age;
    }
}
