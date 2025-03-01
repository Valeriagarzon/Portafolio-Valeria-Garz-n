import { getDatabase, ref, get } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-database.js";

// Función para obtener y mostrar las asistencias de un empleado
function mostrarAsistencias() {
    const idEmpleado = document.getElementById('idEmpleado').value; // Obtener ID del empleado
    const database = getDatabase();
    const registrosRef = ref(database, 'registros_asistencia'); // Referencia a la colección de registros de asistencia

    limpiarTabla(); // Limpiar tabla antes de mostrar registros

    // Obtener todas las asistencias del empleado
    get(registrosRef).then((snapshot) => {
        if (snapshot.exists()) {
            const registros = snapshot.val();
            let registrosEmpleado = false; // Variable para verificar si hay registros del empleado

            // Iterar a través de las fechas para encontrar registros del empleado
            for (const fecha in registros) {
                if (registros[fecha][idEmpleado]) {
                    const data = registros[fecha][idEmpleado];
                    agregarFilaTabla(idEmpleado, fecha, data.horaEntrada || 'No registrada', data.horaSalida || 'No registrada', data.estado || 'Sin estado');
                    registrosEmpleado = true; // Se encontró al menos un registro del empleado
                }
            }

            if (!registrosEmpleado) {
                alert('No se encontraron registros de asistencia para el empleado con ID: ' + idEmpleado);
            }
        } else {
            alert('No hay registros de asistencia disponibles.');
        }
    }).catch((error) => {
        console.error('Error al obtener los registros de asistencia:', error);
    });
}

// Función para limpiar la tabla de asistencias
function limpiarTabla() {
    const tabla = document.getElementById('tablaAsistencia').getElementsByTagName('tbody')[0];
    tabla.innerHTML = ''; // Limpia el contenido de tbody
}

// Función para agregar una fila a la tabla de asistencias
function agregarFilaTabla(idEmpleado, fecha, horaEntrada, horaSalida, estado) {
    const tabla = document.getElementById('tablaAsistencia').getElementsByTagName('tbody')[0];
    const nuevaFila = tabla.insertRow(); // Insertar nueva fila

    // Insertar celdas y asignar los valores
    const celdaIdEmpleado = nuevaFila.insertCell(0);
    const celdaFecha = nuevaFila.insertCell(1);
    const celdaHoraEntrada = nuevaFila.insertCell(2);
    const celdaHoraSalida = nuevaFila.insertCell(3);
    const celdaEstado = nuevaFila.insertCell(4);

    celdaIdEmpleado.textContent = idEmpleado;
    celdaFecha.textContent = fecha;
    celdaHoraEntrada.textContent = horaEntrada;
    celdaHoraSalida.textContent = horaSalida;
    celdaEstado.textContent = estado;
}

export { mostrarAsistencias };
