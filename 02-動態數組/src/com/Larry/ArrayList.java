package com.Larry;
@SuppressWarnings("unchecked")
public class ArrayList<E> {
    /**
     * 元素的數量
     */
    private int size;
    /**
     * 所有的元素
     */
    private E[] elements;
    private static final int DEFAULT_CAPACITY=10;
    private static final int ELEMENT_NOT_FOUND=1;
    /**
     * 清除所有元素
     */
    public ArrayList(int capaticy){
        capaticy = (capaticy<DEFAULT_CAPACITY)?DEFAULT_CAPACITY:capaticy;
        elements = (E[]) new Object[capaticy];
    }
    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * 移除所有元素
     * p.s.用size限制訪問範圍因此無須實際清除所有內存的資料
     */
    public void clear(){

        for(int i =0;i<size;i++){
            elements[i]=null;
        }
        size =0;
    }

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
     * 是否包含某個元素
     * @param element
     * @return
     */
    public boolean contains(E element){
        return indexOf(element) !=ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到尾部
     * @param element
     */
    public void add(E element){
        add(size,element);
    }

    /**
     * 取回index位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        rangeCheck(index);
        return  elements[index];
    }

    /**
     * 設置index位置的元素
     * @param index
     * @param element
     * @return 原來的元素
     */
    public E set(int index,E element){
        rangeCheck(index);
        E old = elements[index];
        elements[index]=element;
        return old;
    }

    /**
     * 在index位置插入一個元素
     * @param index
     * @param element
     */
    public void add(int index,E element){
        rangeCheckForAdd(index);
        ensureCapacity(size+1);
        for(int i=size-1;i>=index;i--){
            elements[i+1] =elements[i];
        }
        elements[index]=element;
        size++;

    }

    /**
     * 刪除index位置的元素
     * @param index
     * @return 原來的元素
     */
    public E remove(int index){
        rangeCheck(index);
        E old = elements[index];
        for (int i =index+1;i<=size-1;i++){
            elements[i-1] =elements[i];
        }
        //size先減1在清空 清掉最後一個
        elements[--size]=null;
        return  old;
    }

    /**
     * 查看元素的索引
     * @param element
     * @return
     */
    public int indexOf(E element){
        if (element ==null){
            for(int i=0;i<size;i++){
                if(elements[i]==null) return i;
            }
        }else{
            for(int i=0;i<size;i++){
                //Object是不能用==因為Object是先指向內存位置
                //equals不重寫的話是比內存
                if(element.equals(elements[i])) return i;
            }
        }

        return  ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        //size=3 [99,88,77]
        StringBuffer string = new StringBuffer();
        string.append("size=").append(size).append(",【");
        for (int i=0;i<size ;i++){
            if (i!=0){
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("】");
        return string.toString();
    }
    private void outOfBounds(int index){
        throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
    }
    private void rangeCheck(int index){
        if(index<0 ||index>=size) outOfBounds(index);
    }
    private void rangeCheckForAdd(int index){
        if(index<0 ||index>size) outOfBounds(index);
    }

    /**
     * 保證擁有capacity數量
     * @param capacity
     */
    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(oldCapacity>=capacity) return;
        //新容量為舊容量的1.5倍
        int newCapacity =oldCapacity+(oldCapacity>>1);
        E[] newElements = (E[]) new Object[newCapacity];
        for(int i=0 ; i<size;i++){
            newElements[i]=elements[i];
        }
        elements =newElements;
        System.out.println(oldCapacity+"擴容為"+newCapacity);
    }


}
