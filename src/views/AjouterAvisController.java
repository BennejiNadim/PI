/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;



import entite.Avis;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import service.AgoraEmail;
import service.AvisService;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class AjouterAvisController implements Initializable {

    @FXML
    private ChoiceBox AvisBOX;
    @FXML
    private TextArea input_com;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOAvis
       
        AvisBOX.setItems(FXCollections.observableArrayList("Contant", "Satisfait","Non Satisfait"));
        
    }    

    @FXML
    private void Envoyer(ActionEvent event) {
        Alert a1=new Alert(Alert.AlertType.CONFIRMATION,"added successfully",ButtonType.OK);
        
        Avis a = new Avis();
        String box = (String) AvisBOX.getValue();
        if(  input_com.getText().equals("") || box == null){
        
            
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
        AvisService as = new AvisService();
        a.setChxAvis((String) AvisBOX.getValue());
        a.setContenu(input_com.getText());
        as.AjouterAvis(a);
            try {
                AgoraEmail.sendMail("salem.yaiche@esprit.tn",input_com.getText(),"Avis");
            } catch (Exception ex) {
                Logger.getLogger(AjouterAvisController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    a1.showAndWait();
    if(a1.getResult().equals(ButtonType.OK)){
     Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("ListAvis.fxml"));
            Stage myWindow =  (Stage) input_com.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Show Details");
            myWindow.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("ListAvis.fxml"));
            Scene sc = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
