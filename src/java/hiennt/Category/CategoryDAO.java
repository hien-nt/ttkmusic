/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.Category;

import hiennt.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CategoryDAO implements Serializable {

    List<CategoryDTO> listCategory;

    public List<CategoryDTO> getListCategory() {
        return listCategory;
    }

    public void getAllCategory() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            con = DBUtils.makeConnection();
            String sql = "SELECT [categoryID]\n"
                    + "      ,[categoryName]\n"
                    + "  FROM [dbo].[Category]";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt("categoryID");
                String categoryName = rs.getString("categoryName");
                CategoryDTO category = new CategoryDTO(categoryId, categoryName);
                if (listCategory == null) {
                    listCategory = new ArrayList<>();
                }
                listCategory.add(category);
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
