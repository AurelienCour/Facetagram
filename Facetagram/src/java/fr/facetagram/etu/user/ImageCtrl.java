/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author aurel
 */
@Named(value = "imageCtrl")
@SessionScoped
public class ImageCtrl implements Serializable  {
    
    @EJB
    private DAO daoImage;

    private Image newImage;
    
    private Image selectedImage;
    
    private UploadedFile file;
    
    /**
     * Creates a new instance of ImageCtrl
     */
    public ImageCtrl() {
        newImage = new Image();
        selectedImage = new Image();
    }
    
    public List<Image> getImageUtilisateur(Utilisateur connectedUser){
        return daoImage.allImageForTheUser(connectedUser);  
    }
    
    public List<Image> getAllImage(List<Utilisateur> amis){
        List<Image> img = new ArrayList<>();
        for (Utilisateur ami : amis) {
             img.addAll(getImageUtilisateur(ami));
         } 
        return img;
    }

    public Image getNewImage() {
        return newImage;
    }

    public void setNewImage(Image newImage) {
        this.newImage = newImage;
    }
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload(Utilisateur connectedUser) throws IOException {
        if(file != null) {
            newImage.setDatePublication(new Date());
            newImage.setPublic1(false);
            newImage.setIdUtilisateur(connectedUser);
            InputStream input = file.getInputstream();
            Path folder = Paths.get("/Users/severinlhommelet/jeeProject/Facetagram/Facetagram/web/res/img");
            String filename = FilenameUtils.getBaseName(file.getFileName()); 
            String extension = FilenameUtils.getExtension(file.getFileName());
            Path file2 = Files.createTempFile(folder, filename + "-", "." + extension);
            try (InputStream input2 = file.getInputstream()) {
                Files.copy(input2, file2, StandardCopyOption.REPLACE_EXISTING);
                
                String filename2 = FilenameUtils.getBaseName(file.getFileName()); 
                String extension2 = FilenameUtils.getExtension(file.getFileName());
                newImage.setCheminImage(file2.getFileName().toString());
                daoImage.addImage(newImage);
                newImage = new Image();
                file = null;
            }
        }
    }

    public Image getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(Image selectedImage) {
        this.selectedImage = selectedImage;
    }
    
    public void modifImage(){
        daoImage.updateImage(selectedImage);
    }
    
    public void suppImage(){
        daoImage.removeImage(selectedImage);
    }
    
    public void addVue(){
        selectedImage.setNombreDeVue(selectedImage.getNombreDeVue()+1);
        daoImage.updateImage(selectedImage);
    }
}
