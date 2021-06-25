package com.myshop.servlets;

import com.myshop.connection.Order;
import com.myshop.dao.DAOConnection;
import com.myshop.dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            
            String phone = session.getAttribute("userphone").toString();
            String name = request.getParameter("pname");
            int productId = Integer.parseInt(request.getParameter("productid"));
            int productQty = Integer.parseInt(request.getParameter("pqty"));
            int totalAmount = Integer.parseInt(request.getParameter("pamount"));
            
            Order order = new Order();
            order.setUserPhone(phone);
            order.setProductName(name);
            order.setProduct_id(productId);
            order.setProductQuantity(productQty);
            order.setOrderAmount(totalAmount);
            
            OrderDAO odao = new OrderDAO(DAOConnection.sqlconnection());
            odao.saveOrder(order);
            session.setAttribute("success_message", "Order Placed Successfully....!");
            response.sendRedirect("index.jsp");
            
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
