package models;

import java.io.Serializable;

public class UsuarioModel implements Serializable {
    private Long id;
    private String nombreusuario;
    private String mail;
    private String clave;
    private boolean estado;

    public UsuarioModel(Long id, String nombreusuario, String mail, String clave, boolean estado) {
        this.id = id;
        this.nombreusuario = nombreusuario;
        this.mail = mail;
        this.clave = clave;
        this.estado = estado;
    }

    public UsuarioModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "id=" + id +
                ", nombreusuario='" + nombreusuario + '\'' +
                ", mail='" + mail + '\'' +
                ", clave='" + clave + '\'' +
                ", estado=" + estado +
                '}';
    }
}
