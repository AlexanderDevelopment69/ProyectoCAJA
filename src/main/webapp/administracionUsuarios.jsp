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
    <title>Administración de Usuarios</title>
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
        <span class="text">Administracion de Usuarios</span>
    </div>
    <div id="content-area" class="container mt-5">

        <!-- Mensajes de éxito -->
        <c:if test="${not empty param.success}">
            <div class="alert alert-success">Usuario actualizado correctamente.</div>
        </c:if>
        <c:if test="${not empty param.deleted}">
            <div class="alert alert-danger">Usuario eliminado correctamente.</div>
        </c:if>
        <h4>Usuarios Registrados</h4>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre Completo</th>
                <th>Email</th>
                <th>Rol</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td>${usuario.id}</td>
                    <td>${usuario.nombreCompleto}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.rol.nombre}</td>
                    <td>${usuario.estado ? 'Activo' : 'Inactivo'}</td>
                    <td>
                        <button class="btn btn-warning" data-toggle="modal" data-target="#editModal${usuario.id}">Editar</button>
                        <button class="btn btn-danger" data-toggle="modal" data-target="#confirmDeleteModal${usuario.id}">Eliminar</button>
                    </td>
                </tr>

                <!-- Modal para editar usuario -->
                <div class="modal fade" id="editModal${usuario.id}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel${usuario.id}" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="editModalLabel${usuario.id}">Editar Usuario</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form method="post" action="administracionUsuarios">
                                <div class="modal-body">
                                    <input type="hidden" name="id" value="${usuario.id}">
                                    <div class="form-group">
                                        <%--@declare id="nombrecompleto"--%><label for="nombreCompleto">Nombre Completo</label>
                                        <input type="text" class="form-control" name="nombreCompleto" value="${usuario.nombreCompleto}" required>
                                    </div>
                                    <div class="form-group">
                                        <%--@declare id="email"--%><label for="email">Email</label>
                                        <input type="email" class="form-control" name="email" value="${usuario.email}" required>
                                    </div>
                                    <div class="form-group">
                                        <%--@declare id="rol"--%><label for="rol">Rol</label>
                                        <select class="form-control" name="rol" required>
                                            <c:forEach var="rol" items="${roles}">
                                                <option value="${rol.id}" ${usuario.rol.id == rol.id ? 'selected' : ''}>${rol.nombre}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <%--@declare id="estado"--%><label for="estado">Estado</label>
                                        <select class="form-control" name="estado">
                                            <option value="true" ${usuario.estado ? 'selected' : ''}>Activo</option>
                                            <option value="false" ${!usuario.estado ? 'selected' : ''}>Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-primary" name="action" value="update">Actualizar Usuario</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Modal para confirmar eliminación -->
                <div class="modal fade" id="confirmDeleteModal${usuario.id}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel${usuario.id}" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="confirmDeleteModalLabel${usuario.id}">Confirmar Eliminación</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <p>¿Estás seguro de que deseas eliminar a ${usuario.nombreCompleto}?</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                <form method="post" action="administracionUsuarios" style="display:inline;">
                                    <input type="hidden" name="id" value="${usuario.id}">
                                    <button type="submit" class="btn btn-danger" name="action" value="delete">Eliminar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<script src="js/main.js"></script>

</body>
</html>
