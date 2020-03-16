/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import entite.Materiels;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceEquipement;
import service.ServiceMateriels;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierMaterielsController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField Qte;
    @FXML
    private ComboBox etatc;
    @FXML
    private Button annuler;
    @FXML
    private Button confirmer;
    String e;

    /**
     * Initializes the controller class.
     */
    ServiceMateriels sm= new ServiceMateriels();
    ServiceEquipement se = new ServiceEquipement();
    public ObservableList<String> etcom = FXCollections.observableArrayList("disponnible","en_panne","vendu","corbeille", "en_maintenance");
    @FXML
    private AnchorPane invalid;
    @FXML
    private Button oker;
    @FXML
    private Label invalidtext;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        e="";
         etatc.setItems(etcom);
         Materiels m1 = sm.chercheParId(FrontController.id_courant);
         Qte.setText(m1.getQte().toString());
         nom.setText(m1.getNom());
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
            Logger.getLogger(ModifierMaterielsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void confirmer(ActionEvent event) {
         if (se.testTextInput(nom.getText())){
            if (se.testNumberInput(Qte.getText()) && (!(Qte.getText().length()==0))){ 
                if (se.testTextInput(e)){
            Materiels m = new Materiels(Integer.parseInt(Qte.getText()), nom.getText(), se.stringToEtat(etatc.getValue().toString()));
            sm.modifierMateriels(FrontController.id_courant,m);
            FrontController.don = 1;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierMaterielsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
                else {
                    invalid.setVisible(true);
                     invalidtext.setText("Etat invalid !");
                }
            }
           
            else {
                invalid.setVisible(true);
                 invalidtext.setText("Qte invalid !");
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
    }
    

