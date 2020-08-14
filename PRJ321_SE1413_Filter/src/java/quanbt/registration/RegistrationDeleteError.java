/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.registration;

import java.io.Serializable;

/**
 *
 * @author johny
 */
public class RegistrationDeleteError implements Serializable{
    
    private String cannotDeleteCurrentUser;

    public String getCannotDeleteCurrentUser() {
        return cannotDeleteCurrentUser;
    }

    public void setCannotDeleteCurrentUser(String cannotDeleteCurrentUser) {
        this.cannotDeleteCurrentUser = cannotDeleteCurrentUser;
    }
    
}
