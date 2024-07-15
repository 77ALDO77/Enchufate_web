const predefinedQA = {
    "¿Cuáles son sus métodos de pago?": "Aceptamos tarjetas de crédito, PayPal y transferencias bancarias.",
    "¿Dónde están ubicados?": "Nuestra tienda principal está en la Avenida Principal 123, Ciudad.",
    "¿Cuál es su horario de atención?": "Nuestro horario de atención es de lunes a viernes de 9:00 AM a 6:00 PM.",
    "¿Ofrecen envíos a domicilio?": "Sí, ofrecemos envíos a domicilio a nivel nacional.",
    "¿Que tipos de Cubiculos existen en su empresa?": "Ofrecemos una variedad de 3 tipos, General S/.5 x hora, VIP S/.8 x hora, ULTRAVIP S/.12 x hora.",
    "¿Que productos ofrecen en su tienda?":"Poseemos una variedad de productos con buen precio como Platos de Comida, Snacks, Bebidas,Productos de Cuidado Personal y Cigarros."
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

function addOptions(options, callback) {
    const optionsContainer = document.createElement('div');
    optionsContainer.classList.add('options');

    options.forEach(option => {
        const button = document.createElement('button');
        button.innerText = option.text;
        button.onclick = () => {
            addUserMessage(option.text);
            optionsContainer.remove();
            callback(option.value);
        };
        optionsContainer.appendChild(button);
    });

    const chatBody = document.getElementById('chatBody');
    if (chatBody) {
        chatBody.appendChild(optionsContainer);
    } else {
        console.error('chatBody no encontrado para añadir opciones');
    }
}

function handleUserChoice(choice) {
    if (choice === 'yes') {
        askQuestion();
    } else if (choice === 'no') {
        addBotMessage('Gracias por visitarnos.');
        setTimeout(() => {
            askInitialQuestion();
        }, 2000);
    }
}

function askQuestion() {
    addOptions(
        Object.keys(predefinedQA).map(question => ({
            text: question,
            value: question
        })),
        question => {
            addBotMessage(predefinedQA[question]);
            addBotMessage('¿Tiene más consultas sobre nuestra empresa?');
            addOptions([
                { text: 'Sí', value: 'yes' },
                { text: 'No', value: 'no' }
            ], handleUserChoice);
        }
    );
}

function askInitialQuestion() {
    addBotMessage('¿Tienes consultas sobre nuestra empresa?');
    addOptions([
        { text: 'Sí', value: 'yes' },
        { text: 'No', value: 'no' }
    ], handleUserChoice);
}

document.addEventListener('DOMContentLoaded', function() {
    console.log('Inicializando el chat...');
    addBotMessage('¡Hola! Bienvenido a nuestra empresa.');
    setTimeout(() => {
        askInitialQuestion();
    }, 1000);
});






