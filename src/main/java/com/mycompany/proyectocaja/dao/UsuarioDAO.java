package com.mycompany.proyectocaja.dao;

import com.mycompany.proyectocaja.model.Usuario;
import com.mycompany.proyectocaja.model.Rol;
import com.mycompany.proyectocaja.util.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    //metodo para registrar un nuevo usuario
    public void registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (username, password, id_rol, nombre_completo, email, estado) VALUES (?, ?, ?, ?, ?, ?)";

        // encriptar la contraseña
        String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, hashedPassword);
            pstmt.setInt(3, usuario.getRol().getId());
            pstmt.setString(4, usuario.getNombreCompleto());
            pstmt.setString(5, usuario.getEmail());
            pstmt.setBoolean(6, usuario.isEstado());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // metodo para iniciar sesion
    public Usuario iniciarSesion(String username, String password) {
        Usuario usuario = null;
        String sql = "SELECT u.*, r.nombre as rol_nombre, r.descripcion as rol_descripcion FROM usuarios u "
                + "JOIN roles r ON u.id_rol = r.id WHERE u.username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEstado(rs.getBoolean("estado"));

                Rol rol = new Rol();
                rol.setId(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("rol_nombre"));
                rol.setDescripcion(rs.getString("rol_descripcion"));
                usuario.setRol(rol);

                // verifica la contraseña
                if (!BCrypt.checkpw(password, usuario.getPassword())) {
                    // contraseña incorrecta, retorna null o lanza excepción
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }


    public void actualizarUsuario(Usuario usuario, int idUsuarioActual) {
        String sql = "UPDATE usuarios SET nombre_completo = ?, email = ?, id_rol = ?, estado = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombreCompleto());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setInt(3, usuario.getRol().getId());
            pstmt.setBoolean(4, usuario.isEstado());
            pstmt.setInt(5, usuario.getId());
            pstmt.executeUpdate();

            // Registrar la acción en la tabla de auditoría
            AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
            auditoriaDAO.registrarAuditoria(idUsuarioActual, "Actualizó usuario con ID: " + usuario.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // metodo para restablecer la contraseña de un usuario
    public void restablecerContrasena(String username, String nuevaContrasena) {
        String sql = "UPDATE usuarios SET password = ? WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nuevaContrasena);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Usuario obtenerUsuarioPorUsername(String username) {
        Usuario usuario = null;
        String sql = "SELECT u.*, r.nombre AS rol_nombre FROM usuarios u JOIN roles r ON u.id_rol = r.id WHERE u.username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEstado(rs.getBoolean("estado"));


                Rol rol = new Rol();
                rol.setId(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("rol_nombre"));
                usuario.setRol(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.*, r.nombre as rol_nombre FROM usuarios u JOIN roles r ON u.id_rol = r.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEstado(rs.getBoolean("estado"));

                Rol rol = new Rol();
                rol.setId(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("rol_nombre"));
                usuario.setRol(rol);

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void eliminarUsuario(int id, int idUsuarioActual) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        String sqlAuditoria = "INSERT INTO auditoria (id_usuario, accion) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (PreparedStatement pstmtAuditoria = conn.prepareStatement(sqlAuditoria)) {
                    pstmtAuditoria.setInt(1, idUsuarioActual);
                    pstmtAuditoria.setString(2, "Eliminó el usuario con ID: " + id);
                    pstmtAuditoria.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
