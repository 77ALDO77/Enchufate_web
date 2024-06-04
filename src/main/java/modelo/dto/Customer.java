/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;

import java.sql.Date;

/**
 *
 * @author usuario
 */
public class Customer {
    private int codcliente;
    private String nombre;
    private String apepaterno;
    private String apematerno;
    private String dni;
    private String usuario;
    private String correo;
    private String fechanacimiento;
    private String contrasena;
    public Customer() {
    }

    public Customer(int codcliente, String nombre, String apepaterno, String apematerno, String dni, String usuario, String correo, String fechanacimiento, String contrasena) {
        this.codcliente = codcliente;
        this.nombre = nombre;
        this.apepaterno = apepaterno;
        this.apematerno = apematerno;
        this.dni = dni;
        this.usuario = usuario;
        this.correo = correo;
        this.fechanacimiento = fechanacimiento;
        this.contrasena = contrasena;
    }

    public int getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(int codcliente) {
        this.codcliente = codcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApepaterno() {
        return apepaterno;
    }

    public void setApepaterno(String apepaterno) {
        this.apepaterno = apepaterno;
    }

    public String getApematerno() {
        return apematerno;
    }

    public void setApematerno(String apematerno) {
        this.apematerno = apematerno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getContraseña() {
        return contrasena;
    }

    public void setContraseña(String contraseña) {
        this.contrasena = contraseña;
    }

    
    @Override
    public String toString() {
        return getNombre();
    }
}
