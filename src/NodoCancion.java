public class NodoCancion {

    private String nombre;
    private String Autor;
    private NodoCancion izquierda;
    private NodoCancion derecha;
    private NodoCancion siguientexAutor;

    public NodoCancion(String nombre, String autor) {
        this.nombre = nombre;
        Autor = autor;
    }

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

    public NodoCancion getSiguientexAutor() {
        return siguientexAutor;
    }

    public void setSiguientexAutor(NodoCancion siguienteAutor) {
        this.siguientexAutor = siguienteAutor;
    }

    @Override
    public String toString() {return this.nombre+" por: "+this.getAutor().toString();}
}
