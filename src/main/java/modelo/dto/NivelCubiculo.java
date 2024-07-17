package modelo.dto;

public class NivelCubiculo {
    private int codNivel;
    private String nombre;
    private double precio;

    // Getters and Setters
    public int getcodNivel() {
        return codNivel;
    }

    public void setcodNivel(int codNivel) {
        this.codNivel = codNivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
