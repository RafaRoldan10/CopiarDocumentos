package principal;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelBusquedaPorTamanio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCarpeta;
	private JScrollPane scrollPane = new JScrollPane();
	private JSlider slider;
	private JLabel lblTamanio = new JLabel("> 0 KB");
	private JFileChooser jfileChooser;

	/**
	 * Create the panel.
	 */
	public PanelBusquedaPorTamanio() {
		setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Busqueda de Ficheros Por Tamaño ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 12;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
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
		gbc_jtfCarpeta.gridwidth = 10;
		gbc_jtfCarpeta.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCarpeta.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCarpeta.gridx = 1;
		gbc_jtfCarpeta.gridy = 1;
		panel.add(jtfCarpeta, gbc_jtfCarpeta);
		jtfCarpeta.setColumns(10);

		JButton btnSeleccionar = new JButton("Selecciona Carpeta");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfCarpeta.setText(Utils.seleccionaFicheroOrigen());
			}
		});
		GridBagConstraints gbc_btnSeleccionar = new GridBagConstraints();
		gbc_btnSeleccionar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSeleccionar.gridx = 11;
		gbc_btnSeleccionar.gridy = 1;
		panel.add(btnSeleccionar, gbc_btnSeleccionar);

		JLabel lblNewLabel_2 = new JLabel("Tamaño:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);


		slider = new JSlider();
		slider.setValue(0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				selectFilesBySizeKB();
			}
		});
		slider.setMaximum(5);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridwidth = 9;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 2;
		gbc_slider.gridy = 2;
		panel.add(slider, gbc_slider);

		GridBagConstraints gbc_lblTamanio = new GridBagConstraints();
		gbc_lblTamanio.insets = new Insets(0, 0, 5, 0);
		gbc_lblTamanio.gridx = 11;
		gbc_lblTamanio.gridy = 2;
		panel.add(lblTamanio, gbc_lblTamanio);

		scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

	}
	
	
	/**
	 * 
	 */

	private void selectFilesBySizeKB() {

		int fileLengSelection = 0;
		switch (slider.getValue()) {
		case 0:
			lblTamanio.setText("> 0 KB");
			fileLengSelection = 0;
			break; // 0 KB
		case 1:
			lblTamanio.setText("> 100 KB");
			fileLengSelection = 100;
			break; // 100 KB
		case 2:
			lblTamanio.setText("> 1 MB");
			fileLengSelection = 1024;
			break; // 1 MB
		case 3:
			lblTamanio.setText("> 10 MB");
			fileLengSelection = 10240;
			break; // 10 MB
		case 4:
			lblTamanio.setText("> 100 MB");
			fileLengSelection = 102400;
			break; // 100 MB
		case 5:
			lblTamanio.setText("> 1 GB");
			fileLengSelection = 1048576;
			break; // 1 GB
		}

		List<File> files = Utils.obtenerArchivosEnCarpetaSeleccionada(this.jtfCarpeta.getText());

		List<File> filteredFiles = new ArrayList<File>();

		for (File f : files) {
			if (f.length() >= (fileLengSelection * 1024)) {
				filteredFiles.add(f);
			}
		}

		Object tableData[][] = new Object[filteredFiles.size()][3];

		for (int i = 0; i < filteredFiles.size(); i++) {
			File f = filteredFiles.get(i);
			tableData[i][0] = f.getName();
			tableData[i][1] = (f.length() / 1024f) + " KB";
			Date d = new Date(f.lastModified());
			tableData[i][2] = d.toString();
		}

		JTable table = new JTable(tableData, new String[] { "Nombre", "Tamaño", "Última modificación" });

		lblTamanio.setText(lblTamanio.getText() + " (" + filteredFiles.size() + " ficheros)");

		this.scrollPane.setViewportView(table);
	}

}
