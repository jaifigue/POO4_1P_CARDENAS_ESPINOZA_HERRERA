package src.modelo;

import java.util.ArrayList;

/**
 * Representa un kit: un paquete que agrupa varios partidos vendidos como
 * un solo producto. Guarda una lista de objetos Partido completos (y no
 * solo sus códigos como texto), lo que permite usar directamente los
 * datos reales de cada partido (local, visitante) sin tener que volver a
 * buscarlos cada vez que se necesita mostrar el kit.
 *
 * @author Jair Cárdenas
 */
public class Kit {
    //Atributos
    private String codigo;
    private String nombre;
    private String descripcion;
    private ArrayList<Partido> partidos = new ArrayList<>();
    private double precio;
    private int disponibles;

    //Constructores y métodos basicos
    /**
     * Crea un kit vacío, sin inicializar sus atributos. Se usa cuando el
     * kit se completará posteriormente mediante los setters.
     */
    public Kit(){}

    /**
     * Crea un nuevo kit con todos sus datos, incluyendo la lista de
     * partidos que incluye. Los partidos deben resolverse previamente a
     * partir de sus códigos (ver Sistema.cargarKits()), ya que un archivo
     * de texto plano no puede guardar referencias a objetos Java.
     *
     * @param codigo código único que identifica al kit
     * @param nombre nombre del kit
     * @param descripcion descripción del kit
     * @param partidos lista de partidos incluidos en el kit
     * @param precio precio del kit
     * @param disponibles cantidad de unidades disponibles para la venta
     */
    public Kit(String codigo, String nombre, String descripcion, ArrayList<Partido> partidos, double precio, int disponibles){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.partidos = partidos;
        this.precio = precio;
        this.disponibles = disponibles;
    }

    //Método to String
    /**
     * Genera una representación en texto legible del kit, listando los
     * partidos que incluye (local vs visitante), su precio y su
     * disponibilidad. Usa directamente los objetos Partido guardados en
     * la lista, sin necesidad de volver a buscarlos.
     *
     * @return el texto formateado con los datos del kit
     */
    @Override
    public String toString(){
        String resultado = "";
        resultado = codigo+" - "+nombre+"\nIncluye: "+"\n";
        for(Partido p : partidos){
            resultado = resultado +"- "+p.getLocal()+" vs "+p.getVisitante()+"\n";
        }
        resultado = resultado + "\nPrecio: "+precio+"\nDisponibles: "+disponibles;
        System.out.println("----------------");
        return resultado;
    }




    //Getters y setters
    /** @return el código único del kit */
    public String getCodigo() {
        return codigo;
    }

    /** @param codigo nuevo código a asignar */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /** @return el nombre del kit */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre nuevo nombre a asignar */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return la descripción del kit */
    public String getDescripcion() {
        return descripcion;
    }

    /** @param descripcion nueva descripción a asignar */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /** @return la lista de partidos incluidos en el kit */
    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    /** @param partidos nueva lista de partidos a asignar al kit */
    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    /** @return el precio del kit */
    public double getPrecio() {
        return precio;
    }

    /** @param precio nuevo precio a asignar */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /** @return la cantidad de unidades disponibles del kit */
    public int getDisponibles() {
        return disponibles;
    }

    /** @param disponibles nueva cantidad de unidades disponibles a asignar */
    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    
}