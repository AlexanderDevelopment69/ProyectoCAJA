package com.mycompany.proyectocaja.servlet;

import com.mycompany.proyectocaja.dao.CajaDAO;
import com.mycompany.proyectocaja.model.Caja;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/aperturaCaja")
public class AperturaCajaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CajaDAO cajaDAO;

    @Override
    public void init() {
        cajaDAO = new CajaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Caja> cajasDisponibles = cajaDAO.obtenerCajasPorEstado("disponible");
        request.setAttribute("cajasDisponibles", cajasDisponibles);
        request.getRequestDispatcher("aperturaCaja.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // obtiene el ID de la caja seleccionada
        int cajaId = Integer.parseInt(request.getParameter("cajaId"));

        // verifica la disponibilidad de la caja
        Caja caja = cajaDAO.obtenerCajaPorId(cajaId);
        if (caja == null || !"disponible".equals(caja.getEstado())) {
            request.setAttribute("mensajeError", "La caja seleccionada no está disponible.");
            doGet(request, response); // muestra de nuevo el formulario
            return;
        }

        // actualiza el estado de la caja y registra la fecha de apertura
        caja.setEstado("aperturada");
        caja.setFechaApertura(new Timestamp(System.currentTimeMillis()));
        cajaDAO.actualizarCaja(caja);

        // redirige a la lista de cajas
//        request.setAttribute("mensajeExito", "Caja abierta exitosamente.");
        request.getSession().setAttribute("mensajeExito", "Caja abierta exitosamente.");
        response.sendRedirect("aperturaCaja");
    }
}
