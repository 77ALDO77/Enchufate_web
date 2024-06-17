<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/adminDasboard.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/Admin-Display.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <title>Administrador</title>
    </head>
    <body class="parent-container">
        <jsp:include page="components/navegadorAdm.jsp"/>
        <script src="resources/scrip/AdmPng.js" type="text/javascript"></script>
        <div class="box-content">
            <div class="container">
                <h1>Dashboard de Ventas</h1>
                <form id="dateForm">
                    <label for="fechaInicio">Fecha Inicio:</label>
                    <input type="date" id="fechaInicio" name="fechaInicio" required>
                    <label for="fechaFin">Fecha Fin:</label>
                    <input type="date" id="fechaFin" name="fechaFin" required>
                    <button type="submit">Cargar Datos</button>
                </form>
                <button id="exportExcelBtn">Exportar a Excel</button>
                <div class="graficos-ventas">
                    <h2>Ventas Diarias</h2>
                    <div id="piechart_3d"></div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            google.charts.load("current", {packages: ["corechart"]});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart(fechaInicio, fechaFin) {
                $.ajax({
                    url: '<%= request.getContextPath()%>/cntDashboard',
                    method: 'GET',
                    dataType: 'json',
                    data: {
                        accion: 'cargar',
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

                        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
                        chart.draw(dataTable, options);
                    },
                    error: function (error) {
                        console.log('Error al obtener los datos:', error);
                    }
                });
            }

            $('#dateForm').on('submit', function (event) {
                event.preventDefault();
                var fechaInicio = $('#fechaInicio').val();
                var fechaFin = $('#fechaFin').val();
                drawChart(fechaInicio, fechaFin);
            });

            $('#exportExcelBtn').on('click', function () {
                var fechaInicio = $('#fechaInicio').val();
                var fechaFin = $('#fechaFin').val();
                window.location.href = '<%= request.getContextPath()%>/cntDashboard?accion=exportarExcel&fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin;
            });

            $(document).ready(function () {
                var today = new Date().toISOString().split('T')[0];
                $('#fechaInicio').val(today);
                $('#fechaFin').val(today);
                drawChart(today, today);
            });
        </script>
    </body>
</html>

