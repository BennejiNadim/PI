/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.MouvementProduit;
import entite.MouvementProduit.src_dest;
import service.GestionMouvementProduit;
import service.GestionProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static views.UpdateProductController.p;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateMouvementController implements Initializable {

    @FXML
    private Label ref_produit;
    @FXML
    private Label nom_produit;
    @FXML
    private Label marque;
    @FXML
    private Label categorie;
    @FXML
    private Label quantité_magasin;
    @FXML
    private Label quantité_stock;
    @FXML
    private Label prix_vente;
    @FXML
    private TextField id_mouvement_Produit_field;
    @FXML
    private TextField id_facture_field;
    @FXML
    private TextField quantité_field;
    @FXML
    private DatePicker date_field;
    @FXML
    private TextField id_produit_field;
    @FXML
    private Button update_mouvement_button;
    @FXML
    private ComboBox source_field=new ComboBox();
    @FXML
    private ComboBox destination_field=new ComboBox();
    public static MouvementProduit p;
    public ObservableList<String> srcCombo=FXCollections.observableArrayList("stock","magasin","supplier","client");


       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        source_field.setItems(srcCombo);
        destination_field.setItems(srcCombo);
    }

    @FXML
    private void update_mouvement_button(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PageAcceuil.fxml"));
        TableMouvementController Tc = loader.getController();
        Parent root;
        newmvt();
        GestionMouvementProduit Gp = new GestionMouvementProduit();
        Gp.modifierMouvement(p);
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setFields(MouvementProduit p) {
        id_mouvement_Produit_field.setText(String.valueOf(p.getId_mouvement_Produit()));
        id_facture_field.setText(String.valueOf(p.getId_facture()));
        source_field.setValue(p.getSource().toString());
        destination_field.setValue(p.getDestination().toString());
        quantité_field.setText(String.valueOf(p.getQuantité()));
        date_field.setValue(p.getDate().toLocalDate());
        id_produit_field.setText(String.valueOf(p.getId_produit()));
        this.p = p;
    }

    private void newmvt() {
        p.setSource(src_dest.valueOf(source_field.getValue().toString()));
        p.setDestination(src_dest.valueOf(destination_field.getValue().toString()));
        p.setQuantité(Integer.parseInt(quantité_field.getText()));
        p.setDate(Date.valueOf(date_field.getValue()));

    }
        public void cancel(ActionEvent event){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PageAcceuil.fxml"));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }

}

}
