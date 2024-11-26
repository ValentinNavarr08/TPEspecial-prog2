//verificar donde crear las rutas de los archivos, en persistencia o en el main

//TODO loguearUsuario(arbolUsuarios,arbolCanciones, listaAutores, scanner)

//TODO nuevoUsuario(arbolUsuarios, scanner)
//TODO verificarUsuario(String nombre) (Clase: ArbolUsuarios)

//TODO agregarUsuario(String nombre, String password) (Clase: ArbolUsuarios)

//TODO mostrarUsuariosRec() (Clase: ArbolUsuarios)

//TODO verificarCancionEnPlaylist(String playlist, String cancion) (Clase: ArbolUsuarios)

//TODO agregarCancionAPropias(String nombre, String playlist, String cancion, String autor) (Clase: ArbolUsuarios)

//TODO verificarSeguidas(String nombre, String playlist) (Clase: ArbolUsuarios)

//TODO mostrarListaPropias(String seguirNombre) (Clase: ArbolUsuarios)

/*

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
public class Main {

    private static int OPCION_MIN = 1;
    private static int OPCION_MAX = 4;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArbolUsuarios arbolUsuarios = new ArbolUsuarios();
        NodoCancion arbolCanciones = new NodoCancion();
        ListaAutores listaAutores = new ListaAutores();

        Persistencia persistencia = new Persistencia();
        persistencia.cargarDatos();

        int opcion = 1;

        while (true) {
            imprimirMenu1();

            opcion = obtenerEnteroValido(1, 4);
            
            switch (opcion) {
                case 1: loguearUsuario(arbolUsuarios,arbolCanciones, listaAutores, scanner);break;
                case 2: nuevoUsuario(arbolUsuarios, scanner);break;
                case 3: verUsuariosExistentes(arbolUsuarios);break;
                case 4: persistencia.guardarDatos();
                    System.exit(0); break;
                default: System.out.println("Algo Salió mal");break;
            }

        }
    }

    private static void imprimirMenu1() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Login");
        System.out.println("2. Nuevo Usuario");
        System.out.println("3. Ver Usuarios existentes");
        System.out.println("4. Salir");
        System.out.print("Elige una opción: ");
    }

    public static int obtenerEnteroValido(int opcionMin, int opcionMax) {
        int numero = 1;
        while (!(numero >= opcionMin && numero <= opcionMax)) {
            // si está dentro del rango
            try {
                numero = scanner.nextInt();
                System.out.println();
            } catch (Exception e) {
                System.out.println("Opción inválida, intente de nuevo: ");
                scanner.next();
            }
        }
        return numero;
    }

    public static String obtenerStringValido() {
        String texto = "";
        while (texto.isEmpty()) {
            try {
                texto = scanner.nextLine().trim();
                if (texto.isEmpty()) {
                    System.out.println("Entrada vacía, intente de nuevo: ");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida, intente de nuevo: ");
                scanner.next();
            }
        }
        return texto;
    }    

    private static void loguearUsuario(ArbolUsuarios arbolUsuarios, NodoCancion arbolCanciones, ListaAutores listaAutores) {
        String nombre = obtenerStringValido();
        String password = obtenerStringValido();

        boolean existe = arbolUsuarios.verificarUsuario(nombre);

        if (existe) {
            segundoMenu(arbolUsuarios, arbolCanciones, listaAutores, scanner, nombre); //verificar que necesito
        } else {
            System.out.println("Usuario inexistente");
        }
    }

    private static void nuevoUsuario (ArbolUsuarios arbolUsuarios) {
        String nombre = obtenerStringValido();
        String password = obtenerStringValido();

        boolean existe = arbolUsuarios.verificarUsuario(nombre, password);

        if (!existe) {
            arbolUsuarios.agregarUsuario(nombre, password);
        } else {
            System.out.println("Usuario ya existente");
        }
    }

    private static void verUsuariosExistentes(ArbolUsuarios arbolUsuarios) {
        arbolUsuarios.mostrarUsuariosRec();
    }

    private static void segundoMenu (ArbolUsuarios arbolUsuarios, NodoCancion arbolCanciones, ListaAutores listaAutores, Scanner scanner, String nombre) {
        boolean continuar = true;

        while (continuar) {
            imprimirMenu2();

            int opcion = obtenerEnteroValido(1, 7);

            switch (opcion) {
                case 1: datosCancion(arbolCanciones, scanner, listaAutores);
                case 2: crearPlaylistPropia(nombre, arbolCanciones, scanner, arbolUsuarios);
                case 3: agregarCancionAPlaylist(arbolUsuarios, arbolCanciones, nombre, scanner);
                case 4: agregarCancionPorAutor(arbolUsuarios, arbolCanciones, nombre, scanner, listaAutores);
                case 5: eliminarPlaylistPropia(nombre, scanner, arbolUsuarios);
                case 6: seguirUsuario(arbolUsuarios, nombre, scanner);
                case 7: continuar = false;
            }
        }
    }

    private static void imprimirMenu2() {
        System.out.println("\n--- Menú del Usuario Logueado ---");
        System.out.println("1. Agregar una canción");
        System.out.println("2. Crear una lista de reproducción propia");
        System.out.println("3. Agregar una canción por título a una lista de reproducción");
        System.out.println("4. Agregar una canción por autor a una lista de reproducción");
        System.out.println("5. Eliminar una lista de reproducción propia");
        System.out.println("6. Incluir la lista de otro usuario (seguirlo)");
        System.out.println("7. Salir al menú principal");
        System.out.print("Elige una opción: ");
    }

    private static void datosCancion(NodoCancion arbolCanciones, Scanner scanner, ListaAutores listaAutores) {
        String titulo = obtenerStringValido();
        String autor = obtenerStringValido();

        boolean existeCancion = arbolCanciones.verificarCancion(titulo, autor);
        boolean existePorAutor = listaAutores.verificarCancionEnAutor(titulo, autor);
        
        if (!existeCancion && !existePorAutor) {
            listaAutores.agregarCancion(titulo, autor);
        }
    }

    private static void crearPlaylistPropia(String nombre, NodoCancion arbolCanciones, Scanner scanner, ArbolUsuarios arbolUsuarios) {
        String playlist = obtenerStringValido();
        boolean playlistExiste = arbolUsuarios.verificarPlaylist(nombre, playlist);

        if  (!playlistExiste) {
            arbolUsuarios.crearPlaylist(nombre, playlist);
        } else {
            System.out.println("la playlist no existe");
        }
    }

    private static void agregarCancionAPlaylist(ArbolUsuarios arbolUsuarios, NodoCancion arbolCanciones,String nombre, Scanner scanner) {
        String playlist = obtenerStringValido();
        String tituloCancion = obtenerStringValido();

        boolean playlistExiste = arbolUsuarios.verificarPlaylist(nombre, playlist);
        boolean cancionExiste = arbolUsuarios.verificarCancion(tituloCancion);
        if(!cancionExiste) {
            
            String autor = obtenerStringValido();
            arbolUsuarios.agregarCancion(tituloCancion, autor);
        }

        if (!playlistExiste) {
            arbolUsuarios.crearPlaylist(nombre, playlist);
            arbolUsuarios.agregarCancionAPropias(playlist, tituloCancion);
        } else {
            boolean cancionExisteEnPlaylist = arbolUsuarios.verificarCancionEnPlaylist(nombre, playlist, tituloCancion);
            if (!cancionExisteEnPlaylist) {
                arbolUsuarios.agregarCancionAPropias(playlist, tituloCancion);
            }
        }
    }
    //este metodo se encarga de elegir una cancion de un autor y agregarla a una playlist propia
    private static void agregarCancionPorAutor(ArbolUsuarios arbolUsuarios,NodoCancion arbolCanciones,String nombre,Scanner scanner, ListaAutores listaAutores) {
        String playlist = obtenerStringValido();
        String autor = obtenerStringValido();

        listaAutores.mostrarCancionesAutor(autor);
        String cancion = obtenerStringValido();
        
        boolean playlistExiste = arbolUsuarios.verificarPlaylist(nombre, playlist);
        if (playlistExiste) {
            boolean cancionExisteEnPlaylist = verificarCancionEnPlaylist(playlist, cancion);
            if (!cancionExisteEnPlaylist) {
                arbolUsuarios.agregarCancionAPropias(nombre, playlist, cancion, autor);
            } else {
                System.out.println("esta cancion ya existe en la playlist");
            }
        } else {
            arbolUsuarios.crearPlaylist(nombre, playlist);
            arbolUsuarios.agregarCancionAPropias(nombre, playlist, cancion, autor);
        }
    }

    private static void eliminarPlaylistPropia(String nombre,Scanner scanner,ArbolUsuarios arbolUsuarios) {
        String playlist = obtenerStringValido();
        boolean existe = arbolUsuarios.verificarPlaylist(playlist, nombre);

        if (existe) {
            arbolUsuarios.eliminarPlaylist(nombre, scanner);
        } else {
            System.out.println("esta playlist no existe");
        }
    }

    private static void seguirUsuario(ArbolUsuarios arbolUsuarios,String nombre,Scanner scanner) {
        
        String seguirNombre = obtenerStringValido();
        boolean existe = arbolUsuarios.verificarUsuario(nombre);

        if(existe) {
            arbolUsuarios.mostrarListaPropias(seguirNombre);
            
            String playlist = obtenerStringValido();
            boolean playlistEnSeguidas = arbolUsuarios.verificarSeguidas(nombre, playlist);

            if(!playlistEnSeguidas) {
                arbolUsuarios.agregarASeguidas(nombre, playlist);
            }
        } else {
            System.out.println("este usuario no existe");
        }
    }
}*/
