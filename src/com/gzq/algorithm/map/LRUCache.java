package com.gzq.algorithm.map;

import java.util.HashMap;

//146
//自定义实现HashMap+双向链表的缓存机制
public class LRUCache {
    //定义双向链表的节点类
    class Node {
        int key;
        int value;
        Node next;
        Node pre; //指向前一个节点的指针

        public Node() {

        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    //定义哈希表
    private HashMap<Integer, Node> hashMap = new HashMap<Integer, Node>();
    //定义属性
    private int capacity;
    private int size;
    //定义头尾指针
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        //用哑节点定义烧饼，方便统一处理
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    //get方法
    public int get(int key) {
        //从哈希表中查找key，如果不存在就返回-1
        Node node = hashMap.get(key);
        if (node == null) {
            return -1;
        }
        //如果存在，当前节点移到链表末尾
        moveToTail(node);
        return node.value;
    }

    //put操作
    public void put(int key, int value) {
        //同样先在哈希表中查找key
        Node node = hashMap.get(key);
        //如果key存在，修改value，并移到末尾
        if (node != null) {
            node.value = value;
            moveToTail(node);
        }
        //如果不存在,创建新的节点插入到末尾
        else {
            Node newNode = new Node(key, value);
            hashMap.put(key, newNode);//保存进hashmap
            addToTail(newNode);//添加到双向链表的末尾
            size++;//当前size增大
            //如果超出了容量限制，删除链表头结点
            if (size > capacity) {
                Node head = removeHead();
                hashMap.remove(head.key);
                size--;
            }
        }
    }

    //移动节点到链表末尾
    private void moveToTail(Node node) {
        removeNode(node);
        addToTail(node);
    }

    //通用方法，删除链表的某一个节点
    private void removeNode(Node node) {
        //跳过当前node
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    //在链表末尾增加一个节点
    private void addToTail(Node node) {
        node.next = tail;
        node.pre = tail.pre;//以原先的末尾节点作为前一个节点
        tail.pre.next = node;
        tail.pre = node;
    }

    //删除头结点
    private Node removeHead() {
        Node relHead = head.next;
        removeNode(relHead);
        return relHead;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
