package com.felix.thread.queue;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * 顺序队列
 */
public class SeqQueue<T> implements Queue<T> {
    // 元素数组
    private T[] elementData;
    // 第一个元素，和最后一个元素的角标
    private int front,rear;

    private static int DEFAULT_SIZE = 16;

    // 当前队列大小
    private int size;


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return ArrayUtils.contains(elementData,o);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    /**
     * 添加信息，添加信息为空，不报错，返回false
     * @param t
     * @return
     */
    @Override
    public boolean add(T t) {
        if(size < DEFAULT_SIZE){
            elementData[size++] = t;
            rear++;
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    /**
     * 添加信息，添加信息为空，报错，列表不够报错
     * @param o
     * @return
     */
    @Override
    public boolean offer(T o) {
        if(o == null){
            throw new NullPointerException();
        }
        if(size < DEFAULT_SIZE){
            elementData[size++] = o;
            rear++;
            return true;
        }else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 删除头列信息，不存在报错，返回删除信息
     * @return
     */
    @Override
    public T remove() {
        if(elementData == null || elementData.length == 0){
            throw new NoSuchElementException();
        }
        T t = elementData[front];
        elementData[front++] = null;
        size--;
        return t;
    }

    /**
     * 删除头列信息，不存在返回空，返回删除信息
     * @return
     */
    @Override
    public T poll() {
        if(elementData == null || elementData.length == 0){
            return null;
        }
        T t = elementData[front];
        elementData[front++] = null;
        size--;
        return t;
    }

    /**
     * 返回头列信息，不存在报错
     * @return
     */
    @Override
    public T element() {
        if(elementData == null || elementData.length == 0){
            throw new NoSuchElementException();
        }
        return elementData[front];
    }

    /**
     * 返回头列信息，不存在返回空
     * @return
     */
    @Override
    public T peek() {
        if(elementData == null || elementData.length == 0){
            return null;
        }

       return  elementData[front];
    }
}
