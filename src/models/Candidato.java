package models;

public class Candidato extends Persona {

    private int idCandidato;
    private int idPerfil;

    public Candidato() {
    }

    public Candidato(Candidato candidato) {
        super(candidato.getNroDocumento(), candidato.getNombre(), candidato.getApellidos(), candidato.getSexo(), candidato.getEdad(), candidato.getTelefono(), candidato.getEstado(), candidato.getFechaCreado(), candidato.getFechaActualizado(), candidato.getIdPersona());
        this.idPerfil = candidato.getIdPerfil();
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

}
