package com.felix.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDemo {

    @Test
    public void testDemo(){
        RandomEmailGenerator randomEmailGenerator = new RandomEmailGenerator();

        String str = randomEmailGenerator.generate();

        Assert.assertTrue("caoming hao badao".equals(str));
    }

}
