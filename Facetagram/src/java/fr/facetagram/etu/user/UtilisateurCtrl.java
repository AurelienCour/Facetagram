/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author aurel
 */

@Named(value = "utilisateurCtrl")
@ApplicationScoped
public class UtilisateurCtrl implements Serializable {
    
    @EJB
    private UtilisateurDAO daoUtilisateur;
    
    private Utilisateur utilisateur;
    
    private Utilisateur selectedUser;
    
    private Boolean connecte;

    /**
     * Creates a new instance of UtilisateurCtrl
     */
    
    public UtilisateurCtrl() {
        this.utilisateur = new Utilisateur();
        this.selectedUser = new Utilisateur();
    }

    public Utilisateur getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Utilisateur selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
     public List<Utilisateur> getUtilisateurs() {
        return daoUtilisateur.allUtilisateur();
    }
     
     public void addUtilisateur(){
        daoUtilisateur.add(this.utilisateur);
        this.utilisateur = new Utilisateur();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Etudiant ajouté !"));
    }

    public UtilisateurDAO getDaoUtilisateur() {
        return daoUtilisateur;
    }

    public void setDaoUtilisateur(UtilisateurDAO daoUtilisateur) {
        this.daoUtilisateur = daoUtilisateur;
    }

    public Boolean getConnecte() {
        return connecte;
    }

    public void setConnecte(Boolean connecte) {
        this.connecte = connecte;
    }
    
     
     
     public void login(ActionEvent event) throws IOException {
        List<Utilisateur> utilisateurs = getUtilisateurs();
     
        for(Utilisateur u : utilisateurs){
            if(u.getEmail().equals(utilisateur.getEmail()) && u.getMotDePasse().equals(utilisateur.getMotDePasse()) ){
                connecte = true;
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                utilisateur = new Utilisateur();
            }
        }

    }
    
    public void register(ActionEvent event) throws IOException {
        
        if(utilisateur.getEmail() != null && utilisateur.getMotDePasse()!= null) {
            connecte = true;
            addUtilisateur();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    } 
    
}