<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Actualizar Información</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Actualizar Información</h2>
    <form action="ActualizarUsuarioServlet" method="post">
        <div class="form-group">
            <label for="nombreCompleto">Nombre Completo:</label>
            <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" value="${usuario.nombreCompleto}" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="${usuario.email}" required>
        </div>
        <button type="submit" class="btn btn-primary">Actualizar</button>
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
    </form>
</div>
</body>
</html>
