package com.felix.data.geek.time.linkedList.impl;

import com.felix.data.geek.time.linkedList.AbstractLinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 单向链表反转
     * @param linkedList
     * @return
     */
    public SingleLinkedList<E> reverse(SingleLinkedList<E> linkedList){
        if(linkedList == null || linkedList.modCount == 0 || linkedList.modCount == 1){
            return linkedList;
        }

        Node<E> node = linkedList.first;

        // 存放链表的数据，双向链表实现起来更好
        Object[] arrays = new Object[linkedList.modCount];

        for(int i = 0; node!= null;i++){
            arrays[i] = node.getItem();
            node = node.getNext();
        }

        SingleLinkedList<E> newLinkedList = new SingleLinkedList<>();
        for(int i = 0 ; i < linkedList.modCount; i++){
            if(i == 0) {
                newLinkedList.last = new Node<E>((E) arrays[i], null, null);
                node = newLinkedList.last;
            }else if(i != 0){
                node = new Node<E>((E) arrays[i], null, node);
                if(i == linkedList.modCount -1){
                    newLinkedList.first = node;
                }
            }
        }

        return newLinkedList;

    }


    /**
     * 链表中是否有环
     * @param linkedList
     * @return
     */
    public boolean isLoopLinkedList(SingleLinkedList<E> linkedList){
        if(linkedList == null || linkedList.modCount ==  0 || linkedList.modCount == 1){
            return false;
        }

        Map<E,Integer> map = new HashMap<>();
        Node<E> node = linkedList.first;

        while (node != null){
            if(map.containsKey(node.getItem())){
                return true;
            }
            map.put(node.getItem(),1);
            node = node.getNext();
        }
        return false;

    }

    /**
     * 获取链表中间节点
     * @param linkedList
     * @return
     */
    public List<Node<E>> getMiddle(SingleLinkedList<E> linkedList){
        if(linkedList == null || linkedList.modCount < 3){
            throw new RuntimeException("没有任何意义");
        }

        List<Node<E>> list = new ArrayList<>();
        Node<E> oneNode = linkedList.first;
        Node<E> twoNode = linkedList.first.getNext();
        while (twoNode != null){
            if(twoNode.getNext()== null){
                list.add(oneNode);
                list.add(oneNode.getNext());
                return list;
            }else if(twoNode.getNext().getNext() == null){
                list.add(oneNode.getNext());
                return list;
            }
            oneNode = oneNode.getNext();
            twoNode = twoNode.getNext().getNext();
        }

        return list;
    }

    @Override
    public String toString(){
        String s = "";
        Node<E> node = this.first;
        while (node != null){
            s += node.getItem().toString();
            node = node.getNext();
        }
        return s;
    }


}
