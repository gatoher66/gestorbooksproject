package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

public class VistaPrestamos extends JPanel
{
	private JComboBox comboBuscar;
	private String filtros [] = { "Todos", "Pendientes", "Devueltos" };
	private JTable tabla;
	private JButton buscar;
	public VistaPrestamos()
	{
		setLayout( new BorderLayout(1,1) );
		setBorder( BorderFactory.createTitledBorder( "Busqueda" ) );
		setBackground(Color.WHITE);	

		Box caja = Box.createVerticalBox();
		caja.setBackground(Color.WHITE);
		caja.createVerticalStrut(30);
		
		JPanel busqueda = new JPanel();
		busqueda.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		busqueda.setBackground(Color.WHITE);
		comboBuscar = new JComboBox(filtros);
		buscar = new JButton("Buscar");
		busqueda.add(buscar);
		busqueda.add(comboBuscar);
		
		caja.createVerticalStrut(30);

		JPanel panelTabla = new JPanel();
		
		panelTabla.setLayout( new BorderLayout(1,1) );
		panelTabla.setBorder( BorderFactory.createTitledBorder( "Resultados" ) );
		panelTabla.setBackground(Color.WHITE);
		
		DefaultTableModel modelo = new DefaultTableModel(){
				public boolean isCellEditable( int row, int column )
			{
				return false;
			}
		};

		tabla = new JTable();
		
		modelo.addColumn("ID prestamo");
		modelo.addColumn("ID libro");
		modelo.addColumn("Matricula Estudiante");
		modelo.addColumn("Fecha inicio");		
		modelo.addColumn("Fecha fin");
		modelo.addColumn("Devuelto");

		tabla.setModel( modelo );
		tabla.setPreferredScrollableViewportSize( new Dimension(600, 200) );
		JScrollPane scroll = new JScrollPane(tabla);		
		panelTabla.add( scroll );			

		JPanel opciones = new JPanel();
		opciones.setLayout(new FlowLayout(FlowLayout.CENTER));
		opciones.setBackground(Color.WHITE);
		JButton ampliar = new JButton("Ampliar prestamo");		
		JButton devolver = new JButton("Devolver");		
		opciones.add(ampliar);
		opciones.add(devolver);
		caja.add(busqueda);
		caja.add(panelTabla);		
		caja.add(opciones);
		add(caja);
	}
}
