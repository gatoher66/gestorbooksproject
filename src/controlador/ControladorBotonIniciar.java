package controlador;
import java.awt.event.*;
import vista.*;
import modelo.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class ControladorBotonIniciar implements ActionListener
{
	private MarcoAplicacion marcoApp;
	private ResultSet resultado_consulta;
	EjecutaConsultas obj = new EjecutaConsultas();
	private VentanaPrincipal window;
	public MenuAdmon tabpane2;
	public ControladorBotonIniciar( MarcoAplicacion elMarco, VentanaPrincipal v )
	{
		this.marcoApp = elMarco;
		this.window = v;
	}

	public void actionPerformed( ActionEvent evt )
	{
		String user = marcoApp._usuario.getText();
		String pwd = new String(marcoApp.contrasenia.getPassword());
		
		if( validaCampos(user, pwd) ){
			resultado_consulta = obj.EjecutarConsultas(user, pwd);
			String aux1 = "";
			String aux2 = "";
			try{
				while( resultado_consulta.next() ){
					aux1 =  resultado_consulta.getString(1);
					aux2 = resultado_consulta.getString(2);
				}
			}catch( Exception ex ){
				ex.printStackTrace();
			}			

			if( user.equals(aux1) && pwd.equals(aux2) ){			
				JOptionPane.showMessageDialog(null, "Bienvenido a la aplicacion " + user);
				verificaCamposVacios(user, pwd);
				marcoApp.dispose();
				window.iniciarSesion.setText("Cerrar Sesion");
				window.iniciarSesion.setBackground( Color.RED );
				window.usuarioActual = user;
				window.contraActual = pwd;
				tabpane2 = new MenuAdmon(window, user, pwd);
				window.panelFichas.addTab("Opciones Administrador", null, tabpane2, "Segundo panel");
				window.panelFichas.setSelectedIndex(1);
				window.panelFichas.setBackgroundAt(1, Color.RED);
				window.panelFichas.setForegroundAt(1, Color.WHITE);				
			}
			else{
				JOptionPane.showMessageDialog(null, "Usuario y/o Password Incorrectos!", "ERROR" ,  JOptionPane.ERROR_MESSAGE);
				verificaCamposVacios(user, pwd);
				marcoApp._usuario.setText("");
				marcoApp.contrasenia.setText("");
			}
		}else{			
			JOptionPane.showMessageDialog(null, "Campos sin llenar!", "ADVERTENCIA" ,  JOptionPane.WARNING_MESSAGE);
			verificaCamposVacios(user, pwd);
		}		
	}

	public boolean validaCampos( String usuario, String contra )
	{
		if( usuario.equals("") || contra.equals("") ){
			return false;
		}else{
			return true;
		}
	}

	public void verificaCamposVacios( String user, String pwd )
	{
		marcoApp._usuario.setBorder(BorderFactory.createLineBorder( Color.LIGHT_GRAY ));
		marcoApp.contrasenia.setBorder(BorderFactory.createLineBorder( Color.LIGHT_GRAY ));

		if( user.equals("") ){
			marcoApp._usuario.setBorder(BorderFactory.createLineBorder( Color.RED ));			
		}
		if( pwd.equals("") ){
			marcoApp.contrasenia.setBorder(BorderFactory.createLineBorder( Color.RED ));
		}
	}
}