package com.clientes.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public static final String sqlSelect = "select * from cliente";
    public static final String sqlInsert = "insert into cliente(Nombres,Correo,Telefono)values(?,?,?)";
    public static final String sqlUpdate = "update cliente set Nombres=?,Correo=?,Telefono=? where Id=?";
    public static final String sqlDelete = "delete from cliente where Id=";
    PreparedStatement ps;
    ResultSet rs;
    java.sql.Connection con;
    Connection conectar = new Connection();
    public List<Cliente> listar() {
        List<Cliente> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente p = new Cliente();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelefono(rs.getString(4));
                datos.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return datos;
    }

    public int agregar(Cliente per) {
        int r = 0;
        String sql = sqlInsert;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getNombres());
            ps.setString(2, per.getCorreo());
            ps.setString(3, per.getTelefono());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }

    public int actualizar(Cliente per) {
        int r = 0;
        String sql = sqlUpdate;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getNombres());
            ps.setString(2, per.getCorreo());
            ps.setString(3, per.getTelefono());
            ps.setInt(4, per.getId());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return r;
    }

    public void eliminar(int id) {
        String sql = sqlDelete + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
