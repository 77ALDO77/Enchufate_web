package modelo.dto;

public class Local {
    private int CodLocal;
    private String direccion;
    private String nombre;
    private String telefono;
    private String estado;

    // Getters y Setters
    public int getCodLocal() {
        return CodLocal;
    }

    public void setCodLocal(int CodLocal) {
        this.CodLocal = CodLocal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}