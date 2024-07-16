package modelo.dto;

import java.sql.Date;

public class Cliente {
    private int codCliente;
    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private String DNI;
    private Date fechaNacimiento;
    private String usuario;
    private String correo;
    private String contraseña;

    public Cliente(int codCliente, String nombres, String apePaterno, String apeMaterno, String DNI, Date fechaNacimiento, String usuario, String correo, String contraseña) {
        this.codCliente = codCliente;
        this.nombres = nombres;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.DNI = DNI;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public Cliente() {}
    
    
    
    
    
    

    // Getters y Setters
    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
