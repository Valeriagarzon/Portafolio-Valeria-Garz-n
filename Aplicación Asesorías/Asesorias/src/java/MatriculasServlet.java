import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import ClasesJava.*;
import java.util.Map;

public class MatriculasServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la matrícula del parámetro en la URL
        String matriculaStr = request.getParameter("matricula");
        int matricula = Integer.parseInt(matriculaStr);

        // Llamar al método para obtener los detalles del alumno
        Map<String, Object> detallesAlumno = Consultas.obtenerDetallesAlumno(matricula);

        // Guardar los detalles del alumno como un atributo en el objeto request
        request.setAttribute("detallesAlumno", detallesAlumno);

        // Redirigir la solicitud al JSP Materias.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("DatosMatricula.jsp");
        dispatcher.forward(request, response);
    }
}
