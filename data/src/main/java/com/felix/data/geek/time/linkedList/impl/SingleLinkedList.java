package com.felix.data.geek.time.linkedList.impl;

import com.felix.data.geek.time.linkedList.AbstractLinkedList;

/**
 * 单向链表，实现方法增删查
 *
 * @param <E>
 */
public class SingleLinkedList<E> extends AbstractLinkedList<E> {

    /**
     * 添加的方法
     * @param e
     * @return
     */
    public boolean add(E e){
        if(this.modCount == 0){
            this.first = new AbstractLinkedList.Node<E>(e,null,null);
            modCount++;
            return true;
        }

        AbstractLinkedList.Node<E> newNode = new AbstractLinkedList.Node<>(e,null,null);
        if(this.last == null){
            this.first.setNext(newNode);
            this.last = newNode;
        }else{
            this.last.setNext(newNode);
            this.last = newNode;
        }

        modCount++;
        return true;
    }

    /**
     * 指定的删除的方法
     * @param e
     * @return
     */
    public boolean remove(E e){
        if(this.modCount == 0){
            return false;
        }


        for(int i = 0; i < this.modCount; i++){
            if(get(i).equals(e)){
                // 删除是第一个元素
                if(i == 0){
                    this.first = null;
                    modCount--;
                    return true;
                }

                // 删除的是最后一个元素
                if(i == modCount-1){
                    this.last = new AbstractLinkedList.Node<E>(get(i-1),null,null);
                    modCount--;
                    return true;
                }

                // 删除的中间元素
                AbstractLinkedList.Node<E> prevNode = this.first;
                for(int j = 1; j<= i-1; j++){
                    prevNode = prevNode.getNext();

                }

                // 前一个元素指向后一个元素
                prevNode.setNext(prevNode.getNext().getNext());
                modCount--;
                return true;

            }
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
