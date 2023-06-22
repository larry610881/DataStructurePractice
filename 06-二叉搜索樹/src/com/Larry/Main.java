package com.Larry;

import com.Larry.BinarySearchTree.Visitor;
import com.Larry.print.BinaryTrees;

import java.util.Comparator;

public class Main {
    private static class PersonComparator1 implements Comparator<Person> {
      public int compare(Person e1,Person e2){
          return e1.getAge()-e2.getAge();
      }
    };
    private static class PersonComparator2 implements Comparator<Person>{
        public int compare(Person e1,Person e2){
            return e2.getAge()-e1.getAge();
        }
    };
    static void test1(){
        Integer data[] =new Integer[]{
                7,4,9,2,5,8,11,3
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for(int i=0; i< data.length;i++){
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        System.out.println(bst);
    }
    static void test2(){
        Integer data[] =new Integer[]{
                7,4,9,2,5,8,11,3
        };
        BinarySearchTree<Person> bst= new BinarySearchTree<>();
        for(int i=0; i< data.length;i++){
            bst.add(new Person(data[i]));
        }
        BinaryTrees.println(bst);
    }
    static void test3(){
        Integer data[] =new Integer[]{
                7,4,9,2,1,3,5,8,11,10,12
        };
        BinarySearchTree<Person> bst= new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge()- o1.getAge();
            }
        });
        for(int i=0; i< data.length;i++){
            bst.add(new Person(data[i]));
        }
        BinaryTrees.println(bst);

    }
    static void test4(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i=0;i<30;i++){
            bst.add((int)(Math.random()*100));
        }
        BinaryTrees.println(bst);
    }
    static void test5(){
        Integer data[] =new Integer[]{
                7,4,9,2,5,8,11,3,12,1
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for(int i=0; i< data.length;i++){
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        //bst.levelOderTraversal();
        bst.levelOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(" _"+element+"_ ");
                return false;
            }
        });
    }
    static void test6(){
        Integer data[] =new Integer[]{
                7,4,9,2,5,8,11,3,12,1
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for(int i=0; i< data.length;i++){
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        bst.preorder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element +" ");
                return  element==2 ;
            }
        });
        System.out.println();
        bst.inorder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element +" ");
                return element==4;
            }
        });
        System.out.println();
        bst.postorder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element +" ");
                return element==4;
            }
        });
        System.out.println();
        bst.levelOrder(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element +" ");
                return element==9;
            }
        });

    }
    public static void main(String[] args) {
        test1();

    }
}