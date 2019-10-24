package com.felix.base;

import java.io.*;

/**
 * 1、测试Serializable 中的transient 序列化的问题
 * 2、Externalizable 接口
 * 总结：
 * 1、继承Serializable 自动序列化的过程会忽略transient修饰的字段
 * 2、static 修饰的变量不会自动序列化
 * 3、实现Externalizable都会序列化transient
 */
public class SerializableTest {

    public static void main(String[] args) throws Exception{
        UserVo userVo = new UserVo();
        userVo.setUsername("felix");
        userVo.setPassword("123456");

        ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(
                        new File("/Users/finup/Downloads/test.txt")));

        outputStream.writeObject(userVo);
        outputStream.flush();
        outputStream.close();

        userVo.setPassword("123123");
        userVo.setUsername("123423");

        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("/Users/finup/Downloads/test.txt"));
        UserVo vo = (UserVo) objectInputStream.readObject();
        System.out.println(vo.getUsername()+"..."+vo.getPassword());

    }
}
