package com.Larry.list;

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
        /**
         * 最好:O(1) 每一次都只對尾項目新增
         * 最壞:O(n) 擴容的時候便O(n)
         * 平均:O(1)
         * 均攤複雜度:O(1)
         * 經過連續多次的複雜度後，出現個別複雜度比較高的情況
         */
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
