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

import com.qa.reservation.system.util.DBConnection;

@WebServlet("/editReservation")
public class EditReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM reservations WHERE reservationNumber=?"
            );

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Edit Reservation</title>");

                out.println("<style>");

                out.println("*{margin:0;padding:0;box-sizing:border-box;font-family:Arial;}");

                out.println("body{height:100vh;display:flex;justify-content:center;align-items:center;"
                        + "background-image:url('A.png');"
                        + "background-size:cover;background-position:center;}");

                out.println(".card{width:420px;padding:35px;border-radius:15px;"
                        + "background:rgba(255,255,255,0.15);"
                        + "backdrop-filter:blur(10px);"
                        + "box-shadow:0 8px 30px rgba(0,0,0,0.4);"
                        + "color:white;text-align:center;}");

                out.println(".card h2{margin-bottom:10px;}");

                out.println(".subtitle{margin-bottom:20px;font-size:14px;opacity:0.9;}");

                out.println(".input-group{text-align:left;margin-bottom:15px;}");

                out.println(".input-group label{font-size:14px;}");

                out.println(".input-group input{width:100%;padding:10px;margin-top:5px;"
                        + "border:none;border-radius:8px;outline:none;}");

                out.println(".btn{width:100%;padding:12px;margin-top:10px;border:none;"
                        + "border-radius:25px;font-size:15px;cursor:pointer;}");

                out.println(".update{background:#27ae60;color:white;}");

                out.println(".update:hover{background:#1e874b;}");

                out.println(".back{background:#1e5aa5;color:white;}");

                out.println(".back:hover{background:#15437d;}");

                out.println("</style>");

                out.println("</head>");
                out.println("<body>");

                out.println("<div class='card'>");

                out.println("<h2>🌊 Ocean View Resort</h2>");
                out.println("<p class='subtitle'>Edit Reservation</p>");

                out.println("<form action='updateReservation' method='post'>");

                out.println("<div class='input-group'>");
                out.println("<label>Reservation Number</label>");
                out.println("<input type='text' name='id' value='" + rs.getString("reservationNumber") + "' readonly>");
                out.println("</div>");

                out.println("<div class='input-group'>");
                out.println("<label>Guest Name</label>");
                out.println("<input type='text' name='guestName' value='" + rs.getString("guestName") + "'>");
                out.println("</div>");

                out.println("<div class='input-group'>");
                out.println("<label>Address</label>");
                out.println("<input type='text' name='address' value='" + rs.getString("address") + "'>");
                out.println("</div>");

                out.println("<div class='input-group'>");
                out.println("<label>Contact Number</label>");
                out.println("<input type='text' name='contactNumber' value='" + rs.getString("contactNumber") + "'>");
                out.println("</div>");
                
                out.println("<button class='btn update' type='submit'>Update Reservation</button>");

                out.println("</form>");

                out.println("<a href='viewReservations'><button class='btn back'>Back to Reservations</button></a>");

                out.println("</div>");

                out.println("</body>");
                out.println("</html>");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}