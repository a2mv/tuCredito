package com.pl10.presta.model;

import com.pl10.presta.enums.CreditoStatus;
import com.pl10.presta.enums.CreditoType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CreditoModel {

    private String id;
    private ClienteModel clienteModel;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date fecha;
    private Double valor;
    private Double taza;
    private CreditoType creditoType;
    private Integer cuotas;
    private CreditoStatus creditoStatus;

    public CreditoModel() {
    }

    public CreditoModel(String id, ClienteModel clienteModel, Date fecha, Double valor, Double taza, CreditoType creditoType, Integer cuotas, CreditoStatus creditoStatus) {
        this.id = id;
        this.clienteModel = clienteModel;
        this.fecha = fecha;
        this.valor = valor;
        this.taza = taza;
        this.creditoType = creditoType;
        this.cuotas = cuotas;
        this.creditoStatus = creditoStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getTaza() {
        return taza;
    }

    public void setTaza(Double taza) {
        this.taza = taza;
    }

    public CreditoType getCreditoType() {
        return creditoType;
    }

    public void setCreditoType(CreditoType creditoType) {
        this.creditoType = creditoType;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public CreditoStatus getCreditoStatus() {
        return creditoStatus;
    }

    public void setCreditoStatus(CreditoStatus creditoStatus) {
        this.creditoStatus = creditoStatus;
    }

    @Override
    public String toString() {
        return "CreditoModel{" +
                "id='" + id + '\'' +
                ", clienteModel=" + clienteModel +
                ", fecha=" + fecha +
                ", valor=" + valor +
                ", taza=" + taza +
                ", creditoType=" + creditoType +
                ", cuotas=" + cuotas +
                ", creditoStatus=" + creditoStatus +
                '}';
    }
}
