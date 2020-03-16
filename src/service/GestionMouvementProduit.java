/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entite.Facture;
import entite.MouvementProduit;
import entite.MouvementProduit.src_dest;
import entite.Produit;
import entite.Transaction;
import java.math.BigDecimal;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;


/**
 *
 * @author ASUS
 */
public class GestionMouvementProduit {

    private Connection con = ConnexionBD.getinstance().getCnx();

    /**
     * ****************AJOUT****************
     * @param M
     */
    public void ajouterMouvement(MouvementProduit M) {
        Statement st;
        
        try {
            st = con.createStatement();
            String req = "insert into MouvementProduit(id_facture,source,destination,quantité,date,id_produit) values(" + M.getId_facture()
                    + ",'" + M.getSource().toString() + "','" + M.getDestination().toString() + "'," + M.getQuantité() + ",'" + M.getDate() + "',"+M.getId_produit()+")";
            st.executeUpdate(req);
            GestionProduit Gp=new GestionProduit();
            Produit p=Gp.rechercherProduit(M.getId_produit());
            if(M.getDestination().equals(src_dest.stock)){

                p.setQuantité_stock(p.getQuantité_stock()+M.getQuantité());
            }
            else if(M.getDestination().equals(src_dest.magasin)){
                p.setQuantité_stock(p.getQuantité_stock()-M.getQuantité());
                p.setQuantité_magasin(p.getQuantité_magasin()+M.getQuantité());
            }
            else if(M.getDestination().equals(src_dest.client)){
                p.setQuantité_magasin(p.getQuantité_magasin()-M.getQuantité());
            }
            Gp.modifierProduit(p);
            PreparedStatement pt1=null;
            pt1=con.prepareStatement("select nom_produit,quantité_stock,quantité_magasin from produit where ref_produit="+M.getId_produit());
            ResultSet rs=pt1.executeQuery();
            rs.next();
            if(M.getSource().equals(src_dest.magasin)||M.getSource().equals(src_dest.stock)){
            if(rs.getInt(2)<100){
            AgoraEmail.sendMail("nadim.benneji@esprit.tn","la quantité de stock de produit "+rs.getString(1)+" est inferieur au seuil","alert quantité stock");
            }if(rs.getInt(3)<100){
            AgoraEmail.sendMail("nadim.benneji@esprit.tn","la quantité de magasin de produit "+rs.getString(1)+" est inferieur au seuil","alert quantité magasin");
            }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionMouvementProduit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestionMouvementProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * ******************AFFICHAGE******************
     */
    public ResultSet afficherListeMouvement() {
        PreparedStatement pt;
        ResultSet ts=null;
        try {
            pt = con.prepareStatement("select * from MouvementProduit");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Mouvement [id_mouvement : " + rs.getInt(1) +",id_produit : "+rs.getInt(7)+" , id_facture :" + rs.getString(2) + " , source : " + rs.getString(3) + " , destination : " + rs.getString(4) + " , quantité: " + rs.getInt(5) + " , Date " + rs.getDate(6) + "]");
            }

            ts=pt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(GestionMouvementProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ts;
    }

    /**
     * ***********SUPPRESSION********************
     */
    public void supprimerMouvement(int id) {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("delete from mouvementProduit where id_mouvement_Produit = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionMouvementProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *************MODIFICATION********************
     */
    public void modifierMouvement(MouvementProduit m) {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("update MouvementProduit set source = ? , destination = ? , quantité = ? , date = ? where id_mouvement_Produit = ?");
            pt.setString(1, m.getSource().toString());
            pt.setString(2, m.getDestination().toString());
            pt.setInt(3, m.getQuantité());
            pt.setDate(4, m.getDate());
            pt.setInt(5, m.getId_mouvement_Produit());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionMouvementProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void creatMvt(Map<Integer,Integer> m,float total,String login){
        for(Map.Entry<Integer, Integer> entry : m.entrySet()){
        MouvementProduit mvt=new MouvementProduit(0, 0, src_dest.magasin, src_dest.client, entry.getValue(),new Date(System.currentTimeMillis()), entry.getKey());
        if(login.equals("")||login==null){
        login="guest";
        }
        Facture f = new Facture(Facture.etat.payed, new BigDecimal(total), login, login, 0, Facture.typef.vente_produit);
        mvt.setId_facture(f.getId());
        ajouterMouvement(mvt);
        GestionFacture gf = new GestionFacture();
        gf.ajouterFacture(f);
        Transaction t = new Transaction(f.getId(), Facture.etat.payed, "Achat Produit", f.getDateFacturation(), f.getMontant());
        GestionTransaction gt = new GestionTransaction();
        gt.ajouterTransaction(t);
        }
    
    }

}
