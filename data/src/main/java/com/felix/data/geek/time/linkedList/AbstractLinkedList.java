package com.felix.data.geek.time.linkedList;

/**
 * 链表的数据结构：单链表、双向链表、循环链表、循环双向链表
 */
public abstract class AbstractLinkedList<E> {

    /**
     * 链表的总数
     */
    public transient int modCount;

    /**
     * 最后一个对象
     */
    public transient Node<E> last;

    /**
     * 第一个指向的对象
     */
    public transient Node<E> first;


    public int size(){
        return modCount;
    }

    /**
     * 双向链表的结构，单向链表少prev的属性
     * @param <E>
     */
    public static class Node<E>{
        private E item;

        private Node<E> prev;

        private Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public Node(){

        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
