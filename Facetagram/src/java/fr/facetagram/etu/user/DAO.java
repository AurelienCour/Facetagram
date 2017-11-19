/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;

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

    public void deleteUtilisateur(Utilisateur user) {
        //Query query = em.createNamedQuery("Etudiant.findById").setParameter("id", etu.getId());
        //em.remove(query.getSingleResult());
        em.remove(em.merge(user));
        em.flush();
    }
}
