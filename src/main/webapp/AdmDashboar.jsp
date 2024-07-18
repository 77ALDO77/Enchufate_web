<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="resources/css/adminDasboard.css" rel="stylesheet" type="text/css"/>
    <link href="resources/css/Admin.css" rel="stylesheet" type="text/css"/>
    <link href="resources/css/Admin-Display.css" rel="stylesheet" type="text/css"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

    <title>Administrador</title>
</head>
<body class="parent-container">
    <jsp:include page="components/navegadorAdm.jsp"/>
    <script src="resources/scrip/AdmPng.js" type="text/javascript"></script>
    <div class="box-content">
        <div class="container">
            <h1>Dashboard de Ventas</h1>
            <form id="dateFormVentas">
                <label for="fechaInicioVentas">Fecha Inicio:</label>
                <input id="fechaInicioVentas" name="fechaInicioVentas" required>
                <label for="fechaFinVentas">Fecha Fin:</label>
                <input id="fechaFinVentas" name="fechaFinVentas" required>
                <button type="submit">Cargar Datos</button>
            </form>
            <button id="exportExcelBtnVentas">Exportar a Excel</button>
            <div class="graficos-ventas">
                <h2>Ventas Diarias</h2>
                <div id="piechart_ventas"></div>
            </div>

            <h1>Dashboard de Reservas de Cubículos</h1>
            <form id="dateFormReservas">
                <label for="fechaInicioReservas">Fecha Inicio:</label>
                <input id="fechaInicioReservas" name="fechaInicioReservas" required>
                <label for="fechaFinReservas">Fecha Fin:</label>
                <input id="fechaFinReservas" name="fechaFinReservas" required>
                <button type="submit">Cargar Datos</button>
            </form>
            <button id="exportExcelBtnReservas">Exportar a Excel</button>
            <div class="graficos-reservas">
                <h2>Reservas Diarias</h2>
                <div id="piechart_reservas"></div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        google.charts.load("current", {packages: ["corechart"]});
        google.charts.setOnLoadCallback(initialize);

        function initialize() {
            var today = new Date().toISOString().split('T')[0];
            $('#fechaInicioVentas, #fechaFinVentas, #fechaInicioReservas, #fechaFinReservas').val(today);
            drawChartVentas(today, today);
            drawChartReservas(today, today);
        }

        function drawChartVentas(fechaInicio, fechaFin) {
            $.ajax({
                url: '<%= request.getContextPath()%>/cntDashboard',
                method: 'GET',
                dataType: 'json',
                data: {
                    accion: 'cargarVentas',
                    fechaInicio: fechaInicio,
                    fechaFin: fechaFin
                },
                success: function (data) {
                    var dataArray = [['Fecha', 'Total Ventas']];
                    data.forEach(function (venta) {
                        var fecha = new Date(venta.Fecha);
                        var fechaStr = fecha.getFullYear() + '-' + (fecha.getMonth() + 1).toString().padStart(2, '0') + '-' + fecha.getDate().toString().padStart(2, '0');
                        dataArray.push([fechaStr, venta.total]);
                    });

                    var dataTable = google.visualization.arrayToDataTable(dataArray);

                    var options = {
                        title: 'Ventas Diarias',
                        is3D: true,
                    };

                    var chart = new google.visualization.PieChart(document.getElementById('piechart_ventas'));
                    chart.draw(dataTable, options);
                },
                error: function (error) {
                    console.log('Error al obtener los datos:', error);
                }
            });
        }

        function drawChartReservas(fechaInicio, fechaFin) {
            $.ajax({
                url: '<%= request.getContextPath()%>/cntDashboard',
                method: 'GET',
                dataType: 'json',
                data: {
                    accion: 'cargarReservas',
                    fechaInicio: fechaInicio,
                    fechaFin: fechaFin
                },
                success: function (data) {
                    var dataArray = [['Fecha', 'Total Reservas']];
                    data.forEach(function (reserva) {
                        var fecha = new Date(reserva.fecha);
                        var fechaStr = fecha.getFullYear() + '-' + (fecha.getMonth() + 1).toString().padStart(2, '0') + '-' + fecha.getDate().toString().padStart(2, '0');
                        dataArray.push([fechaStr, reserva.total]);
                    });

                    var dataTable = google.visualization.arrayToDataTable(dataArray);

                    var options = {
                        title: 'Reservas Diarias',
                        is3D: true,
                    };

                    var chart = new google.visualization.PieChart(document.getElementById('piechart_reservas'));
                    chart.draw(dataTable, options);
                },
                error: function (error) {
                    console.log('Error al obtener los datos:', error);
                }
            });
        }

        $('#dateFormVentas').on('submit', function (event) {
            event.preventDefault();
            var fechaInicio = $('#fechaInicioVentas').val();
            var fechaFin = $('#fechaFinVentas').val();
            drawChartVentas(fechaInicio, fechaFin);
        });

        $('#exportExcelBtnVentas').on('click', function () {
            var fechaInicio = $('#fechaInicioVentas').val();
            var fechaFin = $('#fechaFinVentas').val();
            window.location.href = '<%= request.getContextPath()%>/cntDashboard?accion=exportarExcelVentas&fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin;
        });

        $('#dateFormReservas').on('submit', function (event) {
            event.preventDefault();
            var fechaInicio = $('#fechaInicioReservas').val();
            var fechaFin = $('#fechaFinReservas').val();
            drawChartReservas(fechaInicio, fechaFin);
        });

        $('#exportExcelBtnReservas').on('click', function () {
            var fechaInicio = $('#fechaInicioReservas').val();
            var fechaFin = $('#fechaFinReservas').val();
            window.location.href = '<%= request.getContextPath()%>/cntDashboard?accion=exportarExcelReservas&fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin;
        });

        $(function () {
            $("#fechaInicioVentas, #fechaFinVentas, #fechaInicioReservas, #fechaFinReservas").datepicker({
                dateFormat: 'yy-mm-dd',
                changeMonth: true,
                changeYear: true,
                yearRange: "-100:+0",
                maxDate: "0"
            });
        });

    </script>
</body>
</html>
