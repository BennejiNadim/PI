/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import entite.Avis;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.AvisService;
import service.CurrentSession;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class ListAvisController implements Initializable {

    @FXML
    private TableColumn<Avis,Integer> id;
    @FXML
    private TableColumn<Avis, String> Avis;
    @FXML
    private TableColumn<Avis, String> Commentaire;
    @FXML
    private Button add;
    @FXML
    private Button del;
    @FXML
    private Button upd;

    private ObservableList<Avis> avisdata = FXCollections.observableArrayList();
    @FXML
    private TableView<Avis> Table;
    @FXML
    private VBox buttonVBox;
    @FXML
    private ImageView logo;
    @FXML
    private Button gestequipbutt;
    @FXML
    private Button gestuserview;
    @FXML
    private Button gestcomptbutt;
    @FXML
    private AnchorPane avisAP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        avisAP.setPrefSize(CurrentSession.wx, CurrentSession.wy);
        if(!CurrentSession.isAdmin){
        buttonVBox.getChildren().remove(gestcomptbutt);
       
        }
        
         List<Avis> listUser= new ArrayList<Avis>();
        AvisService as =  new AvisService();
        listUser = as.getAllAvis();
        avisdata.clear();
        avisdata.addAll(listUser);
        Table.setItems(avisdata);
        // TODO
        
          id.setCellValueFactory(
            new PropertyValueFactory<>("id")
        );
        Avis.setCellValueFactory(
            new PropertyValueFactory<>("chxAvis")
        );
        Commentaire.setCellValueFactory(
            new PropertyValueFactory<>("contenu")
        );
           

    }    

    @FXML
    private void Add(ActionEvent event) {
        
        
         
     Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("AjouterAvis.fxml"));
            Stage myWindow =  (Stage) Table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Ajouter Avis");
            myWindow.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"are you sure you want to delete",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
    
                Avis AvisSelc = (Avis) Table.getSelectionModel().getSelectedItem();
                
                AvisService as = new AvisService();
                as.SupprimerAvis(AvisSelc.getId());
                resetTableData();
                a.close();
        }else{
        a.close();
        }

    
    }
    
    public void resetTableData()
    {
        List<Avis> listAVIS = new ArrayList<>();
                AvisService as = new AvisService();
        listAVIS = as.getAllAvis();
        ObservableList<Avis> data = FXCollections.observableArrayList(listAVIS);
        Table.setItems(data);
    }

    @FXML
    private void Update(ActionEvent event) {
        

        try {
                   ModifierAvisController.ida =  Table.getSelectionModel().getSelectedItem().getId();

           
     Parent root ;
          root = FXMLLoader.load(getClass().getResource("ModifierAvis.fxml"));
            Stage myWindow =  (Stage) Table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Modifier Avis");
            myWindow.show();
            
           
        } catch (Exception ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    @FXML
    private void toViewComptaAdmin(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) avisAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) avisAP.getBoundsInParent().getHeight();

        Stage appStage=(Stage)buttonVBox.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("FXMLComptabiliteAdmin.fxml"));
        Scene scene=new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }
    
    @FXML
    private void toViewGestEquip(ActionEvent event) throws IOException{
        CurrentSession.wx = (int) avisAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) avisAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewStock(ActionEvent event) throws IOException{
       CurrentSession.wx = (int) avisAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) avisAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("PageAcceuil.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }


    @FXML
    private void toViewMark(ActionEvent event) throws IOException{
        
       /* CurrentSession.wx = (int) avisAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) avisAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();*/
    }
	
	@FXML
    private void toViewVente(ActionEvent event) throws IOException{
        CurrentSession.wx = (int) avisAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) avisAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("vente.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    

    @FXML
    private void logout(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) avisAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) avisAP.getBoundsInParent().getHeight();CurrentSession.wx=(int) avisAP.getPrefWidth();
        CurrentSession.wy=(int) avisAP.getPrefHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    

    @FXML
    private void toViewRec(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) avisAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) avisAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
       
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    
    @FXML
    private void toViewUserAdmin(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) avisAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) avisAP.getBoundsInParent().getHeight();

        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLUserAdmin.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();

    }

    
    
}
