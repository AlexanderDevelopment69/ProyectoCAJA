package com.mycompany.proyectocaja.servlet;

import com.mycompany.proyectocaja.dao.RolDAO;
import com.mycompany.proyectocaja.dao.UsuarioDAO;
import com.mycompany.proyectocaja.model.Rol;
import com.mycompany.proyectocaja.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/administracionUsuarios")
public class AdministracionUsuariosServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private RolDAO rolDAO = new RolDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
        List<Rol> roles = rolDAO.obtenerTodosLosRoles();
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("administracionUsuarios.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario"); // el usuario que está en sesión

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombreCompleto = request.getParameter("nombreCompleto");
            String email = request.getParameter("email");
            int rolId = Integer.parseInt(request.getParameter("rol"));
            boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNombreCompleto(nombreCompleto);
            usuario.setEmail(email);
            usuario.setRol(new Rol(rolId));
            usuario.setEstado(estado);

            usuarioDAO.actualizarUsuario(usuario, usuarioActual.getId());
            response.sendRedirect("administracionUsuarios?success=true");
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            int idUsuarioActual = usuarioActual.getId();
            usuarioDAO.eliminarUsuario(id, idUsuarioActual);
            response.sendRedirect("administracionUsuarios?deleted=true");
        }

    }
}
