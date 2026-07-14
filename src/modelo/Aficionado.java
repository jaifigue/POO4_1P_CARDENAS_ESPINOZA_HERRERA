package src.modelo;
import src.enums.*;

import java.util.ArrayList;

/**
 * Representa a un aficionado del sistema, un tipo de usuario que puede
 * consultar los partidos disponibles, comprar entradas individuales,
 * comprar kits y consultar el historial de sus propias compras.
 * Hereda de Usuario los atributos comunes y agrega dos atributos propios:
 * celular y país favorito.
 *
 * @author Jair Cárdenas
 */
public class Aficionado extends Usuario{
    //Atributos básicos
    private String celular;
    private String paisFavorito;

    //Métodos y constructores
    /**
     * Crea un nuevo aficionado. Invoca al constructor de Usuario mediante
     * super() para inicializar los atributos comunes, asignando siempre
     * el rol RolUsuario.A, y luego inicializa sus propios atributos
     * exclusivos (celular y país favorito).
     *
     * @param codigoUnico código único que identifica al aficionado en el sistema
     * @param cedula número de cédula del aficionado
     * @param nombres nombres del aficionado
     * @param apellidos apellidos del aficionado
     * @param usuario nombre de usuario para iniciar sesión
     * @param contrasena contraseña para iniciar sesión
     * @param correo correo electrónico del aficionado
     * @param celular número de celular registrado, usado como verificación adicional al iniciar sesión
     * @param paisFavorito país favorito del aficionado
     */
    public Aficionado(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasena,
        String correo, String celular, String paisFavorito){
            super(codigoUnico, cedula, nombres, apellidos, usuario, contrasena, correo, RolUsuario.A);
            this.celular = celular;
            this.paisFavorito = paisFavorito;
        }
        
    /**
     * Muestra únicamente las compras que pertenecen a este aficionado,
     * filtrando la lista completa de compras del sistema por su código
     * único. Es la implementación específica del método abstracto
     * declarado en Usuario, aplicando polimorfismo: a diferencia de
     * Organizador, aquí solo se muestran las compras propias.
     *
     * @param compras lista completa de compras registradas en el sistema
     */
    @Override
    public void consultarEntradas(ArrayList<Compra> compras){
        boolean encontro = false;
        for (Compra c : compras){
            if(c.getCodigoAficionado().equalsIgnoreCase(this.codigoUnico)){
                System.out.println(c);
                encontro = true;
            }
        }
        if (!encontro){
            System.out.println("No tiene compras registradas");}
    }

    /**
     * Genera una representación en texto legible del aficionado,
     * incluyendo sus atributos comunes y los propios de esta subclase
     * (celular y país favorito). Sobrescribe el toString() genérico de
     * Object para mostrar información útil del usuario.
     *
     * @return el texto formateado con los datos del aficionado
     */
    @Override
    public String toString(){
        return "Codigo único: "+codigoUnico+"\nCédula: "+cedula+"\nNombres: "+nombres+"\nApellidos: "+apellidos+"\nCelular: "+celular
        +"\nPaís favorito: "+paisFavorito;
    }

    /**
     * Muestra por consola todos los partidos disponibles en el sistema,
     * o un mensaje indicando que no hay partidos si la lista está vacía
     * o es nula.
     *
     * @param partidos lista de partidos cargados en el sistema
     */
    public void consultarPartidos(ArrayList<Partido> partidos){
            if((partidos==null) || (partidos.isEmpty())){
                System.out.println("No hay partidos disponibles.");
            }
            else {
                for (Partido p : partidos){
                    System.out.println(p);
                } 
            }
        }
    

    //Getters y setters
    /** @return el número de celular del aficionado */
    public String getCelular() {
        return celular;
    }

    /** @param celular nuevo número de celular a asignar */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /** @return el país favorito del aficionado */
    public String getPaisFavorito() {
        return paisFavorito;
    }

    /** @param paisFavorito nuevo país favorito a asignar */
    public void setPaisFavorito(String paisFavorito) {
        this.paisFavorito = paisFavorito;
    }

    

}