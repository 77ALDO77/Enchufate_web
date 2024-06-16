<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/AdmCubiculos.css" rel="stylesheet" type="text/css"/>      
        <link href="resources/css/Admin.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin-Display.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title>Cubiculos</title>
    </head>
    <body class="parent-container">
        <jsp:include page="components/navegadorAdm.jsp"/>
        <script src="resources/scrip/AdmPng.js" type="text/javascript"></script>
        <div class="box-content">
            <div class="container">
                <h1>Gestión de Cubículos</h1>
                <table id="cubiculosTable">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nivel</th>
                            <th>Estado</th>
                            <th>Asignar Cliente</th>
                            <th>Tiempo Restante</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Los datos se cargarán aquí dinámicamente -->
                    </tbody>
                </table>
            </div>
        </div>

        <script type="text/javascript">
            function cargarCubiculos() {
                $.ajax({
                    url: '<%= request.getContextPath()%>/cntCubiculos',
                    method: 'GET',
                    dataType: 'json',
                    data: {
                        accion: 'listar'
                    },
                    success: function (data) {
                        var tableBody = $('#cubiculosTable tbody');
                        tableBody.empty();
                        data.forEach(function (cubiculo) {
                            var row = '<tr>' +
                                    '<td>' + cubiculo.codCubiculo + '</td>' +
                                    '<td>' + cubiculo.nivel + '</td>' +
                                    '<td>' + cubiculo.estado + '</td>' +
                                    '<td>' +
                                    '<form class="asignarClienteForm" action="cntCubiculos" method="post">' +
                                    '<input type="hidden" name="accion" value="crearReserva">' +
                                    '<input type="hidden" name="codCubiculo" value="' + cubiculo.codCubiculo + '">' +
                                    'DNI Cliente: <input type="text" name="dniCliente" required>' +
                                    'Duración (minutos): <input type="number" name="duracion" required>' +
                                    '<button type="submit">Asignar</button>' +
                                    '</form>' +
                                    '</td>' +
                                    '<td class="tiempoRestante" data-codcubiculo="' + cubiculo.codCubiculo + '">--</td>' +
                                    '</tr>';
                            tableBody.append(row);
                        });

                        // Agregar manejador de formularios
                        $('.asignarClienteForm').on('submit', function (event) {
                            event.preventDefault();
                            var form = $(this);
                            var dniCliente = form.find('input[name="dniCliente"]').val();
                            var codCubiculo = form.find('input[name="codCubiculo"]').val();
                            var duracion = form.find('input[name="duracion"]').val();

                            $.ajax({
                                url: '<%= request.getContextPath()%>/cntCubiculos',
                                method: 'POST',
                                data: {
                                    accion: 'crearReserva',
                                    dniCliente: dniCliente,
                                    codCubiculo: codCubiculo,
                                    duracion: duracion
                                },
                                success: function () {
                                    cargarCubiculo(codCubiculo);  // Actualizar solo el cubículo afectado
                                },
                                error: function (error) {
                                    alert('Error al asignar el cliente: ' + error.responseText);
                                }
                            });
                        });

                        // Actualizar tiempo restante
                        setInterval(actualizarTiemposRestantes, 60);
                    },
                    error: function (error) {
                        console.log('Error al obtener los datos:', error);
                    }
                });
            }

            function cargarCubiculo(codCubiculo) {
                $.ajax({
                    url: '<%= request.getContextPath()%>/cntCubiculos',
                    method: 'GET',
                    dataType: 'json',
                    data: {
                        accion: 'obtenerCubiculo',
                        codCubiculo: codCubiculo
                    },
                    success: function (cubiculo) {
                        var row = '<tr>' +
                                '<td>' + cubiculo.codCubiculo + '</td>' +
                                '<td>' + cubiculo.nivel + '</td>' +
                                '<td>' + cubiculo.estado + '</td>' +
                                '<td>' +
                                '<form class="asignarClienteForm" action="cntCubiculos" method="post">' +
                                '<input type="hidden" name="accion" value="crearReserva">' +
                                '<input type="hidden" name="codCubiculo" value="' + cubiculo.codCubiculo + '">' +
                                'DNI Cliente: <input type="text" name="dniCliente" required>' +
                                'Duración (minutos): <input type="number" name="duracion" required>' +
                                '<button type="submit">Asignar</button>' +
                                '</form>' +
                                '</td>' +
                                '<td class="tiempoRestante" data-codcubiculo="' + cubiculo.codCubiculo + '">--</td>' +
                                '</tr>';
                        $('#cubiculosTable tbody').find('[data-codcubiculo="' + codCubiculo + '"]').closest('tr').replaceWith(row);

                        // Agregar manejador de formularios
                        $('.asignarClienteForm').on('submit', function (event) {
                            event.preventDefault();
                            var form = $(this);
                            var dniCliente = form.find('input[name="dniCliente"]').val();
                            var codCubiculo = form.find('input[name="codCubiculo"]').val();
                            var duracion = form.find('input[name="duracion"]').val();

                            $.ajax({
                                url: '<%= request.getContextPath()%>/cntCubiculos',
                                method: 'POST',
                                data: {
                                    accion: 'crearReserva',
                                    dniCliente: dniCliente,
                                    codCubiculo: codCubiculo,
                                    duracion: duracion
                                },
                                success: function () {
                                    cargarCubiculo(codCubiculo);
                                },
                                error: function (error) {
                                    alert('Error al asignar el cliente: ' + error.responseText);
                                }
                            });
                        });
                    },
                    error: function (error) {
                        console.log('Error al obtener los datos:', error);
                    }
                });
            }

            function actualizarTiemposRestantes() {
                $('.tiempoRestante').each(function () {
                    var elem = $(this);
                    var codCubiculo = elem.data('codcubiculo');

                    $.ajax({
                        url: '<%= request.getContextPath()%>/cntCubiculos',
                        method: 'GET',
                        dataType: 'json',
                        data: {
                            accion: 'obtenerTiempoRestante',
                            codCubiculo: codCubiculo
                        },
                        success: function (data) {
                            elem.text(data.tiempoRestante + ' minutos');
                        },
                        error: function (error) {
                            console.log('Error al obtener el tiempo restante:', error);
                        }
                    });
                });
            }

            $(document).ready(function () {
                cargarCubiculos();
            });
        </script>
    </body>
</html>
