package com.Larry.tree;


import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E>{
    protected int size;
    protected Node<E> root;
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void clear(){
        root =null;
        size=0;
    }

    public void preorder(Visitor<E> visitor){
        if(visitor ==null) return;
        preorder(root,visitor);
    }
    private void preorder(Node<E> node, Visitor<E> visitor){
        if(node==null||visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorder(node.left,visitor);
        preorder(node.right,visitor);
    }
    public void inorder(Visitor<E> visitor){
        if(visitor ==null) return;
        inorder(root,visitor);
    }
    private void inorder(Node<E> node, Visitor<E> visitor){
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
    private void postorder(Node<E> node, Visitor<E> visitor){
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
    //recursion
    public int height1(){
        return height1(root);
    }
    public int height1(Node<E> node){
        if (node==null)return 0;
        return 1+ Math.max(height1(node.left),height1(node.right));
    }
    public int height2(){
        if(root==null) return 0;
        int height =0;
        int levelSize =1;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            levelSize--;
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
            if(levelSize==0){//即將反問下一層
                levelSize =queue.size();
                height++;
            }

        }
        return height;
    }

    public boolean isComplete() {
        if(root==null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf =false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(leaf && !node.isLeaf()) return false;

            if(node.left!=null){
                queue.offer(node.left);
            }else if(node.right!=null) {
                //node.left ==null &&node.right !=null
                return false;
            }

            if(node.right!=null){
                queue.offer(node.right);
            }else{
                leaf=true;
            }


        }
        return false;
    }
    public static abstract class Visitor<E>{
        Boolean stop =false;
        /**
         * true停止遍歷
         * @param element
         * @return
         */
        public abstract boolean  visit(E element);
    }

    protected static class Node<E>{
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        public Node(E element,Node<E> parent){
            this.element =element;
            this.parent =parent;

        }
        public boolean isLeaf(){
            return left ==null&& right ==null;
        }
        public boolean hasTwoChildren(){
            return left !=null&& right !=null;
        }
        public boolean isLeftChild(){
            return parent!=null &&this ==parent.left;
        }
        public boolean isRightChild(){
            return parent!=null &&this ==parent.right;
        }
        public Node<E> sibling(){
            if(isLeftChild()){
                return parent.right;
            }else if(isRightChild()){
                return  parent.left;
            }else {
                return null;
            }
        }
    }

    protected Node<E> predecessor(Node<E> node){
        if(node ==null) return null;
        //前驅節點在左子樹
        Node<E> p = node.left;
        if(p!=null){

            while (p.right!=null){
                p = p.right;
            }
            return p;
        }
        //從祖父節點中尋找
        while (node.parent!=null && node == node.parent.left){
            node = node.parent;
        }
        //node.parent ==null
        //node ==node.parent.right
        return node.parent;
    }

    protected Node<E> successor(Node<E> node){
        if(node ==null) return null;
        //前驅節點在左子樹
        Node<E> p = node.right;
        if(p!=null){

            while (p.left!=null){
                p = p.left;
            }
            return p;
        }
        //從祖父節點中尋找
        while (node.parent!=null && node == node.parent.right){
            node = node.parent;
        }
        //node.parent ==null
        //node ==node.parent.left
        return node.parent;
    }
    protected Node<E> createNode(E element,Node<E> parent){
        return new Node<>(element,parent);
    }

}
