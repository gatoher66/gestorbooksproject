package controlador;
import modelo.*;
import vista.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ControladorReservar extends WindowAdapter implements ActionListener
{	
	private String id_lib;
	private String id_user;
	private ResultSet resultado_consulta;
	public VentanaPrincipal vp;
	EjecutaConsultas obj = new EjecutaConsultas();
	//RegistrarEstudiante reg = new RegistrarEstudiante();
	VentanConfirmarReserva vApp;	
	public ControladorReservar()
	{
		
	}
	
	public ControladorReservar( String isbn, VentanConfirmarReserva marco )
	{		
		this.id_lib = isbn;		
		this.vApp = marco;		
	}

	public ControladorReservar( String isbn, VentanConfirmarReserva marco, String user, VentanaPrincipal v )
	{		
		this.id_lib = isbn;		
		this.vApp = marco;			
		this.id_user = user;
		this.vp = v;
	}


	public void windowOpened( WindowEvent evt )
	{
		resultado_consulta = obj.obtenerLibrosPorISBN( id_lib );

		try{
			while( resultado_consulta.next() ){
				vApp.book.setText("Libro: " + resultado_consulta.getString("TITULO"));
				vApp.autor.setText("Autor: " + resultado_consulta.getString("AUTOR"));

			}
		}catch( Exception e ){
			e.printStackTrace();
		}
	}

	public void actionPerformed( ActionEvent evt )
	{
		vApp.dispose();		
		String fecha_inicio = getFechaActual();		
		//Calculamos la fecha de entrega, la cual sera solo de 7 dias
		
		String fecha_entrega = getFechaEntrega();
		
		System.out.println( "fecha inicio: " + fecha_inicio );
		System.out.println( "fecha entrega: " + fecha_entrega );					
		
		ResultSet lib = obj.obtenerLibrosPorISBN(id_lib);
		int cantidad = 0;
		try{
			while( lib.next() ){
				cantidad = lib.getInt("cantidad");
				System.out.println("cantidad: " + cantidad);
			}			
		}catch(Exception ex ){
			ex.printStackTrace();			
		}
		
		obj.InsertarPrestamoNuevo( id_user, id_lib, fecha_inicio, fecha_entrega, 0 );
		obj.actualizaCantidadLibros(id_lib, cantidad-1);
		//vp.panelFichas.repaint();
		//vp.modelo.repaint();
		vp.enviarConsulta.doClick();
		//vp.tabla.repaint();
		
		JOptionPane.showMessageDialog( null, "Reservacion exitosa!", "Mensaje", JOptionPane.INFORMATION_MESSAGE );
	}
	
	public String getFechaActual()
	{
		Date fecha_actual = new Date();
		DateFormat date_simple = new SimpleDateFormat("dd/MM/yyy");
		
		return date_simple.format(fecha_actual);
	}

	public String getFechaEntrega()
	{
		Date fecha_actual = new Date();
		Calendar calendario = Calendar.getInstance();
		DateFormat date_simple = new SimpleDateFormat("dd/MM/yyy");
		
		calendario.setTime(fecha_actual); //establecemos la fecha actual en el calendario
		calendario.add( Calendar.DAY_OF_YEAR, 7 ); //sumamos 7 dias a la fecha actual
		Date fecha_devolucion = calendario.getTime(); //obtenemos la fecha dentro de 7 dias
		
		return date_simple.format(fecha_devolucion);
	}
}