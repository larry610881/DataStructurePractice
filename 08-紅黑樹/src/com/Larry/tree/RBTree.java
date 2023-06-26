package com.Larry.tree;

import java.util.Comparator;

public class RBTree<E> extends BBST<E> {
    private static final boolean RED =false;
    private static final boolean BLACK =true;
    public RBTree(){
        this(null);

    }
    public RBTree(Comparator<E> comparator){
        super(comparator);

    }
    private Node<E> color(Node<E> node,boolean color){
        if(node ==null)return node;
        ((RBNode<E>)node).color =color;
        return node;

    }
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }
    private boolean colorOf(Node<E> node){
        return  node==null?BLACK:((RBNode<E>)node).color;
    }
    private boolean isBlack(Node<E> node){
        return colorOf(node)==BLACK;
    }
    private boolean isRed(Node<E> node){
        return colorOf(node)==RED;
    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent =node.parent;
        //添加的是root
        //同時處理上溢到根結點需要染黑的操作
        if(parent==null) {
            black(node);
            return;
        }
        //父節點是黑
        if(isBlack(parent))return;

        Node<E> uncle =parent.sibling();
        Node<E> grand =red(parent.parent);
        if(isRed(uncle)){
            black(parent);
            black(uncle);
            //grand 當成是新添加的節點
            afterAdd(grand);
            return;
        }
        //叔父節點不是紅色
        if(parent.isLeftChild()){
            if(node.isLeftChild()){//LL
                black(parent);
            }else{//LR
                black(node);
                rotateL(parent);
            }
            rotateR(grand);
        }else{
            if(node.isLeftChild()){//RL
                black(node);
                rotateR(parent);
            }else{//RR
                black(parent);
            }
            rotateL(grand);
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E>{
        boolean color =RED;
        public RBNode(E element,Node<E> parent){
            super(element,parent);
        }

        @Override
        public String toString() {
            String  s="";
            if(color ==RED){
                s ="R_";
            }
            return s+element.toString();
        }
    }
}
