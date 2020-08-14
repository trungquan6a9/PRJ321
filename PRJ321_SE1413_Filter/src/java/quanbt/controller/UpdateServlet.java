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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quanbt.registration.RegistrationDAO;
import quanbt.registration.RegistrationSignUpError;

/**
 *
 * @author johny
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private static final String ERROR_PAGE = "errorPage";
    private static final String SEARCH_CONTROLLER = "search";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        //check box khi duoc check = value
        //ko duoc check = null
        String isAdmin = request.getParameter("chkAdmin");
        String searchValue = request.getParameter("lastSearchValue");
        boolean role = false;
        if (isAdmin != null) {
            role = true;
        }

        RegistrationSignUpError errors = new RegistrationSignUpError();

        ServletContext context = request.getServletContext();
        Map<String, String> siteMap
                = (Map<String, String>) context.getAttribute("SITE_MAP");

        String url = siteMap.get(ERROR_PAGE);
        try {
            boolean foundErr = false;
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthErr("PASSWORD is required inputted from 6 to 30 chars");
            }

            if (foundErr) {
                request.setAttribute("UPDATE_ERRORS", errors);
                request.setAttribute("CURRENT_USER", username);
                url = siteMap.get(SEARCH_CONTROLLER) + "?txtSearchValue=" + searchValue;
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.updateAccount(username, password, role);
                if (result) {
                    url = siteMap.get(SEARCH_CONTROLLER) + "?txtSearchValue=" + searchValue;
                }
            }
        } catch (SQLException ex) {
            log("ERROR at UpdateServlet" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("ERROR at UpdateServlet" + ex.getMessage());
        } catch (NamingException ex) {
            log("ERROR at UpdateServlet" + ex.getMessage());
        } finally {
//            response.sendRedirect(url);//bat loi khi sua pass->rd
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
