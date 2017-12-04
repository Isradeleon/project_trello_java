package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Isra
 */
public class Tarea extends Model_T {
    public void updateStatusByProjectID(int proyecto_id, int status){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "update tareas set status = "+String.valueOf(status)
                        +" where id = "+String.valueOf(proyecto_id);
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
    
    public ResultSet findByUserId(int id){
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
                        + "proyectos.titulo as p_titulo, "
                        + "proyectos.id as p_id "
                        + "from tareas inner join proyectos on proyectos.id = tareas.proyecto_id";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
