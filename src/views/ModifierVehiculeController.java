/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import entite.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.ServiceEquipement;
import service.ServiceVehicule;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierVehiculeController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField matricule;
    @FXML
    private TextField km;
    @FXML
    private ComboBox utilisationc;
    @FXML
    private ComboBox etatc;
    @FXML
    private Button annuler;
    @FXML
    private Button valider;
    String c;
    String u;
    String e;

    /**
     * Initializes the controller class.
     */
    ServiceVehicule sv = new ServiceVehicule();
    ServiceEquipement se = new ServiceEquipement();
     public ObservableList<String> utcom = FXCollections.observableArrayList("vehicule_personnel","vehicule_livraison");
     public ObservableList<String> etcom = FXCollections.observableArrayList("disponnible","en_panne","vendu","corbeille", "en_maintenance");
    @FXML
    private AnchorPane invalid;
    @FXML
    private Button oker;
    @FXML
    private Label invalidtext;
    @FXML
    private ColorPicker couleurt;
    @FXML
    private TextField matricule1;
    @FXML
    private TextField matricule2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         utilisationc.setItems(utcom); 
        etatc.setItems(etcom);
        Vehicule v1 = sv.chercheParId(FrontController.id_courant);
       
        km.setText(v1.getKM().toString());
        nom.setText(v1.getNom());
        u="";
         e="";
         c="";
         couleurt.setValue(Color.valueOf(v1.getCouleur()));
         matricule1.setText("Tun");
        
    }    

    @FXML
    private void utilisationc(ActionEvent event) {
         if (!utilisationc.isPressed()) {
            u = utilisationc.getValue().toString();
    }
    }

    @FXML
    private void etatc(ActionEvent event) {
        if(!etatc.isPressed()) {
            e=etatc.getValue().toString();
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void valider(ActionEvent event) {
        if (se.testTextInput(nom.getText())){
            if (se.testNumberInput(matricule.getText())&& (!(matricule.getText().length()==0)) && se.testTextInput(matricule1.getText()) && se.testNumberInput(matricule2.getText())&& (!(matricule2.getText().length()==0))) {
              

        if(se.testTextInput(c) ) {
            if (se.testTextInput(u)) {
                if (se.testNumberInput(km.getText()) && (!(km.getText().length()==0))) {
                    if (se.testTextInput(e)){
           String mat = matricule.getText()+matricule1.getText()+matricule2.getText();
                        System.out.println(mat);
  Vehicule v = new Vehicule(mat,couleurt.getValue().toString(), sv.stringToUtilisation(utilisationc.getValue().toString())  , Integer.parseInt(km.getText()), nom.getText(), se.stringToEtat(etatc.getValue().toString()));
           sv.modifierVehicule(FrontController.id_courant, v);
            FrontController.don = 1;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
                    }
                    else {
                        invalid.setVisible(true);
                        invalidtext.setText("Etat invalid !");
                    }
            }
                else {
                    invalid.setVisible(true);
                    invalidtext.setText("Km invalid !");
                }
            }
           
            else {
                invalid.setVisible(true);
               invalidtext.setText("Utilisation invalid !");
            }
        }
        else {
            invalid.setVisible(true);
            invalidtext.setText("Couleur invalid !");
        }
            }
            else {
                invalid.setVisible(true);
                invalidtext.setText("Matricule invalid !");
            }
            }
        else {
            invalid.setVisible(true);
             invalidtext.setText("Nom invalid !");
               
        }
    }

    @FXML
    private void oker(ActionEvent event) {        
          invalid.setVisible(false);
    }

    @FXML
    private void couleurt(ActionEvent event) {
        c=couleurt.getValue().toString();        
    }
    
}
