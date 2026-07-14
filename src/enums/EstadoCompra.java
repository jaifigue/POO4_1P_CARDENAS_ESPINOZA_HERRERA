package src.enums;

/**
 * Representa el estado de una compra dentro del sistema. Se usa un enum
 * en lugar de un String o un boolean para que el compilador impida
 * asignar un valor inválido, y para que el código sea más legible que
 * usar una bandera booleana ambigua.
 *
 * @author Jair Cárdenas
 */
public enum EstadoCompra{
    /** La compra fue registrada correctamente y sigue vigente. */
    REGISTRADA,
    /** La compra fue anulada y ya no debe considerarse válida. */
    ANULADA
}