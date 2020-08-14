/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.products;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import quanbt.utilities.DBHelpers;

/**
 *
 * @author johny
 */
public class ProductsDAO implements Serializable{
    
    
    
    List<ProductsDTO> bookList;

    public List<ProductsDTO> getBookList() {
        return bookList;
    }
    
    public void getAllBook() throws ClassNotFoundException, SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ProName "
                        + "FROM Products";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String proName = rs.getString("ProName");
                    ProductsDTO dto = new ProductsDTO(proName);

                    if (this.bookList == null) {
                        this.bookList = new ArrayList<>();
                    }
                    this.bookList.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
