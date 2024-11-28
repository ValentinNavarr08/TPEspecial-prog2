public class Autor {

    private String nombre;
    private Autor siguienteAutor;
    private NodoCancion primera;

    public Autor(String nautor) {
        this.nombre = nautor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Autor getSiguiente() {
        return siguienteAutor;
    }

    public void setSiguiente(Autor siguienteAutor) {
        this.siguienteAutor = siguienteAutor;
    }

    public NodoCancion getPrimera() {
        return primera;
    }

    public void agregarCancion(NodoCancion nueva) {
        if(primera != null){
            NodoCancion aux =  this.primera;
            while(aux != null && aux.getSiguientexAutor() != null) {
                aux = aux.getSiguientexAutor();
            }
            aux.setSiguientexAutor(nueva);
        }
        else{
            primera = nueva;
        }


    }

    public void imprimirCanciones() {
        if(primera != null){
            NodoCancion aux =  this.primera;
            while(aux != null) {
                System.out.println(aux);
                aux = aux.getSiguientexAutor();
            }
        }
    }

    @Override
    public String toString() {return this.nombre;}
}
