package modelo.dto;

import java.sql.Date;
import java.sql.Time;

public class Venta {

    private int codCompra;
    private int codCliente;
    private int codEmpleado;
    private Date Fecha;
    private Time Hora;
    private int total;
    
    public Venta(){}

    public int getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(int codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Time getHora() {
        return Hora;
    }

    public void setHora(Time Hora) {
        this.Hora = Hora;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    

}
