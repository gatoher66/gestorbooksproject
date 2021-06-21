package modelo;
import java.sql.*;

public class EjecutaConsultas
{
	Conexion miConexion;
	public PreparedStatement enviarConsulta;
	private PreparedStatement enviarConsultaLibroISBN;
	private final String consulta= "select usuario, password from administradores where usuario=? and password=?";	
	private final String consultaISBN= "SELECT TITULO, AUTOR, CANTIDAD FROM books WHERE ISBN=?";
	private final String consultarLibros = "SELECT * FROM LIBROS";
	private final String consultaAdmin = "Select nombre, apellidos from administradores where usuario=? and password=?";
	static Connection conexion;
    static Statement s;
    static ResultSet rs;
    
    
    private Statement InsertarEstudianteNuevo;
	private Statement InsertarAdminNuevo;
	private Statement InsertarPrestamoNuevo;
	private final String consultaStudent= "SELECT MATRICULA FROM ESTUDIANTE WHERE MATRICULA=?";

    
    Conexion on = new Conexion();
	public EjecutaConsultas()
	{
		miConexion = new Conexion();
	}

	public ResultSet EjecutarConsultas( String user, String pwd )
	{		
		try{
			conexion = on.Enlace(conexion);
            //Statement s = conexion.createStatement();			
			enviarConsulta = conexion.prepareStatement(consulta);
			enviarConsulta.setString(1, user);
			enviarConsulta.setString(2, pwd);
			rs = enviarConsulta.executeQuery();					
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet consultaPrestamos( String id_usuario )
	{		
		try{
			conexion = on.Enlace(conexion);
            //Statement s = conexion.createStatement();		
            String consultaEsto = "select devuelto from prestamos where id_usuario=?";	
			enviarConsulta = conexion.prepareStatement(consultaEsto);
			enviarConsulta.setInt(1, Integer.parseInt(id_usuario));			
			rs = enviarConsulta.executeQuery();
			//enviarConsulta.close();					
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet ConsultaAdministrador( String user, String pwd )
	{		
		try{
			conexion = on.Enlace(conexion);
            //Statement s = conexion.createStatement();			
			enviarConsulta = conexion.prepareStatement(consultaAdmin);
			enviarConsulta.setString(1, user);
			enviarConsulta.setString(2, pwd);
			rs = enviarConsulta.executeQuery();					
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		return rs;
	}
	
	public void actualizaUsuarioAdmin( String uAntiguo, String uNuevo )
	{
		String cons = "update administradores set usuario='" + uNuevo + "' where usuario='" + uAntiguo + "'"; 
		System.out.println("consulta a realizar: " + cons );
		Statement st;
		try{
			conexion = on.Enlace(conexion);            
			st = conexion.createStatement();;							
			int a = st.executeUpdate(cons);			
		}catch( Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public void actualizaPasswordAdmin( String us, String passNuevo )
	{
		String cons = "update administradores set password='" + passNuevo + "' where usuario='" + us + "'"; 
		//System.out.println("consulta a realizar: " + cons );
		Statement st;
		try{
			conexion = on.Enlace(conexion);            
			st = conexion.createStatement();;							
			int a = st.executeUpdate(cons);			
		}catch( Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public void actualizaCantidadLibros( String isbn, int cantidad )
	{
		String cons = "update books set cantidad='" + cantidad + "' where isbn='" + isbn + "'"; 
		System.out.println("consulta a realizar: " + cons );
		Statement st;
		try{
			conexion = on.Enlace(conexion);            
			st = conexion.createStatement();;							
			int a = st.executeUpdate(cons);			
		}catch( Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public ResultSet buscar( String valor, String filtro )
	{
		ResultSet books = null;
		String consult="";
		if( filtro.equals("palabra") ){
			consult= "SELECT * FROM books WHERE TITULO LIKE '%"+valor+"%'" +
			"OR AUTOR LIKE '%"+valor+"%' OR EDITORIAL LIKE '%"+valor+"%'";
		}else if( filtro.equals("titulo") ){
			consult= "SELECT * FROM books WHERE TITULO LIKE '%"+valor+"%'";
		}else if( filtro.equals("autor") ){
			consult= "SELECT * FROM books WHERE AUTOR LIKE '%"+valor+"%'";
		}
		
		try{
			conexion = on.Enlace(conexion);            
			enviarConsulta = conexion.prepareStatement(consult);					
			books = enviarConsulta.executeQuery();						
			
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		return books;
	}
/*
	public ResultSet obtenerMetadatos( String tabla )
	{
		ResultSet campos = null;
		Connection accesoBD = miConexion.dameConexion();
		try{
			//primero capturamos informacion de la tabla, nombre de encabezados
			DatabaseMetaData datosBD = accesoBD.getMetaData();

			campos = datosBD.getColumns(null, null, tabla, null);		

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return campos;
	}

	public ResultSet obtenerLibros()
	{
		ResultSet libros = null;
		Connection accesoBD = miConexion.dameConexion();
		try{
			st = accesoBD.createStatement();
			libros = st.executeQuery(consultarLibros);

		}catch( Exception e ){
			e.printStackTrace();
		}
		return libros;
	}
*/
	public ResultSet obtenerLibrosPorISBN( String isbn )
	{
		ResultSet books = null;
		
		try{
			conexion = on.Enlace(conexion);			
			enviarConsulta = conexion.prepareStatement(consultaISBN);
			enviarConsulta.setString(1, isbn);			
			books = enviarConsulta.executeQuery();			
			
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		return books;
	}			
	
	
	//CODIGO NUEVO CLASE ANTIGUA
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