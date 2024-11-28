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
        }
        while(pl.getSiguiente() != null){
            pl.getSiguiente();
        }

        pl.setSiguiente(new Playlist(playListnueva));
    }

    public Playlist obtenerPlaylist(String nombre){
        Playlist pl = null;
        if(primera != null){
            pl = primera;
        }
        while(pl !=null && pl.getNombre() != nombre){
            pl = pl.getSiguiente();
        }

        if(pl.getNombre() != nombre){
            pl = null;
        }

        return pl;
    }
}
