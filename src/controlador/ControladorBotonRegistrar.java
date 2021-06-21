package controlador;
import java.awt.event.*;
import vista.*;
import modelo.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class ControladorBotonRegistrar implements ActionListener
{
	private MarcoAplicacion marcoApp;
	private MarcoNuevoUsuario marco2;
	EjecutaConsultas obj = new EjecutaConsultas();
	private int btn;
	public ControladorBotonRegistrar( MarcoAplicacion elMarco )
	{
		this.marcoApp = elMarco;
		this.btn = 1;
	}
	
	public ControladorBotonRegistrar( MarcoNuevoUsuario marco )
	{
		this.marco2 = marco;
		this.btn = 2;
	}

	public void actionPerformed( ActionEvent e )
	{
		if( btn == 1 ){		
			MarcoNuevoUsuario ventana_registro = new MarcoNuevoUsuario();				
		} 
		else if( btn == 2 && e.getSource() == marco2.btn_cancel ){
				marco2.dispose();
		}
		else if( btn == 2 && e.getSource() == marco2.btn_register ){
				
				String nom = marco2.caja_nombre.getText();
				String ape = marco2.caja_apellidos.getText();
				String user = marco2.caja_usuario.getText();
				//nota aun debemos validar la contraseña...
				String pass = new String(marco2.caja_pwd.getPassword());
				
				obj.InsertarAdministrador(nom,ape,user,pass);
									
				JOptionPane.showMessageDialog(null,"Registro Exitoso");
		}
		
	}
}