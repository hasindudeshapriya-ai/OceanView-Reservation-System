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

@WebServlet("/viewReservations")
public class ViewReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.getConnection()) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservations");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Reservations</title>");

            out.println("<style>");

            out.println("*{margin:0;padding:0;box-sizing:border-box;font-family:Arial;}");

            out.println("body{height:100vh;background-image:url('A.png');"
                    + "background-size:cover;background-position:center;"
                    + "display:flex;justify-content:center;align-items:center;}");

            out.println(".container{width:90%;max-width:1100px;padding:30px;"
                    + "border-radius:15px;background:rgba(255,255,255,0.15);"
                    + "backdrop-filter:blur(10px);box-shadow:0 8px 30px rgba(0,0,0,0.4);"
                    + "color:black;text-align:center;}");

            out.println("h2{margin-bottom:20px;}");

            out.println("table{width:100%;border-collapse:collapse;margin-top:15px;}");

            out.println("th{background:#0d47a1;color:white;padding:12px;}");

            out.println("td{padding:10px;text-align:center;background:rgba(255,255,255,0.1);}");


            out.println(".edit{background:#f39c12;color:white;padding:6px 12px;border-radius:5px;text-decoration:none;margin-right:5px;}");

            out.println(".delete{background:#e74c3c;color:white;padding:6px 12px;border-radius:5px;text-decoration:none;margin-right:5px;}");

            out.println(".bill{background:#27ae60;color:white;padding:6px 12px;border-radius:5px;text-decoration:none;}");

            out.println(".back{display:inline-block;margin-top:20px;padding:10px 20px;"
                    + "background:#1e5aa5;color:white;border-radius:25px;text-decoration:none;}");

            out.println(".back:hover{background:#15437d;}");

            out.println("</style>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container'>");

            out.println("<h2>🌊 Ocean View Resort - Reservation List</h2>");

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Guest Name</th>");
            out.println("<th>Contact</th>");
            out.println("<th>Room</th>");
            out.println("<th>Check In</th>");
            out.println("<th>Check Out</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");

            while (rs.next()) {

                String id = rs.getString("reservationNumber");

                out.println("<tr>");

                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString("guestName") + "</td>");
                out.println("<td>" + rs.getString("contactNumber") + "</td>");
                out.println("<td>" + rs.getString("roomType") + "</td>");
                out.println("<td>" + rs.getDate("checkInDate") + "</td>");
                out.println("<td>" + rs.getDate("checkOutDate") + "</td>");

                out.println("<td>");

                out.println("<a class='edit' href='editReservation?id=" + id + "'>✏ Edit</a>");

                out.println("<a class='delete' href='deleteReservation?id=" + id + "' "
                        + "onclick=\"return confirm('Delete this reservation?');\">🗑 Delete</a>");

                out.println("<a class='bill' href='" + request.getContextPath() + "/invoice/" + id + "'>💳 Bill</a>");

                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<a class='back' href='dashboard.html'>⬅ Back to Dashboard</a>");

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}