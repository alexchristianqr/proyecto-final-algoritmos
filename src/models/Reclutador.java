package models;

import java.io.Serializable;

public class Reclutador extends Persona implements Serializable {

    private int idReclutador;
    private String empresa;

    public Reclutador() {
    }

    public Reclutador(Reclutador reclutador) {
        super(reclutador.getTipoDocumento(), reclutador.getNroDocumento(), reclutador.getNombre(), reclutador.getApellidos(), reclutador.getSexo(), reclutador.getEdad(), reclutador.getTelefono(), reclutador.getEstado(), reclutador.getFechaCreado(), reclutador.getFechaActualizado(), reclutador.getIdPersona());
        this.empresa = reclutador.getEmpresa();
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

}
