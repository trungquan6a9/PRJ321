/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author johny
 */
public class CartObject implements Serializable {
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String title){
        //1. check existed items (Ngăn chứa)
        if(this.items == null){
            this.items = new HashMap<>();
        }//end if items is not existed
        
        //2. check existed item (Đồ)
        int quantity = 1;
        if(this.items.containsKey(title)){
            quantity = this.items.get(title) + 1;
        }
        //3. check quantity of item
        this.items.put(title, quantity);
    }
    
    public void removeItemFromCart(String title) {
        if (this.items == null) {
            return;
        }

        if (this.items.containsKey(title)) {
            this.items.remove(title);
            if (this.items.isEmpty()) {
                this.items.clear();
            }
        }
    }
    
    
//    <Resource name="ASSASSIN" type="javax.sql.DataSource" auth="container"
//              driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"
//              url="jdbc:sqlserver://localhost:1433;databaseName=PRJ321SE1413"
//              username="sa" password="123456" 
//    />
}
