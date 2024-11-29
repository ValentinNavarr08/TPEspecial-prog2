import java.io.Serializable;

public class NodoUsuario  implements Serializable {

    private String nombre;
    private String contrasena;
    private transient NodoUsuario izquierda;
    private transient NodoUsuario derecha;
    private transient ListaSeguidos seguidas;
    private transient ListaPropia propias;

    public NodoUsuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.izquierda = null;
        this.derecha = null;
        this.seguidas = new ListaSeguidos();
        this.propias = new ListaPropia();
    }

    public NodoUsuario getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoUsuario izquierda) {
        this.izquierda = izquierda;
    }

    public NodoUsuario getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoUsuario derecha) {
        this.derecha = derecha;
    }

    public ListaSeguidos getSeguidas() {
        return this.seguidas;
    }

    public ListaPropia getPropias() {
        return propias;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {return this.nombre+" " +this.contrasena;}

    public boolean tienePLPropia(String playlist) {
        boolean resultado;
        if(this.propias.obtenerPlaylist(playlist) != null){
            resultado = true;
        }
        else resultado = false;

        return resultado;
    }

    public void agegarPLPropia(String playlist) {
        this.propias.insertar(playlist);
    }

    public Playlist obtenerPropia(String playlist) {
        return this.propias.obtenerPlaylist(playlist);
    }

    public void imprimirPLPropias() {
        propias.imprimirTodas();
    }

    public void eliminarPropia(Playlist eliminar) {
        this.propias.eliminar(eliminar);
    }

    public void agregarSeguida(NodoUsuario aseguir, Playlist playlist) {
        this.seguidas.agregarSeguidas(aseguir, playlist);
    }
}
