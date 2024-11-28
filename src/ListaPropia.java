public class ListaPropia {

    private Playlist primera;

    public Playlist getPrimera() {
        return primera;
    }

    public void setPrimera(Playlist primera) {
        this.primera = primera;
    }

    public void insertar(String playListnueva) {
        Playlist pl = null;
        if(primera != null){
            pl = primera;

            while(pl.getSiguiente() != null){
                pl.getSiguiente();
            }
            pl.setSiguiente(new Playlist(playListnueva));
        }
        else{
            primera = new Playlist(playListnueva);
        }
    }

    public void eliminar(Playlist borrar) {
        Playlist pl = null;
        if(primera != null){
            pl = primera;

            while(pl.getSiguiente() != null && pl.getSiguiente() != borrar){
                pl.getSiguiente();
            }
        }
        pl.setSiguiente(borrar.getSiguiente());
        borrar.setSiguiente(null);
    }

    public Playlist obtenerPlaylist(String nombre){
        Playlist pl = null;
        if(primera != null){
            pl = primera;
            while(pl.getSiguiente() != null && pl.getNombre() != nombre){
                pl = pl.getSiguiente();
            }
            if(!pl.getNombre().equalsIgnoreCase(nombre)){
                pl = null;
            }
        }

        return pl;
    }

    public void imprimirTodas() {
        Playlist pl = null;
        if(primera != null){
            pl = primera;
            while(pl.getSiguiente() != null){
                System.out.println(pl);
                pl = pl.getSiguiente();
            }
            System.out.println(pl);
        }
    }
}
