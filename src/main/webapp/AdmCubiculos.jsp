<%@page contentType="text/html charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/AdmCubiculos.css" rel="stylesheet" type="text/css"/>      
        <link href="resources/css/Admin.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin-Display.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title>Gestión de Cubículos</title>
    </head>
    <body class="parent-container">
        <jsp:include page="components/navegadorAdm.jsp"/>
        <script src="resources/scrip/AdmPng.js" type="text/javascript"></script>
        <div class="box-content">
            <div class="container">
                <h1>Gestión de Cubículos</h1>
                <label for="locales">Seleccionar Local:</label>
                <select id="locales" onchange="cargarCubiculos(this.value)">
                    <option value="">Seleccione un Local</option>
                </select>
                <table id="cubiculosTable">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nivel</th>
                            <th>Estado</th>
                            <th>Asignar Cliente</th>
                            <th>Hora de Inicio</th>
                            <th>Hora de Fin</th>
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
            $(document).ready(function () {
                cargarLocales();
                cargarClientes();
            });

            function cargarLocales() {
                $.ajax({
                    url: '<%= request.getContextPath()%>/cntCubiculos',
                    method: 'GET',
                    dataType: 'json',
                    data: {
                        accion: 'listarLocales'
                    },
                    success: function (data) {
                        var selectLocales = $('#locales');
                        data.forEach(function (local) {
                            var option = '<option value="' + local.CodLocal + '">' + local.Nombre + '</option>';
                            selectLocales.append(option);
                        });
                    },
                    error: function (error) {
                        console.log('Error al cargar locales:', error);
                    }
                });
            }
            

            function cargarClientes() {
                $.ajax({
                    url: '<%= request.getContextPath()%>/cntCubiculos',
                    method: 'GET',
                    dataType: 'json',
                    data: {
                        accion: 'listarClientes'
                    },
                    success: function (data) {
                        window.clientes = data; // Almacena los clientes en una variable global para su uso posterior
                    },
                    error: function (error) {
                        console.log('Error al cargar clientes:', error);
                    }
                });
            }

            function cargarCubiculos(CodLocal) {
                if (!CodLocal) {
                    $('#cubiculosTable tbody').empty();
                    return;
                }

                $.ajax({
                    url: '<%= request.getContextPath()%>/cntCubiculos',
                    method: 'GET',
                    dataType: 'json',
                    data: {
                        accion: 'listar',
                        CodLocal: CodLocal
                    },
                    success: function (data) {
                        var tableBody = $('#cubiculosTable tbody');
                        tableBody.empty();
                        data.forEach(function (cubiculo) {
                            var row = '<tr>' +
                                    '<td>' + cubiculo.codCubiculo + '</td>' +
                                    '<td>' + cubiculo.codNivel + '</td>' +
                                    '<td class="estadoCubiculo">' + cubiculo.estadoCubiculo + '</td>' +
                                    '<td>' +
                                    '<form class="asignarClienteForm" action="cntCubiculos" method="post">' +
                                    '<input type="hidden" name="accion" value="crearReserva">' +
                                    '<input type="hidden" name="codCubiculo" value="' + cubiculo.codCubiculo + '">' +
                                    '<label for="clientes">Cliente:</label>' +
                                    '<select name="codCliente" required>';
                            window.clientes.forEach(function (cliente) {
                                row += '<option value="' + cliente.codCliente + '">' + cliente.nombres + ' ' + cliente.apePaterno + ' ' + cliente.apeMaterno + '</option>';
                            });
                            row += '</select>' +
                                    'Fecha: <input type="date" name="fecha" required>' +
                                    'Hora de Inicio: <input type="datetime-local" name="horaInicio" required>' +
                                    'Hora de Fin: <input type="datetime-local" name="horaFin" required>' +
                                    'Duración (minutos): <input type="number" name="tiempo" required>' +
                                    '<button type="submit">Asignar</button>' +
                                    '</form>' +
                                    '</td>' +
                                    '<td>' + (cubiculo.horaInicio || '--') + '</td>' +
                                    '<td>' + (cubiculo.horaFin || '--') + '</td>' +
                                    '<td class="tiempoRestante" data-codcubiculo="' + cubiculo.codCubiculo + '">--</td>' +
                                    '</tr>';
                            tableBody.append(row);
                        });

                        // Actualizar tiempo restante
                        setInterval(actualizarTiemposRestantes, 60000); // Actualiza cada minuto
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
                            if (data.tiempoRestante <= 0) {
                                elem.closest('tr').find('.estadoCubiculo').text('Disponible');
                                elem.text('0 minutos');
                            } else {
                                elem.text(data.tiempoRestante + ' minutos');
                            }
                        },
                        error: function (error) {
                            console.log('Error al obtener el tiempo restante:', error);
                        }
                    });
                });
            }
        </script>
    </body>
</html>
