package src;

/**
 * Representa la zona de ubicación de una entrada dentro de un partido.
 * Cada zona tiene su propio precio y disponibilidad de cupos. Usar un
 * enum en vez de un número o texto evita valores mágicos como zona == 2
 * y hace el código más legible (ej. ZonaEntrada.VIP).
 *
 * @author Jair Cárdenas
 */
public enum ZonaEntrada{
    /** Zona general, la de menor precio. */
    GENERAL,
    /** Zona preferencial, de precio intermedio. */
    PREFERENCIAL,
    /** Zona VIP, la de mayor precio. */
    VIP
}