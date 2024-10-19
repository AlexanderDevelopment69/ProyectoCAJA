package com.mycompany.proyectocaja.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Caja {

    private int id;
    private String identificador;
    private String estado; // 'disponible' o 'ocupada'
    private BigDecimal saldoInicial;
    private BigDecimal saldoFinal;
    private Timestamp fechaApertura;
    private Timestamp fechaCierre;
    private int cajeroId; // Nuevo atributo para relacionar con el usuario

    // Constructor vacío
    public Caja() {}

    // Constructor completo
    public Caja(int id, String identificador, String estado, BigDecimal saldoInicial,
                BigDecimal saldoFinal, Timestamp fechaApertura, Timestamp fechaCierre, int cajeroId) {
        this.id = id;
        this.identificador = identificador;
        this.estado = estado;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.cajeroId = cajeroId; // Inicializar el nuevo atributo
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public Timestamp getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Timestamp fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Timestamp getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Timestamp fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public int getCajeroId() {
        return cajeroId; // Getter para el nuevo atributo
    }

    public void setCajeroId(int cajeroId) {
        this.cajeroId = cajeroId; // Setter para el nuevo atributo
    }

    // Método para mostrar una descripción de la caja
    @Override
    public String toString() {
        return "Caja{" +
                "id=" + id +
                ", identificador='" + identificador + '\'' +
                ", estado='" + estado + '\'' +
                ", saldoInicial=" + saldoInicial +
                ", saldoFinal=" + saldoFinal +
                ", fechaApertura=" + fechaApertura +
                ", fechaCierre=" + fechaCierre +
                ", cajeroId=" + cajeroId + // Mostrar el nuevo atributo
                '}';
    }
}
