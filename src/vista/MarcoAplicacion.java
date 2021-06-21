package vista;
import controlador.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager;

public class MarcoAplicacion extends JFrame
{
	public JTextField _usuario;
	public JPasswordField contrasenia;
	public JButton btn_iniciar;
	public JButton linkRegister;

	//arreglo para almacenar las apariencias disponibles del sitema
	UIManager.LookAndFeelInfo apariencias[]; 
	private VentanaPrincipal miVentana;
	
	public MarcoAplicacion( VentanaPrincipal elMarco )
	{
		super("Practica");
		this.miVentana = elMarco;
		//obtenemos las apariencias disponibles
		apariencias = UIManager.getInstalledLookAndFeels();
		//manejamos las posibles excepciones en caso de error
        try{
            UIManager.setLookAndFeel(apariencias[1].getClassName());
            SwingUtilities.updateComponentTreeUI( this );
        }catch( Exception ex ){
            ex.printStackTrace();
        }
		setSize(350,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		iniciarComponentes();
		setVisible(true);
	}

	public void iniciarComponentes()
	{
		colocarComponentes();
		agregarEventoBotones();		
		agregarEventosCajasDeTexto();
	}

	public void agregarEventosCajasDeTexto()
	{
		_usuario.addFocusListener( new ControladorCajasDeTexto(this) );
		contrasenia.addFocusListener( new ControladorCajasDeTexto(this) );
	}

	public void agregarEventoBotones()
	{
		btn_iniciar.addActionListener( new ControladorBotonIniciar(this, miVentana) );
		linkRegister.addActionListener( new ControladorBotonRegistrar(this) );
	}

	public void colocarComponentes()
	{
		JPanel pared = new JPanel();
		pared.setLayout( new BorderLayout(5,5) );				
		pared.setBackground(Color.WHITE);

		Box arriba = Box.createVerticalBox();
		Box abajo = Box.createVerticalBox();
		JLabel bienvenido = new JLabel("Bienvenido al Sistema");

		bienvenido.setFont( new Font( "Arial", Font.BOLD, 22 ) );
		JPanel welcome = new JPanel();
		welcome.setLayout( new FlowLayout() );
		welcome.setOpaque(false);

		_usuario = new JTextField(7);
		arriba.add( Box.createVerticalStrut( 25 ) );
		
		JPanel us = new JPanel();
		us.setLayout( new FlowLayout(FlowLayout.RIGHT) );
		JLabel etq_usuario = new JLabel("Usuario:");
		etq_usuario.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		us.add( etq_usuario );
		us.setOpaque(false);
		arriba.add( us );
		arriba.add( Box.createVerticalStrut( 25 ) );
		JLabel etq_contra = new JLabel("Contrasenia:");
		etq_contra.setFont( new Font( "Arial", Font.BOLD, 14 ) ); 
		arriba.add( etq_contra );		
		
		contrasenia = new JPasswordField(7);		
		abajo.add( Box.createVerticalStrut( 25 ) );
		abajo.add( _usuario );
		abajo.add( Box.createVerticalStrut( 25 ) );
		abajo.add( contrasenia );

		JPanel centro = new JPanel();
		FlowLayout l = new FlowLayout( FlowLayout.LEFT );
		l.setHgap(15);
		centro.setLayout( l );
		centro.setOpaque(false);
		centro.add(arriba);
		centro.add(abajo);

		welcome.add(bienvenido);
		pared.add( welcome, BorderLayout.NORTH );
		pared.add(centro, BorderLayout.CENTER);
		
		JPanel botones = new JPanel();
		botones.setLayout( new FlowLayout() );
		botones.setOpaque(false);

		btn_iniciar = new JButton("Iniciar Sesion");
		btn_iniciar.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		btn_iniciar.setBackground( new Color(0,128,0) );
		btn_iniciar.setForeground( Color.WHITE );
		botones.add(btn_iniciar);

		linkRegister = new JButton("Registrarse");
		linkRegister.setBackground( new Color(0,0,255) );
		linkRegister.setForeground( Color.WHITE );
		
		linkRegister.setFont( new Font( "Arial", Font.BOLD, 14 ) );		
		botones.add( linkRegister );
		
		pared.add( botones, BorderLayout.SOUTH );
		add(pared);
	}
}