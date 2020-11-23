package com.felix.spring.dao;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author caoming
 * @Date: 2020/10/13 19:13
 */
public class AccountDao {

    public void add(){
        System.out.println("add...account");
    }

    public void update(){
        System.out.println("update...account");
    }

    public void delete(){
        System.out.println("delete...account");
    }

    public AccountDao() {
    }
}
