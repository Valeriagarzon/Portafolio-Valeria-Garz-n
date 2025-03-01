// Función para actualizar la fecha y hora en tiempo real
function actualizartiempo() {
    const mostrarDate = new Date();
    const date = mostrarDate.toLocaleDateString();
    const time = mostrarDate.toLocaleTimeString();
    document.getElementById('mostrarFechaHora').innerHTML = `Fecha: ${date}<br>Hora: ${time}`;
}
// Actualizar la fecha y hora cada segundo
setInterval(actualizartiempo, 1000);


