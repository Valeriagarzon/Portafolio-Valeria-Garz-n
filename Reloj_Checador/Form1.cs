using Firebase.Database;
using Firebase.Database.Query;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Reflection.Emit;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Reloj_Checador
{
    public partial class Form1 : Form
    {
        private static readonly string firebaseUrl = "https://reloj-checador-6637f-default-rtdb.firebaseio.com/"; 
        private FirebaseClient firebaseClient;
        public Form1()
        {
            InitializeComponent();
            firebaseClient = new FirebaseClient(firebaseUrl);

            timer1.Interval = 1000;
            timer1.Tick += timer1_Tick; // Asignar evento Tick
            timer1.Start(); // Iniciar el Timer
        }
        private void timer1_Tick(object sender, EventArgs e)
        {
            FechaLb.Text = DateTime.Now.ToString("dd/MM/yyyy");
            HoraLb.Text = DateTime.Now.ToString("HH:mm:ss");
        }
        private async void btnRegistrar_Click(object sender, EventArgs e)
        {
            string fecha = DateTime.Now.ToString("dd-MM-yyyy");
            string empleadoId = txtEmpleadoId.Text;
            string estado = "";

            if (string.IsNullOrWhiteSpace(empleadoId))
            {
                MessageBox.Show("Por favor, ingrese el ID del empleado.");
                return;
            }

            // Verificar si el empleado existe
            Empleado empleado = await Empleado.ObtenerEmpleadoPorId(firebaseClient, empleadoId);
            if (empleado == null)
            {
                MessageBox.Show("El empleado con ID " + empleadoId + " no existe.");
                return;
            }

            // Verificar el registro de asistencia existente
            RegistroAsistencia registro = await RegistroAsistencia.ObtenerRegistro(firebaseClient, fecha, empleadoId);

            string horaEntrada = registro?.HoraEntrada;
            string horaSalida = registro?.HoraSalida;

            if (rbEntrada.Checked)
            {
                if (!string.IsNullOrEmpty(horaEntrada))
                {
                    MessageBox.Show("Ya existe una hora de entrada registrada para este día.");
                    return;
                }

                horaEntrada = DateTime.Now.ToString("HH:mm");
                estado = RegistroAsistencia.ObtenerEstado(horaEntrada);
                RegistroAsistencia nuevoRegistro = new RegistroAsistencia
                {
                    Estado = estado,
                    HoraEntrada = horaEntrada,
                    HoraSalida = horaSalida
                };

                await RegistroAsistencia.Insertar(firebaseClient, fecha, empleadoId, nuevoRegistro);
            }
            else if (rbSalida.Checked)
            {
                if (string.IsNullOrEmpty(horaEntrada))
                {
                    MessageBox.Show("No se puede registrar la salida sin haber registrado la entrada.");
                    return;
                }

                if (!string.IsNullOrEmpty(horaSalida))
                {
                    MessageBox.Show("La salida ya ha sido registrada.");
                    return;
                }

                horaSalida = DateTime.Now.ToString("HH:mm");
                estado = RegistroAsistencia.ObtenerEstado(horaEntrada); // Se puede ajustar esta lógica si es necesario
                registro.HoraSalida = horaSalida;
                registro.Estado = estado;

                await RegistroAsistencia.Insertar(firebaseClient, fecha, empleadoId, registro);
            }
            else
            {
                MessageBox.Show("Por favor, selecciona si es una Entrada o Salida.");
            }

            MostrarImagenPorEstado(estado);
        }
        // Método para mostrar la imagen según el estado
        private void MostrarImagenPorEstado(string estado)
        {
            if (estado == "puntual")
            {
                pictureBox.Image = Properties.Resources.puntual;
            }
            else if (estado == "tardanza")
            {
                pictureBox.Image = Properties.Resources.tardanza;
            }
        }

        private async void btnAsistencias_Click(object sender, EventArgs e)
        {
            string empleadoId = txtEmpleadoId.Text; // Obtener el ID del empleado desde el TextBox

            // Validar si el campo de empleado ID está vacío
            if (string.IsNullOrWhiteSpace(empleadoId))
            {
                MessageBox.Show("Por favor, ingrese el ID del empleado.");
                return;
            }

            // Llamar el método para obtener las asistencias del empleado
            await MostrarAsistenciasEmpleado(empleadoId);
        }

        // Método para obtener los registros de asistencia del empleado
        private async Task MostrarAsistenciasEmpleado(string empleadoId)
        {
            try
            {
                // Obtener los registros de asistencia del empleado
                var registrosAsistencia = await firebaseClient
                    .Child("registros_asistencia")
                    .OnceAsync<Dictionary<string, RegistroAsistencia>>();

                // Crear una lista para almacenar las asistencias filtradas por empleado
                var listaAsistencias = new List<RegistroAsistencia>();

                foreach (var fechaRegistro in registrosAsistencia)
                {
                    if (fechaRegistro.Object.ContainsKey(empleadoId))
                    {
                        // Obtener el registro del empleado en esa fecha
                        var registro = fechaRegistro.Object[empleadoId];
                        listaAsistencias.Add(new RegistroAsistencia
                        {
                            EmpleadoId = empleadoId,
                            Fecha = fechaRegistro.Key,
                            HoraEntrada = registro.HoraEntrada,
                            HoraSalida = registro.HoraSalida,
                            Estado = registro.Estado
                        });
                    }
                }

                // Asignar la lista al DataGridView para mostrarla
                dataGridView1.DataSource = listaAsistencias;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al consultar los registros de asistencia: " + ex.Message);
            }
        }
    }

}

    public class Empleado
    {
        public string Id { get; set; }
        public string Nombre { get; set; }
        public string Departamento { get; set; }

        // Método para verificar si el empleado existe en Firebase
        public static async Task<Empleado> ObtenerEmpleadoPorId(FirebaseClient firebaseClient, string empleadoId)
        {
            var empleadoExistente = await firebaseClient
                .Child("empleados")
                .Child(empleadoId)
                .OnceSingleAsync<Empleado>();

            return empleadoExistente != null ? empleadoExistente : null;
        }
    }

    public class RegistroAsistencia
    {
        public string Estado { get; set; }
        public string HoraEntrada { get; set; }
        public string HoraSalida { get; set; }
        public string Fecha { get; set; }
        public string EmpleadoId { get; set; }

    // Método para insertar un registro de asistencia en Firebase
    public static async Task Insertar(FirebaseClient firebaseClient, string fecha, string empleadoId, RegistroAsistencia registro)
        {
            try
            {
                await firebaseClient
                    .Child("registros_asistencia")
                    .Child(fecha)
                    .Child(empleadoId)
                    .PutAsync(registro);
            }
            catch (Exception ex)
            {
                throw new Exception("Error al guardar el registro de asistencia: " + ex.Message);
            }
        }

        // Método para obtener un registro de asistencia existente
        public static async Task<RegistroAsistencia> ObtenerRegistro(FirebaseClient firebaseClient, string fecha, string empleadoId)
        {
            var registroExistente = await firebaseClient
                .Child("registros_asistencia")
                .Child(fecha)
                .Child(empleadoId)
                .OnceSingleAsync<RegistroAsistencia>();

            return registroExistente;
        }

        // Método para determinar el estado según la hora de entrada
        public static string ObtenerEstado(string horaEntrada)
        {
            DateTime horaEntradaOficial = DateTime.Today.AddHours(11).AddMinutes(0); // 11:00 AM
            DateTime horaActual = DateTime.Now;

            return horaActual <= horaEntradaOficial ? "puntual" : "tardanza";
        }
    }


