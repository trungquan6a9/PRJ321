/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.products;

import java.io.Serializable;

/**
 *
 * @author johny
 */
public class ProductsDTO implements Serializable {

    private String proName;

    public ProductsDTO() {
    }

    public ProductsDTO(String proName) {
        this.proName = proName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    

}
