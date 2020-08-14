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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quanbt.cart.CartObject;
import quanbt.carts.CartsCheckOutError;
import quanbt.carts.CartsDAO;

/**
 *
 * @author johny
 */
@WebServlet(name = "CheckoutBookServlet", urlPatterns = {"/CheckoutBookServlet"})
public class CheckoutBookServlet extends HttpServlet {

//    public static final String SUCCESSFUL_PAGE = "onlineStore.html";
//    public static final String FAILED_PAGE = "viewCart.jsp";
    public static final String SUCCESSFUL_PAGE = "onlineStore";
    public static final String FAILED_PAGE = "checkOutConfirm";

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

        String url = siteMap.get(FAILED_PAGE);
        boolean result = false;

        String name = request.getParameter("txtName");
        String address = request.getParameter("txtAddress");
        CartsCheckOutError errors = new CartsCheckOutError();

        try {
            boolean foundErr = false;
            if (name.trim().length() < 6 || address.trim().length() > 50) {
                foundErr = true;
                errors.setNameLengthErr("NAME is required inputted from 6 to 50 chars");
            }
            if (address.trim().length() < 15 || address.trim().length() > 50) {
                foundErr = true;
                errors.setAddressLengthErr("ADDRESS is required inputted from 15 to 50 chars");
            }

            if (foundErr) {
                request.setAttribute("INFO_CHECKOUT_ERRORS", errors);
            } else {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    if (cart != null) {
                        Map<String, Integer> items = cart.getItems();
                        if (items != null) {
                            CartsDAO dao = new CartsDAO();
                            for (String title : items.keySet()) {
                                result = dao.saveBooksToDB(title, items.get(title), name, address);
                                if (result != true) {
                                    break;
                                }
                            }
                            if (result == true) {
                                url = siteMap.get(SUCCESSFUL_PAGE);
                                session.invalidate();
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log("ERROR at CheckoutBook: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("ERROR at CheckoutBook: " + ex.getMessage());
        } catch (NamingException ex) {
            log("ERROR at CheckoutBook: " + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
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
