<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilosLogin.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Exo+2:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <title>Inicio</title> 
    </head>
    <body>
        <div class="main">  	
            <input type="checkbox" id="chk" aria-hidden="true">
            <div class="login">
                <div class="form">
                    <div class="logo-container">
                        <img src="estudiante.png" alt="Estudiante" class="logo">
                        <label for="chk" aria-hidden="true">Estudiante</label>
                    </div>
                    <div id="welcome-student">
                        <h3>Bienvenido</h3>
                        <p>Ingresa tu matricula para ver el estado de tus asesorias.</p>
                    </div>
                    <form class="input-group" action="ConsultarAsesoria" method="post">
                        <input type="text" name="matricula" class="input" placeholder="Matrícula de estudiante">
                        <input class="button-student" type="submit" value="Consultar">
                    </form>
                    <div class="button-student"><a  href="FormAsesoria.jsp">Solicitar Asesoria</a></div>
                </div>
            </div>

            <div class="register">
                <div class="form">
                    <div class="logo-container">
                        <img src="profesor.png" alt="Profesor" class="logo">
                        <label for="chk" aria-hidden="true">Profesor</label>
                    </div>
                    <div id="welcome-teacher">
                        <h3>Bienvenido</h3>
                        <p>Ingresa tu identificador de docente para consultar tus asesorias.</p>
                    </div>
                    <form class="input-group" action="AccesoProfesorServlet" method="POST">
                        <input type="text" class="input" name="id_profesor" placeholder="ID de profesor">
                        <input class="button-teacher" type="submit" value="Acceder">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

