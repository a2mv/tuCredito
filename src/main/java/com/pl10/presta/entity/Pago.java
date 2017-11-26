package com.pl10.presta.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pagos")
public class Pago implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true, nullable = false, updatable = false)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credito", nullable = false)
    private Credito credito;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecha;
    @Column(name = "cuotas", nullable = true)
    private Integer cuotas;
    @Column(name = "valor", nullable = true, precision=8, scale=2)
    private Double valor;

    public Pago() {
    }

    public Pago(Credito credito, Date fecha, Integer cuotas, Double valor) {
        this.credito = credito;
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

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
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
        return "Pago{" +
                "id='" + id + '\'' +
                ", credito=" + credito +
                ", fecha=" + fecha +
                ", cuotas=" + cuotas +
                ", valor=" + valor +
                '}';
    }
}
