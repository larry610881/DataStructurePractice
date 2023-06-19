package com.Larry;

public class LinkList<E>  extends AbstractList<E>  {
    private Node<E> first;
    private static final int ELEMENT_NOT_FOUND=-1;
    private static class Node<E>{
        E element;
        Node<E> next;
        public  Node(E element,Node<E> next){
            this.element =element;
            this.next =next;
        }
    }
    @Override
    public void clear() {
        size=0;
        first =null;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element)!=ELEMENT_NOT_FOUND;
    }


    @Override
    public E get(int index) {
        /**
         * 最好:O(1)
         * 最壞:O(n)
         * 平均:O(n)
         */
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        /**
         * 最好:O(1)
         * 最壞:O(n)
         * 平均:O(n)
         */
        Node<E> node =node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        /**
         * 最好:O(1)
         * 最壞:O(n)
         * 平均:O(n)
         */
        rangeCheckForAdd(index);
        if (index ==0){
            first = new Node<>(element,first);
        }else{
            Node<E> prev = node(index-1);
            prev.next =new Node<>(element,prev.next);
        }
        size++;

    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node =first;
        if(index ==0){
            first = first.next;
        }else{
            Node<E> prev= node(index-1);
            node = prev.next;
            prev.next =node.next;
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

        Node<E> node =first;
        for(int i =0;i<index;i++){
            node = node.next;
        }
        return node;
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
            string.append(node.element);
            node = node.next;
        }
        string.append("】");
        return string.toString();
    }

}
