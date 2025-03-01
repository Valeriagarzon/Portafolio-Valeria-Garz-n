<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="ClasesJava.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de Asesoría</title>
        <link rel="stylesheet" href="estilosForm.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Exo+2:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1>Formulario de Asesoría</h1>
        <div class="container">
            <form action="DetallesServlet" method="post">

                <p><strong>Nombre:</strong> <%= request.getAttribute("nombre")%></p>
                <p><strong>Matrícula:</strong> <%= request.getAttribute("matricula")%></p>
                <p><strong>Programa Educativo:</strong> <%= request.getAttribute("nombrePrograma")%></p>
                <p><strong>Materia:</strong> <%= request.getAttribute("materia")%></p>

                <label for="profesor">Profesor:</label><br>
                <select id="profesor" name="profesor" required>
                    <option value="">Selecciona un profesor</option>
                    <%
                        Map<String, Integer> profesoresMap = (Map<String, Integer>) request.getAttribute("profesoresMap");
                        for (Map.Entry<String, Integer> entry : profesoresMap.entrySet()) {
                            String nombreProfesor = entry.getKey();
                            int idProfesor = entry.getValue();
                    %>
                    <option value="<%= idProfesor%>_<%= nombreProfesor%>"><%= nombreProfesor%></option>
                    <% }%>
                </select><br>

                <label for="fecha">Fecha:</label><br>
                <input type="date" id="fecha" name="fecha" required><br>

                <label for="hora">Hora:</label><br>
                <input type="time" id="hora" name="hora" required><br>

                <label for="asunto">Asunto:</label><br>
                <textarea id="asunto" name="asunto" required></textarea><br>


                <input hidden name="nombre" value="<%= request.getAttribute("nombre")%>">
                <input hidden name="matricula" value="<%= request.getAttribute("matricula")%>">
                <input hidden name="nombrePrograma" value="<%= request.getAttribute("nombrePrograma")%>">
                <input type="hidden" name="id_materia" value="<%= request.getAttribute("id_materia")%>">
                <input hidden name="materia" value="<%= request.getAttribute("materia")%>">
                <input hidden name="profesor" value="<%= request.getAttribute("profesor")%>">

                <input type="submit" value="Siguiente">
            </form>
        </div>
    </body>
</html>

