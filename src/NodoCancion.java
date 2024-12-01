import java.io.Serializable;

public class NodoCancion implements Serializable {

    private String nombre;
    private NodoCancion izquierda;
    private NodoCancion derecha;
    private NodoCancion siguientexAutor;

    public NodoCancion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public NodoCancion getSiguientexAutor() {
        return siguientexAutor;
    }

    public void setSiguientexAutor(NodoCancion siguienteAutor) {
        this.siguientexAutor = siguienteAutor;
    }

    @Override
    public String toString() {return this.nombre;}
}