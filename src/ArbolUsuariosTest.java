import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ArbolUsuariosTest {

    private ArbolUsuarios arbol;

    @Before
    public void setUp() {
        arbol = new ArbolUsuarios();
    }

    @Test
    public void testInsertarUsuario() {
        arbol.insertarUsuario("alice", "1234");
        arbol.insertarUsuario("bob", "5678");
        arbol.insertarUsuario("charlie", "abcd");
        arbol.imprimirOrdenado();

        NodoUsuario raiz = arbol.getRaiz();
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
        arbol.insertarUsuario("alice", "1234");
        arbol.insertarUsuario("bob", "5678");

        NodoUsuario usuario = arbol.buscarUsuario("bob");
        Assert.assertNotNull(usuario);
        Assert.assertEquals("bob", usuario.getNombre());

        usuario = arbol.buscarUsuario("charlie");
        Assert.assertNull(usuario);
    }
}
