package com.felix.data.geek.time.linkedList.impl;

import com.felix.data.geek.time.linkedList.AbstractLinkedList;

/**
 * 双向链表的实现：增删查
 * @param <E>
 */
public class DoubleLinkedList<E> extends AbstractLinkedList<E> {

    /**
     * 双向链表添加数据
     * @param e
     */
    public void add(E e){
        if(modCount == 0){
            this.first = new AbstractLinkedList.Node<E>(e,null,null);
            modCount++;
            return;
        }

        if(modCount == 1){
            this.last = new AbstractLinkedList.Node<E>(e,this.first,null);
            this.first.setNext(this.last);
            modCount++;
            return;
        }

        AbstractLinkedList.Node<E> newNode = new AbstractLinkedList.Node<>(e,this.last,null);
        this.last.setNext(newNode);
        this.last = newNode;
        modCount++;
    }

    /**
     * 双向链表删除数据
     * @param e
     * @return
     */
    public boolean remove(E e){
        if(modCount == 0){
            throw new RuntimeException("链表为空");
        }

        AbstractLinkedList.Node<E> node = this.first;
        for(int i = 0; i < modCount; i++){
            if(node.getItem().equals(e)){
                if(i == 0){
                    this.first = node.getNext();
                    node.getNext().setPrev(null);
                    modCount--;
                    return true;
                }
                if(i == modCount -1){
                    this.last = node.getPrev();
                    node.getPrev().setNext(null);
                    modCount--;
                    return true;
                }
                node.getPrev().setNext(node.getNext());
                node.getNext().setPrev(node.getPrev());
                modCount--;
                return true;

            }
            node = node.getNext();
        }

        return false;

    }

    public E get(int index){
        if(this.first == null){
            throw new RuntimeException("改值不存在");
        }

        if(index >= modCount){
            throw  new RuntimeException("索引的值大于链表长度");
        }

        AbstractLinkedList.Node<E> newNode =this.first;
        for(int i = 0; i< index; i++){
            newNode = newNode.getNext();
        }

        return newNode.getItem();
    }
}
