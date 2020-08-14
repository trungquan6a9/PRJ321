/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quanbt.registration.RegistrationDAO;
import quanbt.registration.RegistrationDTO;

/**
 *
 * @author johny
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    public static final String SEARCH_PAGE = "adminPage";
    public static final String SEARCH_PAGE_FOR_USER = "userPage";
    public static final String INVALID_PAGE = "invalid";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = INVALID_PAGE;

        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.checkLogin(username, password);
            if (result == true) {
                RegistrationDTO dto = dao.getInfomation(username);
                if (dto.isIsAdmin() == true) {
                    url = SEARCH_PAGE;
                } else {
                    url = SEARCH_PAGE_FOR_USER;
                }
                HttpSession session = request.getSession();//true

                session.setAttribute("FULLNAME", dto.getFullname());
                session.setAttribute("USERNAME", username);
                session.setAttribute("ROLE", dto.isIsAdmin());

            }
        } catch (SQLException ex) {
            log("Error at LoginServlet: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Error at LoginServlet: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error at LoginServlet: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
