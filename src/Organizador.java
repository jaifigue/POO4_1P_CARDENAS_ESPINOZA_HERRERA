package src;
import java.util.ArrayList;

public class Organizador extends Usuario{
    private String empresa;
    private String cargo;

    public Organizador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasena,
        String correo, String empresa, String cargo){
            super(codigoUnico, cedula, nombres, apellidos, usuario, contrasena, correo, RolUsuario.O);
            this.empresa = empresa;
            this.cargo = cargo;
        }
    
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

    @Override
    public String toString(){
        return "Codigo único: "+codigoUnico+"\nCédula: "+cedula+"\nNombres: "+nombres+"\nApellidos: "+apellidos+"\nEmpresa: "+empresa
        +"\nCargo: "+cargo;
    }


    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
}
