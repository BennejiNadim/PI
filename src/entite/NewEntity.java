/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author Djemaiel
 */
public class NewEntity {
    private User user;
    private CarteFidelite cartefidelite;

    public NewEntity() {
    }

    public NewEntity(User user, CarteFidelite cartefidelite) {
        this.user = user;
        this.cartefidelite = cartefidelite;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarteFidelite getCartefidelite() {
        return cartefidelite;
    }

    public void setCartefidelite(CarteFidelite cartefidelite) {
        this.cartefidelite = cartefidelite;
    }

    @Override
    public String toString() {
        return "NewEntity{" + "user=" + user + ", cartefidelite=" + cartefidelite.toString() + '}';
    }
    
}
