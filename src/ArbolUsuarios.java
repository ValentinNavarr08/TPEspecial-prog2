public class ArbolUsuarios {

    private NodoUsuario raiz;
    private int cantNodos;

    public ArbolUsuarios() {
        this.raiz = null;
        this.cantNodos = 0;
    }

    public NodoUsuario getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoUsuario raiz) {
        this.raiz = raiz;
    }

    public void insertarUsuario(String nUsuario, String contra) {
        this.agregarRecursivo(this.raiz, nUsuario, contra);
    }

    private void agregarRecursivo(NodoUsuario n, String nUsuarioNuevo, String contra) {
        if(this.raiz == null) {
            this.raiz = new NodoUsuario(nUsuarioNuevo, contra);
            cantNodos++;
        }else {
            //si es menor al nodo que recibe
            if(n.getNombre().compareTo(nUsuarioNuevo) > 0) {
                if(n.getIzquierda() != null) {
                    //y tiene menores, les paso el problema a los menores
                    agregarRecursivo(n.getIzquierda(), nUsuarioNuevo, contra);
                }
                else{
                    // si es hoja, lo creo y lo inserto
                    NodoUsuario aux = new NodoUsuario(nUsuarioNuevo, contra);
                    n.setIzquierda(aux);
                    this.cantNodos++;
                }
            }

            else if(n.getNombre().compareTo(nUsuarioNuevo) < 0) {
                //si es mayor, uso la misma logica
                if(n.getDerecha() != null) {
                    agregarRecursivo(n.getDerecha(), nUsuarioNuevo, contra);
                }
                else{
                    NodoUsuario aux = new NodoUsuario(nUsuarioNuevo, contra);
                    n.setDerecha(aux);
                    this.cantNodos++;
                }
            }

        }

        this.balancear();
        //TODO: Balancear el arbol
    }

    private void balancear(){
        NodoUsuario[] uOrdenados = new NodoUsuario[cantNodos];
        uOrdenados = llenararreglo(uOrdenados, this.raiz, new int[1]);
        this.raiz = construirArbolBalanceado(uOrdenados, 0, cantNodos - 1);
    }

    private NodoUsuario[] llenararreglo(NodoUsuario[] arr, NodoUsuario n, int[] index){
        if(n != null) {
                llenararreglo(arr, n.getIzquierda(), index);
                arr[index[0]++] = n;
                llenararreglo(arr, n.getDerecha(), index);
        }

        return arr;
    }

    private NodoUsuario construirArbolBalanceado(NodoUsuario[] arr, int inicio, int fin) {
        if (inicio > fin) {
            return null;
        }

        int medio = (inicio + fin) / 2;
        NodoUsuario nodo = arr[medio];

        nodo.setIzquierda(construirArbolBalanceado(arr, inicio, medio - 1));
        nodo.setDerecha(construirArbolBalanceado(arr, medio + 1, fin));

        return nodo;
    }


    public NodoUsuario buscarUsuario(String nUsuario) {
        return this.buscarRec(this.raiz, nUsuario);
    }

    private NodoUsuario buscarRec(NodoUsuario nodo, String nombre){
        NodoUsuario aux = null;
        //si entra a un nulo, o a lo que está buscando retorna
        if(nodo != null) {
            //si el nombre es menor
            if(nodo.getNombre().compareTo(nombre) > 0) {
                aux = buscarRec(nodo.getIzquierda(), nombre);
            }
            //si el nombre es mayor
            else if(nodo.getNombre().compareTo(nombre) < 0) {
                aux = buscarRec(nodo.getDerecha(), nombre);
            }
            //es igual
            else aux = nodo;
        }
        else aux = nodo;
        return aux;
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

    public void imprimirOrdenado(){
         this.imprimirOrdRec(this.raiz);
    }

    private void imprimirOrdRec(NodoUsuario n) {
        if(n != null) {
            if(n.getIzquierda() != null) {
                imprimirOrdRec(n.getIzquierda());
            }
            System.out.println(n);
            if(n.getDerecha() != null) {
                imprimirOrdRec(n.getDerecha());
            }
        }

    }

    public void serializarArbol(Archivos persistencia) {
        this.serializarRec(this.raiz, persistencia);
    }

    private void serializarRec(NodoUsuario n, Archivos persistencia) {
        if(n != null) {
            if(n.getIzquierda() != null) {
                this.serializarRec(n.getIzquierda(), persistencia);
            }
            persistencia.guardarUsuario(n);
            if(n.getDerecha() != null) {
                this.serializarRec(n.getDerecha(), persistencia);
            }
        }

    }

}

