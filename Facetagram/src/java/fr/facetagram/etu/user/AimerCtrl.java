/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author severinlhommelet
 */
@Named(value = "aimerCtrl")
@SessionScoped
public class AimerCtrl implements Serializable {
    
    @EJB
    private DAO dao;
    
    public void addAimer(Utilisateur user, Image img){
        Aimer aimer = new Aimer();
        aimer.setIdImage(img);
        aimer.setIdUtilisateur(user);
        dao.addAimer(aimer);
    }
    
    public void removeAimer(Utilisateur user, Image img){
        Aimer aimer = new Aimer();
        aimer.setIdImage(img);
        aimer.setIdUtilisateur(user);
        dao.removeAimer(aimer);
    }
    
}
