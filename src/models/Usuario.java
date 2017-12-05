package models;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Isra
 */
public class Usuario extends Model_T{
    public void update(int id_usuario, String nombre, String apellidos, int tipo, String email, String password){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "update usuarios set "+
                        (!"".equals(nombre) && null != nombre ? " nombre = '"+String.valueOf(nombre)+"', " : " ")
                        +
                        (!"".equals(apellidos) && null != apellidos ? " apellidos = '"+String.valueOf(apellidos)+"', " : " ")
                        +
                        (!"".equals(email) && null != email ? " email = '"+String.valueOf(email)+"', " : " ")
                        +
                        (!"".equals(password) && null != password ? " password = '"+this.md5(password)+"', " : " ")
                        + " tipo = "+String.valueOf(tipo)
                        + " where id = "+String.valueOf(id_usuario);
                stm.execute(query);
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public ResultSet validUser(String email, String password){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select * from usuarios where email = '"+email+"' and password = '"+this.md5(password)+"' limit 1";
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
    
    public void removeTask(int usuario_id, int tarea_id){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "delete from asignaciones where usuario_id = "+String.valueOf(usuario_id)+" and tarea_id = "+String.valueOf(tarea_id);
                stm.execute(query);
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void addTask(int usuario_id, int tarea_id){
        if (this.connectDB() != null) {
            try{
                if (!this.taskExists(usuario_id, tarea_id)) {
                    stm = (Statement)conn.createStatement();
                    query = "insert into asignaciones "
                        + "(usuario_id,tarea_id,asignada) "
                        + "values ("+String.valueOf(usuario_id)+", "+String.valueOf(tarea_id)+", now())";
                    stm.execute(query);
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public boolean taskExists(int usuario_id, int tarea_id){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select * from asignaciones where usuario_id = "+String.valueOf(usuario_id)+" and tarea_id = "+String.valueOf(tarea_id)+" limit 1";
                ResultSet results = stm.executeQuery(query);
                if (results.first()) {
                    return true;
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    
    public boolean emailExists(String email){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select * from usuarios where email = '"+email+"' limit 1";
                ResultSet results = stm.executeQuery(query);
                if (results.first()) {
                    return true;
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    
    public void insert(String nombre, String apellidos, int tipo, String email, String password){
        if (this.connectDB() != null) {
            try{
                String md5_pass = this.md5(password);
                if (md5_pass != null && !this.emailExists(email)) {
                    stm = (Statement)conn.createStatement();
                    query = "insert into usuarios "
                        + "(nombre,apellidos,tipo,email,password) "
                        + "values ('"+nombre+"','"+apellidos+"',"+String.valueOf(tipo)
                        +",'"+email+"','"+md5_pass+"')";
                    stm.execute(query);
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public String md5(String input_string){
        try{
            // ENCRYPT PASSWORD WITH MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input_string.getBytes("UTF-8"), 0, input_string.length()); 
            byte[] bt = md.digest();
            BigInteger bi = new BigInteger(1, bt); 
            return bi.toString(16);
        }catch(UnsupportedEncodingException | NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public ResultSet findById(int id){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select * from usuarios where id = "+String.valueOf(id)+" limit 1";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    
    public ResultSet getCount(){
        if (this.connectDB() != null) {
            try{
                stm = (Statement)conn.createStatement();
                query = "select count(id) as cuenta from usuarios";
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
                query = "select * from usuarios";
                ResultSet results = stm.executeQuery(query);
                return results;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
