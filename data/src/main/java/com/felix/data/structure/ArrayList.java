package com.felix.data.structure;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 顺序存储结构实现线性表
 */
public class ArrayList<E> implements Serializable{
    private static final long serialVersionUID = 8788671345773468101L;

    // 存储数据的数组
    private Object[] elementDatas = {};

    // 初始化的大小
    private int initSize = 20;

    // 现在的大小
    private int currSize = 0;

    public ArrayList() {
        initList();
    }

    /**
     * 初始化
     * @return
     */
    public void initList(){
        elementDatas = new Object[initSize];
    }

    /**
     * 销毁队列
     */
    public void destroyList(){
        currSize = 0;
        for(Object o : elementDatas){
            o = null;
        }
        elementDatas = new Object[]{};
    }

    /**
     * 清空队列
     */
    public void clearList(){

        for(int i = 0; i < currSize;i++ ){
            elementDatas[i] = null;
        }

        currSize = 0;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        if(currSize > 0){
            return false;
        }

        return true;
    }

    /**
     * 队列大小
     * @return
     */
    public int size(){
        return currSize;
    }

    /**
     * 获取元素
     * @param index
     * @return
     */
    public E get(int index){
        if(index > (currSize -1) || index < 0){
            throw new RuntimeException("角标越界");
        }

        return (E)elementDatas[index];
    }

    /**
     * 添加元素
     * @param e
     * @return
     */
    public void add(E e){
        int methodSize = currSize + 1;
        // 是否超过最大长度
        if(methodSize > Integer.MAX_VALUE){
            throw new RuntimeException("队列已超过指定长度");
        }

        currSize = methodSize;

        // 数组需要扩大容量
        if(currSize > methodSize){
            Arrays.copyOf(elementDatas,currSize);
        }

        elementDatas[currSize-1] = e;
    }

    /**
     * 删除指定位置的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if(index < 0 || index >= currSize ){
            throw new RuntimeException("角标越界");
        }

        E e  = (E)elementDatas[index];

        System.arraycopy(elementDatas,index+1,elementDatas,index,currSize-index -1);

        return e;
    }

    /**
     * 排序算法，默认从小排到大
     */
    public void sort(){

    }
}
