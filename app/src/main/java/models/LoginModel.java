package models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public String getFechaYHora(String Format){
        Date d = new Date(this.fechaYhora);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Format);
        return dateFormat.format(d);
    }

    public Long getFechaYhora() {
        return fechaYhora;
    }

    public void setFechayHora(Date fechayHora){
        this.fechaYhora = fechayHora.getTime();
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
