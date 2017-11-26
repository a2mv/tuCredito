package com.pl10.presta.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PagoModel {

    private String id;
    private CreditoModel creditoModel;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecha;
    private Integer cuotas;
    private Double valor;

    public PagoModel() {
    }

    public PagoModel(String id, CreditoModel creditoModel, Date fecha, Integer cuotas, Double valor) {
        this.id = id;
        this.creditoModel = creditoModel;
        this.fecha = fecha;
        this.cuotas = cuotas;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CreditoModel getCreditoModel() {
        return creditoModel;
    }

    public void setCreditoModel(CreditoModel creditoModel) {
        this.creditoModel = creditoModel;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "PagoModel{" +
                "id='" + id + '\'' +
                ", creditoModel=" + creditoModel +
                ", fecha=" + fecha +
                ", cuotas=" + cuotas +
                ", valor=" + valor +
                '}';
    }
}
