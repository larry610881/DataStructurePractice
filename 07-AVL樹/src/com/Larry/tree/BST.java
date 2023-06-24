package com.Larry.tree;

import java.util.Comparator;

public class BST<E>extends BinaryTree<E>  {

    private Comparator<E> comparator;
    private Boolean stop;
    public BST(){
        this(null);
    }
    public BST(Comparator<E> comparator){
        this.comparator =comparator;
    }

    public void add(E element){
        elementNotNullCheck(element);
        if(size==0){
            root = createNode(element,null);
            size++;
            afterAdd(root);
            return;
        }
        //添加的不是第一個節點
        //找到父節點
        Node<E> parent=root;
        Node<E> node =root;

        int cmp = 0;
        while (node!=null){
            cmp= compare(element, node.element);
            parent =node;
            if(cmp>0){
                node =node.right;
            }else if(cmp<0){
                node =node.left;
            }else {
                //如果是自定義對象 他們可能會是不同的實體
                node.element =element;
                return;
            }
        }

        Node<E> newNode = createNode(element,parent);
        if(cmp>0){
            parent.right = newNode;
        }else if(cmp<0){

            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    /**
     * 添加node之後的調整
     * @param node 新添加的節點
     */
    protected void afterAdd(Node<E> node){  }
    protected void afterRemove(Node<E> node){  }
    public void remove(E element){
        remove(node(element));

    }
    private void remove(Node<E> node){
        if(node==null) return;
        size--;
        if(node.hasTwoChildren()){
         Node<E> s = successor(node);
         node.element = s.element;
         //刪除度為2實際上要移除內存的節點是前繼或後繼，因此將node指定為前繼或後繼即可
         node = s;

        }
        //只要刪除node就好 能到這node度必為1 or 0
        Node<E> replacement = node.left!=null? node.left :node.right;

        if(replacement !=null){
            //更改parent
            replacement.parent =node.parent;
            if(node.parent==null){
                root =replacement;
            }else if(node ==node.parent.left){
                node.parent.left =replacement;
            }
            else {
                node.parent.right =replacement;
            }
        }else if(node.parent ==null){//node為葉子節點 且是跟節點
            root =null;
        }else{
            if(node == node.parent.left){
                node.parent.left =null;
            }else{
                node.parent.right =null;
            }
        }
        afterRemove(node);
    }
    private Node<E> node(E element){
        Node<E> node =root;
        while (node!=null){
            int cmp = compare(element,node.element);
            if(cmp==0) return  node;
            if(cmp>0){
                node =node.right;
            }else {
                node =node.left;
            }
        }
        return null;
    }
    public boolean contains(E element){
        return node(element)!=null;
    }


    /**
     *
     * @param e1
     * @param e2
     * @return 等於0代表 e1等於e2 ,大於0 代表 e1大於e2 ,小於0 e1小於e2
     */
    private int compare(E e1,E e2){
        if(comparator!=null){
            //使用外面傳入的比較器
            return comparator.compare(e1,e2);
        }
        //不用外面的比較器,但是能到這邊要強制能夠有可比性的interface
        return ((Comparable<E>)e1).compareTo(e2);
    }
    private void elementNotNullCheck(E element){
        if (element ==null){
            throw new  IllegalArgumentException("element must not be null");
        }
    }




}
