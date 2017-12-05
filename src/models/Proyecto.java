package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Connection;

/**
 *
 * @author Isra
 */
public class Proyecto extends Model_T {
    public ResultSet getCount(){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select count(id) as cuenta from proyectos";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public ResultSet getAllPie(){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select status, count(id) as cuenta from proyectos group by status";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public void update(int id_proyecto, String titulo, String descripcion, int status){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "update proyectos set "+
                        (!"".equals(titulo) && null != titulo ? " titulo = '"+String.valueOf(titulo)+"', " : " ")
                        +
                        (!"".equals(descripcion) && null != descripcion ? " descripcion = '"+String.valueOf(descripcion)+"', " : " ")
                        +" status = "+String.valueOf(status)
                        +" where id = "+String.valueOf(id_proyecto);
                stm.execute(query);
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void insert(String titulo, String descripcion, int status){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "insert into proyectos "
                        +"(titulo,descripcion,status,creado) "
                        + "values ('"+titulo+"','"+descripcion+"',"+String.valueOf(status)+",now())";
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
                query = "select * from proyectos where id = "+String.valueOf(id)+" limit 1";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public ResultSet findByTitle(String title){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select * from proyectos where titulo = '"+title+"' limit 1";
                ResultSet results = stm.executeQuery(query);
                if (results.first()) {
                    return results;
                }
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
                query = "select proyectos.*, count(tareas.id) as count_tareas "
                        + "from proyectos left join tareas on tareas.proyecto_id = proyectos.id "
                        + "group by proyectos.titulo order by proyectos.id";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public ResultSet getAllPending(){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select * from proyectos "
                        + "where status = 1 "
                        + "order by id";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
