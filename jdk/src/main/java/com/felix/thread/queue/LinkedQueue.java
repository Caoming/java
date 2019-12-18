package com.felix.thread.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class LinkeQueue<T> implements Queue<T> {

    // 当前的大小
    private int size;

    private Node<T> frontNode;

    private Node<T> rearNode;

    // 链表大小
    private int capacity;

    public LinkeQueue(int capacity) {
        this.capacity = capacity;
    }

    class Node<T>{
        T t;
        Node<T> prexNode;
        Node<T> lastNode;

        Node(T t){
            this.t = t;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(frontNode == null){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
