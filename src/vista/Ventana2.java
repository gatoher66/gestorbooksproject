package vista;
import controlador.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Ventana2 extends JFrame
{
	
	private JButton continuar;
	private JLabel 	nombre;
	public JTextField _nombre;
	private JLabel apellido;
	public JTextField _apellido;
	private JLabel carrera;
	public JTextField _carrera;
	private JLabel semestre;
	public JTextField _semestre;
	public JLabel mat;
	public JTextField _mat;
	String mm;
	public Ventana2( String mat )
	{
	
		super("Registrar");
		setSize(500,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.mm = mat;
		iniciarComponentes();		
		setVisible(true);
	}
	
	private void iniciarComponentes()
	{
		
		
		setLayout(null);
		mat = new JLabel("Matricula: ");
		mat.setBounds(50,0,100,50);
		mat.setFont(new Font("Arial",Font.BOLD,20));

		_mat = new JTextField ();
		_mat.setBounds(170,5,300,35);
		_mat.setFont(new Font("Arial",Font.BOLD,20));	
		_mat.setText(mm);

		nombre = new JLabel("Nombre: ");
		nombre.setBounds(50,40,100,50);
		nombre.setFont(new Font("Arial",Font.BOLD,20));
		
		_nombre = new JTextField ();
		_nombre.setBounds(170,50,300,35);
		_nombre.setFont(new Font("Arial",Font.BOLD,20));	

			
		apellido = new JLabel("Apellido: ");
		apellido.setBounds(50,80,100,50);
		apellido.setFont(new Font("Arial",Font.BOLD,20));
		
		_apellido = new JTextField ();
		_apellido.setBounds(170,85,300,35);
		_apellido.setFont(new Font("Arial",Font.BOLD,20));
		
		carrera = new JLabel("Carrera: ");
		carrera.setBounds(50,120,100,50);
		carrera.setFont(new Font("Arial",Font.BOLD,20));
		
		_carrera = new JTextField ();
		_carrera.setBounds(170,125,300,35);
		_carrera.setFont(new Font("Arial",Font.BOLD,20));
		
		semestre = new JLabel("Semestre: ");
		semestre.setBounds(50,160,100,50);
		semestre.setFont(new Font("Arial",Font.BOLD,18));
		
		_semestre = new JTextField ();
		_semestre.setBounds(170,165,300,35);
		_semestre.setFont(new Font("Arial",Font.BOLD,20));
		
		continuar = new JButton("continuar");
		continuar.setBounds(300,200,150,50);
		
		add(_nombre);
		add(nombre);
		
		add(_apellido);
		add(apellido);
		
		add(_carrera);
		add(carrera);
		
		add(_semestre);
		add(semestre);
		add(continuar);

		add(mat);
		add(_mat);

		continuar.addActionListener( new ControladorPrincipal(4, this) );
	}
}
