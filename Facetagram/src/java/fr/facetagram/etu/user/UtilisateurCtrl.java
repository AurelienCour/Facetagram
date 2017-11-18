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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author aurel
 */
@Named(value = "utilisateurCtrl")
@SessionScoped
public class UtilisateurCtrl implements Serializable {
    
    @EJB
    private UtilisateurDAO daoUtilisateur;
    
    private Utilisateur utilisateur;
    
    private Utilisateur selectedUser;
    
    private Utilisateur connectedUser;
    
    private Boolean connecte;

    /**
     * Creates a new instance of UtilisateurCtrl
     */
    public UtilisateurCtrl() {
        this.utilisateur = new Utilisateur();
        this.selectedUser = new Utilisateur();
        this.connectedUser = new Utilisateur();
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

    public Utilisateur getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(Utilisateur connectedUser) {
        this.connectedUser = connectedUser;
    }
    
    
 

 
    public void setConnecte(Boolean connecte) {
 
        this.connecte = connecte;
 
    }
     
     public void login(ActionEvent event) throws IOException {
 
        List<Utilisateur> utilisateurs = getUtilisateurs();
 
     
 
        for(Utilisateur u : utilisateurs){
 
            if(u.getEmail().equals(connectedUser.getEmail()) && u.getMotDePasse().equals(connectedUser.getMotDePasse()) ){
 
                connecte = true;
                
                connectedUser = u;
 
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
 
            }
 
        }
 
    }    
 
    public void register(ActionEvent event) throws IOException {     
 
        if(connectedUser.getEmail() != null && connectedUser.getMotDePasse()!= null) {
 
            connecte = true;
 
            daoUtilisateur.add(this.connectedUser);
 
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
 
        }
 
    } 

    
}