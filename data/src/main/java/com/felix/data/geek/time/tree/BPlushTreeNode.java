package com.felix.data.geek.time.tree;

public class BPlushTreeNode<E> {
    /**
     *
     *
     * 这是B+ tree 非叶子结点的定义：
     * 这是B+树非叶子节点的定义。
     *
     * 假设keywords=[3, 5, 8, 10]
     * 4个键值将数据分为5个区间：(-INF,3), [3,5), [5,8), [8,10), [10,INF)
     * 5个区间分别对应：children[0]...children[4]
     *
     *
     * m值是实现计算得出，计算的依据是让所有信息的大小正好等于页的大小：
     * PageSize=(m-1) * 4[keyword大小] + m * 8[children大小]
     */
    private static int m = 5;// 5叉树
    private int[] keywords = new int[m-1];// 划分数据区间
    public BPlushTreeNode[] treeNodes = new BPlushTreeNode[m];// 保存子结点的指针

    /**
     * 这是B+树中叶子节点的定义。
     * B+树中的叶子节点跟内部结点是不一样的,
     * 叶子节点存储的是值，而非区间。
     * 这个定义里，每个叶子节点存储3个数据行的键值及地址信息。
     * k值是事先计算得到的，计算的依据是让所有信息的大小正好等于页的大小：
     * PAGE_SIZE = k*4[keyw..大小]+k*8[dataAd..大小]+8[prev大小]+8[next大小]
     * @param <E>
     */
    public static class BPlushTreeLeafNode<E>{
        private static int k;
        private int[] keywords = new int[k];
        private long[] dataAddress = new long[k];

        private BPlushTreeLeafNode prex;
        private BPlushTreeLeafNode next;

    }
}
