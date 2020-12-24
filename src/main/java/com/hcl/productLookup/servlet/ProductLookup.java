package com.hcl.productLookup.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductLookup",
        description = "Servlet for looking up products in h2 db",
        urlPatterns = {"/ProductLookup"})
public class ProductLookup extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        String sql = "select * from product where id=?";
        try {
            Class.forName("h2.org.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                response.getWriter()
                        .println("<p>" + resultSet.getString("name") + "</p>");
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
