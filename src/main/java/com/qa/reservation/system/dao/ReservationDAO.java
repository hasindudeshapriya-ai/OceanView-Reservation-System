/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qa.reservation.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qa.reservation.system.util.DBConnection;

public class ReservationDAO {

    public static void insertReservation(
            String reservationNumber,
            String guestName,
            String address,
            String contactNumber,
            String roomType,
            String checkInDate,
            String checkOutDate) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO reservations VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, reservationNumber);
            ps.setString(2, guestName);
            ps.setString(3, address);
            ps.setString(4, contactNumber);
            ps.setString(5, roomType);
            ps.setString(6, checkInDate);
            ps.setString(7, checkOutDate);

            ps.executeUpdate();

            System.out.println("✅ Reservation Inserted Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> getAllReservations() {

        List<String[]> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM reservations";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String[] row = new String[7];

                row[0] = rs.getString("reservationNumber");
                row[1] = rs.getString("guestName");
                row[2] = rs.getString("address");
                row[3] = rs.getString("contactNumber");
                row[4] = rs.getString("roomType");
                row[5] = rs.getString("checkInDate");
                row[6] = rs.getString("checkOutDate");

                list.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
    

