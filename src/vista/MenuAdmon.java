package vista;
import controlador.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class MenuAdmon extends JPanel
{
	public JList listaOpciones;
	private final String nombreOpciones[] = { "Perfil", "Info Usuarios", "Info Libros", "Prestamos", "Principal" };
	public JPanel cuerpo;
	private JPanel lateral;
	public JPanel centro;
	public VentanaPrincipal vp;
	public String user = "";
	public String name;	
	public String pass;

	public JButton ver;
	public JLabel usuario;
	public JPasswordField contra;
		
	public JTextField contraVisible;
	ControladorAdministrador contAdmin = new ControladorAdministrador();
	public MenuAdmon( VentanaPrincipal v, String us, String p )
	{				
		this.user = us;
		this.pass = p;
		this.vp = v;
		
		System.out.println("user actual: " +  user);
		
		setLayout( new BorderLayout(1,1) );
		cuerpo = new JPanel();
		lateral = new JPanel();
		centro = new JPanel();

		cuerpo.setLayout( new BorderLayout(1,1) );
		cuerpo.setBackground(Color.WHITE);
		centro.setBackground(Color.WHITE);
		lateral.setBackground( new Color(121,121,255) );
		centro.setLayout( new BorderLayout(1,1) );
		lateral.setLayout( new FlowLayout( FlowLayout.LEFT ) );

		listaOpciones = new JList(nombreOpciones);
		listaOpciones.setSelectedIndex(0);
		listaOpciones.setBackground( new Color(121,121,255) );
		listaOpciones.setSelectionBackground( Color.RED );
		listaOpciones.setSelectionForeground( Color.WHITE );
		listaOpciones.setFont( new Font( "Arial", Font.BOLD, 16 ) );
		listaOpciones.setForeground( Color.YELLOW );

		listaOpciones.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		lateral.add( listaOpciones );
		
		Box caja = Box.createVerticalBox();	
		JPanel fila1 = new JPanel();
		JPanel fila2 = new JPanel();
		JPanel fila3 = new JPanel();
		JPanel fila4 = new JPanel();

		fila1.setLayout( new FlowLayout( FlowLayout.LEFT ) );
		JLabel titulo = new JLabel("Perfil de administrador");
		titulo.setFont( new Font("Arial", Font.BOLD, 20) );
		fila1.add( titulo );
		fila1.setBackground(Color.WHITE);
		caja.add(fila1);				
		//caja.createVerticalStrut(15);

		fila2.setLayout( new FlowLayout( FlowLayout.LEFT ) );
		//obtenemos el nombre del usuario
		name = contAdmin.consultaNombreAdmin(vp.usuarioActual,vp.contraActual);
		JLabel nombre = new JLabel("Nombre: " + name);		
		nombre.setFont( new Font("Arial", Font.BOLD, 16) );
		fila2.add( nombre );
		fila2.setBackground(Color.WHITE);
		caja.add(fila2);				
		//caja.createVerticalStrut(15);

		fila3.setLayout( new FlowLayout( FlowLayout.LEFT ) );
		usuario = new JLabel("Usuario " + vp.usuarioActual );
		usuario.setFont( new Font("Arial", Font.BOLD, 16) );
		fila3.add( usuario );

		JButton cambia1 = new JButton();
		creaEstiloBoton(cambia1, "Cambiar");
		fila3.add( cambia1 );
		fila3.setBackground(Color.WHITE);
		caja.add(fila3);				
//		caja.createVerticalStrut(15);

		fila4.setLayout( new FlowLayout( FlowLayout.LEFT ) );
		JLabel pwd = new JLabel("Password: ");
		pwd.setFont( new Font("Arial", Font.BOLD, 16) );
		fila4.add( pwd );
		contra = new JPasswordField(10);
		contra.setText( vp.contraActual );
		fila4.add( contra );
		//fila4.remove(contra);
		//fila4.add( new JTextField("hola como estan") );
		contraVisible = new JTextField( vp.contraActual , 10);
		contraVisible.setVisible(false);
		fila4.add(contraVisible);
		ver = new JButton();
		creaEstiloBoton(ver, "Ver");
		fila4.add( ver );

		JButton cambia2 = new JButton();
		creaEstiloBoton(cambia2, "Cambiar");
		fila4.add( cambia2 );
		fila4.setBackground(Color.WHITE);
		caja.add(fila4);						

		listaOpciones.addListSelectionListener( new ControladorAdministrador(this,0 ,vp) );
		
		cambia1.addActionListener( new ControladorAdministrador(this, 1, vp) );
		cambia2.addActionListener( new ControladorAdministrador(this, 3, vp) );
		
		ver.addActionListener( new ControladorAdministrador(this,2,vp) );
		
		centro.add(caja, BorderLayout.CENTER);		
		cuerpo.add( lateral, BorderLayout.WEST );
		cuerpo.add( centro, BorderLayout.CENTER );		
		add(cuerpo, BorderLayout.CENTER);
	}

	public void creaEstiloBoton( JButton btn, String texto  )
	{
		btn.setText(texto);
		btn.setFont( new Font("Arial", Font.BOLD, 14) );
		btn.setBackground(new Color(0,128,0));
		btn.setForeground( Color.WHITE );
	}

	public JPanel getCentro()
	{
		return centro;
	}
	
	public void setUser( String u )
	{
		user = u;
	}
	
	public String getUser( )
	{
		return user;
	}
}