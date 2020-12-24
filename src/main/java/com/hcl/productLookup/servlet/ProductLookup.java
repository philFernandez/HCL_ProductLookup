package com.hcl.productLookup.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            // Define a List for holding the ResultSet from the database query.
            // This will be passed to result.jsp for rendering
            List<String> results = new ArrayList<>();
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            PreparedStatement pst = conn.prepareStatement(sql);
            try {
                pst.setInt(1, Integer.parseInt(request.getParameter("id")));
            } catch (NumberFormatException e) {
                response.sendRedirect("bad_input.jsp");
            }
            ResultSet resultSet = pst.executeQuery();
            // Add the db result to the results List in String form
            while (resultSet.next()) {
                results.add(resultSet.getString("name"));
                results.add("$" + String.format("%.2f", resultSet.getFloat("price")));
                results.add(Integer.toString(resultSet.getInt("quantity")));
            }
            // Put the results list into the request's context to be used in jsp
            request.setAttribute("dbResults", results);
            // Dispatch the request context to the result.jsp
            RequestDispatcher reqDispatch =
                    getServletContext().getRequestDispatcher("/result.jsp");
            reqDispatch.forward(request, response);
        } catch (IOException e) {
            System.out.println("There was an IOException");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("There was a SQLException");
            e.printStackTrace();
        } catch (ServletException e) {
            System.out.println("There was a ServletException");
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            System.out.println("There was a ClassNotFoundException");
            e1.printStackTrace();
        }
    }
}
