public class ArbolCanciones {


    //TODO: testear arbol canciones
        private NodoCancion raiz;
        private int cantNodos;

        public ArbolCanciones() {
            this.raiz = null;
            this.cantNodos = 0;
        }

        public NodoCancion getRaiz() {
            return raiz;
        }

        public void setRaiz(NodoCancion raiz) {
            this.raiz = raiz;
        }

        public void insertarCancion(NodoCancion nodoCancion) {
            this.agregarRecursivo(this.raiz, nodoCancion);
        }

        private void agregarRecursivo(NodoCancion n, NodoCancion insertar) {
            if(this.raiz == null) {
                this.raiz = insertar;
                cantNodos++;
            }else {
                //si es menor al nodo que recibe
                if(n.getNombre().compareTo(insertar.getNombre()) > 0) {
                    if(n.getIzquierda() != null) {
                        //y tiene menores, les paso el problema a los menores
                        agregarRecursivo(n.getIzquierda(), insertar);
                    }
                    else{
                        // si es hoja, lo creo y lo inserto
                        n.setIzquierda(insertar);
                        this.cantNodos++;
                    }
                }

                else if(n.getNombre().compareTo(insertar.getNombre()) < 0) {
                    //si es mayor, uso la misma logica
                    if(n.getDerecha() != null) {
                        agregarRecursivo(n.getDerecha(), insertar);
                    }
                    else{
                        n.setDerecha(insertar);
                        this.cantNodos++;
                    }
                }

            }

            this.balancear();
        }

        private void balancear(){
            NodoCancion[] uOrdenados = new NodoCancion[cantNodos];
            uOrdenados = llenararreglo(uOrdenados, this.raiz, new int[1]);
            this.raiz = construirArbolBalanceado(uOrdenados, 0, cantNodos - 1);
        }

        private NodoCancion[] llenararreglo(NodoCancion[] arr, NodoCancion n, int[] index){
            if(n != null) {
                llenararreglo(arr, n.getIzquierda(), index);
                arr[index[0]++] = n;
                llenararreglo(arr, n.getDerecha(), index);
            }

            return arr;
        }

        private NodoCancion construirArbolBalanceado(NodoCancion[] arr, int inicio, int fin) {
            if (inicio > fin) {
                return null;
            }

            int medio = (inicio + fin) / 2;
            NodoCancion nodo = arr[medio];

            nodo.setIzquierda(construirArbolBalanceado(arr, inicio, medio - 1));
            nodo.setDerecha(construirArbolBalanceado(arr, medio + 1, fin));

            return nodo;
        }


        public NodoCancion buscarCancion(String nCancion) {
            return this.buscarRec(this.raiz, nCancion);
        }

        private NodoCancion buscarRec(NodoCancion nodo, String nombre){
            NodoCancion aux = null;
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

        public void imprimirCanciones() {
            this.imprimirRecursivo(this.raiz);
        }

        private void imprimirRecursivo(NodoCancion n) {
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

        private void imprimirOrdRec(NodoCancion n) {
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

}
