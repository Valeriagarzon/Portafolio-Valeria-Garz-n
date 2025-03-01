<%-- 
    Document   : Materias.jsp
    Created on : 30 abr 2024, 16:59:50
    Author     : itzee
--%>

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
            <form action="ProfesoresServlet" method="post">

                <p><strong>Nombre: </strong><%= request.getAttribute("nombre")%></p>
                <p><strong>Matrícula: </strong><%= request.getAttribute("matricula")%> </p>
                <p><strong>Programa Educativo: </strong><%= request.getAttribute("nombrePrograma")%></p>
                <label for="materia">Materia:</label><br>
                <select id="materia" name="materia" required>
                    <option value="">Selecciona una materia</option>
                    <% List<String> materias = (List<String>) request.getAttribute("materias");
                        for (String materia : materias) {%>
                    <option value="<%= materia%>"><%= materia%></option>
                    <% }%>
                </select><br>

                <input hidden name="nombre" value="<%= request.getAttribute("nombre")%>">
                <input hidden name="matricula" value="<%= request.getAttribute("matricula")%>">
                <input hidden name="nombrePrograma" value="<%= request.getAttribute("nombrePrograma")%>">

                <input type="submit" value="Siguiente">
            </form>
        </div>
    </body>
</html>

