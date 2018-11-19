package com.felix.chapter7;

import java.io.InputStream;

/**
 * 类加载器的概念
 */
public class ClassLoadTest {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try{
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if(inputStream == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[inputStream.available()];
                    inputStream.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (Exception e){
                    e.printStackTrace();
                }

                return null;
            }
        };

        Object obj  = classLoader.loadClass("com.felix.chapter7.ClassLoadTest");
        System.out.println(obj);
        System.out.println(obj instanceof  ClassLoadTest);
    }

}
