package vista;
import controlador.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager;

public class MarcoNuevoUsuario extends JFrame
{
	public JButton btn_cancel;
	public JButton btn_register;
	public JTextField caja_nombre;
	public JTextField caja_apellidos;
	public JTextField caja_usuario;
	public JPasswordField caja_pwd;
	
	public MarcoNuevoUsuario()
	{
		super("Practica");
		setSize(550,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		iniciarComponentes();
		setVisible(true);
	}

	public void iniciarComponentes()
	{
		JLabel titulo = new JLabel("Nuevo Registro");
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
		JPanel p_nombre = new JPanel();
		p_nombre.setLayout( new FlowLayout(FlowLayout.RIGHT) );
		JLabel etq_nombre = new JLabel("Nombre:");
		etq_nombre.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		p_nombre.add( etq_nombre );
		p_nombre.setBackground(Color.WHITE);
		cajaLabels.add(p_nombre);
				
		
		cajaLabels.add( Box.createVerticalStrut( 15 ) );
		JPanel p_apellidos = new JPanel();
		p_apellidos.setLayout( new FlowLayout(FlowLayout.RIGHT) );
		JLabel etq_apellidos = new JLabel("Apellidos:");
		etq_apellidos.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		p_apellidos.add( etq_apellidos );
		p_apellidos.setBackground(Color.WHITE);
		cajaLabels.add(p_apellidos);
		
		cajaLabels.add( Box.createVerticalStrut( 15 ) );
		JPanel p_usuario = new JPanel();
		p_usuario.setLayout( new FlowLayout(FlowLayout.RIGHT) );
		JLabel etq_usuario = new JLabel("Usuario:");
		etq_usuario.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		p_usuario.add( etq_usuario );
		p_usuario.setBackground(Color.WHITE);
		cajaLabels.add(p_usuario);
		
		cajaLabels.add( Box.createVerticalStrut( 15 ) );
		JPanel p_pwd = new JPanel();
		p_pwd.setLayout( new FlowLayout(FlowLayout.RIGHT) );
		JLabel etq_pwd = new JLabel("Contrasenia:");
		etq_pwd.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		p_pwd.add( etq_pwd );
		p_pwd.setBackground(Color.WHITE);
		cajaLabels.add(p_pwd);

		Box cajaTextFields = Box.createVerticalBox();
		caja_nombre = new JTextField(25);
		caja_nombre.setFont( new Font( "Arial", Font.BOLD, 14 ) );		
		cajaTextFields.add(caja_nombre);
		
		cajaTextFields.add( Box.createVerticalStrut( 15 ) );
		caja_apellidos = new JTextField(25);
		caja_apellidos.setFont( new Font( "Arial", Font.BOLD, 14 ) );		
		cajaTextFields.add(caja_apellidos);
		
		cajaTextFields.add( Box.createVerticalStrut( 15 ) );
		caja_usuario = new JTextField(25);
		caja_usuario.setFont( new Font( "Arial", Font.BOLD, 14 ) );		
		cajaTextFields.add(caja_usuario);
		
		cajaTextFields.add( Box.createVerticalStrut( 15 ) );
		caja_pwd = new JPasswordField(25);
		caja_pwd.setFont( new Font( "Arial", Font.BOLD, 14 ) );		
		cajaTextFields.add(caja_pwd);				
						
		centro.add(cajaLabels);
		centro.add(cajaTextFields);
		
		btn_cancel = new JButton("Cancelar");								
		btn_cancel.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		btn_cancel.setBackground( new Color(128,0,0) );
		btn_cancel.setForeground( Color.WHITE );
		
		btn_register = new JButton("Registrar");								
		btn_register.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		btn_register.setBackground( new Color(0,128,0) );
		btn_register.setForeground( Color.WHITE );
		
		JPanel panel_botones = new JPanel();
		panel_botones.setLayout( new FlowLayout(FlowLayout.CENTER) );
		panel_botones.setBackground( Color.WHITE );
		
		//eventos de botones
		btn_cancel.addActionListener( new ControladorBotonRegistrar(this) );
		btn_register.addActionListener( new ControladorBotonRegistrar(this) );
		
		panel_botones.add(btn_cancel);
		panel_botones.add(btn_register);
	
		add( centro, BorderLayout.CENTER );
		add( panel_botones, BorderLayout.SOUTH );
	}
}