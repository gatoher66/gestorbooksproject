package controlador;
import vista.*;
import modelo.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.*;
public class ControladorLibroConsultas extends WindowAdapter implements ActionListener
{
	private VentanaPrincipal v;
	private ResultSet camposLibro;
	EjecutaConsultas obj = new EjecutaConsultas();
	public ControladorLibroConsultas( VentanaPrincipal vp )
	{
		this.v = vp;
	}

	public void windowOpened( WindowEvent evt )
	{

		v.op_palabra_frase.setSelected(true);									

		v.modelo.addColumn("Libro");
		v.modelo.addColumn("Disponibilidad");
		v.modelo.addColumn("Reservar");
		v.tabla.setModel( v.modelo );		
		v.tabla.getColumnModel().getColumn(0).setPreferredWidth(650);
		v.tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		v.tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		ManejadorBotonTabla manejador = new ManejadorBotonTabla();
		v.tabla.addMouseListener(manejador);

	}

	public void actionPerformed( ActionEvent ev )
	{
		if(v.tabla.getRowCount() > 0 ){			
			while( v.tabla.getRowCount() > 0 ){
				v.modelo.removeRow(0);				
			}
		}
			
		
		camposLibro = obj.buscar( v._buscar.getText(), v.radioSeleccionado );		
		String info = "";
		int cantidad;
		String disp = "";
			
		try{			
			while( camposLibro.next() ){
				info += camposLibro.getString("TITULO");
				info += ", " + camposLibro.getString("AUTOR");
				info += ", " + camposLibro.getString("EDITORIAL");				
				cantidad = camposLibro.getInt("CANTIDAD");				
				
				if( cantidad > 0 ){
					disp = "si";
					JButton botonReservar2 = new JButton("Reservar");
					botonReservar2.setFont( new Font( "Arial", Font.BOLD, 14 ) );
					botonReservar2.setBackground( new Color(107,116,216) );
					botonReservar2.setForeground( Color.WHITE );							
					botonReservar2.setName( camposLibro.getString("ISBN") );										
					Object row[] = { info, disp, botonReservar2 };					
					v.modelo.addRow(row);
				}
				else{
					disp="no";
					JLabel ninguna = new JLabel("None", SwingConstants.CENTER);
					Object row[] = { info, disp, ninguna};	
					v.modelo.addRow(row);
				}
								
				info = "";
			}
		}catch( Exception ex ){
			ex.printStackTrace();
		}		
	}

	private class ManejadorBotonTabla extends MouseAdapter
	{
		public void mouseClicked( MouseEvent evt )
		{
			//obtenemos la posicion x y y de la tabla donde se hizo click
			int column = v.tabla.getColumnModel().getColumnIndexAtX( evt.getX() );
			int row = evt.getY()/v.tabla.getRowHeight();			
			if( row < v.tabla.getRowCount() && row >= 0 && column < v.tabla.getColumnCount() && column >= 0 )
			{
				Object value = v.tabla.getValueAt(row, column);								
				if( value instanceof JButton )
				{
					((JButton)value).doClick();
					JButton boton = (JButton)value;
					Ventana ven = new Ventana( boton.getName(), v );					
				}
			}

		}
	}

}