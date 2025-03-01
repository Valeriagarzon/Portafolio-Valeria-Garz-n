import { initializeApp } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-app.js";
import { getDatabase, ref, update, get, set } from "https://www.gstatic.com/firebasejs/10.14.1/firebase-database.js";
import { mostrarAsistencias } from "./TablaAsistencias.js"; // Importar la función para mostrar asistencias

const firebaseConfig = {
    apiKey: "AIzaSyCY5khDcwNwj-OIEZREkYUap6pyOEfM3PE",
    authDomain: "reloj-checador-6637f.firebaseapp.com",
    databaseURL: "https://reloj-checador-6637f-default-rtdb.firebaseio.com",
    projectId: "reloj-checador-6637f",
    storageBucket: "reloj-checador-6637f.appspot.com",
    messagingSenderId: "738067506235",
    appId: "1:738067506235:web:6926d67f5925d2a004681e",
    measurementId: "G-5JK896VZZE"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const database = getDatabase(app);
const horaEntradaDef = "12:00";

class Empleado {
    constructor(id) {
        this.id = id;
        this.empleadoRef = ref(database, 'empleados/' + id);
    }
    // Verificar si el empleado existe en la base de datos
    verificarExistencia() {
        return get(this.empleadoRef);
    }
}
class RegistroAsistencia {
    constructor(idEmpleado, fecha) {
        this.idEmpleado = idEmpleado;
        this.fechaFormateada = `${String(fecha.getDate()).padStart(2, '0')}-${String(fecha.getMonth() + 1).padStart(2, '0')}-${fecha.getFullYear()}`;
        this.registroRef = ref(database, 'registros_asistencia/' + this.fechaFormateada + '/' + this.idEmpleado);
    }
    // Registrar la hora de entrada
    registrarEntrada(horaEmpleado) {
        get(this.registroRef).then((snapshot) => {
            if (snapshot.exists()) {
                const data = snapshot.val();
                if (data.horaEntrada) {
                    alert('Ya existe una hora de entrada registrada para este día.');
                } else {
                    let estado = horaEmpleado <= horaEntradaDef ? "puntual" : "tardanza";
                    set(this.registroRef, {
                        horaEntrada: horaEmpleado,
                        estado: estado
                    }).then(() => {
                        alert('Hora de entrada registrada con éxito: ' + horaEmpleado);
                    }).catch(error => {
                        console.error('Error al registrar la entrada:', error);
                    });
                }
            } else {
                let estado = horaEmpleado <= horaEntradaDef ? "puntual" : "tardanza";
                set(this.registroRef, {
                    horaEntrada: horaEmpleado,
                    estado: estado
                }).then(() => {
                    alert('Hora de entrada registrada con éxito: ' + horaEmpleado);
                }).catch(error => {
                    console.error('Error al registrar la entrada:', error);
                });
            }
        }).catch(error => {
            console.error('Error al obtener los datos:', error);
        });
    }

    // Registrar la hora de salida
    registrarSalida(horaEmpleado) {
        get(this.registroRef).then((snapshot) => {
            if (snapshot.exists()) {
                const data = snapshot.val();
                if (!data.horaSalida) {
                    update(this.registroRef, {
                        horaSalida: horaEmpleado
                    }).then(() => {
                        alert('Hora de salida registrada con éxito: ' + horaEmpleado);
                    }).catch(error => {
                        console.error('Error al registrar la salida:', error);
                    });
                } else {
                    alert('La salida ya ha sido registrada.');
                }
            } else {
                alert('Primero debe registrar la hora de entrada.');
            }
        }).catch(error => {
            console.error('Error al obtener los datos:', error);
        });
    }
}

// Controlador para manejar el flujo de registro
function registrarAsistencia() {
    const idEmpleado = document.getElementById('idEmpleado').value;
    const fecha = new Date();
    const horaActual = new Date();
    const horaEmpleado = horaActual.toLocaleTimeString('es-MX', { hour: '2-digit', minute: '2-digit', hour12: false });
    const tipoRegistro = document.querySelector('input[name="tipoRegistro"]:checked').value;

    // Crear instancia del empleado
    const empleado = new Empleado(idEmpleado);

    // Verificar si el empleado existe
    empleado.verificarExistencia().then((snapshot) => {
        if (snapshot.exists()) {
            // Crear instancia de registro de asistencia
            const registro = new RegistroAsistencia(idEmpleado, fecha);
            // Verificar tipo de registro (entrada/salida)
            if (tipoRegistro === "entrada") {
                registro.registrarEntrada(horaEmpleado);
            } else if (tipoRegistro === "salida") {
                registro.registrarSalida(horaEmpleado);
            }
        } else {
            alert('El ID de empleado no es válido. Por favor, verifique.');
        }
    }).catch((error) => {
        console.error('Error al verificar el empleado:', error);
    });
}

// Event listener para el botón de registrar asistencia
document.getElementById('btnRegistrar').addEventListener('click', function (event) {
    event.preventDefault();
    registrarAsistencia();
});

// Event listener para mostrar las asistencias
document.getElementById('btnmostrarAsistencias').addEventListener('click', function () {
    mostrarAsistencias();
});
