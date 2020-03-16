/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Categorie;
import entite.Fournisseur;
import entite.Marque;
import entite.MouvementProduit;
import entite.Produit;
import service.AgoraEmail;
import service.GestionCategorie;
import service.GestionFournisseur;
import service.GestionMarque;
import service.GestionMouvementProduit;
import service.GestionProduit;
import service.Pdf;
import util.ConnexionBD;
import com.itextpdf.text.Image;
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
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.CurrentSession;
import static views.UpdateProductController.p;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PageAcceuilController implements Initializable {

    @FXML
    private Tab tab_product;
    @FXML
    private TableView<Produit> table_produit;
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
    private Button add_product_btn;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Tab mouvement_tab;
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
    private TableColumn<?, ?> nom_produit1;
    @FXML
    private TableColumn<?, ?> action1;
    @FXML
    private Button add_mouvement;
    @FXML
    private Button update1;
    @FXML
    private Button delete1;
    @FXML
    private Tab catg_tab;
    @FXML
    private Tab marque_tab;
    @FXML
    private Tab four_tab;
    @FXML
    private AnchorPane product_pane;
    @FXML
    private AnchorPane mvt_pane;
    @FXML
    private AnchorPane catg_pane;
    @FXML
    private AnchorPane marque_pane;
    @FXML
    private AnchorPane four_pane;
    @FXML
    private TextArea message_field;
    @FXML
    private TextField recipient_field;
    @FXML
    private TextField oject_field;
    @FXML
    private Button pdf_btn;
    @FXML
    private Label msg_sent;
    private ObservableList<Produit> Oblist = FXCollections.observableArrayList();
    private ObservableList<MouvementProduit> Oblist1 = FXCollections.observableArrayList();
    private ObservableList<Categorie> Oblist2 = FXCollections.observableArrayList();
    private ObservableList<Marque> Oblist3 = FXCollections.observableArrayList();
    private ObservableList<Fournisseur> Oblist4 = FXCollections.observableArrayList();

    private Connection con = ConnexionBD.getinstance().getCnx();
    @FXML
    private ImageView logo;
    @FXML
    private TableColumn<?, ?> id_catg;
    @FXML
    private TableColumn<?, ?> lib_catg;
    @FXML
    private Button add_catg_btn1;
    @FXML
    private Button update_catg_btn;
    @FXML
    private Button delete_catg;
    @FXML
    private TableColumn<?, ?> id_marque;
    @FXML
    private TableColumn<?, ?> nom_marque;
    @FXML
    private TableColumn<?, ?> action3;
    @FXML
    private Button add_marque_btn;
    @FXML
    private Button update_marque_btn;
    @FXML
    private Button delete_marque;
    @FXML
    private TableColumn<?, ?> id_fournisseur;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> numero;
    @FXML
    private TableColumn<?, ?> Email;
    @FXML
    private TableColumn<?, ?> action4;
    @FXML
    private Button add_four_btn;
    @FXML
    private Button update_four_btn;
    @FXML
    private Button delete_four;
    @FXML
    private TableView<Categorie> table_catg;
    @FXML
    private TableColumn<?, ?> action2;
    @FXML
    private TableView<Marque> table_marque;
    @FXML
    private TableView<Fournisseur> table_four;
    @FXML
    private ComboBox<String> combobox_filter;
    private ObservableList<String> ob = FXCollections.observableArrayList("marque", "categorie", "all");
    @FXML
    private ComboBox<String> choice;
    @FXML
    private Button send_email_btn1;
    @FXML
    private Button exit;
    @FXML
    private Button searsh_product;
    @FXML
    private TextField searsh_product_field;
    @FXML
    private Button product_under;
    @FXML
    private Label notify_admin;
    @FXML
    private ImageView logo1;
    @FXML
    private Button gestuserview;
    @FXML
    private Button gestcomptbutt;
    @FXML
    private VBox buttonVBox;
    @FXML
    private BorderPane prodAP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        prodAP.setPrefSize(CurrentSession.wx, CurrentSession.wy);
        if (!CurrentSession.isAdmin) {
            buttonVBox.getChildren().remove(gestcomptbutt);

        }
        combobox_filter.setItems(ob);

    }

    @FXML
    private void tab_product(Event event) {

        PreparedStatement pt;
        table_produit.getItems().clear();
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
    private void update_product_btn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/UpdateProduct.fxml"));
        try {
            Produit p1 = get_selected_row();
            if (p1 != null) {
                Parent root = loader.load();
                UpdateProductController upc = loader.getController();
                upc.setFields(p1);
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_product(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "are you sure you want to delete", ButtonType.YES, ButtonType.NO);
        boolean b = false;
        for (Produit p : table_produit.getItems()) {
            if (p.getCh_box().isSelected()) {
                b = true;
                break;
            }
        }
        if (b) {
            a.showAndWait();
            if (a.getResult() == ButtonType.YES) {
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
                this.tab_product(event);
                a.close();
            } else {
                a.close();
            }
        }
    }

    @FXML
    private void add_mouvement_btn(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/AddMouvement.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableMouvementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void update_mouvement_btn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/UpdateMouvement.fxml"));
        try {
            MouvementProduit p1 = get_selected_row1();
            if (p1 != null) {
                Parent root = loader.load();
                UpdateMouvementController upc = loader.getController();
                upc.setFields(p1);
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delete_mouvement(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "are you sure you want to delete", ButtonType.YES, ButtonType.NO);
        boolean c = false;
        for (MouvementProduit p : table_mouvement.getItems()) {
            if (p.getCh_box().isSelected()) {
                c = true;
                break;
            }

        }
        if (c) {
            a.showAndWait();
            if (a.getResult() == ButtonType.YES) {
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
                this.mouvement_tab(event);
                a.close();
            } else {
                a.close();
            }
        }
    }

    @FXML
    private void mouvement_tab(Event event) {
        PreparedStatement pt;
        table_mouvement.getItems().clear();
        try {
            pt = con.prepareStatement("SELECT id_mouvement_Produit,id_facture,source,destination,quantité,date,nom_produit from mouvementproduit,produit where id_produit=ref_produit");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Oblist1.add(new MouvementProduit(rs.getInt("id_mouvement_Produit"), rs.getInt("id_facture"), MouvementProduit.src_dest.valueOf(rs.getString("source")), MouvementProduit.src_dest.valueOf(rs.getString("destination")), rs.getInt("quantité"), rs.getDate("date"), rs.getString("nom_produit")));
            }
            id_mouvement_Produit.setCellValueFactory(new PropertyValueFactory<>("id_mouvement_Produit"));
            id_facture.setCellValueFactory(new PropertyValueFactory<>("id_facture"));
            source.setCellValueFactory(new PropertyValueFactory<>("source"));
            destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
            quantité.setCellValueFactory(new PropertyValueFactory<>("quantité"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            nom_produit1.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
            action1.setCellValueFactory(new PropertyValueFactory<>("ch_box"));
            table_mouvement.setItems(Oblist1);
            System.out.println(Oblist1);
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void catg_tab(Event event) {
        PreparedStatement pt;
        table_catg.getItems().clear();
        try {
            pt = con.prepareStatement("select * from categorie");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

                Oblist2.add(new Categorie(rs.getInt(1), rs.getString(2)));
            }
            id_catg.setCellValueFactory(new PropertyValueFactory<>("id_catg"));
            lib_catg.setCellValueFactory(new PropertyValueFactory<>("lib_catg"));
            action2.setCellValueFactory(new PropertyValueFactory<>("ch_box"));
            table_catg.setItems(Oblist2);
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void marque_tab(Event event) {
        PreparedStatement pt;
        table_marque.getItems().clear();
        try {
            pt = con.prepareStatement("select * from marque");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

                Oblist3.add(new Marque(rs.getInt(1), rs.getString(2)));
            }
            id_marque.setCellValueFactory(new PropertyValueFactory<>("id_marque"));
            nom_marque.setCellValueFactory(new PropertyValueFactory<>("nom_marque"));
            action3.setCellValueFactory(new PropertyValueFactory<>("ch_box"));
            table_marque.setItems(Oblist3);
        } catch (SQLException ex) {
        }

    }

    @FXML
    private void four_tab(Event event) {
        PreparedStatement pt;
        table_four.getItems().clear();
        try {
            pt = con.prepareStatement("select * from fournisseur");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

                Oblist4.add(new Fournisseur(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
            id_fournisseur.setCellValueFactory(new PropertyValueFactory<>("id_fournisseur"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
            Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
            action4.setCellValueFactory(new PropertyValueFactory<>("ch_box"));
            table_four.setItems(Oblist4);
        } catch (SQLException ex) {
        }

    }

    @FXML
    private void send_email_btn(ActionEvent event) {
        String recipient = recipient_field.getText();
        String object = oject_field.getText();
        String msg = message_field.getText();
        try {
            AgoraEmail.sendMail(recipient, msg, object);
        } catch (Exception ex) {
            Logger.getLogger(PageAcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        msg_sent.setText("Email sent successfully");

    }

    @FXML
    private void pdf_btn(ActionEvent event) {
        if (tab_product.isSelected()) {
            Pdf.PdfListeProduits();
        } else if (mouvement_tab.isSelected()) {
            Pdf.PdfListeMouvementsProduits();
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

    private MouvementProduit get_selected_row1() {
        MouvementProduit p1 = null;
        for (MouvementProduit p : table_mouvement.getItems()) {
            if (p.getCh_box().isSelected()) {
                p1 = p;
            }
        }
        return p1;

    }

    private Categorie get_selected_row2() {
        Categorie p1 = null;
        for (Categorie p : table_catg.getItems()) {
            if (p.getCh_box().isSelected()) {
                p1 = p;
            }
        }
        return p1;

    }

    private Marque get_selected_row3() {
        Marque p1 = null;
        for (Marque p : table_marque.getItems()) {
            if (p.getCh_box().isSelected()) {
                p1 = p;
            }
        }
        return p1;

    }

    private Fournisseur get_selected_row4() {
        Fournisseur p1 = null;
        for (Fournisseur p : table_four.getItems()) {
            if (p.getCh_box().isSelected()) {
                p1 = p;
            }
        }
        return p1;

    }

    @FXML
    private void add_catg_btn1(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/AddCategorie.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableMouvementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void update_catg_btn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/UpdateCategorie.fxml"));
        try {
            Categorie p1 = get_selected_row2();

            Parent root = loader.load();
            UpdateCatgController upc = loader.getController();
            upc.setFields(p1);
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delete_catg(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "are you sure you want to delete", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            ObservableList<Categorie> Oblist = FXCollections.observableArrayList();
            GestionCategorie gp = new GestionCategorie();
            for (Categorie p : table_catg.getItems()) {
                if (p.getCh_box().isSelected()) {
                    Oblist.add(p);
                }

            }
            for (Categorie p : Oblist) {
                gp.supprimerCategorie(p.getId_catg());
            }

            table_catg.getItems().clear();
            this.catg_tab(event);
            a.close();
        } else {
            a.close();
        }
    }

    @FXML
    private void add_marque_btn(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/AddMarque.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableMouvementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void update_marque_btn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/UpdateMarque.fxml"));
        try {
            Marque p1 = get_selected_row3();

            Parent root = loader.load();
            UpdateMarqueController upc = loader.getController();
            upc.setFields(p1);
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete_marque(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "are you sure you want to delete", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            ObservableList<Marque> Oblist = FXCollections.observableArrayList();
            GestionMarque gp = new GestionMarque();
            for (Marque p : table_marque.getItems()) {
                if (p.getCh_box().isSelected()) {
                    Oblist.add(p);
                }

            }
            for (Marque p : Oblist) {
                gp.supprimerMarque(p.getId_marque());
            }

            table_marque.getItems().clear();
            this.marque_tab(event);
            a.close();
        } else {
            a.close();
        }
    }

    @FXML
    private void add_four_btn(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/AddFournisseur.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableMouvementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void update_four_btn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/UpdateFournisseur.fxml"));
        try {
            Fournisseur p1 = get_selected_row4();

            Parent root = loader.load();
            UpdateFourController upc = loader.getController();
            upc.setFields(p1);
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delete_four(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "are you sure you want to delete", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            ObservableList<Fournisseur> Oblist = FXCollections.observableArrayList();
            GestionFournisseur gp = new GestionFournisseur();
            for (Fournisseur p : table_four.getItems()) {
                if (p.getCh_box().isSelected()) {
                    Oblist.add(p);
                }

            }
            for (Fournisseur p : Oblist) {
                gp.supprimerFournisseur(p.getId_fournisseur());
            }

            table_four.getItems().clear();
            this.four_tab(event);
            a.close();
        } else {
            a.close();
        }
    }

    @FXML
    private void combobox_filter(ActionEvent event) {
        ObservableList<String> ob = FXCollections.observableArrayList();

        if (combobox_filter.getValue().toString().equals("categorie")) {
            PreparedStatement pt1;
            try {
                pt1 = con.prepareStatement("select lib_catg from categorie");
                ResultSet rs = pt1.executeQuery();
                while (rs.next()) {
                    ob.add(rs.getString(1));
                }
            } catch (Exception e) {
            }
        } else if (combobox_filter.getValue().toString().equals("marque")) {
            PreparedStatement pt1;
            try {
                pt1 = con.prepareStatement("select nom_marque from marque");
                ResultSet rs = pt1.executeQuery();
                while (rs.next()) {
                    ob.add(rs.getString(1));
                }
            } catch (Exception e) {
            }
        } else {
            ob = null;
        }
        choice.setItems(ob);

    }

    @FXML
    private void choice(ActionEvent event) {
        ObservableList<Produit> ob = FXCollections.observableArrayList();
        String s = "";
        GestionProduit gp = new GestionProduit();
        s = choice.getValue();
        if (combobox_filter.getValue().toString().equals("marque")) {
            ob.clear();
            ob = gp.afficherProduitParMarque(s);
            table_produit.getItems().clear();
            table_produit.setItems(ob);
        } else if (combobox_filter.getValue().toString().equals("categorie")) {
            ob.clear();
            ob = gp.afficherProduitParCategorie(s);
            table_produit.getItems().clear();
            table_produit.setItems(ob);

        } else {
            ob.clear();
            ob = gp.afficherProduit();
            table_produit.getItems().clear();
            table_produit.setItems(ob);

        }
    }

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void searsh_product(ActionEvent event) {
        String nom = searsh_product_field.getText();
        GestionProduit gp = new GestionProduit();
        ObservableList<Produit> ob = FXCollections.observableArrayList();
        ob.clear();
        ob = gp.rechercherProduitParNom(nom);
        table_produit.getItems().clear();
        table_produit.setItems(ob);
    }

    @FXML
    private void product_under(ActionEvent event) {
        try {
            PreparedStatement pt = con.prepareStatement("select * from Produit where quantité_stock<50");
            ResultSet rs = pt.executeQuery();
            String msg = "la quantité de ces produit est inferieur au seuil :";
            while (rs.next()) {
                msg += rs.getString(2) + ",";
            }
            if (!msg.equals("la quantité de ces produit est inferieur au seuil :")) {
                AgoraEmail.sendMail("nadim.benneji@esprit.tn", msg, "stock alert");

                notify_admin.setText("admin notified");
            } else {
                notify_admin.setText("no new updates");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PageAcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PageAcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void toViewComptaAdmin(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) prodAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) prodAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLComptabiliteAdmin.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewUserAdmin(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) prodAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) prodAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLUserAdmin.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();

    }

    @FXML
    private void toViewGestEquip(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) prodAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) prodAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewMark(ActionEvent event) throws IOException {
        /*CurrentSession.wx = (int) prodAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) prodAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();*/
    }

    @FXML
    private void toViewVente(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) prodAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) prodAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("vente.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) prodAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) prodAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewAvis(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) prodAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) prodAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListAvis.fxml"));

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewRec(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) prodAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) prodAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

}
