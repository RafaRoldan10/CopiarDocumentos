package principal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class Utils {
	
	private static JFileChooser jfileChooser;
	
	public static List<File> obtenerArchivosEnCarpetaSeleccionada(String jtfCarpeta) {
        List<File> archivos = new ArrayList<>();

        // Obtenemos la ruta de la carpeta seleccionada
        String rutaCarpeta = jtfCarpeta;

        // Verificamos si la ruta de la carpeta es válida
        if (rutaCarpeta != null && !rutaCarpeta.isEmpty()) {
            File carpeta = new File(rutaCarpeta);

            // Verificamos si la carpeta existe y es un directorio
            if (carpeta.exists() && carpeta.isDirectory()) {
                // Obtenemos todos los archivos dentro de la carpeta
                File[] archivosCarpeta = carpeta.listFiles();

                // Agregamos los archivos a la lista
                for (File archivo : archivosCarpeta) {
                    archivos.add(archivo);
                }
            }
        }

        return archivos;
    }
	
	public static String seleccionaFicheroOrigen () {
		jfileChooser = new JFileChooser();
		
		// Configurando el componente
		
		// Establecimiento de la carpeta de inicio
		jfileChooser.setCurrentDirectory(new File("C:\\Users"));
		
		
		jfileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 

		
		// Filtro del tipo de ficheros que puede abrir
		jfileChooser.setFileFilter(new FileFilter() {
			
			public String getDescription() {
				return "Carpetas";
			}
			
			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) 
					return true;
				return false;
			}
		});
		
		// Abro el diálogo para la elección del usuario
		int seleccionUsuario = jfileChooser.showOpenDialog(null);
		
		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			File fichero = jfileChooser.getSelectedFile();
			
			// Vuelco el nombre del fichero sobre el JTextField
			return fichero.getAbsolutePath();
			
		}
		return "";
	}
	
	
	
 

}
