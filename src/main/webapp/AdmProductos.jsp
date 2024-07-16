<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="ServLets.cntAdmProductos"%>
<%@page import="modelo.dao.ProductoDAO"%>
<%@page import="modelo.dto.Producto"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/AdmProductos.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin-Display.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>Productos</title>
    </head>
    <body class="parent-container-admproductos">
        <jsp:include page="components/navegadorAdm.jsp"/>
        <script src="resources/scrip/AdmPng.js" type="text/javascript"></script>
        <div class="box-content-admproductos">
            <header class="header-admproductos">
                <h1>Gestión de Productos</h1>
            </header>
            <div class="container-admproductos">
                <main class="main-content-admproductos">
                    <br>
                    <div class="registrar-productos">
                        <div class="form-prod-admproductos">
                            <button type="button" class="btn-agregarprod btn-primary mt-2 mb-4" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="setAgregarContext()">
                                <i class="fa-sharp fa-regular fa-file"></i>
                                Agregar Nuevo Producto
                            </button>
                        </div>
                        <div class="modal fade" id="exampleModal" tabindex="1" aria labellebdy="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="contenido-registrar-admproductos">
                                        <div class="modal-header">
                                            <h1 class="modal-tittle fs-5" id="exampleModalLabel">Registro de Productos</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form action="${pageContext.request.contextPath}/cntAdmProductos" method="post" class="formulario-admproductos" id="formProductos">
                                            <fieldset class="campos-admproductos">
                                                <br>
                                                <label for="nombre" class="etiqueta-admproductos">(*)Nombre</label>
                                                <input type="text" name="nombre" value="${nombre}" placeholder="Nuevo Producto" class="entrada-admproductos" id="nombre"/>

                                                <label for="descripcion" class="etiqueta-admproductos">(*)Descripción</label>
                                                <input type="text" name="descripcion" value="${descripcion}" placeholder="Insertar descripción" class="entrada-admproductos" id="descripcion"/>

                                                <label for="fechavencimiento" class="etiqueta-admproductos">Fecha de Vencimiento</label>
                                                <input type="date" name="fechavencimiento" value="${fechavencimiento}" placeholder="DD-MM-YYYY" class="entrada-admproductos" id="fechavencimiento">

                                                <label for="precio" class="etiqueta-admproductos">(*)Precio(s/.)</label>
                                                <input type="number" name="precio" id="precio" step="0.01" value="${precio}" placeholder="00.00" class="entrada-admproductos" id="precio">

                                                <label for="categoria" class="etiqueta-admproductos">(*)Categoria</label>
                                                <div class="combo-cat">
                                                    <select id="categoria" name="cboCategoria" class="cbx-admproductos">
                                                        <c:forEach var="itemCategoria" items="${listaCategoria}">
                                                            <option value="${itemCategoria.codCategoria}" ${categoria == itemCategoria.codCategoria ? "selected" : ""}>${itemCategoria.nombre}</option>                        
                                                        </c:forEach>
                                                    </select>    
                                                    <i></i>
                                                </div>

                                                <label for="proveedor" class="etiqueta-admproductos">(*)Proveedor</label>
                                                <div class="combo-prov">
                                                    <select id="proveedor" name="cboProveedor" class="cbx-admproductos">
                                                        <c:forEach var="itemProveedor" items="${listaProveedor}">
                                                            <option value="${itemProveedor.codProveedor}" ${proveedor == itemProveedor.codProveedor ? "selected" : ""}>${itemProveedor.nombre}</option>                        
                                                        </c:forEach>
                                                    </select>    
                                                    <i></i>
                                                </div>
                                                <input type="hidden" name="codproducto" value="${codproducto}" />
                                                <label class="nota">No olvide que los campos con (*) son <b>obligatorios</b></label>
                                                <input type="submit" value="Registrar" name="accion" class="button-admproductos" id="btnRegistrar" />
                                                <input type="submit" value="Actualizar" name="accion" class="button-admproductos" id="btnActualizar" />
                                            </fieldset>

                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="mostrar-productos">
                        <div class="contenedor-tabla-admproductos">
                            <table class="tabla-admproductos">
                                <thead class="cabeza-tabla-admproductos">
                                    <tr class="fila-cabeza-tabla-admproductos"">
                                        <td class="cuadro-cabeza-tabla-admproductos">Código</td>
                                        <td class="cuadro-cabeza-tabla-admproductos">Nombre</td>
                                        <td class="cuadro-cabeza-tabla-admproductos">Descripción</td>
                                        <td class="cuadro-cabeza-tabla-admproductos">Fecha de Vencimiento</td>
                                        <td class="cuadro-cabeza-tabla-admproductos">Precio</td>
                                        <td class="cuadro-cabeza-tabla-admproductos">Categoría</td>
                                        <td class="cuadro-cabeza-tabla-admproductos">Proveedor</td>
                                        <td class="cuadro-cabeza-tabla-admproductos">Acciones</td>
                                    </tr>
                                </thead>
                                <tbody class="cuerpo-tabla-admproductos">
                                    <c:forEach var="producto" items="${listaProducto}">
                                        <tr class="fila-cuerpo-tabla-admproductos">
                                            <td class="cuadro-cuerpo-tabla-admproductos">${producto.codproducto}</td>
                                            <td class="cuadro-cuerpo-tabla-admproductos">${producto.nombre}</td>
                                            <td class="cuadro-cuerpo-tabla-admproductos">${producto.descripcion}</td>
                                            <td class="cuadro-cuerpo-tabla-admproductos">${producto.fechavencimiento}</td>
                                            <td class="cuadro-cuerpo-tabla-admproductos">${producto.precio}</td>
                                            <td class="cuadro-cuerpo-tabla-admproductos">${producto.categoria}</td>
                                            <td class="cuadro-cuerpo-tabla-admproductos">${producto.proveedor}</td>
                                            <td class="acciones-cuerpo-tabla-admproductos">
                                                <a href="<%=request.getContextPath()%>/cntAdmProductos?accion=editar&codigoproducto=${producto.codproducto}" class="btn-editar-admproductos">Editar</a>
                                                <input type="button" value="Borrar" class="btn-eliminar-admproductos">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script>
                                $(document).ready(function () {
                                    // Mostrar mensaje de confirmación si existe
                                    var mensajeConfirmacion = "<%= session.getAttribute("mensajeConfirmacion")%>";
                                    if (mensajeConfirmacion !== "null" && mensajeConfirmacion !== "") {
                                        toastr.success(mensajeConfirmacion, null, {
                                            className: 'toast-success' // Agregar la clase de estilo personalizado
                                        });
                                        // Eliminar el atributo de sesión después de mostrar la notificación
            <% session.removeAttribute("mensajeConfirmacion");%>
                                    }

                                    // Mostrar mensaje de advertencia si existe
                                    var mensajeAdvertencia = "<%= session.getAttribute("mensajeAdvertencia")%>";
                                    if (mensajeAdvertencia !== "null" && mensajeAdvertencia !== "") {
                                        toastr.warning(mensajeAdvertencia);
                                        // Eliminar el atributo de sesión después de mostrar la notificación
            <% session.removeAttribute("mensajeAdvertencia");%>
                                    }

                                    // Mostrar el modal si el atributo showModal está presente
                                    var showModal = "${showModal}";
                                    if (showModal === "true") {
                                        $('#exampleModal').modal('show');
                                    }

                                    // Ocultar o mostrar botones en función del contexto (agregar o editar)
                                    var isEdit = "${isEdit}";
                                    if (isEdit === "true") {
                                        $('#btnRegistrar').hide();
                                        $('#btnActualizar').show();
                                    } else {
                                        $('#btnRegistrar').show();
                                        $('#btnActualizar').hide();
                                    }
                                });
        </script>
        <script>
            function setAgregarContext() {
                $('#btnRegistrar').show();
                $('#btnActualizar').hide();
            }
        </script>
    </body>
</html>
