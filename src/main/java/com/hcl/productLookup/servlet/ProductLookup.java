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
            response.setContentType("text/html");
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(request.getParameter("id")));
            ResultSet resultSet = pst.executeQuery();
            response.getWriter().print("<h1>Search Result</h1>");
            while (resultSet.next()) {
                response.getWriter()
                        .print("<h3>" + resultSet.getString("name") + "</h3>");
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
