package src.modelo;
import src.enums.*;


import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Clase de dominio que representa un reporte de ventas del sistema.
 * Recorre todas las compras registradas y calcula estadísticas
 * agregadas: número total de compras, entradas vendidas, kits vendidos y
 * dinero recaudado. Existe como clase separada, en vez de ser un simple
 * método de Sistema que solo imprime, porque representa un concepto
 * propio del dominio, con sus propios atributos y su propio toString(),
 * reutilizable si en el futuro se quisiera guardar reportes históricos o
 * generarlos en otro formato.
 *
 * @author Jair Cárdenas
 */
public class ReporteVentas {
    //Atributos básicos
    private int totalCompras;
    private int totalEntradas;
    private int totalKits;
    private double montoTotal;
    private Date fechaReporte;

    //Métodos y constructores
    /**
     * Crea un reporte vacío. Sus atributos se calculan posteriormente
     * mediante generarResumen().
     */
    public ReporteVentas(){}

    /**
     * Calcula las estadísticas del reporte recorriendo la lista completa
     * de compras del sistema: total de compras, cuántas corresponden a
     * entradas, cuántas a kits, y el monto total recaudado. También
     * registra la fecha en que se generó el reporte. Si la lista de
     * compras es nula o está vacía, el reporte queda con todos sus
     * valores en cero.
     *
     * @param compras lista completa de compras registradas en el sistema
     */
    public void generarResumen(ArrayList<Compra> compras){
        totalCompras = 0;
        totalEntradas = 0;
        totalKits = 0;
        montoTotal = 0;
        fechaReporte = new Date();

        if (compras==null || compras.isEmpty()){
            return;
        }
        for (Compra c : compras){
            totalCompras++;
            montoTotal+= c.getValorPagado();
            
            if (c.getTipo() == TipoCompra.ENTRADA){
                totalEntradas++;
            }
            else if (c.getTipo() == TipoCompra.KIT){
                totalKits++;
            }
        }
    }
    //toString
    /**
     * Genera una representación en texto legible del reporte de ventas,
     * mostrando el total de compras, el desglose por tipo (entradas y
     * kits), el monto total recaudado y la fecha en que se generó.
     *
     * @return el texto formateado con el resumen del reporte
     */
    @Override
    public String toString(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = formato.format(fechaReporte);
        return " ==== GENERAR REPORTE DE VENTAS ===="+"\nResumen de ventas registradas:"+"\nTotal de compras: "+totalCompras+
        "\nCompras por tipo:"+"\nEntradas: "+totalEntradas+"\nKits: "+totalKits+"\nMonto total recaudado: "+"\n$"+montoTotal+
        "\nFecha reporte: "+fechaTexto;
    }

    /** @return el número total de compras registradas en el reporte */
    public int getTotalCompras() {
        return totalCompras;
    }

    /** @param totalCompras nuevo total de compras a asignar */
    public void setTotalCompras(int totalCompras) {
        this.totalCompras = totalCompras;
    }

    /** @return el número total de compras de tipo entrada */
    public int getTotalEntradas() {
        return totalEntradas;
    }

    /** @param totalEntradas nuevo total de entradas a asignar */
    public void setTotalEntradas(int totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    /** @return el número total de compras de tipo kit */
    public int getTotalKits() {
        return totalKits;
    }

    /** @param totalKits nuevo total de kits a asignar */
    public void setTotalKits(int totalKits) {
        this.totalKits = totalKits;
    }

    /** @return el monto total recaudado por todas las compras */
    public double getMontoTotal() {
        return montoTotal;
    }

    /** @param montoTotal nuevo monto total a asignar */
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    /** @return la fecha en que se generó el reporte */
    public Date getFechaReporte() {
        return fechaReporte;
    }

    /** @param fechaReporte nueva fecha de reporte a asignar */
    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    


}