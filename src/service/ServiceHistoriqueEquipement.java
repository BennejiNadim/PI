/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Equipement;
import entite.Facture;
import entite.HistoriqueEquipement;
import entite.Transaction;
import java.math.BigDecimal;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author USER
 */
public class ServiceHistoriqueEquipement {

    private Connection con = ConnexionBD.getinstance().getCnx();

    public void ajouterHistoriqueEquipemnt(HistoriqueEquipement h) {

        try {
            if (h.getPrix() != 0) {
                Facture.typef tf;
                switch (h.getOperation()) {
                    case achat :tf=Facture.typef.achat_materiel;
                        break;
                    case vente :tf=Facture.typef.vente_materiel;
                          break;
                    default :tf=Facture.typef.out;
                           
                }
                Facture f = new Facture(Facture.etat.payed, new BigDecimal(h.getPrix()), "", "", 0, tf);
                Transaction t = new Transaction(f.getId(), Facture.etat.payed, "", f.getDateFacturation(), f.getMontant());
                GestionFacture gf = new GestionFacture();
                GestionTransaction gt = new GestionTransaction();
                gf.ajouterFacture(f);
                gt.ajouterTransaction(t);
                h.setId_f(f.getId());
            }

            Statement st = con.createStatement();
            String req = "insert into `historiqueequipements` values(" + h.getId() + ",'" + h.getDate() + "','" + h.getOperation() + "','" + h.getEq() + "','" + h.getQte() + "','" + h.getId_m() + "','" + h.getId_v() + "','" + h.getPrix()+  "','" + h.getId_f()+ "')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//    public void ajouterHistoriqueEquipemntv(HistoriqueEquipement h) {
//        try {
//            Statement st = con.createStatement();
//            String req = "insert into `historiqueequipements` values(" + h.getId() + ",'" + h.getDate() + "','" + h.getOperation() + "','" + h.getEq() + "','" + h.getQte() + "','" + h.getId_m() + "','" + h.getId_v() + "')";
//            String req1 = "insert into 'historiqueequipements`(`id`, `date`,`operation`, `equipement`,'qte', `id_m`, `id_v`) VALUES (?,?,?,?,?,?,?)";
//
//            PreparedStatement ps = con.prepareStatement(req1);
//            ps.setInt(1, h.getId());
//            ps.setString(2, h.getDate().toString());
//            ps.setString(3, h.getOperation().toString());
//            ps.setString(4, h.getEq().toString());
//
//            ps.setInt(5, h.getQte());
//            ps.setInt(6, NULL);
//            ps.setInt(7, h.getId_v());
//
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void ajouterHistoriqueEquipemntm(HistoriqueEquipement h) {
//        try {
//            Statement st = con.createStatement();
//            String req = "insert into `historiqueequipements` values(" + h.getId() + ",'" + h.getDate() + "','" + h.getOperation() + "','" + h.getEq() + "','" + h.getQte() + "','" + h.getId_m() + "','" + h.getId_v() + "')";
//            String req1 = "insert into 'historiqueequipements`(`id`, `date`,`operation`, `equipement`, 'qte', `id_m`) VALUES (?,?,?,?,?,?)";
//
//            PreparedStatement ps = con.prepareStatement(req1);
//            ps.setInt(1, h.getId());
//            ps.setString(2, h.getDate().toString());
//            ps.setString(3, h.getOperation().toString());
//            ps.setString(4, h.getEq().toString());
//
//            ps.setInt(5, h.getQte());
//
//            ps.setInt(6, h.getId_m());
//
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public ObservableList<HistoriqueEquipement> afficherHistorique() {
        ObservableList obh = FXCollections.observableArrayList();
        try {
            PreparedStatement pt = con.prepareStatement("select * from `historiqueequipements`");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                HistoriqueEquipement h = new HistoriqueEquipement();
                h.setId(rs.getInt(1));
                h.setDate(rs.getDate(2));
                h.setOperation(stringToOperation(rs.getString(3)));
                h.setEq(stringToEquipement(rs.getString(4)));
                h.setQte(rs.getInt(5));
                h.setId_m(rs.getInt(6));
                h.setId_v(rs.getInt(7));
                h.setPrix(rs.getFloat(8));
                h.setId_f(rs.getInt(9));
                obh.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHistoriqueEquipement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obh;
    }

    public HistoriqueEquipement.operation etatToOperation(Equipement.etat e) {
        if (e.equals("")) {
            return HistoriqueEquipement.operation.NULL;
        }
        if (e.equals(Equipement.etat.vendu)) {
            return HistoriqueEquipement.operation.vente;
        }
        if (e.equals(Equipement.etat.corbeille)) {
            return HistoriqueEquipement.operation.corbeille;
        }
        if (e.equals(Equipement.etat.en_panne)) {
            return HistoriqueEquipement.operation.panne;
        }
        if (e.equals(Equipement.etat.en_maintenance)) {
            return HistoriqueEquipement.operation.maintenance;
        }

        return HistoriqueEquipement.operation.disponnible;
    }

    public HistoriqueEquipement.operation stringToOperation(String o) {
        if (o.equals("")) {
            return HistoriqueEquipement.operation.NULL;
        }
        if (o.equals("achat")) {
            return HistoriqueEquipement.operation.achat;
        }
        if (o.equals("vente")) {
            return HistoriqueEquipement.operation.vente;
        }
        if (o.equals("corbeille")) {
            return HistoriqueEquipement.operation.corbeille;
        }
        if (o.equals("maintenance")) {
            return HistoriqueEquipement.operation.maintenance;
        }
        if (o.equals("panne")) {
            return HistoriqueEquipement.operation.panne;
        }
        return HistoriqueEquipement.operation.disponnible;
    }

    public HistoriqueEquipement.equipement stringToEquipement(String e) {
        if (e.equals("vehicule")) {
            return HistoriqueEquipement.equipement.vehicule;
        }
        return HistoriqueEquipement.equipement.materiels;
    }

    public List<HistoriqueEquipement> chercherParId_v(int id_v) {
        List ALLvehicule = new ArrayList();
        HistoriqueEquipement h = new HistoriqueEquipement();
        try {
            Statement st = con.createStatement();
            PreparedStatement pt = con.prepareStatement("select * from historiqueequipements where id_v = ? ");
            pt.setInt(1, id_v);
            ResultSet rest = pt.executeQuery();
            while (rest.next()) {
                h.setId(rest.getInt("id"));
                h.setDate(rest.getDate("date"));
                h.setOperation(stringToOperation(rest.getString("operation")));
                h.setEq(stringToEquipement(rest.getString("equipement")));
                h.setQte(rest.getInt("qte"));
                h.setId_m(rest.getInt("id_m"));
                h.setId_v(rest.getInt("id_v"));
                h.setPrix(rest.getFloat("prix"));
                h.setId_f(rest.getInt("id_f"));
                ALLvehicule.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ALLvehicule;
    }

    public List<HistoriqueEquipement> chercherParId_m(int id_m) {
        List ALLmateriels = new ArrayList();
        HistoriqueEquipement h = new HistoriqueEquipement();
        try {
            Statement st = con.createStatement();
            PreparedStatement pt = con.prepareStatement("select * from historiqueequipements where id_m = ? ");
            pt.setInt(1, id_m);
            ResultSet rest = pt.executeQuery();
            while (rest.next()) {
                h.setId(rest.getInt("id"));
                h.setDate(rest.getDate("date"));
                h.setOperation(stringToOperation(rest.getString("operation")));
                h.setEq(stringToEquipement(rest.getString("equipement")));
                h.setQte(rest.getInt("qte"));
                h.setId_m(rest.getInt("id_m"));
                h.setId_v(rest.getInt("id_v"));
                 h.setId_f(rest.getInt("id_f"));
                ALLmateriels.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ALLmateriels;
    }

    public List<HistoriqueEquipement> chercherParId_mOperation(int id_m, HistoriqueEquipement.operation o) {
        List ALLmateriels = new ArrayList();
        HistoriqueEquipement h = new HistoriqueEquipement();
        try {
            Statement st = con.createStatement();
            PreparedStatement pt = con.prepareStatement("select * from historiqueequipements where id_m = ? and operation = ?");
            pt.setInt(1, id_m);
            pt.setString(2, o.toString());
            ResultSet rest = pt.executeQuery();
            while (rest.next()) {
                h.setId(rest.getInt("id"));
                h.setDate(rest.getDate("date"));
                h.setOperation(stringToOperation(rest.getString("operation")));
                h.setEq(stringToEquipement(rest.getString("equipement")));
                h.setQte(rest.getInt("qte"));
                h.setId_m(rest.getInt("id_m"));
                h.setId_v(rest.getInt("id_v"));
                 h.setPrix(rest.getInt("prix"));
                  h.setId_f(rest.getInt("id_f"));
                ALLmateriels.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ALLmateriels;
    }

    public HistoriqueEquipement chercherParId(int id) {
        HistoriqueEquipement h = new HistoriqueEquipement();
        try {

            Statement st = con.createStatement();
            PreparedStatement pt = con.prepareStatement("select * from historiqueequipements where id = ? ");
            pt.setInt(1, id);
            ResultSet rest = pt.executeQuery();
            while (rest.next()) {
                h.setId(rest.getInt("id"));
                h.setDate(rest.getDate("date"));
                h.setOperation(stringToOperation(rest.getString("operation")));
                h.setEq(stringToEquipement(rest.getString("equipement")));
                h.setQte(rest.getInt("qte"));
                h.setId_m(rest.getInt("id_m"));
                h.setId_v(rest.getInt("id_v"));
                 h.setPrix(rest.getInt("prix"));
                  h.setId_f(rest.getInt("id_f"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceHistoriqueEquipement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return h;
    }

    public void modifierHistorique(int id, HistoriqueEquipement h)  {
        
        try {       
            sendM("majdi.guedri@esprit.tn");
        } catch (MessagingException ex) {
            Logger.getLogger(ServiceHistoriqueEquipement.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        try {
            PreparedStatement pt = con.prepareStatement("update historiqueequipements set date = ?, operation = ?, equipement = ? ,qte = ?, id_m = ? , id_v = ?, prix = ? ,id_f = ? where id=?");
            pt.setString(1, h.getDate().toString());
            pt.setString(2, h.getOperation().toString());
            pt.setString(3, h.getEq().toString());
            pt.setInt(4, h.getQte());
            pt.setInt(5, h.getId_m());
            pt.setInt(6, h.getId_v());
             pt.setFloat(7, h.getPrix());
             pt.setInt(8, h.getId_f());
            pt.setInt(9, id);
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHistoriqueEquipement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerHistorique(int id) {
        try {
            PreparedStatement pt = con.prepareStatement("delete from historiqueequipements where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHistoriqueEquipement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerHM(int id_m) {
        try {
            PreparedStatement pt = con.prepareStatement("delete from historiqueequipements where id_m = ?");
            pt.setInt(1, id_m);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHistoriqueEquipement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerHV(int id_v) {
        try {
            PreparedStatement pt = con.prepareStatement("delete from historiqueequipements where id_v = ?");
            pt.setInt(1, id_v);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceHistoriqueEquipement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendM (String email) throws AddressException, MessagingException {
         String  mesg1=" Changement au niveau HistoriqueEquipement par "+CurrentSession.currentLogin;
          
          
       String from ="agora.agoratn@gmail.com";
       String pass="93410376M";
       String [] to={"majdi.guedri@esprit.tn"};
       String host="mail.javatpoint.com";
       String sub="Modification Historique";
       
        Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",      
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from,pass);
    }
}); 
          //compose message                
           MimeMessage message = new MimeMessage(session);   
            message.addRecipient(Message.RecipientType.TO,new InternetAddress("majdi.guedri@esprit.tn"));
            message.setSubject(sub);    
           message.setText(mesg1);    
           //send message  
           Transport.send(message); 
    }

}
