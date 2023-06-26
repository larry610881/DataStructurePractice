package com.Larry.tree;

import java.util.Comparator;

public class AVLTree<E> extends BBST<E>{
    public AVLTree(){
        this(null);

    }
    public AVLTree(Comparator<E> comparator){
        super(comparator);

    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node =node.parent)!=null){
            if(isBalance(node)){
                ///更新高度
                updateHeight(node);
            }else{
                //恢復平衡
                rebalance(node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node =node.parent)!=null){
            if(isBalance(node)){
                ///更新高度
                updateHeight(node);
            }else{
                //恢復平衡
                rebalance(node);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private static class AVLNode<E> extends Node<E>{
        int height =1;
        public AVLNode(E element,Node<E> parent){
            super(element,parent);

        }
        public int balanceFactor(){
            int leftHeight =left ==null? 0:((AVLNode<E>)left).height;
            int rightHeight =right ==null? 0:((AVLNode<E>)right).height;
            return leftHeight-rightHeight;
        }
        public void updateHeight(){
            int leftHeight =left ==null? 0:((AVLNode<E>)left).height;
            int rightHeight =right ==null? 0:((AVLNode<E>)right).height;
            height =1+Math.max(leftHeight,rightHeight);
        }
        public Node<E> tallerChild(){
            int leftHeight =left ==null? 0:((AVLNode<E>)left).height;
            int rightHeight =right ==null? 0:((AVLNode<E>)right).height;
            if(leftHeight>rightHeight) return left;
            if(leftHeight<rightHeight) return right;
            return isLeftChild()?left:right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if(parent!=null){
                parentString =parent.element.toString();
            }
            return element+"_p("+parentString+")_h("+height+")";
        }
    }
    private boolean isBalance(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <=1;
    }
    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);

        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);

        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    /**
     *
     * @param grand 高度最低的不平衡節點
     */
        private void rebalance(Node<E> grand){
        Node<E> parent =((AVLNode<E>)grand).tallerChild();
        Node<E> node =((AVLNode<E>)parent).tallerChild();
        if(parent.isLeftChild()){
            if(node.isLeftChild()){
                //LL
                rotate(grand, node.left,node,node.right,parent,parent.right,grand,grand.right);
            }else{
                //LR
                rotate(grand, parent.left,parent,node.left,node,node.right,grand,grand.right);
            }
        }else{
            if(node.isLeftChild()){
                //RL
                rotate(grand, grand.left,grand,node.left,node,node.right,parent,parent.right);
            }else{
                //RR
                rotate(grand,grand.left,grand,parent.left,parent,node.left,node,node.right);
            }
        }
    }
    private void rebalance2(Node<E> grand){
        Node<E> parent =((AVLNode<E>)grand).tallerChild();
        Node<E> node =((AVLNode<E>)parent).tallerChild();
        if(parent.isLeftChild()){
            if(node.isLeftChild()){
                //LL
                rotateR(grand);
            }else{
                //LR
                rotateL(parent);
                rotateR(grand);
            }
        }else{
            if(node.isLeftChild()){
                //RL
                rotateR(parent);
                rotateL(grand);
            }else{
                //RR
                rotateL(grand);
            }
        }
    }


}
