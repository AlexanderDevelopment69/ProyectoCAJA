<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    <title>Iniciar Sesión</title>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="w-100" style="max-width: 400px;">
        <h2 class="text-center">Iniciar Sesión</h2>

        <!-- Mostrar alerta de error si hay un problema con las credenciales -->
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger" role="alert">
                    ${param.error}
            </div>
        </c:if>

        <form action="iniciarSesion" method="post">
            <div class="form-group">
                <label for="username">Nombre de usuario</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Iniciar Sesión</button>
        </form>

        <div class="text-center mt-3">
            <a href="restablecerContrasena">¿Olvidaste tu contraseña?</a>
        </div>

        <div class="text-center mt-2">
            <p>¿No tienes cuenta? <a href="registrarUsuario">Regístrate aquí</a></p>
        </div>
    </div>
</div>


</body>
</html>
