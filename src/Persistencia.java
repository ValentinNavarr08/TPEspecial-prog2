//TODO: falta adaptarla al programa

import java.io.*;

public class Persistencia {

    private static final String ARCHIVO_USUARIOS = "ArchUsuarios.ser";
    private static final String ARCHIVO_CANCIONES = "ArchCanciones.ser";
    private static final String ARCHIVO_LISTAS_PROPIAS = "ArchListasPropias.ser";
    private static final String ARCHIVO_LISTAS_SEGUIDAS = "ArchListasSeguidas.ser";

    //private ArbCanciones arbCanciones;
    //private ListasPropias listasPropias;
    //private ListasSeguidos listasSeguidos;

    private ArbolUsuarios arbUsuarios;
    private NodoCancion arbCanciones;
    private ListaPropia listasPropias;
    private ListaSeguidos listasSeguidos;

    /*public void cargarDatos() {
        arbUsuarios = cargarUsuarios();
        arbCanciones = cargarCanciones();
        listasPropias = cargarListasPropias();
        listasSeguidos = cargarListasSeguidas();
    }

    public void guardarDatos() {
        guardarUsuarios(arbUsuarios);
        guardarCanciones(arbCanciones);
        guardarListasPropias(listasPropias);
        guardarListasSeguidas(listasSeguidos);
    }

    private ArbolUsuarios cargarUsuarios() {
        File archivo = new File(ARCHIVO_USUARIOS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("Archivo de usuarios encontrado. Deserializando...");
                return (ArbolUsuarios) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al deserializar el archivo de usuarios.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de usuarios no encontrado. Creando archivo nuevo...");
            crearArchivoVacio(ARCHIVO_USUARIOS, new ArbolUsuarios());
        }
        return new ArbolUsuarios();
    }

    private NodoCancion cargarCanciones() {
        File archivo = new File(ARCHIVO_CANCIONES);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("Archivo de canciones encontrado. Deserializando...");
                return (NodoCancion) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al deserializar el archivo de canciones.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de canciones no encontrado. Creando archivo nuevo...");
            crearArchivoVacio(ARCHIVO_CANCIONES, new NodoCancion());
        }
        return new NodoCancion();
    }

    private ListaPropia cargarListasPropias() {
        File archivo = new File(ARCHIVO_LISTAS_PROPIAS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("Archivo de listas propias encontrado. Deserializando...");
                return (ListaPropia) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al deserializar el archivo de listas propias.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de listas propias no encontrado. Creando archivo nuevo...");
            crearArchivoVacio(ARCHIVO_LISTAS_PROPIAS, new ListaPropia());
        }
        return new ListaPropia();
    }

    private ListaSeguidos cargarListasSeguidas() {
        File archivo = new File(ARCHIVO_LISTAS_SEGUIDAS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("Archivo de listas seguidas encontrado. Deserializando...");
                return (ListaSeguidos) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al deserializar el archivo de listas seguidas.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de listas seguidas no encontrado. Creando archivo nuevo...");
            crearArchivoVacio(ARCHIVO_LISTAS_SEGUIDAS, new ListaSeguidos());
        }
        return new ListaSeguidos();
    }

    private void guardarUsuarios(ArbolUsuarios arbUsuarios) {
        crearArchivoVacio(ARCHIVO_USUARIOS, arbUsuarios);
    }

    private void guardarCanciones(NodoCancion arbCanciones) {
        crearArchivoVacio(ARCHIVO_CANCIONES, arbCanciones);
    }

    private void guardarListasPropias(ListaPropia listasPropias) {
        crearArchivoVacio(ARCHIVO_LISTAS_PROPIAS, listasPropias);
    }

    private void guardarListasSeguidas(ListaSeguidos listasSeguidos) {
        crearArchivoVacio(ARCHIVO_LISTAS_SEGUIDAS, listasSeguidos);
    }

    // Método para crear un archivo vacío inicializando con el objeto indicado
    //TODO ¿dejar el parametro object o reemplazarlo para mantener los parametros comunes de la catedra?
    private void crearArchivoVacio(String nombreArchivo, Object objetoInicial) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(objetoInicial);
            System.out.println("Archivo " + nombreArchivo + " guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + nombreArchivo);
            e.printStackTrace();
        }
    }*/
}