package com.mycompany.proyectocaja.servlet;

import com.mycompany.proyectocaja.dao.UsuarioDAO;
import com.mycompany.proyectocaja.dao.RolDAO;
import com.mycompany.proyectocaja.model.Usuario;
import com.mycompany.proyectocaja.model.Rol;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
        rolDAO = new RolDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // obtiene los roles disponibles para mostrarlos en el formulario de registro
        List<Rol> roles = rolDAO.obtenerTodosLosRoles();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("registrarUsuario.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // obtiene los parAmetros del formulario de registro
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombreCompleto = request.getParameter("nombreCompleto");
        String email = request.getParameter("email");
        int idRol = Integer.parseInt(request.getParameter("rol"));

        // verifica si ya existe un usuario con el mismo nombre de usuario
        Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorUsername(username);
        if (usuarioExistente != null) {
            // si ya existe, redirige al formulario de registro con un mensaje de error
            request.setAttribute("mensajeError", "El nombre de usuario ya está en uso.");
            doGet(request, response);
            return;
        }

        // crea un objeto Usuario y establece los valores
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setNombreCompleto(nombreCompleto);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setEstado(false); // estado por defecto 0 (inactivo)

        // obtiene el rol seleccionado y lo asigna al usuario
        Rol rol = rolDAO.obtenerRolPorId(idRol);
        nuevoUsuario.setRol(rol);

        // registra el usuario en la base de datos
        usuarioDAO.registrarUsuario(nuevoUsuario);

        // redirige al formulario de registro con un mensaje de exito
        request.setAttribute("mensajeExito", "Usuario registrado exitosamente.");
        doGet(request, response);
    }
}
