/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.webproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.controller.Controller;
import pl.polsl.entities.NumberCalculated;
import pl.polsl.entities.NumberData;
import pl.polsl.DBmanager.DBManager;
import pl.polsl.model.*;
import pl.polsl.view.SummaryGenerator;

/**
 * Servlet class responsible for simulating Lotto raffles with probability
 *
 * @author Bartosz Pomierny
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String numberParam = request.getParameter("number");
            String rafflesParam = request.getParameter("raffles");

            List raffleList = List.of("");
            Raffles raffles = new Raffles(raffleList);
            RaffleDatabase raffleDB = new RaffleDatabase();
            SummaryGenerator summaryGen = new SummaryGenerator();
            String[] numberParamStrArr = numberParam.split(" ");
            String[] rafflesParamsStrArr = rafflesParam.split(" ");

            Controller controller = new Controller(raffleDB, raffles, summaryGen);

            if (controller.checkAll(numberParamStrArr, 49)) {
                response.sendError(response.SC_BAD_REQUEST, "Invalid input, make sure your number is between 1 and 49");
            }
            if (controller.checkAll(rafflesParamsStrArr, 1000000)) {
                response.sendError(response.SC_BAD_REQUEST, "Invalid input, make sure your number of raffles is between 1 and 1 000 000");
            }
            controller.generateRaffles(Integer.parseInt(rafflesParam));
            int occ = controller.countOcc(Integer.parseInt(numberParam), controller.raffles.rafflesGenerated);
            double prob = controller.raffles.probability(occ, Integer.parseInt(rafflesParam));
            String probString = String.format("%.5f", prob);

            out.println("<title>Lotto Stats</title>");
            out.println("<h1>Your results are here!</h1>");
            out.println("<p>The number <b>" + numberParam + "</b> has appeared <b>" + occ + "</b> times in the last <b>" + rafflesParam + "</b> raffles.\nThe probability of it being in the next raffle is <b>" + probString + "</b>%</p>");
            out.println("<a href=\"index.html\"><button>Back</button></a> \n");

            Cookie cookieRaffles = new Cookie("cookieRaff", rafflesParam);
            Cookie cookieOccurence = new Cookie("cookieOcc", Integer.toString(occ));
            Cookie cookieProbability = new Cookie("cookieProb", probString);
            Cookie cookieNumber = new Cookie("cookieNum", numberParam);
            cookieNumber.setMaxAge(60);
            cookieRaffles.setMaxAge(60);
            cookieOccurence.setMaxAge(60);
            cookieProbability.setMaxAge(60);
            response.addCookie(cookieNumber);
            response.addCookie(cookieRaffles);
            response.addCookie(cookieOccurence);
            response.addCookie(cookieProbability);

            DBManager manager = new DBManager();
            manager.initDB();
            manager.SendNumber(numberParam, occ, rafflesParam, prob);
            List<NumberCalculated> numCList = manager.getNumbersCalculated();
            List<NumberData> numDList = manager.getNumbersData();
            out.println("<h1>DB - your past searches database:</h1>");
            out.println("<p>Note: the first record has been added as a result of the database's initialization!<p>");
            for (int i = 0; i < numDList.size(); i++) {
                for (int ii = 0; ii < numDList.size(); ii++) {
                    if (numCList.get(ii).getNumberData().getId() == numDList.get(i).getId()) {
                        out.println("<p>Number: " + numDList.get(i).getNumber() + " - it's occurrence is " + numCList.get(ii).getOccurrences() + "/" + numDList.get(i).getRafflesNum() + " raffles, and it's probability of occurring in the next one is:" + numCList.get(ii).getProbability() + "%</p>");
                    }
                }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
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
