package com.felix.chapter2;

import com.felix.common.Apple;

public class AppleGreenColorPredicate implements  ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
