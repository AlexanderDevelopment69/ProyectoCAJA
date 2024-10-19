package com.mycompany.proyectocaja.dao;
import com.mycompany.proyectocaja.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuditoriaDAO {
    // Metodo para registrar la auditoría
    public void registrarAuditoria(int idUsuario, String accion) {
        String sql = "INSERT INTO auditoria (id_usuario, accion) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUsuario);
            pstmt.setString(2, accion);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
