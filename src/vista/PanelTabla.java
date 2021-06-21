package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class PanelTabla extends JPanel
{
	DefaultTableModel modelo;
	public PanelTabla()
	{
		modelo = new DefaultTableModel();
		

		JTable tablaLibrosDisp = new JTable(modelo);
		tablaLibrosDisp.setPreferredScrollableViewportSize( new Dimension(500, 300) );
		add( new JScrollPane(tablaLibrosDisp) );
	}

}