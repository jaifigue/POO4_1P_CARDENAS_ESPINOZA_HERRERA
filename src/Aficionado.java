package src;

public class Aficionado extends Usuario {
    private String celular;
    private String paisFavorito;

    public Aficionado() {
    }

    public Aficionado(String codigoUnico, String cedula, String nombres, String apellidos,
                      String usuario, String contrasena, String correo,
                      String celular, String paisFavorito) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contrasena, correo, RolUsuario.A);
        this.celular = celular;
        this.paisFavorito = paisFavorito;
    }

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