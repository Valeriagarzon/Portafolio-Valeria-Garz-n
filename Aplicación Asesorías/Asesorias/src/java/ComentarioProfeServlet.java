
import ClasesJava.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ComentarioProfeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String id_profesor = request.getParameter("id_profesor");
        List<SolicitudProfesor> solicitudes = new ArrayList<>();
        
        try (Connection con = ConectaDB.obtenConexion(); PreparedStatement ps = con.prepareStatement("SELECT * FROM profesores WHERE id_profesor=?")) {

            ps.setInt(1, Integer.parseInt(id_profesor));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Obtener el nombre del profesor
                    String nombreProfesor = rs.getString("nombre");
                    String apellidoPaternoProfe = rs.getString("apellido_paterno");
                    String apellidoMaternoProfe = rs.getString("apellido_materno");
                    // Realizar la consulta para obtener las solicitudes del profesor
                    
                    try (PreparedStatement ps2 = con.prepareStatement("SELECT * FROM solicitudes WHERE id_profesor = ?")) {
                        ps2.setString(1, id_profesor);
                        try (ResultSet rs2 = ps2.executeQuery()) {
                            while (rs2.next()) {
                                // Obtener la fecha del ResultSet
                                Date fechaOriginal = rs2.getDate("fecha_asesoria");
                                // Formatear la fecha en el formato deseado
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                String fechaFormateada = sdf.format(fechaOriginal);

                                // Crear objeto Solicitud y agregarlo a la lista
                                SolicitudProfesor solicitud = new SolicitudProfesor();
                                solicitud.setIdSolicitud(rs2.getInt("id_solicitud"));
                                solicitud.setFechaAsesoria(fechaFormateada);
                                solicitud.setHoraAsesoria(rs2.getTime("hora_asesoria"));
                                solicitud.setAsunto(rs2.getString("asunto"));
                                solicitud.setEstado(rs2.getString("estado"));
                                solicitud.setComentario_Profesor(rs2.getString("comentario_profesor"));
                                solicitud.setIdProfesor(rs2.getString("id_profesor"));
                                solicitud.setMatricula(rs2.getString("matricula"));
                                solicitud.setMatricula(rs2.getString("matricula"));
                                solicitud.setMateria(rs2.getString("materia"));

                                // Obtener la matrícula del ResultSet
                                String matricula = rs2.getString("matricula");
                                // Realizar una consulta a la tabla de alumnos para obtener el nombre del alumno
                                String nombreAlumno = null;
                                String apellidoPaterno = null;
                                String apellidoMaterno = null;
                                String idProgramaEdu = null;
                                try (PreparedStatement ps3 = con.prepareStatement("SELECT nombre, apellido_paterno, apellido_materno, id_programaedu FROM alumnos WHERE matricula = ?")) {
                                    ps3.setString(1, matricula);
                                    try (ResultSet rs3 = ps3.executeQuery()) {
                                        if (rs3.next()) {
                                            nombreAlumno = rs3.getString("nombre");
                                            apellidoPaterno = rs3.getString("apellido_paterno");
                                            apellidoMaterno = rs3.getString("apellido_materno");
                                            idProgramaEdu = rs3.getString("id_programaedu");
                                        }
                                    }
                                }
                                // Agregar el nombre apellidos y id programa edu del alumno al objeto Solicitud
                                solicitud.setNombreAlumno(nombreAlumno);
                                solicitud.setApellidoPaterno(apellidoPaterno);
                                solicitud.setApellidoMaterno(apellidoMaterno);
                                solicitud.setIdProgramaEdu(idProgramaEdu);

                                // Obtener la idProgramaEdu del ResultSet
                                String nombreProgramaEdu = null;
                                try (PreparedStatement ps4 = con.prepareStatement("SELECT nombre FROM programa_educativo WHERE id_programaedu = ?")) {
                                    ps4.setString(1, idProgramaEdu);
                                    try (ResultSet rs4 = ps4.executeQuery()) {
                                        if (rs4.next()) {
                                            nombreProgramaEdu = rs4.getString("nombre");
                                        }
                                    }
                                }
                                solicitud.setNombreProgramaEdu(nombreProgramaEdu);

                                String materia = rs2.getString("materia");
                                // Realizar la consulta para obtener la cantidad de materias
                                int cantidadMaterias = 0;
                                try (PreparedStatement ps5 = con.prepareStatement("SELECT COUNT(*) AS cantidad_materias "
                                        + "FROM inscritos i "
                                        + "INNER JOIN materias m ON i.id_materia = m.id_materia "
                                        + "INNER JOIN profesores p ON m.id_profesor = p.id_profesor "
                                        + "WHERE i.matricula = ? AND p.id_profesor = ? AND m.nombre = ?")) {
                                    ps5.setString(1, matricula);
                                    ps5.setString(2, id_profesor);
                                    ps5.setString(3, materia);

                                    try (ResultSet rs5 = ps5.executeQuery()) {
                                        if (rs5.next()) {
                                            cantidadMaterias = rs5.getInt("cantidad_materias");
                                        }
                                    }
                                }
                                // Establecer la cantidad de materias en la solicitud actual
                                solicitud.setCantidadMaterias(cantidadMaterias);

                                // Agregar la solicitud a la lista
                                solicitudes.add(solicitud);
                            }
                        }
                    }
                    // Agregar el nombre del profesor al conjunto de atributos
                request.setAttribute("nombreProfesor", nombreProfesor);
                request.setAttribute("apellidoPaternoProfesor", apellidoPaternoProfe);
                request.setAttribute("apellidoMaternoProfesor", apellidoMaternoProfe);

                }
            }

        } catch (NumberFormatException | SQLException e) {
            // Manejar o registrar el error según sea necesario
            e.printStackTrace();
        }

        // Obtener los parámetros del formulario
        String idSolicitud = request.getParameter("idSolicitud");
        String comentario = request.getParameter("comentario");
        String accion = request.getParameter("accion");

        if (idSolicitud != null && comentario != null && accion != null) {
            try (Connection con = ConectaDB.obtenConexion(); PreparedStatement ps = con.prepareStatement("UPDATE solicitudes SET comentario_profesor = ?, estado = ? WHERE id_solicitud = ?")) {

                // Establecer los parámetros en la consulta
                ps.setString(1, comentario);
                // Establecer el estado según la acción seleccionada
                if (accion.equals("aceptar")) {
                    ps.setString(2, "Aceptada");
                } else if (accion.equals("rechazar")) {
                    ps.setString(2, "Rechazada");
                }
                ps.setString(3, idSolicitud);

                // Ejecutar la consulta
                int filasActualizadas = ps.executeUpdate();

                // Verificar si se actualizó la solicitud correctamente
                if (filasActualizadas > 0) {
                    for (SolicitudProfesor solicitud : solicitudes) {
                        if (solicitud.getIdSolicitud() == Integer.parseInt(idSolicitud)) {
                            // Encontramos la solicitud correspondiente, actualizamos el comentario
                            solicitud.setComentario_Profesor(comentario);
                            // No necesitamos continuar el bucle, así que salimos
                            break;
                        }
                    }
                    // Luego, reenviamos la solicitud al JSP
                    request.setAttribute("solicitudes", solicitudes);
                    request.getRequestDispatcher("InicioProfesor.jsp").forward(request, response);

                }
            } catch (SQLException e) {
                // Manejar o registrar el error según sea necesario
                e.printStackTrace();
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    /*@Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>*/
}
