// contactenos.js

document.addEventListener("DOMContentLoaded", () => {
    const contactForm = document.getElementById("contactForm");
    const claimForm = document.getElementById("claimForm");

    contactForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const nombre = document.getElementById("nombre").value;
        const email = document.getElementById("email").value;
        const mensaje = document.getElementById("mensaje").value;
        if (nombre && email && mensaje) {
            document.getElementById("formResponse").textContent = "Gracias por contactarnos, " + nombre + ". Te responderemos pronto.";
            contactForm.reset();
        } else {
            document.getElementById("formResponse").textContent = "Por favor, completa los campos obligatorios.";
        }
    });

    claimForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const nombreReclamo = document.getElementById("nombre-reclamo").value;
        const emailReclamo = document.getElementById("email-reclamo").value;
        const mensajeReclamo = document.getElementById("mensaje-reclamo").value;
        if (nombreReclamo && emailReclamo && mensajeReclamo) {
            document.getElementById("claimResponse").textContent = "Gracias por tu reclamo, " + nombreReclamo + ". Nos pondremos en contacto contigo pronto.";
            claimForm.reset();
        } else {
            document.getElementById("claimResponse").textContent = "Por favor, completa los campos obligatorios.";
        }
    });
});

