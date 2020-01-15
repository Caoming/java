package com.felix.data.geek.time.linkedList;

import com.felix.data.geek.time.linkedList.impl.DoubleLinkedList;
import com.felix.data.geek.time.linkedList.impl.SingleLinkedList;
import org.junit.Test;

import java.util.Base64;
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
    public void testMerge(){
        SingleLinkedList<Integer> A = new SingleLinkedList<>();
        A.add(1);
        A.add(3);
        A.add(5);
        A.add(7);

        SingleLinkedList<Integer> B = new SingleLinkedList<>();
        B.add(2);
        B.add(4);
        B.add(6);
        B.add(8);

        SingleLinkedList<Integer> C = new SingleLinkedList<>();
        while (A.first != null && B.first != null){
            if(A.first.getItem() > B.first.getItem()){
                SingleLinkedList.Node<Integer> S = B.first.getNext();
                B.first.setNext(C.first);
                C.first = B.first;
                B.first = S;
            }else {
                SingleLinkedList.Node<Integer> S = A.first.getNext();
                A.first.setNext(C.first);
                C.first = A.first;
                A.first = S;
            }
        }

        while(A.first != null){
            SingleLinkedList.Node<Integer> S = A.first.getNext();
            A.first.setNext(C.first);
            C.first = A.first;
            A.first = S;
        }

        while(B.first != null){
            SingleLinkedList.Node<Integer> S = B.first.getNext();
            B.first.setNext(C.first);
            C.first = B.first;
            B.first = S;
        }
        for(int i = 0; i < C.modCount; i++){
            System.out.println();
        }
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

    @Test
    public void test(){
        byte[] encode = Base64.getEncoder().encode("ewewe12".getBytes());
        byte[] key = "sss".getBytes();
        byte[] result = new byte[encode.length];
        for (int i = 0;i < encode.length ; i++){
            result[i] = (byte) (encode[i] ^ key[i % key.length]);
        }
        String s = new String(result);
        System.out.println(s);

        byte[] sByte = s.getBytes();
        byte[] sResult = new byte[sByte.length];
        for (int i = 0;i < sByte.length ; i++){
            sResult[i] = (byte) (sByte[i] ^ key[i % key.length]);
        }

        System.out.println(new String(Base64.getDecoder().decode(new String(sResult))));
    }
}
