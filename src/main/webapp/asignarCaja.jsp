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
    <title>Asignación de Caja</title>
    <link rel="stylesheet" href="css/main.css"> <!-- Enlace al archivo CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"> <!-- Boxicons -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- CSS de Bootstrap -->

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="sidebar.jsp"/> <!-- Incluir el sidebar aquí -->

<section class="home-section p-3">
    <div class="home-content">
        <i class='bx bx-menu'></i>
        <span class="text">Asignación de Caja</span>
    </div>
    <div class="container mt-5">

        <%--    <c:if test="${not empty mensajeExito}">--%>
        <%--      <div class="alert alert-success">--%>
        <%--          ${mensajeExito}--%>
        <%--      </div>--%>
        <%--    </c:if>--%>
        <%--    <c:if test="${not empty mensajeError}">--%>
        <%--      <div class="alert alert-danger">--%>
        <%--          ${mensajeError}--%>
        <%--      </div>--%>
        <%--    </c:if>--%>

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


        <h4>Cajas Aperturadas</h4>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Identificador</th>
                <th>Estado</th>
                <th>Saldo Inicial</th>
                <th>Saldo Final</th>
                <th>Fecha Apertura</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="caja" items="${cajasAperturadas}">
                <tr>
                    <td>${caja.id}</td>
                    <td>${caja.identificador}</td>
                    <td>${caja.estado}</td>
                    <td>${caja.saldoInicial}</td>
                    <td>${caja.saldoFinal}</td>
                    <td>${caja.fechaApertura}</td>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#confirmModal${caja.id}">Asignarme
                        </button>

                        <!-- Modal de Confirmación -->
                        <div class="modal fade" id="confirmModal${caja.id}" tabindex="-1" role="dialog"
                             aria-labelledby="confirmModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="confirmModalLabel">Confirmar Asignación</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        ¿Está seguro de que desea asignar esta caja?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar
                                        </button>
                                        <form method="post" action="asignarCaja">
                                            <input type="hidden" name="cajaId" value="${caja.id}">
                                            <button type="submit" class="btn btn-primary">Confirmar</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</section>

<script src="js/main.js"></script>
</body>
</html>
