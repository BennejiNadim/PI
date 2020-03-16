/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.MouvementProduit;
import entite.MouvementProduit.src_dest;
import entite.Produit;
import service.GestionMouvementProduit;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TableMouvementController implements Initializable {

    @FXML
    private TableView<MouvementProduit> table_mouvement;
    @FXML
    private TableColumn<?, ?> id_mouvement_Produit;
    @FXML
    private TableColumn<?, ?> id_facture;
    @FXML
    private TableColumn<?, ?> source;
    @FXML
    private TableColumn<?, ?> destination;
    @FXML
    private TableColumn<?, ?> quantité;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> nom_produit;
    @FXML
    private TableColumn<?, ?> action;
    @FXML
    private Button add_mouvement;
    @FXML
    private Button update;
    @FXML
    private Button delete;
     private Connection con = ConnexionBD.getinstance().getCnx();

    private ObservableList<MouvementProduit> Oblist = FXCollections.observableArrayList();
    @FXML


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.tableMvt();
    }    
    public void tableMvt(){
    PreparedStatement pt;
        try {
            pt = con.prepareStatement("​SELECT id_mouvement_Produit,id_facture,	source,destination,quantité,date,nom_produit from mouvementproduit,produit where id_produit=ref_produit");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Oblist.add(new MouvementProduit(rs.getInt("id_mouvement_Produit"), rs.getInt("id_facture"), src_dest.valueOf(rs.getString("source")),src_dest.valueOf(rs.getString("destination")) , rs.getInt("quantité"), rs.getDate("date"), rs.getInt("nom_produit")));
            }
            id_mouvement_Produit.setCellValueFactory(new PropertyValueFactory<>("id_mouvement_Produit"));
            id_facture.setCellValueFactory(new PropertyValueFactory<>("id_facture"));
            source.setCellValueFactory(new PropertyValueFactory<>("source"));
            destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
            quantité.setCellValueFactory(new PropertyValueFactory<>("quantité"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            nom_produit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
            action.setCellValueFactory(new PropertyValueFactory<>("ch_box"));

            table_mouvement.setItems(Oblist);
        } catch (SQLException ex) {
    }
    }

    @FXML
    private void add_mouvement_btn(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/AddMouvement.fxml"));
                    Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableMouvementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update_mouvement_btn(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../views/UpdateMouvement.fxml"));
        try {
            MouvementProduit p1=get_selected_row();
            
            Parent root=loader.load();
            UpdateMouvementController upc=loader.getController(); 
            upc.setFields(p1);
            Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delete_mouvement(ActionEvent event) {
        ObservableList<MouvementProduit> Oblist = FXCollections.observableArrayList();
        GestionMouvementProduit gp = new GestionMouvementProduit();
        for (MouvementProduit p : table_mouvement.getItems()) {
            if (p.getCh_box().isSelected()) {
                Oblist.add(p);
            }

        }
        for (MouvementProduit p : Oblist) {
            gp.supprimerMouvement(p.getId_mouvement_Produit());
        }

        table_mouvement.getItems().clear();
        this.tableMvt();

    }

    private MouvementProduit get_selected_row() {
         MouvementProduit p1=null;
            for (MouvementProduit p : table_mouvement.getItems()) {
            if (p.getCh_box().isSelected()) {
                p1=p;
            }
            }
            return p1;
    
    }    
    
}
