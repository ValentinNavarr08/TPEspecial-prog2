import java.io.Serializable;

public class RegistroListaSeguidas implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombreUsuario;
    private String nombreUsuarioSeguido;
    private String nombreLista;

    public RegistroListaSeguidas(String nombreUsuario, String nombreUsuarioSeguido, String nombreLista) {
        this.nombreUsuario = nombreUsuario;
        this.nombreUsuarioSeguido = nombreUsuarioSeguido;
        this.nombreLista = nombreLista;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombreUsuarioSeguido() {
        return nombreUsuarioSeguido;
    }

    public String getNombreLista() {
        return nombreLista;
    }
}