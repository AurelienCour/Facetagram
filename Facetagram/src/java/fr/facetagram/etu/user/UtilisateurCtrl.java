/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author aurel
 */
@Named(value = "utilisateurCtrl")
@ApplicationScoped
public class UtilisateurCtrl {
    
    @EJB
    private UtilisateurDAO daoUtilisateur;

    /**
     * Creates a new instance of UtilisateurCtrl
     */
    public UtilisateurCtrl() {
    }
    
}
