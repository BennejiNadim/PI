/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import entite.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class DetailsReclamationController implements Initializable {

    @FXML
    private Label nomu;
    @FXML
    private Label numu;
    @FXML
    private Label date;
    
    public static int idr ;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ReclamationService rs = new ReclamationService();
        
        Reclamation r = rs.findReclamationById(idr);
        
        nomu.setText(r.getNom());
        numu.setText(String.valueOf(r.getNumero()));
        date.setText(String.valueOf(r.getDate()));
       // System.out.println("controller.DetailsReclamationController.initialize()"+r.getImage());
        
       Image i = new Image("file:///C:/Users/ASUS/Desktop/PIDEV/Nouveau dossier/projet PIDEV/Gestionvente/src/views/image/"+r.getImage());
       img.setImage(i);
      img.setCache(true);
        System.out.println(r.getImage());
      //  Image image = new Image(r.getImage().toURI().toURL().toExternalForm());
       // imgPr.setImage(r.getImage());
        
        
    }    

    @FXML
    private void back(ActionEvent event) {
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
