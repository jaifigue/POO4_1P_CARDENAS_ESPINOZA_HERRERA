package src;

/**
 * Punto de entrada del programa. Deliberadamente tiene muy poco código:
 * su única responsabilidad es arrancar la aplicación, no gobernarla.
 * Toda la lógica de negocio (autenticación, menús, reglas de compra,
 * persistencia) vive en la clase Sistema, lo que mantiene el punto de
 * entrada desacoplado de las reglas del sistema y facilita mantener,
 * probar y reutilizar esa lógica de forma independiente.
 *
 * @author Jair Cárdenas
 */
public class Main {
    /**
     * Crea el objeto Sistema, le pide que cargue los datos desde los
     * archivos de texto y luego inicia el flujo de sesión, que se
     * encarga de mostrar el menú correspondiente según el rol del
     * usuario autenticado.
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.cargarDatos();
        sistema.iniciarSesion();
    }
}