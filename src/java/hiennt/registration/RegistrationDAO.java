/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.registration;

import hiennt.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "SELECT [username]\n"
                    + "      ,[password]\n"
                    + "      ,[name]\n"
                    + "      ,[isRole]\n"
                    + "  FROM [dbo].[Registration]\n"
                    + "  WHERE [username] = ? AND [password] = ?";

            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }

    public RegistrationDTO getRegistration(String username) throws SQLException {
        RegistrationDTO dto = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            con = DBUtils.makeConnection();
            String sql = "SELECT [password]\n"
                    + "      ,[name]\n"
                    + "      ,[isRole]\n"
                    + "  FROM [dbo].[Registration]\n"
                    + "  WHERE [username] = ? ";

            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                String name = rs.getString("name");
                boolean isRole = rs.getBoolean("isRole");
                dto = new RegistrationDTO(username, password, name, isRole);
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

    public boolean insertRegistration(String username, String password, String name, boolean isRole) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "INSERT INTO [dbo].[Registration]\n"
                    + "           ([username]\n"
                    + "           ,[password]\n"
                    + "           ,[name]\n"
                    + "           ,[isRole])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, name);
            stm.setBoolean(4, isRole);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
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
