package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import persistencia.Persistencia;
import persistencia.impl.PersistenciaImpl;
import utils.Utils;
import utils.UtilsContraseñaVerifications;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import com.sun.prism.Image;
import java.awt.Toolkit;
import java.awt.Color;

public class InicioSesion extends JFrame {
	private JPanel panel;
	private JButton botonIniciarSesion;
	private JLabel lblContrasea;
	private JPasswordField passwordField;
	private JButton btnImprotarInfo;
	private JButton btnExportarInfo;
	private JLabel lblBienvenido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {

					if (UtilsContraseñaVerifications.verSiElArchivoExisteOSiEstaVacio()) {
						UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
						InicioSesion frame;
						frame = new InicioSesion();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
						frame.setResizable(false);
						//javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.aero.AeroLookAndFeel");
						
					} else {
						new ModificarContraseñaGUI("No existe ninguna contraseña maestra, crear una", true)
								.setVisible(true);
					}
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
		setTitle("Gestor Contrase\u00F1as PUJ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 259);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(null);
			panel.add(getBotonIniciarSesion());
			panel.add(getLblContrasea());
			panel.add(getPasswordField());
			panel.add(getBtnImprotarInfo());
			panel.add(getBtnExportarInfo());
			panel.add(getLblBienvenido());
		}
		return panel;
	}

	private JButton getBotonIniciarSesion() {
		if (botonIniciarSesion == null) {
			botonIniciarSesion = new JButton("Iniciar Sesíon");
			botonIniciarSesion.setBounds(101, 146, 157, 23);
			botonIniciarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String contrasenia = getPasswordField().getText().trim();
					System.out.println("Contraseña digitada: " + contrasenia);
					Persistencia persistencia = new PersistenciaImpl();

					try {
						String contraseniaEncriptada = persistencia.leerUsuario().getContrasenaMaestra();
						String contraseniaDesencriptada = Utils.desencriptar(contraseniaEncriptada);
						System.out.println("Contraseña que está en la base de datos: " + contraseniaEncriptada);
						if (contraseniaEncriptada.equals(Utils.encriptar(contrasenia))) {
							LocalDate fechaCreacionContra = persistencia.leerUsuario().getFechaDeCreacionContraseña();
							LocalDate date = LocalDate.now();
							long diferencia = ChronoUnit.DAYS.between(fechaCreacionContra, date);
							if (diferencia < 0)// Just in case xdxdxdddd
							{
								diferencia *= -1;
							}
							if (diferencia > 7) {
								ModificarContraseñaGUI mod = new ModificarContraseñaGUI(
										"Su contraseña ha expirado, por favor ingresar una contraseña nueva", false);
								mod.setLocationRelativeTo(null);
								mod.setResizable(false);
								mod.setBackground(Color.WHITE);
								mod.setVisible(true);
								dispose();
							} else {
								System.out.println("Sesion Iniciada");
								RegistroGUI reg = new RegistroGUI();
								reg.setLocationRelativeTo(null);							
								reg.setResizable(false);
								reg.setBackground(Color.WHITE);
								reg.setVisible(true);
								dispose();
							}
						} else {
							System.out.println("Contraseña erronea");
							JOptionPane.showMessageDialog(new JFrame(), "Contraseña Incorrecta :(", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						// System.out.println(claveDesemcriptada);
					} catch (IOException e) {
						System.out.println("error" + e.toString());
						e.printStackTrace();
					} catch (Exception e) {
						System.out.println("error" + e.toString());
						e.printStackTrace();
					}

				}
			});
		}
		return botonIniciarSesion;
	}

	private JLabel getLblContrasea() {
		if (lblContrasea == null) {
			lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setBounds(21, 104, 122, 14);
		}
		return lblContrasea;
	}

	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(115, 101, 199, 20);
		}
		return passwordField;
	}

	private JButton getBtnImprotarInfo() {
		if (btnImprotarInfo == null) {
			btnImprotarInfo = new JButton("Importar Info");
			btnImprotarInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(new java.io.File("."));
					fc.setDialogTitle("Seleccionar archivo para importar");
					fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					if (fc.showOpenDialog(btnImprotarInfo) == JFileChooser.APPROVE_OPTION) {
						System.out.println("You selected: " + fc.getSelectedFile().getAbsolutePath());

						try {
							Files.copy(Paths.get(fc.getSelectedFile().getPath()),
									new PersistenciaImpl().flujoDelArchivo());
						} catch (IOException e1) {
							System.out.println("error" + e.toString());
							e1.printStackTrace();
						}
					}
				}
			});
			btnImprotarInfo.setBounds(10, 186, 116, 23);
		}
		return btnImprotarInfo;
	}

	private JButton getBtnExportarInfo() {
		if (btnExportarInfo == null) {
			btnExportarInfo = new JButton("Exportar Info");
			btnExportarInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(new java.io.File("."));
					fc.setDialogTitle("Seleccionar Carpeta para exportar");
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if (fc.showOpenDialog(btnImprotarInfo) == JFileChooser.APPROVE_OPTION) {
						System.out.println("You selected: " + fc.getSelectedFile().getAbsolutePath());

						try {
							Files.copy(Paths.get(new PersistenciaImpl().getBdd().getPath()), new FileOutputStream(
									new File(fc.getSelectedFile().getAbsolutePath() + "\\bdd.txt")));
						} catch (IOException e1) {
							System.out.println("error" + e.toString());
							e1.printStackTrace();
						}
					}
				}
			});
			btnExportarInfo.setBounds(202, 186, 112, 23);
		}
		return btnExportarInfo;
	}
	private JLabel getLblBienvenido() {
		if (lblBienvenido == null) {
			lblBienvenido = new JLabel("Bienvenido");
			lblBienvenido.setIcon(new ImageIcon("C:\\Users\\User\\Documents\\Universidad\\2019-2020\\Introduccion Seeguridad Informatica\\Proyecto2\\GitGestor\\SeguridadInformatica\\GestorContrase\u00F1a\\bnv.png"));
			lblBienvenido.setBounds(32, 4, 282, 89);
		}
		return lblBienvenido;
	}
}
