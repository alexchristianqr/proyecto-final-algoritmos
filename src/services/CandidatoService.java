package services;

import models.Candidato;

public class CandidatoService extends BaseService {

    public void crearCandidato(Candidato candidato) {
        querySQL_1 = "INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, estado, edad, telefono, fecha_creado) VALUES (?,?,?,?,?,?,?,?,NOW())";
        Object[] parametrosSQL_1 = {candidato.getNombre(), candidato.getApellidos(), candidato.getTipoDocumento(), candidato.getNroDocumento(), candidato.getSexo(), candidato.getEstado(), candidato.getEdad(), candidato.getTelefono()};
        int id_persona = db.queryInsertar(querySQL_1, parametrosSQL_1);

        querySQL_2 = "INSERT INTO candidatos (id_persona, id_usuario, aptitudes, imagen_perfil, path_curriculum_vitae, path_certificado_trabajo, path_antecendente_policial, estado, fecha_creado) VALUES (?,?,?,?,?,?,?,?,NOW())";
        Object[] parametrosSQL_2 = {id_persona, candidato.getIdUsuario(), candidato.getAptitudes(), candidato.getImagenPerfil(), candidato.getPathCV(), candidato.getPathCertificadoTrabajo(), candidato.getPathAntecedentePolicial(), candidato.getEstado()};
        db.queryInsertar(querySQL_2, parametrosSQL_2);

        db.cerrarConsulta();
    }
}
