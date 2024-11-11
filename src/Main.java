//nota: ver si persistencia se queda o se modifica
//metodo de carga de datos de la clase persistencia: tratarlo como objeto o deserializacion directa?
//verificar donde crear las rutas de los archivos, en persistencia o en el main

//agregar los try-catch (señalados en el apunte)
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArbolUsuarios arbolUsuarios = new ArbolUsuarios();
        NodoCancion arbolCanciones = new NodoCancion();
        ListaAutores listaAutores = new ListaAutores();

        //consultar con valen
        Persistencia persistencia = new Persistencia();
        persistencia.cargarDatos();

        int opcion = 1;

        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Login");
            System.out.println("2. Nuevo Usuario");
            System.out.println("3. Ver Usuarios existentes");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            //TRY-CATCH (controlar que esté entre 1 y 4, inclusive)
            opcion = scanner.nextInt();

            if (opcion == 1) {
                loguearUsuario(arbolUsuarios, scanner);
            } else if (opcion == 2) {
                nuevoUsuario(arbolUsuarios, scanner);
            } else if (opcion == 3) {
                verUsuariosExistentes(arbolUsuarios);
            } else if (opcion == 4) {
                persistencia.guardarDatos(); //consultar con valen
                System.exit(0);
            }
        }
    }

    private static void loguearUsuario(ArbolUsuarios arbolUsuarios, Scanner scanner) {
        String nombre = scanner.nextLine();
        String password = scanner.nextLine();

        boolean existe = arbolUsuarios.verificarUsuario(nombre);

        if (existe) {
            mostrarSegundoMenu(arbolUsuarios, arbolCanciones, listaAutores, scanner, nombre); //verificar que necesito
        } else {
            System.out.println("Usuario inexistente");
        }
    }

    private static void nuevoUsuario (ArbolUsuarios arbolUsuarios, Scanner scanner) {
        String nombre = scanner.nextLine();
        String password = scanner.nextLine();

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

    private static void mostrarSegundoMenu (ArbolUsuarios arbolUsuarios, NodoCancion arbolCanciones, ListaAutores listaAutores, Scanner scanner, String nombre) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menú del Usuario Logueado ---");
            System.out.println("1. Agregar una canción");
            System.out.println("2. Crear una lista de reproducción propia");
            System.out.println("3. Agregar una canción por título a una lista de reproducción");
            System.out.println("4. Agregar una canción por autor a una lista de reproducción");
            System.out.println("5. Eliminar una lista de reproducción propia");
            System.out.println("6. Incluir la lista de otro usuario (seguirlo)");
            System.out.println("7. Salir al menú principal");
            System.out.print("Elige una opción: ");

            //TRY-CATCH (controlar rango de 1 a 7)
            int opcion = scanner.nextInt();

            if(opcion == 1) {
                datosCancion(arbolUsuarios, scanner, listaAutores);
            } else if (opcion == 2) {
                crearPlaylistPropia(nombre, arbolCanciones, scanner, arbolUsuarios);
            } else if (opcion == 3) {
                agregarCancionAPlaylist(arbolUsuarios, arbolCanciones, nombre, scanner);
            }else if (opcion == 4) {
                agregarCancionPorAutor(arbolUsuarios, arbolCanciones, nombre, scanner, listaAutores);
            } else if (opcion == 5) {
                eliminarPlaylistPropia(nombre, scanner, arbolUsuarios);
            } else if (opcion == 6) {
                seguirUsuario(arbolUsuarios, nombre, scanner);
            } else if (opcion == 7) {
                continuar = false;
            }
        }
    }

    private static void datosCancion(NodoCancion arbolCanciones, Scanner scanner, ListaAutores listaAutores) {
        //TRY_CATCH
        String titulo = scanner.nextLine();
        String autor = scanner.nextLine();

        boolean existeCancion = arbolCanciones.verificarCancion(titulo, autor);
        boolean existePorAutor = listaAutores.verificarCancionEnAutor(titulo, autor);
        
        if (!existeCancion && !existePorAutor) {
            listaAutores.agregarCancion(titulo, autor);
        }
    }

    private static void crearPlaylistPropia(String nombre, NodoCancion arbolCanciones, Scanner scanner, ArbolUsuarios arbolUsuarios) {
        String playlist = scanner.nextLine();
        boolean playlistExiste = arbolUsuarios.verificarPlaylist(nombre, playlist);

        if  (!playlistExiste) {
            arbolUsuarios.crearPlaylist(nombre, playlist);
        } else {
            System.out.println("la playlist no existe");
        }
    }

    private static void agregarCancionAPlaylist(ArbolUsuarios arbolUsuarios, NodoCancion arbolCanciones,String nombre, Scanner scanner) {
        //TRY-CATCH
        String playlist = scanner.nextLine();
        String tituloCancion = scanner.nextLine();

        boolean playlistExiste = arbolUsuarios.verificarPlaylist(nombre, playlist);
        boolean cancionExiste = arbolUsuarios.verificarCancion(tituloCancion);
        if(!cancionExiste) {
            //TRY-CATCH
            String autor = scanner.nextLine();
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
        //TRY-CATCH
        String playlist = scanner.nextLine();
        String autor = scanner.nextLine();

        listaAutores.mostrarCancionesAutor(autor);
        //TRY-CATCH
        String cancion = scanner.nextLine();
        
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
        //TRY-CATCH
        String playlist = scanner.nextLine();
        boolean existe = arbolUsuarios.verificarPlaylist(playlist, nombre);

        if (existe) {
            arbolUsuarios.eliminarPlaylist(nombre, scanner);
        } else {
            System.out.println("esta playlist no existe");
        }
    }

    private static void seguirUsuario(ArbolUsuarios arbolUsuarios,String nombre,Scanner scanner) {
        //TRI-CATCH
        String seguirNombre = scanner.nextLine();
        boolean existe = arbolUsuarios.verificarUsuario(nombre);

        if(existe) {
            arbolUsuarios.mostrarListaPropias(seguirNombre);
            //TRY-CATCH
            String playlist = scanner.nextLine();
            boolean playlistEnSeguidas = arbolUsuarios.verificarSeguidas(nombre, playlist);

            if(!playlistEnSeguidas) {
                arbolUsuarios.agregarASeguidas(nombre, playlist);
            }
        } else {
            System.out.println("este usuario no existe");
        }
    }
}