<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Restablecer Contraseña</title>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="w-100" style="max-width: 400px;">
        <h2 class="text-center">Restablecer Contraseña</h2>

        <!-- Mostrar alerta de error si hay un problema -->
        <c:if test="${not empty mensajeError}">
            <div class="alert alert-danger" role="alert">
                    ${mensajeError}
            </div>
        </c:if>

        <!-- Mostrar alerta de éxito si la contraseña fue restablecida -->
        <c:if test="${not empty mensajeExito}">
            <div class="alert alert-success" role="alert">
                    ${mensajeExito}
            </div>
        </c:if>

        <form action="restablecerContrasena" method="post">
            <div class="form-group">
                <label for="username">Nombre de usuario</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="email">Correo electrónico</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="nuevaContrasena">Nueva contraseña</label>
                <input type="password" class="form-control" id="nuevaContrasena" name="nuevaContrasena" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Restablecer Contraseña</button>
        </form>

        <p class="text-center mt-3">
            <a href="iniciarSesion">Volver a iniciar sesión</a>
        </p>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
