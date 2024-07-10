const predefinedQA = {
    "¿Cuáles son sus métodos de pago?": "Aceptamos tarjetas de crédito, PayPal y transferencias bancarias.",
    "¿Dónde están ubicados?": "Nuestra tienda principal está en la Avenida Principal 123, Ciudad.",
    "¿Cuál es su horario de atención?": "Nuestro horario de atención es de lunes a viernes de 9:00 AM a 6:00 PM.",
    "¿Ofrecen envíos a domicilio?": "Sí, ofrecemos envíos a domicilio a nivel nacional."
};

function addBotMessage(message) {
    const chatBody = document.getElementById('chatBody');
    if (chatBody) {
        const botMessageElement = document.createElement('div');
        botMessageElement.classList.add('chat-message', 'bot');
        botMessageElement.innerHTML = `<p>${message}</p>`;
        chatBody.appendChild(botMessageElement);
        chatBody.scrollTop = chatBody.scrollHeight;
    } else {
        console.error('chatBody no encontrado');
    }
}

function addUserMessage(message) {
    const chatBody = document.getElementById('chatBody');
    if (chatBody) {
        const userMessageElement = document.createElement('div');
        userMessageElement.classList.add('chat-message', 'user');
        userMessageElement.innerHTML = `<p>${message}</p>`;
        chatBody.appendChild(userMessageElement);
        chatBody.scrollTop = chatBody.scrollHeight;
    } else {
        console.error('chatBody no encontrado');
    }
}

function addOptions() {
    const options = document.createElement('div');
    options.classList.add('options');

    for (const question in predefinedQA) {
        const button = document.createElement('button');
        button.innerText = question;
        button.onclick = () => {
            addBotMessage(predefinedQA[question]);
            addOptions();
        };
        options.appendChild(button);
    }

    const chatBody = document.getElementById('chatBody');
    if (chatBody) {
        chatBody.appendChild(options);
    } else {
        console.error('chatBody no encontrado para añadir opciones');
    }
}

document.addEventListener('DOMContentLoaded', function() {
    console.log('Inicializando el chat...');
    addBotMessage('¡Hola! Bienvenido a nuestro servicio de atención al cliente. ¿En qué puedo ayudarte hoy?');
    addOptions();
});

function initializeChat() {
    // Lógica para inicializar el chat, si es necesaria alguna configuración adicional
}




