package com.felix.test;

import com.felix.data.structure.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest {
    private static ArrayList<Long> arrayList = new ArrayList<>();

    static {
        arrayList.add(2L);
        arrayList.add(3L);
        arrayList.add(5L);
        arrayList.add(7L);
        arrayList.add(1L);
        arrayList.add(3L);
        arrayList.add(4L);
        arrayList.add(7L);
        arrayList.add(5L);
        arrayList.add(3L);
        arrayList.add(5L);
        arrayList.add(6L);
        arrayList.add(7L);
    }
    /**
     * 测试
     */
    @Test
    public void testAdd(){
        arrayList.clearList();
        arrayList.add(1L);
        arrayList.add(2L);
        arrayList.add(3L);
        arrayList.add(4L);
        arrayList.add(5L);
        arrayList.add(6L);
        arrayList.add(7L);

        Assert.assertTrue(arrayList.size() == 7);
        Assert.assertTrue(arrayList.remove(3) == 4L);
    }


    /**
     * 排序，默认从小到大排序
     */
    @Test
    public void testSort(){


    }


}
