package com.felix.data.geek.time.linkedList;

import com.felix.data.geek.time.linkedList.impl.DoubleLinkedList;
import com.felix.data.geek.time.linkedList.impl.SingleLinkedList;
import org.junit.Test;

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
}
