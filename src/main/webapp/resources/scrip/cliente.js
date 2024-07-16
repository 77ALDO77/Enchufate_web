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