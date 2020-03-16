/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionvente2;

/**
 *
 * @author Djemaiel
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entite.CarteFidelite;
import service.serviceCarteFid;
import entite.Commande;
import service.serviceLivraison;
import entite.livraison;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.collections.ObservableList;
import service.ServiceCommande;
import service.ServicePanier;

/**
 *
 * @author Djemaiel
 */
public class Gestionvente2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   /*    int choix;
        Scanner s=new Scanner(System.in);
        choix=s.nextInt();
        /*
        livraison l= new livraison(25,1,1,"en");
       
*/
        /*
          serviceLivraison srv = new serviceLivraison();
          //srv.ajouterlivraison(l);
         //  srv.ajouterlivraison(L1);*/
        /* Commande c =new Commande();
        c.setDate("01/01/2020");
        c.setId_user(123);
         ServiceCommande sc=new ServiceCommande();
         //sc.modifierCommande(1022,333);
         sc.supprimerCommande(c);*/
         
        // srv.supprimerlivraison(l);
      //   srv.modifierlivraison(26, "chaima");
        //srv.afficherlivraison();
        
        
  /*      switch(choix) {
  case 1:
   livraison L1= new livraison(2,3,3,"en cours de livraison ");
   livraison l= new livraison(1,1,1,"en cours de preparation ");
   serviceLivraison srv = new serviceLivraison();
   srv.ajouterlivraison(l);// code block
   srv.ajouterlivraison(L1);
   srv.modifierlivraison(1, "en cours de livraison ");
   srv.supprimerlivraison(L1);
   srv.afficherlivraison();
    break;
  case 2:
      
   serviceCarteFid cf = new serviceCarteFid();
   CarteFidelite c = new CarteFidelite(1,22,"11/12/202","11/12/2020","gold");
       CarteFidelite c2 = new CarteFidelite(2,22,"11/12/202","11/12/2020","argent");
     cf.ajoutercarte(c);
     cf.ajoutercarte(c2);
     cf.modifiercarte(2,555);
     cf.supprimercarte(c); 
     cf.affichercarte();
  case 3:
      Map<Integer,Integer> panier = new HashMap<Integer, Integer>();
        ServicePanier ps = new ServicePanier();
        ps.ajouterProduit(1, 5, panier);
        ps.ajouterProduit(1, 14, panier);
        ps.ajouterProduit(123, 5, panier);
        
        //nahi 3 kaabet mel panier
        ps.supprimerProduit(1, 3, panier);
        //nahi produit mel panier
       ps.supprimerProduit(123, panier.get(123), panier);
        
        
      //  ps.checkout(panier);
        
       System.out.println(ps.totalPanier(panier));
        System.out.println(ps.totalnbpoint(panier));
    

    break;
  case 4 :
     
     /*Commande c = new Commande();
      c.setDate("01/01/2020");
       c.setId_user(123);
         ServiceCommande sc=new ServiceCommande();
         sc.modifierCommande(1022,333);
         sc.supprimerCommande(c);*/
  /* default:  
      */
    // code block
}
        
        

// TODO code application logic here
    }
    
    

