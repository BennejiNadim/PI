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
import service.AvisService;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class ModifierAvisController implements Initializable {

    @FXML
    private ChoiceBox AvisBOX;
    @FXML
    private TextArea input_com;

    public static int ida ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AvisBOX.setItems(FXCollections.observableArrayList("Contant", "Satisfait","Non Satisfait"));
        AvisService as = new AvisService();
       Avis a = as.findAvisById(ida);
       
      input_com.setText(a.getContenu());
        
        


        
    }    

    @FXML
    private void Envoyer(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"added successfully",ButtonType.OK);
        
        Avis a1 = new Avis();
        a1.setId(ida);
        a1.setContenu(input_com.getText());
        a1.setChxAvis((String)AvisBOX.getValue());
             if(  input_com.getText().equals("")){
        
            
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
        as.ModifierAvis(a1);
        
        
         
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
