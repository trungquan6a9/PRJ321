/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.utilities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author johny
 */
public class DBHelpers implements Serializable {

    public static Connection makeConnection()
            throws ClassNotFoundException, SQLException, NamingException {

        Connection con = null;
        //1 Init context
        Context currentContext = new InitialContext();
        //2 lookup server nickname
        Context serverContext = (Context) currentContext.lookup("java:comp/env");
        //3 lookup aliat name
        DataSource ds = (DataSource) serverContext.lookup("DV007");
        //4 get con
        con = ds.getConnection();
        
        return con;
//        //1 load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2 create connection string
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJ321SE1413";
//        //3 open connection
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//        return con;
    }

}
