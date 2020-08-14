/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.carts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import quanbt.utilities.DBHelpers;

/**
 *
 * @author johny
 */
public class CartsDAO implements Serializable {

    public boolean saveBooksToDB(String title, int quantity, String owner, String address) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Carts (Title, Quantity, Owner, Address) "
                        + "VALUES (?, ?, ?, ?);";
                stm = con.prepareStatement(sql);
                stm.setString(1, title);
                stm.setInt(2, quantity);
                stm.setString(3, owner);
                stm.setString(4, address);
                int effectedRow = stm.executeUpdate();
                if (effectedRow > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
