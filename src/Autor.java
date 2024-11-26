public class Autor {

    private String nombre;
    private Autor siguienteAutor;
    private NodoCancion primera;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Autor getSiguienteAutor() {
        return siguienteAutor;
    }

    public void setSiguienteAutor(Autor siguienteAutor) {
        this.siguienteAutor = siguienteAutor;
    }

    public NodoCancion getPrimera() {
        return primera;
    }

    public void agregarCancion(NodoCancion nueva) {
        NodoCancion aux =  this.primera;
        while(aux != null && aux.getSiguienteAutor() != null) {
            aux = aux.getSiguienteAutor();
        }
        aux.setSiguienteAutor(nueva);
    }
}
