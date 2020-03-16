/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Produit;
import entite.User;
import service.GestionProduit;
import util.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewFocusModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TableController implements Initializable {

    private Connection con = ConnexionBD.getinstance().getCnx();

    private ObservableList<Produit> Oblist = FXCollections.observableArrayList();
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TableColumn<?, ?> ref_produit;
    @FXML
    private TableColumn<?, ?> nom_produit;
    @FXML
    private TableColumn<?, ?> marque;
    @FXML
    private TableColumn<?, ?> categorie;
    @FXML
    private TableColumn<?, ?> quantité_stock;
    @FXML
    private TableColumn<?, ?> quantité_magasin;
    @FXML
    private TableColumn<?, ?> prix_vente;
    @FXML
    private TableColumn<?, ?> prix_achat;
    @FXML
    private TableColumn<?, ?> action;
    @FXML
    private TableView<Produit> table_produit;
    @FXML
    private Button add_product_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.product_table();
    }

    public void product_table() {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("select * from produit");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

                Oblist.add(new Produit(rs.getInt("ref_produit"), rs.getString("nom_produit"), rs.getString("marque"), rs.getString("categorie"), rs.getInt("quantité_stock"), rs.getInt("quantité_magasin"), rs.getFloat("prix_vente"), rs.getFloat("prix_achat")));
            }
            ref_produit.setCellValueFactory(new PropertyValueFactory<>("ref_produit"));
            nom_produit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
            marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
            categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            quantité_stock.setCellValueFactory(new PropertyValueFactory<>("quantité_stock"));
            quantité_magasin.setCellValueFactory(new PropertyValueFactory<>("quantité_magasin"));
            prix_vente.setCellValueFactory(new PropertyValueFactory<>("prix_vente"));
            prix_achat.setCellValueFactory(new PropertyValueFactory<>("prix_achat"));
            action.setCellValueFactory(new PropertyValueFactory<>("ch_box"));

            table_produit.setItems(Oblist);
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void add_product_btn(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/Addproduct.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_product(ActionEvent event) {
        ObservableList<Produit> Oblist = FXCollections.observableArrayList();
        GestionProduit gp = new GestionProduit();
        for (Produit p : table_produit.getItems()) {
            if (p.getCh_box().isSelected()) {
                Oblist.add(p);
            }

        }
        for (Produit p : Oblist) {
            gp.supprimerProduit(p.getRef_produit());
        }

        table_produit.getItems().clear();
        this.product_table();

    }

    @FXML
    private void update_product_btn(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/UpdateProduct.fxml"));
        try {
            Produit p1 = get_selected_row();

            Parent root = loader.load();
            UpdateProductController upc = loader.getController();
            upc.setFields(p1);
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Produit get_selected_row() {
        Produit p1 = null;
        for (Produit p : table_produit.getItems()) {
            if (p.getCh_box().isSelected()) {
                p1 = p;
            }
        }
        return p1;

    }

}
