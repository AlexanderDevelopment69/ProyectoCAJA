package com.mycompany.proyectocaja.servlet;

import com.mycompany.proyectocaja.dao.UsuarioDAO;
import com.mycompany.proyectocaja.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/restablecerContrasena")
public class RestablecerContrasenaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("restablecerContrasena.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String nuevaContrasena = request.getParameter("nuevaContrasena");

        // verifica si el usuario existe
        Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorUsername(username);

        if (usuarioExistente == null) {
            // si el usuario no existe, muestra un mensaje de error
            request.setAttribute("mensajeError", "El nombre de usuario es incorrecto.");
            request.getRequestDispatcher("restablecerContrasena.jsp").forward(request, response);
            return;
        }

        // verifica que el correo coincida
        if (!usuarioExistente.getEmail().equals(email)) {
            request.setAttribute("mensajeError", "El correo electrónico no coincide con el nombre de usuario.");
            request.getRequestDispatcher("restablecerContrasena.jsp").forward(request, response);
            return;
        }

        // si el usuario existe y el email coincide, se actualiza la contraseña
        if (nuevaContrasena != null && !nuevaContrasena.isEmpty()) {
            usuarioDAO.restablecerContrasena(username, nuevaContrasena);
            request.setAttribute("mensajeExito", "Contraseña restablecida exitosamente.");
        }

        // muestra el formulario nuevamente con el mensaje
        request.getRequestDispatcher("restablecerContrasena.jsp").forward(request, response);
    }
}
