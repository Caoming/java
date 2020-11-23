package com.felix.spring;

import com.felix.spring.service.PetStoreServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author caoming
 * @Date: 2020/10/13 19:12
 */
public class Info {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("daos.xml", "services.xml");
        PetStoreServiceImpl petStore = context.getBean("petStore", PetStoreServiceImpl.class);
        petStore.services();
    }
}
