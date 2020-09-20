package models;

import java.io.Serializable;

public class EstacionamientoModel implements Serializable {

    private Long id;
    private Long idUsuario;
    private String patente;
    private Long duracion;
    private Boolean estado;

    public EstacionamientoModel(Long id, Long idUsuario, String patente, Long duracion, Boolean estado){
        this.id = id;
        this.idUsuario = idUsuario;
        this.patente = patente;
        this.duracion = duracion;
        this.estado = estado;
    }

    public EstacionamientoModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EstacionamientoModel{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", patente='" + patente + '\'' +
                ", duracion=" + duracion +
                ", estado=" + estado +
                '}';
    }
    
}
