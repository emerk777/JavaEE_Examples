package com.academik.security.beans;

import com.academik.security.dao.DaoAccount;
import com.academik.security.dao.DaoRole;
import com.academik.security.models.Account;
import com.academik.security.models.AccountRole;
import com.academik.security.models.Role;
import com.academik.security.util.PasswordEncrypter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author esvux
 */
@ManagedBean(name = "registerAdminBean")
@ViewScoped
public class RegisterAdminBean {
    
    @Inject
    DaoAccount daoAccount;
        
    @Inject
    DaoRole daoRole;    
    
    private String tempEmail;
    private String tempNickname;
    private String tempPassword;
    private String tempFirstName;
    private String tempLastName;

    public String getTempEmail() {
        return tempEmail;
    }

    public void setTempEmail(String tempEmail) {
        this.tempEmail = tempEmail;
    }

    public String getTempNickname() {
        return tempNickname;
    }

    public void setTempNickname(String tempNickname) {
        this.tempNickname = tempNickname;
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    public String getTempFirstName() {
        return tempFirstName;
    }

    public void setTempFirstName(String tempFirstName) {
        this.tempFirstName = tempFirstName;
    }

    public String getTempLastName() {
        return tempLastName;
    }

    public void setTempLastName(String tempLastName) {
        this.tempLastName = tempLastName;
    }    
    
    public String create() {
        Account account = new Account();
        account.setEmail(tempEmail);
        account.setNickname(tempNickname);
        account.setPassword(PasswordEncrypter.encrypt(tempPassword));
        account.setFirstName(tempFirstName);
        account.setLastName(tempLastName);
        
        Role adminR = daoRole.getAdminRole();     
        AccountRole ar = new AccountRole();
        //Linea ROJA
        ar.setAccount(account);
        //Linea AZUL
        ar.setRole(adminR);     
        //Linea NEGRA
        List<AccountRole> accountRoles = new ArrayList<>();
        accountRoles.add(ar);
        account.setAccountRoles(accountRoles);
     
        daoAccount.createNormalAccount(account);
        return "users";
    }    
    
}
