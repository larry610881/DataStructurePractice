package com.Larry;

public abstract class AbstractList<E> implements List<E> {
    /**
     * 元素的數量
     */
    protected int size;
    /**
     * 元素的數量
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 是否為空
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }
    /**
     * 添加元素到尾部
     * @param element
     */
    public void add(E element){
        add(size,element);
    }
    protected void outOfBounds(int index){
        throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
    }
    protected void rangeCheck(int index){
        if(index<0 ||index>=size) outOfBounds(index);
    }
    protected void rangeCheckForAdd(int index){
        if(index<0 ||index>size) outOfBounds(index);
    }

}
