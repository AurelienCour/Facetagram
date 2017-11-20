/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aurel
 */
@Stateless
public class DAO {

    @PersistenceContext(unitName = "FacetagramPU")
    private EntityManager em;
     
    public List<Utilisateur> allUtilisateur(){
        Query query = em.createNamedQuery("Utilisateur.findAll");
        return query.getResultList();
    }
    
    public List<Image> allImageForTheUser(Utilisateur connectedUser){
        Query query = em.createNamedQuery("Image.findByIdUtilisateur").setParameter("idUtilisateur", connectedUser);
        List<Image> img = query.getResultList();
        if (img.size() > 5){
            List<Image> img2 = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                img2.add(img.get(i));
            }
            return img2;
        }else
           return img; 
    }
    
    public List<Amis> allAmis(Utilisateur user){
        Query query = em.createNamedQuery("Amis.findByIdUser").setParameter("idUtilisateur", user);
        return query.getResultList();
    }
    
    public List<Amis> matchedAmi(Utilisateur user1,Utilisateur user2){
        Query query = em.createNamedQuery("Amis.findByTwoUsers").setParameter("idUtilisateur1", user1).setParameter("idUtilisateur2", user2);
        List<Amis>  amis = query.getResultList();
        if(amis.isEmpty()) return null;
        else return amis;
    }
    
    public void addAmis (Amis ami) {
        em.persist(ami);
        em.flush();
    }
    
    public void addUtilisateur (Utilisateur user) {
        em.persist(user);
        em.flush();
    }
    
    public void addImage (Image image) {
        em.persist(image);
        em.flush();
    }
    
    public void updateUtilisateur (Utilisateur user) {
        em.merge(user);
        em.flush();
    }
    
    public void updateImage (Image img) {
        em.merge(img);
        em.flush();
    }

    public void deleteUtilisateur(Utilisateur user) {
        //Query query = em.createNamedQuery("Etudiant.findById").setParameter("id", etu.getId());
        //em.remove(query.getSingleResult());
        em.remove(em.merge(user));
        em.flush();
    }
    
    public void addAimer(Aimer aimer){
        em.persist(aimer);
        em.flush();
    }
    
    public void removeAimer(Aimer aimer){
        em.remove(em.merge(aimer));
        em.flush();
    }
    
    public List<Aimer> allAimer(){
        Query query = em.createNamedQuery("Aimer.findAll");
        return query.getResultList();
    }
}
