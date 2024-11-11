public class ArbolUsuarios {

    private NodoUsuario raiz;

    public ArbolUsuarios() {
        this.raiz = null;
    }

    public NodoUsuario getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoUsuario raiz) {
        this.raiz = raiz;
    }

    //TODO: BUSCAR USUARIO

    public void insertarUsuario(String nUsuario) {
        //le paso el nodo ya creado o creo uno en el metodo?

        this.agregarRecursivo(this.raiz, nUsuario);
    }

    private void agregarRecursivo(NodoUsuario n, String nUsuarioNuevo) {
        //si es menor al nodo que recibe
        if(n.getNombre().compareTo(nUsuarioNuevo) < 0) {
            if(n.getIzquierda() != null) {
                //y tiene menores, les paso el problema a los menores
                agregarRecursivo(n.getIzquierda(), nUsuarioNuevo);
            }
            else{
                // si es hoja, lo creo y lo inserto
                NodoUsuario aux = new NodoUsuario(nUsuarioNuevo);
                n.setIzquierda(aux);
            }
        }
        else if(n.getNombre().compareTo(nUsuarioNuevo) > 0) {
            //si es mayor, uso la misma logica
            if(n.getDerecha() != null) {
                agregarRecursivo(n.getDerecha(), nUsuarioNuevo);
            }
            else{
                NodoUsuario aux = new NodoUsuario(nUsuarioNuevo);
                n.setDerecha(aux);
            }
        }
    }


    public void imprimirUsuarios() {
        this.imprimirRecursivo(this.raiz);
    }

    private void imprimirRecursivo(NodoUsuario n) {
        if(n != null) {
            if(n.getIzquierda() != null) {
                //imprimo menores primero
                imprimirRecursivo(n.getIzquierda());
            }
            //nodo actual
            System.out.println(n.toString());
            if(n.getDerecha() != null) {
                //imprimo mayores
                imprimirRecursivo(n.getDerecha());
            }
        }
    }

    public boolean buscarUsuario

}
