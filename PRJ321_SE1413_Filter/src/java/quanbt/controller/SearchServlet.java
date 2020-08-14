/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
import quanbt.registration.RegistrationDTO;

/**
 *
 * @author johny
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    public static final String SEARCH_PAGE = "adminPage";
    public static final String LOGIN_PAGE = "try";
//    public static final String SHOW_SEARCH_PAGE = "ShowSearchResultServlet";

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

        String searchValue = request.getParameter("txtSearchValue");

        ServletContext context = request.getServletContext();
        Map<String, String> siteMap
                = (Map<String, String>) context.getAttribute("SITE_MAP");
        String url = siteMap.get(SEARCH_PAGE);

        HttpSession session = request.getSession(false);

        try {
            if (session == null) {
                url = siteMap.get(LOGIN_PAGE);
            } else if (searchValue.trim().length() > 0) {
                RegistrationDAO dao = new RegistrationDAO();
                dao.searchByFullname(searchValue);

                List<RegistrationDTO> result = dao.getListAccounts();
                request.setAttribute("SEARCH_RESULT", result);

                url = siteMap.get(SEARCH_PAGE);
            }
        } catch (SQLException ex) {
            log("Error at SearchController" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Error at SearchController" + ex.getMessage());
        } catch (NamingException ex) {
            log("Error at SearchController" + ex.getMessage());
        } finally {
            //mat du lieu --> dung rd
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
