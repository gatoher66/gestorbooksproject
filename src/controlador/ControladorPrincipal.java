package controlador;
import modelo.*;
import vista.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class ControladorPrincipal implements ActionListener, ItemListener
{

	private int boton;
	private String mat;
	private ResultSet resultado_consulta;
	private Ventana vt;
	private VentanaPrincipal vp;
	private Ventana2 vt2;
	//RegistrarEstudiante obj = new RegistrarEstudiante();
	EjecutaConsultas ejeObj = new EjecutaConsultas();
	public ControladorPrincipal( VentanaPrincipal marco )
	{
		this.vp = marco;
	}

	public ControladorPrincipal( int n )
	{
		this.boton = n;		
	}

	public ControladorPrincipal( int n, Ventana v, VentanaPrincipal vprin )
	{
		this.boton = n;
		this.vt = v;		
		this.vp = vprin;
	}

	public ControladorPrincipal( int n, Ventana2 v )
	{
		this.boton = n;
		this.vt2 = v;
	}

	public void actionPerformed( ActionEvent evt )
	{
		if( boton == 1 ){
			System.exit(0);
		}
		else 
			//En caso de que la matricula no está registrada
			if( boton == 2 && primeraVez() == true ){
				Ventana2 v2 = new Ventana2(vt._matricula.getText());				

			}
			else
				if( boton == 2 && primeraVez() == false ){
					vt.dispose();					
					if( tienesReservasPendientes() ) {
						JOptionPane.showMessageDialog(null, "Tienes prestamos pendientes...", "Advertencia", JOptionPane.WARNING_MESSAGE);
						try{
							ejeObj.enviarConsulta.close();	
						}catch( Exception ex ){
							ex.printStackTrace();
						}							
						
					}						
					else{
						VentanConfirmarReserva v3 = new VentanConfirmarReserva( vt.isbnLibro, vt._matricula.getText(), vp );
					}						
				}
				else
					if( boton == 4 ){						
						int val = ejeObj.InsertarEstudiante( vt2._mat.getText(), vt2._nombre.getText(),
											vt2._apellido.getText(), vt2._carrera.getText(), vt2._semestre.getText() );						
					}
	}

	public void itemStateChanged( ItemEvent evento )
	{
		if( evento.getSource() == vp.op_palabra_frase )
			vp.radioSeleccionado = "palabra";
		else if( evento.getSource() == vp.op_titulo )
			vp.radioSeleccionado = "titulo";
		else if( evento.getSource() == vp.op_autor )
			vp.radioSeleccionado = "autor";
	}

	public boolean primeraVez(  )
	{
		resultado_consulta = ejeObj.RegistrarEstudiante(vt._matricula.getText());
		String aux1 = "";

		try{
			while( resultado_consulta.next() ){
				aux1 =  resultado_consulta.getString(1);			
			}
		}catch( Exception ex ){
				ex.printStackTrace();
		}
		
		try{
			ejeObj.enviarConsulta.close();	
		}catch( Exception ex ){
				ex.printStackTrace();
		}
								
		if( vt._matricula.getText().equals(aux1) )
			return false;
		else
			return true;
	}
	
	public boolean tienesReservasPendientes()
	{
		ResultSet prestamos = ejeObj.consultaPrestamos( vt._matricula.getText() );
		boolean edo = false;
		System.out.println("entro a preguntar " + vt._matricula.getText());
		int estado=1;
		try{
			while( prestamos.next() ){
				estado = prestamos.getInt("devuelto");
				System.out.println( "Estado --> " + estado );
				if( estado == 0 ){
					return true;
				}
			}
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		
		return edo;
	}
}