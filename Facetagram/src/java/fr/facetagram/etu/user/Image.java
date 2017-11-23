/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.text.SimpleDateFormat;

/**
 *
 * @author aurel
 */
@Entity
@Table(name = "image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i")
    , @NamedQuery(name = "Image.findByIdImage", query = "SELECT i FROM Image i WHERE i.idImage = :idImage")
    , @NamedQuery(name = "Image.findByIdUtilisateur", query = "SELECT i FROM Image i WHERE i.idUtilisateur = :idUtilisateur ORDER BY i.datePublication DESC")
    , @NamedQuery(name = "Image.findByCheminImage", query = "SELECT i FROM Image i WHERE i.cheminImage = :cheminImage")
    , @NamedQuery(name = "Image.findByNombreDeVue", query = "SELECT i FROM Image i WHERE i.nombreDeVue = :nombreDeVue")
    , @NamedQuery(name = "Image.findByDatePublication", query = "SELECT i FROM Image i WHERE i.datePublication = :datePublication")
    , @NamedQuery(name = "Image.findByDescripton", query = "SELECT i FROM Image i WHERE i.descripton = :descripton")
    , @NamedQuery(name = "Image.findByPublic1", query = "SELECT i FROM Image i WHERE i.public1 = :public1")})
public class Image implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idImage")
    private Collection<Notification> notificationCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdImage")
    private Integer idImage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CheminImage")
    private String cheminImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NombreDeVue")
    private int nombreDeVue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatePublication")
    @Temporal(TemporalType.DATE)
    private Date datePublication;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "Descripton")
    private String descripton;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Public")
    private boolean public1;
    @JoinColumn(name = "IdUtilisateur", referencedColumnName = "IdUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idImage")
    private Collection<Aimer> aimerCollection;

    public Image() {
    }

    public Image(Integer idImage) {
        this.idImage = idImage;
    }

    public Image(Integer idImage, String cheminImage, int nombreDeVue, Date datePublication, String descripton, boolean public1) {
        this.idImage = idImage;
        this.cheminImage = cheminImage;
        this.nombreDeVue = nombreDeVue;
        this.datePublication = datePublication;
        this.descripton = descripton;
        this.public1 = public1;
    }

    public Integer getIdImage() {
        return idImage;
    }

    public void setIdImage(Integer idImage) {
        this.idImage = idImage;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public int getNombreDeVue() {
        return nombreDeVue;
    }

    public void setNombreDeVue(int nombreDeVue) {
        this.nombreDeVue = nombreDeVue;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public boolean getPublic1() {
        return public1;
    }

    public void setPublic1(boolean public1) {
        this.public1 = public1;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @XmlTransient
    public Collection<Aimer> getAimerCollection() {
        return aimerCollection;
    }

    public void setAimerCollection(Collection<Aimer> aimerCollection) {
        this.aimerCollection = aimerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImage != null ? idImage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.idImage == null && other.idImage != null) || (this.idImage != null && !this.idImage.equals(other.idImage))) {
            return false;
        }
        return true;
    }
    
    public String getMyFormattedDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(datePublication);
    }

    @Override
    public String toString() {
        return "fr.facetagram.etu.user.Image[ idImage=" + idImage + " ]";
    }

    @XmlTransient
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }
    
}
