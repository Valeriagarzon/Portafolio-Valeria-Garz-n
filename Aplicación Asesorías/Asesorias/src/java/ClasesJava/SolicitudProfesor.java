package ClasesJava;

import java.sql.Time;

public class SolicitudProfesor {
    private int idSolicitud;
    private String fechaAsesoria;
    private Time horaAsesoria;
    private String asunto;
    private String estado;
    private String comentario_profesor;
    private String idProfesor;
    private String matricula;
    private String materia;
    
    private String nombreAlumno;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String idProgramaEdu;
    
    private String nombreProgramaEdu;
    
    private int cantidadMaterias;

    // Métodos getters y setters
    
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    public String getFechaAsesoria() {
        return fechaAsesoria;
    }

    public void setFechaAsesoria(String fechaAsesoria) {
        this.fechaAsesoria = fechaAsesoria;
    }

    public Time getHoraAsesoria() {
        return horaAsesoria;
    }

    public void setHoraAsesoria(Time horaAsesoria) {
        this.horaAsesoria = horaAsesoria;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getComentario_Profesor() {
        return comentario_profesor;
    }

    public void setComentario_Profesor(String comentario_profesor) {
        this.comentario_profesor = comentario_profesor;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }
    
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    public String getIdProgramaEdu() {
        return idProgramaEdu;
    }

    public void setIdProgramaEdu(String idProgramaEdu) {
        this.idProgramaEdu = idProgramaEdu;
    }
    
    public String getNombreProgramaEdu() {
        return nombreProgramaEdu;
    }

    public void setNombreProgramaEdu(String nombreProgramaEdu) {
        this.nombreProgramaEdu = nombreProgramaEdu;
    }
    
    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
    
     public int getCantidadMaterias() {
        return cantidadMaterias;
    }

    public void setCantidadMaterias(int cantidadMaterias) {
        this.cantidadMaterias = cantidadMaterias;
    }
}

