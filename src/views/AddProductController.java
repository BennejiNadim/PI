/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Produit;
import service.GestionProduit;
import util.ConnexionBD;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField ref_produit_field;
    private TextField marque_field;
    private TextField categorie_field;
    @FXML
    private TextField quantité_stock_field;
    @FXML
    private TextField quantité_magasin_field;
    @FXML
    private TextField prix_vente_field;
    @FXML
    private TextField prix_achat_field;
    @FXML
    private TextField nom_produit_field;
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
    private Label prix_achat;
    @FXML
    private Button add_prodcut_button;
    @FXML
    private Button cancel;
    @FXML
    private ComboBox<String> marque_field_box;
    @FXML
    private ComboBox<String> categorie_field_box;
private Connection con = ConnexionBD.getinstance().getCnx();
    @FXML
    private Label ref_msg;
    @FXML
    private Label nom_msg;
    @FXML
    private Label q_s_msg;
    @FXML
    private Label q_m_msg;
    @FXML
    private Label p_a_msg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PreparedStatement pt1,pt2;
        ObservableList<String> ob=FXCollections.observableArrayList();
        ObservableList<String> ob1=FXCollections.observableArrayList();
        try {
            pt1 = con.prepareStatement("select lib_catg from categorie");
            pt2=con.prepareStatement("select nom_marque from Marque");
            ResultSet rs = pt1.executeQuery();
            ResultSet rs1=pt2.executeQuery();
            while(rs.next()&&rs1.next()){
            ob.add(rs.getString(1));
            
            ob1.add(rs1.getString(1));
     
            }
                   ob1.add("other");
                   ob.add("other");
            marque_field_box.setItems(ob1);
            categorie_field_box.setItems(ob);
            
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        prix_achat_field.setOnAction(event);
            
    }    

    
   @FXML
    private void add_product_button(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"added successfully",ButtonType.OK);
                        if((ref_produit_field.getText()==null||nom_produit_field.getText()==null||categorie_field_box.getValue()==null||marque_field_box.getValue()==null||quantité_stock_field.getText()==null||quantité_magasin_field.getText()==null||prix_achat_field.getText()==null)){
                q_m_msg.setText("invalid entries");
                }
                        else{
        int ref_prod=0;
        int prix_a=0;
        try{
        ref_prod=Integer.parseInt(ref_produit_field.getText());
        }
        catch(Exception e){
            ref_msg.setText("check value of ref");
        
        }
                try{
        prix_a=Integer.parseInt(prix_achat_field.getText());
                }
        catch(Exception e){
            p_a_msg.setText("check value of prix achat");
        
        }

        if(ref_prod!=0&&prix_a!=0){
        Produit p =new Produit(ref_prod, nom_produit_field.getText(), marque_field_box.getValue(), categorie_field_box.getValue(), parseInt(quantité_stock_field.getText()), parseInt(quantité_magasin_field.getText()), parseFloat(prix_vente_field.getText()),prix_a);
        GestionProduit gp=new GestionProduit();
        gp.ajouterProduit(p);
        a.showAndWait();
        if(a.getResult().equals(ButtonType.OK))
        {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/PageAcceuil.fxml"));
                    Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }
    }
    @FXML
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
         EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                        prix_vente_field.setText(String.valueOf(Float.parseFloat(prix_achat_field.getText())*1.3));
        System.out.println("text changed");
  
            } 
        }; 
  

    @FXML
    private void calcul_prix_vente(InputMethodEvent event) {

    }


    
}
