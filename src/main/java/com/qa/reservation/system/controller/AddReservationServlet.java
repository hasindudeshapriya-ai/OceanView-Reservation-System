/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qa.reservation.system.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

import com.qa.reservation.system.dao.ReservationDAO;


/**
 *
 * @author hasin
 */
@WebServlet("/addReservation")
public class AddReservationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reservationNumber = request.getParameter("reservationNumber");
        String guestName = request.getParameter("guestName");
        String address = request.getParameter("address");
        String contactNumber = request.getParameter("contactNumber");
        String roomType = request.getParameter("roomType");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");

        ReservationDAO.insertReservation(
                reservationNumber,
                guestName,
                address,
                contactNumber,
                roomType,
                checkInDate,
                checkOutDate
        );

        response.sendRedirect("dashboard.html?success=1");
    }
}