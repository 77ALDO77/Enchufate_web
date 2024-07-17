package modelo.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class Reserva {
    private int codReserva;
    private int codCliente;
    private int codEmpleado;
    private int codCubiculo;
    private Date fecha;
    private Timestamp horaInicio;
    private Timestamp horaFin;
    private String tiempo;
    private int total;

    // Getters and Setters
    public int getcodReserva() {
        return codReserva;
    }

    public void setcodReserva(int codReserva) {
        this.codReserva = codReserva;
    }

    public int getcodCliente() {
        return codCliente;
    }

    public void setcodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getcodEmpleado() {
        return codEmpleado;
    }

    public void setcodEmpleado(int codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public int getcodCubiculo() {
        return codCubiculo;
    }

    public void setcodCubiculo(int codCubiculo) {
        this.codCubiculo = codCubiculo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Timestamp gethoraInicio() {
        return horaInicio;
    }

    public void sethoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Timestamp gethoraFin() {
        return horaFin;
    }

    public void sethoraFin(Timestamp horaFin) {
        this.horaFin = horaFin;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int gettotal() {
        return total;
    }

    public void settotal(int total) {
        this.total = total;
    }
}
