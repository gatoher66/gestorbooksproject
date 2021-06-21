package vista;
import controlador.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class VentanConfirmarReserva extends JFrame
{
    private JButton bt2;
    private JLabel etiqueta;
    String id_usuario;
    String id_libro;
    public JLabel book;
    public JLabel autor;
    public JButton confirmar;
    public String fecha_inicio;
    public String fecha_entrega;
    public VentanaPrincipal vp;
    ControladorReservar c_reser = new ControladorReservar();
 
    public VentanConfirmarReserva( String isbnBook, String mat, VentanaPrincipal v )
    {
        super("Confirmar Reservacion");
        this.id_libro = isbnBook;
        this.id_usuario = mat;
        this.vp = v;
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(400, 300);
        
        iniciarComponentes();        
        setVisible(true);
    }

    public void iniciarComponentes()
    {        

        JPanel mesa = new JPanel();
        mesa.setBackground(Color.WHITE);

        mesa.setLayout( new BorderLayout(15,15) );
        
        Box horizontal = Box.createHorizontalBox();
        horizontal.add(Box.createHorizontalStrut(10));
        horizontal.add(new JLabel(""));
        mesa.add(horizontal, BorderLayout.WEST);

        JPanel principal = new JPanel();        

        principal.setBackground( Color.WHITE );
        //principal.setBorder( BorderFactory.createBevelBorder(1) );
        principal.setLayout( new BorderLayout(25,25) );
        JLabel norte = new JLabel("Confirmar reservacion para:", SwingConstants.CENTER);
        norte.setFont(new Font( "Arial", Font.BOLD, 14 ));
        
        principal.add( norte, BorderLayout.NORTH );

        Box vertical = Box.createVerticalBox();

        vertical.add( Box.createVerticalStrut( 10 ) );
        JLabel user = new JLabel("Usuario: " + id_usuario, SwingConstants.CENTER);
        user.setFont(new Font( "Arial", Font.BOLD, 14 ));
        vertical.add( user );

        vertical.add( Box.createVerticalStrut( 5 ) );
        
        book = new JLabel();
        book.setFont(new Font( "Arial", Font.BOLD, 14 ));
        vertical.add( book );

        vertical.add( Box.createVerticalStrut( 5 ) );
        autor = new JLabel();
        autor.setFont(new Font( "Arial", Font.BOLD, 14 ));
        vertical.add( autor );

        vertical.add( Box.createVerticalStrut( 5 ) );

        JLabel idBook = new JLabel("ISBN: " + id_libro, SwingConstants.CENTER);
        idBook.setFont(new Font( "Arial", Font.BOLD, 14 ));
        vertical.add( idBook );
        
        vertical.add( Box.createVerticalStrut( 5 ) );

        JLabel f_a = new JLabel("Fecha de inicio: " + c_reser.getFechaActual(), SwingConstants.CENTER);
        f_a.setFont(new Font( "Arial", Font.BOLD, 14 ));
        f_a.setForeground( Color.BLUE );
        
        vertical.add( f_a );

		vertical.add( Box.createVerticalStrut( 5 ) );

        JLabel f_e = new JLabel("Fecha de enrega: " + c_reser.getFechaEntrega(), SwingConstants.CENTER);
        f_e.setFont(new Font( "Arial", Font.BOLD, 14 ));
        f_e.setForeground( Color.RED );
        
        vertical.add( f_e );

        principal.add(vertical, BorderLayout.CENTER);

        confirmar = new JButton("Confirmar");
        confirmar.setFont( new Font( "Arial", Font.BOLD, 14 ) );
        confirmar.setBackground( Color.BLUE );
        confirmar.setForeground( Color.WHITE );
        JPanel botonC = new JPanel();
        botonC.setBackground( Color.WHITE );
        botonC.setLayout( new FlowLayout( FlowLayout.CENTER ) );
        botonC.add(confirmar);
        principal.add( botonC, BorderLayout.SOUTH );        

        mesa.add(principal, BorderLayout.CENTER);

        confirmar.addActionListener( new ControladorReservar(id_libro, this, id_usuario, vp) );
        
        addWindowListener( new ControladorReservar(id_libro, this) );
        add(mesa, BorderLayout.CENTER);
    }
}