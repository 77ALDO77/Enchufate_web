function llenarFormulario(fila){
    var idCliente = $(fila).find(".idcliente").text();
    var nombres = $(fila).find(".nombres").text();
    var apellidop = $(fila).find(".apellidop").text();
    var apellidom = $(fila).find(".apellidom").text();
    var ndocumento = $(fila).find(".ndocumento").text();    
    var fechan = $(fila).data("fechan");
    var usuario = $(fila).data("usuario");
    var correo = $(fila).data("correo");
    var contrasena = $(fila).data("contrasena");

    
    $("#txtIdCliente").val(idCliente);
    $("#txtNombres").val(nombres);
    $("#txtApellidoP").val(apellidop);
    $("#txtApellidoM").val(apellidom);
    $("#txtNdocumento").val(ndocumento);
    $("#txtFechaN").val(fechan);
    
    $("#txtUsuario").val(usuario);
    $("#txtCorreo").val(correo);
    $("#txtContrasena").val(contrasena);

}

$(document).ready(function(){
    $('#mydataTable').DataTable();
    
    $("#exampleModal").on("hidden.bs.modal", function(){
        $('form')[0].reset();
    });
    
    $(document).on('click', '.btnEditar', function(){
        llenarFormulario($(this).closest('tr'));
//        $('.btnOcultar1').attr('disabled', 'disabled');
//        $('.btnOcultar').removeAttr('disabled');
        $('.btnGuardarModal').hide();
        $('.btnEditarModal').show();
        $('.btnEliminarModal').hide();
        $('.btnCancelarModal').hide();
    });
    $(document).on('click', '.btnEliminar', function(){
        llenarFormulario($(this).closest('tr'));
//        $('.btnOcultar1').attr('disabled', 'disabled');
//        $('.btnOcultar').removeAttr('disabled');
        $('.btnGuardarModal').hide();
        $('.btnEditarModal').hide();
        $('.btnEliminarModal').show();
        $('.btnCancelarModal').hide();
    });
    $(document).on('click', '.btnAdd', function(){
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
            link.download = 'cliente.pdf';

            // Hacer clic en el enlace para iniciar la descarga del PDF
            link.click();

            // Limpiar la URL del objeto Blob creado
            window.URL.revokeObjectURL(link.href);
        } else {
            alert("Error al generar el PDF.");
        }
    };

    // Construir la URL para la solicitud al servidor
    var url = contextPath + '/PDFs/ClientePdf.jsp';

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
            link.download = 'Cliente.xls';

            // Hacer clic en el enlace para iniciar la descarga del Excel
            link.click();

            // Limpiar la URL del objeto Blob creado
            window.URL.revokeObjectURL(link.href);
        } else {
            alert("Error al generar el Excel.");
        }
    };

    // Construir la URL para la solicitud al servidor
    var url = contextPath + '/Excels/ClienteExcel.jsp';

    // Abrir la conexión y enviar la solicitud al servidor para generar el Excel
    xhr.open("GET", url, true);
    xhr.responseType = 'arraybuffer'; // Especificar que la respuesta será un ArrayBuffer (para el Excel)
    xhr.send();
}