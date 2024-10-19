package com.mycompany.proyectocaja.servlet;

import com.mycompany.proyectocaja.dao.UsuarioDAO;
import com.mycompany.proyectocaja.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/iniciarSesion")
public class IniciarSesionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // redirige a la página de inicio de sesion
        request.getRequestDispatcher("iniciarSesion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parametros del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // intenta iniciar sesion
        Usuario usuario = usuarioDAO.iniciarSesion(username, password);

        if (usuario != null) {
            // si las credenciales son correctas y el usuario esta activo
            if (usuario.isEstado()) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario); // almacena el usuario en la sesión
                response.sendRedirect("inicio.jsp"); // redirige al inicio
            } else {
                // si el usuario está inactivo
                response.sendRedirect("iniciarSesion.jsp?error=Usuario%20Inactivo");
            }
        } else {
            // si las credenciales son incorrectas
            response.sendRedirect("iniciarSesion.jsp?error=Credenciales%20incorrectas");
        }
    }

}
