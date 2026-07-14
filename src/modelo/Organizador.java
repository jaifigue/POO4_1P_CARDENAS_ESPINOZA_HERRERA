package src.modelo;
import src.enums.*;

import java.util.ArrayList;

/**
 * Representa a un organizador del sistema, un tipo de usuario que puede
 * consultar todas las compras registradas y generar el reporte de ventas
 * del evento. Hereda de Usuario los atributos comunes y agrega dos
 * atributos propios: empresa y cargo.
 *
 * @author Jair Cárdenas
 */
public class Organizador extends Usuario{
    private String empresa;
    private String cargo;

    /**
     * Crea un nuevo organizador. Invoca al constructor de Usuario mediante
     * super() para inicializar los atributos comunes, asignando siempre
     * el rol RolUsuario.O, y luego inicializa sus propios atributos
     * exclusivos (empresa y cargo).
     *
     * @param codigoUnico código único que identifica al organizador en el sistema
     * @param cedula número de cédula del organizador
     * @param nombres nombres del organizador
     * @param apellidos apellidos del organizador
     * @param usuario nombre de usuario para iniciar sesión
     * @param contrasena contraseña para iniciar sesión
     * @param correo correo electrónico del organizador
     * @param empresa empresa a la que pertenece el organizador, usada como verificación adicional al iniciar sesión
     * @param cargo cargo que ocupa el organizador dentro de la empresa
     */
    public Organizador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasena,
        String correo, String empresa, String cargo){
            super(codigoUnico, cedula, nombres, apellidos, usuario, contrasena, correo, RolUsuario.O);
            this.empresa = empresa;
            this.cargo = cargo;
        }
    
    /**
     * Muestra todas las compras registradas en el sistema, sin filtrar por
     * aficionado. Es la implementación específica del método abstracto
     * declarado en Usuario, aplicando polimorfismo: a diferencia de
     * Aficionado, aquí se muestran todas las compras, no solo las propias.
     *
     * @param compras lista completa de compras registradas en el sistema
     */
    @Override
    public void consultarEntradas(ArrayList<Compra> compras){
        if(compras ==null || compras.isEmpty()){
            System.out.println("No hay compras registradas");
        }
        else {
            for(Compra c : compras){
                System.out.println(c);
            }
        }
    }

    /**
     * Genera una representación en texto legible del organizador,
     * incluyendo sus atributos comunes y los propios de esta subclase
     * (empresa y cargo). Sobrescribe el toString() genérico de Object
     * para mostrar información útil del usuario.
     *
     * @return el texto formateado con los datos del organizador
     */
    @Override
    public String toString(){
        return "Codigo único: "+codigoUnico+"\nCédula: "+cedula+"\nNombres: "+nombres+"\nApellidos: "+apellidos+"\nEmpresa: "+empresa
        +"\nCargo: "+cargo;
    }


    /** @return la empresa a la que pertenece el organizador */
    public String getEmpresa() {
        return empresa;
    }

    /** @param empresa nueva empresa a asignar */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /** @return el cargo que ocupa el organizador */
    public String getCargo() {
        return cargo;
    }

    /** @param cargo nuevo cargo a asignar */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
}