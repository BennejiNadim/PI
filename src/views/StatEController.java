/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import entite.Materiels;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.ResolverStyle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.ServiceMateriels;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatEController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private Button affstat;
    @FXML
    private BarChart<String, Integer> BarChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
ServiceMateriels sm = new ServiceMateriels();
    @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }

    @FXML
    private void affstat(ActionEvent event) {
        int q1=0;
        int q2=0;
        int q3=0;
        int q4=0;
        int q5=0;
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
 
         ObservableList <Materiels> list = sm.afficherMateriels();
         for (Materiels m1 : list) {
             if (m1.getEtat().toString().equals("disponnible"))
                 q1+=m1.getQte();
                  if (m1.getEtat().toString().equals("en_panne"))
                      q2+=m1.getQte();
                       if (m1.getEtat().toString().equals("vendu"))
                           q3+=m1.getQte();
                            if (m1.getEtat().toString().equals("corbeille"))
                                q4+=m1.getQte();
                                 if (m1.getEtat().toString().equals("en_maintenance"))
                                  q5+=m1.getQte();
         }
            series.getData().add(new XYChart.Data<>("disponnible" , q1));
            series.getData().add(new XYChart.Data<>("en_panne" , q2));
            series.getData().add(new XYChart.Data<>("vendu" , q3));
            series.getData().add(new XYChart.Data<>("corbeille" , q4));
            series.getData().add(new XYChart.Data<>("en_maintenance" , q5));
            BarChart.getData().add(series);
            
       
         
    }
    
}
