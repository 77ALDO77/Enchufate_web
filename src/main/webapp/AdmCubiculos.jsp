<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
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
                <c:forEach var="local" items="${locales}">
                    <option value="${local.codLocal}">${local.nombre}</option>
                </c:forEach>
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
        function cargarCubiculos(codLocal) {
            if (!codLocal) {
                $('#cubiculosTable tbody').empty();
                return;
            }

            $.ajax({
                url: '<%= request.getContextPath()%>/cntCubiculos',
                method: 'GET',
                dataType: 'json',
                data: {
                    accion: 'listar',
                    codLocal: codLocal
                },
                success: function (data) {
                    var tableBody = $('#cubiculosTable tbody');
                    tableBody.empty();
                    data.forEach(function (cubiculo) {
                        var row = '<tr>' +
                                '<td>' + cubiculo.codCubiculo + '</td>' +
                                '<td>' + cubiculo.nivel + '</td>' +
                                '<td class="estadoCubiculo">' + cubiculo.estadoCubiculo + '</td>' +
                                '<td>' +
                                '<form class="asignarClienteForm" action="cntCubiculos" method="post">' +
                                '<input type="hidden" name="accion" value="crearReserva">' +
                                '<input type="hidden" name="codCubiculo" value="' + cubiculo.codCubiculo + '">' +
                                'DNI Cliente: <input type="text" name="codCliente" required>' +
                                'Duración (minutos): <input type="number" name="duracion" required>' +
                                '<button type="submit">Asignar</button>' +
                                '</form>' +
                                '</td>' +
                                '<td>' + cubiculo.horaInicio + '</td>' +
                                '<td>' + cubiculo.horaFin + '</td>' +
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
