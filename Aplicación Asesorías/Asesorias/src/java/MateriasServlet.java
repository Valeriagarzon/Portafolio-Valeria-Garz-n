
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ClasesJava.*;


public class MateriasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Recuperar los datos del formulario
        String nombre = request.getParameter("nombre");
        String matricula = request.getParameter("matricula");
        String nombrePrograma = request.getParameter("nombrePrograma");
        
        int IDPrograma = Consultas.obtenerIDPrograma(nombrePrograma); 
        // Convertir el ID del programa educativo a String
        String IDProgramaStr = String.valueOf(IDPrograma);
        List<String> materias = Consultas.obtenerNombresMateriasPorPrograma(IDProgramaStr);

        // Setear los datos en el request para pasarlos al JSP
        request.setAttribute("nombre", nombre);
        request.setAttribute("matricula", matricula);
        request.setAttribute("nombrePrograma", nombrePrograma);
        request.setAttribute("materias", materias);

        // Redirigir al JSP para mostrar los datos
        request.getRequestDispatcher("Materias.jsp").forward(request, response);
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
