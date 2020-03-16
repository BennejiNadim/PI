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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private TextField num;
    @FXML
    private TextField nomUser;
    
    public static int idrec ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Reclamation r = new Reclamation();
        ReclamationService rs = new ReclamationService();
        
        r= rs.findReclamationById(idrec);
        nomUser.setText(r.getNom());
        num.setText(String.valueOf(r.getNumero()));
        // TODO
    }    

    @FXML
    private void AddReclamation(ActionEvent event) {
        
        
        ReclamationService rs = new ReclamationService();
        
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
       Reclamation r=rs.findReclamationById(idrec);
       
       r.setNom(nomUser.getText());
       r.setNumero(Integer.parseInt(num.getText()));
       
       rs.ModifierReclamation(r);
       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
            Stage myWindow =  (Stage) nomUser.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Ajouter Reclamation");
            myWindow.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
               
    }

    @FXML
    private void cancel(ActionEvent event) {
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
