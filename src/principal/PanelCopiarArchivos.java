package principal;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileFilter;

public class PanelCopiarArchivos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfDestino;
	private JTextField jtfOrigen;
	private JFileChooser jfileChooser;
	private JProgressBar jprogressbar;

	/**
	 * Create the panel.
	 */
	public PanelCopiarArchivos() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel_2 = new JLabel("Copiado De Ficheros");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 8;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 0;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Origen");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		jtfOrigen = new JTextField();
		GridBagConstraints gbc_jtfOrigen = new GridBagConstraints();
		gbc_jtfOrigen.gridwidth = 10;
		gbc_jtfOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_jtfOrigen.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfOrigen.gridx = 2;
		gbc_jtfOrigen.gridy = 1;
		add(jtfOrigen, gbc_jtfOrigen);
		jtfOrigen.setColumns(10);
		
		JButton btnOrigen = new JButton("Selecciona Carpeta Origen");
		btnOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfOrigen.setText(Utils.seleccionaFicheroOrigen());
			}
		});
		GridBagConstraints gbc_btnOrigen = new GridBagConstraints();
		gbc_btnOrigen.insets = new Insets(0, 0, 5, 0);
		gbc_btnOrigen.gridx = 12;
		gbc_btnOrigen.gridy = 1;
		add(btnOrigen, gbc_btnOrigen);
		
		JLabel lblNewLabel_1 = new JLabel("Destino");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfDestino = new JTextField();
		GridBagConstraints gbc_jtfDestino = new GridBagConstraints();
		gbc_jtfDestino.gridwidth = 10;
		gbc_jtfDestino.insets = new Insets(0, 0, 5, 5);
		gbc_jtfDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfDestino.gridx = 2;
		gbc_jtfDestino.gridy = 2;
		add(jtfDestino, gbc_jtfDestino);
		jtfDestino.setColumns(10);
		
		JButton btnDestino = new JButton("Selecciona carpeta destino");
		btnDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfDestino.setText(Utils.seleccionaFicheroOrigen());
			}
		});
		GridBagConstraints gbc_btnDestino = new GridBagConstraints();
		gbc_btnDestino.insets = new Insets(0, 0, 5, 0);
		gbc_btnDestino.gridx = 12;
		gbc_btnDestino.gridy = 2;
		add(btnDestino, gbc_btnDestino);
		
		JButton btnCopiar = new JButton("Copiar");
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copiarFicheros();
			}
		});
		GridBagConstraints gbc_btnCopiar = new GridBagConstraints();
		gbc_btnCopiar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCopiar.gridx = 7;
		gbc_btnCopiar.gridy = 3;
		add(btnCopiar, gbc_btnCopiar);
		
		jprogressbar = new JProgressBar();
		jprogressbar.setStringPainted(true);
		GridBagConstraints gbc_jprogressBar = new GridBagConstraints();
		gbc_jprogressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jprogressBar.gridwidth = 13;
		gbc_jprogressBar.insets = new Insets(0, 0, 5, 0);
		gbc_jprogressBar.gridx = 0;
		gbc_jprogressBar.gridy = 4;
		add(jprogressbar, gbc_jprogressBar);

	}

	
	private void copiarFicheros () {
	    // Establecer la barra de progreso en 0 al inicio
	    jprogressbar.setValue(0);

	    // Obtener las rutas de los directorios de origen y destino desde los campos de texto
	    String origenPath = jtfOrigen.getText();
	    String destinoPath = jtfDestino.getText();

	    // Asegurarse de que ambas rutas no estén vacías
	    if (!origenPath.isEmpty() && !destinoPath.isEmpty()) {
	        // Crear objetos File que representen los directorios de origen y destino
	        File origenDir = new File(origenPath);
	        File destinoDir = new File(destinoPath);

	        // Asegurarse de que el directorio de origen exista
	        if (origenDir.exists() && origenDir.isDirectory()) {
	            // Asegurarse de que el directorio de destino exista, crearlo si no existe
	            if (!destinoDir.exists()) {
	                destinoDir.mkdirs(); // Crear el directorio de destino de forma recursiva si no existe
	            }

	            // Obtener una lista de archivos en el directorio de origen
	            File[] archivos = origenDir.listFiles();
	            int totalArchivos = archivos.length;

	            // Iterar sobre cada archivo en el directorio de origen
	            for (int i = 0; i < totalArchivos; i++) {
	                File archivo = archivos[i];

	                // Construir la ruta del archivo de destino en el directorio de destino
	                Path rutaArchivoDestino = destinoDir.toPath().resolve(archivo.getName());

	                try {
	                    // Copiar el archivo al directorio de destino
	                    Files.copy(archivo.toPath(), rutaArchivoDestino, StandardCopyOption.REPLACE_EXISTING);

	                    // Calcular el progreso en porcentaje
	                    int progreso = (int) (((double) (i + 1) / totalArchivos) * 100);

	                    // Actualizar la barra de progreso
	                    jprogressbar.setValue(progreso);
	                } catch (IOException e) {
	                    System.err.println("Error al copiar el archivo: " + archivo.getName());
	                    e.printStackTrace(); // Imprimir la traza de pila para depuración
	                }
	            }
	        }
	    }
	}

}
