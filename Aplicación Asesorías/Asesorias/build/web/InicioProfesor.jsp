<%@page import="ClasesJava.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="ProfesorInicio.css"> <!-- Link to your CSS file -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Solicitudes del Profesor</title>
    </head>
    <body>

        <div class="bienvenida">
            <h1>Solicitudes del Profesor</h1>
            <h3>Bienvenido Profesor</h3>
            <p><%= request.getAttribute("nombreProfesor") + " " + request.getAttribute("apellidoPaternoProfesor") + " " + request.getAttribute("apellidoMaternoProfesor")%></p>
        </div>

        <div class="request-grid">
            <%
                List<SolicitudProfesor> solicitudes = (List<SolicitudProfesor>) request.getAttribute("solicitudes");
                for (SolicitudProfesor solicitud : solicitudes) {
                    String esAlumno = (solicitud.getCantidadMaterias() == 1) ? "SI" : "NO";
            %>
            <div class="request-container">
                <div class="student-estado">Estado: <%= solicitud.getEstado()%></div>
                <div class="student-matricula">Matricula: <%= solicitud.getMatricula()%></div>
                <div class="student-name">Nombre del Alumno: <%= solicitud.getNombreAlumno() + " " + solicitud.getApellidoPaterno() + " " + solicitud.getApellidoMaterno()%></div>
                <div class="student-progedu">Programa Educativo: <%= solicitud.getNombreProgramaEdu()%></div>
                <div class="student-fecha">Fecha Asesoría: <%= solicitud.getFechaAsesoria()%></div>
                <div class="student-hora">Hora Asesoría: <%= solicitud.getHoraAsesoria()%></div>
                <div class="student-asunto">Asunto: <%= solicitud.getAsunto()%></div>
                <div class="student-materia">Materia: <%= solicitud.getMateria()%></div>
                <div class="student-pregunta">¿Es alumno?: <%= esAlumno%></div>
                <div class="student-coment">
                    <%-- Verificar si hay un comentario asociado a la solicitud --%>
                    <% String comentario = solicitud.getComentario_Profesor();
        if (comentario != null && !comentario.isEmpty()) {%>
                    Comentario: <%= comentario%>
                    <% } else {%>
                    <%-- Mostrar el textarea para agregar un comentario --%>
                    <form id="comentarioForm" action="ComentarioProfeServlet" method="POST" onsubmit="return validarComentario()">
                        <input type="hidden" name="id_profesor" value="${id_profesor}">
                        <input type="hidden" name="idSolicitud" value="<%= solicitud.getIdSolicitud()%>">
                        <textarea id="comentarioInput" class="comment-textarea" name="comentario" rows="3" cols="20"></textarea>
                        <div class="action-buttons">
                            <button type="submit" name="accion" value="aceptar">Aceptar <i class="fa fa-check" style="color: white;"></i></button>
                            <button type="submit" name="accion" value="rechazar">Rechazar <i class="fa fa-close" style="color: white;"></i></button>
                        </div>
                    </form>
                    <% }%>
                    <script>
                        function validarComentario() {
                            var comentario = document.getElementById("comentarioInput").value.trim();
                            if (comentario === "") {
                                alert("El comentario no puede estar vacío.");
                                return false; // Evita que se envíe el formulario
                            }
                            return true; // Permite enviar el formulario si el comentario no está vacío
                        }
                    </script>
                </div>
            </div>
            <% }%>
        </div>

    </body>

</html>
