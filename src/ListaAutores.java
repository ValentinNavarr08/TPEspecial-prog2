public class ListaAutores {

    private Autor primero;

    public void insertar(String nautor) {
        Autor a = null;
        if(primero != null){
            a = primero;
            while(a.getSiguiente() != null){
                a.getSiguiente();
            }
            
            a.setSiguiente(new Autor(nautor));
        }
        else{
            primero = new Autor(nautor);
        }
    }
    
    public Autor obtenerAutorPorCancion(NodoCancion cancion) {
        Autor aux = primero;
        while (aux != null) {
            NodoCancion nodoCancion = aux.getPrimera();
            while (nodoCancion != null) {
                if (nodoCancion.equals(cancion)) {
                    return aux;
                }
                nodoCancion = nodoCancion.getSiguientexAutor();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }


    public boolean verificarAutor(String autor) {
        Autor aux = null;
        if (primero != null) {
            aux = primero;
            while (aux.getSiguiente() != null && !aux.getNombre().equalsIgnoreCase(autor)) {
                aux = aux.getSiguiente();
            }
        }
        else{
            return false;
        }
        return aux.getNombre().equalsIgnoreCase(autor);
    }

    public Autor obtenerAutor(String nombre){
        Autor a = null;
        if(primero != null){
            a = primero;
            while(a != null && !a.getNombre().equalsIgnoreCase(nombre)){
                a = a.getSiguiente();
            }
            if(a.getNombre().equalsIgnoreCase(nombre)){
                return a;
            }
        }
        return a;
    }

}