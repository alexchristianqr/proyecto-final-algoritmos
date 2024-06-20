package services;

import core.services.MysqlDBService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Reclutador;

public class ReclutadorService extends BaseService {
    
    public ReclutadorService(){
        db = new MysqlDBService();
    }
    
    public void crearReclutador(Reclutador reclutador) {

        this.crearReclutador(reclutador, false);
    }
    
    public void crearReclutador(Reclutador reclutador, boolean useTransaction) {

        if (useTransaction) {
            originalAutoCommit = db.getAutoCommit();
            db.setAutoCommit(false);
        }
        querySQL_1 = "INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, estado, edad, telefono, fecha_creado) VALUES (?,?,?,?,?,?,?,?,NOW())";
        Object[] parametrosSQL_1 = {reclutador.getNombre(), reclutador.getApellidos(), reclutador.getTipoDocumento(), reclutador.getNroDocumento(), reclutador.getSexo(), reclutador.getEstado(), reclutador.getEdad(), reclutador.getTelefono()};
        int id_persona = db.queryInsertar(querySQL_1, parametrosSQL_1);

        querySQL_2 = "INSERT INTO reclutadores (id_persona, id_usuario, estado, fecha_creado) VALUES (?,?,?,NOW())";
        Object[] parametrosSQL_2 = {id_persona, reclutador.getIdUsuario(), reclutador.getEstado()};
        db.queryInsertar(querySQL_2, parametrosSQL_2);

        db.cerrarConsulta();
        
        if (useTransaction) {
            db.commit();
        }
    }
    
    public DefaultTableModel tablaReclutadores(DefaultTableModel modelo, Object[] data) {
        querySQL_1 = "SELECT p.id_persona, p.nombre, p.apellido, p.tipo_documento, p.nrodocumento, p.sexo, p.estado, p.edad, p.telefono, r.id_usuario, r.fecha_creado FROM personas p JOIN reclutadores ON r.id_persona = r.id_persona;";
        Object[] parametrosSQL_1 = {};
        ResultSet res = db.queryConsultar(querySQL_1, parametrosSQL_1);

        try {
            while (res.next()) {
                data[0] = res.getInt("id_persona");
                data[1] = res.getString("nombre");
                data[2] = res.getString("apellido");
                data[3] = res.getInt("tipo_documento");
                data[4] = res.getString("nrodocumento");
                data[5] = res.getString("sexo");
                data[6] = res.getString("estado");
                data[7] = res.getInt("edad");
                data[8] = res.getString("telefono");
                data[9] = res.getInt("id_usuario");
                data[10] = res.getDate("fecha_creado");
                modelo.addRow(data);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al obtener los datos solicitados", ex);
        }

        db.cerrarConsulta();

        return modelo;
    }

    public List<Reclutador> listarReclutadores() {
        List<Reclutador> reclutadors = new ArrayList<>();
        querySQL_1 = "SELECT p.id_persona, p.nombre, p.apellido, p.tipo_documento, p.nrodocumento, p.sexo, p.estado, p.edad, p.telefono, r.id_usuario, r.fecha_creado FROM personas p JOIN reclutadores r ON p.id_persona = r.id_persona;";
        Object[] parametrosSQL_1 = {};
        ResultSet res = db.queryConsultar(querySQL_1, parametrosSQL_1);

        try {
            while (res.next()) {
                Reclutador reclutador = new Reclutador();
                reclutador.setIdPersona(res.getInt("id_persona"));
                reclutador.setNombre(res.getString("nombre"));
                reclutador.setApellidos(res.getString("apellido"));
                reclutador.setTipoDocumento(res.getInt("tipo_documento"));
                reclutador.setNroDocumento(res.getString("nrodocumento"));
                reclutador.setSexo(res.getString("sexo"));
                reclutador.setEstado(res.getString("estado"));
                reclutador.setEdad(res.getString("edad"));
                reclutador.setTelefono(res.getString("telefono"));
                reclutador.setIdUsuario(res.getInt("id_usuario"));
                reclutador.setFechaCreado(res.getString("fecha_creado"));
                reclutadors.add(reclutador);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar los datos", ex);
        }

        db.cerrarConsulta();

        return reclutadors;
    }
    
    //Metodo eliminar reclutador
    
    public void eliminarReclutador(int idPersona){      
        try {
            querySQL_1 = "DELETE FROM reclutadores WHERE id_persona = ?";
            Object[] parametrosSQL_1 = {idPersona};
            db.queryEliminar(querySQL_1, parametrosSQL_1);
            
            querySQL_2 = "DELETE FROM personas WHERE id = ?";
            Object[] parametrosSQL_2 = {idPersona};
            db.queryEliminar(querySQL_2, parametrosSQL_2);
            
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar el reclutador y la persona", ex);
        }finally{
            db.cerrarConsulta();
        }
    }


    /*public void crearReclutador(Reclutador reclutador) {
        querySQL_1 = "INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, estado, edad, telefono, fecha_creado) VALUES (?,?,?,?,?,?,?,?,NOW())";
        Object[] parametrosSQL_1 = {reclutador.getNombre(), reclutador.getApellidos(),
            reclutador.getTipoDocumento(), reclutador.getNroDocumento(), reclutador.getSexo(), reclutador.getEstado(),
            reclutador.getEdad(), reclutador.getTelefono()};
        int id_persona = db.queryInsertar(querySQL_1, parametrosSQL_1);

        querySQL_2 = "INSERT INTO reclutadores (id_persona, id_usuario, estado, fecha_creado) VALUES (?,?,?,NOW())";
        Object[] parametrosSQL_2 = {id_persona, reclutador.getIdUsuario(), reclutador.getEstado()};
        db.queryInsertar(querySQL_2, parametrosSQL_2);

        db.cerrarConsulta();
    }

    public void actualizarReclutador(Reclutador reclutador) {
        querySQL_1 = "UPDATE INTO personas SET nombre = ?, apellido = ?, tipo_documento = ?, nrodocumento = ?, sexo = ?, estado = ?, edad = ?, telefono = ? WHERE id = ?";
        Object[] parametrosSQL_1 = {reclutador.getNombre(), reclutador.getApellidos(),
            reclutador.getTipoDocumento(), reclutador.getNroDocumento(), reclutador.getSexo(), reclutador.getEstado(),
            reclutador.getEdad(), reclutador.getTelefono(), reclutador.getIdPersona()};
        db.queryActualizar(querySQL_1, parametrosSQL_1);

        querySQL_2 = "UPDATE INTO reclutadores SET estado = ? WHERE id_persona = ?";
        Object[] parametrosSQL_2 = {reclutador.getEstado(), reclutador.getIdPersona()};

        db.queryActualizar(querySQL_2, parametrosSQL_2);
        db.cerrarConsulta();
    }

    public void eliminarReclutador(int idPersona, int idUsuario) {
        querySQL_1 = "DELETE FROM reclutadores WHERE id_persona = ?";
        Object[] parametrosSQL_1 = {idPersona};

        db.queryEliminar(querySQL_1, parametrosSQL_1);

        //para eliminar de la tabla personas
        querySQL_2 ="DELETE FROM reclutadores WHERE id = ?";
        Object[] parametrosSQL_2 = {idPersona};
        
        db.queryELiminar(querySQL_2, parametrosSQL_2);
        
        //para eliminar de la tabla usuarios
        String querySQL_3 = "DELETE FROM usuarios WHERE id = ?";
        Object[] parametrosSQL_3 = {idUsuario};

        db.queryEliminar(querySQL_3, parametrosSQL_3);

        db.cerrarConsulta();
    }*/
}
