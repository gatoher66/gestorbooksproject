package controlador;
import java.awt.event.*;
import javax.swing.*;
import vista.*;
import java.awt.*;

public class ControladorCajasDeTexto implements FocusListener
{
	private MarcoAplicacion marcoApp;	
	public ControladorCajasDeTexto( MarcoAplicacion elMarco )
	{
		this.marcoApp = elMarco;
	}

	public void focusGained( FocusEvent e )
	{
		if( e.getSource() == marcoApp._usuario )
			marcoApp._usuario.setBorder(BorderFactory.createLineBorder( Color.GRAY ));
		if( e.getSource() == marcoApp.contrasenia )
			marcoApp.contrasenia.setBorder(BorderFactory.createLineBorder( Color.GRAY ));
	}

	public void focusLost( FocusEvent e )
	{
		if( e.getSource() == marcoApp._usuario )
			if( marcoApp._usuario.getText().equals("") )
				marcoApp._usuario.setBorder(BorderFactory.createLineBorder( Color.RED ));
		if( e.getSource() == marcoApp.contrasenia )
			if( marcoApp.contrasenia.getText().equals("") )
				marcoApp.contrasenia.setBorder(BorderFactory.createLineBorder( Color.RED ));
	}	
}