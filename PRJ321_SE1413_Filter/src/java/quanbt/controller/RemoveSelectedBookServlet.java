/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanbt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quanbt.cart.CartObject;

/**
 *
 * @author johny
 */
@WebServlet(name = "RemoveSelectedBookServlet", urlPatterns = {"/RemoveSelectedBookServlet"})
public class RemoveSelectedBookServlet extends HttpServlet {

    private static final String VIEW_CART_PAGE = "cartPlace";
    private static final String CHECK_OUT_BOOK_CONTROLLER = "checkOutConfirm";
    
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

        String button = request.getParameter("btnAction");
        String url = VIEW_CART_PAGE;
        String urlRewriting = "cart"
                + "?btnAction=View Your Cart";
        try {
            if (button.equals("Remove Selected Book")) {
                //1.User goes to cart's place
                HttpSession session = request.getSession(false);
                if (session != null) {
                    //2. User takes cart
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    if (cart != null) {
                        //3. System gets all selected items
                        String[] items = request.getParameterValues("chkItem");
                        if (items != null) {
                            //4. remove items from cart
                            for (String item : items) {
                                cart.removeItemFromCart(item);
                            }//end for item
                            //5. Update cart to Scope
                            session.setAttribute("CART", cart);
                            url = urlRewriting;
                        }//end if items have selected
                    }//end if cart is existed
                }//end if session is existed
            } else if (button.equals("Check Out")) {
                url = CHECK_OUT_BOOK_CONTROLLER;
            }

        } finally {
            //6. call view cart again
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
