package src.enums;

/**
 * Representa el rol de un usuario dentro del sistema. Se guarda como una
 * sola letra (A o O) en usuarios.txt para simplificar el parseo con
 * split("\\|"), y ese mismo valor se traduce directamente a este enum
 * al cargar los usuarios.
 *
 * @author Jair Cárdenas
 * @version 1.0
 */
public enum RolUsuario{
    /** Rol de Aficionado. */
    A,
    /** Rol de Organizador. */
    O
}