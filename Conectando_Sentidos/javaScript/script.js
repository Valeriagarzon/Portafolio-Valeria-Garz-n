function mostrarVentana(contenido) {
  var ventana = document.getElementById("ventana");
  var fondo = document.getElementById("fondo");
  ventana.innerHTML = contenido;
  ventana.style.display = "none";
  fondo.style.display = "none";
}

function mostrarVentana(contenido) {
  var ventana = document.getElementById("ventana");
  var fondo = document.getElementById("fondo");
  ventana.innerHTML = contenido;
  ventana.style.display = "block"; 
  fondo.style.display = "block";   
}


var T1 = ["Terapeuta 1 <br><br>NRC: 52785<br><br>LUIS SANDOVAL HERNÁNDEZ <br>Organizador <br><br> <a href=reunion.html><span></span><span></span><span></span><span></span><input type=submit value=Iniciar_Reunion></a>"];
var T2 = ["Terapeuta 2 <br><br>NRC: 57814 <br><br>JOSE RAMIREZ ALFARO <br>Organizador <br><br> <a href=reunion.html><span></span><span></span><span></span><span></span><input type=submit value=Iniciar_Reunion></a>"];
var T3 = ["Terapeuta 3 <br><br>NRC: 87642 <br><br>ROBERTO ROBLEDO OLIVÉ <br>Organizador <br><br> <a href=reunion.html><span></span><span></span><span></span><span></span><input type=submit value=Iniciar_Reunion></a>"];
var T4 = ["Terapeuta 4 <br><br>NRC: 97842 <br><br>MAXIMILIANO TUR LARA <br>Organizador <br><br> <a href=reunion.html><span></span><span></span><span></span><span></span><input type=submit value=Iniciar_Reunion></a>"];
var T5 = ["Terapeuta 5 <br><br>NRC: 21378 <br><br>ERNESTO VIDAL PALACIOS <br>Organizador <br><br> <a href=reunion.html><span></span><span></span><span></span><span></span><input type=submit value=Iniciar_Reunion></a>"];
var tabla = document.getElementsByTagName("table")[0];
var tbody = document.getElementsByTagName("tbody")[0];
var tr = document.getElementsByTagName("tr")[0];
var td = document.getElementsByTagName("td")[0];
var i = 0;
