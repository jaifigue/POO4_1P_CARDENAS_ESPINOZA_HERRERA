package src;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Compra {
    //Atributos básicos
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

    public String generarCodigo(){
        contador++;
        return "C" + String.format("%03d", contador);
    }

    public double calcularTotal(){
        return valorPagado;
    }

    //AGG GITHUB
    @Override
    public String toString(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = formato.format(fechaCompra);
        return "Código de compra: "+codigo+"\nTipo: "+tipo+"\nReferencia: "+codigoReferencia+"\nFecha: "+fechaTexto
        +"\nCantidad: "+cantidad+"\nValor pagado: "+valorPagado+"\nCódigo aficionado: "+codigoAficionado+"\nEstado: "+estado;
    }

    //Getters y setters
    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Compra.contador = contador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoCompra getTipo() {
        return tipo;
    }

    public void setTipo(TipoCompra tipo) {
        this.tipo = tipo;
    }

    public String getCodigoReferencia() {
        return codigoReferencia;
    }

    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public String getCodigoAficionado() {
        return codigoAficionado;
    }

    public void setCodigoAficionado(String codigoAficionado) {
        this.codigoAficionado = codigoAficionado;
    }

    public EstadoCompra getEstado() {
        return estado;
    }

    public void setEstado(EstadoCompra estado) {
        this.estado = estado;
    }

    
}

