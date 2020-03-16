/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import entite.HistoriqueEquipement;
import entite.Materiels;
import entite.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Material;
import javafx.stage.Stage;
import service.ServiceEquipement;
import service.ServiceHistoriqueEquipement;
import service.ServiceMateriels;
import service.ServiceVehicule;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjoutHistoriqueController implements Initializable {

    @FXML
    private TextField qte;
    @FXML
    private ComboBox operationc;
    @FXML
    private Button annuler;
    @FXML
    private Button valider;
    String e;
    String o;
   
    
    /**
     * Initializes the controller class.
     */
    public ObservableList<String> opcom = FXCollections.observableArrayList("achat","disponnible","panne","vente","corbeille","maintenance");
    public ObservableList<String> eqcom = FXCollections.observableArrayList("vehicule","materiels");
    ServiceHistoriqueEquipement sh = new ServiceHistoriqueEquipement();
    @FXML
    private ComboBox equipementc;
    @FXML
    private TextField id_v;
    @FXML
    private TextField id_m;
    @FXML
    private AnchorPane invalid;
    @FXML
    private Button oker;
    @FXML
    private Label invalidtext;
    ServiceEquipement se = new ServiceEquipement();
    ServiceVehicule sv = new ServiceVehicule();
    ServiceMateriels sm = new ServiceMateriels();
    @FXML
    private TextField prix;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        operationc.setItems(opcom);
        equipementc.setItems(eqcom);
        e="";
        o="";
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
            Logger.getLogger(AjoutHistoriqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void valider(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"added successfully",ButtonType.OK);
        Calendar c1 = Calendar.getInstance();
            Date d = new Date (c1.getTimeInMillis());
            if (se.testTextInput(o)){
            if (se.testTextInput(e)) {
              

        if(se.testNumberInput(qte.getText()) && (!(qte.getText().length()==0)) ) {
            if (se.testNumberInput(id_m.getText()) && (!(id_m.getText().length()==0))) {
                if (se.testNumberInput(id_v.getText()) && (!(id_v.getText().length()==0))) {
                    Materiels mt = sm.chercheParId(Integer.parseInt(id_m.getText()));
                    Vehicule vt = sv.chercheParId(Integer.parseInt(id_v.getText()));
                    if ((!(mt.getEtat().equals(Materiels.etat.NULL))) || (!(vt.getEtat().equals(Vehicule.etat.NULL)))  ) {
                        if (se.testFloatInput(prix.getText())) {
                   
           HistoriqueEquipement h = new HistoriqueEquipement(d,sh.stringToOperation(operationc.getValue().toString()), sh.stringToEquipement(equipementc.getValue().toString()), Integer.parseInt(qte.getText()),Integer.parseInt(id_m.getText()),Integer.parseInt(id_v.getText()),Float.parseFloat(prix.getText()));
            sh.ajouterHistoriqueEquipemnt(h);
            FrontController.don = 1;
            a.showAndWait();
            if(a.getResult().equals(ButtonType.OK)){
        try {            
            Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutHistoriqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
              
               
            }
                        else {
                           invalid.setVisible(true);
                            invalidtext.setText("Prix invalid !");
                        }
                    }
                    else {
                        invalid.setVisible(true);
                    invalidtext.setText("Id introuvable !");
                    }
                }
                else {
                    invalid.setVisible(true);
                    invalidtext.setText("Id_v invalid !");
                }
            }
           
            else {
                invalid.setVisible(true);
               invalidtext.setText("Id_m invalid !");
            }
        }
        else {
            invalid.setVisible(true);
            invalidtext.setText("Qte invalid !");
        }
            }
            else {
                invalid.setVisible(true);
                invalidtext.setText("Equipement invalid !");
            }
            }
        else {
            invalid.setVisible(true);
             invalidtext.setText("Opération invalid !");
               
        }
         
    }

    @FXML
    private void operationc(ActionEvent event) {
        if (!operationc.isPressed()) {
            o = operationc.getValue().toString();
        }
    }

    @FXML
    private void equipementc(ActionEvent event) {
        if (!equipementc.isPressed()) {
            e = equipementc.getValue().toString();
        }
        
    }

    @FXML
    private void oker(ActionEvent event) {
        invalid.setVisible(false);
    }
    
}
