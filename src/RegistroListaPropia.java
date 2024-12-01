import java.io.Serializable;

public class RegistroListaPropia implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombreUsuario;
    private String nombrePlaylist;
    private String tituloCancion;

    public RegistroListaPropia(String nombreUsuario, String nombrePlaylist, String tituloCancion) {
        this.nombreUsuario = nombreUsuario;
        this.nombrePlaylist = nombrePlaylist;
        this.tituloCancion = tituloCancion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombrePlaylist() {
        return nombrePlaylist;
    }

    public String getTituloCancion() {
        return tituloCancion;
    }
}