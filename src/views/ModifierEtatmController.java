/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Equipement;
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
public class ModifierEtatmController implements Initializable {

    @FXML
    private ComboBox etatc;
    @FXML
    private Button Confirmer;
    @FXML
    private Button Annuler;

    /**
     * Initializes the controller class.
     */
    public ObservableList<String> etcom = FXCollections.observableArrayList("disponnible", "en_panne", "vendu", "corbeille", "en_maintenance");
    ServiceEquipement se = new ServiceEquipement();
    ServiceMateriels sm = new ServiceMateriels();
    @FXML
    private TextField qte;
    @FXML
    private AnchorPane invalid;
    @FXML
    private Button oker;
    @FXML
    private Label invalidtext;
    String e;
    @FXML
    private TextField prix;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        etatc.setItems(etcom);
        e = "";
        prix.setText("0.0");
        prix.setDisable(true);
    }

    @FXML
    private void etatc(ActionEvent event) {
        if (!etatc.isPressed()) {
            e = etatc.getValue().toString();
            if (e.equals(Equipement.etat.disponnible.toString()) || e.equals(Equipement.etat.vendu.toString())) {
                prix.setDisable(false);
                prix.setText("");
            } else {
                prix.setText("0.0");
                prix.setDisable(true);
            }
        }
    }
     Materiels m = sm.chercheParId(FrontController.id_courant);

    @FXML
    private void Confirmer(ActionEvent event) {
        if (!m.getEtat().toString().equals(etatc.getValue())) {
        if (se.testNumberInput(qte.getText()) && (!(qte.getText().length() == 0))) {
            if (se.testTextInput(e)) {
                if (se.testFloatInput(prix.getText())) {
                    if (e.equals(Equipement.etat.disponnible.toString()) || e.equals(Equipement.etat.vendu.toString())) {
                        System.out.println("1");
                        sm.changerEtat(FrontController.id_courant, Integer.parseInt(qte.getText()), se.stringToEtat(etatc.getValue().toString()), Float.parseFloat(prix.getText()));
                    } else {
                        sm.changerEtat(FrontController.id_courant, Integer.parseInt(qte.getText()), se.stringToEtat(etatc.getValue().toString()), 0f);
                    }
                    FrontController.don = 1;
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
                        Scene scene = new Scene(root);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(scene);
                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ModifierEtatmController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    invalid.setVisible(true);
                    invalidtext.setText("Prix invalid !");
                }
            } else {
                invalid.setVisible(true);
                invalidtext.setText("Etat invalid !");
            }
        } else {
            invalid.setVisible(true);
            invalidtext.setText("Qte invalid !");
        }
        } else {
            invalid.setVisible(true);
            invalidtext.setText("Meme etat !");
        }
    }

    @FXML
    private void Annuler(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierEtatmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void oker(ActionEvent event) {
        invalid.setVisible(false);
    }

}
