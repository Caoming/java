package com.felix.chapter3;

import java.io.BufferedReader;

/**
 * 描述读取流行为的函数接口
 */
public interface BufferedReaderProcessor {
    public String process(BufferedReader br) throws Exception;
}
