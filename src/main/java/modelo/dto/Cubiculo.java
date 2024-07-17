package modelo.dto;

public class Cubiculo {
    private int codCubiculo;
    private int codLocal;
    private int codNivel;
    private String estadoCubiculo;
    private String estado;

    // Getters and Setters
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

    public int getCodNivel() {
        return codNivel;
    }

    public void setCodNivel(int codNivel) {
        this.codNivel = codNivel;
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
}
