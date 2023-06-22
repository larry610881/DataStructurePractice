package com.Larry;

import com.Larry.print.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;
    private Boolean stop;
    public BinarySearchTree(){
        this(null);
    }
    public BinarySearchTree(Comparator<E> comparator){
        this.comparator =comparator;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void clear(){

    }
    public void add(E element){
        elementNotNullCheck(element);
        if(size==0){
            root = new Node<>(element,null);
            size++;
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

        Node<E> newNode = new Node<>(element,parent);
        if(cmp>0){
            parent.right = newNode;
        }else if(cmp<0){

            parent.left = newNode;
        }
        size++;
    }
    public void remove(E element){

    }
    public boolean contains(E element){
        return false;
    }

//    public void preorderTraversal(){
//        preorderTraversal(root);
//    }
//    private void preorderTraversal(Node<E> node){
//        if(node==null)return;
//        System.out.println(node.element);
//        preorderTraversal(node.left);
//        preorderTraversal(node.right);
//
//    }
//    public void inorderTraversal(){
//        inorderTraversal(root);
//    }
//    private void inorderTraversal(Node<E> node){
//
//        if(node==null)return;
//        inorderTraversal(node.left);
//        System.out.println(node.element);
//        inorderTraversal(node.right);
//
//    }
//    public void postorderTraversal(){
//        postorderTraversal(root);
//    }
//    private void postorderTraversal(Node<E> node){
//
//        if(node==null)return;
//        postorderTraversal(node.left);
//        postorderTraversal(node.right);
//        System.out.println(node.element);
//
//    }
//    public void levelOderTraversal(){
//        if (root==null) return;
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()){
//           Node<E> node = queue.poll();
//            System.out.println(node.element);
//            if(node.left!=null){
//                queue.offer(node.left);
//            }
//            if(node.right!=null){
//                queue.offer(node.right);
//            }
//        }
//
//    }
    public void preorder(Visitor<E> visitor){
        if(visitor ==null) return;
        preorder(root,visitor);
    }
    private void preorder(Node<E> node,Visitor<E> visitor){
        if(node==null||visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorder(node.left,visitor);
        preorder(node.right,visitor);
    }
    public void inorder(Visitor<E> visitor){
        if(visitor ==null) return;
        inorder(root,visitor);
    }
    private void inorder(Node<E> node,Visitor<E> visitor){
        if(node==null||visitor.stop) return;

        inorder(node.left,visitor);
        //不能移除 因為可能是因為上一行執行完的stack出來執行到這
        if(visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorder(node.right,visitor);
    }
    public void postorder(Visitor<E> visitor){
        if(visitor ==null) return;
        postorder(root,visitor);
    }
    private void postorder(Node<E> node,Visitor<E> visitor){
        if(node==null||visitor.stop) return;

        postorder(node.left,visitor);
        postorder(node.right,visitor);
        //不能移除 因為可能是因為上一行執行完的stack出來執行到這
        if(visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }
    public void levelOrder(Visitor<E> visitor){
        if(root==null || visitor ==null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(visitor.visit(node.element))return;
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
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


    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> target =((Node<E>)node);
        StringBuffer sb = new StringBuffer();
        sb.append("_p(");
        if(target.parent==null){
            sb.append("null");
        }else {
            sb.append(target.parent.element);
        }
        sb.append(")");
        return target.element+sb.toString();
    }
//    public void levelOrder(Visitor<E> visitor){
//        if (root==null) return;
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()){
//            Node<E> node = queue.poll();
//            visitor.visit(node.element);
//            if(node.left!=null){
//                queue.offer(node.left);
//            }
//            if(node.right!=null){
//                queue.offer(node.right);
//            }
//        }
//    }
    public static abstract class Visitor<E>{
        Boolean stop =false;
        /**
         * true停止遍歷
         * @param element
         * @return
         */
        abstract boolean  visit(E element);
    }

    private static class Node<E>{
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        public Node(E element,Node<E> parent){
            this.element =element;
            this.parent =parent;

        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        toString(root,sb,"");
        return sb.toString();
    }
    private void toString(Node<E> node,StringBuffer sb,String prefix){
        if(node==null) return;
        sb.append(prefix).append(node.element).append("\n");
        toString(node.left,sb,prefix+"L---");
        toString(node.right,sb,prefix+"R---");
    }

}
