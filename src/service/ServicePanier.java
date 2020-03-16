/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Commande;
import entite.LigneCommmande;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import entite.CarteFidelite;
import entite.livraison;
import java.awt.AWTException;
import service.serviceCarteFid;
import service.TrayIconDemo;

/**
 *
 * @author Djemaiel
 */
public class ServicePanier {

    public void ajouterProduit(int idproduit, int qte, Map<Integer, Integer> panier) {
        if (panier.containsKey(idproduit)) {
            panier.put(idproduit, panier.get(idproduit) + qte);
        } else {
            panier.put(idproduit, qte);
        }
    }

    public void supprimerProduit(int produit, int qte, Map<Integer, Integer> panier) {
        if (panier.containsKey(produit)) {
            if (panier.get(produit) > qte) {
                panier.put(produit, panier.get(produit) - qte);
            } else {
                panier.remove(produit);
            }
        }
    }

    public void checkout(Map<Integer,Integer> panier, float total, boolean livraison,String login) throws AWTException {

        livraison l = new livraison();
        serviceLivraison sl = new serviceLivraison();
        Commande c = new Commande();
        ServiceCommande sc = new ServiceCommande();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.setDate(sdf.format(cal.getTime()));
        if(login.equals("")||login==null){
        login="guest";
        }
        c.setId_user(login);
        c.setTotal(total);
        sc.ajouterComande(c);
        int idc = sc.derniereCommande();
        if (livraison) {
            l.setEtat("en traitement");
            l.setId_commande(idc);
            l.setTotal(total);
            l.setTrucking(sl.getAlphaNumericString());
            sl.ajouterlivraison(l);
        }
        ServiceLigneCommande slc = new ServiceLigneCommande();

        for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {

            LigneCommmande lc = new LigneCommmande();
            lc.setId_commande(idc);
            lc.setId_produit(entry.getKey());
            lc.setQuantite(entry.getValue());
            slc.ajouterLigneComande(lc);
            GestionMouvementProduit gm=new GestionMouvementProduit();
            gm.creatMvt(panier, total, login);

            //System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }

        TrayIconDemo T = new TrayIconDemo();
        T.displayTray();
    }

    public int totalPanier(Map<Integer, Integer> panier) {
        System.out.println(panier.size());
        ServiceProduit sp = new ServiceProduit();
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {

            total += sp.prixProduit(entry.getKey()) * entry.getValue();
            System.out.println("la Quantite :" + entry.getValue());
            System.out.println("le Prix :" + sp.prixProduit(entry.getKey()));
        }
        return total;
    }

    public int totalnbpoint(Map<Integer, Integer> panier) {
        CarteFidelite n = new CarteFidelite();
        serviceCarteFid cn = new serviceCarteFid();

        int total1 = 0;
        for (Map.Entry<Integer, Integer> entry : panier.entrySet()) {
            total1 += entry.getValue();

            cn.modifiercarte(2, total1);
        }
        return total1;
    }

}
