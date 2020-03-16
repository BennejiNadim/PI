/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.math.BigDecimal;

/**
 *
 * @author Wael
 */
public class Employe extends Client {
    private String poste;
    private BigDecimal salaire;

    public Employe(String login, String nom, String prenom, String mdp, String email, String tel,String poste, BigDecimal salaire) {
        super(login, nom, prenom, mdp, email, tel);
        this.poste = poste;
        this.salaire = salaire;
    }

    public Employe() {
    }

    public String getPoste() {
        return poste;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }
    
    
}
