/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JaveBeans.User;
import JaveBeans.UserDB;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

import java.sql.*;
/**
 *
 * @author David K
 */
@WebServlet(name = "NewCustomerServlet", urlPatterns = {"/NewCustomerServlet"})
public class NewCustomerServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String message = "";
        String url = "New_customer.jsp";
        String action = request.getParameter("action");
        
        GregorianCalendar currentDate = new GregorianCalendar();
        int currentYear = currentDate.get(Calendar.YEAR);
        request.setAttribute("currentYear", currentYear);
        
        
        if (action == null)
        {
            action = "signup";
        }
        
        if (action == "signup"){
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zipCode = request.getParameter("zipCode");
            String email = request.getParameter("email");
            
        
        if(firstName == null || lastName == null || phoneNumber == null ||
                address == null || city == null || state == null ||
                zipCode == null || email == null || firstName.isEmpty() || 
                lastName.isEmpty() || phoneNumber.isEmpty() || 
                address.isEmpty() || city.isEmpty() || 
                state.isEmpty() || zipCode.isEmpty() || email.isEmpty()) {
            message = "Fill out all information please.";
            url = "/New_customer.jsp";
        }
        else{
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);
            user.setCity(city);
            user.setState(state);
            user.setZipCode(zipCode);
            user.setEmail(email);
            String userName = lastName + zipCode;
            user.setUserName(userName);
            user.setPassword("welcome1");
            
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                    String sqlStatement = request.getParameter("sqlStatement");
                    String sqlResult = "";
                    String database = "jdbc:mysql://localhost:3306/Program Files";
                    String username = "root";
                    String password = "sesame";
                    
                    Connection connection = DriverManager.getConnection(database, username, password);
                    
                    Statement statement = connection.createStatement();
                    
                    ResultSet resultset = statement.executeQuery(sqlStatement);
                    sqlResult = UserDB.getHtmlTable(resultset);
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NewCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            url = "/Success.html";
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
            
        url = "password_reset.jsp";
        if(action == "submit"){
            session.removeAttribute("password");
            String password = request.getParameter("password");
            session.setAttribute("user", user);
            
            url = "/Account_activity.html";
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
            
        }
        }
    }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
