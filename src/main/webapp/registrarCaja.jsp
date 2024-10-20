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
    <title>Administración de Cajas</title>
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
        <span class="text">Administración de Cajas</span>
    </div>
    <div id="content-area" class="container mt-5">

        <!-- Botón para abrir el modal de registrar caja -->
        <button class="btn btn-primary mb-3" data-toggle="modal" data-target="#registrarCajaModal">Registrar Caja
        </button>

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


        <h4>Cajas Creadas</h4>
        <!-- Tabla de Cajas Registradas -->
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Identificador</th>
                <th>Estado</th>
                <th>Saldo Inicial</th>
                <th>Saldo Final</th>
                <th>Fecha Apertura</th>
                <th>Fecha Cierre</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="caja" items="${cajas}">
                <tr>
                    <td>${caja.id}</td>
                    <td>${caja.identificador}</td>
                    <td>${caja.estado}</td>
                    <td>${caja.saldoInicial}</td>
                    <td>${caja.saldoFinal}</td>
                    <td>${caja.fechaApertura}</td>
                    <td>${caja.fechaCierre}</td>
                    <td>
                        <!-- Aquí puedes agregar botones para editar o eliminar la caja -->
                            <%--            <button class="btn btn-warning">Editar</button>--%>
                            <%--            <button class="btn btn-danger">Eliminar</button>--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Modal para registrar nueva caja -->
        <div class="modal fade" id="registrarCajaModal" tabindex="-1" role="dialog"
             aria-labelledby="registrarCajaModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="registrarCajaModalLabel">Registrar Nueva Caja</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="post" action="registrarCaja">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="identificador">Identificador:</label>
                                <input type="text" class="form-control" id="identificador" name="identificador"
                                       required>
                            </div>

                            <div class="form-group">
                                <label for="estado">Estado:</label>
                                <select class="form-control" id="estado" name="estado">
                                    <option value="disponible">Disponible</option>
                                    <%--                  <option value="ocupada">Ocupada</option>--%>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="saldoInicial">Saldo Inicial:</label>
                                <input type="number" class="form-control" id="saldoInicial" name="saldoInicial"
                                       step="0.01" value="0.00" required>
                            </div>

                            <div class="form-group">
                                <label for="saldoFinal">Saldo Final:</label>
                                <input type="number" class="form-control" id="saldoFinal" name="saldoFinal" step="0.01"
                                       value="0.00">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-primary">Registrar Caja</button>
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
