package src.enums;

/**
 * Representa el tipo de una compra dentro del sistema: entrada
 * individual para un partido, o kit que agrupa varios partidos. Se usa
 * en lugar de un String para evitar errores de tipeo y permitir
 * comparaciones seguras con ==.
 *
 * @author Jair Cárdenas
 * @version 1.0
 */
public enum TipoCompra{
    /** Compra de una entrada individual para un partido específico. */
    ENTRADA,
    /** Compra de un kit que incluye varios partidos. */
    KIT,
}