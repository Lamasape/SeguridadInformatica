package igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Registro;
import servicio.ServicioRegistro;
import servicio.impl.ServiceRegistroImpl;
import utils.PeticionesWeb;
import utils.Utils;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.Color;

public class AgregarRegistroGUI extends JFrame {

	private JPanel contentPane;
	private JTextField nombreUsuario;
	private JPasswordField passwordField;
	private JTextField PaginaWeb;
	private JButton btnAgregarRegistro;
	private JButton btnCancelar;
	private JLabel lblNombreDeLa;
	private JLabel lblNombreDeUsuario;
	private JLabel lblContrasea;
	private JLabel lblLlenarLosSiguientes;
	private ServicioRegistro servicio;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public AgregarRegistroGUI(ServicioRegistro servicio) {
		this.servicio=servicio;
		setBackground(Color.WHITE);
		setTitle("Gestor Contrase\u00F1as PUJ");
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getNombreUsuario());
		contentPane.add(getPasswordField());
		contentPane.add(getPaginaWeb());
		contentPane.add(getBtnAgregarRegistro());
		contentPane.add(getBtnCancelar());
		contentPane.add(getLblNombreDeLa());
		contentPane.add(getLblNombreDeUsuario());
		contentPane.add(getLblContrasea());
		contentPane.add(getLblLlenarLosSiguientes());
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
	private JTextField getNombreUsuario() {
		if (nombreUsuario == null) {
			nombreUsuario = new JTextField();
			nombreUsuario.setBounds(235, 90, 146, 20);
			nombreUsuario.setColumns(10);
		}
		return nombreUsuario;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(235, 121, 146, 20);
		}
		return passwordField;
	}
	private JTextField getPaginaWeb() {
		if (PaginaWeb == null) {
			PaginaWeb = new JTextField();
			PaginaWeb.setBounds(235, 62, 146, 20);
			PaginaWeb.setColumns(10);
		}
		return PaginaWeb;
	}
	private JButton getBtnAgregarRegistro() {
		if (btnAgregarRegistro == null) {
			btnAgregarRegistro = new JButton("Agregar Registro");
			btnAgregarRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if(nombreUsuario.getText().length()>0)
					{
						if(passwordField.getText().length()>0)
						{
							if(PaginaWeb.getText().length()>0)
							{
								Registro registro= new Registro();
								try {
									registro.setContrasena( Utils.encriptar(passwordField.getText()));
									registro.setTitulo(nombreUsuario.getText());
									registro.setId(2);
									registro.setNombreUsuario(nombreUsuario.getText());
									registro.setURL(PaginaWeb.getText());
									registro.setInputName(PeticionesWeb.generarInput(registro.getURL()));
									///registro.setInputName(inputName.getText()); Llamada de diego
									ServicioRegistro servicio=new ServiceRegistroImpl();
									servicio.crearRegistro(registro);
									RegistroGUI frame=	new RegistroGUI(servicio);
									frame.setVisible(true);
									frame.setResizable(false);
									frame.setLocationRelativeTo(null);
									dispose();
									JOptionPane.showMessageDialog(new JFrame(), "Registro agregado exitosamente.", "Exito!",JOptionPane.DEFAULT_OPTION);
								} catch (Exception e1) {
									System.out.println("error" + e.toString());
									e1.printStackTrace();
								}	
							}
							else
							{
								JOptionPane.showMessageDialog(new JFrame(), "Insertar la URL o nombre de la plataforma.", "Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(new JFrame(), "Insertar contraseņa.", "Error",JOptionPane.ERROR_MESSAGE);

						}
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame(), "Insertar nombre de usuario.", "Error",JOptionPane.ERROR_MESSAGE);

					}


				}
			});
			btnAgregarRegistro.setBounds(235, 166, 146, 23);
		}
		return btnAgregarRegistro;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RegistroGUI frame=	new RegistroGUI(servicio);
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					dispose();
				}
			});
			btnCancelar.setBounds(26, 166, 112, 23);
		}
		return btnCancelar;
	}
	private JLabel getLblNombreDeLa() {
		if (lblNombreDeLa == null) {
			lblNombreDeLa = new JLabel("Nombre o link de la plataforma :");
			lblNombreDeLa.setBounds(38, 68, 275, 14);
		}
		return lblNombreDeLa;
	}
	private JLabel getLblNombreDeUsuario() {
		if (lblNombreDeUsuario == null) {
			lblNombreDeUsuario = new JLabel("Nombre de usuario:");
			lblNombreDeUsuario.setBounds(38, 93, 136, 14);
		}
		return lblNombreDeUsuario;
	}
	private JLabel getLblContrasea() {
		if (lblContrasea == null) {
			lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setBounds(38, 124, 87, 14);
		}
		return lblContrasea;
	}
	private JLabel getLblLlenarLosSiguientes() {
		if (lblLlenarLosSiguientes == null) {
			lblLlenarLosSiguientes = new JLabel("Llenar el siguiente formulario para agregar un registro.");
			lblLlenarLosSiguientes.setBounds(10, 26, 328, 14);
		}
		return lblLlenarLosSiguientes;
	}
}
