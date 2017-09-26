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

/**
 *
 * @author David K
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlett"})
public class LoginServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = "Login.html";
        String action = request.getParameter("action");
        
        
        
        if(action == "submit")
        {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
        
            if(userName != "jsmith@toba.com" || password != "letmein")
            {
                url = "/Login_failure.html";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            else{
                url = "/Account_activity.html";
                response.sendRedirect(url);
            }
        
        }
        
    }

}
