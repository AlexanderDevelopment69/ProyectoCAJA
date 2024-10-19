<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
    // Verifica si el usuario ha iniciado sesión
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("iniciarSesion.jsp"); // Redirige a la página de inicio de sesión
        return; // Asegúrate de que el código no continúe ejecutándose
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio</title>
    <link rel="stylesheet" href="css/main.css"> <!-- Enlace al archivo CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"> <!-- Boxicons -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> <!-- CSS de Bootstrap -->

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="sidebar.jsp" /> <!-- Incluir el sidebar aquí -->

<section class="home-section p-3">
    <div class="home-content">
        <i class='bx bx-menu'></i>
        <span class="text">Bienvenido al Sistema</span>
    </div>
    <div id="content-area">
        <p class="text-center">¡Hola, ${usuario.nombreCompleto}!</p>
    </div>
</section>

<script src="js/main.js"></script>
</body>
</html>
