import java.io.Serializable;

public class NodoUsuarioSerializable implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String contrasena;

    public NodoUsuarioSerializable(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }
}