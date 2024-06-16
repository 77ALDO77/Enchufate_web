package modelo.dto;

public class Categoria {
    private int codCategoria;
    private String nombre;
    
    public Categoria(){}
    
    public Categoria(int codCategoria, String nombre){
        this.codCategoria = codCategoria;
        this.nombre = nombre;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return getNombre();
    }
}
