package com.clientes.modelo;

import java.sql.DriverManager;

public class Connection {
    String url = "jdbc:mysql://localhost:3306/bd_ejemplo";
    String user = "root", pass = "";
    java.sql.Connection con;

    public java.sql.Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return con;
    }
}
