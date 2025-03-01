<%-- 
    Document   : DatosMatricula
    Created on : 5 may 2024, 22:25:32
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
            <form action="MateriasServlet" method="post">

                <p><strong>Nombre: </strong>${detallesAlumno.nombre} ${detallesAlumno.apellidoPaterno} ${detallesAlumno.apellidoMaterno}</p>
                <p><strong>Matrícula: </strong> ${detallesAlumno.matricula}</p>
                <p><strong>Programa Educativo: </strong> ${detallesAlumno.programaEducativo}</p>
                
                <input hidden name="nombre" value="${detallesAlumno.nombre} ${detallesAlumno.apellidoPaterno} ${detallesAlumno.apellidoMaterno}">
                <input hidden name="matricula" value="${detallesAlumno.matricula}">
                <input hidden name="nombrePrograma" value="${detallesAlumno.programaEducativo}">

                <input type="submit" value="Continuar">
            </form>
        </div>
    </body>
</html>

