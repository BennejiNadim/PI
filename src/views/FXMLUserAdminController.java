/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Client;
import entite.Employe;
import service.GestionClient;
import service.GestionEmploye;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.CurrentSession;


/**
 * FXML Controller class
 *
 * @author Wael
 */
public class FXMLUserAdminController implements Initializable {

    
    private Button btncmpt;
    @FXML
    private TableColumn<?, ?> login;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> mdp;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> tel;
    @FXML
    private TableView<Client> table_client;
GestionClient gc;
    @FXML
    private TableView<Employe> table_employe;
    @FXML
    private TableColumn<?, ?> login1;
    @FXML
    private TableColumn<?, ?> nom1;
    @FXML
    private TableColumn<?, ?> prenom1;
    @FXML
    private TableColumn<?, ?> mdp1;
    @FXML
    private TableColumn<?, ?> email1;
    @FXML
    private TableColumn<?, ?> tel1;
    @FXML
    private TableColumn<?, ?> poste;
    @FXML
    private TableColumn<?, ?> salaire;
    
    GestionEmploye ge;
    @FXML
    private TextField logintf;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField prenomtf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField numteltf;
    @FXML
    private TextField postetf;
    @FXML
    private TextField mdptf;
    @FXML
    private TextField salairetf;
    @FXML
    private TextField logintf1;
    @FXML
    private TextField nomtf1;
    @FXML
    private TextField prenomtf1;
    @FXML
    private TextField emailtf1;
    @FXML
    private TextField numteltf1;
    @FXML
    private TextField mdptf1;
    @FXML
    private ImageView logo;
    @FXML
    private Button btncomptabilite;
    
    @FXML
    private Tab employeTab;
    @FXML
    private AnchorPane userTabPane;
    @FXML
    private VBox buttonVBox;
    @FXML
    private AnchorPane userAP;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userAP.setPrefSize(CurrentSession.wx, CurrentSession.wy);
            employeTab.disableProperty().set(!CurrentSession.isAdmin);
            if(!CurrentSession.isAdmin){
        buttonVBox.getChildren().remove(btncomptabilite);
        
        }
        
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        gc = new GestionClient();
        gc.updateClientList();
        table_client.setItems(gc.Clist);
        
        login1.setCellValueFactory(new PropertyValueFactory<>("login"));
        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mdp1.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        email1.setCellValueFactory(new PropertyValueFactory<>("email"));
        tel1.setCellValueFactory(new PropertyValueFactory<>("tel"));
        poste.setCellValueFactory(new PropertyValueFactory<>("poste"));
        salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        ge = new GestionEmploye();
        ge.updateEmployeList();
        table_employe.setItems(ge.Elist);
            

