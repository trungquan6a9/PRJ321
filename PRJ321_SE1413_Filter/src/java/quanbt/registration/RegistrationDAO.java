/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.registration;

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
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException, NamingException {
        //1. make connection
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1 make connection
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2 create SQL string
                String sql = "SELECT username "
                        + "FROM Registration "
                        + "WHERE username = ? AND password = ?";
                //3 create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4 query data
                rs = stm.executeQuery();
                // 5 process
                if (rs.next()) {
                    return true;
                }
            }//end if connection null
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
        return false;
    }

    public RegistrationDTO getInfomation(String username) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO dto = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT password, fullname, isAdmin "
                        + "FROM Registration "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    boolean isAdmin = rs.getBoolean("isAdmin");

                    dto = new RegistrationDTO(username, password, fullname, isAdmin);
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
        return dto;
    }

    private List<RegistrationDTO> listAccounts;

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchByFullname(String searchValue) throws SQLException,
            ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT username, password, fullname, isAdmin "
                        + "FROM Registration "
                        + "WHERE fullname LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    boolean isAdmin = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, isAdmin);

                    if (this.listAccounts == null) {
                        this.listAccounts = new ArrayList<>();
                    }
                    this.listAccounts.add(dto);
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

    public boolean deleteAccount(String username) throws SQLException,
            ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM Registration "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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

    public boolean updateAccount(String username, String password, boolean isAdmin)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
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

    public boolean createAccount(String username, String password, String fullname, boolean role) throws ClassNotFoundException, SQLException, NamingException {
        //dua tren so cot ma khong cho phep allow null
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Registration(username, password, fullname, isAdmin) "
                        + "VALUES (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
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
