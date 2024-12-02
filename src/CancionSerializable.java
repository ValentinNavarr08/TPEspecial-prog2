import java.io.Serializable;

public class CancionSerializable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String autor;

    public CancionSerializable(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}