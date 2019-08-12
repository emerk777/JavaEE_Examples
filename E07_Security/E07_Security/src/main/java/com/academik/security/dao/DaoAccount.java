package com.academik.security.dao;

import com.academik.security.models.Account;
import com.academik.security.models.AccountRole;
import com.academik.security.models.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author esvux
 */
@RequestScoped
public class DaoAccount {
    
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createNormalAccount(Account account) {
        em.persist(account);
    }

    public List<Account> findAllAdmins() {
        //JPQL
        TypedQuery<Account> query = em.createQuery(
                //Equivalente a SELECT * FROM votante
                "SELECT a FROM Account a INNER JOIN a.accountRoles ar WHERE ar.role.id = 1", 
                Account.class
        );
        List<Account> result = query.getResultList();
        return result;        
    }
    
    public List<Account> findAllUsers() {
        //JPQL
        TypedQuery<Account> query = em.createQuery(
                //Equivalente a SELECT * FROM votante
                "SELECT a FROM Account a INNER JOIN a.accountRoles ar WHERE ar.role.id = 2", 
                Account.class
        );
        List<Account> result = query.getResultList();
        return result;        
    }    
    
}
