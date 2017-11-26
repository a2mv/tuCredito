package com.pl10.presta.entity;

import com.pl10.presta.enums.CreditoStatus;
import com.pl10.presta.enums.CreditoType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import sun.security.util.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "credito")
public class Credito implements java.io.Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true, nullable = false, updatable = false)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecha;
    @Column(name = "valor", nullable = true, precision=8, scale=2)
    private Double valor;
    @Column(name = "taza", nullable = true, precision=2, scale=2)
    private Double taza;
    @Column(name = "credito_type", nullable = false)
    private CreditoType creditoType;
    @Column(name = "cuotas", nullable = true)
    private Integer cuotas;
    @Column(name = "credito_status", nullable = true)
    private CreditoStatus creditoStatus;

    public Credito() {
    }

    public Credito(Cliente cliente, Date fecha, Double valor, Double taza, CreditoType creditoType, Integer cuotas, CreditoStatus creditoStatus) {
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        return "Credito{" +
                "id='" + id + '\'' +
                ", cliente=" + cliente +
                ", fecha=" + fecha +
                ", valor=" + valor +
                ", taza=" + taza +
                ", creditoType=" + creditoType +
                ", cuotas=" + cuotas +
                ", creditoStatus=" + creditoStatus +
                '}';
    }
}
