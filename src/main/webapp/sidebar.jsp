<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
    // verifica si el usuario ha iniciado sesión
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("iniciarSesion.jsp"); // redirige a la página de inicio de sesion
        return; // no continua ejecutandose
    }
%>
<div class="sidebar">
    <div class="logo-details">
        <i class='bx bxl-c-plus-plus'></i>
        <span class="logo_name">Sistema Caja</span>
    </div>
    <ul class="nav-links">
        <c:if test="${usuario.rol.nombre == 'administrador'}">
            <li>
                <a href="administracionUsuarios">
                    <i class='bx bx-user'></i>
                    <span class="link_name">Administración de usuarios</span>
                </a>
            </li>
        </c:if>
        <li>
            <div class="iocn-link">
                <a href="#">
                    <i class='bx bx-box'></i>
                    <span class="link_name">Caja</span>
                </a>
                <i class='bx bxs-chevron-down arrow'></i>
            </div>
            <ul class="sub-menu">
                <li><a href="registrarCaja" class="nav_link nav_sub_link">Registro de Cajas</a></li>
                <li><a href="aperturaCaja" class="nav_link nav_sub_link">Apertura de Caja</a></li>
                <li><a href="asignarCaja" class="nav_link nav_sub_link">Asignación de Caja</a></li>
                <li><a href="arqueoCaja" class="nav_link nav_sub_link">Arqueo de Caja</a></li>
                <li><a href="cierreCaja" class="nav_link nav_sub_link">Cierre de Caja</a></li>
                <li><a href="registroEgresos" class="nav_link nav_sub_link">Registro de Egresos</a></li>
                <li><a href="operacionesCaja" class="nav_link nav_sub_link">Operaciones de Caja</a></li>
            </ul>
        </li>
    </ul>
    <div class="profile-details">
        <div class="profile-content">
            <img src="https://i.imgur.com/hczKIze.jpg" alt="profileImg">
        </div>
        <div class="name-job">
            <div class="profile_name">${usuario.nombreCompleto}</div>
            <div class="job">${usuario.rol.nombre}</div>
            <div class="cerrar-sesion">
                <a href="cerrarSesion">Cerrar sesión</a>
            </div>
        </div>
    </div>
</div>




