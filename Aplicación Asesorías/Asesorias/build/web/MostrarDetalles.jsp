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
            <form action="InsertarSolicitud" method="post">
                <p><strong>Nombre:</strong> <%= request.getAttribute("nombre")%></p>
                <p><strong>Matrícula:</strong> <%= request.getAttribute("matricula")%></p>
                <p><strong>Programa Educativo:</strong> <%= request.getAttribute("nombrePrograma")%></p>
                <p><strong>Materia:</strong><%= request.getAttribute("materia")%></p>
                <p><strong>Profesor:</strong> <%= request.getAttribute("nombreProfesor")%></p>
                <p><strong>Fecha:</strong> <%= request.getAttribute("fecha")%></p>
                <p><strong>Hora:</strong> <%= request.getAttribute("hora")%></p>
                <p><strong>Asunto:</strong> <%= request.getAttribute("asunto")%></p>

                
                <input type="hidden" name="matricula" value="<%= request.getAttribute("matricula")%>">
                <input type="hidden" name="idProfesor" value="<%= request.getAttribute("idProfesor")%>">
                <input type="hidden" name="profesor" value="<%= request.getAttribute("nombreProfesor")%>">
                <input type="hidden" name="fecha" value="<%= request.getAttribute("fecha")%>">
                <input type="hidden" name="hora" value="<%= request.getAttribute("hora")%>">
                <input type="hidden" name="asunto" value="<%= request.getAttribute("asunto")%>">
                
                <input type="hidden" name="materia" value="<%= request.getAttribute("materia")%>">

                <input type="submit" value="Enviar Solicitud">
            </form>
        </div>
    </body>
</html>

