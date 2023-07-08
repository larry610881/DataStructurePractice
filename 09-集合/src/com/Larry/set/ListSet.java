package com.Larry.set;
import com.Larry.list.LinkedList;
import com.Larry.list.List;


public class ListSet<E> implements Set<E> {
    private List<E> list = new LinkedList<>();
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size()==0;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        int index = list.indexOf(element);
        if(index!= list.ELEMENT_NOT_FOUND){//存在就覆蓋
            list.set(index,element);
        }else{
            list.add(element);
        }
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != list.ELEMENT_NOT_FOUND) {
            list.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if(visitor==null)return;;
        int size =list.size();
        for(int i=0;i<size;i++){
            if(visitor.visit(list.get(i))) return;
        }
    }
}
