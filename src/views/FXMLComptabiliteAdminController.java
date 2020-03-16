/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Client;
import entite.Facture;
import entite.Facture.etat;
import entite.Facture.typef;
import entite.Transaction;
import service.GestionClient;
import service.GestionEmploye;
import service.GestionFacture;
import service.GestionTransaction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.sql.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.CurrentSession;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class FXMLComptabiliteAdminController implements Initializable {

    @FXML
    private ComboBox facturetypeCB;
    @FXML
    private ComboBox etatFactureCB;

    @FXML
    private TextField factureTF;
    @FXML
    private Label facturetLable;
    @FXML
    private Spinner<Integer> idSupplier;
    @FXML
    private CheckBox createTCheck;
    @FXML
    private TextArea descriptionTA;
    @FXML
    private Label errorLable;
    @FXML
    private TextField montonF;
    @FXML
    private ComboBox modeP;
    @FXML
    private ComboBox everyCB;
    @FXML
    private Label everyLabel;
    @FXML
    private Label froLabel;
    @FXML
    private Spinner<Integer> forSpinner;
    @FXML
    private Slider percentSlider;
    @FXML
    private Label increaseLabel;
    @FXML
    private Label percLabel;
    @FXML
    private Label displayper;
    @FXML
    private Label modepl;
    @FXML
    private Label descriptionL;
    @FXML
    private TableView<Facture> table_facture;

    private GestionFacture gf;
    private GestionTransaction gt;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> dateFacturation;
    @FXML
    private TableColumn<?, ?> montant;
    @FXML
    private TableColumn<?, ?> clientLogin;
    @FXML
    private TableColumn<?, ?> supplierId;
    @FXML
    private TableColumn<?, ?> EmployeLogin;
    @FXML
    private TableColumn<?, ?> typeFacture;
    @FXML
    private TableColumn<?, ?> etatFacture;

    @FXML
    private TableView<Transaction> table_transaction;
    @FXML
    private TableColumn<?, ?> etatTransaction;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> monton;
    @FXML
    private TableColumn<?, ?> idFacture;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> idt;
    private Button btnusr;
    @FXML
    private ComboBox facturetypeCB1;
    @FXML
    private ComboBox etatFactureCB1;
    @FXML
    private Label facturetLable1;
    @FXML
    private TextField factureTF1;
    @FXML
    private TextField montonF1;

    @FXML
    private DatePicker datemod;
    @FXML
    private Label fidE;

    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFSheet sheett;
    @FXML
    private LineChart<Date, BigDecimal> line1;
    @FXML
    private BarChart<String, Double> bar1;
    @FXML
    private NumberAxis money1;
    @FXML
    private CategoryAxis time1;
    @FXML
    private NumberAxis bary1;
    @FXML
    private CategoryAxis barx1;

    XYChart.Series dataSeries1;
    XYChart.Series dataSeries2;
    XYChart.Series dataSeries3;
    XYChart.Series dataSeriesBarIn;
    XYChart.Series dataSeriesBarOut;
    @FXML
    private BarChart<String, Double> bar11;
    @FXML
    private NumberAxis bary11;
    @FXML
    private CategoryAxis barx11;
    @FXML
    private Label montanFinale;
    @FXML
    private ImageView logo;
    @FXML
    private Button btncomptabilite;
    @FXML
    private AnchorPane compAP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        compAP.setPrefSize(CurrentSession.wx, CurrentSession.wy);
        facturetypeCB.getItems().addAll("vente produit", "achat produit", "vente materiel", "achat materiel", "taxe", "salaire", "other in", "other out");
        facturetypeCB.getSelectionModel().select("vente produit");
        etatFactureCB.getItems().addAll("Payed", "Not Payed");
        etatFactureCB.getSelectionModel().select("Not Payed");
        facturetypeCB1.getItems().addAll("vente produit", "achat produit", "vente materiel", "achat materiel", "taxe", "salaire", "other in", "other out");
        etatFactureCB1.getItems().addAll("Payed", "Not Payed");
        modeP.getItems().addAll("1 payment", "many payments");
        modeP.getSelectionModel().select("1 payment");
        everyCB.getItems().addAll("Month", "Year");
        everyCB.getSelectionModel().select("Month");
        SpinnerValueFactory vfs = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1, 1);
        idSupplier.setValueFactory(vfs);
        SpinnerValueFactory forsvf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1, 1);
        forSpinner.setValueFactory(forsvf);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateFacturation.setCellValueFactory(new PropertyValueFactory<>("dateFacturation"));
        etatFacture.setCellValueFactory(new PropertyValueFactory<>("etatFacture"));
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        clientLogin.setCellValueFactory(new PropertyValueFactory<>("clientLogin"));
        supplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        EmployeLogin.setCellValueFactory(new PropertyValueFactory<>("EmployeLogin"));
        typeFacture.setCellValueFactory(new PropertyValueFactory<>("typeFacture"));
        gf = new GestionFacture();
        gt = new GestionTransaction();
        gf.updateFactureList();
        gt.updateTransactionList();
        table_facture.setItems(gf.Oblist);
        idt.setCellValueFactory(new PropertyValueFactory<>("id"));
        etatTransaction.setCellValueFactory(new PropertyValueFactory<>("etatTransaction"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        monton.setCellValueFactory(new PropertyValueFactory<>("monton"));
        idFacture.setCellValueFactory(new PropertyValueFactory<>("idFacture"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table_transaction.setItems(gt.Oblist);
        workbook = new XSSFWorkbook();

        try {
            sheet = workbook.createSheet("Facture Data");
        } catch (java.lang.IllegalArgumentException e) {
            System.out.println("Views.FXMLComptabiliteAdminController.excelGeneratetr() " + e.getMessage());
        }
        try {
            sheett = workbook.createSheet("Transaction Data");
        } catch (java.lang.IllegalArgumentException e) {
            System.out.println("Views.FXMLComptabiliteAdminController.excelGeneratetr() " + e.getMessage());
        }
        dataSeries1 = gt.getIncomeChart();
        dataSeries2 = gt.getExpenceChart();
        dataSeries3 = gt.getProfitChart();
        dataSeries1.setName("Income");
        dataSeries2.setName("Expense");
        dataSeries3.setName("Net Profit");
        dataSeriesBarIn = gt.getBarIncomeStats();
        dataSeriesBarOut = gt.getBarExpenseStats();
        dataSeriesBarIn.setName("Income");
        dataSeriesBarOut.setName("Expense");
        bar1.getData().add(dataSeriesBarIn);
        bar11.getData().add(dataSeriesBarOut);

        ObservableList<XYChart.Series<Date, BigDecimal>> olxy = FXCollections.observableArrayList();
        //line1.getData().add(dataSeries1);
        olxy.add(dataSeries1);
        olxy.add(dataSeries2);
        olxy.add(dataSeries3);
        line1.setData(olxy);

        montanFinale.setText("Montan Finale: 0");

    }

    @FXML
    private void changeType() {
        String selectedItem = facturetypeCB.getSelectionModel().getSelectedItem().toString();
        switch (StringtoTypef(selectedItem)) {
            case salaire:
                facturetLable.setText("Login Employe");
                if (!factureTF.isVisible()) {
                    factureTF.setVisible(true);
                }
                if (idSupplier.isVisible()) {
                    idSupplier.setVisible(false);
                }
                break;
            case vente_materiel:
            case vente_produit:
                facturetLable.setText("Login Client");
                if (!factureTF.isVisible()) {
                    factureTF.setVisible(true);
                }
                if (idSupplier.isVisible()) {
                    idSupplier.setVisible(false);
                }
                break;
            case achat_materiel:
            case achat_produit:
                facturetLable.setText("ID Fournisseur");
                if (factureTF.isVisible()) {
                    factureTF.setVisible(false);
                }
                if (!idSupplier.isVisible()) {
                    idSupplier.setVisible(true);
                }
                break;
            default:
                facturetLable.setText("");
                if (factureTF.isVisible()) {
                    factureTF.setVisible(false);
                }
                if (idSupplier.isVisible()) {
                    idSupplier.setVisible(false);
                }
                break;
        }
    }

    private typef StringtoTypef(String ch) {
        switch (ch) {
            case "vente produit":
                return Facture.typef.vente_produit;

            case "achat produit":
                return Facture.typef.achat_produit;

            case "vente materiel":
                return Facture.typef.vente_materiel;

            case "achat materiel":
                return Facture.typef.achat_materiel;

            case "taxe":
                return Facture.typef.taxe;

            case "salaire":
                return Facture.typef.salaire;

            case "other in":
                return Facture.typef.in;

            case "other out":
            default:
                return Facture.typef.out;
        }

    }

    @FXML
    private void createFacture() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "added successfully", ButtonType.OK);
        etat ef;
        boolean err = false;
        String selectedItem = facturetypeCB.getSelectionModel().getSelectedItem().toString();
        if (etatFactureCB.getSelectionModel().getSelectedItem().toString().equals("Payed")) {
            ef = etat.payed;
        } else {
            ef = etat.not_payed;
        }
        Facture f;
        BigDecimal bd;
        try {
            bd = new BigDecimal(montonF.getText());
            f = new Facture(ef, bd, factureTF.getText(), factureTF.getText(), idSupplier.getValue(), StringtoTypef(selectedItem));
            if (facturetLable.getText().equals("Login Employe")) {
                if (f.getEmployeLogin().equals("")) {
                    return;
                }
                GestionEmploye ge = new GestionEmploye();
                if (ge.verifyEmploye(f.getEmployeLogin())) {
                    err = false;
                    errorLable.setText("");
                } else {
                    err = true;
                    errorLable.setText("Employe Doesnt Exist");
                }
            } else if (facturetLable.getText().equals("Login Client")) {
                if (f.getClientLogin() == null) {
                    return;
                }
                GestionClient gc = new GestionClient();
                err = false;
                errorLable.setText("");
                if (!gc.verifyClient(f.getClientLogin())) {
                    Client c = new Client(f.getClientLogin(), f.getClientLogin(), f.getClientLogin(), "", "", "");
                    gc.ajouterClient(c);
                }
            } else if (facturetLable.getText().equals("ID Fournisseur")) {
                if (f.getSupplierId() == 0) {
                    return;
                }
                err = false;
                //if supplier doesnt exist add placeholder supplier
            }
            if (f.getMontant().equals(BigDecimal.ZERO)) {
                return;
            }

            if (!err) {
                gf.ajouterFacture(f);
                if (createTCheck.isSelected()) {

                    long x = 31l * 24l * 60l * 60l * 1000l;
                    Date d = f.getDateFacturation();
                    if (modeP.getSelectionModel().getSelectedItem().toString().equals("1 payment") || forSpinner.getValue() == 1) {

                        gt.ajouterTransaction(new Transaction(f.getId(), f.getEtatFacture(), descriptionTA.getText(), f.getDateFacturation(), f.getMontant()));
                    } else {
                        BigDecimal monton = f.getMontant();
                        int n = forSpinner.getValue();
                        BigDecimal percent = new BigDecimal(percentSlider.getValue() / 100 + 1);
                        monton.multiply(percent, MathContext.UNLIMITED);
                        BigDecimal bn = new BigDecimal(n, MathContext.UNLIMITED);
                        if (everyCB.getSelectionModel().getSelectedItem().equals("Year")) {
                            x = 365l * 24l * 60l * 60l * 1000l + 1000l * 3600l * 6l;
                        }
                        f.setMontant(monton);
                        gf.modifierFacture(f.getId(), f);
                        gt.ajouterTransaction(new Transaction(f.getId(), etat.payed, descriptionTA.getText(), d, monton.divide(bn, MathContext.UNLIMITED)));
                        for (int i = 1; i < n; i++) {

                            d.setTime(x + f.getDateFacturation().getTime());

                            gt.ajouterTransaction(new Transaction(f.getId(), etat.not_payed, descriptionTA.getText(), d, monton.divide(bn, MathContext.UNLIMITED)));
                            a.showAndWait();
                          
                        }
                    }

                }
            }
              if (a.getResult().equals(ButtonType.OK)) {
                                a.close();
                            }
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Views.FXMLComptabiliteAdminController.createFacture() " + e.getMessage());

        }
    }

    private void transactionset(boolean b) {
        everyCB.setVisible(b);
        everyLabel.setVisible(b);
        froLabel.setVisible(b);
        forSpinner.setVisible(b);
        percentSlider.setVisible(b);
        increaseLabel.setVisible(b);
        percLabel.setVisible(b);
        displayper.setVisible(b);
        montanFinale.setVisible(b);
    }

    @FXML
    private void descriptionVisible() {
        transactionset(createTCheck.isSelected());
        descriptionTA.setVisible(createTCheck.isSelected());
        descriptionL.setVisible(createTCheck.isSelected());
        modeP.setVisible(createTCheck.isSelected());
        modepl.setVisible(createTCheck.isSelected());
        if (!createTCheck.isSelected()) {
            transactionset(false);
        } else {
            modeChange();
        }
    }

    @FXML
    private void modeChange() {
        transactionset(!modeP.getSelectionModel().getSelectedItem().toString().equals("1 payment"));
    }

    private int percentvalue() {
        int x = (int) (percentSlider.getValue() / 5);
        x *= 5;
        if (percentSlider.getValue() > 99) {
            x = 100;
        } else if (percentSlider.getValue() < 1) {
            x = 0;
        }
        return x;
    }

    @FXML
    private void displayChange() {
        int x = percentvalue();
        displayper.setText(x + "");
        try {
            float xy = x;
            float y = (float) Math.round(Float.parseFloat(montonF.getText()) * (1 + xy / 100) * 1000) / 1000;
            montanFinale.setText("Montan Finale: " + y);
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Views.FXMLComptabiliteAdminController.displayChange() " + e.getMessage());
        }
    }

    @FXML
    private void deletefact(ActionEvent event) {
        if(table_facture.getSelectionModel().getSelectedItem()!=null){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "are you sure you want to delete", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            gf.supprimerFacture(table_facture.getSelectionModel().getSelectedItem().getId());
            gf.updateFactureList();
            a.close();
        } else {
            a.close();
        }
        }
    }

    @FXML
    private void deletetran(ActionEvent event) {
        if(table_transaction.getSelectionModel().getSelectedItem()!=null){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "are you sure you want to delete", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            gt.supprimerTransaction(table_transaction.getSelectionModel().getSelectedItem().getId());
            gt.updateTransactionList();
            a.close();
        } else {
            a.close();
        }
        }
    }

    @FXML
    private void factureSelected() {
        try{
        Facture f = table_facture.getSelectionModel().getSelectedItem();
        switch (f.getTypeFacture()) {
            case vente_produit:
                facturetypeCB1.getSelectionModel().select("vente produit");
                factureTF1.setText(f.getClientLogin());
                break;
            case achat_produit:
                facturetypeCB1.getSelectionModel().select("achat produit");
                factureTF1.setText(f.getSupplierId() + "");
                break;
            case vente_materiel:
                facturetypeCB1.getSelectionModel().select("vente materiel");
                factureTF1.setText(f.getClientLogin());
                break;
            case achat_materiel:
                facturetypeCB1.getSelectionModel().select("achat materiel");
                factureTF1.setText(f.getSupplierId() + "");
                break;
            case taxe:
                facturetypeCB1.getSelectionModel().select("taxe");
                break;
            case salaire:
                facturetypeCB1.getSelectionModel().select("salaire");
                factureTF1.setText(f.getEmployeLogin());
                break;
            case in:
                facturetypeCB1.getSelectionModel().select("other in");
                break;
            case out:
            default:
                facturetypeCB1.getSelectionModel().select("other out");
                break;
        }
        if (f.getEtatFacture() == etat.not_payed) {
            etatFactureCB1.getSelectionModel().select("Not Payed");
        } else {
            etatFactureCB1.getSelectionModel().select("Payed");
        }
        montonF1.setText(f.getMontant().toString());

        datemod.setValue(f.getDateFacturation().toLocalDate());
        }catch(Exception e){}

    }

    @FXML
    private void modFacture() {
        if(table_transaction.getSelectionModel().getSelectedItem()!=null){
        String selectedItem = facturetypeCB1.getSelectionModel().getSelectedItem().toString();
        etat ef;
        if (etatFactureCB1.getSelectionModel().getSelectedItem().toString().equals("Payed")) {
            ef = etat.payed;
        } else {
            ef = etat.not_payed;
        }
        Facture f;
        BigDecimal bd;
        try {
            bd = new BigDecimal(montonF1.getText());
            int x = 0;
            if (StringtoTypef(selectedItem) == typef.achat_materiel || StringtoTypef(selectedItem) == typef.achat_produit) {
                try {
                    x = Integer.parseInt(factureTF1.getText());
                    f = new Facture(ef, bd, factureTF1.getText(), factureTF1.getText(), x, StringtoTypef(selectedItem));
                    Facture f1 = table_facture.getSelectionModel().getSelectedItem();
                    f.setId(f1.getId());
                    f.setDateFacturation(Date.valueOf(datemod.getValue()));
                    gf.modifierFacture(f.getId(), f);
                    fidE.setText("");
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    fidE.setText("Fournisseur ID is a number!!");
                }
            }
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Views.FXMLComptabiliteAdminController.modFacture() " + e.getMessage());

        }
        }

    }

    @FXML
    private void changeType1(ActionEvent event) {
        String selectedItem = facturetypeCB1.getSelectionModel().getSelectedItem().toString();
        switch (StringtoTypef(selectedItem)) {
            case salaire:
                facturetLable1.setText("Login Employe");
                if (!factureTF1.isVisible()) {
                    factureTF1.setVisible(true);
                }

                break;
            case vente_materiel:
            case vente_produit:
                facturetLable1.setText("Login Client");
                if (!factureTF1.isVisible()) {
                    factureTF1.setVisible(true);
                }

                break;
            case achat_materiel:
            case achat_produit:
                facturetLable1.setText("ID Fournisseur");
                if (!factureTF1.isVisible()) {
                    factureTF1.setVisible(true);
                }

                break;
            default:
                facturetLable1.setText("");
                if (factureTF1.isVisible()) {
                    factureTF1.setVisible(false);
                }

                break;
        }
    }

    @FXML
    private void excelGenerate(ActionEvent event) {

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"ID", "dateFacturation", "etatFacture", "montant", "typeFacture", "clientLogin", "EmployeLogin", "supplierId"});
        for (int i = 0; i < gf.Oblist.size(); i++) {
            Facture f = gf.Oblist.get(i);
            data.put(i + 2 + "", new Object[]{f.getId(), f.getDateFacturation().toString(), f.getEtatFacture().toString(), f.getMontant().toString(), f.getTypeFacture().toString(), f.getClientLogin(), f.getEmployeLogin(), f.getSupplierId() + ""});
        }

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("Factures.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Factures.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void excelGeneratetr(ActionEvent event) {

        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"ID", "description", "monton", "date", "etatTransaction", "idFacture"});
        for (int i = 0; i < gt.Oblist.size(); i++) {
            Transaction t = gt.Oblist.get(i);
            data.put(i + 2 + "", new Object[]{t.getId(), t.getDescription(), t.getMonton().toString(), t.getDate().toString(), t.getEtatTransaction().toString(), t.getIdFacture()});
        }

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheett.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("Factures.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Factures.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayChange1(KeyEvent event) {
        displayChange();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) compAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) compAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) btncomptabilite.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewUserAdmin(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) compAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) compAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) btncomptabilite.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLUserAdmin.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();

    }

    @FXML
    private void toViewGestEquip(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) compAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) compAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) btncomptabilite.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewStock(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) compAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) compAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) btncomptabilite.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("PageAcceuil.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewMark(ActionEvent event) throws IOException {
        /*CurrentSession.wx = (int) compAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) compAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) btncomptabilite.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();*/
    }

    @FXML
    private void toViewVente(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) compAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) compAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) btncomptabilite.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("vente.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewAvis(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) compAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) compAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) btncomptabilite.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListAvis.fxml"));

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void toViewRec(ActionEvent event) throws IOException {
        CurrentSession.wx = (int) compAP.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) compAP.getBoundsInParent().getHeight();
        Stage appStage = (Stage) btncomptabilite.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListReclamation.fxml"));

        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

}
