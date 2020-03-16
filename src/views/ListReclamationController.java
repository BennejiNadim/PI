/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import entite.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.AvisService;
import service.CurrentSession;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Rzouga
 */
public class ListReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> Table;
    @FXML
    private TableColumn<Reclamation,Integer> id;
    @FXML
    private TableColumn<Reclamation, Integer> nomuser;
    @FXML
    private TableColumn<Reclamation, Date> date;
    @FXML
    private TextField input_rech;
    private ObservableList<Reclamation> recdata = FXCollections.observableArrayList();
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
    private AnchorPane recAP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recAP.setPrefSize(CurrentSession.wx, CurrentSession.wy);
        if(!CurrentSession.isAdmin){
        buttonVBox.getChildren().remove(gestcomptbutt);
       
        }
        List<Reclamation> listRec= new ArrayList<Reclamation>();
        ReclamationService rs =  new ReclamationService();
        listRec = rs.getAllRec();
        recdata.clear();
        recdata.addAll(listRec);
        Table.setItems(recdata);
        
        
        id.setCellValueFactory(
            new PropertyValueFactory<>("id")
        );
        nomuser.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        date.setCellValueFactory(
            new PropertyValueFactory<>("date")
        );
        // TODO
        
        // TODO
           

    }    

    @FXML
    private void Rechercher(ActionEvent event) {
        
         id.setCellValueFactory(
            new PropertyValueFactory<>("id")
        );
        nomuser.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        date.setCellValueFactory(
            new PropertyValueFactory<>("date")
        );
            
            ReclamationService rs = new ReclamationService();
            List<Reclamation> list = rs.getAllRec();
            
            //tableview.setItems(observablelist);
            
            FilteredList<Reclamation> filtredData= new FilteredList<>(recdata, b ->true);
            input_rech.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super Reclamation> Evenement;
                filtredData.setPredicate((Reclamation evenement) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(evenement.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reclamation> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(Table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        Table.setItems(sortedData);
       
    }
        
        
    

    @FXML
    private void Details(ActionEvent event) {
        
         DetailsReclamationController.idr = Table.getSelectionModel().getSelectedItem().getId();
         Parent root ;
        try {
            root = FXMLLoader.load(getClass().getResource("DetailsReclamation.fxml"));
            Stage myWindow =  (Stage) Table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("DétailReclamation Reclamation");
            myWindow.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void deleteRec(ActionEvent event) throws SQLException {
        Reclamation recSelc = (Reclamation) Table.getSelectionModel().getSelectedItem();
                if(recSelc!=null){        
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"are you sure you want to delete",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
        
      
                ReclamationService rs = new ReclamationService();
                rs.SupprimerReclamation(recSelc.getId());
                resetTableData();
                a.close();
                
        }else{
        a.close();
        }
        }

    }
    
    public void resetTableData()
    {
        List<Reclamation> listRec = new ArrayList<>();
                ReclamationService rs = new ReclamationService();
        listRec = rs.getAllRec();
        ObservableList<Reclamation> data = FXCollections.observableArrayList(listRec);
        Table.setItems(data);
    }

    @FXML
    private void AddRec(ActionEvent event) {
        
         Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("ReclamationAjout.fxml"));
            Stage myWindow =  (Stage) Table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Ajouter Reclamation");
            myWindow.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void Update(ActionEvent event) {
       
        try {
                       ModifierReclamationController.idrec = Table.getSelectionModel().getSelectedItem().getId();
              
          Parent root ;
          root = FXMLLoader.load(getClass().getResource("ModifierReclamation.fxml"));
            Stage myWindow =  (Stage) Table.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Modifier Reclamation");
            myWindow.show();
            
           
        } catch (Exception ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
    }

    @FXML
    private void toViewComptaAdmin(ActionEvent event) throws IOException {
    CurrentSession.wx = (int) recAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) recAP.getBoundsInParent().getHeight();
        Stage appStage=(Stage)buttonVBox.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("FXMLComptabiliteAdmin.fxml"));
        Scene scene=new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }
    
    @FXML
    private void toViewGestEquip(ActionEvent event) throws IOException{
        CurrentSession.wx = (int) recAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) recAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewStock(ActionEvent event) throws IOException{
        CurrentSession.wx = (int) recAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) recAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("PageAcceuil.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }


    @FXML
    private void toViewMark(ActionEvent event) throws IOException{
       /* CurrentSession.wx = (int) recAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) recAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();*/
    }
	
	@FXML
    private void toViewVente(ActionEvent event) throws IOException{CurrentSession.wx = (int) recAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) recAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("vente.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    

    @FXML
    private void logout(ActionEvent event) throws IOException {CurrentSession.wx = (int) recAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) recAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewAvis(ActionEvent event) throws IOException {CurrentSession.wx = (int) recAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) recAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListAvis.fxml"));
      
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    

    
    @FXML
    private void toViewUserAdmin(ActionEvent event) throws IOException {CurrentSession.wx = (int) recAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) recAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLUserAdmin.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();

    }
    
}
