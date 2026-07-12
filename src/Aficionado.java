package src;

import java.util.ArrayList;

public class Aficionado extends Usuario{
    //Atributos básicos
    private String celular;
    private String paisFavorito;

    //Métodos y constructores
    public Aficionado(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasena,
        String correo, String celular, String paisFavorito){
            super(codigoUnico, cedula, nombres, apellidos, usuario, contrasena, correo, RolUsuario.A);
            this.celular = celular;
            this.paisFavorito = paisFavorito;
        }
        
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

    @Override
    public String toString(){
        return "Codigo único: "+codigoUnico+"\nCédula: "+cedula+"\nNombres: "+nombres+"\nApellidos: "+apellidos+"\nCelular: "+celular
        +"\nPaís favorito: "+paisFavorito;
    }

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
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPaisFavorito() {
        return paisFavorito;
    }

    public void setPaisFavorito(String paisFavorito) {
        this.paisFavorito = paisFavorito;
    }

    

}
