/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aurel
 */
@Entity
@Table(name = "aimer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aimer.findAll", query = "SELECT a FROM Aimer a")
    , @NamedQuery(name = "Aimer.findByIdAimer", query = "SELECT a FROM Aimer a WHERE a.idAimer = :idAimer")
    , @NamedQuery(name = "Aimer.findByIdImage", query = "SELECT a FROM Aimer a WHERE a.idImage = :idImage")
    , @NamedQuery(name = "Aimer.findByIdUtilisateur", query = "SELECT a FROM Aimer a WHERE a.idUtilisateur = :idUtilisateur")})
public class Aimer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdAimer")
    private Integer idAimer;
    @JoinColumn(name = "IdUtilisateur", referencedColumnName = "IdUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;
    @JoinColumn(name = "IdImage", referencedColumnName = "IdImage")
    @ManyToOne(optional = false)
    private Image idImage;

    public Aimer() {
    }

    public Aimer(Integer idAimer) {
        this.idAimer = idAimer;
    }

    public Integer getIdAimer() {
        return idAimer;
    }

    public void setIdAimer(Integer idAimer) {
        this.idAimer = idAimer;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Image getIdImage() {
        return idImage;
    }

    public void setIdImage(Image idImage) {
        this.idImage = idImage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAimer != null ? idAimer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aimer)) {
            return false;
        }
        Aimer other = (Aimer) object;
        if ((this.idAimer == null && other.idAimer != null) || (this.idAimer != null && !this.idAimer.equals(other.idAimer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.facetagram.etu.user.Aimer[ idAimer=" + idAimer + " ]";
    }
    
}
