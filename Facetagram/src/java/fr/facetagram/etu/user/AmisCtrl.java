package fr.facetagram.etu.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author severinlhommelet
 */

@Named(value = "amisCtrl")
@SessionScoped
public class AmisCtrl implements Serializable   {
    @EJB
    private DAO dao;
    private List<Utilisateur> amis;

    public AmisCtrl() {
        amis = new ArrayList<Utilisateur>();
    }
    
    
    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }
    
    public List<Utilisateur> getAmis(Utilisateur user) {
        
        for(Amis a : dao.allAmis(user)){
            if(a.getIdUtilisateur1().equals(user)) amis.add(a.getIdUtilisateur2());
            if(a.getIdUtilisateur2().equals(user)) amis.add(a.getIdUtilisateur1());         
        }
        
        return amis;
    }
    
    public boolean getMatchedAmi(Utilisateur user1, Utilisateur user2){
        if(dao.matchedAmi(user1, user2) == null){
            return false;
        }
        
        return true;
    }
        
    
    
}
