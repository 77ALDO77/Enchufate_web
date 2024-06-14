/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;

import java.io.Serializable;

public class Producto implements Serializable {

    private int codproducto;
    private int codcategoria;
    private int codproveedor;
    private double precio;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String fechavencimiento;
    private String nombreCategoria;
    private int cantidad;
    public Producto() {
    }

    public Producto(int codproducto, int codcategoria, int codproveedor, double precio, String nombre, String descripcion, String imagen, String fechavencimiento, String nombreCategoria,int cantidad) {
        this.codproducto = codproducto;
        this.codcategoria = codcategoria;
        this.codproveedor = codproveedor;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechavencimiento = fechavencimiento;
        this.nombreCategoria = nombreCategoria;
        this.cantidad=cantidad;
    }

    public int getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(int codproducto) {
        this.codproducto = codproducto;
    }

    public int getCodcategoria() {
        return codcategoria;
    }

    public void setCodcategoria(int codcategoria) {
        this.codcategoria = codcategoria;
    }

    public int getCodproveedor() {
        return codproveedor;
    }

    public void setCodproveedor(int codproveedor) {
        this.codproveedor = codproveedor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(String fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override
    public String toString() {
        return getNombre();
    }
}
