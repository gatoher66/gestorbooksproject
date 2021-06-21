package vista;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import javax.swing.table.*;
import controlador.*;

public class VentanaPrincipal extends JFrame
{
	private JPanel principal;
	//arreglo para almacenar las apariencias disponibles del sitema
	UIManager.LookAndFeelInfo apariencias[]; 
	public JButton iniciarSesion;
	public JButton botonReservar;
	public DefaultTableModel modelo;
	public JTable tabla;
	public JPanel panelTablaDeResultados;
	public Box contenedorPrincipal;
	public JButton enviarConsulta;
	public JButton ayuda;
	public JTabbedPane panelFichas;
	public JRadioButton op_palabra_frase;
	public JRadioButton op_titulo;
	public JRadioButton op_autor;
	public ButtonGroup grupoRadios;
	public RoundJTextField _buscar;
	public String radioSeleccionado = "palabra";
	public String usuarioActual = "";
	public String contraActual = "";
	public VentanaPrincipal()
	{
		super("Sistema de Reservaciones de Libros");
		//obtenemos las apariencias disponibles
		apariencias = UIManager.getInstalledLookAndFeels();
		//manejamos las posibles excepciones en caso de error
        try{
            UIManager.setLookAndFeel(apariencias[1].getClassName());
            SwingUtilities.updateComponentTreeUI( this );
        }catch( Exception ex ){
            ex.printStackTrace();
        }
		setSize(1000,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		iniciarComponentes();
		setVisible(true);
	}

	public void iniciarComponentes()
	{

		contenedorPrincipal = Box.createVerticalBox();
		principal = new JPanel();
		principal.setBackground( new Color(0,128,192) );

		//zona de titulo
		JPanel head = new JPanel();
		head.setBackground( new Color(81,168,255) );
		head.setLayout( new FlowLayout(FlowLayout.CENTER, 50, 10) );
		JLabel titulo = new JLabel("Bienvenido al Sistema de Reservaciones de Libros");
		titulo.setFont( new Font( "Comic Sans MS", Font.BOLD, 24 ) );
		titulo.setForeground( Color.WHITE );
		head.add( titulo );

		JPanel botonesArriba = new JPanel();
		botonesArriba.setBackground( new Color(81,168,255) );
		botonesArriba.setLayout( new FlowLayout() );
		ayuda = new JButton("Ayuda");
		ayuda.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		ayuda.setBackground( new Color(185,175,0) );
		ayuda.setForeground( Color.WHITE );
		
		iniciarSesion = new JButton("Iniciar Sesion");
		iniciarSesion.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		iniciarSesion.setBackground( new Color(0,128,0) );
		iniciarSesion.setForeground( Color.WHITE );
		//botonReservar = new JButton("Reservar");
		

		botonesArriba.add( ayuda );
		botonesArriba.add(iniciarSesion);
		head.add(botonesArriba);		
		contenedorPrincipal.add( head );

		panelFichas = new JTabbedPane();
		try{
            UIManager.setLookAndFeel(apariencias[2].getClassName());
            SwingUtilities.updateComponentTreeUI( panelFichas );
        }catch( Exception ex ){
            ex.printStackTrace();
        }
		JPanel panelInvitado = new JPanel();
		panelInvitado.setLayout( new BorderLayout(1,1) );
		try{
            UIManager.setLookAndFeel(apariencias[1].getClassName());
            SwingUtilities.updateComponentTreeUI( panelInvitado );
        }catch( Exception ex ){
            ex.printStackTrace();
        }
		//zona de consultas
		contenedorPrincipal.add( Box.createVerticalStrut( 25 ) );
		
		JPanel barraBusqueda = new JPanel();
		barraBusqueda.setLayout( new FlowLayout() );


		op_palabra_frase = new JRadioButton("Palabra o frase");
		op_titulo = new JRadioButton("Titulo");
		op_autor = new JRadioButton("Autor");
		grupoRadios = new ButtonGroup();
		grupoRadios.add(op_palabra_frase);
		grupoRadios.add(op_titulo);
		grupoRadios.add(op_autor);

		Box barrax = Box.createVerticalBox();

		JPanel opciones = new JPanel();
		opciones.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		opciones.add( op_palabra_frase );
		opciones.add( op_titulo );
		opciones.add( op_autor );

		barrax.add( opciones );

		barraBusqueda.setBackground( new Color(255,255,200) );
		JLabel buscar = new JLabel( "Buscar:" );
		buscar.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		_buscar = new RoundJTextField(25);
		_buscar.setFont(new Font( "Arial", Font.BOLD, 14 ));
		_buscar.setForeground( Color.BLUE );
		enviarConsulta = new JButton("enviar consulta");
		enviarConsulta.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		enviarConsulta.setBackground( new Color(0,0,255) );
		enviarConsulta.setForeground( Color.WHITE );
		barraBusqueda.add(buscar);
		barraBusqueda.add(_buscar);
		barraBusqueda.add( enviarConsulta );
		barrax.add( barraBusqueda );
		//contenedorPrincipal.add( barraBusqueda );
		panelInvitado.add(barrax, BorderLayout.NORTH );

		//zona de la tabla
		contenedorPrincipal.add( Box.createVerticalStrut( 25 ) );	
		panelTablaDeResultados = new JPanel();	
		panelTablaDeResultados.setBackground( new Color(255,255,255) );
		panelTablaDeResultados.setLayout( new FlowLayout() );
		tabla = new JTable();
		tabla.setBackground( new Color(255,255,255) );
		//lo primero que hacemos es renderizar la tabla
		tabla.setPreferredScrollableViewportSize( new Dimension(950, 300) );
		tabla.setDefaultRenderer( Object.class, new Render(1) ); //lo que nos permitira agregar botones
		modelo = new DefaultTableModel(){
				public boolean isCellEditable( int row, int column )
			{
				return false;
			}
		};

		tabla.setModel(modelo);
		tabla.setRowHeight(30);
		
		addWindowListener( new ControladorLibroConsultas(this) );
		
		tabla.getTableHeader().setDefaultRenderer( new Render(0) );		
		
		JScrollPane scroll = new JScrollPane(tabla);
		
		tabla.setFillsViewportHeight(true);
		scroll.getViewport().setBackground( new Color(255,255,159) );
		panelTablaDeResultados.add( scroll );
		panelInvitado.add(panelTablaDeResultados, BorderLayout.CENTER);

		panelFichas.addTab( "Principal", null, panelInvitado, "Primer panel" );
		//contenedorPrincipal.add( panelTablaDeResultados );
		//int indPan = panelFichas.getSelectedIndex();
		panelFichas.setBackgroundAt(0, Color.RED);
		panelFichas.setForegroundAt(0, Color.WHITE);
		panelFichas.setFont( new Font("Arial", Font.BOLD, 14) );
		contenedorPrincipal.add( panelFichas );

		//zona de abajo
		contenedorPrincipal.add( Box.createVerticalStrut( 25 ) );
		
		JPanel p_btnS = new JPanel();
		p_btnS.setLayout( new FlowLayout(FlowLayout.CENTER) );
		JButton salir = new JButton("Salir");
		salir.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		salir.setBackground( new Color(255,0,0) );
		salir.setForeground( Color.WHITE );
		p_btnS.setBackground(new Color(0,128,192));
		p_btnS.add(salir);
		
		contenedorPrincipal.add( p_btnS );

		contenedorPrincipal.add( Box.createVerticalStrut( 25 ) );

		JPanel down = new JPanel();

		down.setLayout( new FlowLayout(FlowLayout.CENTER) );

		Box footer = Box.createVerticalBox();		

		JPanel p_Lb1 = new JPanel();
		p_Lb1.setBackground( new Color(255,255,200) );
		p_Lb1.setLayout( new FlowLayout(FlowLayout.CENTER) );
		JLabel info = new JLabel("Instituto Tecnologico de Pochutla");
		info.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		p_Lb1.add(info);
		
		JPanel p_Lb2 = new JPanel();
		p_Lb2.setBackground(new Color(255,255,200));
		p_Lb2.setLayout( new FlowLayout(FlowLayout.CENTER) );
		JLabel info2 = new JLabel("San Pedro Pochutla Oaxaca, region de la Costa. Con domicilio Km. 5.35 Carretera San Pedro Pochutla"); 
		info2.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		p_Lb2.add(info2);
		
		JPanel p_Lb3 = new JPanel();
		p_Lb3.setBackground(new Color(255,255,200));
		p_Lb3.setLayout( new FlowLayout(FlowLayout.CENTER) );		
		JLabel info3 = new JLabel("Puerto Angel, Colonia El Colorado, 70902 Oax.");		 
		info3.setFont( new Font( "Arial", Font.BOLD, 14 ) );
		p_Lb3.add(info3);

		footer.add( p_Lb1 );
		footer.add( p_Lb2 );
		footer.add( p_Lb3 );
		down.add(footer);
		down.setBackground( new Color(255,255,200) );
		contenedorPrincipal.add( down );


		principal.add( contenedorPrincipal, BorderLayout.CENTER );	

		add( principal, BorderLayout.CENTER );
		
		iniciarSesion.addActionListener( new ControladorBotonIniciarSesion(this) );
		salir.addActionListener( new ControladorPrincipal(1) );
		enviarConsulta.addActionListener( new ControladorLibroConsultas(this) );
		op_palabra_frase.addItemListener( new ControladorPrincipal(this) );
		op_titulo.addItemListener( new ControladorPrincipal(this) );
		op_autor.addItemListener( new ControladorPrincipal(this) );
	}
}