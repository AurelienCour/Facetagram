/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author severinlhommelet
 */

@Named(value = "notificationCtrl")
@SessionScoped
public class notificationCtrl implements Serializable{
    
    @EJB
    private DAO dao;
    
    public void addNotif(Image img,Utilisateur userRetrieve,Utilisateur userSend,String type){
        
        Notification notif = new Notification();
        notif.setIdImage(img);
        notif.setIdutilisateurRetrieve(userRetrieve);
        notif.setIdutilisateurSend(userSend);
        notif.setType(type);       
        dao.addNotif(notif);
    }
    
    public void addNotifAmi(Utilisateur userRetrieve,Utilisateur userSend,String type){
        
        Notification notif = new Notification();
        notif.setIdutilisateurRetrieve(userRetrieve);
        notif.setIdutilisateurSend(userSend);
        notif.setIdImage(dao.allImage().get(0));
        notif.setType(type);       
        dao.addNotif(notif);
    }
    
    public void removeNotif(Utilisateur user){
        
        for(Notification not : dao.allNotif()){
            if(not.getIdutilisateurRetrieve().equals(user)){
                dao.removeNotif(not);
            }
        }          
    }
    
    public List<Notification> allNotifByUser(Utilisateur user){
        return dao.allNotifByUser(user);
    }
    
    public boolean checkType(Notification notif){
        if(notif.getType().equals("ami ?")) return true;
        
        return false;

    }
    
}
