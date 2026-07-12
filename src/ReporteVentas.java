package src;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReporteVentas {
    //Atributos básicos
    private int totalCompras;
    private int totalEntradas;
    private int totalKits;
    private double montoTotal;
    private Date fechaReporte;

    //Métodos y constructores
    public ReporteVentas(){}

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
    @Override
    public String toString(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaTexto = formato.format(fechaReporte);
        return " ==== GENERAR REPORTE DE VENTAS ===="+"\nResumen de ventas registradas:"+"\nTotal de compras: "+totalCompras+
        "\nCompras por tipo:"+"\nEntradas: "+totalEntradas+"\nKits: "+totalKits+"\nMonto total recaudado: "+"\n$"+montoTotal+
        "\nFecha reporte: "+fechaTexto;
    }

    public int getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(int totalCompras) {
        this.totalCompras = totalCompras;
    }

    public int getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(int totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public int getTotalKits() {
        return totalKits;
    }

    public void setTotalKits(int totalKits) {
        this.totalKits = totalKits;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    


}

