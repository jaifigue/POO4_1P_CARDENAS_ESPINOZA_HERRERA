package src.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase de dominio que representa un partido del Mundial. Agrupa toda la
 * información propia del partido (código, equipos, fecha, estadio,
 * ciudad, capacidad y fase), junto con el stock disponible por cada zona
 * (general, preferencial, vip). El stock de cada zona vive en esta clase
 * porque es un atributo intrínseco de cada partido: cada uno tiene su
 * propia capacidad y su propio remanente, independiente de los demás.
 *
 * @author Jair Cárdenas
 * @version 1.0
 */
public class Partido {
    //Atributos Básicos
    /** Código único que identifica al partido. */
    private String codigo;
    /** Selección local del partido. */
    private String local;
    /** Selección visitante del partido. */
    private String visitante;
    /** Fecha en la que se disputa el partido. */
    private Date fecha;
    /** Estadio donde se disputa el partido. */
    private String estadio;
    /** Ciudad donde se ubica el estadio. */
    private String ciudad;
    /** Capacidad total de espectadores del estadio. */
    private int capacidad;
    /** Cantidad de entradas disponibles en la zona general. */
    private int general;
    /** Cantidad de entradas disponibles en la zona preferencial. */
    private int preferencial;
    /** Cantidad de entradas disponibles en la zona VIP. */
    private int vip;
    /** Fase del torneo a la que corresponde el partido. */
    private String fase;

    //Constructores y métodos básicos

    /**
     * Crea un nuevo partido con todos sus datos y la disponibilidad
     * inicial de cada zona.
     *
     * @param codigo código único que identifica al partido
     * @param local nombre del equipo local
     * @param visitante nombre del equipo visitante
     * @param fecha fecha en la que se disputa el partido
     * @param estadio nombre del estadio donde se juega
     * @param ciudad ciudad sede del partido
     * @param capacidad capacidad total del estadio
     * @param general cupos disponibles en la zona general
     * @param preferencial cupos disponibles en la zona preferencial
     * @param vip cupos disponibles en la zona vip
     * @param fase fase del torneo a la que corresponde el partido
     */
    public Partido(String codigo, String local, String visitante, Date fecha, String estadio,
        String ciudad, int capacidad, int general, int preferencial, int vip, String fase
    ){
        this.codigo = codigo;
        this.local = local;
        this.visitante = visitante;
        this.fecha = fecha;
        this.estadio = estadio;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
        this.general = general;
        this.preferencial = preferencial;
        this.vip = vip;
        this.fase = fase;
    }

    //Método toString agg a GITHUB
   /**
    * Genera una representación en texto legible del partido, incluyendo
    * sus datos generales y la disponibilidad y precio de cada una de las
    * tres zonas de entrada.
    *
    * @return el texto formateado con los datos del partido
    */
   @Override
   public String toString(){
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    String fechaTexto = formato.format(fecha);
    String zonas = 
    String.format("- %-13s | Disponibles: %-5d | Precio: $%.2f%n", "GENERAL", general, 45.00) +
    String.format("- %-13s | Disponibles: %-5d | Precio: $%.2f%n", "PREFERENCIAL", preferencial, 85.00) +
    String.format("- %-13s | Disponibles: %-5d | Precio: $%.2f%n", "VIP", vip, 150.00);

    return "Código: "+codigo+"\nPartido: "+local+" vs "+visitante
    +"\nFecha: "+fechaTexto+"\nEstadio: "+estadio+"\nCiudad: "+ciudad+"\nCapacidad: "+capacidad+"\nFase: "+fase+
    "\nZonas disponibles:\n"+zonas+"\n ------------------------------";
   }


   //Getters y Setters
    /** @return el código único del partido */
    public String getCodigo() {
        return codigo;
    }

    /** @param codigo nuevo código a asignar */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /** @return el nombre del equipo local */
    public String getLocal() {
        return local;
    }

    /** @param local nuevo equipo local a asignar */
    public void setLocal(String local) {
        this.local = local;
    }

    /** @return el nombre del equipo visitante */
    public String getVisitante() {
        return visitante;
    }

    /** @param visitante nuevo equipo visitante a asignar */
    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    /** @return la fecha del partido */
    public Date getFecha() {
        return fecha;
    }

    /** @param fecha nueva fecha a asignar */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /** @return el nombre del estadio donde se juega el partido */
    public String getEstadio() {
        return estadio;
    }

    /** @param estadio nuevo estadio a asignar */
    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    /** @return la ciudad sede del partido */
    public String getCiudad() {
        return ciudad;
    }

    /** @param ciudad nueva ciudad a asignar */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /** @return la capacidad total del estadio */
    public int getCapacidad() {
        return capacidad;
    }

    /** @param capacidad nueva capacidad a asignar */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /** @return los cupos disponibles en la zona general */
    public int getGeneral() {
        return general;
    }

    /** @param general nuevo valor de cupos disponibles en zona general */
    public void setGeneral(int general) {
        this.general = general;
    }

    /** @return los cupos disponibles en la zona preferencial */
    public int getPreferencial() {
        return preferencial;
    }

    /** @param preferencial nuevo valor de cupos disponibles en zona preferencial */
    public void setPreferencial(int preferencial) {
        this.preferencial = preferencial;
    }

    /** @return los cupos disponibles en la zona vip */
    public int getVip() {
        return vip;
    }

    /** @param vip nuevo valor de cupos disponibles en zona vip */
    public void setVip(int vip) {
        this.vip = vip;
    }

    /** @return la fase del torneo a la que corresponde el partido */
    public String getFase() {
        return fase;
    }

    /** @param fase nueva fase a asignar */
    public void setFase(String fase) {
        this.fase = fase;
    }

    
}