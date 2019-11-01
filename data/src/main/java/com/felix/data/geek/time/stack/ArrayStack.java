package com.felix.data.geek.time.stack;

import java.util.Arrays;

/**
 * 顺序栈
 */
public class ArrayStack<E> {

    // 顺序栈存储数组
    public Object[]  arrayStack = new Object[16];

    // 当前数据
    public int modSize = 0;

    /**
     * 出栈
     * @return
     */
    public boolean pop(){
        if(modSize == 0){
            return false;
        }
        arrayStack[modSize-1] = null;
        modSize--;

        return true;
    }

    /**
     * 入栈
     * @param e
     * @return
     */
    public boolean push(E e){
        if(modSize+1 > arrayStack.length){//  要扩容
            arrayStack = Arrays.copyOf(arrayStack, arrayStack.length * 2);
        }

        arrayStack[modSize] = e;
        modSize++;

        return true;
    }
}
