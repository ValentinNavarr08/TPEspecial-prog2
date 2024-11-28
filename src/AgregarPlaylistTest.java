import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AgregarPlaylistTest {



    private ArbolUsuarios arbolU;
    private ArbolCanciones arbolC;
    private ListaAutores listaA;

    @Before
    public void setUp() {
        arbolU = new ArbolUsuarios();
    }

    @Test
    public void testInsertarUsuario() {
        arbolU.insertarUsuario("alice", "1234");
        arbolU.insertarUsuario("bob", "5678");
        arbolU.insertarUsuario("charlie", "abcd");
        arbolU.imprimirOrdenado();

        NodoUsuario raiz = arbolU.getRaiz();
        Assert.assertNotNull(raiz);
        Assert.assertEquals("bob", raiz.getNombre());

        NodoUsuario derecha = raiz.getDerecha();
        NodoUsuario izquierda = raiz.getIzquierda();

        Assert.assertNotNull(derecha);
        Assert.assertEquals("charlie", derecha.getNombre());

        Assert.assertNotNull(izquierda);
        Assert.assertEquals("alice", izquierda.getNombre());
    }

    @Test
    public void testBuscarUsuario() {
        arbolU.insertarUsuario("alice", "1234");
        arbolU.insertarUsuario("bob", "5678");

        NodoUsuario usuario = arbolU.buscarUsuario("bob");
        Assert.assertNotNull(usuario);
        Assert.assertEquals("bob", usuario.getNombre());

        usuario = arbolU.buscarUsuario("charlie");
        Assert.assertNull(usuario);
    }
}
