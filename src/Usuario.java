package src;

import java.util.ArrayList;

/**
 * Clase abstracta que representa a un usuario del sistema. Nunca existe
 * un objeto Usuario genérico: todo usuario real es, o un Aficionado, o
 * un Organizador. Declararla abstract impide al compilador crear una
 * instancia directa de Usuario, obligando a que solo se instancien sus
 * subclases concretas.
 * 
 * Contiene los siete atributos comunes a cualquier tipo de usuario
 * (código, cédula, nombres, apellidos, credenciales de acceso, correo y
 * rol), evitando así duplicarlos en cada subclase.
 *
 * @author Jair Cárdenas
 */
public abstract class Usuario {
    //Atributos básicos
    protected String codigoUnico;
    protected String cedula;
    protected String nombres;
    protected String apellidos;
    protected String usuario;
    protected String contrasena;
    protected String correo;
    protected RolUsuario rol;

    //Constructores y metodos
    /**
     * Crea un nuevo usuario con los atributos comunes a toda subclase.
     * Este constructor es invocado desde las subclases (Aficionado y
     * Organizador) mediante super(), siempre como primera instrucción de
     * sus propios constructores.
     *
     * @param codigoUnico código único que identifica al usuario en el sistema
     * @param cedula número de cédula del usuario
     * @param nombres nombres del usuario
     * @param apellidos apellidos del usuario
     * @param usuario nombre de usuario para iniciar sesión
     * @param contrasena contraseña para iniciar sesión
     * @param correo correo electrónico del usuario
     * @param rol rol del usuario en el sistema (A para Aficionado, O para Organizador)
     */
    public Usuario(String codigoUnico, String cedula, String nombres, String apellidos, String usuario,
        String contrasena, String correo, RolUsuario rol){
            this.codigoUnico = codigoUnico;
            this.cedula = cedula;
            this.nombres = nombres;
            this.apellidos = apellidos;
            this.usuario = usuario;
            this.contrasena = contrasena;
            this.correo = correo;
            this.rol = rol; 
        }

    /**
     * Consulta las entradas y compras asociadas al usuario. Se declara
     * abstract porque el comportamiento es distinto según el rol: un
     * Aficionado debe ver únicamente sus propias compras, mientras que un
     * Organizador debe ver todas las compras del sistema. Cuando Sistema
     * invoca este método sobre un objeto Usuario, Java decide en tiempo
     * de ejecución cuál implementación ejecutar según el tipo real del
     * objeto (polimorfismo).
     *
     * @param compras lista completa de compras registradas en el sistema
     */
    public abstract void consultarEntradas(ArrayList<Compra> compras);


    //Getters y setters
    /** @return el código único del usuario */
    public String getCodigoUnico() {
        return codigoUnico;
    }
    /** @param codigoUnico nuevo código único a asignar */
    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }
    /** @return la cédula del usuario */
    public String getCedula() {
        return cedula;
    }
    /** @param cedula nueva cédula a asignar */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    /** @return los nombres del usuario */
    public String getNombres() {
        return nombres;
    }
    /** @param nombres nuevos nombres a asignar */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    /** @return los apellidos del usuario */
    public String getApellidos() {
        return apellidos;
    }
    /** @param apellidos nuevos apellidos a asignar */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    /** @return el nombre de usuario usado para iniciar sesión */
    public String getUsuario() {
        return usuario;
    }
    /** @param usuario nuevo nombre de usuario a asignar */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    /** @return la contraseña del usuario */
    public String getContrasena() {
        return contrasena;
    }
    /** @param contrasena nueva contraseña a asignar */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    /** @return el correo electrónico del usuario */
    public String getCorreo() {
        return correo;
    }
    /** @param correo nuevo correo electrónico a asignar */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /** @return el rol del usuario (A para Aficionado, O para Organizador) */
    public RolUsuario getRol() {
        return rol;
    }
    /** @param rol nuevo rol a asignar */
    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    
}