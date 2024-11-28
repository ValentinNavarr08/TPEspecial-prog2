public class Playlist {

    private String nombre;
    private int cantCanciones;
    private NodoPlaylist primero;
    private Playlist siguiente;

    public Playlist(String nombre) {
        this.nombre = nombre;
        this.cantCanciones = 0;
        this.primero = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NodoPlaylist getPrimero() {
        return primero;
    }

    public void setPrimero(NodoPlaylist primero) {
        this.primero = primero;
    }

    public Playlist getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Playlist siguiente) {
        this.siguiente = siguiente;
    }

    public boolean tieneCancion(NodoCancion cancion) {
        boolean contiene = false;
        NodoPlaylist aux = primero;
        for (int i=0; i<cantCanciones; i++){
            if(aux.getCancion().equals(cancion)){
                contiene = true;
            }
            aux.getSiguiente();
        }
        return contiene;
    }

    public void agregarCancion(NodoCancion cancion) {
        if(primero != null){
            NodoPlaylist aux = primero;
            for (int i=0; i<cantCanciones; i++){
                aux.getSiguiente();
            }
            NodoPlaylist nPL = new NodoPlaylist(cancion);
            nPL.setSiguiente(primero);
            aux.setSiguiente(nPL);
        }
        else primero = new NodoPlaylist(cancion);
        cantCanciones++;
    }

    public String toString() {return this.nombre;}
}
