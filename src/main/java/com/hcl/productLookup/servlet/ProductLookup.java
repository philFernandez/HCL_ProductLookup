package com.hcl.productLookup.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
        try {
            String sql = "select * from product where id=?";
            ArrayList<String> results = new ArrayList<>();
            response.setContentType("text/html");
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(request.getParameter("id")));
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                results.add(resultSet.getString("name"));
                results.add(String.format("%.2f", resultSet.getFloat("price")));
                results.add(Integer.toString(resultSet.getInt("quantity")));
            }
            request.setAttribute("dbResults", results);
            RequestDispatcher reqDispatch =
                    getServletContext().getRequestDispatcher("/result.jsp");
            reqDispatch.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
