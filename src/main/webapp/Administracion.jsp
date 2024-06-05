<%-- 
    Document   : Administracion
    Created on : 4 jun. 2024, 12:09:40 a. m.
    Author     : aldom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Administración de Cubículos</title>
        <link href="resources/css/administracion.css" rel="stylesheet" type="text/css"/>
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
                        <li><a href="#">Configuración</a></li>
                    </ul>
                </nav>
            </aside>
            <main class="main-content">
                <header class="header">
                    <h1>Administración de Cubículos</h1>
                </header>
                <section class="dashboard">
                    <div class="stats">
                        <div class="card">
                            <h3>Cubículos Activos</h3>
                            <p>15</p>
                        </div>
                        <div class="card">
                            <h3>Reservas Hoy</h3>
                            <p>8</p>
                        </div>
                        <div class="card">
                            <h3>Clientes Activos</h3>
                            <p>10</p>
                        </div>
                    </div>
                    <div class="details">
                        <h2>Detalles de Cubículos</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Estado</th>
                                    <th>Usuario</th>
                                    <th>Tiempo Restante</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Ocupado</td>
                                    <td>Juan Pérez</td>
                                    <td>30 min</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Disponible</td>
                                    <td>-</td>
                                    <td>-</td>
                                </tr>
                                <!-- Más filas aquí -->
                            </tbody>
                        </table>
                    </div>
                </section>
            </main>
        </div>
    </body>
</html>

