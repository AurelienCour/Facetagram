/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.facetagram.etu.user;
 
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FilenameUtils;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;
 
@Named(value = "fileUploadView")
@ApplicationScoped
public class FileUploadView {
     
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() throws IOException {
        if(file != null) {
            InputStream input = file.getInputstream();
            Path folder = Paths.get("C:\\Users\\aurel\\Documents\\ProjetJee\\Facetagram\\Facetagram\\web\\res");
            String filename = FilenameUtils.getBaseName(file.getFileName()); 
            String extension = FilenameUtils.getExtension(file.getFileName());
            Path file2 = Files.createTempFile(folder, filename + "-", "." + extension);
            try (InputStream input2 = file.getInputstream()) {
                Files.copy(input2, file2, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
