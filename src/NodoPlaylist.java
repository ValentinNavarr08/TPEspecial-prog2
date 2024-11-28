public class NodoPlaylist {

    private NodoPlaylist siguiente;
    private NodoCancion cancion;

    public NodoPlaylist(NodoCancion cancion) {
        this.cancion = cancion;
    }

    public NodoPlaylist getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPlaylist siguiente) {
        this.siguiente = siguiente;
    }

    public NodoCancion getCancion() {
        return cancion;
    }

    public void setCancion(NodoCancion cancion) {
        this.cancion = cancion;
    }
}
