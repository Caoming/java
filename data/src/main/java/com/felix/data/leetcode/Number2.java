package com.felix.data.leetcode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 *
 * 示例：
 *      输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *      输出：7 -> 0 -> 8
 *      原因：342 + 465 = 807
 */
public class Number2 {

    public static void main(String[] args) {
        ListNode<Integer> l1 = new ListNode<>(9);
        l1.next = new ListNode<>(4);
        l1.next.next = new ListNode<>(3);

        ListNode<Integer> l2 = new ListNode<>(6);
        l2.next = new ListNode<>(6);
        l2.next.next = new ListNode<>(4);
        l2.next.next.next = new ListNode<>(4);

        System.out.println(linkedSum(l1,l2));

    }

    /**
     * 解法1
     * 时间复杂度n
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode linkedSum(ListNode<Integer> l1,
                                ListNode<Integer> l2){
        if(l1 == null || l2 == null){
            throw new RuntimeException("参数有误");
        }

        ListNode listNode =null;
        int value = 0;
        int tmp = 0;
        for(int i = 0; l1 != null || l2 != null ; i++){
            int l1Value = 0;
            if(l1 != null){
                l1Value = l1.val;
            }

            int l2Value = 0;
            if(l2 != null){
                l2Value = l2.val;
            }

            int v = l1Value + l2Value;


            value += ((v+tmp) % 10) * Math.pow(10.0,i);
            if(listNode != null) {
                listNode = new ListNode(value);
            }else {
                listNode.next = new ListNode(value);
            }
            tmp = (v+tmp) / 10;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }


        return listNode;
    }

    public static class ListNode<E>{

        private E val;

        private ListNode<E> next;

        public ListNode(E e) {
            this.val = e;
        }

        public ListNode() {
        }
    }
}
