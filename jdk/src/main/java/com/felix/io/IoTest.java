package com.felix.io;

import org.junit.Test;

import java.io.*;

public class IoTest {

    @Test
    public void testInputStream() throws Exception {
        InputStream inputStream = new FileInputStream(new File("/Users/finup/Downloads/zipkin1.json"));
        byte[] bytes = new byte[1024];
        OutputStream outputStream = new FileOutputStream(new File("/Users/finup/Downloads/io.json"));
        while(inputStream.read(bytes) !=-1){
            outputStream.write(bytes);
        }
        inputStream.close();
        outputStream.close();
    }

    @Test
    public void testReader() throws Exception{
        Reader reader = new FileReader(new File("/Users/finup/Downloads/zipkin1.json"));
        char[] chars = new char[1024];
        Writer writer = new FileWriter(new File("/Users/finup/Downloads/reader.json"));
        while(reader.read(chars) != -1){
            writer.write(chars);
        }

        reader.close();
        writer.close();
    }

}
