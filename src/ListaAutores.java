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

    public boolean verificarAutor(String autor) {
        Autor aux = null;
        if (primero != null) {
            aux = primero;

            while (aux != null && aux.getNombre() != autor) {
                aux = aux.getSiguiente();
            }
        }
        else{
            return false;
        }

        return aux.getNombre() != autor;
    }

    public Autor obtenerAutor(String nombre){
        Autor a = null;
        if(primero != null){
            a = primero;
        }
        while(a !=null && a.getNombre() != nombre){
            a = a.getSiguiente();
        }

        if(a.getNombre() == nombre){
            return a;
        }
        else {
            a = null;
        }
        return a;
    }

}
