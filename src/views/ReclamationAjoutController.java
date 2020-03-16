/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import entite.Reclamation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class ReclamationAjoutController implements Initializable {

    @FXML
    private TextField num;
    @FXML
    private TextField nomUser;
    @FXML
    private ImageView imv;
    
    int c;
    int file;
    File pDir;
    File pfile;
    String lien;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/views/image/Profile" + c + ".jpg");
        lien = "Profile" + c + ".jpg";
        
        // TODO
    }    

    @FXML
    private void UploadFile(ActionEvent event) throws MalformedURLException {
        
        
           FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imv.setImage(image);
        }
    }

    @FXML
    private void AddReclamation(ActionEvent event) {
        
        
        Reclamation rec = new Reclamation();
        
             if(  nomUser.getText().equals("")|| num.getText().equals("")||num.getText().matches("^[a-zA-Z]+$")){
        
            
            Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Verifier votre Champs").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
        
        
        }
        else{
        
        rec.setNom(nomUser.getText());
        rec.setNumero(Integer.parseInt(num.getText()));
        copier(pfile,pDir);
        rec.setImage(lien);
        
         LocalDate dd = LocalDate.now();
      Date date = java.sql.Date.valueOf(dd);
       rec.setDate(date);
       ReclamationService rs = new ReclamationService();
        rs.AjouterReclamation(rec);
                 Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
            Stage myWindow =  (Stage) num.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Show Details");
            myWindow.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    
    
                           
             }
        
        
    }
    
    
      
 public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

    @FXML
    private void Cancel(ActionEvent event) {
        Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
            Scene sc = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    
}
