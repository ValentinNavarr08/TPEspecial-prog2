public class ListaSeguidos {

    private NodoSeguido primero;

    public ListaSeguidos() {}

    public void agregarSeguidos(NodoUsuario nuevo) {
        NodoSeguido insertar = new NodoSeguido(nuevo);

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
}
