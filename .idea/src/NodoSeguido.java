public class NodoSeguido {

    private NodoUsuario usuario;
    private Playlist plSeguida;
    private NodoSeguido siguiente;

    public NodoSeguido(NodoUsuario usuario, Playlist plSeguida) {
        this.usuario = usuario;
        this.plSeguida = plSeguida;
        this.siguiente = null;
    }

    public NodoUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(NodoUsuario usuario) {
        this.usuario = usuario;
    }

    public NodoSeguido getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSeguido siguiente) {
        this.siguiente = siguiente;
    }

    public Playlist getPlSeguida() {
        return plSeguida;
    }

    public void setPlSeguida(Playlist plSeguida) {
        this.plSeguida = plSeguida;
    }
}