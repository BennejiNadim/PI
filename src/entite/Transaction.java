/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import entite.Facture.etat;
import util.ConnexionBD;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wael
 */
public class Transaction {
    
    private static int idc=1;
    private int id;
    private int idFacture;
    private etat etatTransaction;
    private Date date;
    private BigDecimal monton;
    private String description;

    public Transaction() {
        
       Connection cnx = ConnexionBD.getinstance().getCnx();
        if (idc==1) {
            PreparedStatement pt;
            try {
                pt = cnx.prepareStatement("select MAX(id) from transaction");
                ResultSet rs = pt.executeQuery();
                if (rs.next()) {
                    
                    idc=5+rs.getInt(1);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        this.id=idc++;
        this.etatTransaction=etat.not_payed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction(int idFacture,etat etatTransaction, String description, Date date, BigDecimal monton) {
        this();
        this.etatTransaction=etatTransaction;
        this.idFacture = idFacture;
        this.description = description;
        this.date = date;
        this.monton = monton;
    }

    public etat getEtatTransaction() {
        return etatTransaction;
    }
    
    public void setPayed() {
        this.etatTransaction = etat.payed;
    }

    public void setNotPayed() {
        this.etatTransaction = etat.not_payed;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public static int getIdc() {
        return idc;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    

    public int getId() {
        return id;
    }



    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getMonton() {
        return monton;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMonton(BigDecimal monton) {
        this.monton = monton;
    }

}
