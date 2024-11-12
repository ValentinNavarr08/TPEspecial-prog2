//TODO: falta adaptarla al programa

import java.io.*;

public class Persistencia {

    private static final String ARCHIVO_USUARIOS = "ArchUsuarios.ser";
    private static final String ARCHIVO_CANCIONES = "ArchCanciones.ser";
    private static final String ARCHIVO_LISTAS_PROPIAS = "ArchListasPropias.ser";
    private static final String ARCHIVO_LISTAS_SEGUIDAS = "ArchListasSeguidas.ser";

    private ArbUsuarios arbUsuarios;
    private ArbCanciones arbCanciones;
    private ListasPropias listasPropias;
    private ListasSeguidos listasSeguidos;

    public void cargarDatos() {
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

    private ArbUsuarios cargarUsuarios() {
        File archivo = new File(ARCHIVO_USUARIOS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("Archivo de usuarios encontrado. Deserializando...");
                return (ArbUsuarios) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al deserializar el archivo de usuarios.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de usuarios no encontrado. Creando archivo nuevo...");
            crearArchivoVacio(ARCHIVO_USUARIOS, new ArbUsuarios());
        }
        return new ArbUsuarios();
    }

    private ArbCanciones cargarCanciones() {
        File archivo = new File(ARCHIVO_CANCIONES);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("Archivo de canciones encontrado. Deserializando...");
                return (ArbCanciones) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al deserializar el archivo de canciones.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de canciones no encontrado. Creando archivo nuevo...");
            crearArchivoVacio(ARCHIVO_CANCIONES, new ArbCanciones());
        }
        return new ArbCanciones();
    }

    private ListasPropias cargarListasPropias() {
        File archivo = new File(ARCHIVO_LISTAS_PROPIAS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("Archivo de listas propias encontrado. Deserializando...");
                return (ListasPropias) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al deserializar el archivo de listas propias.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de listas propias no encontrado. Creando archivo nuevo...");
            crearArchivoVacio(ARCHIVO_LISTAS_PROPIAS, new ListasPropias());
        }
        return new ListasPropias();
    }

    private ListasSeguidos cargarListasSeguidas() {
        File archivo = new File(ARCHIVO_LISTAS_SEGUIDAS);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("Archivo de listas seguidas encontrado. Deserializando...");
                return (ListasSeguidos) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al deserializar el archivo de listas seguidas.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo de listas seguidas no encontrado. Creando archivo nuevo...");
            crearArchivoVacio(ARCHIVO_LISTAS_SEGUIDAS, new ListasSeguidos());
        }
        return new ListasSeguidos();
    }

    private void guardarUsuarios(ArbUsuarios arbUsuarios) {
        crearArchivoVacio(ARCHIVO_USUARIOS, arbUsuarios);
    }

    private void guardarCanciones(ArbCanciones arbCanciones) {
        crearArchivoVacio(ARCHIVO_CANCIONES, arbCanciones);
    }

    private void guardarListasPropias(ListasPropias listasPropias) {
        crearArchivoVacio(ARCHIVO_LISTAS_PROPIAS, listasPropias);
    }

    private void guardarListasSeguidas(ListasSeguidos listasSeguidos) {
        crearArchivoVacio(ARCHIVO_LISTAS_SEGUIDAS, listasSeguidos);
    }

    // Método para crear un archivo vacío inicializando con el objeto indicado
    private void crearArchivoVacio(String nombreArchivo, Object objetoInicial) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(objetoInicial);
            System.out.println("Archivo " + nombreArchivo + " guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + nombreArchivo);
            e.printStackTrace();
        }
    }
}