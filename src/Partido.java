package src;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Partido {
    //Atributos Básicos
    private String codigo;
    private String local;
    private String visitante;
    private Date fecha;
    private String estadio;
    private String ciudad;
    private int capacidad;
    private int general;
    private int preferencial;
    private int vip;
    private String fase;

    //Constructores y métodos básicos

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
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getGeneral() {
        return general;
    }

    public void setGeneral(int general) {
        this.general = general;
    }

    public int getPreferencial() {
        return preferencial;
    }

    public void setPreferencial(int preferencial) {
        this.preferencial = preferencial;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    
}

