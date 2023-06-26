package com.Larry;

import com.Larry.tree.AVLTree;
import com.Larry.tree.BST;
import com.Larry.print.BinaryTrees;
import com.Larry.tree.BinaryTree;
import com.Larry.tree.RBTree;

public class Main {
    static void test1(){
        Integer data[] =new Integer[]{
                85,19,69,3,7,99,95
        };
        AVLTree<Integer> avl = new AVLTree<>();
        for(int i=0; i< data.length;i++){
            avl.add(data[i]);
        }
        avl.remove(99);
        avl.remove(85);
        avl.remove(95);
        BinaryTrees.println(avl);


    }
    static void test2(){

        Integer data[] =new Integer[]{
                55,87,56,74,96,22,62,20,70,68,90,50
        };
        RBTree<Integer> rb = new RBTree<>();
        for(int i=0; i< data.length;i++){
            rb.add(data[i]);
        }

        BinaryTrees.println(rb);
    }

    public static void main(String[] args) {
        test2();

    }
}