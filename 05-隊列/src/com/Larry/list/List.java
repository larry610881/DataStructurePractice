package com.Larry.list;

public interface List<E> {
    static final int ELEMENT_NOT_FOUND=-1;
    /**
     * 移除所有元素
     * p.s.用size限制訪問範圍因此無須實際清除所有內存的資料
     */
    void clear();

    /**
     * 元素的數量
     * @return
     */
    int size();

    /**
     * 是否為空
     * @return
     */
    boolean isEmpty();

    /**
     * 是否包含某個元素
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * 添加元素到尾部
     * @param element
     */
    void add(E element);

    /**
     * 取回index位置的元素
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 設置index位置的元素
     * @param index
     * @param element
     * @return 原來的元素
     */
    E set(int index,E element);

    /**
     * 在index位置插入一個元素
     * @param index
     * @param element
     */
    void add(int index,E element);

    /**
     * 刪除index位置的元素
     * @param index
     * @return 原來的元素
     */
    E remove(int index);

    /**
     * 查看元素的索引
     * @param element
     * @return
     */
    int indexOf(E element);
}
