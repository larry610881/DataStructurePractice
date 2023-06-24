package com.Larry;

import com.Larry.tree.BST;
import com.Larry.print.BinaryTrees;
import com.Larry.tree.BinaryTree;

public class Main {
    static void test1(){
        Integer data[] =new Integer[]{
                7,4,9,2,5,8,11,3,12,1
        };
        BST<Integer> bst = new BST<>();
        for(int i=0; i< data.length;i++){
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        bst.remove(7);
        BinaryTrees.println(bst);

    }
    public static void main(String[] args) {
        test1();

    }
}