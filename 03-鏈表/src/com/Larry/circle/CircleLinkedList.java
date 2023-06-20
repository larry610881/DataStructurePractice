package com.Larry.circle;

import com.Larry.AbstractList;

public class CircleLinkedList<E>  extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private Node<E> current;

    private static class Node<E>{
        E element;
        Node<E> prev;
        Node<E> next;
        public  Node(Node<E> prev,E element,Node<E> next){
            this.prev = prev;
            this.element =element;
            this.next =next;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            if(prev!=null){
                sb.append(prev.element);
            }else {
                sb.append("null");
            }
            sb.append("_").append(element).append("_");
            if(next!=null){
                sb.append(next.element);
            }else {
                sb.append("null");
            }
            return sb.toString();
        }
    }
    public void reset(){
        current =first;
    }
    public E next(){
        if(current==null) return null;
        current =current.next;
        return current.element;
    }
    public  E remove(){
        if(current==null) return null;
        Node<E> next = current.next;
        E element = remove(current);
        if(size==0){
            current=null;
        }else{
            current =next;
        }

        return  element;
    }
    @Override
    public void clear() {
        size=0;
        first =null;
        last =null;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element)!=ELEMENT_NOT_FOUND;
    }


    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node =node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        //size == 0
        //index == 0
        if(index==size){//尾節點插入
            Node<E> oldLast=last;
            last = new Node<>(oldLast,element,first);
            if(oldLast ==null){//原本是空的
                first =last;
                first.prev = first;
                first.next =first;
            }else {
                oldLast.next =last;
                first.prev = last;
            }

        }else {
            Node<E> next =node(index);
            Node<E> prev = next.prev;
            Node<E> node =new Node<>(prev,element,next);
            next.prev =node;
            prev.next =node;

            if(next==first){ //index ==0
                first =node;
            }
        }

        size++;

    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return  remove(node(index));
    }

    private E remove(Node<E> node){
        if(size==1){
            first = null;
            last = null;
        }else{
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if(node ==first){ // index ==0
                first = next;
            }

            if(node ==last){ // index == size -1
                last =prev;
            }
        }

        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node =first;
        if (element ==null){
            for(int i=0;i<size;i++){
                if(node.element==null) return i;
                node =node.next;
            }
        }else{
            for(int i=0;i<size;i++){
                //Object是不能用==因為Object是先指向內存位置
                //equals不重寫的話是比內存
                if(element.equals(node.element)) return i;
                node =node.next;
            }
        }
        return  ELEMENT_NOT_FOUND;
    }

    /**
     * 獲取index節點對應對象
     * @param index
     * @return
     */
    private Node<E> node (int index){
        rangeCheck(index);
        if(index<(size>>1)){
            Node<E> node =first;
            for(int i =0;i<index;i++){
                node = node.next;
            }
            return node;
        }else{
            Node<E> node =last;
            for(int i =size-1;i>index;i--){
                node = node.prev;
            }
            return node;
        }

    }
    @Override
    public String toString() {
        //size=3 [99,88,77]
        StringBuffer string = new StringBuffer();
        string.append("size=").append(size).append(",【");
        Node<E> node = first;
        for (int i=0;i<size ;i++){
            if (i!=0){
                string.append(", ");
            }
            string.append(node);
            node = node.next;
        }
        string.append("】");
        return string.toString();
    }

}
