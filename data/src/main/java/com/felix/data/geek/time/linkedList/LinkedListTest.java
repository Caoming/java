package com.felix.data.geek.time.linkedList;

import com.felix.data.geek.time.linkedList.impl.DoubleLinkedList;
import com.felix.data.geek.time.linkedList.impl.SingleLinkedList;
import org.junit.Test;

import java.util.List;

public class LinkedListTest {

    @Test
    public void testSingleLinkedList(){
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);

        singleLinkedList.remove(2);

        System.out.println(singleLinkedList.get(2));
        System.out.println(singleLinkedList.size());
    }

    @Test
    public void testDoubleLinkList(){
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();
        doubleLinkedList.add(1);
        doubleLinkedList.add(2);
        doubleLinkedList.add(3);
        doubleLinkedList.add(4);

        doubleLinkedList.remove(2);

        System.out.println(doubleLinkedList.get(2));
        System.out.println(doubleLinkedList.size());
    }

    @Test
    public void testReverse(){
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);
        System.out.println(singleLinkedList.reverse(singleLinkedList).toString());

    }

    @Test
    public void testLoop(){
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);
//        singleLinkedList.last.setNext(singleLinkedList.first);
        System.out.println(singleLinkedList.isLoopLinkedList(singleLinkedList));
    }

    /**
     * 找中间节点
     */
    @Test
    public void testMiddle(){
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);
        singleLinkedList.add(5);
        singleLinkedList.add(6);

        List<AbstractLinkedList.Node<Integer>> middleList = singleLinkedList.getMiddle(singleLinkedList);
        for(AbstractLinkedList.Node<Integer> node : middleList){
            System.out.println(node.getItem());
        }

    }
}
