package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane panelTabbed = new JTabbedPane();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		super("General");
		this.setBounds(0,0,800,600);
		
		panelTabbed.addTab("Copiar Archivos", new PanelCopiarArchivos());
		panelTabbed.addTab("Busqueda de ficheros", new PanelFicherosTabla());
		panelTabbed.addTab("Busqueda Por Tamaño", new PanelBusquedaPorTamanio());
		this.getContentPane().add(panelTabbed);		
	}

}
