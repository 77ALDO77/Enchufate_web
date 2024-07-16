package modelo.dto;

public class Empleado {
    private int codEmpleado;
    private int codLocal;
    private int codArea;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private int dni;
    private String sexo;
    private int celular;
    private double salario;
    private String correo;
    private String clave;
    
    private Locales clase_local;
    private Area clase_area;

    public Empleado()
    {
        clase_local = new Locales();
        clase_area = new Area();
    }

    public Empleado(Locales clase_local, Area clase_area){this();this.clase_local = clase_local;this.clase_area = clase_area;}

    public Empleado(Locales clase_local) {this();this.clase_local = clase_local;}

    public Empleado(Area clase_area) {this();this.clase_area = clase_area;}

    public Empleado(int codEmpleado, int codLocal, int codArea, String nombre, String apellidos, String fechaNacimiento, int dni, String sexo, int celular, double salario, String correo, String clave) {
        this.codEmpleado = codEmpleado;
        this.codLocal = codLocal;
        this.codArea = codArea;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.sexo = sexo;
        this.celular = celular;
        this.salario = salario;
        this.correo = correo;
        this.clave = clave;
    }


    
    
    
    
    
    
    
    
    // Getters de las clases
    public Locales getClase_local() {        return clase_local;    }
    public Area getClase_area() {        return clase_area;    }

    // Getters y Setters
    public int getCodEmpleado() {return codEmpleado;}
    public void setCodEmpleado(int codEmpleado) {this.codEmpleado = codEmpleado;}

    public int getCodLocal() {return codLocal;}
    public void setCodLocal(int codLocal) {this.codLocal = codLocal;}

    public int getCodArea() {return codArea;}
    public void setCodArea(int codArea) {this.codArea = codArea;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellidos() {return apellidos;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;    }

    public String getFechaNacimiento() {return fechaNacimiento;}
    public void setFechaNacimiento(String fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public int getDni() {return dni;}
    public void setDni(int dni) {this.dni = dni;}

    public String getSexo() {return sexo;}
    public void setSexo(String sexo) {this.sexo = sexo;}

    public int getCelular() {return celular;}
    public void setCelular(int celular) {this.celular = celular;}

    public double getSalario() {return salario;}
    public void setSalario(double salario) {this.salario = salario;}

    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}

    public String getClave() {return clave;}
    public void setClave(String clave) {this.clave = clave;}
    
}
