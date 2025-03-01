package ClasesJava;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Time;
import java.sql.ResultSetMetaData;

/**
 *
 * @author itzee
 */
public class Consultas {

    public static List<String[]> obtenerProgramas() {
        List<String[]> programas = new ArrayList<>();
        try {
            Connection conn = ConectaDB.obtenConexion();
            PreparedStatement ps = conn.prepareStatement("SELECT id_programaedu, nombre FROM programa_educativo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] programa = new String[2];
                programa[0] = rs.getString("id_programaedu");
                programa[1] = rs.getString("nombre");
                programas.add(programa);
            }
            ConectaDB.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programas;
    }

    public static List<String> obtenerNombresMateriasPorPrograma(String idPrograma) {
        List<String> nombresMaterias = new ArrayList<>();
        try {
            Connection conn = ConectaDB.obtenConexion();
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT nombre FROM materias WHERE id_programaedu = ?");
            ps.setString(1, idPrograma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombreMateria = rs.getString("nombre");
                nombresMaterias.add(nombreMateria);
            }
            // Cerrar ResultSet, PreparedStatement y Connection en este orden.
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombresMaterias;
    }

// Método para obtener el ID del programa educativo 
    public static int obtenerIDPrograma(String nombrePrograma) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int IDPrograma = 0;

        try {
            // Establecer conexión con la base de datos
            conn = ConectaDB.obtenConexion(); // Suponiendo que tienes una clase de conexión llamada ConexionDB

            // Consulta SQL para obtener el nombre del programa educativo
            String query = "SELECT id_programaedu FROM programa_educativo WHERE nombre = ?";

            // Preparar la declaración SQL
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, IDPrograma);

            // Ejecutar la consulta
            rs = stmt.executeQuery();

            // Verificar si se encontró el programa y obtener su nombre
            if (rs.next()) {
                IDPrograma = rs.getInt("id_programaedu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return IDPrograma;
    }

    public static Map<String, Integer> obtenerProfesoresPorMateria(String nombreMateria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Integer> profesores = new HashMap<>();

        try {
            conn = ConectaDB.obtenConexion();
            String query = "SELECT p.id_profesor, p.nombre, p.apellido_paterno, p.apellido_materno "
                    + "FROM profesores p "
                    + "INNER JOIN materias m ON p.id_profesor = m.id_profesor "
                    + "WHERE m.nombre = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreMateria);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idProfesor = rs.getInt("id_profesor");
                String nombre = rs.getString("nombre");
                String apellidoPaterno = rs.getString("apellido_paterno");
                String apellidoMaterno = rs.getString("apellido_materno");
                String nombreCompleto = nombre + " " + apellidoPaterno + " " + apellidoMaterno;
                profesores.put(nombreCompleto, idProfesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return profesores;
    }

    public static Time formatTime(java.sql.Time hora) {
        LocalTime localTime = hora.toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = localTime.format(formatter);
        return Time.valueOf(formattedTime);
    }

    public static int insertarSolicitud(int matricula, String fecha, java.sql.Time hora, String asunto, int idProfesor, String estado, String comentario,  String materia) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idSolicitud = -1; // Inicializar con un valor inválido

        try {
            // Establecer conexión con la base de datos
            conn = ConectaDB.obtenConexion();

            // Consulta SQL para insertar la solicitud
            String query = "INSERT INTO solicitudes (fecha_asesoria, hora_asesoria, asunto, estado, id_profesor, matricula, comentario_profesor, materia) VALUES (?, ?, ?, ?, ?, ?,?,?)";

            // Preparar la declaración SQL
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setDate(1, java.sql.Date.valueOf(fecha));
            stmt.setTime(2, formatTime(hora));
            stmt.setString(3, asunto);
            stmt.setString(4, estado);
            stmt.setInt(5, idProfesor);
            stmt.setInt(6, matricula);
            stmt.setString(7, comentario);
            stmt.setString(8, materia);

            // Ejecutar la consulta
            int filasInsertadas = stmt.executeUpdate();

            // Obtener las claves generadas por la base de datos
            rs = stmt.getGeneratedKeys();

            // Si se insertó un registro, obtener el id_asesoria
            if (filasInsertadas > 0 && rs.next()) {
                idSolicitud = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return idSolicitud;
    }

    public static List<Map<String, Object>> obtenerDetallesAsesorias(int matricula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> detallesAsesorias = new ArrayList<>();

        try {
            // Establecer conexión con la base de datos
            conn = ConectaDB.obtenConexion();

            // Consulta SQL para obtener los detalles de las solicitudes de asesoría
            String query = "SELECT * FROM solicitudes WHERE matricula = ?";

            // Preparar la declaración SQL
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, matricula);

            // Ejecutar la consulta
            rs = stmt.executeQuery();

            // Iterar sobre los resultados y agregarlos a la lista de detalles de asesorías
            while (rs.next()) {
                Map<String, Object> detallesSolicitud = new HashMap<>();
                detallesSolicitud.put("idSolicitud", rs.getInt("id_solicitud"));
                detallesSolicitud.put("fechaAsesoria", rs.getDate("fecha_asesoria"));
                detallesSolicitud.put("horaAsesoria", rs.getTime("hora_asesoria"));
                detallesSolicitud.put("asunto", rs.getString("asunto"));
                detallesSolicitud.put("idProfesor", rs.getInt("id_profesor"));
                detallesSolicitud.put("matricula", rs.getInt("matricula"));
                detallesSolicitud.put("estado", rs.getString("estado"));
                detallesSolicitud.put("comentarios", rs.getString("comentario_profesor"));
                detallesSolicitud.put("materia", rs.getString("materia"));
                // Agrega otros campos según tu esquema de base de datos

                detallesAsesorias.add(detallesSolicitud);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return detallesAsesorias;
    }

    // Método para obtener el nombre del profesor por su ID
    public static String obtenerNombreProfesor(int idProfesor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nombreProfesor = null;

        try {
            conn = ConectaDB.obtenConexion(); // Obtener la conexión a la base de datos
            String sql = "SELECT nombre, apellido_paterno, apellido_materno FROM profesores WHERE id_profesor = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProfesor);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String paterno = rs.getString("apellido_paterno");
                String materno = rs.getString("apellido_materno");
                nombreProfesor = nombre + " " + paterno + " " + materno;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar la excepción adecuadamente
            }
        }

        return nombreProfesor;
    }

    // Método para obtener el nombre del alumno por su matrícula
    public static String obtenerNombreAlumno(int matricula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nombreAlumno = null;

        try {
            conn = ConectaDB.obtenConexion(); // Obtener la conexión a la base de datos
            String sql = "SELECT nombre, apellido_paterno, apellido_materno FROM alumnos WHERE matricula = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, matricula);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String paterno = rs.getString("apellido_paterno");
                String materno = rs.getString("apellido_materno");
                nombreAlumno = nombre + " " + paterno + " " + materno;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar la excepción adecuadamente
            }
        }

        return nombreAlumno;
    }

    // Método para obtener el programa educativo por matrícula
    public static String obtenerProgramaEducativo(int matricula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String programaEducativo = null;

        try {
            conn = ConectaDB.obtenConexion(); // Obtener la conexión a la base de datos
            String sql = "SELECT pe.nombre FROM alumnos a JOIN programa_educativo pe ON a.id_programaedu = pe.id_programaedu WHERE a.matricula = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, matricula);
            rs = stmt.executeQuery();

            if (rs.next()) {
                programaEducativo = rs.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar la excepción adecuadamente
            }
        }

        return programaEducativo;
    }

    public static Map<String, Object> obtenerDetallesAlumno(int matricula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> detallesAlumno = new HashMap<>();

        try {
            // Establecer conexión con la base de datos
            conn = ConectaDB.obtenConexion();

            // Consulta SQL para obtener los detalles del alumno
            String query = "SELECT a.nombre, a.apellido_paterno, a.apellido_materno, a.id_programaedu, p.nombre AS nombre_programa "
                    + "FROM alumnos a "
                    + "INNER JOIN programa_educativo p ON a.id_programaedu = p.id_programaedu "
                    + "WHERE a.matricula = ?";

            // Preparar la declaración SQL
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, matricula);

            // Ejecutar la consulta
            rs = stmt.executeQuery();

            // Verificar si se encontró el alumno con la matrícula especificada
            if (rs.next()) {
                detallesAlumno.put("matricula", matricula);
                detallesAlumno.put("nombre", rs.getString("nombre"));
                detallesAlumno.put("apellidoPaterno", rs.getString("apellido_paterno"));
                detallesAlumno.put("apellidoMaterno", rs.getString("apellido_materno"));
                detallesAlumno.put("programaEducativo", rs.getString("nombre_programa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return detallesAlumno;
    }

}
