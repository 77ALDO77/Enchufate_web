package modelo.dto;

import java.sql.Time;

public class Cubiculo {
    private int codCubiculo;
    private int codLocal;
    private String nivel;
    private String estadoCubiculo;
    private String estado;
    private Time horaInicio;
    private Time horaFin;
    private int tiempoRestante;

    // Getters y Setters

    public int getCodCubiculo() {
        return codCubiculo;
    }

    public void setCodCubiculo(int codCubiculo) {
        this.codCubiculo = codCubiculo;
    }

    public int getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(int codLocal) {
        this.codLocal = codLocal;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getEstadoCubiculo() {
        return estadoCubiculo;
    }

    public void setEstadoCubiculo(String estadoCubiculo) {
        this.estadoCubiculo = estadoCubiculo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }
}
