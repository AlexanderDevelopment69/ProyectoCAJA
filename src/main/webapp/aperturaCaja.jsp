<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
    // verifica si el usuario ha iniciado sesion
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("iniciarSesion.jsp"); // redirige a la pagina de inicio de sesion
        return; // no continua ejecutandose
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Apertura de Cajas</title>
    <link rel="stylesheet" href="css/main.css"> <!-- Enlace al archivo CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"> <!-- Boxicons -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> <!-- CSS de Bootstrap -->

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="sidebar.jsp"/> <!-- Incluir el sidebar aquí -->

<section class="home-section p-3">
    <div class="home-content">
        <i class='bx bx-menu'></i>
        <span class="text">Apertura de Cajas</span>
    </div>
    <div id="content-area" class="container mt-5">

<%--        <c:if test="${not empty mensajeExito}">--%>
<%--            <div class="alert alert-success">--%>
<%--                    ${mensajeExito}--%>
<%--            </div>--%>
<%--        </c:if>--%>
<%--        <c:if test="${not empty mensajeError}">--%>
<%--            <div class="alert alert-danger">--%>
<%--                    ${mensajeError}--%>
<%--            </div>--%>
<%--        </c:if>--%>

        <c:if test="${not empty sessionScope.mensajeExito}">
            <div class="alert alert-success">
                    ${sessionScope.mensajeExito}
            </div>
            <c:remove var="mensajeExito" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.mensajeError}">
            <div class="alert alert-danger">
                    ${sessionScope.mensajeError}
            </div>
            <c:remove var="mensajeError" scope="session"/>
        </c:if>


        <h4>Cajas Disponibles</h4>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Identificador</th>
                <th>Estado</th>
                <th>Saldo Inicial</th>
                <th>Saldo Final</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="caja" items="${cajasDisponibles}">
                <tr>
                    <td>${caja.id}</td>
                    <td>${caja.identificador}</td>
                    <td>${caja.estado}</td>
                    <td>${caja.saldoInicial}</td>
                    <td>${caja.saldoFinal}</td>
                    <td>
                        <button class="btn btn-success" data-toggle="modal" data-target="#abrirCajaModal" onclick="document.getElementById('cajaId').value='${caja.id}'; document.getElementById('cajaNombre').textContent='${caja.identificador}';">Abrir Caja</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Modal para confirmar la apertura de caja -->
        <div class="modal fade" id="abrirCajaModal" tabindex="-1" role="dialog" aria-labelledby="abrirCajaModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="abrirCajaModalLabel">Confirmar Apertura de Caja</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="post" action="aperturaCaja">
                        <div class="modal-body">
                            <p>¿Está seguro de que desea abrir la caja <strong id="cajaNombre"></strong>?</p>
                            <input type="hidden" id="cajaId" name="cajaId" value="">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-success">Aperturar Caja</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</section>

<script src="js/main.js"></script>

</body>
</html>
