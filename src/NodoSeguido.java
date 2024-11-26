public class NodoSeguido {

    private NodoUsuario actual;
    private NodoSeguido siguiente;

    public NodoSeguido(NodoUsuario actual) {
        this.actual = actual;
    }

    public NodoSeguido getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSeguido siguiente) {
        this.siguiente = siguiente;
    }
}
