public class NodoCancion {

    private String nombre;
    private String Autor;
    private NodoCancion izquierda;
    private NodoCancion derecha;
    private NodoCancion siguienteAutor;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public NodoCancion getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoCancion izquierda) {
        this.izquierda = izquierda;
    }

    public NodoCancion getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoCancion derecha) {
        this.derecha = derecha;
    }

    public NodoCancion getSiguienteAutor() {
        return siguienteAutor;
    }

    public void setSiguienteAutor(NodoCancion siguienteAutor) {
        this.siguienteAutor = siguienteAutor;
    }
}
