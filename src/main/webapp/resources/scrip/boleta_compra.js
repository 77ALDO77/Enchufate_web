function generarPDF(callback) {
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
            link.download = 'boleta.pdf';

            // Hacer clic en el enlace para iniciar la descarga del PDF
            link.click();

            // Limpiar la URL del objeto Blob creado
            window.URL.revokeObjectURL(link.href);

            // Llamar al callback para continuar con el envío del formulario
            if (callback) callback();
        } else {
            alert("Error al generar el PDF.");
        }
    };

    // Construir la URL para la solicitud al servidor
    var url = contextPath + '/PDFs/BoletaPdf.jsp';

    // Abrir la conexión y enviar la solicitud al servidor para generar el PDF
    xhr.open("GET", url, true);
    xhr.responseType = 'arraybuffer'; // Especificar que la respuesta será un ArrayBuffer (para el PDF)
    xhr.send();
}

// Modificar el evento 'submit' del formulario
document.getElementById('payment-form').addEventListener('submit', function (event) {
    event.preventDefault(); // Prevenir el envío del formulario
    generarPDF(function () {
        document.getElementById('payment-form').submit(); // Enviar el formulario después de generar el PDF
    });
});
