package com.pl10.presta.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class CreditoDetalleModel {

    private CreditoModel creditoModel;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date proximacuota;
    private Double valorcuota;
    /**
     * porcentaje adeudado
     */
    private Double faltante100;
    private Double faltante;
    private List<PagoModel> pagoModels;
    private Integer cuotasfaltantes;

    public CreditoDetalleModel() {
    }

    public CreditoDetalleModel(CreditoModel creditoModel, Date proximacuota, Double valorcuota, Double faltante100,
                               Double faltante, List<PagoModel> pagoModels, Integer cuotasfaltantes) {
        this.creditoModel = creditoModel;
        this.proximacuota = proximacuota;
        this.valorcuota = valorcuota;
        this.faltante100 = faltante100;
        this.faltante = faltante;
        this.pagoModels = pagoModels;
        this.cuotasfaltantes = cuotasfaltantes;
    }

    public CreditoModel getCreditoModel() {
        return creditoModel;
    }

    public void setCreditoModel(CreditoModel creditoModel) {
        this.creditoModel = creditoModel;
    }

    public Date getProximacuota() {
        return proximacuota;
    }

    public void setProximacuota(Date proximacuota) {
        this.proximacuota = proximacuota;
    }

    public Double getValorcuota() {
        return valorcuota;
    }

    public void setValorcuota(Double valorcuota) {
        this.valorcuota = valorcuota;
    }

    public Double getFaltante100() {
        return faltante100;
    }

    public void setFaltante100(Double faltante100) {
        this.faltante100 = faltante100;
    }

    public Double getFaltante() {
        return faltante;
    }

    public void setFaltante(Double faltante) {
        this.faltante = faltante;
    }

    public List<PagoModel> getPagoModels() {
        return pagoModels;
    }

    public void setPagoModels(List<PagoModel> pagoModels) {
        this.pagoModels = pagoModels;
    }

    public Integer getCuotasfaltantes() {
        return cuotasfaltantes;
    }

    public void setCuotasfaltantes(Integer cuotasfaltantes) {
        this.cuotasfaltantes = cuotasfaltantes;
    }

    @Override
    public String toString() {
        return "CreditoDetalleModel{" +
                "creditoModel=" + creditoModel +
                ", proximacuota=" + proximacuota +
                ", valorcuota=" + valorcuota +
                ", faltante100=" + faltante100 +
                ", faltante=" + faltante +
                ", pagoModels=" + pagoModels +
                ", cuotasfaltantes=" + cuotasfaltantes +
                '}';
    }
}