        // TODO
    }    

    

        
    @FXML
    private void deleteem(ActionEvent event) {
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"are you sure you want to delete",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
        ge.supprimerEmploye(table_employe.getSelectionModel().getSelectedItem().getLogin());
        a.close();
        }else{
        a.close();
        }
    }

    @FXML
    private void AjouterE(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"added successfully",ButtonType.OK);
        try{
        BigDecimal  x = new BigDecimal(salairetf.getText());
        Integer.parseInt(numteltf.getText());
        if(numteltf.getText().length()==8){
        Employe e = new Employe(logintf.getText(), nomtf.getText(), prenomtf.getText(), mdptf.getText(), emailtf.getText(), numteltf.getText(), postetf.getText(), x);
        ge.ajouterEmploye(e);
        a.showAndWait();
        if(a.getResult().equals(ButtonType.OK)){a.close();}
        }
         } catch (Exception e) {
            System.out.println("Views.FXMLUserAdminController.AjouterE()"+ e.getMessage());
        } 
    }

    @FXML
    private void modempl(ActionEvent event) {
        if(table_employe.getSelectionModel().getSelectedItem()!=null){
        try{
            if(numteltf.getText().length()==8){
        BigDecimal  x = new BigDecimal(salairetf.getText());
        Employe e = table_employe.getSelectionModel().getSelectedItem();
        String login2 = e.getLogin();
        e.setEmail(emailtf.getText());
        e.setLogin(logintf.getText());
        e.setMdp(mdptf.getText());
        e.setNom(nomtf.getText());
        e.setPoste(postetf.getText());
        e.setPrenom(prenomtf.getText());
        e.setSalaire(x);
        Integer.parseInt(numteltf.getText());
        e.setTel(numteltf.getText());
        ge.modifierEmploye(login2, e);}
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Views.FXMLUserAdminController.modempl()"+ e.getMessage());

        }
        }
    }

    @FXML
    private void selectEm(MouseEvent event) {
        try{
        Employe e = table_employe.getSelectionModel().getSelectedItem();
        logintf.setText(e.getLogin());
        nomtf.setText(e.getNom());
        prenomtf.setText(e.getPrenom());
        mdptf.setText(e.getMdp());
        emailtf.setText(e.getEmail());
        numteltf.setText(e.getTel());
        postetf.setText(e.getPoste());
        salairetf.setText(e.getSalaire()+"");
        }
        catch(java.lang.NullPointerException e){
            cleare();
            System.out.println("Views.FXMLUserAdminController.selectcl() "+e.getMessage());
        }
    }

    @FXML
    private void cleare() {
        logintf.clear();
        nomtf.clear();
        prenomtf.clear();
        mdptf.clear();
        emailtf.clear();
        numteltf.clear();
        postetf.clear();
        salairetf.clear();
        table_employe.getSelectionModel().clearSelection();
    }

    @FXML
    private void deletecl(ActionEvent event) {
            if(table_employe.getSelectionModel().getSelectedItem()!=null){
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"are you sure you want to delete",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
        
            gc.supprimerClient(table_client.getSelectionModel().getSelectedItem().getLogin());
                a.close();
        }else{
        a.close();
        }
    }
    }

    @FXML
    private void Ajoutercl(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION," added successfully",ButtonType.OK);
        try{
        if(numteltf1.getText().length()==8){
            Integer.parseInt(numteltf1.getText());
        Client c = new Client(logintf1.getText(), nomtf1.getText(), prenomtf1.getText(), mdptf1.getText(), emailtf1.getText(), numteltf1.getText());
        gc.ajouterClient(c);
        a.showAndWait();
        if(a.getResult().equals(ButtonType.OK)){a.close();}
        }}
        catch(Exception e){
            System.out.println("views.FXMLUserAdminController.Ajoutercl()");
        }
    }

    @FXML
    private void modcl(ActionEvent event) {
        try{
            if(numteltf1.getText().length()==8){
            Integer.parseInt(numteltf1.getText());
        Client e = table_client.getSelectionModel().getSelectedItem();
        String login2 = e.getLogin();
        e.setEmail(emailtf1.getText());
        e.setLogin(logintf1.getText());
        e.setMdp(mdptf1.getText());
        e.setNom(nomtf1.getText());
        e.setPrenom(prenomtf1.getText());
        e.setTel(numteltf1.getText());
        gc.modifierClient(login2, e);}
        }
        catch(Exception e){
            clearcl();
             System.out.println("Views.FXMLUserAdminController.modcl()"+e.getMessage());
        }
    }

    @FXML
    private void clearcl() {
        logintf1.clear();
        nomtf1.clear();
        prenomtf1.clear();
        mdptf1.clear();
        emailtf1.clear();
        numteltf1.clear();
        table_client.getSelectionModel().clearSelection();
    }

    @FXML
    private void selectcl(MouseEvent event) {
        try{
        Client e = table_client.getSelectionModel().getSelectedItem();
        logintf1.setText(e.getLogin());
        nomtf1.setText(e.getNom());
        prenomtf1.setText(e.getPrenom());
        mdptf1.setText(e.getMdp());
        emailtf1.setText(e.getEmail());
        numteltf1.setText(e.getTel());
        }
        catch(java.lang.NullPointerException e){
            clearcl();
            System.out.println("Views.FXMLUserAdminController.selectcl() "+e.getMessage());
        }
    }
    
    @FXML
    private void toViewComptaAdmin(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) userAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) userAP.getBoundsInParent().getHeight();
        Stage appStage=(Stage)buttonVBox.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("FXMLComptabiliteAdmin.fxml"));
        Scene scene=new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }
    
    @FXML
    private void toViewGestEquip(ActionEvent event) throws IOException{
        CurrentSession.wx = (int) userAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) userAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewStock(ActionEvent event) throws IOException{
        CurrentSession.wx = (int) userAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) userAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("PageAcceuil.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }


    @FXML
    private void toViewMark(ActionEvent event) throws IOException{
       /*CurrentSession.wx = (int) userAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) userAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();*/
    }
	
	@FXML
    private void toViewVente(ActionEvent event) throws IOException{
        CurrentSession.wx = (int) userAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) userAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("vente.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    

    @FXML
    private void logout(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) userAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) userAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
       
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewAvis(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) userAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) userAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListAvis.fxml"));
        
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewRec(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) userAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) userAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) buttonVBox.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));
        
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }
    
}
