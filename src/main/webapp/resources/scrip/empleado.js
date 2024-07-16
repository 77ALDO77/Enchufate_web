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