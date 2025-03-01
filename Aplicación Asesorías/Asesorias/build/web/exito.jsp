<%-- 
    Document   : exito
    Created on : 30 abr 2024, 23:02:01
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
        <title>Asesoría</title>
        <link rel="stylesheet" href="estilosForm.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Exo+2:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <h1></h1>
        <div class="container">  
                    <div>
                        <img src="enviado.png" alt="solicitudEnviada"  style="width: 100px; height: auto;">
                    </div>
                <strong>Solicitud de asesoria enviada correctamente!</strong>
                <p>Puedes consultar el estado de tu solicitud en el inicio.</p>
                <p>El ID de la solicitud es: <%= request.getAttribute("id_solicitud") %></p>
                <div class="button"><a href="index.jsp">Regresar al inicio</a></div>
        </div>
    </body>
</html>


