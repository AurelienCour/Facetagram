/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.inject.Named;
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
    private DAO daoUtilisateur;
    
    private Utilisateur utilisateur;
    
    private Utilisateur selectedUser;
    
    private Utilisateur connectedUser;
    
    private Utilisateur targetUser;
    
    private int NbTotVue;
    private int NbTotLike;
    
    private List<Utilisateur> targetUserList;
    
    private String searchUser;
    
    private Boolean connecte;

    /**
     * Creates a new instance of UtilisateurCtrl
     */
    public UtilisateurCtrl() {
        this.utilisateur = new Utilisateur();
        this.selectedUser = new Utilisateur();
        this.connectedUser = new Utilisateur();
        this.targetUser = new Utilisateur();
        this.targetUserList = new ArrayList<>();
    }
    
    public List<String> complete(String query) {
        List<String> results = new ArrayList<>();
        Pattern pattern = Pattern.compile(query.toUpperCase());
        for (Utilisateur utilisateur1 : getUtilisateurs()) {
            Matcher matcher = pattern.matcher(utilisateur1.getNom().toUpperCase() + " " + utilisateur1.getPrenom().toUpperCase());
            if (matcher.find())
                results.add(utilisateur1.getNom() + " " + utilisateur1.getPrenom());
        }
        return results;
    }
 

    public List<Utilisateur> getTargetUserList() {
        return targetUserList;
    }

    public void setTargetUserList(List<Utilisateur> targetUserList) {
        this.targetUserList = targetUserList;
    }

    public Utilisateur getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(Utilisateur targetUser) throws IOException {
        this.targetUser = targetUser;
        FacesContext.getCurrentInstance().getExternalContext().redirect("userProfile.xhtml");
    }
    
    public boolean checkImage(Image img){
        for(Aimer a : connectedUser.getAimerCollection())
            if(a.getIdImage().equals(img)) return true;
        return false;    
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
        daoUtilisateur.addUtilisateur(this.utilisateur);
        this.utilisateur = new Utilisateur();
    }
     
    public DAO getDaoUtilisateur() {
        return daoUtilisateur;
    }
 
    public void setDaoUtilisateur(DAO daoUtilisateur) {
        this.daoUtilisateur = daoUtilisateur;
    }
 
    public Boolean getConnecte() {
        return connecte;
    }

    public Utilisateur getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(Utilisateur connectedUser){
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
                majStat();
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        }
    }    
 
    public void register(ActionEvent event) throws IOException {     
        if(connectedUser.getEmail() != null && connectedUser.getMotDePasse()!= null) {
            connecte = true;
            daoUtilisateur.addUtilisateur(this.connectedUser);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    } 

    public String getSearchUser() {
        return searchUser;
    }

    public void setSearchUser(String searchUser) {
        this.searchUser = searchUser;
    }
    
    public void searchUtilisateur() throws IOException{
        List<Utilisateur> users = daoUtilisateur.allUtilisateur();
        List<Utilisateur> match = new ArrayList<>();
        Pattern pattern = Pattern.compile(searchUser.toUpperCase());
        for (Utilisateur user : users) {
            Matcher matcher = pattern.matcher(user.getNom().toUpperCase() + " " + user.getPrenom().toUpperCase());
            if (matcher.find())
                match.add(user);
        }
        match.remove(this.connectedUser);
        if (match.isEmpty())
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Utilisateur non trouvé"));
        else{
            if(match.size() == 1){ 
                targetUser = match.get(0);
                FacesContext.getCurrentInstance().getExternalContext().redirect("userProfile.xhtml");
            }else{
                targetUserList = match;
                FacesContext.getCurrentInstance().getExternalContext().redirect("userProfileList.xhtml");
            }
        }
        searchUser = "";
    }

    public int getNbTotVue() {
        return NbTotVue;
    }

    public void setNbTotVue(int NbTotVue) {
        this.NbTotVue = NbTotVue;
    }

    public int getNbTotLike() {
        return NbTotLike;
    }

    public void setNbTotLike(int NbTotLike) {
        this.NbTotLike = NbTotLike;
    }

    public void majStat() {
        setNbTotLike(0);
        setNbTotVue(0);
        for (Image image : connectedUser.getImageCollection()) {
            setNbTotLike(getNbTotLike()+image.getAimerCollection().size());
            setNbTotVue(getNbTotVue()+image.getNombreDeVue());
        }
    }
    
    public void logout() throws IOException{
        connectedUser = new Utilisateur();
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    }
    
    
}