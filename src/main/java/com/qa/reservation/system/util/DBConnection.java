/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qa.reservation.system.util;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author hasin
 */
public class DBConnection {
    private static String url = "jdbc:mysql://localhost:3306/oceanview";
    private static String user = "root";
    private static String password = "";

    public static Connection getConnection() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
    

