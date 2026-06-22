package src;

public class Organizador extends Usuario {
    private String empresa;
    private String cargo;

    public Organizador() {
    }

    public Organizador(String codigoUnico, String cedula, String nombres, String apellidos,
                       String usuario, String contrasena, String correo,
                       String empresa, String cargo) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contrasena, correo, RolUsuario.O);
        this.empresa = empresa;
        this.cargo = cargo;
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