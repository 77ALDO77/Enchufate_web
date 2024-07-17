package modelo.dto;

public class Cubiculo {
    private int codCubiculo;
    private int CodLocal;
    private int codNivel;
    private String estadoCubiculo;
    private String estado;

    // Getters and Setters
    public int getcodCubiculo() {
        return codCubiculo;
    }

    public void setcodCubiculo(int codCubiculo) {
        this.codCubiculo = codCubiculo;
    }

    public int getCodLocal() {
        return CodLocal;
    }

    public void setCodLocal(int CodLocal) {
        this.CodLocal = CodLocal;
    }

    public int getcodNivel() {
        return codNivel;
    }

    public void setcodNivel(int codNivel) {
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
