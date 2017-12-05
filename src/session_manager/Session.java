/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_manager;

/**
 *
 * @author Isra
 */
public class Session {
   private Session() {}
   
   private static Session instance = null;
   private static int tipo = 0;
   private static int id = 0;
   private static String email = "";
   private static String nombre = "";
   private static String apellidos = "";
   
   public static int getTipo() { return tipo; }
   public static int getId() { return id; }
   public static String getEmail() { return email; }
   public static String getNombre() { return nombre; }
   public static String getApellidos() { return apellidos; }
   
   public static boolean getCurrentStatus(){
       return (instance!=null);
   }
   
   public static void login(int tipo_usuario, int usuario_id, String usuario_email, String usuario_nombre, String usuario_apellidos) {
      if(instance == null) {
         instance = new Session();
         tipo = tipo_usuario;
         id = usuario_id;
         email = usuario_email;
         nombre = usuario_nombre;
         apellidos = usuario_apellidos;
      }
   }
   
   public static void logout() {
      if(instance != null){
          instance = null;
          tipo = 0;
          id = 0;
          email = "";
          nombre = "";
          apellidos = "";
      }
   }
}
