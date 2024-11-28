//verificar donde crear las rutas de los archivos, en persistencia o en el main

//TODO loguearUsuario(arbolUsuarios,arbolCanciones, listaAutores, scanner)

//TODO nuevoUsuario(arbolUsuarios, scanner)
//TODO verificarUsuario(String nombre) (Clase: ArbolUsuarios)


//TODO verificarCancionEnPlaylist(String playlist, String cancion) (Clase: ArbolUsuarios)

//TODO agregarCancionAPropias(String nombre, String playlist, String cancion, String autor) (Clase: ArbolUsuarios)

//TODO verificarSeguidas(String nombre, String playlist) (Clase: ArbolUsuarios)

//TODO mostrarListaPropias(String seguirNombre) (Clase: ArbolUsuarios)



import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static int OPCION_MIN = 1;
    private static int OPCION_MAX = 4;
    private static Scanner scanner = new Scanner(System.in);
    private static ArbolUsuarios arbolUsuarios;
    private static ArbolCanciones arbolCanciones;
    private static ListaAutores listaAutores;
    private static NodoUsuario usuarioLogeado;

    public static void main(String[] args) {

         arbolUsuarios = new ArbolUsuarios();
         arbolCanciones = new ArbolCanciones();
         listaAutores = new ListaAutores();
         usuarioLogeado = null;

//        Persistencia persistencia = new Persistencia();
//        persistencia.cargarDatos();

        int opcion;
        boolean seguir = true;

        while (seguir) {
            opcion = obtenerEnteroValido(1, 4,1);
            
            switch (opcion) {
                case 1: loguearUsuario();break;
                case 2: nuevoUsuario();break;
                case 3: verUsuariosExistentes();break;
                case 4: //persistencia.guardarDatos();
                    System.exit(0); break;
                default: System.out.println("Algo Salió mal");break;
            }
            if (comienzaNuevaInteraccion()){
                seguir = true;
            }
            else seguir = false;
        }
    }
    public static boolean comienzaNuevaInteraccion(){
        System.out.println("-------------------------------");
        System.out.println("Desea acceder al Menu otra vez?");
        return determinarBooleano();
    }

    public static boolean determinarBooleano() {
        String ingresoTeclado = "";
        while (!(ingresoTeclado.equals("SI") || ingresoTeclado.equals("NO"))) {
            // si está dentro de las opciones
            try {
                System.out.println("Recorda que en esta opcion solo podes poner SI o NO ");
                ingresoTeclado = scanner.next();
                System.out.println();
            } catch (Exception e) {
                System.out.println("Opción inválida, intente de nuevo: ");
                scanner.next();
            }
        }
        return ingresoTeclado.equals("SI");
    }

    private static void imprimirMenu1() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Login");
        System.out.println("2. Nuevo Usuario");
        System.out.println("3. Ver Usuarios existentes");
        System.out.println("4. Salir");
        System.out.print("Elige una opcion: ");
    }

    public static int obtenerEnteroValido(int opcionMin, int opcionMax, int menu) {
        int numero = -100000;
        while (!(numero >= OPCION_MIN && numero <= OPCION_MAX)) {
            // si está dentro del rango

            try {
                if(menu == 1){
                    imprimirMenu1();
                }
                if(menu == 2){
                    imprimirMenu2();
                }
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

        String condicion = "\\w{1,20}";

        String texto = scanner.nextLine();
        //compruebo condocion de contraseña
        Pattern pattern = Pattern.compile(condicion);
        Matcher matcher = pattern.matcher(texto);

        while (!matcher.matches()) {
            try {
                texto = scanner.nextLine();
                matcher = pattern.matcher(texto);
            } catch (Exception e) {
                System.out.println("Entrada inválida, intente de nuevo: ");
                scanner.next();
            }
        }
        return texto;
    }    

    private static void loguearUsuario() {
        System.out.println("Ingrese el nombre del Usuario");
        String nombre = obtenerStringValido();
        System.out.println("Ingrese la contraseña del Usuario");
        String password = obtenerStringValido();

        NodoUsuario usuario = arbolUsuarios.buscarUsuario(nombre);

        if (usuario != null && usuario.getContrasena() == password) {
            segundoMenu(nombre);
            usuarioLogeado = usuario;
            System.out.println("Usted se ha logeado correctamente como:"+nombre);

        } else {
            System.out.println("Usuario inexistente");
        }

    }


    private static void nuevoUsuario () {
        System.out.println("Usted selecciono la opcion Logearse como un nuevo usuario");
        System.out.println("Ingrese el nombre del Usuario");
        String nombre = obtenerStringValido();
        System.out.println("Ingrese la contrseña del Usuario");
        System.out.println("Recuerde que la contrseña debe contener al menos 3 letras");
        String password = obtenercontraValida();

        NodoUsuario nombreIngresado = arbolUsuarios.buscarUsuario(nombre);

        //si no hay un usuario con ese nombre, y la contra coicide, lo agrega
        if (nombreIngresado == null) {
            arbolUsuarios.insertarUsuario(nombre, password);
            System.out.println("El Usuario "+nombre +", se ha creado satisfactoriamente");
            usuarioLogeado = arbolUsuarios.buscarUsuario(nombre);
            segundoMenu();
        } else {
            System.out.println("Usuario ya existente");
        }
    }
    public static String obtenercontraValida() {

        String condicion = "\\S{3,}";

        String texto = scanner.nextLine();
        //compruebo condocion de contraseña
        Pattern pattern = Pattern.compile(condicion);
        Matcher matcher = pattern.matcher(texto);

        while (!matcher.matches()) {
            try {
                texto = scanner.nextLine();
                matcher = pattern.matcher(texto);
            } catch (Exception e) {
                System.out.println("Entrada inválida, intente de nuevo: ");
                System.out.println("Recuerde que la contrseña debe contener al menos 3 letras");
                scanner.next();
            }
        }
        return texto;
    }

    private static void verUsuariosExistentes() {
        arbolUsuarios.imprimirOrdenado();
    }

    private static void segundoMenu() {
        boolean continuar = true;

        while (continuar) {
            int opcion = obtenerEnteroValido(1, 7,2);

            switch (opcion) {
                case 1: agregarCancion();
                case 2: crearPlaylistPropia();
                case 3: agregarCancionAPlaylist();
                /*case 4: agregarCancionPorAutor(arbolUsuarios, arbolCanciones, nombre, scanner, listaAutores);
                case 5: eliminarPlaylistPropia(nombre, scanner, arbolUsuarios);
                case 6: seguirUsuario(arbolUsuarios, nombre, scanner);*/
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

    private static void agregarCancion() {
        System.out.println("Usted ha seleccionado la opcion de agregar una cancion");
        System.out.println("Ingrese el nombre de la nueva cancion");
        String Ntitulo = obtenerStringValido();
        System.out.println("Ingrese el nombre del Autor de la cancion");
        String Nautor = obtenerStringValido();

        NodoCancion cancion = arbolCanciones.buscarCancion(Ntitulo);
        boolean existeAutor = listaAutores.verificarAutor(Nautor);

        //si no existe la creo
        if (cancion == null) {
            NodoCancion insertar = new NodoCancion(Ntitulo,Nautor);
            arbolCanciones.insertarCancion(insertar);
            //si no existe tampoco el autor
            if(!existeAutor){
                listaAutores.insertar(Nautor);
            }
            Autor autor = listaAutores.obtenerAutor(Nautor);
            NodoCancion cancionyaexistente = arbolCanciones.buscarCancion(Ntitulo);
            autor.agregarCancion(cancionyaexistente);
            System.out.println("La cancion se ha creado con exito!");
        }
        else{
            System.out.println("Esta cancion ya forma parte de nuestra biblioteca de canciones");
        }
        segundoMenu();

    }

    private static void crearPlaylistPropia() {
        String playlist = obtenerStringValido();


        if  (!usuarioLogeado.tienePLPropia(playlist)) {
            usuarioLogeado.agegarPLPropia(playlist);
            System.out.println("Se ha agregado la playlist: "+playlist+", vacia");
        } else {
            System.out.println("Usted ya tiene una playlist con ese nombre");
        }
    }

    private static void agregarCancionAPlaylist() {
        String playlist = obtenerStringValido();
        String tituloCancion = obtenerStringValido();


        Playlist playlistPropia = usuarioLogeado.obtenerPropia(playlist);
        NodoCancion cancion = arbolCanciones.buscarCancion(tituloCancion);

        //si existe la playlist
        if(playlistPropia != null){
            //y la cancion en el arbol
            if(cancion != null){
                //y no tiene la cancion en la playlist
                if(!playlistPropia.tieneCancion(cancion)){
                    playlistPropia.agregarCancion(cancion);
                    System.out.println("La cancion "+tituloCancion+" ha sido agregada a la lista: "+playlist);
                }
                else{
                    System.out.println("La cancion "+tituloCancion+" ya está en la lista: "+playlist);
                }
            }
            else System.out.println("La cancion "+tituloCancion+"no existe en la base de Datos, agregue la cancion antes de insertarla en la Playlist");
        }
        else System.out.println("La lista: "+playlist+", no existe para el usuario: "+usuario.getNombre());

    }
    //este metodo se encarga de elegir una cancion de un autor y agregarla a una playlist propia
    /*private static void agregarCancionPorAutor(ArbolUsuarios arbolUsuarios,NodoCancion arbolCanciones,String nombre,Scanner scanner, ListaAutores listaAutores) {
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
    }*/
}
