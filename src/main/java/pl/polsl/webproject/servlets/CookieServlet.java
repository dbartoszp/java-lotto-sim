/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.webproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet class responsible for handling cookies
 *
 * @author Bartuś
 */
@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {

            Cookie[] cookies = request.getCookies();
            String cookieNumber, cookieRaffles, cookieOccurence, cookieProbability = "none";

            out.println("<title>Lotto Stats</title>");
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("cookieNum")) {
                        cookieNumber = cookie.getValue();
                        out.println("<h1>Last searched number: " + cookieNumber + "</h1>");
                    }
                    if (cookie.getName().equals("cookieRaff")) {
                        cookieRaffles = cookie.getValue();
                        out.println("<h1>Number of raffles: " + cookieRaffles + "</h1>");
                    }
                    if (cookie.getName().equals("cookieOcc")) {
                        cookieOccurence = cookie.getValue();
                        out.println("<h1>Number of occurrences: " + cookieOccurence + "</h1>");
                    }
                    if (cookie.getName().equals("cookieProb")) {
                        cookieProbability = cookie.getValue();
                        out.println("<h1>Probability of it occurring in the next raffle: " + cookieProbability + "%</h1>");
                        out.println("<a href=\"index.html\"><button>Back</button></a> \n");
                    }

                }

            } else {
                out.println("<h1>You have to calculate the probability of a number first!</h1>");
                out.println("<a href=\"index.html\"><button>Back</button></a> \n");
            }

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
