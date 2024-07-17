function llenarFormulario(fila) {
    var idEmpleado = $(fila).find(".idempleado").text();

    var nombres = $(fila).find(".nombres").text();
    var apellidos = $(fila).find(".apellidos").text();
    var dni = $(fila).find(".dni").text();

    var Sexo = $(fila).data("sexo");
    var FechaN = $(fila).data("fechan");
    var Celular = $(fila).data("celular");
    var Salario = $(fila).data("salario");
    var Correo = $(fila).data("correo");
    var Clave = $(fila).data("clave");



    var nombrelocal = $(fila).find(".nombrelocal").text(); //COMBOBOX
    var descripcionarea = $(fila).find(".descripcionarea").text(); //COMBOBOX

    $("#txtidEmpleado").val(idEmpleado);
    $("#txtNombres").val(nombres);
    $("#txtApellidos").val(apellidos);
    $("#txtDni").val(dni);
    $("#txtSexo").val(Sexo);
    $("#txtFechaN").val(FechaN);
    $("#txtCelular").val(Celular);
    $("#txtSalario").val(Salario);
    $("#txtCorreo").val(Correo);
    $("#txtClave").val(Clave);

    $("#txtidLocal option[selected]").removeAttr('selected');
    $("#txtidLocal option:contains(" + nombrelocal + ")").attr('selected', true);

    $("#txtidArea option[selected]").removeAttr('selected');
    $("#txtidArea option:contains(" + descripcionarea + ")").attr('selected', true);
}


$(document).ready(function () {
    $('#mydataTable').DataTable();

    $("#exampleModal").on("hidden.bs.modal", function () {
        $('form')[0].reset();
        $("#txtidLocal option[selected]").removeAttr('selected');
        $("#txtidArea option[selected]").removeAttr('selected');

    });

    $(document).on('click', '.btnEditar', function () {
        llenarFormulario($(this).closest('tr'));
//        $('.btnOcultar1').attr('disabled', 'disabled');
//        $('.btnOcultar').removeAttr('disabled');
        $('.btnGuardarModal').hide();
        $('.btnEditarModal').show();
        $('.btnEliminarModal').hide();
        $('.btnCancelarModal').hide();
    });
    $(document).on('click', '.btnEliminar', function () {
        llenarFormulario($(this).closest('tr'));
//        $('.btnOcultar1').attr('disabled', 'disabled');
//        $('.btnOcultar').removeAttr('disabled');
        $('.btnGuardarModal').hide();
        $('.btnEditarModal').hide();
        $('.btnEliminarModal').show();
        $('.btnCancelarModal').hide();
    });
    $(document).on('click', '.btnAdd', function () {
//        $('.btnOcultar').attr('disabled', 'disabled');
//        $('.btnOcultar1').removeAttr('disabled');
        $('.btnGuardarModal').show();
        $('.btnEditarModal').hide();
        $('.btnEliminarModal').hide();
        $('.btnCancelarModal').hide();
    });
});


function generarPDF() {
    // Crear un objeto XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Definir la función que manejará la respuesta del servidor
    xhr.onload = function () {
        if (xhr.status == 200) {
            // Convertir la respuesta del servidor (PDF) en un objeto Blob
            var blob = new Blob([xhr.response], {type: 'application/pdf'});

            // Crear un enlace <a> en el documento
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(blob);

            // Asignar el nombre del archivo PDF
            link.download = 'empleado.pdf';

            // Hacer clic en el enlace para iniciar la descarga del PDF
            link.click();

            // Limpiar la URL del objeto Blob creado
            window.URL.revokeObjectURL(link.href);
        } else {
            alert("Error al generar el PDF.");
        }
    };

    // Construir la URL para la solicitud al servidor
    var url = contextPath + '/PDFs/EmpleadoPdf.jsp';

    // Abrir la conexión y enviar la solicitud al servidor para generar el PDF
    xhr.open("GET", url, true);
    xhr.responseType = 'arraybuffer'; // Especificar que la respuesta será un ArrayBuffer (para el PDF)
    xhr.send();
}

function generarExcel() {
    // Crear un objeto XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Definir la función que manejará la respuesta del servidor
    xhr.onload = function () {
        if (xhr.status == 200) {
            // Convertir la respuesta del servidor (Excel) en un objeto Blob
            var blob = new Blob([xhr.response], {type: 'application/vnd.ms-excel'});

            // Crear un enlace <a> en el documento
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(blob);

            // Asignar el nombre del archivo Excel
            link.download = 'Empleado.xls';

            // Hacer clic en el enlace para iniciar la descarga del Excel
            link.click();

            // Limpiar la URL del objeto Blob creado
            window.URL.revokeObjectURL(link.href);
        } else {
            alert("Error al generar el Excel.");
        }
    };

    // Construir la URL para la solicitud al servidor
    var url = contextPath + '/Excels/EmpleadoExcel.jsp';

    // Abrir la conexión y enviar la solicitud al servidor para generar el Excel
    xhr.open("GET", url, true);
    xhr.responseType = 'arraybuffer'; // Especificar que la respuesta será un ArrayBuffer (para el Excel)
    xhr.send();
}