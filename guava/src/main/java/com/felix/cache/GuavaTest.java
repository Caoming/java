package com.felix.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * guava 测试
 * @Author caoming
 * @Date: 2020/4/17 15:57
 */
public class GuavaTest {

    @Test
    public void test(){
        Cache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(2).build();
        Obj obj_a = new Obj(1, 2);
        Obj obj_b = new Obj(2, 2);
        Obj obj_c = new Obj(3, 2);

        // first put count=1
        cache.put("a", obj_a);
        Assert.assertEquals(obj_a, cache.getIfPresent("a"));

        // 2nd put count=2
        cache.put("b", obj_b);

        // use a more than b
        cache.getIfPresent("a");
        Assert.assertEquals(obj_b, cache.getIfPresent("b"));
        Assert.assertEquals(obj_a, cache.getIfPresent("a"));

        // 3rd put count=3 need remove one
        cache.put("c", obj_c);
        Assert.assertEquals(obj_c, cache.getIfPresent("c"));
        Assert.assertTrue(cache.getIfPresent("b") == null);
    }


    @Test
    public void testLoad() throws Exception{
        LoadingCache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(200).
                refreshAfterWrite(10L,TimeUnit.MINUTES).
                expireAfterAccess(3, TimeUnit.MINUTES).
                build(new CacheLoader<Object,Object>() {
            @Override
            public Object load(Object key) throws Exception {
                System.out.println("wo dong le");
                return new Obj(1000,1000);
            }
        });



        Obj obj_a = new Obj(1, 2);
        Obj obj_b = new Obj(2, 2);
        Obj obj_c = new Obj(3, 2);

        System.out.println(cache.get(123).toString());
    }

    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    static class Obj{
        private int size;
        private int weight;

    }
}
