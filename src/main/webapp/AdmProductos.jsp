<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/AdmProductos.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin-Display.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>Productos</title>
    </head>
    <body class="parent-container-admproductos">
        <jsp:include page="components/navegadorAdm.jsp"/>
        <%
            Boolean redireccionado = (Boolean) session.getAttribute("redireccionado");
            if (redireccionado == null || !redireccionado) {
                response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");

                session.setAttribute("redireccionado", true);
            }
        %>
        <script src="resources/scrip/AdmPng.js" type="text/javascript"></script>
        <div class="box-content-admproductos">
            <header class="header-admproductos">
                <h1>Gestión de Productos</h1>
            </header>
            <div class="container-admproductos">
                <main class="main-content-admproductos">
                    <div class="registrar-productos">
                        <div class="form-prod-admproductos">
                            <button type="button" class="btn-agregarprod btn-primary mt-2 mb-4" data-bs-toggle="modal" data-bs-target="#exampleModal">
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
                                        <form action="<%= request.getContextPath()%>./cntAdmProductos" method="post" class="formulario-admproductos" id="formProductos">
                                            <fieldset class="campos-admproductos">
                                                <br>
                                                <label for="nombre" class="etiqueta-admproductos">(*)Nombre</label>
                                                <input type="text" name="nombre" value="${nombre}" placeholder="Nuevo Producto" class="entrada-admproductos"/>
                                                <label for="precio" class="etiqueta-admproductos">(*)Precio(s/.)</label>
                                                <input type="number" name="precio" id="precio" value="${precio}" placeholder="00.00" class="entrada-admproductos">
                                                <label for="fecha" class="etiqueta-admproductos">Fecha de Vencimiento</label>
                                                <input type="date" name="fecha" value="${fecha}" placeholder="DD-MM-YYYY" class="entrada-admproductos">
                                                <label for="nombre" class="etiqueta-admproductos">(*)Descripción</label>
                                                <input type="text" name="descripcion" value="${descripcion}" placeholder="Insertar descripción" class="entrada-admproductos"/>
                                                <label for="categoria" class="etiqueta-admproductos">(*)Categoria</label>
                                                <div class="combo-cat">
                                                    <select id="categoria" name="cboCategoria" class="cbx-admproductos">
                                                        <c:forEach var="itemCategoria" items="${listaCategoria}">
                                                            <option value="${itemCategoria.codCategoria}"  ${categoria == itemCategoria.codCategoria ? "selected" : ""} >${itemCategoria.nombre}</option>                        
                                                        </c:forEach>
                                                    </select>    
                                                    <i></i>
                                                </div>
                                                <label for="proveedor" class="etiqueta-admproductos">(*)Proveedor</label>
                                                <div class="combo-prov">
                                                    <select id="proveedor" name="cboProveedor" class="cbx-admproductos">
                                                        <c:forEach var="itemProveedor" items="${listaProveedor}">
                                                            <option value="${itemProveedor.codProveedor}"  ${proveedor == itemProveedor.codProveedor ? "selected" : ""} >${itemProveedor.nombre}</option>                        
                                                        </c:forEach>
                                                    </select>    
                                                    <i></i>
                                                </div>
                                                <label class="nota">No olvide que los campos con (*) son <b>obligatorios</b></label>
                                                <input type="submit" value="Registrar" name="accion" class="button-admproductos"/>                
                                            </fieldset>

                                            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
                                            <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="mostrar-productos">
                        
                    </div>
                </main>
            </div>
            <script>
                function showAddProductForm() {
                    document.getElementById('addProductForm').style.display = 'block';
                }

                function closeAddProductForm() {
                    document.getElementById('addProductForm').style.display = 'none';
                }

                function showEditProductForm(id) {
                    // Aquí puedes agregar lógica para cargar los datos del producto a editar
                    document.getElementById('editProductForm').style.display = 'block';
                    document.getElementById('edit-product-id').value = id;
                }

                function closeEditProductForm() {
                    document.getElementById('editProductForm').style.display = 'none';
                }

                function deleteProduct(id) {
                    // Aquí puedes agregar lógica para eliminar el producto
                    alert('Producto ' + id + ' eliminado');
                }
            </script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        </div>
    </body>
</html>
