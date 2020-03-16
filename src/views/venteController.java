/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entite.CarteFidelite;
import entite.Commande;
import entite.NewEntity;
import entite.Produit;
import entite.User;
import entite.livraison;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.CurrentSession;
import service.GestionMouvementProduit;
import service.ServiceCommande;
import service.ServicePanier;
import service.ServiceProduit;
import service.serviceCarteFid;
import service.serviceLivraison;

/**
 * FXML Controller class
 *
 * @author Djemaiel
 */
public class venteController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField nbrPoint;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private JFXButton ajouterbtn;
    serviceCarteFid sf = new serviceCarteFid();
    ServiceProduit sp = new ServiceProduit();
    serviceLivraison sl = new serviceLivraison();
    @FXML
    private TableView<NewEntity> tableCF;

    private TableColumn<NewEntity, Integer> cid;
    @FXML
    private TableColumn<NewEntity, String> ctype;
    @FXML
    private TableColumn<NewEntity, Integer> cnumPoint;
    @FXML
    private TableColumn<NewEntity, String> cDebut;
    @FXML
    private TableColumn<NewEntity, String> cFin;
    Stage primaryStage = new Stage();
    @FXML
    private Label nubreCarte;
    @FXML
    private TableColumn<NewEntity, String> cUser;
    @FXML
    private TableColumn<NewEntity, String> cEmail;
    @FXML
    private Label Stype;
    @FXML
    private Label Snum;
    @FXML
    private Label SDateFin;
    @FXML
    private JFXComboBox<String> Combo;
    @FXML
    private JFXButton affecterbtn;
    @FXML
    private JFXButton modifierbtn;
    final ToggleGroup group = new ToggleGroup();
    @FXML
    private JFXRadioButton radioModifier;
    @FXML
    private JFXRadioButton radioAjouter;
    @FXML
    private JFXRadioButton radioAffecter;
    @FXML
    private Label idLabel;
    @FXML
    private TableView<Produit> tablePr;
    @FXML
    private TableColumn<Produit, String> cNom;
    @FXML
    private TableColumn<Produit, Float> cPrix;
    Map<Integer, Integer> panier = new HashMap<Integer, Integer>();
    ServicePanier ps = new ServicePanier();
    @FXML
    private AnchorPane panierAnchor;
    HashSet<Produit> p1 = new HashSet();
    @FXML
    private Label totalLabel;
    @FXML
    private Label Total;
    @FXML
    private JFXButton acheterButton;
    @FXML
    private JFXCheckBox utliser_livraison;
    @FXML
    private JFXCheckBox utliser_CF;
    @FXML
    private Label NouvTotal;
    @FXML
    private Label NvPrix;
    @FXML
    private Line line;
    Map<Integer, Integer> Panier = new HashMap<>();
    @FXML
    private TableView<livraison> TableLiv;
    @FXML
    private TableColumn<livraison, String> cEtat;
    @FXML
    private TableColumn<livraison, Float> cTotal;
    @FXML
    private TableColumn<livraison, String> cTrucking;
    @FXML
    private TableView<Commande> TableCommande;
    @FXML
    private TableColumn<Commande, String> cDtate;
    @FXML
    private TableColumn<Commande, Float> cTotalCommande;
    ServiceCommande serc = new ServiceCommande();
    @FXML
    private JFXTextField login;
    @FXML
    private Button gestcomptbutt;
    @FXML
    private Button gestuserview;
    @FXML
    private Button gestequipbutt;
    @FXML
    private VBox buttonVBox;
    @FXML
    private AnchorPane venteAP;
    @FXML
    private TableColumn<Commande, Void> cActionCommande;
    @FXML
    private TableColumn<livraison, Void> cActionLIv;
    @FXML
    private JFXComboBox<String> ComboType;
    @FXML
    private TableColumn<NewEntity, Void> cAction;
    ObservableList<String> Typevalues = FXCollections.observableArrayList("Gold", "Silver", "Diamond");

    public venteController() {
    }

    // 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (!CurrentSession.isAdmin) {
            buttonVBox.getChildren().remove(gestcomptbutt);

        }
        ComboType.setValue("Gold");
        ComboType.setItems(Typevalues);
        venteAP.setPrefSize(CurrentSession.wx, CurrentSession.wy);
        dateDebut.setVisible(false);
        dateFin.setVisible(false);
        NvPrix.setVisible(false);
        NouvTotal.setVisible(false);
        idLabel.setVisible(false);
        radioAffecter.setVisible(false);
        radioAffecter.setToggleGroup(group);
        radioAjouter.setToggleGroup(group);
        radioModifier.setToggleGroup(group);
        radioAjouter.setSelected(true);
        modifierbtn.setVisible(false);
        affecterbtn.setVisible(false);
        totalLabel.setText("0");
        type.setVisible(false);
        ObservableList<String> values = FXCollections.observableArrayList("En Stock", "Utiliser", "All");
        Combo.setItems(values);
        tableCF.setEditable(true);
       
          nubreCarte.setText(Integer.toString(sf.nbreCarte()));
        ArrayList<NewEntity> ne = new ArrayList();
        ne = sf.getNomUser();
        ObservableList ob = FXCollections.observableArrayList(ne);
        tableCF.setItems(ob);
        if (!ne.isEmpty()) {
            // cid.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCartefidelite().getId()).asObject());
            ctype.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getType()));
            cnumPoint.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCartefidelite().getNbPoints()).asObject());
            cDebut.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getDateDebut()));
            cFin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getDateFin()));
            cEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getEmail()));
            cUser.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getNom()));
            supprimerBtn();
        } else {
            tableCF.setPlaceholder(new Label("il y a aucune carte fidelie"));
        }

        /* tableCF.setRowFactory(tv -> {
            TableRow<NewEntity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    CarteFidelite rowData = row.getItem().getCartefidelite();
                    JFXButton btn = new JFXButton("delete");
                    Stage popupwindow = new Stage();
                    popupwindow.initModality(Modality.APPLICATION_MODAL);
                    popupwindow.setTitle("Supprimer");
                    btn.setOnAction(e -> {
                        sf.supprimercarte(rowData);
                        System.out.println(rowData);
                        popupwindow.close();
                        this.afficherCarteFidelite();
                        nubreCarte.setText(Integer.toString(sf.nbreCarte()));
                    });
                    Label label1 = new Label("etes vous sur !!");
                    JFXButton button1 = new JFXButton("Cancel");
                    button1.setOnAction(e -> popupwindow.close());
                    VBox layout = new VBox(10);
                    HBox buttons = new HBox(10);
                    buttons.getChildren().addAll(btn, button1);
                    layout.getChildren().addAll(label1, buttons);
                    layout.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(layout, 300, 250);
                    popupwindow.setScene(scene1);
                    popupwindow.showAndWait();
                }
            });
            return row;
        });*/
        ///Produit
        ArrayList<Produit> p = new ArrayList();
        p = sp.getProduit();
        ObservableList<Produit> op = FXCollections.observableArrayList(p);
        tablePr.setItems(op);
        cNom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom_produit()));
        cPrix.setCellValueFactory(data -> new SimpleFloatProperty(data.getValue().getPrix_vente()).asObject());

        tablePr.setRowFactory(tv -> {
            TableRow<Produit> row = new TableRow<>();
            row.setOnMouseClicked(events -> {
                if (events.getClickCount() == 2 && (!row.isEmpty())) {
                    Produit rowData = row.getItem();
                    p1.add(rowData);
                }
            });
            return row;
        });
        System.out.println(p1.toString());
        ///
        //gestuserbutt.setVisible(CurrentSession.sessionType.equals("admin"));
        //gestcomptbutt.setVisible(CurrentSession.sessionType.equals("admin"));

    }

    @FXML
    public void AjouterCarteFidelite(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"added successfully", ButtonType.OK);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        CarteFidelite c = new CarteFidelite();
        if (Saisie()) {
            c.setType(ComboType.getValue());
            c.setDateDebut(dateFormat.format(cal.getTime()).toString());
            if (ComboType.getValue().equals("Gold")) {
                cal.add(Calendar.MONTH, 6);
                c.setDateFin(dateFormat.format(cal.getTime()).toString());
                c.setNbPoints(Integer.parseInt(nbrPoint.getText()));
                sf.ajoutercarte(c);
            } else if (ComboType.getValue().equals("Silver")) {
                cal.add(Calendar.MONTH, 3);
                c.setDateFin(dateFormat.format(cal.getTime()).toString());
                c.setNbPoints(Integer.parseInt(nbrPoint.getText()));
                sf.ajoutercarte(c);
            } else {
                cal.add(Calendar.YEAR, 1);
                c.setDateFin(dateFormat.format(cal.getTime()).toString());
                c.setNbPoints(Integer.parseInt(nbrPoint.getText()));
                sf.ajoutercarte(c);
            }
            a.showAndWait();
            if(a.getResult().equals(ButtonType.OK)){
            a.close();
            }
        }

        this.afficherCarteFidelite();
        nubreCarte.setText(Integer.toString(sf.nbreCarte()));
    }

    public void afficherCarteFidelite() {
        ArrayList<NewEntity> cf = new ArrayList();
        cf = sf.getNomUser();
        ArrayList<User> nu = new ArrayList();
        ObservableList ob = FXCollections.observableArrayList(cf);
        ObservableList u = FXCollections.observableArrayList(nu);
        tableCF.setItems(ob);
        // cid.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCartefidelite().getNbPoints()).asObject());
        ctype.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getType()));
        cnumPoint.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCartefidelite().getNbPoints()).asObject());
        cDebut.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getDateDebut()));
        cFin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getDateFin()));
        cEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getEmail()));
        cUser.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getNom()));
        supprimerBtn();
    }

    public boolean Saisie() {
        int a = 0;
        Stype.setText("");
        Snum.setText("");
        dateDebut.getEditor().setText("");
        SDateFin.setText("");

        if (nbrPoint.getText().compareTo("") == 0) {
            Snum.setText("Ajouter Un Nombre !");
            a = 1;
        }
        /*  System.out.println(dateDebut.getEditor().getText());
        if (dateFin.getEditor().getText().compareTo("") == 0) {
            SDateFin.setText("Ajouter Date Fin !");
            a++;
        }
        if (dateDebut.getEditor().getText().compareTo("") == 0) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            dateDebut.getEditor().setText(dateFormat.format(date));
        }*/
        if (a == 0) {
            return true;
        }
        return false;
    }

    @FXML
    public void ComboChange(ActionEvent event) {
        if (Combo.getValue() == "En Stock") {
            ArrayList<NewEntity> cf1 = new ArrayList();
            cf1 = sf.CarteStock();
            ObservableList ob = FXCollections.observableArrayList(cf1);
            tableCF.setItems(ob);
//            cid.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCartefidelite().getId()).asObject());
            ctype.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getType()));
            cnumPoint.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCartefidelite().getNbPoints()).asObject());
            cDebut.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getDateDebut()));
            cFin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getDateFin()));
            cEmail.setCellValueFactory(data -> new SimpleStringProperty("null"));
            cUser.setCellValueFactory(data -> new SimpleStringProperty("null"));
        }
        if (Combo.getValue() == "All") {
            afficherCarteFidelite();
        }
        if (Combo.getValue() == "Utiliser") {
            ArrayList<NewEntity> cf = new ArrayList();
            cf = sf.CarteUtiliser();
            ObservableList ob = FXCollections.observableArrayList(cf);
            tableCF.setItems(ob);
            //    cid.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCartefidelite().getId()).asObject());
            ctype.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getType()));
            cnumPoint.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCartefidelite().getNbPoints()).asObject());
            cDebut.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getDateDebut()));
            cFin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCartefidelite().getDateFin()));
            cEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getEmail()));
            cUser.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getNom()));
        }

    }

    @FXML
    public void Modifier(ActionEvent event) {
        sf.modifiercarte(Integer.parseInt(idLabel.getText()), Integer.parseInt(nbrPoint.getText()));
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"updated successfully", ButtonType.OK);
        a.showAndWait();
        if(a.getResult().equals(ButtonType.OK)){
        a.close();
        afficherCarteFidelite();
        }
        
    }

    private void changer() {
        TableViewSelectionModel<NewEntity> selectionModel = tableCF.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        ObservableList selectedItems = selectionModel.getSelectedItems();
        try {
            selectedItems.addListener(new ListChangeListener<NewEntity>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends NewEntity> change) {
                    System.out.println(change.getList().get(0).getCartefidelite().getType().toString());
                    ComboType.setValue(change.getList().get(0).getCartefidelite().getType());
                    nbrPoint.setText(String.valueOf(change.getList().get(0).getCartefidelite().getNbPoints()));
                    dateDebut.getEditor().setText(change.getList().get(0).getCartefidelite().getDateDebut());
                    dateFin.getEditor().setText(change.getList().get(0).getCartefidelite().getDateFin());
                    idLabel.setText(String.valueOf(change.getList().get(0).getCartefidelite().getId()));
                }
            });
        } catch (NullPointerException e) {
            System.out.println("");
        }
    }

    @FXML
    public void Affecter(ActionEvent event) {

    }

    @FXML
    public void selectModifier(ActionEvent event) {
        System.out.println("hellloooo");
        modifierbtn.setVisible(true);
        affecterbtn.setVisible(false);
        ajouterbtn.setVisible(false);
        dateDebut.setVisible(true);
        dateFin.setVisible(true);
        changer();
    }

    @FXML
    public void selectAffecter(ActionEvent event) {
        modifierbtn.setVisible(false);
        affecterbtn.setVisible(true);
        ajouterbtn.setVisible(false);
    }

    @FXML
    public void selectAjouter(ActionEvent event) {
        modifierbtn.setVisible(false);
        affecterbtn.setVisible(false);
        ajouterbtn.setVisible(true);
        dateDebut.setVisible(false);
        dateFin.setVisible(false);

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Produit> ajouterProduitPanier() {
        ArrayList<Produit> p = new ArrayList();
        tablePr.setRowFactory(tv -> {
            TableRow<Produit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Produit rowData = row.getItem();
                    p.add(rowData);
                }
            });
            return row;
        });
        return p;
    }
