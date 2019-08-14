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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    
    @Pattern(regexp = "\\p{Alnum}{5,}\\@[a-zA-Z0-9.-]{5,}+$", message="El Correo deber de poseer el siguiente formato usuario@dominio.com con un usuario y dominio no menor a 5 caracteres")
    private String tempEmail;
    @Pattern(regexp = "\\p{Alpha}{5,}", message="El Nickname deber de poseer al menos 5 caracteres alfabeticos")
    private String tempNickname;
    @Pattern (regexp="\\d{1,4}", message="La contrasena debe de ser entre uno y cuatro numeros")
    private String tempPassword;
    @Size(min=2, max=40, message="El primer nombre necesita al menos 2 caracteres")
    private String tempFirstName;
    @Size(min=2, max=40, message="El apellido necesita al menos 2 caracteres")
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
