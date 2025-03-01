
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ClasesJava.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ConsultarAsesoria")
public class ConsultarAsesoria extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el idSolicitud del formulario
        int matricula = Integer.parseInt(request.getParameter("matricula"));

        // Obtener los detalles del alumno asociado a la matrícula
        Map<String, Object> detallesAlumno = Consultas.obtenerDetallesAlumno(matricula);
        // Obtener los detalles de todas las solicitudes de asesoría asociadas a la matrícula
        List<Map<String, Object>> detallesSolicitudes = Consultas.obtenerDetallesAsesorias(matricula);

        // Obtener los ID de profesor de cada solicitud de asesoría
        List<Integer> idProfesores = new ArrayList<>();
        for (Map<String, Object> solicitud : detallesSolicitudes) {
            idProfesores.add((Integer) solicitud.get("idProfesor"));
        }

        // Obtener los nombres de los profesores correspondientes a los ID de profesor
        List<String> nombresProfesores = new ArrayList<>();
        for (int idProfesor : idProfesores) {
            String nombreProfesor = Consultas.obtenerNombreProfesor(idProfesor); // Ajusta según tu implementación
            nombresProfesores.add(nombreProfesor);
        }

        // Añadir los detalles del alumno al objeto request
        request.setAttribute("detallesAlumno", detallesAlumno);
        // Añadir los detalles de las solicitudes al objeto request
        request.setAttribute("detallesSolicitudes", detallesSolicitudes);
        // Añadir los nombres de los profesores al objeto request
        request.setAttribute("nombresProfesores", nombresProfesores);
        // Enviar la solicitud al JSP para que se muestren los detalles
        request.getRequestDispatcher("ConsultarEstado.jsp").forward(request, response);
    }
}
