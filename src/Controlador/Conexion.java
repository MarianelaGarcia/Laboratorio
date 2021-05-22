/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Grupo2
 */
public class Conexion {
    private String url="jdbc:mysql://localhost/base de datos de prueba";
    private String usuario="root";
    private String password="";
    private Connection con;
    
    public Conexion() throws ClassNotFoundException{
            Class.forName("org.mariadb.jdbc.Driver");
    }
    
    public Conexion(String url, String usuario, String password) throws ClassNotFoundException {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        Class.forName("org.mariadb.jdbc.Driver");
    }
    
    public Connection getConexion() throws SQLException{
        if(con == null){
           con = DriverManager.getConnection(url,usuario,password);
        }
        return con;
    }
    
}
