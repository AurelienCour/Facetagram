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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author severinlhommelet
 */
@Entity
@Table(name = "Notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    , @NamedQuery(name = "Notification.findByIdNotif", query = "SELECT n FROM Notification n WHERE n.idNotif = :idNotif")
    , @NamedQuery(name = "Notification.findByType", query = "SELECT n FROM Notification n WHERE n.type = :type")
, @NamedQuery(name = "Notification.findByUser", query = "SELECT n FROM Notification n WHERE n.idutilisateurRetrieve = :idUser")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_notif")
    private Integer idNotif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "id_utilisateurSend", referencedColumnName = "IdUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idutilisateurSend;
    @JoinColumn(name = "id_image", referencedColumnName = "IdImage")
    @ManyToOne
    private Image idImage;
    @JoinColumn(name = "id_utilisateurRetrieve", referencedColumnName = "IdUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idutilisateurRetrieve;

    public Notification() {
    }

    public Notification(Integer idNotif) {
        this.idNotif = idNotif;
    }

    public Notification(Integer idNotif, String type) {
        this.idNotif = idNotif;
        this.type = type;
    }

    public Integer getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(Integer idNotif) {
        this.idNotif = idNotif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Utilisateur getIdutilisateurSend() {
        return idutilisateurSend;
    }

    public void setIdutilisateurSend(Utilisateur idutilisateurSend) {
        this.idutilisateurSend = idutilisateurSend;
    }

    public Image getIdImage() {
        return idImage;
    }

    public void setIdImage(Image idImage) {
        this.idImage = idImage;
    }

    public Utilisateur getIdutilisateurRetrieve() {
        return idutilisateurRetrieve;
    }

    public void setIdutilisateurRetrieve(Utilisateur idutilisateurRetrieve) {
        this.idutilisateurRetrieve = idutilisateurRetrieve;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotif != null ? idNotif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.idNotif == null && other.idNotif != null) || (this.idNotif != null && !this.idNotif.equals(other.idNotif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.facetagram.etu.user.Notification[ idNotif=" + idNotif + " ]";
    }
    
}
