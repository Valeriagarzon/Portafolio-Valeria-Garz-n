
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ClasesJava.*;
import javax.servlet.RequestDispatcher;

public class InsertarSolicitud extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperar los parámetros enviados desde el formulario

        int matricula = Integer.parseInt(request.getParameter("matricula"));
        String fecha = request.getParameter("fecha");

        String asunto = request.getParameter("asunto");
        String profesor = request.getParameter("idProfesor");
        int idProfesor = Integer.parseInt(profesor);
        String estado = "Pendiente";
        String comentario = null;
        // Obtener la cadena de hora del formulario
        String hora = request.getParameter("hora");
        String materia = request.getParameter("materia");

        if (hora.length() == 5) {
            hora += ":00";
        } else if (hora.length() == 4) {
            hora += ":00:00";
        }
        java.sql.Time tiempo = java.sql.Time.valueOf(hora);
        // Insertar la solicitud en la base de datos
        int id_solicitud = Consultas.insertarSolicitud(matricula, fecha, tiempo, asunto, idProfesor, estado, comentario, materia);
        // Enviar una respuesta al cliente
        if (id_solicitud != -1) {
            // Agregar el ID de la solicitud como un atributo de la solicitud
            request.setAttribute("id_solicitud", id_solicitud);
            // Redirigir a una página JSP en caso de éxito
            RequestDispatcher dispatcher = request.getRequestDispatcher("exito.jsp");
            dispatcher.forward(request, response);

        } else {
            response.getWriter().println("Error al insertar la solicitud");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si el servlet no admite el método GET, podrías redirigir a una página de error o realizar alguna otra acción
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Este servlet solo admite solicitudes POST");
    }
}
