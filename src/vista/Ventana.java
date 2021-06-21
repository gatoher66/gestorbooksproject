package vista;
import controlador.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Ventana extends JFrame
{
	
	private JButton continuar;
	private JLabel 	matricula;
	public JTextField _matricula;
	public String isbnLibro;
	VentanaPrincipal vp;

	public Ventana( String isbn, VentanaPrincipal v )
	{	
		super("Reservar");
		this.isbnLibro = isbn;
		this.vp = v;
		setSize(500,200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		iniciarComponentes();
		setVisible(true);
	}
	
	private void iniciarComponentes()
	{
		
		
		setLayout(null);
		matricula = new JLabel("Matricula: ");
		matricula.setBounds(100,25,100,50);
		matricula.setFont(new Font("Arial",Font.BOLD,20));
		
		_matricula = new JTextField ();
		_matricula.setBounds(200,30,200,35);
		_matricula.setFont(new Font("Arial",Font.BOLD,20));		
		
		continuar = new JButton("continuar");
		continuar.setBounds(200,80,100,30);
		
		add(_matricula);
		
		add(matricula);
	
		add(continuar);

		

		continuar.addActionListener( new ControladorPrincipal(2, this, vp) );
	}
}