//////////////////////////////////////////////////////
/////////////////////////////////////////////////////

    public void panier(HashSet<Produit> produits) {
        VBox vbox = new VBox();
        final int quantity = 1;
        float Total = 0;
        HashMap<Integer, Integer> panier = new HashMap<>();
        HashMap<Float, Integer> pan = new HashMap<>();
        produits.forEach(p -> {
            JFXButton add = new JFXButton("+");
            JFXButton remove = new JFXButton("-");
            remove.setStyle("-fx-background-color:  #ffb3b3"
                    + "; -fx-border-color: #ffacac; -fx-border-width: 3;"
                    + " -fx-background-radius: 10 10 10 10;"
                    + "; -fx-border-radius: 10 10 10 10;");
            add.setStyle("-fx-background-color:  #dcffb7"
                    + "; -fx-border-color: #dcffb7; -fx-border-width: 3;"
                    + " -fx-background-radius: 10 10 10 10;"
                    + "; -fx-border-radius: 10 10 10 10;");
            Label qu = new Label("1");
            qu.setStyle("-fx-font-size: 20px; -fx-font-weight:bold; -fx-font-family: Tahoma;");

            qu.setText(String.valueOf(quantity));
            JFXButton btn = new JFXButton("Supprimer");
            btn.setStyle("-fx-background-color:  #d3d3d3"
                    + "; -fx-border-color: #b5cde5; -fx-border-width: 3;"
                    + " -fx-background-radius: 10 10 10 10;"
                    + "; -fx-border-radius: 10 10 10 10;-fx-text-fill: BLACK;-fx-font-size: 18px;-fx-opacity: 0.7;");
            if (Integer.valueOf(qu.getText()) < 2) {
                remove.setDisable(true);
            }
            add.setOnAction(e -> {

                int a;
                a = Integer.valueOf(qu.getText());
                a++;
                qu.setText(String.valueOf(a));
                remove.setDisable(false);
                ps.ajouterProduit(p.getRef_produit(), Integer.valueOf(qu.getText()), panier);

                pan.put(p.getPrix_vente(), Integer.valueOf(qu.getText()));
                calculer(pan);

            });
            remove.setOnAction(e -> {

                int a;
                a = Integer.valueOf(qu.getText());
                if (a == 2) {
                    remove.setDisable(true);
                }
                a--;
                qu.setText(String.valueOf(a));
                pan.put(p.getPrix_vente(), Integer.valueOf(qu.getText()));
                calculer(pan);
                ps.supprimerProduit(p.getRef_produit(), Integer.valueOf(qu.getText()), panier);

            });

            HBox hbox = new HBox();
            hbox.setSpacing(40);
            hbox.setPadding(new Insets(60, 60, 30, 80));
            Label l = new Label();
            l.setText(p.getNom_produit());
            l.setStyle(" -fx-font-family:monospace;-fx-font-size: 28px;");
            hbox.getChildren().add(l);
            hbox.getChildren().add(remove);
            hbox.getChildren().add(qu);
            hbox.getChildren().add(add);
            hbox.getChildren().add(btn);
            vbox.getChildren().add(hbox);
            pan.put(p.getPrix_vente(), Integer.valueOf(qu.getText()));
            ps.ajouterProduit(p.getRef_produit(), Integer.valueOf(qu.getText()), panier);
            btn.setOnAction(e -> {
                //System.out.println(p.getNom());
                produits.remove(p);
                pan.remove(p.getPrix_vente());
                hbox.getChildren().removeAll(l, btn, remove, qu, add);
                vbox.getChildren().remove(hbox);
                calculer(pan);
                //totalLabel.setText(String.valueOf(ps.totalPanier(panier)));
            });
        });
        //loula
        calculer(pan);
        System.out.println("le total dans panier est :=" + ps.totalPanier(panier));
        //totalLabel.setText(String.valueOf(ps.totalPanier(panier)));
        panierAnchor.getChildren().add(vbox);
        Panier = panier;
    }

    @FXML
    public void goPanier(Event event) {
        panierAnchor.getChildren().clear();
        panierAnchor.getChildren().addAll(Total, totalLabel, NouvTotal, NvPrix, acheterButton, utliser_livraison, utliser_CF, line);
        System.out.println("Size de p1:" + p1.size());
        panier(p1);
        NvPrix.setVisible(false);
        NouvTotal.setVisible(false);
    }

    @FXML
    public void AddProduct(Event event) {
    }

    private void calculer(Map<Float, Integer> panier) {
        float Total = 0;
        for (Map.Entry<Float, Integer> entry : panier.entrySet()) {
            Total += (entry.getKey() * entry.getValue());
        }
        totalLabel.setText(String.valueOf(Total));
        // Panier=panier;
    }

    @FXML
    public void Acheter(ActionEvent event) {
        GestionMouvementProduit MP = new GestionMouvementProduit();

        try {
            if (NvPrix.isVisible()) {
                ps.checkout(Panier, Float.valueOf(NvPrix.getText()), utliser_livraison.isSelected(), login.getText());
            } else {
                ps.checkout(Panier, Float.valueOf(totalLabel.getText()), utliser_livraison.isSelected(), login.getText());
            }
            ps.totalnbpoint(Panier);
        } catch (AWTException ex) {
            Logger.getLogger(venteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        panierAnchor.getChildren().clear();
        panierAnchor.getChildren().addAll(Total, totalLabel, NouvTotal, NvPrix, acheterButton, utliser_livraison, utliser_CF, line);
        p1.clear();
       envoyermail("chaimadjm55@gmail.com");
    }

    @FXML
    public void use_Livraison(ActionEvent event) {

        if (!utliser_CF.isSelected() && utliser_livraison.isSelected()) {
            NvPrix.setVisible(true);
            NouvTotal.setVisible(true);
            float b = Float.valueOf(totalLabel.getText());
            float total = b + ((b * 15 / 100));
            NvPrix.setText(String.valueOf(total));

        }
        if (utliser_CF.isSelected() && !utliser_livraison.isSelected()) {
            NvPrix.setVisible(true);
            NouvTotal.setVisible(true);
            float b = Float.valueOf(totalLabel.getText());
            float total = b - ((b * 25 / 100));
            NvPrix.setText(String.valueOf(total));

        }
        if (utliser_CF.isSelected() && utliser_livraison.isSelected()) {
            NvPrix.setVisible(true);
            NouvTotal.setVisible(true);
            float b = Float.valueOf(totalLabel.getText());
            float total = b - ((b * 25 / 100)) + ((b * 15 / 100));
            NvPrix.setText(String.valueOf(total));
        }
        if (!utliser_CF.isSelected() && !utliser_livraison.isSelected()) {
            NvPrix.setVisible(false);
            NouvTotal.setVisible(false);
            //int b = Integer.valueOf(totalLabel.getText());
            //int a=Integer.valueOf(totalLabel.getText());
            // float total = b + ((b * 15 / 100));
            // NvPrix.setText(String.valueOf(total));
        }

    }

    @FXML
    public void Use_Cf(ActionEvent event) {
        if (utliser_CF.isSelected() && !utliser_livraison.isSelected()) {
            NvPrix.setVisible(true);
            NouvTotal.setVisible(true);

            float b = Float.valueOf(totalLabel.getText());
            float total = b - ((b * 25 / 100));
            NvPrix.setText(String.valueOf(total));

        }
        if (!utliser_CF.isSelected() && utliser_livraison.isSelected()) {
            NvPrix.setVisible(true);
            NouvTotal.setVisible(true);

            float b = Float.valueOf(totalLabel.getText());
            float total = b + ((b * 15 / 100));
            NvPrix.setText(String.valueOf(total));

        }
        if (utliser_CF.isSelected() && utliser_livraison.isSelected()) {
            NvPrix.setVisible(true);
            NouvTotal.setVisible(true);

            float b = Float.valueOf(totalLabel.getText());
            float total = b - ((b * 25 / 100)) + ((b * 15 / 100));
            NvPrix.setText(String.valueOf(total));
        }
        if (!utliser_CF.isSelected() && !utliser_livraison.isSelected()) {
            NvPrix.setVisible(false);
            NouvTotal.setVisible(false);
            //int b = Integer.valueOf(totalLabel.getText());
            //int a=Integer.valueOf(totalLabel.getText());
            // float total = b + ((b * 15 / 100));
            // NvPrix.setText(String.valueOf(total));
        }
    }

    private void afficherlivraison() {
        ArrayList<livraison> l = new ArrayList();
        l = sl.getlivraison();
        ObservableList ol = FXCollections.observableArrayList(l);
        TableLiv.setItems(ol);
        cTotal.setCellValueFactory(data -> new SimpleFloatProperty(data.getValue().getTotal()).asObject());
        cEtat.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEtat()));
        cTrucking.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTrucking()));
        suppbtnLivraison();

    }

    private void affichercommande() {
        ArrayList<Commande> l = new ArrayList();
        l = serc.getCommande();
        ObservableList ol = FXCollections.observableArrayList(l);
        TableCommande.setItems(ol);
        cTotalCommande.setCellValueFactory(data -> new SimpleFloatProperty(data.getValue().getTotal()).asObject());
        cDtate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
        //  cTrucking.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTrucking()));
        supprimerbtnCommande();
    }

    @FXML
    public void afficherLivraion(Event event) {
        // afficherlivraison();
        TableLiv.setRowFactory(tv -> {
            TableRow<livraison> row = new TableRow<>();
            row.setOnMouseClicked(events -> {
                if (events.getClickCount() == 2 && (!row.isEmpty())) {
                    livraison rowData = row.getItem();
                    JFXButton btn = new JFXButton("delete");
                    Stage popupwindow = new Stage();
                    popupwindow.initModality(Modality.APPLICATION_MODAL);
                    popupwindow.setTitle("Supprimer");
                    btn.setOnAction(e -> {
                        sl.supprimerlivraison(rowData);
                        popupwindow.close();
                        this.afficherlivraison();
                    });
                    Label label1 = new Label("etes vous sur !!");
                    JFXButton button1 = new JFXButton("Cancel");
                    button1.setOnAction(e -> popupwindow.close());
                    VBox layout = new VBox(10);
                    HBox buttons = new HBox(10);
                    buttons.getChildren().addAll(btn, button1);
                    layout.getChildren().addAll(label1, buttons);
                    layout.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(layout, 300, 250);
                    popupwindow.setScene(scene1);
                    popupwindow.showAndWait();
                }
            });
            return row;
        });
        afficherlivraison();
    }

    @FXML
    public void AfficherCommande(Event event) {
        this.affichercommande();
        TableCommande.setRowFactory(tv -> {
            TableRow<Commande> row = new TableRow<>();
            row.setOnMouseClicked(events -> {
                if (events.getClickCount() == 2 && (!row.isEmpty())) {
                    Commande rowData = row.getItem();
                    JFXButton btn = new JFXButton("delete");
                    Stage popupwindow = new Stage();
                    popupwindow.initModality(Modality.APPLICATION_MODAL);
                    popupwindow.setTitle("Supprimer");
                    btn.setOnAction(e -> {
                        System.out.println(rowData.getId());
                        serc.supprimerCommande(rowData);

                        popupwindow.close();
                        this.affichercommande();
                    });
                    Label label1 = new Label("you sure you want to delete!");
                    JFXButton button1 = new JFXButton("Cancel");
                    button1.setOnAction(e -> popupwindow.close());
                    VBox layout = new VBox(10);
                    HBox buttons = new HBox(10);
                    buttons.getChildren().addAll(btn, button1);
                    layout.getChildren().addAll(label1, buttons);
                    layout.setAlignment(Pos.CENTER);
                    Scene scene1 = new Scene(layout, 300, 250);
                    popupwindow.setScene(scene1);
                    popupwindow.showAndWait();
                }
            });
            return row;
        });
    }

    public void envoyermail(String to) {

        Properties props = System.getProperties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", "chaima.jemaeil@esprit.tn");
        props.put("mail.smtp.password", "chacha1234");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress());
            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject("Your New Commande");
            message.setText("Hello thank you for your trust, /n Best Wishes");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, "chaima.jemaiel@esprit.tn", "chacha1234");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Done");
        } catch (MessagingException e) {
            System.out.println("excption");
        }
    }

    @FXML
    private void toViewComptaAdmin(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) venteAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) venteAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLComptabiliteAdmin.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewUserAdmin(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) venteAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) venteAP.getBoundsInParent().getHeight();

        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLUserAdmin.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();

    }

    @FXML
    private void toViewGestEquip(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) venteAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) venteAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewStock(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) venteAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) venteAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("PageAcceuil.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewMark(ActionEvent event) throws IOException {
        /*CurrentSession.wx = (int) venteAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) venteAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();*/
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) venteAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) venteAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewAvis(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) venteAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) venteAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListAvis.fxml"));

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewRec(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) venteAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) venteAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    private void supprimerBtn() {
        Callback<TableColumn<NewEntity, Void>, TableCell<NewEntity, Void>> cellFactory = new Callback<TableColumn<NewEntity, Void>, TableCell<NewEntity, Void>>() {
            @Override
            public TableCell<NewEntity, Void> call(final TableColumn<NewEntity, Void> param) {
                final TableCell<NewEntity, Void> cell = new TableCell<NewEntity, Void>() {
                    private final Button btn = new Button("supprimer");

                    {
                        btn.setStyle("-fx-background-color:  #ffb3b3"
                                + "; -fx-border-color: #ffacac; -fx-border-width: 3;"
                                + " -fx-background-radius: 10 10 10 10;"
                                + "; -fx-border-radius: 10 10 10 10;");
                        btn.setOnAction((ActionEvent event) -> {
                            CarteFidelite carte = getTableView().getItems().get(getIndex()).getCartefidelite();
                            System.out.println(carte);
                            JFXButton supp = new JFXButton("delete");
                            Stage popupwindow = new Stage();
                            popupwindow.initModality(Modality.APPLICATION_MODAL);
                            popupwindow.setTitle("Supprimer");
                            supp.setOnAction(e -> {
                                sf.supprimercarte(carte);
                                popupwindow.close();
                                afficherCarteFidelite();
                                nubreCarte.setText(Integer.toString(sf.nbreCarte()));
                            });
                            Label label1 = new Label("etes vous sur !!");
                            JFXButton button1 = new JFXButton("Cancel");
                            button1.setOnAction(e -> popupwindow.close());
                            VBox layout = new VBox(10);
                            HBox buttons = new HBox(10);
                            buttons.getChildren().addAll(supp, button1);
                            layout.getChildren().addAll(label1, buttons);
                            layout.setAlignment(Pos.CENTER);
                            Scene scene1 = new Scene(layout, 300, 250);
                            popupwindow.setScene(scene1);
                            popupwindow.showAndWait();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        cAction.setCellFactory(cellFactory);
    }

    private void supprimerbtnCommande() {
        Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>> cellFactory = new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(final TableColumn<Commande, Void> param) {
                final TableCell<Commande, Void> cell = new TableCell<Commande, Void>() {
                    private final Button btn = new Button("supprimer");

                    {
                        btn.setStyle("-fx-background-color:  #ffb3b3"
                                + "; -fx-border-color: #ffacac; -fx-border-width: 3;"
                                + " -fx-background-radius: 10 10 10 10;"
                                + "; -fx-border-radius: 10 10 10 10;");

                        btn.setOnAction((ActionEvent event) -> {
                            Commande commande = getTableView().getItems().get(getIndex());
                            System.out.println(commande);
                            JFXButton supp = new JFXButton("delete");
                            Stage popupwindow = new Stage();
                            popupwindow.initModality(Modality.APPLICATION_MODAL);
                            popupwindow.setTitle("Supprimer");
                            supp.setOnAction(e -> {
                                serc.supprimerCommande(commande);
                                popupwindow.close();
                                affichercommande();
                                //nubreCarte.setText(Integer.toString(sf.nbreCarte()));
                            });
                            Label label1 = new Label("etes vous sur !!");
                            JFXButton button1 = new JFXButton("Cancel");
                            button1.setOnAction(e -> popupwindow.close());
                            VBox layout = new VBox(10);
                            HBox buttons = new HBox(10);
                            buttons.getChildren().addAll(supp, button1);
                            layout.getChildren().addAll(label1, buttons);
                            layout.setAlignment(Pos.CENTER);
                            Scene scene1 = new Scene(layout, 300, 250);
                            popupwindow.setScene(scene1);
                            popupwindow.showAndWait();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        cActionCommande.setCellFactory(cellFactory);
    }

    private void suppbtnLivraison() {
        Callback<TableColumn<livraison, Void>, TableCell<livraison, Void>> cellFactory = new Callback<TableColumn<livraison, Void>, TableCell<livraison, Void>>() {
            @Override
            public TableCell<livraison, Void> call(final TableColumn<livraison, Void> param) {
                final TableCell<livraison, Void> cell = new TableCell<livraison, Void>() {
                    private final Button btn = new Button("supprimer");

                    {
                        btn.setStyle("-fx-background-color:  #ffb3b3"
                                + "; -fx-border-color: #ffacac; -fx-border-width: 3;"
                                + " -fx-background-radius: 10 10 10 10;"
                                + "; -fx-border-radius: 10 10 10 10;");
                        btn.setOnAction((ActionEvent event) -> {
                            livraison liv = getTableView().getItems().get(getIndex());

                            JFXButton supp = new JFXButton("delete");
                            Stage popupwindow = new Stage();
                            popupwindow.initModality(Modality.APPLICATION_MODAL);
                            popupwindow.setTitle("Supprimer");
                            supp.setOnAction(e -> {
                                sl.supprimerlivraison(liv);
                                popupwindow.close();
                                afficherlivraison();
                                //nubreCarte.setText(Integer.toString(sf.nbreCarte()));
                            });
                            Label label1 = new Label("etes vous sur !!");
                            JFXButton button1 = new JFXButton("Cancel");
                            button1.setOnAction(e -> popupwindow.close());
                            VBox layout = new VBox(10);
                            HBox buttons = new HBox(10);
                            buttons.getChildren().addAll(supp, button1);
                            layout.getChildren().addAll(label1, buttons);
                            layout.setAlignment(Pos.CENTER);
                            Scene scene1 = new Scene(layout, 300, 250);
                            popupwindow.setScene(scene1);
                            popupwindow.showAndWait();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        cActionLIv.setCellFactory(cellFactory);

    }

}
