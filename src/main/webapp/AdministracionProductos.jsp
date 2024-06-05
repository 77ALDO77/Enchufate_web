<%-- 
    Document   : AdministracionProductos
    Created on : 4 jun. 2024, 10:12:53 p. m.
    Author     : aldom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestión de Productos</title>
        <link href="resources/css/AdmProductos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <aside class="sidebar">
                <div class="sidebar-header">
                    <h2>Ciber Café</h2>
                </div>
                <nav class="menu">
                    <ul>
                        <li><a href="#">Dashboard</a></li>
                        <li><a href="#">Cubículos</a></li>
                        <li><a href="#">Reservas</a></li>
                        <li><a href="#">Clientes</a></li>
                        <li><a href="#">Productos</a></li>
                        <li><a href="#">Configuración</a></li>
                    </ul>
                </nav>
            </aside>
            <main class="main-content">
                <header class="header">
                    <h1>Gestión de Productos</h1>
                </header>
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
                                    <td>Producto A</td>
                                    <td>$10.00</td>
                                    <td>20</td>
                                    <td>
                                        <button class="btn-edit" onclick="showEditProductForm(1)">Editar</button>
                                        <button class="btn-delete" onclick="deleteProduct(1)">Eliminar</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Producto B</td>
                                    <td>$15.00</td>
                                    <td>10</td>
                                    <td>
                                        <button class="btn-edit" onclick="showEditProductForm(2)">Editar</button>
                                        <button class="btn-delete" onclick="deleteProduct(2)">Eliminar</button>
                                    </td>
                                </tr>
                                <!-- Más filas aquí -->
                            </tbody>
                        </table>
                    </div>
                    <!-- Formulario para agregar productos -->
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
                    <!-- Formulario para editar productos -->
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
    </body>
</html>
