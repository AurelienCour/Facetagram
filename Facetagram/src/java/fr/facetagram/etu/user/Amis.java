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
@Table(name = "amis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amis.findAll", query = "SELECT a FROM Amis a")
    , @NamedQuery(name = "Amis.findByIdAmis", query = "SELECT a FROM Amis a WHERE a.idAmis = :idAmis")})
public class Amis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdAmis")
    private Integer idAmis;
    @JoinColumn(name = "IdUtilisateur1", referencedColumnName = "IdUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur1;
    @JoinColumn(name = "IdUtilisateur2", referencedColumnName = "IdUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur2;

    public Amis() {
    }

    public Amis(Integer idAmis) {
        this.idAmis = idAmis;
    }

    public Integer getIdAmis() {
        return idAmis;
    }

    public void setIdAmis(Integer idAmis) {
        this.idAmis = idAmis;
    }

    public Utilisateur getIdUtilisateur1() {
        return idUtilisateur1;
    }

    public void setIdUtilisateur1(Utilisateur idUtilisateur1) {
        this.idUtilisateur1 = idUtilisateur1;
    }

    public Utilisateur getIdUtilisateur2() {
        return idUtilisateur2;
    }

    public void setIdUtilisateur2(Utilisateur idUtilisateur2) {
        this.idUtilisateur2 = idUtilisateur2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAmis != null ? idAmis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amis)) {
            return false;
        }
        Amis other = (Amis) object;
        if ((this.idAmis == null && other.idAmis != null) || (this.idAmis != null && !this.idAmis.equals(other.idAmis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.facetagram.etu.user.Amis[ idAmis=" + idAmis + " ]";
    }
    
}
