package com.mycompany.proyectocaja.servlet;

import com.mycompany.proyectocaja.dao.CajaDAO;
import com.mycompany.proyectocaja.model.Caja;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/registrarCaja")
public class RegistrarCajaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CajaDAO cajaDAO;

    @Override
    public void init() {
        cajaDAO = new CajaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // obtiene todas las cajas registradas y las pasa al JSP
        List<Caja> cajas = cajaDAO.obtenerTodasLasCajas();
        request.setAttribute("cajas", cajas);
        request.getRequestDispatcher("registrarCaja.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // obtiene los parámetros del formulario de registro de caja
        String identificador = request.getParameter("identificador");
        String estado = request.getParameter("estado");


        BigDecimal saldoInicial = new BigDecimal(request.getParameter("saldoInicial"));
        BigDecimal saldoFinal = new BigDecimal(request.getParameter("saldoFinal"));

        // crea un objeto Caja y establece los valores
        Caja nuevaCaja = new Caja();
        nuevaCaja.setIdentificador(identificador);
        nuevaCaja.setEstado(estado);
        nuevaCaja.setSaldoInicial(saldoInicial);
        nuevaCaja.setSaldoFinal(saldoFinal);

        // registra la nueva caja en la base de datos
        cajaDAO.registrarCaja(nuevaCaja);
        request.getSession().setAttribute("mensajeExito", "Caja registrada exitosamente.");
        // redirige a la lista de cajas
        response.sendRedirect("registrarCaja");
    }
}

