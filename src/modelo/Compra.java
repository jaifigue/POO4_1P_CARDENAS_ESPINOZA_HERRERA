package src;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa una compra realizada por un aficionado, ya sea de una entrada
 * individual o de un kit. Cada compra genera un código único mediante un
 * contador estático compartido entre todas las instancias de la clase,
 * garantizando que el código sea único para todo el sistema y no solo
 * para cada objeto.
 *
 * @author Jair Cárdenas
 */
public class Compra {
    //Atributos básicos
    /** Contador compartido entre todas las instancias, usado para generar códigos únicos de compra. */
    private static int contador;
    private String codigo;
    private TipoCompra tipo;
    private String codigoReferencia;
    private Date fechaCompra;
    private int cantidad;
    private double valorPagado;
    private String codigoAficionado;
    private EstadoCompra estado;


    //Constructores y métodos basicos
    /**
     * Crea una nueva compra generando automáticamente su código único
     * mediante generarCodigo(). Se utiliza cuando la compra se registra
     * por primera vez desde el menú del sistema, ya que en ese momento
     * todavía no existe un código asignado.
     *
     * @param tipo tipo de compra (ENTRADA o KIT)
     * @param codigoReferencia código del partido o kit comprado
     * @param fechaCompra fecha en la que se realizó la compra
     * @param cantidad número de unidades compradas
     * @param valorPagado monto total pagado por la compra
     * @param codigoAficionado código único del aficionado que realizó la compra
     */
    public Compra(TipoCompra tipo, String codigoReferencia, Date fechaCompra,
        int cantidad, double valorPagado, String codigoAficionado){
            this.tipo = tipo;
            this.codigoReferencia = codigoReferencia;
            this.fechaCompra = fechaCompra;
            this.cantidad = cantidad;
            this.valorPagado = valorPagado;
            this.codigoAficionado = codigoAficionado;

            this.codigo = generarCodigo();
            this.estado = EstadoCompra.REGISTRADA;
        }
    //Este es nuevo
    /**
     * Reconstruye una compra que ya existía previamente, a partir de un
     * código ya asignado. Se utiliza al cargar las compras guardadas
     * desde compras.txt al arrancar el programa, en lugar de generar un
     * código nuevo. Además, actualiza el contador estático al valor más
     * alto encontrado entre los códigos cargados, evitando que se generen
     * códigos duplicados en la siguiente compra nueva de la ejecución.
     *
     * @param codigo código ya existente de la compra, tal como se guardó en el archivo
     * @param tipo tipo de compra (ENTRADA o KIT)
     * @param codigoReferencia código del partido o kit comprado
     * @param fechaCompra fecha en la que se realizó la compra
     * @param cantidad número de unidades compradas
     * @param valorPagado monto total pagado por la compra
     * @param codigoAficionado código único del aficionado que realizó la compra
     */
    public Compra(String codigo, TipoCompra tipo, String codigoReferencia, Date fechaCompra,
        int cantidad, double valorPagado, String codigoAficionado){

            this.codigo = codigo;
            this.tipo = tipo;
            this.codigoReferencia = codigoReferencia;
            this.fechaCompra = fechaCompra;
            this.cantidad = cantidad;
            this.valorPagado = valorPagado;
            this.codigoAficionado = codigoAficionado;
            this.estado = EstadoCompra.REGISTRADA;

            int numero = Integer.parseInt(codigo.substring(1));

            if (numero > contador) {
                contador = numero;
            }
        }

    /**
     * Genera un nuevo código único de compra incrementando el contador
     * estático compartido entre todas las instancias. El código sigue el
     * formato "C" seguido de un número de tres dígitos (ej. C001, C011).
     *
     * @return el código de compra generado
     */
    public String generarCodigo(){
        contador++;
        return "C" + String.format("%03d", contador);
    }

    /**
     * Retorna el valor total pagado por la compra.
     *
     * @return el monto total pagado
     */
    public double calcularTotal(){
        return valorPagado;
    }

    //AGG GITHUB
    /**
     * Genera una representación en texto legible de la compra, incluyendo
     * código, tipo, referencia, fecha formateada, cantidad, valor pagado,
     * código del aficionado y estado.
     *
     * @return el texto formateado con los datos de la compra
     */
    @Override
    public String toString(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = formato.format(fechaCompra);
        return "Código de compra: "+codigo+"\nTipo: "+tipo+"\nReferencia: "+codigoReferencia+"\nFecha: "+fechaTexto
        +"\nCantidad: "+cantidad+"\nValor pagado: "+valorPagado+"\nCódigo aficionado: "+codigoAficionado+"\nEstado: "+estado;
    }

    //Getters y setters
    /** @return el valor actual del contador estático de compras */
    public static int getContador() {
        return contador;
    }

    /** @param contador nuevo valor a asignar al contador estático de compras */
    public static void setContador(int contador) {
        Compra.contador = contador;
    }

    /** @return el código único de la compra */
    public String getCodigo() {
        return codigo;
    }

    /** @param codigo nuevo código a asignar a la compra */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /** @return el tipo de compra (ENTRADA o KIT) */
    public TipoCompra getTipo() {
        return tipo;
    }

    /** @param tipo nuevo tipo de compra a asignar */
    public void setTipo(TipoCompra tipo) {
        this.tipo = tipo;
    }

    /** @return el código del partido o kit al que hace referencia la compra */
    public String getCodigoReferencia() {
        return codigoReferencia;
    }

    /** @param codigoReferencia nuevo código de referencia a asignar */
    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }

    /** @return la fecha en que se realizó la compra */
    public Date getFechaCompra() {
        return fechaCompra;
    }

    /** @param fechaCompra nueva fecha de compra a asignar */
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /** @return la cantidad de unidades compradas */
    public int getCantidad() {
        return cantidad;
    }

    /** @param cantidad nueva cantidad de unidades a asignar */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /** @return el valor total pagado por la compra */
    public double getValorPagado() {
        return valorPagado;
    }

    /** @param valorPagado nuevo valor pagado a asignar */
    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    /** @return el código único del aficionado que realizó la compra */
    public String getCodigoAficionado() {
        return codigoAficionado;
    }

    /** @param codigoAficionado nuevo código de aficionado a asignar */
    public void setCodigoAficionado(String codigoAficionado) {
        this.codigoAficionado = codigoAficionado;
    }

    /** @return el estado actual de la compra (REGISTRADA o ANULADA) */
    public EstadoCompra getEstado() {
        return estado;
    }

    /** @param estado nuevo estado a asignar a la compra */
    public void setEstado(EstadoCompra estado) {
        this.estado = estado;
    }

    
}