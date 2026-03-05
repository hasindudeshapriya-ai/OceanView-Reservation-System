/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qa.reservation.system.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.qa.reservation.system.util.DBConnection;

/**
 *
 * @author hasin
 */
   
    @WebServlet("/invoice/*")
public class BillServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.getWriter().println("Reservation ID is required!");
            return;
        }

        String reservationId = pathInfo.substring(1);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.getConnection()) {

           PreparedStatement ps = con.prepareStatement(
        "SELECT * FROM reservations WHERE reservationNumber=?"
         );

            ps.setString(1, reservationId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String guestName = rs.getString("guestName");
                String roomId = rs.getString("roomType");

                LocalDate checkIn = rs.getDate("checkInDate").toLocalDate();
                LocalDate checkOut = rs.getDate("checkOutDate").toLocalDate();

                long nights = ChronoUnit.DAYS.between(checkIn, checkOut);

             
                String roomType = rs.getString("roomType");

             double ratePerNight = 0;

         if (roomType.equalsIgnoreCase("Single Room")) {
         ratePerNight = 5000;
         
       } else if (roomType.equalsIgnoreCase("Double Room")) {
         ratePerNight = 8000;
         
       } else if (roomType.equalsIgnoreCase("Family Suite")) {
         ratePerNight = 12000;
         }

                double subtotal = nights * ratePerNight;
                double tax = subtotal * 0.10;   
                double total = subtotal + tax;

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Invoice</title>");

                out.println("<style>");
                out.println("body { font-family: Arial; background:#f4f6f9; }");
                out.println(".invoice { width:700px; margin:40px auto; background:white; padding:30px; border-radius:10px; box-shadow:0 0 15px rgba(0,0,0,0.2); }");
                out.println("h2 { text-align:center; color:#1f3c88; }");
                out.println("table { width:100%; border-collapse:collapse; margin-top:20px; }");
                out.println("th, td { padding:10px; border-bottom:1px solid #ddd; }");
                out.println(".total { font-weight:bold; font-size:18px; color:#27ae60; }");
                out.println(".print { text-align:center; margin-top:20px; }");
                out.println("button { padding:8px 20px; background:#1f3c88; color:white; border:none; border-radius:5px; cursor:pointer; }");
                out.println("</style>");

                out.println("</head>");
                out.println("<body>");

                out.println("<div class='invoice'>");

                out.println("<h2>🌊 Ocean View Resort</h2>");
                out.println("<h3 style='text-align:center;'>INVOICE</h3>");

                out.println("<p><b>Reservation ID:</b> " + reservationId + "</p>");
                out.println("<p><b>Guest Name:</b> " + guestName + "</p>");
                out.println("<p><b>Room:</b> " + roomId + "</p>");
                out.println("<p><b>Check-In:</b> " + checkIn + "</p>");
                out.println("<p><b>Check-Out:</b> " + checkOut + "</p>");
                out.println("<p><b>Total Nights:</b> " + nights + "</p>");

                out.println("<table>");
                out.println("<tr><th>Description</th><th>Amount (LKR)</th></tr>");
                out.println("<tr><td>Room Charge (" + nights + " × " + ratePerNight + ")</td><td>" + subtotal + "</td></tr>");
                out.println("<tr><td>Tax (10%)</td><td>" + tax + "</td></tr>");
                out.println("</table>");

                out.println("<p class='total'>Total Amount: LKR " + total + "</p>");

                out.println("<div class='print'>");
                out.println("<button onclick='window.print()'>🖨 Print Invoice</button>");
                out.println("</div>");

                out.println("</div>");
                out.println("</body>");
                out.println("</html>");

            } else {
                out.println("<h3 style='text-align:center;'>Reservation Not Found!</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    

