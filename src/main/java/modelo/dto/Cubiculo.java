package modelo.dto;

public class Cubiculo {

    private int codCubiculo;
    private int nivel;
    private String estado;

    public int getCodCubiculo() {
        return codCubiculo;
    }

    public void setCodCubiculo(int codCubiculo) {
        this.codCubiculo = codCubiculo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
