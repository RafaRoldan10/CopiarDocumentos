package principal;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;



import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class PanelFicherosTabla extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCarpeta;
	private JTextField jtfFiltro;
	private JFileChooser jfileChooser;
	
	private JScrollPane scrollPane;
	

	/**
	 * Create the panel.
	 */
	public PanelFicherosTabla() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Lista de Ficheros");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Carpeta");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfCarpeta = new JTextField();
		GridBagConstraints gbc_jtfCarpeta = new GridBagConstraints();
		gbc_jtfCarpeta.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCarpeta.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCarpeta.gridx = 1;
		gbc_jtfCarpeta.gridy = 1;
		panel.add(jtfCarpeta, gbc_jtfCarpeta);
		jtfCarpeta.setColumns(10);
		
		JButton btnOrigen = new JButton("Selecciona Carpeta");
		btnOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfCarpeta.setText(Utils.seleccionaFicheroOrigen());
			}
		});
		GridBagConstraints gbc_btnOrigen = new GridBagConstraints();
		gbc_btnOrigen.insets = new Insets(0, 0, 5, 0);
		gbc_btnOrigen.gridx = 2;
		gbc_btnOrigen.gridy = 1;
		panel.add(btnOrigen, gbc_btnOrigen);
		
		JLabel lblNewLabel_2 = new JLabel("Filtro");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		jtfFiltro = new JTextField();
		GridBagConstraints gbc_jtfFiltro = new GridBagConstraints();
		gbc_jtfFiltro.insets = new Insets(0, 0, 0, 5);
		gbc_jtfFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltro.gridx = 1;
		gbc_jtfFiltro.gridy = 2;
		panel.add(jtfFiltro, gbc_jtfFiltro);
		jtfFiltro.setColumns(10);
		
		JButton btnBuscar = new JButton("Filtra");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findFiles();
			}
		});
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.gridx = 2;
		gbc_btnBuscar.gridy = 2;
		panel.add(btnBuscar, gbc_btnBuscar);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		

	}

	
	private void findFiles() {
		List<File> files = Utils.obtenerArchivosEnCarpetaSeleccionada(this.jtfCarpeta.getText());
		
		List<File> filteredFiles = new ArrayList<File>();
		
		for (File f : files) {
			if (f.getName().toUpperCase().contains(this.jtfFiltro.getText().toUpperCase())) {
				filteredFiles.add(f);
			}
		}
		
		Object tableData[][] = new Object[filteredFiles.size()][3];
		
		for (int i = 0; i < filteredFiles.size(); i++) {
			File f = filteredFiles.get(i);
			tableData[i][0] = f.getName();
			tableData[i][1] = (f.length() / 1024f ) + " KB";
			Date d = new Date(f.lastModified());
			tableData[i][2] = d.toString();
		}
		
		JTable table = new JTable(tableData, new String[] {"Nombre", "Tamaño", "Última modificación"});

		this.scrollPane.setViewportView(table);
	}
	 
	 
	 

}
