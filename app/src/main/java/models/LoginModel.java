package models;

import java.io.Serializable;

public class LoginModel implements Serializable {
    private Long id;
    private Long idUsuario;
    private Long fechaYhora;

    public LoginModel (Long id, Long idUsuario, Long fechaYhora){
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaYhora = fechaYhora;
    }

    public LoginModel (){

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

    public Long getFechaYhora() {
        return fechaYhora;
    }

    public void setFechaYhora(Long fechaYhora) {
        this.fechaYhora = fechaYhora;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", fechaYhora=" + fechaYhora +
                '}';
    }
}
