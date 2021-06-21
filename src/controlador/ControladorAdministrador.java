package controlador;
import java.awt.event.*;
import vista.*;
import modelo.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;

public class ControladorAdministrador implements ListSelectionListener, ActionListener
{
	private MenuAdmon v;	
	private JPanel c;
	private VentanaPrincipal m;
	private MenuAdmon aux;
	private ResultSet resultado_consulta;
	EjecutaConsultas obj = new EjecutaConsultas();
	private int num_btn = 0;
	
	public ControladorAdministrador()
	{
		
	}
	
	public ControladorAdministrador( MenuAdmon mar, int boton, VentanaPrincipal v )
	{
		this.v = mar;
		this.num_btn = boton;
		this.m = v;
	}

	public void valueChanged( ListSelectionEvent e )
	{
		System.out.println("cambia lista -- " + v.user);
		switch( v.listaOpciones.getSelectedIndex() )
		{
			case 0:	
					//System.out.println(v.user);				
					aux = new MenuAdmon(m, m.usuarioActual, v.pass);
					v.centro.removeAll();
					v.centro.add(aux.centro);																						
					m.panelFichas.repaint();					
					break;
			case 1: 
					//System.out.println("case 1: " + v.user);										
					v.centro.removeAll();
					VistaUsuarios vista = new VistaUsuarios();					
			 		v.centro.add( vista );
			 		m.panelFichas.repaint();			 					 		
			 		break;
			case 2:	
					v.centro.removeAll();
					VistaLibros books = new VistaLibros();					
			 		v.centro.add( books );
			 		m.panelFichas.repaint();
					break;
			case 3: 
					v.centro.removeAll();
					VistaPrestamos vpres = new VistaPrestamos();					
			 		v.centro.add( vpres );
			 		m.panelFichas.repaint();
					break;
			case 4: m.panelFichas.setSelectedIndex(0);
					v.listaOpciones.setSelectedIndex(0); break;
		}
	}
	
	public void actionPerformed( ActionEvent evt )
	{
		if( num_btn == 1 ){
			String nuevoNomUser = JOptionPane.showInputDialog(null, "Introduzca el nuevo nombre: ");
			if( nuevoNomUser != null )
			{							
				obj.actualizaUsuarioAdmin( m.usuarioActual, nuevoNomUser);
				//v.user = nuevoNomUser;	
				v.usuario.setText("Usuario " +nuevoNomUser);	
				m.usuarioActual = nuevoNomUser;														
				//System.out.println("despues de cambar: "+v.user);
				//v = new MenuAdmon( m, nuevoNomUser, v.pass );				
				
			} 
		} else if( num_btn == 2 && v.ver.getText().equals("Ver") ){
			v.ver.setText("Ocultar");
			v.contra.setVisible(false);
			v.contraVisible.setVisible(true);			
		} else if( num_btn == 2 && v.ver.getText().equals("Ocultar") ){
			v.ver.setText("Ver");
			v.contra.setVisible(true);
			v.contraVisible.setVisible(false);			
		} else if( num_btn == 3 ){
			VentanaNuevaContra vn = new VentanaNuevaContra( v, m );
		}					
	}
	
	public String consultaNombreAdmin( String u, String p )
	{
		resultado_consulta = obj.ConsultaAdministrador(u, p);
		String names = "";
		String apes = "";
		try{
			while( resultado_consulta.next() ){
				names =  resultado_consulta.getString(1);
				apes = resultado_consulta.getString(2);
				System.out.println(names);					
			}
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		return names+" "+apes;
	}		
	
	public void setU( String u )
	{
		u = u;
	}
	
	public void setP( String p )
	{
		p = p;
	}
}