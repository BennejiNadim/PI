/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Facture;
import entite.MouvementProduit;
import entite.MouvementProduit.src_dest;
import entite.Produit;
import entite.Transaction;
import service.GestionMouvementProduit;
import service.GestionProduit;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.GestionFacture;
import service.GestionTransaction;
import static views.UpdateMouvementController.p;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddMouvementController implements Initializable {

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
    private TextField quantité_field;
    @FXML
    private DatePicker date_field;
    @FXML
    private TextField id_produit_field;
    @FXML
    private Button add_mouvement_button;
    @FXML
    private ComboBox source_field;
    @FXML
    private ComboBox destination_field;
    private ObservableList<String> srcCombo=FXCollections.observableArrayList("stock","supplier");
    private ObservableList<String> destCombo=FXCollections.observableArrayList("stock","magasin");
    @FXML
    private Button cancel;
    @FXML
    private Label msg;
    @FXML
    private TextField id_supp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        source_field.setItems(srcCombo);
        destination_field.setItems(destCombo);
    }    

    @FXML
    private void add_mouvement_button(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"added successfully",ButtonType.OK);
        if(id_mouvement_Produit_field.getText()==null||id_supp.getText()==null||source_field.getValue()==null||destination_field.getValue()==null||quantité_field.getText()==null||date_field.getValue()==null||id_produit_field.getText()==null){
        msg.setText("invalid entries");
        }
        else{
        GestionProduit Gp=new GestionProduit();
        int id_mvt=0;
        int id_f=0;
        int quantité=0;
        int id_p=0;
        try{
            id_mvt=parseInt(id_mouvement_Produit_field.getText());
            id_f=parseInt(id_supp.getText());
            quantité= parseInt(quantité_field.getText());
            id_p=parseInt(id_produit_field.getText());
        
        }
        catch(Exception e){
        msg.setText("invalid entries");
        }
        int x=0;
        if(id_mvt!=0&&id_f!=0&&quantité!=0&&id_p!=0&&Gp.RechercherProduitParRef(id_p)){
            
            if(source_field.getValue().toString().equals("supplier")){
            Facture f = new Facture(Facture.etat.payed, new BigDecimal(Gp.getProduitParRef(id_p)*quantité), "", "", id_f, Facture.typef.achat_produit);
            Transaction t = new Transaction(f.getId(), Facture.etat.payed, "", f.getDateFacturation(), f.getMontant());
        GestionFacture gf = new GestionFacture();
        GestionTransaction gt = new GestionTransaction();
        gf.ajouterFacture(f);
        gt.ajouterTransaction(t);
        x=id_f;
            }
            
        
        
         MouvementProduit p =new MouvementProduit(parseInt(id_mouvement_Produit_field.getText()), x,src_dest.valueOf(source_field.getValue().toString()),src_dest.valueOf(destination_field.getValue().toString()), parseInt(quantité_field.getText()), Date.valueOf(date_field.getValue()), parseInt(id_produit_field.getText()));
        GestionMouvementProduit gp=new GestionMouvementProduit();
        gp.ajouterMouvement(p);
        a.showAndWait();
        if(a.getResult().equals(ButtonType.OK))
        {
            a.close();
     
                Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/PageAcceuil.fxml"));
                    Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(AddMouvementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
        else{
        msg.setText("product does not exist");
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
    
}
