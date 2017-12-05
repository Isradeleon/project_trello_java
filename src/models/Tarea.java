package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Isra
 */
public class Tarea extends Model_T {
    public void update(int id_tarea, String titulo, String descripcion, int status){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "update tareas set "+
                        (!"".equals(titulo) && null != titulo ? " titulo = '"+String.valueOf(titulo)+"', " : " ")
                        +
                        (!"".equals(descripcion) && null != descripcion ? " descripcion = '"+String.valueOf(descripcion)+"', " : " ")
                        +" status = "+String.valueOf(status)
                        +" where id = "+String.valueOf(id_tarea);
                stm.execute(query);
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void updateStatusByProjectID(int proyecto_id, int status){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "update tareas set status = "+String.valueOf(status)
                        +" where proyecto_id = "+String.valueOf(proyecto_id)
                        +" and status = 1";
                stm.execute(query);
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void updateStatus(int id_tarea, int status){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "update tareas set status = "+String.valueOf(status)
                        +" where id = "+String.valueOf(id_tarea);
                stm.execute(query);
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public ResultSet findByUserId(int usuario_id){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select tareas.*, "
                        + "proyectos.titulo as p_titulo, "
                        + "proyectos.id as p_id "
                        + "from tareas inner join proyectos on proyectos.id = tareas.proyecto_id "
                        + "inner join asignaciones on tareas.id = asignaciones.tarea_id "
                        + "inner join usuarios on usuarios.id = asignaciones.usuario_id "
                        + "where asignaciones.usuario_id = " + String.valueOf(usuario_id);
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public ResultSet findByProjectId(int proyecto_id){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select tareas.*, "
                        + "usuarios.nombre as u_nombre, "
                        + "usuarios.apellidos as u_apellidos, "
                        + "usuarios.email as u_email "
                        + "from tareas left join asignaciones on tareas.id = asignaciones.tarea_id "
                        + "left join usuarios on usuarios.id = asignaciones.usuario_id "
                        + "where tareas.proyecto_id = " + String.valueOf(proyecto_id);
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public void insert(String titulo, String descripcion, int status, int proyecto_id){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "insert into tareas "
                        + "(titulo,descripcion,status,creada,proyecto_id) "
                        + "values ('"+titulo+"','"+descripcion+"',"+String.valueOf(status)
                        + ",now(),"+String.valueOf(proyecto_id)+")";
                stm.execute(query);
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public ResultSet findById(int id){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select tareas.*, "
                        + "proyectos.titulo as p_titulo, "
                        + "proyectos.id as p_id "
                        + "from tareas inner join proyectos on proyectos.id = tareas.proyecto_id "
                        + "where tareas.id = " + String.valueOf(id) + " limit 1";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public ResultSet getAll(){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select tareas.*, "
                        + "usuarios.nombre as u_nombre, "
                        + "usuarios.apellidos as u_apellidos, "
                        + "usuarios.email as u_email, "
                        + "proyectos.titulo as p_titulo, "
                        + "proyectos.id as p_id "
                        + "from tareas inner join proyectos on proyectos.id = tareas.proyecto_id "
                        + "left join asignaciones on tareas.id = asignaciones.tarea_id "
                        + "left join usuarios on usuarios.id = asignaciones.usuario_id ";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public ResultSet getAllPendingNotUser(){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select tareas.*, "
                        + "usuarios.nombre as u_nombre, "
                        + "usuarios.apellidos as u_apellidos, "
                        + "usuarios.email as u_email, "
                        + "proyectos.titulo as p_titulo, "
                        + "proyectos.id as p_id "
                        + "from tareas inner join proyectos on proyectos.id = tareas.proyecto_id "
                        + "left join asignaciones on tareas.id = asignaciones.tarea_id "
                        + "left join usuarios on usuarios.id = asignaciones.usuario_id "
                        + "where tareas.status = 1 and asignaciones.usuario_id is null";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
