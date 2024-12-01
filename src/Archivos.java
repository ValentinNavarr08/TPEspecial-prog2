//hay contador en la serializacion de nodos usuarios para ver cuantos hay al momento de guardarlos (prueba)

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public class Archivos {

    private static final String ARCHIVO_USUARIOS = "/work/archivoUsuariosPRUEBA.txtLN";                               //"ArchUsuarios.ser";
    private static final String ARCHIVO_CANCIONES = "/work/archivoCancionesPRUEBA.txtLN";                              //ArchCanciones.ser";
    private static final String ARCHIVO_LISTAS_PROPIAS = "/work/archivoListasPropiasPRUEBA.txtLN";                         //"ArchListasPropias.ser";
    private static final String ARCHIVO_LISTAS_SEGUIDAS = "/work/archivoLsitasSeguidasPRUEBA.txtLN";                        //"ArchListasSeguidas.ser";;

    private ArbolUsuarios arbUsuarios;
    private ArbolCanciones arbCanciones;
    private ListaPropia listasPropias;
    private ListaSeguidos listasSeguidos;
    private ListaAutores listaAutores;
    
    public void guardarDatos() {
        guardarUsuarios(arbUsuarios);
        guardarCanciones(arbCanciones, listaAutores);
        guardarListasPropias(arbUsuarios);
        guardarListasSeguidas(arbUsuarios);
    }
    
    public void cargarDatos() {
        // Cargar usuarios
        System.out.println("Cargando usuarios...");
        arbUsuarios = cargarUsuarios();
        
        // Cargar canciones y lista de autores
        System.out.println("Cargando canciones...");
        ListaAutores listaAutores = new ListaAutores();
        arbCanciones = cargarCanciones(listaAutores);
        
        // Cargar listas propias
        System.out.println("Cargando listas propias...");
        cargarListasPropias(arbUsuarios, arbCanciones);
        
        // Cargar listas seguidas
        System.out.println("Cargando listas seguidas...");
        cargarListasSeguidas(arbUsuarios);
    }


    private ArbolUsuarios cargarUsuarios() {
        File archivo = new File(ARCHIVO_USUARIOS);
        ArbolUsuarios arbol = new ArbolUsuarios();
        
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("Cargando usuarios desde archivo...");
                while (true) {
                    try {
                        // Deserializar como NodoUsuarioSerializable
                        NodoUsuarioSerializable nodoSerializado = (NodoUsuarioSerializable) ois.readObject();
                        
                        // Convertir a NodoUsuario
                        NodoUsuario nodoUsuario = new NodoUsuario(nodoSerializado.getNombre(), nodoSerializado.getContrasena());
                        
                        // Insertar en el árbol
                        arbol.insertarUsuario(nodoUsuario.getNombre(), nodoUsuario.getContrasena());
                    } catch (IOException e) {
                        // Fin del archivo alcanzado
                        break;
                    }
                }
                System.out.println("Usuarios cargados exitosamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar el archivo de usuarios.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de usuarios no encontrado. Se creará un árbol vacío.");
        }
        return arbol;
    }

    private void cargarListasPropias(ArbolUsuarios arbolUsuarios, ArbolCanciones arbolCanciones) {
        File archivo = new File(ARCHIVO_LISTAS_PROPIAS);
        
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                while (true) {
                    try {
                        RegistroListaPropia registro = (RegistroListaPropia) ois.readObject();
                        reconstruirListaPropia(registro, arbolUsuarios, arbolCanciones);
                    } catch (IOException e) {
                        break; // Fin del archivo alcanzado
                    }
                }
                System.out.println("Listas propias cargadas exitosamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar las listas propias.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de listas propias no encontrado. Se creará vacío.");
        }
    }

    private void reconstruirListaPropia(RegistroListaPropia registro, ArbolUsuarios arbolUsuarios, ArbolCanciones arbolCanciones) {
        NodoUsuario usuario = arbolUsuarios.buscarUsuario(registro.getNombreUsuario());
        if (usuario == null) {
            System.err.println("Usuario no encontrado: " + registro.getNombreUsuario());
            return;
        }
        
        ListaPropia listaPropia = usuario.getPropias();
        if (listaPropia == null) {
            listaPropia = new ListaPropia();
            usuario.setPropias(listaPropia);
        }
        
        Playlist playlist = listaPropia.obtenerPlaylist(registro.getNombrePlaylist());
        if (playlist == null) {
            playlist = new Playlist(registro.getNombrePlaylist());
            listaPropia.insertar(registro.getNombrePlaylist());
        }
        
        NodoCancion cancion = arbolCanciones.buscarCancion(registro.getTituloCancion());
        if (cancion != null) {
            playlist.agregarCancion(cancion);
        } else {
            System.err.println("Canción no encontrada: " + registro.getTituloCancion());
        }
    }

    private void cargarListasSeguidas(ArbolUsuarios arbolUsuarios) {
        File archivo = new File(ARCHIVO_LISTAS_SEGUIDAS);
        
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("Cargando listas seguidas desde archivo...");
                
                while (true) {
                    try {
                        RegistroListaSeguidas registro = (RegistroListaSeguidas) ois.readObject();
                        reconstruirListaSeguidas(arbolUsuarios, registro);
                    } catch (IOException e) {
                        break; // Fin del archivo
                    }
                }
                
                System.out.println("Listas seguidas cargadas exitosamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar las listas seguidas.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de listas seguidas no encontrado. Se creará un archivo vacío.");
        }
    }

    private void reconstruirListaSeguidas(ArbolUsuarios arbolUsuarios, RegistroListaSeguidas registro) {
        NodoUsuario usuario = arbolUsuarios.buscarUsuario(registro.getNombreUsuario());
        NodoUsuario usuarioSeguido = arbolUsuarios.buscarUsuario(registro.getNombreUsuarioSeguido());
        
        if (usuario != null && usuarioSeguido != null) {
            Playlist playlist = usuarioSeguido.getPropias().obtenerPlaylist(registro.getNombreLista());
            if (playlist != null) {
                usuario.agregarSeguida(usuarioSeguido, playlist);
            } else {
                System.err.println("Error: Lista no encontrada: " + registro.getNombreLista());
            }
        } else {
            System.err.println("Error: Usuario o usuario seguido no encontrado: " + registro.getNombreUsuarioSeguido());
        }
    }
    
    private ArbolCanciones cargarCanciones(ListaAutores listaAutores) {
        File archivo = new File(ARCHIVO_CANCIONES);
        ArbolCanciones arbolCanciones = new ArbolCanciones();
        
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                while (true) {
                    try {
                        // Leer un objeto serializable
                        CancionSerializable cancionSerializable = (CancionSerializable) ois.readObject();
                        // Crear un nodo de canción
                        NodoCancion nodoCancion = new NodoCancion(cancionSerializable.getTitulo());
                        arbolCanciones.insertarCancion(nodoCancion);
                        // Asociar la canción con su autor
                        asociarCancionAutor(nodoCancion, cancionSerializable.getAutor(), listaAutores);
                    } catch (IOException e) {
                        break; // Fin del archivo alcanzado
                    }
                }
                System.out.println("Canciones cargadas exitosamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar las canciones.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de canciones no encontrado. Se creará un árbol vacío.");
        }
        return arbolCanciones;
    }

    private void asociarCancionAutor(NodoCancion cancion, String nombreAutor, ListaAutores listaAutores) {
        if (!listaAutores.verificarAutor(nombreAutor)) {
            listaAutores.insertar(nombreAutor);
        }
        Autor autor = listaAutores.obtenerAutor(nombreAutor);
        autor.agregarCancion(cancion);
    }

    private void guardarUsuarios(ArbolUsuarios arbUsuarios) {
        File archivo = new File(ARCHIVO_USUARIOS);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            // Recorrer el árbol y guardar los nodos serializables
            System.out.println("Guardando usuarios...");
            System.out.println("Cantidad de usuarios a guardar: " + contarNodos(arbUsuarios.getRaiz()));
            generarSerializables(arbUsuarios.getRaiz(), oos);
            System.out.println("Usuarios guardados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo de usuarios.");
            e.printStackTrace();
        }
    }


    private void generarSerializables(NodoUsuario nodo, ObjectOutputStream oos) throws IOException {
        if (nodo != null) {
            // Recorrer subárbol izquierdo
            generarSerializables(nodo.getIzquierda(), oos);
            
            // Crear NodoUsuarioSerializable a partir de NodoUsuario
            NodoUsuarioSerializable nodoSerializado = new NodoUsuarioSerializable(nodo.getNombre(), nodo.getContrasena());
            
            // Escribir el nodo serializable en el archivo
            System.out.println("Guardando usuarios...");
            System.out.println("Cantidad de usuarios a guardar: " + contarNodos(arbUsuarios.getRaiz()));
            oos.writeObject(nodoSerializado);
            
            // Recorrer subárbol derecho
            generarSerializables(nodo.getDerecha(), oos);
        }
    }
    //LO ESTABA USANDO PARA VER SI LA SERIALIZACION FUNCIONABA CORRECTAMENTE
    public int contarNodos(NodoUsuario nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.getIzquierda()) + contarNodos(nodo.getDerecha());
    }


    private void guardarCanciones(ArbolCanciones arbCanciones, ListaAutores listaAutores) {
        File archivo = new File(ARCHIVO_CANCIONES);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            guardarCancionesRecursivo(arbCanciones.getRaiz(), oos, listaAutores);
            System.out.println("Canciones guardadas exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar las canciones.");
            e.printStackTrace();
        }
    }

    private void guardarCancionesRecursivo(NodoCancion nodo, ObjectOutputStream oos, ListaAutores listaAutores) throws IOException {
        if (nodo != null) {
            // Crear un objeto serializable con el título y el autor
            String autor = obtenerAutorPorCancion(nodo, listaAutores);
            CancionSerializable cancionSerializable = new CancionSerializable(nodo.getNombre(), autor);
            oos.writeObject(cancionSerializable);
            
            // Recorrer en PreOrder
            guardarCancionesRecursivo(nodo.getIzquierda(), oos, listaAutores);
            guardarCancionesRecursivo(nodo.getDerecha(), oos, listaAutores);
        }
    }

    private String obtenerAutorPorCancion(NodoCancion cancion, ListaAutores listaAutores) {
        Autor autor = listaAutores.obtenerAutorPorCancion(cancion);
        return (autor != null) ? autor.getNombre() : "Desconocido";
    }

    private void guardarListasPropias(ArbolUsuarios arbolUsuarios) {
        File archivo = new File(ARCHIVO_LISTAS_PROPIAS);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            serializarListasPropiasRecursivo(arbolUsuarios.getRaiz(), oos);
            System.out.println("Listas propias guardadas exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar las listas propias.");
            e.printStackTrace();
        }
    }

    private void serializarListasPropiasRecursivo(NodoUsuario nodoUsuario, ObjectOutputStream oos) throws IOException {
        if (nodoUsuario != null) {
            // Serializar en PreOrder
            serializarListasPropiasUsuario(nodoUsuario, oos);
            serializarListasPropiasRecursivo(nodoUsuario.getIzquierda(), oos);
            serializarListasPropiasRecursivo(nodoUsuario.getDerecha(), oos);
        }
    }

    private void serializarListasPropiasUsuario(NodoUsuario usuario, ObjectOutputStream oos) throws IOException {
        ListaPropia listaPropia = usuario.getPropias();
        if (listaPropia != null) {
            Playlist playlist = listaPropia.getPrimera();
            while (playlist != null) {
                NodoPlaylist nodoPlaylist = playlist.getPrimero();
                while (nodoPlaylist != null) {
                    RegistroListaPropia registro = new RegistroListaPropia(usuario.getNombre(), playlist.getNombre(),nodoPlaylist.getCancion().getNombre());
                    oos.writeObject(registro);
                    nodoPlaylist = nodoPlaylist.getSiguiente();
                }
                playlist = playlist.getSiguiente();
            }
        }
    }

    private void guardarListasSeguidas(ArbolUsuarios arbolUsuarios) {
    File archivo = new File(ARCHIVO_LISTAS_SEGUIDAS);

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
        guardarListasSeguidasRec(arbolUsuarios.getRaiz(), oos);
        System.out.println("Listas seguidas guardadas exitosamente.");
    } catch (IOException e) {
        System.err.println("Error al guardar el archivo de listas seguidas.");
        e.printStackTrace();
    }
}

    private void guardarListasSeguidasRec(NodoUsuario nodo, ObjectOutputStream oos) throws IOException {
        if (nodo != null) {
            // Serializar listas de seguidos del nodo actual
            ListaSeguidos listaSeguidos = nodo.getSeguidas();
            
            if (listaSeguidos != null) {
                NodoSeguido seguido = listaSeguidos.getPrimero();
                
                while (seguido != null) {
                    RegistroListaSeguidas registro = new RegistroListaSeguidas(nodo.getNombre(), seguido.getUsuario().getNombre(), seguido.getPlSeguida().getNombre());
                oos.writeObject(registro);
                seguido = seguido.getSiguiente();
            }
        }
        // Recorrer subárbol izquierdo y derecho
        guardarListasSeguidasRec(nodo.getIzquierda(), oos);
        guardarListasSeguidasRec(nodo.getDerecha(), oos);
    }
}
    
    private void crearArchivoVacio(String nombreArchivo, Object objetoInicial) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(objetoInicial);
            System.out.println("Archivo guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + nombreArchivo);
            e.printStackTrace();
        }
    }
}