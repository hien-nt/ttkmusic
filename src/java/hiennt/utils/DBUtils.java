/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DBUtils implements Serializable{

    public static Connection makeConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MusicTTK;";
            Connection con = DriverManager.getConnection(url, "sa", "123456");
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
         DBUtils dbutils = new DBUtils();
         try {
             if(dbutils.makeConnection() != null){
                 System.out.println("Connected database successfull");
             }else{
                 System.out.println("Failed to connect to database");
             }
         } catch (Exception ex) {
             ex.printStackTrace();
         }
    }
}
