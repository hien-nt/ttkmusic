/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiennt.Category;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TestDAO {

    public static void main(String[] args) throws SQLException, ParseException {
        CategoryDAO dao = new CategoryDAO();
        dao.getAllCategory();
        List<CategoryDTO> listCate = dao.getListCategory();
        for (CategoryDTO dto : listCate) {
            System.out.println(dto.getCategoryId() + ", " + dto.getCategoryName());
        }
        String startDate = "2020-12-12";
        String endDate = "2019-12-12";
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdformat.parse(startDate);
        Date d2 = sdformat.parse(endDate);
        if (d1.compareTo(d2) > 0) {
            System.out.println("Date 1 occurs after Date 2");
        } else if (d1.compareTo(d2) < 0) {
            System.out.println("Date 1 occurs before Date 2");
        } else if (d1.compareTo(d2) == 0) {
            System.out.println("Both dates are equal");
        }
    }
}
