package com.Larry.map;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMap<K,V> implements Map<K,V> {
    private static final boolean RED =false;
    private static final boolean BLACK =true;

    private int size;
    private Node<K,V> root;
    private Comparator<K> comparator;
    public TreeMap(){
        this(null);
    }
    public TreeMap(Comparator<K> comparator){
        this.comparator =comparator;
    }

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

    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);
        if(root ==null){
            root = new Node<>(key,value,null);
            size++;
            afterPut(root);
            return null;
        }
        //添加的不是第一個節點
        //找到父節點
        Node<K,V> parent=root;
        Node<K,V> node =root;

        int cmp = 0;
        while (node!=null){
            cmp= compare(key, node.key);
            parent =node;
            if(cmp>0){
                node =node.right;
            }else if(cmp<0){
                node =node.left;
            }else {
                //如果是自定義對象 他們可能會是不同的實體
                node.key=key;
                V oldValue =node.value;
                node.value =value;
                return oldValue;
            }
        }

        Node<K,V> newNode = new Node<>(key,value,parent);
        if(cmp>0){
            parent.right = newNode;
        }else if(cmp<0){

            parent.left = newNode;
        }
        size++;
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K,V> node = node(key);
        return node!=null? node.value:null;

    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key)!=null;
    }

    @Override
    public boolean containsValue(V value) {
        if(root ==null) return false;
        Queue<Node<K,V>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<K,V> node = queue.poll();
            if(valEquals(value,node.value)) return true;
            if(node.left!=null)
                queue.offer(node.left);
            if(node.right!=null)
                queue.offer(node.right);
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if(visitor==null) return;
        traversal(root,visitor);
    }
    private void traversal(Node<K,V> node,Visitor<K, V> visitor) {
        if(node==null || visitor.stop) return;
        traversal(node.left,visitor);
        if(visitor.stop)return;
        visitor.visit(node.key,node.value);
        traversal(node.right,visitor);
    }
    private boolean valEquals(V v1,V v2){
        return v1 ==null? v2==null:v1.equals(v2);
    }
    private static class Node<K,V>{
        K key;
        V value;
        boolean color =RED;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;
        public Node(K key,V value, Node<K,V> parent){
            this.key =key;
            this.value =value;
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
        public Node<K,V> sibling(){
            if(isLeftChild()){
                return parent.right;
            }else if(isRightChild()){
                return  parent.left;
            }else {
                return null;
            }
        }
    }
    private void keyNotNullCheck(K key){
        if (key ==null){
            throw new  IllegalArgumentException("element must not be null");
        }
    }
    private void afterPut(Node<K,V> node){
        Node<K,V> parent =node.parent;
        //添加的是root
        //同時處理上溢到根結點需要染黑的操作
        if(parent==null) {
            black(node);
            return;
        }
        //父節點是黑
        if(isBlack(parent))return;

        Node<K,V> uncle =parent.sibling();
        Node<K,V> grand =red(parent.parent);
        if(isRed(uncle)){
            black(parent);
            black(uncle);
            //grand 當成是新添加的節點
            afterPut(grand);
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
    private int compare(K k1, K k2){
        if(comparator!=null){
            return comparator.compare(k1,k2);
        }
        return ((Comparable<K>)k1).compareTo(k2);
    }
    private Node<K,V> color(Node<K,V> node, boolean color){
        if(node ==null)return node;
        node.color =color;
        return node;

    }
    private Node<K,V> red(Node<K,V> node){
        return color(node,RED);
    }
    private Node<K,V> black(Node<K,V> node){
        return color(node,BLACK);
    }
    private boolean colorOf(Node<K,V> node){
        return  node==null?BLACK:node.color;
    }
    private boolean isBlack(Node<K,V> node){
        return colorOf(node)==BLACK;
    }
    private boolean isRed(Node<K,V> node){
        return colorOf(node)==RED;
    }
    private void rotateL(Node<K,V> grand){
        Node<K,V> parent =grand.right;
        Node<K,V> child = parent.left;
        grand.right =child;
        parent.left =grand;

        afterRotate(grand,parent,child);
    }
    private void rotateR(Node<K,V> grand){
        Node<K,V> parent =grand.left;
        Node<K,V> child = parent.right;
        grand.left =child;
        parent.right =grand;

        afterRotate(grand,parent,child);
    }
    private void afterRotate(Node<K,V> grand, Node<K,V> parent, Node<K,V> child){
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


    }
    private Node<K,V> node(K key){
        Node<K,V> node =root;
        while (node!=null){
            int cmp = compare(key,node.key);
            if(cmp==0) return  node;
            if(cmp>0){
                node =node.right;
            }else {
                node =node.left;
            }
        }
        return null;
    }
    private V remove(Node<K,V>node){
        if(node==null) return null;
        size--;
        V oldValue = node.value;
        if(node.hasTwoChildren()){
            Node<K,V> s = successor(node);
            node.key = s.key;
            node.value = s.value;
            //刪除度為2實際上要移除內存的節點是前繼或後繼，因此將node指定為前繼或後繼即可
            node = s;

        }
        //只要刪除node就好 能到這node度必為1 or 0
        Node<K,V> replacement = node.left!=null? node.left :node.right;

        if(replacement !=null){//刪除度為1的節點的時候
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
            afterRemove(replacement);
        }else if(node.parent ==null){//node為葉子節點 且是跟節點
            root =null;
        }else{
            if(node == node.parent.left){
                node.parent.left =null;
            }else{
                node.parent.right =null;
            }
            afterRemove(node);
        }
        return oldValue;
    }
    private void afterRemove(Node<K,V> node) {
        //如果刪除的節點是紅色
        //if(isRed(node)) return;
        //如果刪除的節點是紅色 用以取代node的子節點是紅色
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<K, V> parent = node.parent;
        if(parent ==null) return;
        //刪除的是黑色葉子節點
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) {//被刪除的節點在左邊，兄弟節點在右邊
            if (isRed(sibling)) {//兄弟節點是紅色
                black(sibling);
                red(parent);
                rotateR(parent);
                //更換兄弟
                sibling = parent.right;
            }
            //兄弟節點必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //兄弟節點沒有一個紅色子節點 p.s.只能父節點向下合併
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else {//兄弟節點至少有一個紅色子節點 p.s.才可以借
                //右邊是黑色 兄弟要先右旋轉
                if (isBlack(sibling.right)) {
                    rotateR(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateL(parent);

            }
        } else {//被刪除的節點在右邊，兄弟節點在左邊
            if (isRed(sibling)) {//兄弟節點是紅色
                black(sibling);
                red(parent);
                rotateR(parent);
                //更換兄弟
                sibling = parent.left;
            }
            //兄弟節點必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //兄弟節點沒有一個紅色子節點 p.s.只能父節點向下合併
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else {//兄弟節點至少有一個紅色子節點 p.s.才可以借
                //左邊是黑色 兄弟要先左旋轉
                if (isBlack(sibling.left)) {
                    rotateL(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateR(parent);

            }
        }
    }
    private Node<K,V> predecessor (Node<K,V> node) {
        if (node == null) return null;
        //前驅節點在左子樹
        Node<K,V> p = node.left;
        if (p != null) {

            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        //從祖父節點中尋找
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        //node.parent ==null
        //node ==node.parent.right
        return node.parent;
    }

    private Node<K,V> successor (Node<K,V> node) {
        if (node == null) return null;
        //前驅節點在左子樹
        Node<K,V> p = node.right;
        if (p != null) {

            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        //從祖父節點中尋找
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        //node.parent ==null
        //node ==node.parent.left
        return node.parent;
    }

}
