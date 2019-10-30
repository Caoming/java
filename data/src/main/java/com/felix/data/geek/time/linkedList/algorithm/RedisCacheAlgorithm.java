package com.felix.data.geek.time.linkedList.algorithm;

import com.felix.data.geek.time.linkedList.AbstractLinkedList;

/**
 * redis 缓存淘汰算法
 * 1、LRU：最近少使用算法
 * 2、FIFO：先进先出算法
 * 3、LFU：最少使用算法
 */
public class RedisCacheAlgorithm<T> extends AbstractLinkedList<T> {

    /**
     * LRU的实现，采用链表和散列表的数据结构
     * 链表存储对应的数据
     */

    //  容量因子
    private transient double capacity = 0.75;

    // 默认大小
    private transient int defaultCount = 4;



    public void add(T s){
        if(modCount == 0){
            this.first = new AbstractLinkedList.Node<>(s, null, null);
            modCount++;
            return;
        }

        if(modCount == 1){
            this.last = new AbstractLinkedList.Node<>(s,this.first,null);
            this.first.setNext(this.last);
            modCount++;
            return;
        }

        // 删除对应的数据，然后插入
        if(modCount/defaultCount >  capacity){
            //删除第一个数据
            this.first.getNext().setPrev(null);
            this.first = this.first.getNext();
        }else{// 新来的数据，放入到last中
            modCount++;
        }

        Node<T> tNode = new AbstractLinkedList.Node<>(s, this.last, null);
        this.last.setNext(tNode);
        this.last = tNode;
    }

    @Override
    public String toString(){
        if(this.first == null){
            return "";
        }

        AbstractLinkedList.Node<T> node = this.first;
        String s = node.getItem().toString();
        while (node.getNext() != null){
            s += node.getNext().getItem().toString();
            node = node.getNext();
        }
        return s;
    }

    public static void main(String[] args) {
        RedisCacheAlgorithm<Integer> algorithm = new RedisCacheAlgorithm<>();
        algorithm.add(1);
        algorithm.add(2);
        algorithm.add(3);
        algorithm.add(3);
        algorithm.add(4);
        algorithm.add(5);
        algorithm.add(6);
        algorithm.add(1);
        algorithm.add(3);
        System.out.println(algorithm.toString());

    }


}
