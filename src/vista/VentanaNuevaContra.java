package vista;

import controlador.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager;

public class VentanaNuevaContra extends JFrame{
	public JButton btn_cancel;
	public JButton btn_register;
	public JPasswordField caja_newContra;
	public JPasswordField caja_cNewContra;	
	public JPasswordField caja_pwd;
	public MenuAdmon elMarco2;
	VentanaPrincipal vp;
	public VentanaNuevaContra( MenuAdmon u, VentanaPrincipal v )
	{		
		super("Practica");
		this.elMarco2 = u;
		this.vp = v;
		setSize(550,200);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		iniciarComponentes();
		setVisible(true);
	}
	
	public void iniciarComponentes()
	{
		JLabel titulo = new JLabel("Cambiar Contrasenia");
		titulo.setFont( new Font( "Arial", Font.BOLD, 18 ) );
		titulo.setForeground( Color.WHITE );
		JPanel p_t = new JPanel();

		p_t.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		p_t.setBackground( new Color(121,121,255) );
		p_t.add(titulo);
		JPanel centro = new JPanel();
		centro.setBackground( Color.WHITE );
		
		add( p_t, BorderLayout.NORTH );

		centro.setLayout( new FlowLayout() );

		Box cajaLabels = Box.createVerticalBox();
		JPanel p_nc = new JPanel();
		p_nc.setLayout( new FlowLayout(FlowLayout.RIGHT) );
		JLabel etq_ncontra = new JLabel("Nueva contrasenia:");
		etq_ncontra.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		p_nc.add( etq_ncontra );
		p_nc.setBackground(Color.WHITE);
		cajaLabels.add(p_nc);
				
		
		cajaLabels.add( Box.createVerticalStrut( 15 ) );
		JPanel p_conContra = new JPanel();
		p_conContra.setLayout( new FlowLayout(FlowLayout.RIGHT) );
		JLabel etq_confirmar = new JLabel("Confirmar contrasenia nueva:");
		etq_confirmar.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		p_conContra.add( etq_confirmar );
		p_conContra.setBackground(Color.WHITE);
		cajaLabels.add(p_conContra);
				
		Box cajaTextFields = Box.createVerticalBox();
		caja_newContra = new JPasswordField(25);
		caja_newContra.setFont( new Font( "Arial", Font.BOLD, 14 ) );		
		cajaTextFields.add(caja_newContra);
		
		cajaTextFields.add( Box.createVerticalStrut( 15 ) );
		caja_cNewContra = new JPasswordField(25);
		caja_cNewContra.setFont( new Font( "Arial", Font.BOLD, 14 ) );		
		cajaTextFields.add(caja_cNewContra);
										
		centro.add(cajaLabels);
		centro.add(cajaTextFields);
		
		btn_cancel = new JButton("Cancelar");								
		btn_cancel.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		btn_cancel.setBackground( new Color(128,0,0) );
		btn_cancel.setForeground( Color.WHITE );
		
		btn_register = new JButton("Cambiar");								
		btn_register.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		btn_register.setBackground( new Color(0,128,0) );
		btn_register.setForeground( Color.WHITE );
		
		JPanel panel_botones = new JPanel();
		panel_botones.setLayout( new FlowLayout(FlowLayout.CENTER) );
		panel_botones.setBackground( Color.WHITE );
		
		//eventos de botones
		btn_cancel.addActionListener( new ControladorAdmon2(this, vp) );
		btn_register.addActionListener( new ControladorAdmon2(this, vp) );
		
		panel_botones.add(btn_cancel);
		panel_botones.add(btn_register);
	
		add( centro, BorderLayout.CENTER );
		add( panel_botones, BorderLayout.SOUTH );
	}
}
