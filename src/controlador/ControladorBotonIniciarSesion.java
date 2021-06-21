package controlador;
import java.awt.event.*;
import vista.*;
import modelo.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class ControladorBotonIniciarSesion implements ActionListener
{
	private VentanaPrincipal marcoApp;
	public ControladorBotonIniciarSesion( VentanaPrincipal elMarco )
	{
		this.marcoApp = elMarco;
	}

	public void actionPerformed( ActionEvent e )
	{

		if( marcoApp.iniciarSesion.getText().equals("Iniciar Sesion") ){
			MarcoAplicacion ventana_registro = new MarcoAplicacion( marcoApp );				
		}
		else{
			JOptionPane.showMessageDialog( null, "Hasta luego!", "Adios", JOptionPane.INFORMATION_MESSAGE );
			marcoApp.panelFichas.removeTabAt(1);
			marcoApp.iniciarSesion.setText("Iniciar Sesion");
			marcoApp.iniciarSesion.setBackground( new Color(0,128,0) );
		}
	}
}