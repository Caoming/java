package com.felix.spring.service;

import com.felix.spring.dao.AccountDao;
import com.felix.spring.dao.ItemDao;

/**
 * @Author caoming
 * @Date: 2020/10/13 19:13
 */
public class PetStoreServiceImpl {
    private AccountDao accountDao;
    private ItemDao itemDao;

    public PetStoreServiceImpl() {
    }

    public PetStoreServiceImpl(AccountDao accountDao, ItemDao itemDao) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
    }

    public void services(){
        accountDao.add();
        itemDao.add();
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
