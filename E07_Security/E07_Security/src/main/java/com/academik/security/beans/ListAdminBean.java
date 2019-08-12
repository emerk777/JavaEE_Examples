/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.academik.security.beans;

import com.academik.security.dao.DaoAccount;
import com.academik.security.models.Account;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Emer
 */
@ManagedBean(name = "listAdminBean")
@RequestScoped
public class ListAdminBean implements Serializable{
    
    @Inject
    DaoAccount daoAccout;    
    
    List<Account> adminAccounts; 
    List<Account> userAccounts;
    
    @PostConstruct
    public void init() {
        adminAccounts = daoAccout.findAllAdmins();
        userAccounts = daoAccout.findAllUsers();
    }    

    public List<Account> getAdminAccounts() {
        return adminAccounts;
    }

    public void setAdminAccounts(List<Account> adminAccounts) {
        this.adminAccounts = adminAccounts;
    }

    public List<Account> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<Account> userAccounts) {
        this.userAccounts = userAccounts;
    }
    
}
