
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetallesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Recuperar los datos del formulario
            String nombre = request.getParameter("nombre");
            String matricula = request.getParameter("matricula");
            String idPrograma = request.getParameter("id_programa");
            String nombrePrograma = request.getParameter("nombrePrograma");
            String idMateria = request.getParameter("id_materia");
            String materia = request.getParameter("materia");
            
            String profesor = request.getParameter("profesor");
            String[] parts = profesor.split("_");
            int idProfesor = Integer.parseInt(parts[0]);
            String nombreProfesor = parts[1];
            
            String fecha = request.getParameter("fecha");
            String hora = request.getParameter("hora");
            String asunto = request.getParameter("asunto");
            String eresAlumno = request.getParameter("eres_alumno");

            // Configurar los atributos para pasar al JSP
            request.setAttribute("nombre", nombre);
            request.setAttribute("matricula", matricula);
            request.setAttribute("idPrograma", idPrograma);
            request.setAttribute("nombrePrograma", nombrePrograma);
            request.setAttribute("idMateria", idMateria);
            request.setAttribute("materia", materia);
            request.setAttribute("idProfesor", idProfesor);
            request.setAttribute("nombreProfesor", nombreProfesor);
            request.setAttribute("fecha", fecha);
            request.setAttribute("hora", hora);
            request.setAttribute("asunto", asunto);
            request.setAttribute("eresAlumno", eresAlumno);

            // Redirigir al JSP para mostrar los detalles
            request.getRequestDispatcher("MostrarDetalles.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
