package com.mycompany.proyectocaja.dao;

import com.mycompany.proyectocaja.model.Rol;
import com.mycompany.proyectocaja.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {

    // metodo para obtener todos los roles de la base de datos
    public List<Rol> obtenerTodosLosRoles() {
        List<Rol> roles = new ArrayList<>();
        String sql = "SELECT * FROM roles";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Rol rol = new Rol();
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("nombre"));
                rol.setDescripcion(rs.getString("descripcion"));
                roles.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    // metodo para obtener un rol por su ID
    public Rol obtenerRolPorId(int id) {
        Rol rol = null;
        String sql = "SELECT * FROM roles WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rol = new Rol();
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("nombre"));
                rol.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }


}
