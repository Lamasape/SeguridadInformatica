package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Registro;
import servicio.ServicioRegistro;
import utils.PeticionesWeb;
import utils.Utils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class ModificarRegistro extends JFrame {

	private JPanel contentPane;
	private JLabel nombreDeUsuario;
	private JLabel lblInputName;
	private JLabel lblContrasea;
	private JLabel lblNombreDeLa;
	private JTextField textFieldPlataforma;
	private JTextField nombreUsuario;
	private JTextField inputName;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private ServicioRegistro servicio;
	private Registro registro;
	private JPasswordField passwordField;
	private JLabel lblParaModificarLos;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame. @throws
	 */
	public ModificarRegistro(ServicioRegistro servicio, Registro registro) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarRegistro.class.getResource("/img/icon.png")));
		this.servicio = servicio;
		this.registro=registro;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getNombreDeUsuario());
		contentPane.add(getLblInputName());
		contentPane.add(getLblContrasea());
		contentPane.add(getLblNombreDeLa());
		contentPane.add(getTextFieldPlataforma());
		contentPane.add(getNombreUsuario());
		contentPane.add(getInputName());
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnAceptar());
	    contentPane.add(getPasswordField());
	    this.textFieldPlataforma.setText(registro.getURL());
		this.inputName.setText(registro.getInputName());
		this.nombreUsuario.setText(registro.getNombreUsuario());
		String contrasenia = registro.getContrasena();
		try {
			this.passwordField.setText(Utils.desencriptar(Utils.desencriptar(contrasenia)));
			contentPane.add(getLblParaModificarLos());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				RegistroGUI frame;
				frame=new RegistroGUI(servicio);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.setVisible(true);    	
			}
		}); 

	}

	private JLabel getNombreDeUsuario() {
		if (nombreDeUsuario == null) {
			nombreDeUsuario = new JLabel("Nombre de Usuario");
			nombreDeUsuario.setBounds(39, 104, 216, 14);
		}
		return nombreDeUsuario;
	}

	private JLabel getLblInputName() {
		if (lblInputName == null) {
			lblInputName = new JLabel("Input Name");
			lblInputName.setBounds(39, 141, 154, 14);
		}
		return lblInputName;
	}

	private JLabel getLblContrasea() {
		if (lblContrasea == null) {
			lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setBounds(39, 177, 143, 14);
		}
		return lblContrasea;
	}

	private JLabel getLblNombreDeLa() {
		if (lblNombreDeLa == null) {
			lblNombreDeLa = new JLabel("Nombre de la plataforma");
			lblNombreDeLa.setBounds(39, 64, 143, 14);
		}
		return lblNombreDeLa;
	}

	private JTextField getTextFieldPlataforma() {
		if (textFieldPlataforma == null) {
			textFieldPlataforma = new JTextField();
			textFieldPlataforma.setBounds(192, 61, 183, 20);
			textFieldPlataforma.setColumns(10);
		}
		return textFieldPlataforma;
	}

	private JTextField getNombreUsuario() {
		if (nombreUsuario == null) {
			nombreUsuario = new JTextField();
			nombreUsuario.setBounds(192, 101, 183, 20);
			nombreUsuario.setColumns(10);
		}
		return nombreUsuario;
	}

	private JTextField getInputName() {
		if (inputName == null) {
			inputName = new JTextField();
			inputName.setBounds(192, 138, 183, 20);
			inputName.setColumns(10);
		}
		return inputName;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RegistroGUI registro;
					registro = new RegistroGUI(servicio);
					registro.setResizable(false);
					registro.setLocationRelativeTo(null);
					registro.setVisible(true);
					dispose();
				}
			});
			btnCancelar.setBounds(39, 227, 89, 23);
		}
		return btnCancelar;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(!textFieldPlataforma.getText().isEmpty())
					{
						if(!nombreUsuario.getText().isEmpty())
						{
							if(!inputName.getText().isEmpty())
							{
								if(!passwordField.getText().isEmpty())
								{
									System.out.println("id "+registro.getId());
									try {
										servicio.eliminarRegistro(registro, servicio.leerUsuario());
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									try {
										registro.setContrasena(Utils.encriptar(passwordField.getText()));
										registro.setId(2);
										registro.setInputName(inputName.getText());
										registro.setNombreUsuario(nombreUsuario.getText());
										registro.setTitulo(nombreUsuario.getText());
										registro.setURL(textFieldPlataforma.getText());
										servicio.crearRegistro(registro);
										RegistroGUI frame=new RegistroGUI(servicio);
										frame.setLocationRelativeTo(null);
										frame.setResizable(false);
										frame.setVisible(true);
										JOptionPane.showMessageDialog(new JFrame(), "Registro cambiado exitosamente.", "Exito!",JOptionPane.DEFAULT_OPTION);
										dispose();
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									//servicio.crearRegistro();
								}
								else
								{
									JOptionPane.showMessageDialog(new JFrame(), "Insertar una contraseña", "Error",JOptionPane.ERROR_MESSAGE);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(new JFrame(), "Insertar el input name del inicio de sesión", "Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(new JFrame(), "Insertar un nombre de usuario", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame(), "Insertar URL de la plataforma", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnAceptar.setBounds(263, 227, 89, 23);
		}
		return btnAceptar;
	}

	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(192, 174, 183, 20);
		}
		return passwordField;
	}
	private JLabel getLblParaModificarLos() {
		if (lblParaModificarLos == null) {
			lblParaModificarLos = new JLabel("Para modificar los datos del registro, cambiar los sigueintes campos:");
			lblParaModificarLos.setBounds(35, 26, 365, 14);
		}
		return lblParaModificarLos;
	}
}
