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
import javax.servlet.http.HttpSession;
import quanbt.registration.RegistrationDAO;
import quanbt.registration.RegistrationDeleteError;

/**
 *
 * @author johny
 */
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {

    public static final String ERROR_PAGE = "error";

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

        ServletContext context = request.getServletContext();
        Map<String, String> siteMap
                = (Map<String, String>) context.getAttribute("SITE_MAP");

        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        String url = siteMap.get(ERROR_PAGE);

        HttpSession session = request.getSession(false);
        String currentUsername = (String) session.getAttribute("USERNAME");
        try {
            if (username.equals(currentUsername)) {
                RegistrationDeleteError errors = new RegistrationDeleteError();
                errors.setCannotDeleteCurrentUser("Can not delete current User!!!");
                request.setAttribute("CANNOT_DELETE_CURRENT_USER", errors);
                request.setAttribute("CURRENT_USER", username);
                url = siteMap.get("search")
                        + "?txtSearchValue=" + searchValue;
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.deleteAccount(username);
                if (result == true) {
                    url = siteMap.get("search")
                            + "?txtSearchValue=" + searchValue;
                }
            }
        } catch (SQLException ex) {
            log("Error at DeleteServlet" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Error at DeleteServlet" + ex.getMessage());
        } catch (NamingException ex) {
            log("Error at DeleteServlet" + ex.getMessage());
        } finally {
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
