package models;

public class Reclutador extends Persona {

    private int idReclutador;
    private int idUsuario;
    private String empresa;

    public Reclutador() {
    }
    
    public Reclutador(Reclutador reclutador) {
        super(reclutador.getTipoDocumento(), reclutador.getNroDocumento(), reclutador.getNombre(), reclutador.getApellidos(), reclutador.getSexo(), reclutador.getEdad(), reclutador.getTelefono(), reclutador.getEstado(), reclutador.getFechaCreado(), reclutador.getFechaActualizado(), reclutador.getIdPersona());
        this.empresa = reclutador.getEmpresa();
        this.idUsuario = reclutador.getIdUsuario();
        this.idReclutador = reclutador.getIdReclutador();
        
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getIdReclutador() {
        return idReclutador;
    }

    public void setIdReclutador(int idReclutador) {
        this.idReclutador = idReclutador;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}