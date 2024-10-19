package com.mycompany.proyectocaja.servlet;

import com.mycompany.proyectocaja.dao.CajaDAO;
import com.mycompany.proyectocaja.model.Caja;
import com.mycompany.proyectocaja.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/asignarCaja")
public class AsignarCajaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CajaDAO cajaDAO;

    @Override
    public void init() {
        cajaDAO = new CajaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Caja> cajasAperturadas = cajaDAO.obtenerCajasPorEstado("aperturada");
        request.setAttribute("cajasAperturadas", cajasAperturadas);
        request.getRequestDispatcher("asignarCaja.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // obtiene el ID de la caja seleccionada
        int cajaId = Integer.parseInt(request.getParameter("cajaId"));

        // verifica la disponibilidad de la caja
        Caja caja = cajaDAO.obtenerCajaPorId(cajaId);
        if (caja == null || !"aperturada".equals(caja.getEstado())) {
            request.setAttribute("mensajeError", "La caja seleccionada no está disponible.");
            doGet(request, response); // muestra de nuevo el formulario
            return;
        }

        // obtiene el usuario que ha iniciado sesión
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("iniciarSesion.jsp");
            return;
        }

        caja.setCajeroId(usuario.getId());
        caja.setEstado("ocupada");
        cajaDAO.actualizarCaja(caja);

//        request.setAttribute("mensajeExito", "Caja asignada exitosamente al cajero.");
        request.getSession().setAttribute("mensajeExito", "Caja asignada exitosamente al cajero..");
        response.sendRedirect("asignarCaja");
    }
}
