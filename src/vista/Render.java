package vista;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;

public class Render extends DefaultTableCellRenderer
{
	private Color color[] = { Color.RED, Color.WHITE };
	private int c;
	public Render( int tipo )
	{	
		this.c = tipo;
	}
	public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, 
													boolean hasFocus, int row, int column )
	{		
		//si el dato que le asignamos es un boton
		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		comp.setBackground( color[c] );
		comp.setFont( new Font("Arial", Font.BOLD, 14) );
		if( c ==  0)
			comp.setForeground( Color.WHITE );
		else
			comp.setForeground( Color.BLACK );
		

		if( value instanceof JButton )
		{	
			//entonces agrega el boton
			JButton btn = ( JButton ) value;
			return btn;
		}

		if( value instanceof JLabel )
		{	
			//entonces agrega el boton
			JLabel lab = ( JLabel ) value;
			return lab;
		}

		return comp; 
	}
}