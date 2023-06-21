package com.Larry;

import com.Larry.circle.CircleDeque;
import com.Larry.circle.CircleQueue;
import com.Larry.list.Deque;

public class Main {
    public static void main(String[] args) {
        //test2();
        test3();
    }
    static void test(){
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);
        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
    static void test1(){
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);
        while (!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
    static void test2(){
        CircleQueue<Integer> queue = new CircleQueue<>();
        for(int i =0;i<10;i++){
            queue.enQueue(i);
        }
        for(int i =0; i<5;i++){
            queue.deQueue();
        }
        for(int i=10;i<18;i++){
            queue.enQueue(i);
        }
        System.out.println(queue);
        while(!queue.isEmpty()){
            System.out.println(queue.deQueue());
        }
    }
    static void test3(){
        CircleDeque<Integer> queue = new CircleDeque<>();
        for(int i =0;i<10;i++){
            queue.enQueueFront(i+1);
            queue.enQueueRear(i+100);
        }
        for(int i =0; i<3;i++){
            queue.deQueueFront();
            queue.deQueueRear();
        }
        queue.enQueueFront(11);
        queue.enQueueRear(12);
        System.out.println(queue);
        while(!queue.isEmpty()){
            System.out.println(queue.deQueueFront());
        }
    }

}