package com.pl10.presta.model;

public class ClienteModel {

    private String id;
    private String ndocumento;
    private String fullname;
    private String telefono;
    private String direccion;
    private String observacion;

    public ClienteModel() {
    }

    public ClienteModel(String id, String ndocumento, String fullname, String telefono, String direccion, String observacion) {
        this.id = id;
        this.ndocumento = ndocumento;
        this.fullname = fullname;
        this.telefono = telefono;
        this.direccion = direccion;
        this.observacion = observacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNdocumento() {
        return ndocumento;
    }

    public void setNdocumento(String ndocumento) {
        this.ndocumento = ndocumento;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "ClienteModel{" +
                "id='" + id + '\'' +
                ", ndocumento='" + ndocumento + '\'' +
                ", fullname='" + fullname + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", observacion='" + observacion + '\'' +
                '}';
    }
}
