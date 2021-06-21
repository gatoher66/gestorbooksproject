package controlador;

import java.awt.event.*;
import vista.*;
import modelo.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;


public class ControladorAdmon2 implements ActionListener
{
	private VentanaNuevaContra nueva;
	EjecutaConsultas obj = new EjecutaConsultas();
	VentanaPrincipal vp;
	public ControladorAdmon2( VentanaNuevaContra vnpass, VentanaPrincipal v )
	{
		this.nueva = vnpass;
		this.vp = v;
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == nueva.btn_cancel ){
			nueva.dispose();
		}else if( e.getSource() == nueva.btn_register ){
			String p1 = new String(nueva.caja_newContra.getPassword());
			String p2 = new String( nueva.caja_cNewContra.getPassword() );
			if( !p1.equals(p2) ){
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
				nueva.caja_newContra.setText("");
				nueva.caja_cNewContra.setText("");
			}else{
				JOptionPane.showMessageDialog(null, "Contraseña cambiada exitosamente!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Antigua contra: " + nueva.elMarco2.vp.contraActual);
				obj.actualizaPasswordAdmin( vp.usuarioActual, p1);
				vp.contraActual = p1;
				nueva.elMarco2.contra.setText(p1);
				nueva.elMarco2.contraVisible.setText(p1);					
				nueva.dispose();
			}
		}
	}
}
