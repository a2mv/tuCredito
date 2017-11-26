package com.pl10.presta.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente implements java.io.Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true, nullable = false, updatable = false)
    private String id;
    @Column(name = "ndocumento", nullable = false, length = 60)
    private String ndocumento;
    @Column(name = "fullname", nullable = true, length = 45)
    private String fullname;
    @Column(name = "telefono", nullable = true)
    private String telefono;
    @Column(name = "direccion", nullable = true)
    private String direccion;
    @Column(name = "observacion", columnDefinition="TEXT")
    private String observacion;

    public Cliente() {
    }

    public Cliente(String ndocumento, String fullname, String telefono, String direccion, String observacion) {
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
        return "Cliente{" +
                "id='" + id + '\'' +
                ", ndocumento='" + ndocumento + '\'' +
                ", fullname='" + fullname + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", observacion='" + observacion + '\'' +
                '}';
    }
}
