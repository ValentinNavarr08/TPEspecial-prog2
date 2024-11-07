public class NodoUsuario {

    private String nombre;
    private String contrasena;
    private NodoUsuario izquierda;
    private NodoUsuario derecha;
    private ListaSeguidos seguidos;
    private ListaPropia propias;

    public NodoUsuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
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

    public ListaSeguidos getUsuariosSeguidos() {
        return usuariosSeguidos;
    }

    public void setUsuariosSeguidos(ListaSeguidos usuariosSeguidos) {
        this.usuariosSeguidos = usuariosSeguidos;
    }

    public ListaPropia getPropias() {
        return propias;
    }

    public void setPropias(ListaPropia propias) {
        this.propias = propias;
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

    //TODO: crear nueva lista repro,
    // agregar cancion por titulo a una lista de repro, agregar una cancion por autor (preguntar),
    // eliminar una lista de reprod propia,
    // incluir la lista de otro usuario (seguirlo)
}
