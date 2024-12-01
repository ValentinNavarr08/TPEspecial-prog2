import java.util.Scanner;
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
         
         Archivos archivos = new Archivos();
         archivos.cargarDatos(arbolUsuarios, arbolCanciones, listaAutores);
         
        int opcion;
        boolean seguir = true;
        
        while (seguir) {
            //si vuelve al menu principal se deslogea
            if(usuarioLogeado != null){
                usuarioLogeado = null;
            }
            opcion = obtenerEnteroValido(1, 4,1);
            
            switch (opcion) {
                case 1: loguearUsuario();break;
                case 2: nuevoUsuario();break;
                case 3: verUsuariosExistentes();break;
                case 4: archivos.guardarDatos(arbolUsuarios, arbolCanciones, listaAutores);
                    System.exit(0); break;
                default: System.out.println("Algo salió mal");break;
            }
            if (comienzaNuevaInteraccion()){
                seguir = true;
            }
            else {
                seguir = false;
                archivos.guardarDatos(arbolUsuarios, arbolCanciones, listaAutores);
            }
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
                System.out.println("Recorda que en esta opción solo podes poner SI o NO ");
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
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Login");
        System.out.println("2. Nuevo Usuario");
        System.out.println("3. Ver Usuarios existentes");
        System.out.println("4. Salir");
        System.out.print("Elige una opción: ");
    }

    public static int obtenerEnteroValido(int opcionMin, int opcionMax, int menu) {
        int numero = -100000;
        while (!(numero >= opcionMin && numero <= opcionMax)) {
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
        
        if (usuario != null && usuario.getContrasena().equals(password)) {
            usuarioLogeado = usuario;
            System.out.println("Usted se ha logueado correctamente como:"+nombre);
            segundoMenu();
            
        } else {
            System.out.println("Usuario inexistente");
        }
    }


    private static void nuevoUsuario () {
        System.out.println("Usted selecciono la opcion Loguearse como un nuevo usuario");
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
                case 1: agregarCancion();break;
                case 2: crearPlaylistPropia();break;
                case 3: agregarCancionAPlaylist();break;
                case 4: agregarCancionPorAutor();break;
                case 5: eliminarPlaylistPropia();break;
                case 6: seguirUsuario();break;
                case 7: continuar = false; break;
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
        System.out.println("Usted ha seleccionado la opcion de agregar una canción");
        System.out.println("Ingrese el nombre de la nueva canción");
        String Ntitulo = obtenerStringValido();
        System.out.println("Ingrese el nombre del Autor de la canción");
        String Nautor = obtenerStringValido();
        
        NodoCancion cancion = arbolCanciones.buscarCancion(Ntitulo);
        boolean existeAutor = listaAutores.verificarAutor(Nautor);
        
        //si no existe la creo
        if (cancion == null) {
            NodoCancion insertar = new NodoCancion(Ntitulo);
            arbolCanciones.insertarCancion(insertar);
            //si no existe tampoco el autor
            if(!existeAutor){
                listaAutores.insertar(Nautor);
            }
            Autor autor = listaAutores.obtenerAutor(Nautor);
            NodoCancion cancionyaexistente = arbolCanciones.buscarCancion(Ntitulo);
            autor.agregarCancion(cancionyaexistente);
            System.out.println("La canción se ha creado con exito!");
        }
        else{
            System.out.println("Esta canción ya forma parte de nuestra biblioteca de canciones");
        }
    }

    private static void crearPlaylistPropia() {
        System.out.println("Usted ha seleccionado la opcion de crear una playlist propia");
        System.out.println("Ingrese el nombre de la playlist");
        String playlist = obtenerStringValido();
        
        if  (!usuarioLogeado.tienePLPropia(playlist)) {
            usuarioLogeado.agegarPLPropia(playlist);
            System.out.println("Se ha agregado la playlist: "+playlist+", vacia");
        } else {
            System.out.println("Usted ya tiene una playlist con ese nombre");
        }
    }

    private static void agregarCancionAPlaylist() {
        System.out.println("Usted ha seleccionado la opcion de agregar una canción a una playlist propia");
        System.out.println("Estas son sus playlists propias:");
        usuarioLogeado.imprimirPLPropias();
        System.out.println("Ingrese el nombre de la Playlist:");
        String playlist = obtenerStringValido();
        System.out.println("Ingrese el nombre de la canción");
        String tituloCancion = obtenerStringValido();
        
        Playlist playlistPropia = usuarioLogeado.obtenerPropia(playlist);
        System.out.println(playlistPropia);
        NodoCancion cancion = arbolCanciones.buscarCancion(tituloCancion);
        
        //si existe la playlist
        if(playlistPropia != null){
            //y la cancion en el arbol
            if(cancion != null){
                //y no tiene la cancion en la playlist
                if(!playlistPropia.tieneCancion(cancion)){
                    playlistPropia.agregarCancion(cancion);
                    System.out.println("La canción "+tituloCancion+" ha sido agregada a la lista: "+playlist);
                }
                else{
                    System.out.println("La canción "+tituloCancion+" ya está en la lista: "+playlist);
                }
            }
            else System.out.println("La canción "+tituloCancion+"no existe en la base de Datos, agregue la canción antes de insertarla en la Playlist");
        }
        else System.out.println("La lista: "+playlist+", no existe para el usuario: "+usuarioLogeado.getNombre());
    }


    //este metodo se encarga de elegir una cancion de un autor y agregarla a una playlist propia
    private static void agregarCancionPorAutor() {
        System.out.println("Usted ha seleccionado la opcion de agregar una canción a una playlist propia por autor");
        System.out.println("Estas son sus playlists propias:");
        usuarioLogeado.imprimirPLPropias();
        System.out.println("Ingrese el nombre de la Playlist:");
        String playlist = obtenerStringValido();
        System.out.println("Ingrese el nombre del Autor:");
        String nombreAutor = obtenerStringValido();
        System.out.println("Estas son todas las canciones de " + nombreAutor+":");
        if(listaAutores.obtenerAutor(nombreAutor) !=null){
            listaAutores.obtenerAutor(nombreAutor).imprimirCanciones();
        }
        else {
            System.out.println("Este autor no existe");
        }
        System.out.println("Ingrese la cancion que desea agregar:");
        String tituloCancion = obtenerStringValido();
        
        Playlist playlistPropia = usuarioLogeado.obtenerPropia(playlist);
        NodoCancion cancion = arbolCanciones.buscarCancion(tituloCancion);
        
        if(playlistPropia != null){
            //y la cancion en el arbol
            if(cancion != null){
                //y no tiene la cancion en la playlist
                if(!playlistPropia.tieneCancion(cancion)){
                    playlistPropia.agregarCancion(cancion);
                    System.out.println("La canción "+tituloCancion+" ha sido agregada a la lista: "+playlist);
                }
                else{
                    System.out.println("La canción "+tituloCancion+" ya está en la lista: "+playlist);
                }
            }
            else System.out.println("La canción "+tituloCancion+"no existe en la base de Datos, agregue la canción antes de insertarla en la Playlist");
        }
        else System.out.println("La lista: "+playlist+", no existe para el usuario: "+usuarioLogeado.getNombre());
    }

    private static void eliminarPlaylistPropia() {
        System.out.println("Usted ha seleccionado la opcion de eliminar una de sus Playlist");
        System.out.println("Sus playlists son las siguientes:");
        usuarioLogeado.imprimirPLPropias();
        
        System.out.println("Ingrese el nombre de la playlist que desea elimiar:");
        String playlist = obtenerStringValido();
        
        Playlist eliminar = usuarioLogeado.getPropias().obtenerPlaylist(playlist);
        
        if (eliminar != null) {
            usuarioLogeado.eliminarPropia(eliminar);
            System.out.println("La playlist: "+eliminar+" se ha eliminado correctamente");
        } else {
            System.out.println("esta playlist no existe");
        }
    }

    private static void seguirUsuario() {
        System.out.println("Usted ha seleccionado la opcion de seguir un usuario");
        System.out.println("Ingrese el nombre del usuario a Seguir");
        String seguirNombre = obtenerStringValido();
        NodoUsuario aseguir = arbolUsuarios.buscarUsuario(seguirNombre);
        
        if(aseguir != null) {
            System.out.println("Estas son todas las listas del usuario:" + aseguir.getNombre());
            aseguir.imprimirPLPropias();
            System.out.println("Ingrese el nombre de la Playlist:");
            String playlist = obtenerStringValido();
            
            Playlist PlaSeguir = aseguir.getPropias().obtenerPlaylist(playlist);
            
            if(PlaSeguir != null /*&& !usuarioLogeado.getSeguidas().tiene(PlaSeguir)*/) {
                usuarioLogeado.agregarSeguida(aseguir, PlaSeguir);
                System.out.println("se ha seguido la playlist:"+ PlaSeguir.getNombre());
            }
        } else {
            System.out.println("esta Playlist no existe en la lista de:" + aseguir.getNombre());
        }
    }
}