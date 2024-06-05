package modelo.dto;

/**
 *
 * @author aldom
 */
public class Reclamos {

    private int id_reclamos;
    private String nombre_cliente;
    private String correo_reclamo;
    private String dni_reclamo;
    private String asunto_reclamo;
    private String fecha_reclamo;
    private String contenido_reclamo;
    public Reclamos(){
    }   

    public Reclamos(int id_reclamos, String nombre_cliente, String correo_reclamo, String dni_reclamo, String asunto_reclamo, String fecha_reclamo, String contenido_reclamo) {
        this.id_reclamos = id_reclamos;
        this.nombre_cliente = nombre_cliente;
        this.correo_reclamo = correo_reclamo;
        this.dni_reclamo = dni_reclamo;
        this.asunto_reclamo = asunto_reclamo;
        this.fecha_reclamo = fecha_reclamo;
        this.contenido_reclamo = contenido_reclamo;
    }

    public int getId_reclamos() {
        return id_reclamos;
    }

    public void setId_reclamos(int id_reclamos) {
        this.id_reclamos = id_reclamos;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getCorreo_reclamo() {
        return correo_reclamo;
    }

    public void setCorreo_reclamo(String correo_reclamo) {
        this.correo_reclamo = correo_reclamo;
    }

    public String getDni_reclamo() {
        return dni_reclamo;
    }

    public void setDni_reclamo(String dni_reclamo) {
        this.dni_reclamo = dni_reclamo;
    }

    public String getAsunto_reclamo() {
        return asunto_reclamo;
    }

    public void setAsunto_reclamo(String asunto_reclamo) {
        this.asunto_reclamo = asunto_reclamo;
    }

    public String getFecha_reclamo() {
        return fecha_reclamo;
    }

    public void setFecha_reclamo(String fecha_reclamo) {
        this.fecha_reclamo = fecha_reclamo;
    }

    public String getContenido_reclamo() {
        return contenido_reclamo;
    }

    public void setContenido_reclamo(String contenido_reclamo) {
        this.contenido_reclamo = contenido_reclamo;
    }

}
