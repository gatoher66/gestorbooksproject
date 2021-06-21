package vista;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

public class VistaUsuarios extends JPanel
{
	private JTextField _buscar;
	private JComboBox comboBuscar;
	private String filtros [] = { "matricula", "nombre", "carrera" };
	private JTable tabla;
	public VistaUsuarios()
	{
		setLayout( new BorderLayout(1,1) );
		setBorder( BorderFactory.createTitledBorder( "Busqueda" ) );
		setBackground(Color.WHITE);	

		Box caja = Box.createVerticalBox();
		caja.setBackground(Color.WHITE);
		caja.createVerticalStrut(30);
		
		JPanel busqueda = new JPanel();
		busqueda.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		busqueda.add( new JLabel("Buscar") );
		busqueda.setBackground(Color.WHITE);
		_buscar = new JTextField(25);		
		comboBuscar = new JComboBox(filtros);

		busqueda.add(_buscar);		
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
		
		modelo.addColumn("Usuario");
		modelo.addColumn("Nombre");
		modelo.addColumn("Carrera");		
		

		tabla.setModel( modelo );
		tabla.setPreferredScrollableViewportSize( new Dimension(600, 200) );
		JScrollPane scroll = new JScrollPane(tabla);		
		panelTabla.add( scroll );			

		JPanel opciones = new JPanel();
		opciones.setLayout(new FlowLayout(FlowLayout.CENTER));
		opciones.setBackground(Color.WHITE);
		JButton borrar = new JButton("borrar");
		JButton modificar = new JButton("modificar");
		opciones.add(borrar);
		opciones.add(modificar);
		caja.add(busqueda);
		caja.add(panelTabla);		
		caja.add(opciones);
		add(caja);
	}
}
