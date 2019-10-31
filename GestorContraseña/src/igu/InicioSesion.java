package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import static javax.swing.JOptionPane.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import persistencia.Persistencia;
import persistencia.impl.PersistenciaImpl;
import utils.Utils;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

public class InicioSesion extends JFrame {
	private JPanel panel;
	private JButton botonIniciarSesion;
	private JLabel lblContrasea;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		

			public void run() {
				try {
					InicioSesion frame;
					frame = new InicioSesion();
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
	public InicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 259);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getBotonIniciarSesion());
			panel.add(getLblContrasea());
			panel.add(getPasswordField());
		}
		return panel;
	}
	private JButton getBotonIniciarSesion() {
		if (botonIniciarSesion == null) {
			botonIniciarSesion = new JButton("Iniciar Sesíon");
			botonIniciarSesion.setBounds(101, 146, 157, 23);
			botonIniciarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String contrasenia=getPasswordField().getText().trim();
					System.out.println("Contraseña digitada: "+contrasenia);
					Persistencia persistencia= new PersistenciaImpl();
					
					try {
						String contraseniaEncriptada=persistencia.leerUsuario().getContrasenaMaestra();
						String contraseniaDesencriptada=Utils.desencriptar(contraseniaEncriptada);
						System.out.println("Contraseña que está en la base de datos: "+contraseniaEncriptada);
						if(contraseniaDesencriptada.equals(contrasenia))
						{
							System.out.println("Sesion Iniciada");
							new registrosGUI().setVisible(true);
							dispose();
						
						}
						else
						{
							System.out.println("Contraseña erronea");
							   JOptionPane.showMessageDialog(new JFrame(), "Contraseña Incorrecta :(", "Error",
								        JOptionPane.ERROR_MESSAGE);
						}
						//System.out.println(claveDesemcriptada);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					

					// TODO Auto-generated catch block

				}
			});
		}
		return botonIniciarSesion;
	}
	private JLabel getLblContrasea() {
		if (lblContrasea == null) {
			lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setBounds(21, 86, 122, 14);
		}
		return lblContrasea;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(115, 83, 199, 20);
		}
		return passwordField;
	}
}
