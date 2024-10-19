package com.mycompany.proyectocaja.dao;

import com.mycompany.proyectocaja.model.Caja;
import com.mycompany.proyectocaja.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CajaDAO {

    // Metodo para registrar una nueva caja
    public void registrarCaja(Caja caja) {
        String sql = "INSERT INTO cajas (identificador, estado, saldo_inicial, saldo_final, fecha_apertura) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, caja.getIdentificador());
            pstmt.setString(2, caja.getEstado());
            pstmt.setBigDecimal(3, caja.getSaldoInicial());
            pstmt.setBigDecimal(4, caja.getSaldoFinal());
            pstmt.setTimestamp(5, caja.getFechaApertura());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para obtener una caja por su ID
    public Caja obtenerCajaPorId(int id) {
        Caja caja = null;
        String sql = "SELECT * FROM cajas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                caja = new Caja();
                caja.setId(rs.getInt("id"));
                caja.setIdentificador(rs.getString("identificador"));
                caja.setEstado(rs.getString("estado"));
                caja.setSaldoInicial(rs.getBigDecimal("saldo_inicial"));
                caja.setSaldoFinal(rs.getBigDecimal("saldo_final"));
                caja.setFechaApertura(rs.getTimestamp("fecha_apertura"));
                caja.setFechaCierre(rs.getTimestamp("fecha_cierre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return caja;
    }

    // Metodo para obtener todas las cajas
    public List<Caja> obtenerTodasLasCajas() {
        List<Caja> cajas = new ArrayList<>();
        String sql = "SELECT * FROM cajas";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Caja caja = new Caja();
                caja.setId(rs.getInt("id"));
                caja.setIdentificador(rs.getString("identificador"));
                caja.setEstado(rs.getString("estado"));
                caja.setSaldoInicial(rs.getBigDecimal("saldo_inicial"));
                caja.setSaldoFinal(rs.getBigDecimal("saldo_final"));
                caja.setFechaApertura(rs.getTimestamp("fecha_apertura"));
                caja.setFechaCierre(rs.getTimestamp("fecha_cierre"));

                cajas.add(caja);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cajas;
    }


    // Metodo para obtener todas las cajas por estado
    public List<Caja> obtenerCajasPorEstado(String estado) {
        List<Caja> cajas = new ArrayList<>();
        String sql = "SELECT * FROM cajas WHERE estado = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estado);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Caja caja = new Caja();
                caja.setId(rs.getInt("id"));
                caja.setIdentificador(rs.getString("identificador"));
                caja.setEstado(rs.getString("estado"));
                caja.setSaldoInicial(rs.getBigDecimal("saldo_inicial"));
                caja.setSaldoFinal(rs.getBigDecimal("saldo_final"));
                caja.setFechaApertura(rs.getTimestamp("fecha_apertura"));
                caja.setFechaCierre(rs.getTimestamp("fecha_cierre"));
                cajas.add(caja);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cajas;
    }


    // Metodo para actualizar la información de una caja
    public void actualizarCaja(Caja caja) {
        String sql = "UPDATE cajas SET estado = ?, fecha_apertura = ?, saldo_final = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, caja.getEstado());
            pstmt.setTimestamp(2, caja.getFechaApertura());
            pstmt.setBigDecimal(3, caja.getSaldoFinal());
            pstmt.setInt(4, caja.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
