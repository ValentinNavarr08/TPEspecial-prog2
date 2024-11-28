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
        this.izquierda = null;
        this.derecha = null;
        this.seguidos = new ListaSeguidos();
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

    public ListaSeguidos getUsuariosSeguidos() {
        return this.seguidos;
    }

    public void agregarUsuarioSeguido(NodoUsuario usuario) {
        this.seguidos.agregarSeguidos(usuario);
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

    //TODO: crear nueva lista reproducciones,
    // agregar cancion por titulo a una lista de repro, agregar una cancion por autor (preguntar),
    // eliminar una lista de reprod propia,
    // incluir la lista de otro usuario (seguirlo)
}
