public class ListaSeguidos {

    private NodoSeguido primero;



    public ListaSeguidos(){
        this.primero = null;
    }

    public void agregarSeguidas(NodoUsuario nuevo, Playlist playlist) {
        NodoSeguido insertar = new NodoSeguido(nuevo, playlist);
        if(primero == null) {
            primero = insertar;
        }
        else {
            NodoSeguido aux = primero;
            while(aux != null && aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(insertar);
        }
    }
    
    public NodoSeguido getPrimero() {
        return primero;
    }

    public void imprimirTodas() {
        NodoSeguido pl = null;
        if(primero != null){
            pl = primero;
            while(pl.getSiguiente() != null){
                System.out.println(pl);
                pl = pl.getSiguiente();
            }
            System.out.println(pl);
        }
    }
}