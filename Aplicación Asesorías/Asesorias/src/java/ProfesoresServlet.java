
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ClasesJava.*;
import java.util.Map;

@WebServlet(name = "ConsultarProfesoresServlet", urlPatterns = {"/ConsultarProfesoresServlet"})
public class ProfesoresServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Recuperar los datos del formulario
            String nombre = request.getParameter("nombre");
            String matricula = request.getParameter("matricula");
            String nombrePrograma = request.getParameter("nombrePrograma");
            String nombreMateria = request.getParameter("materia");

            // Realizar la consulta a la base de datos para obtener los profesores de la materia
            Map<String, Integer> profesoresMap = Consultas.obtenerProfesoresPorMateria(nombreMateria); 

            // Configurar los atributos para pasar al JSP
            request.setAttribute("nombre", nombre);
            request.setAttribute("matricula", matricula);
            request.setAttribute("nombrePrograma", nombrePrograma);
            request.setAttribute("materia", nombreMateria);
            request.setAttribute("profesoresMap", profesoresMap);

            // Redirigir al JSP para mostrar los resultados
            request.getRequestDispatcher("Profesores.jsp").forward(request, response);
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
