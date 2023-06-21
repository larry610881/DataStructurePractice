package com.Larry.circle;

public class CircleDeque<E>{
        private  int front;
        private  int size;
        private  E[] elements;
        private static final int DEFAULT_CAPACITY=10;
        public CircleDeque(){
            elements = (E[]) new Object[DEFAULT_CAPACITY];

        }
        public int size(){
            return size;
        }
        public boolean isEmpty(){
            return  size==0;
        }

        public  void enQueueRear(E element){
            ensureCapacity(size+1);

            elements[index(size)] =element;
            size++;
        }
        public  void enQueueFront(E element){
            ensureCapacity(size+1);
            front=index(-1);
            elements[front] =element;
            size++;
        }
        public  E deQueueRear(){
            int rearIndex =index(size-1);
            E frontElement = elements[rearIndex];
            elements[rearIndex] = null;
            size--;
            return  frontElement;
        }
        public  E deQueueFront(){
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
            if(index<0){
                return  index+elements.length;
            }
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
