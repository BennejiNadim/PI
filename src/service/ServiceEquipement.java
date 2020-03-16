/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Equipement;

/**
 *
 * @author USER
 */
public class ServiceEquipement {
    public Equipement.etat stringToEtat (String e) {      
      if (e.equals("")) {
          return Equipement.etat.NULL;
      }
      if (e.equals("disponnible")) {
         return Equipement.etat.disponnible;
      }
      if (e.equals("en_panne")) {
          return Equipement.etat.en_panne;
      }
      if (e.equals( "vendu")) {
          return Equipement.etat.vendu;
      }
      if (e.equals("corbeille")) {
          return Equipement.etat.corbeille;
      }
      return 
              Equipement.etat.en_maintenance;
      
  }
       public boolean testTextInput(String a) {

        boolean b = true;
        try{
        if (a.length() == 0 || testNumberInput(a)) {
            b = false;
        }}catch(java.lang.NullPointerException e){
            System.out.println("service.ServiceEquipement.testTextInput()");
            return false;
        }

        return b;

    }

    public boolean testNumberInput(String a) {
        boolean b = false;
        if (a.matches("^[0-9]*")) {
            b = true;
        }
        return b;
    }
    public boolean testFloatInput (String a) {
        try
{
   Float i = Float.parseFloat(a);
   return true;
}
catch ( Exception e )
{
  return false;
}
    }
}
