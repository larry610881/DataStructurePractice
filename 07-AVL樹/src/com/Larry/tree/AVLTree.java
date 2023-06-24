package com.Larry.tree;

import java.util.Comparator;

public class AVLTree<E> extends BST<E>{
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
    private void rotateL(Node<E> grand){
        Node<E> parent =grand.right;
        Node<E> child = parent.left;
        grand.right =child;
        parent.left =grand;

        afterRotate(grand,parent,child);
    }
    private void rotateR(Node<E> grand){
        Node<E> parent =grand.left;
        Node<E> child = parent.right;
        grand.left =child;
        parent.right =grand;

        afterRotate(grand,parent,child);
    }
    private void afterRotate(Node<E> grand,Node<E> parent,Node<E> child){
        parent.parent =grand.parent;
        if(grand.isLeftChild()){
            grand.parent.left =parent;
        }else if(grand.isRightChild()){
            grand.parent.right =parent;
        }else { //root
            root =parent;
        }
        //更新child 的 parent
        if(child!=null){
            child.parent =grand;
        }
        //更新grand 的 parent
        grand.parent =parent;
        updateHeight(grand);
        updateHeight(parent);

    }

}
