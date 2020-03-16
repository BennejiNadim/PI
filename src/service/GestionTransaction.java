/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Facture;
import entite.Transaction;

import util.ConnexionBD;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Wael
 */
public class GestionTransaction {

    private final Connection cnx;
    public ObservableList<Transaction> Oblist = FXCollections.observableArrayList();

    public GestionTransaction() {
        this.cnx = ConnexionBD.getinstance().getCnx();

    }

    public void ajouterTransaction(Transaction t) {
        Statement st;
        try {
            st = cnx.createStatement();

            String req = "insert into Transaction values(" + t.getId() + ",'" + t.getDate() + "','" + t.getMonton() + "','" + t.getEtatTransaction() + "','" + t.getDescription() + "','" + t.getIdFacture() + "')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(GestionTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        updateTransactionList();

    }

    public void afficherTransaction() {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from Transaction");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Transaction [ id: " + rs.getInt(1) + " date : " + rs.getDate(2) + " Monton: " + rs.getDouble(3) + " Etat: " + rs.getString(4) + " Description: " + rs.getString(5) + " Id Facture : " + rs.getInt(6) + "]");

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierTransaction(int id, Transaction t) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("update Transaction set etatTransaction = ?, date = ?, montant = ?, description = ?   where id = ?");
            pt.setString(1, t.getEtatTransaction().toString());
            pt.setDate(2, t.getDate());

            pt.setBigDecimal(3, t.getMonton());
            pt.setInt(5, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerTransaction(int id) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("delete from Transaction where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTransactionList() {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from Transaction");
            ResultSet rs = pt.executeQuery();
            Oblist.clear();
            while (rs.next()) {

                Facture.etat et;

                if (rs.getString(4).equals("payed")) {
                    et = Facture.etat.payed;
                } else {
                    et = Facture.etat.not_payed;
                }

                Transaction t = new Transaction(rs.getInt(6), et, rs.getString(5), rs.getDate(2), rs.getBigDecimal(3));
                t.setId(rs.getInt(1));
                Oblist.add(t);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public XYChart.Series getIncomeChart() {
        XYChart.Series dataSeries1 = new XYChart.Series();
        try {
            PreparedStatement pt = cnx.prepareStatement("select date,Sum(monton) from Transaction,facture where Transaction.idFacture=facture.id and etatTransaction='payed' and typefacture IN ('vente_produit','vente_materiel','in') and date > (NOW() - INTERVAL 1 MONTH) GROUP by date");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data(rs.getDate(1).toString(), rs.getBigDecimal(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataSeries1;
    }

    public XYChart.Series getExpenceChart() {
        XYChart.Series dataSeries1 = new XYChart.Series();
        try {
            PreparedStatement pt = cnx.prepareStatement("select date,ifnull(Sum(monton),0) from Transaction,facture where Transaction.idFacture=facture.id and etatTransaction='payed' and typefacture IN ('achat_produit','achat_materiel','taxe','salaire','out') and date > (NOW() - INTERVAL 1 MONTH) GROUP by date");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data(rs.getDate(1).toString(), rs.getBigDecimal(2).multiply(BigDecimal.valueOf(-1), MathContext.UNLIMITED)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataSeries1;
    }

    public XYChart.Series getProfitChart() {
        XYChart.Series dataSeries1 = new XYChart.Series();
        try {
            PreparedStatement pt = cnx.prepareStatement("SELECT date as 'dateone', ifnull((select Sum(monton) from Transaction,facture where Transaction.idFacture=facture.id and etatTransaction='payed' and typefacture IN ('vente_produit','vente_materiel','in') and date > (NOW() - INTERVAL 1 MONTH) and date = dateone GROUP by date),0) - ifnull((select Sum(monton) from Transaction,facture where Transaction.idFacture=facture.id and etatTransaction='payed' and typefacture IN ('achat_produit','achat_materiel','taxe','salaire','out') and date > (NOW() - INTERVAL 1 MONTH) and date = dateone GROUP by date),0)  FROM `transaction`");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data(rs.getDate(1).toString(), rs.getBigDecimal(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataSeries1;
    }

    public XYChart.Series getBarIncomeStats() {
        XYChart.Series dataSeries1 = new XYChart.Series();
        try {

            PreparedStatement pt = cnx.prepareStatement("SELECT SUM(monton) FROM `transaction`,facture WHERE idFacture=facture.id and facture.typeFacture='vente_produit' and date > (NOW() - INTERVAL 1 MONTH)");
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data("Vente Produit", rs.getDouble(1)));
            }

            pt = cnx.prepareStatement("SELECT SUM(monton) FROM `transaction`,facture WHERE idFacture=facture.id and facture.typeFacture='vente_materiel' and date > (NOW() - INTERVAL 1 MONTH)");
            rs = pt.executeQuery();
            if (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data("Vente Materiel", rs.getDouble(1)));
            }

            pt = cnx.prepareStatement("SELECT SUM(monton) FROM `transaction`,facture WHERE idFacture=facture.id and facture.typeFacture='in' and date > (NOW() - INTERVAL 1 MONTH)");
            rs = pt.executeQuery();
            if (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data("Other", rs.getDouble(1)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataSeries1;
    }

    public XYChart.Series getBarExpenseStats() {
        XYChart.Series dataSeries1 = new XYChart.Series();
        try {
            PreparedStatement pt = cnx.prepareStatement("SELECT SUM(monton) FROM `transaction`,facture WHERE idFacture=facture.id and facture.typeFacture='taxe' and date > (NOW() - INTERVAL 1 MONTH)");
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data("Taxe", rs.getDouble(1)));
            }

            pt = cnx.prepareStatement("SELECT SUM(monton) FROM `transaction`,facture WHERE idFacture=facture.id and facture.typeFacture='achat_produit' and date > (NOW() - INTERVAL 1 MONTH)");
            rs = pt.executeQuery();
            if (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data("Achat Produit", rs.getDouble(1)));
            }

            pt = cnx.prepareStatement("SELECT SUM(monton) FROM `transaction`,facture WHERE idFacture=facture.id and facture.typeFacture='achat_materiel' and date > (NOW() - INTERVAL 1 MONTH)");
            rs = pt.executeQuery();
            if (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data("Achat Materiel", rs.getDouble(1)));
            }
            pt = cnx.prepareStatement("SELECT SUM(monton) FROM `transaction`,facture WHERE idFacture=facture.id and facture.typeFacture='salaire' and date > (NOW() - INTERVAL 1 MONTH)");
            rs = pt.executeQuery();
            if (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data("Salaire", rs.getDouble(1)));
            }

            pt = cnx.prepareStatement("SELECT SUM(monton) FROM `transaction`,facture WHERE idFacture=facture.id and facture.typeFacture='out' and date > (NOW() - INTERVAL 1 MONTH)");
            rs = pt.executeQuery();
            if (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data("Other", rs.getDouble(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataSeries1;
    }
}
