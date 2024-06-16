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
        <title>Productos</title>
    </head>
    <body class="parent-container">
        <jsp:include page="components/navegadorAdm.jsp"/>
        <%
            Boolean redireccionado = (Boolean) session.getAttribute("redireccionado");
            if (redireccionado == null || !redireccionado) {
                response.sendRedirect(request.getContextPath() + "/cntAdmProductos?accion=AdmProductos");

                session.setAttribute("redireccionado", true);
            }
        %>
        <script src="resources/scrip/AdmPng.js" type="text/javascript"></script>
        <div class="box-content">
            <div class="container">
                <main class="main-content">
                    <header class="header">
                        <h1>Gestión de Productos</h1>
                    </header>
                    <section class="dashboard">
                        <div class="agregar-prod">
                            <form action="<%= request.getContextPath()%>./cntAdmProductos" method="post" class="formulario" id="formProductos">
                                <fieldset>
                                    <legend>Registro de Productos</legend>
                                    <label for="nombre" class="etiqueta">(*)Nombre</label>
                                    <input type="text" name="nombre" value="${nombre}" placeholder="Nuevo Producto"/>
                                    <label for="precio" class="etiqueta">(*)Precio(s/.)</label>
                                    <input type="number" name="precio" id="precio" value="${precio}" placeholder="00.00">
                                    <label for="fecha" class="etiqueta">Fecha de Vencimiento</label>
                                    <input type="text" name="fecha" value="${fecha}" placeholder="YYYY-MM-DD">
                                    <label for="categoria" class="etiqueta">(*)Categoria</label>
                                    <div class="combo-cat">
                                        <select id="categoria" name="cboCategoria">
                                            <c:forEach var="itemCategoria" items="${listaCategoria}">
                                                <option value="${itemCategoria.codCategoria}"  ${categoria == itemCategoria.codCategoria ? "selected" : ""} >${itemCategoria.nombre}</option>                        
                                            </c:forEach>
                                        </select>    
                                        <i></i>
                                    </div>
                                    <label for="proveedor" class="etiqueta">(*)Proveedor</label>
                                    <div class="combo-prov">
                                        <select>
                                            <c:forEach var="itemProveedor" items="${listaProveedor}">
                                                <option value="${itemProveedor.codProveedor}"  ${proveedor == itemProveedor.codProveedor ? "selected" : ""} >${itemProveedor.nombre}</option>                        
                                            </c:forEach>
                                        </select>    
                                        <i></i>
                                    </div>
                                    <label class="nota">No olvide que los campos con (*) son <b>obligatorios</b></label>
                                    <input type="submit" value="Registrar" name="accion" class="etiqueta"/>                
                                </fieldset>

                                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
                                <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
                            </form>
                        </div>
                    </section>
                    <!--
                    <section class="dashboard">
                        <div class="stats">
                            <div class="card">
                                <h3>Total de Productos</h3>
                                <p>50</p>
                            </div>
                            <div class="card">
                                <h3>Productos en Stock</h3>
                                <p>45</p>
                            </div>
                            <div class="card">
                                <h3>Productos Vendidos Hoy</h3>
                                <p>5</p>
                            </div>
                        </div>
                        <div class="details">
                            <h2>Detalles de Productos</h2>
                            <button class="btn-add" onclick="showAddProductForm()">Agregar Producto</button>
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nombre</th>
                                        <th>Precio</th>
                                        <th>Cantidad en Stock</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>Bloqueador solar</td>
                                        <td>s/10.00</td>
                                        <td>20</td>
                                        <td>
                                            <button class="btn-edit" onclick="showEditProductForm(1)">Editar</button>
                                            <button class="btn-delete" onclick="deleteProduct(1)">Eliminar</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Mikes</td>
                                        <td>s/15.00</td>
                                        <td>10</td>
                                        <td>
                                            <button class="btn-edit" onclick="showEditProductForm(2)">Editar</button>
                                            <button class="btn-delete" onclick="deleteProduct(2)">Eliminar</button>
                                        </td>
                                    </tr>
                                    <!-- Más filas aquí 
                                </tbody>
                            </table>
                        </div>
                        <!-- Formulario para agregar productos 
                        <div id="addProductForm" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeAddProductForm()">&times;</span>
                                <h2>Agregar Producto</h2>
                                <form>
                                    <label for="nombre">Nombre</label>
                                    <input type="text" id="nombre" name="nombre" required>
                                    <label for="precio">Precio</label>
                                    <input type="text" id="precio" name="precio" required>
                                    <label for="cantidad">Cantidad en Stock</label>
                                    <input type="text" id="cantidad" name="cantidad" required>
                                    <button type="submit">Agregar</button>
                                </form>
                            </div>
                        </div>
                        <!-- Formulario para editar productos 
                        <div id="editProductForm" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeEditProductForm()">&times;</span>
                                <h2>Editar Producto</h2>
                                <form>
                                    <input type="hidden" id="edit-product-id" name="product-id">
                                    <label for="edit-nombre">Nombre</label>
                                    <input type="text" id="edit-nombre" name="nombre" required>
                                    <label for="edit-precio">Precio</label>
                                    <input type="text" id="edit-precio" name="precio" required>
                                    <label for="edit-cantidad">Cantidad en Stock</label>
                                    <input type="text" id="edit-cantidad" name="cantidad" required>
                                    <button type="submit">Guardar Cambios</button>
                                </form>
                            </div>
                        </div>
                    </section>
                    -->
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

        </div>
    </body>
</html>
