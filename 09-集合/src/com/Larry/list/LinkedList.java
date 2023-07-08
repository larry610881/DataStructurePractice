package com.Larry.list;

public class LinkedList<E>  extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private static final int ELEMENT_NOT_FOUND=-1;
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
            last = new Node<>(oldLast,element,null);
            if(oldLast ==null){//原本是空的
                first =last;
            }else {
                oldLast.next =last;
            }

        }else {
            Node<E> next =node(index);
            Node<E> prev = next.prev;
            Node<E> node =new Node<>(prev,element,next);
            next.prev =node;
            if(prev==null){ //inde ==0
                first =node;
            }else {
                prev.next =node;
            }
        }

        size++;

    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node =node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if(prev ==null){ // index ==0
            first = next;
        }else {
            prev.next = next;
        }

        if(next ==null){ // index == size -1
            last =prev;
        }else{
            next.prev = prev;
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
