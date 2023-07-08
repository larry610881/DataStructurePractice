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
        //如果刪除的節點是紅色
        //if(isRed(node)) return;
        //如果刪除的節點是紅色 用以取代node的子節點是紅色
        if(isRed(node)){
            black(node);
            ;return;
        }
        Node<E> parent = node.parent;
        //刪除的是根結點
        if(parent ==null) return;
        //刪除的是黑色葉子節點
        boolean left =parent.left ==null|| node.isLeftChild();
        Node<E> sibling  = left? parent.right:parent.left;
        if(left){//被刪除的節點在左邊，兄弟節點在右邊
            if(isRed(sibling)){//兄弟節點是紅色
                black(sibling);
                red(parent);
                rotateR(parent);
                //更換兄弟
                sibling =parent.right;
            }
            //兄弟節點必然是黑色
            if(isBlack(sibling.left)&&isBlack(sibling.right)){
                //兄弟節點沒有一個紅色子節點 p.s.只能父節點向下合併
                boolean parentBlack =isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){
                    afterRemove(parent);
                }
            }else {//兄弟節點至少有一個紅色子節點 p.s.才可以借
                //右邊是黑色 兄弟要先右旋轉
                if(isBlack(sibling.right)){
                    rotateR(sibling);
                    sibling =parent.right;
                }
                color(sibling,colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateL(parent);

            }
        }else{//被刪除的節點在右邊，兄弟節點在左邊
            if(isRed(sibling)){//兄弟節點是紅色
                black(sibling);
                red(parent);
                rotateR(parent);
                //更換兄弟
                sibling =parent.left;
            }
            //兄弟節點必然是黑色
            if(isBlack(sibling.left)&&isBlack(sibling.right)){
                //兄弟節點沒有一個紅色子節點 p.s.只能父節點向下合併
                boolean parentBlack =isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){
                    afterRemove(parent);
                }
            }else {//兄弟節點至少有一個紅色子節點 p.s.才可以借
                //左邊是黑色 兄弟要先左旋轉
                if(isBlack(sibling.left)){
                    rotateL(sibling);
                    sibling =parent.left;
                }
                color(sibling,colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateR(parent);

            }
        }

    }
//    protected void afterRemove(Node<E> node,Node<E> replacement) {
//        //如果刪除的節點是紅色
//        if(isRed(node)) return;
//        //用以取代node的子節點是紅色
//        if(isRed(replacement)){
//            black(replacement);
//            ;return;
//        }
//        Node<E> parent = node.parent;
//        //刪除的是根結點
//        if(parent ==null) return;
//        //刪除的是黑色葉子節點
//        boolean left =parent.left ==null|| node.isLeftChild();
//        Node<E> sibling  = left? parent.right:parent.left;
//        if(left){//被刪除的節點在左邊，兄弟節點在右邊
//            if(isRed(sibling)){//兄弟節點是紅色
//                black(sibling);
//                red(parent);
//                rotateR(parent);
//                //更換兄弟
//                sibling =parent.right;
//            }
//            //兄弟節點必然是黑色
//            if(isBlack(sibling.left)&&isBlack(sibling.right)){
//                //兄弟節點沒有一個紅色子節點 p.s.只能父節點向下合併
//                boolean parentBlack =isBlack(parent);
//                black(parent);
//                red(sibling);
//                if(parentBlack){
//                    afterRemove(parent,null);
//                }
//            }else {//兄弟節點至少有一個紅色子節點 p.s.才可以借
//                //右邊是黑色 兄弟要先右旋轉
//                if(isBlack(sibling.right)){
//                    rotateR(sibling);
//                    sibling =parent.right;
//                }
//                color(sibling,colorOf(parent));
//                black(sibling.right);
//                black(parent);
//                rotateL(parent);
//
//            }
//        }else{//被刪除的節點在右邊，兄弟節點在左邊
//            if(isRed(sibling)){//兄弟節點是紅色
//                black(sibling);
//                red(parent);
//                rotateR(parent);
//                //更換兄弟
//                sibling =parent.left;
//            }
//            //兄弟節點必然是黑色
//            if(isBlack(sibling.left)&&isBlack(sibling.right)){
//                //兄弟節點沒有一個紅色子節點 p.s.只能父節點向下合併
//                boolean parentBlack =isBlack(parent);
//                black(parent);
//                red(sibling);
//                if(parentBlack){
//                    afterRemove(parent,null);
//                }
//            }else {//兄弟節點至少有一個紅色子節點 p.s.才可以借
//                //左邊是黑色 兄弟要先左旋轉
//                if(isBlack(sibling.left)){
//                    rotateL(sibling);
//                    sibling =parent.left;
//                }
//                color(sibling,colorOf(parent));
//                black(sibling.left);
//                black(parent);
//                rotateR(parent);
//
//            }
//        }
//
//    }

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
