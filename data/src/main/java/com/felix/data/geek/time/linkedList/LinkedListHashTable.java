package com.felix.data.geek.time.linkedList;

/**
 * 链表散列表的结构
 */
public class LinkedListHashTable<E> {

    private int modCount;

    private transient Node<E> first;

    private transient Node<E> last;

    public static class Node<E>{

        private E item;

        private Node<E> prev;

        private Node<E> next;

        private int hashkey;

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

        public int getHashkey() {
            return hashkey;
        }

        public void setHashkey(int hashkey) {
            this.hashkey = hashkey;
        }
    }
}
