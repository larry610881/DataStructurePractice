package com.Larry.circle;

public class CircleQueue<E> {
    private  int front;
    private  int size;
    private  E[] elements;
    private static final int DEFAULT_CAPACITY=10;
    public CircleQueue(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];

    }
    public void clear(){

        for(int i=0;i<size;i++){
            elements[index(i)]=null;
        }
        size=0;
        front=0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return  size==0;
    }

    public  void enQueue(E element){
        ensureCapacity(size+1);

        elements[index(size)] =element;
        size++;
    }
    public  E deQueue(){
        E frontElement = elements[front];
        elements[front] = null;
        front =index(1);
        size--;
        return  frontElement;
    }

    public E front(){
        return elements[front];
    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("capacity=").append(elements.length)
                .append(" size =").append(size)
                .append(" front =").append(front)
                .append(", [");
        for(int i =0;i<elements.length;i++){
            if(i!=0){
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }
    private int index(int index){
        index+=front;
        //用差取代mod 因為這邊index是++不會超過 length的兩倍
        return index -(index>=elements.length?elements.length:0) ;
    }
    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity>=capacity) return;
        //新容量為舊容量的1.5倍
        int newCapacity =oldCapacity+(oldCapacity>>1);
        E[] newElements = (E[]) new Object[newCapacity];
        for(int i=0 ; i<size;i++){
            newElements[i]=elements[index(i)];
        }
        elements =newElements;
        front =0;
        System.out.println(oldCapacity+"擴容為"+newCapacity);
    }
}
