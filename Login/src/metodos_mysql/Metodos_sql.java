/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodos_mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class Metodos_sql {
    
    public static ConexionBD conexion = new ConexionBD();
    
    public static PreparedStatement sentencia_preparada;
    public static ResultSet resultado;
    public static String sql;
    public static int resultado_numero = 0;
    
    public int confirmar(String usuario, String nombre, String apellido, String numero, String correo, String contraseña){
        
        int resultado = 0;
        Connection conexion = null;
        
        String sentencia_confirmar = ("INSERT INTO usuarios (usuario, nombre, apellido, numero, correo, contraseña) VALUES(?, ?, ?, ?, ?, ?)");
        
        try {
            conexion = ConexionBD.conectar();
            sentencia_preparada = conexion.prepareStatement(sentencia_confirmar);
            sentencia_preparada.setString (1, usuario);
            sentencia_preparada.setString (2, nombre);
            sentencia_preparada.setString (3, apellido);
            sentencia_preparada.setString (4, numero);
            sentencia_preparada.setString (5, correo);
            sentencia_preparada.setString (6, contraseña);
            
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
            
            conexion.close();
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
        
        return resultado;
    }
    
    public static String buscarNombre(String correo) {
        
        String buscar_nombre = null;
        Connection conexion = null;
        
          try {
            conexion = ConexionBD.conectar();
            String sent_buscar = ("SELECT nombre, apellido FROM usuarios WHERE correo = '" + correo + "'");
            sentencia_preparada = conexion.prepareStatement(sent_buscar);
            resultado = sentencia_preparada.executeQuery();
            if(resultado.next()){
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                buscar_nombre = (nombre +" "+ apellido);
            }
            conexion.close();
                    } 
          catch (Exception e) {
         
            System.out.println(e);
        }
          return buscar_nombre;   

        
    }
    
    public static String buscarUserR(String correo, String contraseña){
        
        String buscar_userR = null;
        Connection conexion = null;
        
          try {
            conexion = ConexionBD.conectar();
            String sent_buscar_user = ("SELECT usuario, nombre, correo, contraseña, numero FROM usuarios WHERE correo = '"+correo+"' && contraseña = '"+ contraseña);
            sentencia_preparada = conexion.prepareStatement(sent_buscar_user);
            resultado = sentencia_preparada.executeQuery();
            if (resultado.next()){
                buscar_userR = "Usuario registrado";
                
            }else{
                buscar_userR = "El usuario no esta registrado";
            }
            conexion.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
            return buscar_userR;
        
    }
}