package modelo;
import java.sql.*;

public class RegistrarEstudiante
{
	Conexion miConexion;
	public PreparedStatement enviarConsulta;
	private Statement InsertarEstudianteNuevo;
	private Statement InsertarAdminNuevo;
	private Statement InsertarPrestamoNuevo;
	private final String consultaStudent= "SELECT MATRICULA FROM ESTUDIANTE WHERE MATRICULA=?";
	static Connection conexion;
    static Statement s;
    static ResultSet rs;
    Conexion on = new Conexion();
    
	public RegistrarEstudiante()
	{
		miConexion = new Conexion();
	}

	public ResultSet RegistrarEstudiante( String m )
	{		
		try{
			conexion = on.Enlace(conexion);
			enviarConsulta = conexion.prepareStatement(consultaStudent);
			enviarConsulta.setString(1, m);
			rs = enviarConsulta.executeQuery();			
			
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		return rs;
	}

	public int InsertarEstudiante( String matricula, String n, String a, String c, String s )
	{
		int resultado=0;		
		try{
			conexion = on.Enlace(conexion);
           	InsertarEstudianteNuevo = conexion.createStatement();            
            String query = "INSERT INTO ESTUDIANTE(MATRICULA, NOMBRE, APELLIDOS, CARRERA, SEMESTRE)values ('" + Integer.parseInt(matricula) + "','" + n + "','" + a + "','" + c + "','" + Integer.parseInt(s) + "')";
            InsertarEstudianteNuevo.executeUpdate(query);
            InsertarEstudianteNuevo.close();
			resultado=1;
		}catch( SQLException ex ){
			ex.printStackTrace();
		}
		return resultado;
	}
	
	public void InsertarAdministrador( String nom, String ape, String user, String pw )
	{		
		try{
			conexion = on.Enlace(conexion);
           	InsertarAdminNuevo = conexion.createStatement();            
            String query = "INSERT INTO ADMINISTRADORES(NOMBRE, APELLIDOS, USUARIO, PASSWORD)values ('" + nom + "','" + ape + "','" + user + "','" + pw + "')";
            InsertarAdminNuevo.executeUpdate(query);
            InsertarAdminNuevo.close();
			
		}catch( SQLException ex ){
			ex.printStackTrace();
		}		
	}
	
	public void InsertarPrestamoNuevo( String id_user, String id_lib, String f_ini, String f_ent, int dev )
	{		
		try{
			conexion = on.Enlace(conexion);
           	InsertarPrestamoNuevo = conexion.createStatement();            
            String query = "INSERT INTO PRESTAMOS(ID_USUARIO, ID_LIBRO, FECHA_INICIO, FECHA_ENTREGA,DEVUELTO)values ('" + id_user + "','" + id_lib + "','" + f_ini + "','" + f_ent + "','" + dev + "')";
            InsertarPrestamoNuevo.executeUpdate(query);
            InsertarPrestamoNuevo.close();
			
		}catch( SQLException ex ){
			ex.printStackTrace();
		}		
	}
}