package modelo.dto;

public class Proveedor {
    private int codProveedor;
    private String nombre;
    private String contacto;
    private String pais;
    
    public Proveedor(){}
    
    public Proveedor(int codProveedor, String nombre){
        this.codProveedor = codProveedor;
        this.nombre = nombre;
    }
    
    public Proveedor(int codProveedor, String nombre, String contacto, String pais){
        this.codProveedor = codProveedor;
        this.nombre = nombre;
        this.contacto = contacto;
        this.pais = pais;
    }

    public int getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(int codProveedor) {
        this.codProveedor = codProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    @Override
    public String toString() {
        return getNombre();
    }
}
