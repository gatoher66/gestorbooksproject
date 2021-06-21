package modelo;
//import java.sql.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class Conexion
{
	static Connection miConexion = null;
	public Conexion()
	{

	}

	public static Connection Enlace(Connection cn)throws SQLException{
    //ruta de la base de datos la cual crearemos
        File f = new File("datos/baseDatos.db");
        String ruta = obtenerRutaGood(f.getAbsolutePath());
        
        try{
        	Class.forName("org.sqlite.JDBC");
       		miConexion = DriverManager.getConnection("jdbc:sqlite:"+ruta);        
        }catch(ClassNotFoundException e){
        	
        }
        
        return miConexion;
    }
    
    private static String obtenerRutaGood( String rut )
    {
        char [] a = rut.toCharArray();
        Vector< String > vector = new Vector<String>();
        
        for( int i=0; i<rut.length(); i++ )
        {
            vector.add( String.valueOf(a[i]) );
            if( a[i] == '\\' )
                vector.add("\\");
        }
        
        String aux2="";        
        for( String elemento : vector )
        {
            aux2+=elemento;
        }

        return aux2;
    }
}