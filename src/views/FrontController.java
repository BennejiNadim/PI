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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.CurrentSession;
import service.ServiceHistoriqueEquipement;
import service.ServiceMateriels;
import service.ServiceVehicule;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FrontController implements Initializable {

    @FXML
    private Tab VehiculeTab;
    @FXML
    private Tab MaterielsTab;
    @FXML
    private Tab HistoriqueTab;
    @FXML
    private Button btajouterv;
    @FXML
    private Button btmodifierv;
    @FXML
    private Button btsupprimerv;
    @FXML
    private Button btchangeretatv;

    ServiceVehicule sv = new ServiceVehicule();
    ServiceMateriels sm = new ServiceMateriels();
    ServiceHistoriqueEquipement sh = new ServiceHistoriqueEquipement();

    @FXML
    private TableView<Vehicule> TabVehicule;
    @FXML
    private TableColumn delete;
    @FXML
    private Button btajouterm;
    @FXML
    private Button btmodifierm;
    @FXML
    private Button btsupprimerm;
    @FXML
    private Button btchangeretatm;
    @FXML
    private TableView<HistoriqueEquipement> TabHistorique;
    @FXML
    private Button btajouterh;
    @FXML
    private Button btmodifierh;
    @FXML
    private Button btsupprimerh;
    @FXML
    private TableView<Materiels> TabMateriels;
    @FXML
    private TableColumn<?, ?> Qte;
    @FXML
    private TableColumn<?, ?> Etat1;
    @FXML
    private TableColumn<?, ?> Qte1;
    @FXML
    private TableColumn<?, ?> Date;
    @FXML
    private TableColumn<?, ?> Operation;
    @FXML
    private TableColumn Equipement;
    @FXML
    private TableColumn<?, ?> Id;
    @FXML
    private TableColumn<?, ?> Nom;
    @FXML
    private TableColumn<?, ?> Matricule;
    @FXML
    private TableColumn<?, ?> Couleur;
    @FXML
    private TableColumn<?, ?> Utilisation;
    @FXML
    private TableColumn<?, ?> KM;
    @FXML
    private TableColumn<?, ?> Etat;
    @FXML
    private TableColumn<?, ?> Id1;
    @FXML
    private TableColumn<?, ?> Nom1;
    @FXML
    private TableColumn<?, ?> Id2;
    @FXML
    private TableColumn<?, ?> Id_m;
    @FXML
    private TableColumn<?, ?> Id_v;
    @FXML
    private TextField search;
    ObservableList<Vehicule> list;
    ObservableList<Materiels> listm;
    ObservableList<HistoriqueEquipement> listh;
    @FXML
    private AnchorPane supprimerr;
    @FXML
    private Button btno;
    @FXML
    private Button btyes;
    @FXML
    private AnchorPane selectt;
    @FXML
    private ImageView logo;
    @FXML
    private Button OKs;
    @FXML
    private TextField searchm;
    @FXML
    private TextField searchh;
    @FXML
    private AnchorPane done;
    @FXML
    private Button okd;
    public static int don;
    @FXML
    private TableColumn<?, ?> prixe;
    @FXML
    private Button btstat;
    @FXML
    private VBox buttonVBox;
    @FXML
    private Button btncomptabilite;
    @FXML
    private AnchorPane matAP;
    @FXML
    private TableColumn<?, ?> id_f;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        matAP.setPrefSize(CurrentSession.wx, CurrentSession.wy);
        if(!CurrentSession.isAdmin){
        buttonVBox.getChildren().remove(btncomptabilite);
       
        }

        if (don == 1) {
            done.setVisible(true);
        }
        TabVehicule.getItems().clear();
        list = sv.afficherVehicule();
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Matricule.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
        Couleur.setCellValueFactory(new PropertyValueFactory<>("Couleur"));
        Utilisation.setCellValueFactory(new PropertyValueFactory<>("Utilisation"));
        KM.setCellValueFactory(new PropertyValueFactory<>("KM"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                System.out.println(newValue);
                if (newValue.isEmpty()) {
                    TabVehicule.setItems(list);

                } else {
                    List<Vehicule> obsRr = list.stream().filter(o -> o.getMatricule().toLowerCase().contains(newValue.toLowerCase())
                    ).collect((Collectors.toList()));
                    obsRr.forEach(o -> {
                        System.out.println("processed list, only even numbers: " + o);
                    });
                    ObservableList<Vehicule> sortedList = FXCollections.observableArrayList(obsRr);
                    TabVehicule.setItems(sortedList);
                }

//                 
            });

        });
        TabVehicule.setItems(sv.afficherVehicule());

        TabMateriels.getItems().clear();
        listm = sm.afficherMateriels();
        TabMateriels.getItems().clear();
        Id1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Nom1.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Qte.setCellValueFactory(new PropertyValueFactory<>("Qte"));
        Etat1.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        searchm.setOnKeyReleased(e -> {
            searchm.textProperty().addListener((observableValue, oldValue, newValue) -> {
                System.out.println(newValue);
                if (newValue.isEmpty()) {
                    TabMateriels.setItems(listm);

                } else {
                    List<Materiels> obsRr = listm.stream().filter(o -> o.getNom().toLowerCase().contains(newValue.toLowerCase())
                    ).collect((Collectors.toList()));
                    obsRr.forEach(o -> {
                        System.out.println("processed list, only even numbers: " + o);
                    });
                    ObservableList<Materiels> sortedList = FXCollections.observableArrayList(obsRr);
                    TabMateriels.setItems(sortedList);
                }

//                 
            });

        });
        TabMateriels.setItems(sm.afficherMateriels());

        TabHistorique.getItems().clear();
        listh = sh.afficherHistorique();
        TabHistorique.getItems().clear();
        Id2.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Operation.setCellValueFactory(new PropertyValueFactory<>("Operation"));
        Equipement.setCellValueFactory(new PropertyValueFactory<>("Eq"));
        Qte1.setCellValueFactory(new PropertyValueFactory<>("Qte"));
        Id_m.setCellValueFactory(new PropertyValueFactory<>("Id_m"));
        Id_v.setCellValueFactory(new PropertyValueFactory<>("Id_v"));
        id_f.setCellValueFactory(new PropertyValueFactory<>("Id_f"));
        searchh.setOnKeyReleased(e -> {
            searchh.textProperty().addListener((observableValue, oldValue, newValue) -> {
                System.out.println(newValue);
                if (newValue.isEmpty()) {
                    TabHistorique.setItems(listh);

                } else {
                    List<HistoriqueEquipement> obsRr = listh.stream().filter(o -> o.getDate().toString().toLowerCase().contains(newValue.toLowerCase())
                    ).collect((Collectors.toList()));
                    obsRr.forEach(o -> {
                        System.out.println("processed list, only even numbers: " + o);
                    });
                    ObservableList<HistoriqueEquipement> sortedList = FXCollections.observableArrayList(obsRr);
                    TabHistorique.setItems(sortedList);
                }

//                 
            });

        });
        TabHistorique.setItems(sh.afficherHistorique());

    }
    static int id_courant = 0;

    @FXML
    private void btajouterv(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjoutVehicule.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btmodifierv(ActionEvent event) {
        if (TabVehicule.getSelectionModel().isEmpty()) {
            selectt.setVisible(true);
        } else {
            id_courant = TabVehicule.getSelectionModel().getSelectedItem().getId();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ModifierVehicule.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void btsupprimerv(ActionEvent event) {
        if (TabVehicule.getSelectionModel().isEmpty()) {
            selectt.setVisible(true);
        } else {
            supprimerr.setVisible(true);
        }

    }

    @FXML
    private void HistoriqueTab(Event event) {

        TabHistorique.getItems().clear();
        Id2.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Operation.setCellValueFactory(new PropertyValueFactory<>("Operation"));
        Equipement.setCellValueFactory(new PropertyValueFactory<>("Eq"));
        Qte1.setCellValueFactory(new PropertyValueFactory<>("Qte"));
        Id_m.setCellValueFactory(new PropertyValueFactory<>("Id_m"));
        Id_v.setCellValueFactory(new PropertyValueFactory<>("Id_v"));
        prixe.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        id_f.setCellValueFactory(new PropertyValueFactory<>("Id_f"));
        TabHistorique.setItems(sh.afficherHistorique());

    }

    @FXML
    private void btajouterm(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjoutMateriels.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btmodifierm(ActionEvent event) {
        if (TabMateriels.getSelectionModel().isEmpty()) {
            selectt.setVisible(true);
        } else {
            id_courant = TabMateriels.getSelectionModel().getSelectedItem().getId();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ModifierMateriels.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void btsupprimerm(ActionEvent event) {
        if (TabMateriels.getSelectionModel().isEmpty()) {
            selectt.setVisible(true);
        } else {
            supprimerr.setVisible(true);
        }

    }

    @FXML
    private void btajouterh(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjoutHistorique.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btsupprimerh(ActionEvent event) {
        if (TabHistorique.getSelectionModel().isEmpty()) {
            selectt.setVisible(true);
        } else {
            supprimerr.setVisible(true);
        }
    }

    @FXML
    private void VehiculeTab(Event event) {
        TabVehicule.getItems().clear();
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Matricule.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
        Couleur.setCellValueFactory(new PropertyValueFactory<>("Couleur"));
        Utilisation.setCellValueFactory(new PropertyValueFactory<>("Utilisation"));
        KM.setCellValueFactory(new PropertyValueFactory<>("KM"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        TabVehicule.setItems(sv.afficherVehicule());
    }

    @FXML
    private void MaterielsTab(Event event) {
        TabMateriels.getItems().clear();
        Id1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Nom1.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Qte.setCellValueFactory(new PropertyValueFactory<>("Qte"));
        Etat1.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        TabMateriels.setItems(sm.afficherMateriels());
    }

    @FXML
    private void btchangeretatm(ActionEvent event) {
        if (TabMateriels.getSelectionModel().isEmpty()) {
            selectt.setVisible(true);
        } else {
            id_courant = TabMateriels.getSelectionModel().getSelectedItem().getId();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ModifierEtatm.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void btmodifierh(ActionEvent event) {
        if (TabHistorique.getSelectionModel().isEmpty()) {
            selectt.setVisible(true);
        } else {
            id_courant = TabHistorique.getSelectionModel().getSelectedItem().getId();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ModifierHistorique.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void btchangeretatv(ActionEvent event) {
        if (TabVehicule.getSelectionModel().isEmpty()) {
            selectt.setVisible(true);
        } else {
            id_courant = TabVehicule.getSelectionModel().getSelectedItem().getId();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ModifierEtatv.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void btno(ActionEvent event) {
        supprimerr.setVisible(false);

    }

    @FXML
    private void btyes(ActionEvent event) {
        if (!TabVehicule.getSelectionModel().isEmpty()) {
            supprimerr.setVisible(false);
            sv.supprimerVehicule(TabVehicule.getSelectionModel().getSelectedItem().getId());
            TabVehicule.getSelectionModel().clearSelection();
            TabVehicule.getItems().clear();
            TabVehicule.getItems().addAll(sv.afficherVehicule());

        }
        if (!TabMateriels.getSelectionModel().isEmpty()) {
            supprimerr.setVisible(false);
            sm.supprimerMateriels(TabMateriels.getSelectionModel().getSelectedItem().getId());
            TabMateriels.getSelectionModel().clearSelection();
            TabMateriels.getItems().clear();
            TabMateriels.getItems().addAll(sm.afficherMateriels());
        }
        if (!TabHistorique.getSelectionModel().isEmpty()) {
            supprimerr.setVisible(false);
            sh.supprimerHistorique(TabHistorique.getSelectionModel().getSelectedItem().getId());
            TabHistorique.getSelectionModel().clearSelection();
            TabHistorique.getItems().clear();
            TabHistorique.getItems().addAll(sh.afficherHistorique());
        }
        done.setVisible(true);
    }

    @FXML
    private void OKs(ActionEvent event) {
        selectt.setVisible(false);
    }

    @FXML
    private void okd(ActionEvent event) {

        done.setVisible(false);
        don = 0;

    }

    @FXML
    private void btstat(ActionEvent event) {
           try {
            Parent root = FXMLLoader.load(getClass().getResource("statE.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void toViewComptaAdmin(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) matAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) matAP.getBoundsInParent().getHeight();
        Stage appStage=(Stage)buttonVBox.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("FXMLComptabiliteAdmin.fxml"));
        Scene scene=new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }
    
    @FXML
    private void toViewUserAdmin(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) matAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) matAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLUserAdmin.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();

    }

    

    @FXML
    private void toViewStock(ActionEvent event) throws IOException{
        CurrentSession.wx = (int) matAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) matAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("PageAcceuil.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }


    @FXML
    private void toViewMark(ActionEvent event) throws IOException{
       /*CurrentSession.wx = (int) matAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) matAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();*/
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) matAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) matAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

 @FXML
    private void toViewVente(ActionEvent event) throws IOException{
        CurrentSession.wx = (int) matAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) matAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("vente.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewAvis(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) matAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) matAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListAvis.fxml"));
       
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewRec(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) matAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) matAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
        
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

}
