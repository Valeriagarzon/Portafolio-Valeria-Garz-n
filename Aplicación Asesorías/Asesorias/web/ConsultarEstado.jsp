<%-- 
    Document   : ConsultarEstado
    Created on : 1 may 2024, 22:11:14
    Author     : itzee
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Detalles de la Solicitud</title>
        <link rel="stylesheet" href="estilosSolicitud.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Exo+2:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="encabezado">          
            <div class="encabezado-contenedor">               
                <img src="estado.png" alt="estadoSolicitud" style="width: 80px; height: auto;">
                <h1>Solicitudes</h1>
            </div>
        </div>
        <%
            Map<String, Object> detallesAlumno = (Map<String, Object>) request.getAttribute("detallesAlumno");
            if (detallesAlumno != null && !detallesAlumno.isEmpty()) {
        %>
        <div  style="padding-left: 110px; padding-right: 130px; display: flex; flex-direction: row; justify-content: space-between; align-items: flex-start;">
            <div>
                <div class="button" style="padding-top: 60px; align-self: flex-start;"><a href="index.jsp">Regresar al inicio</a></div>
            </div>
            <div>
                <p><strong>Matrícula:</strong> <%= detallesAlumno.get("matricula")%></p>
                <p><strong>Nombre:</strong> <%= detallesAlumno.get("nombre")%> <%= detallesAlumno.get("apellidoPaterno")%> <%= detallesAlumno.get("apellidoMaterno")%></p>
                <p><strong>Programa Educativo:</strong> <%= detallesAlumno.get("programaEducativo")%></p>
            </div>
  
        </div>
        <%
            List<Map<String, Object>> detallesSolicitudes = (List<Map<String, Object>>) request.getAttribute("detallesSolicitudes");
            if (detallesSolicitudes != null && !detallesSolicitudes.isEmpty()) {
        %>
        <div class="consulta">
            <table>
                <tr>
                    <th>ID de Solicitud</th>
                    <th>Fecha de Asesoría</th>
                    <th>Hora de Asesoría</th>
                    <th>Materia</th>
                    <th>Asunto</th>
                    <th>Estado</th>
                    <th>Profesor</th>
                    <th>Comentarios</th>
                </tr>
                <%
                    List<String> nombresProfesores = (List<String>) request.getAttribute("nombresProfesores");
                    if (detallesSolicitudes != null && !detallesSolicitudes.isEmpty() && nombresProfesores != null && !nombresProfesores.isEmpty()) {
                        // Iterar sobre todas las solicitudes de asesoría
                        for (int i = 0; i < detallesSolicitudes.size(); i++) {
                            Map<String, Object> solicitud = detallesSolicitudes.get(i);
                            String nombreProfesor = nombresProfesores.get(i); // Obtener el nombre del profesor correspondiente
%>
                <tr>
                    <td><%= solicitud.get("idSolicitud")%></td>
                    <td><%= solicitud.get("fechaAsesoria")%></td>
                    <td><%= solicitud.get("horaAsesoria")%></td>
                    <td><%= solicitud.get("materia")%></td>
                    <td><%= solicitud.get("asunto")%></td>
                    <td><%= solicitud.get("estado")%></td>
                    <td><%= nombreProfesor%></td> 
                    <td><%= solicitud.get("comentarios") != null ? solicitud.get("comentarios") : "-" %></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
        </div>
        <%
        } else {
        %>
        <p>No se encontraron detalles del alumno.</p>
        <%
            }
        %>
        <%
        } else {
        %>
        <p>No se encontró ninguna solicitud con el ID especificado.</p>
        <%
            }
        %>
    </body>
</html>
