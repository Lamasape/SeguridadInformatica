package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Registro;
import servicio.ServicioRegistro;
import utils.Utils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame. @throws
	 */
	public ModificarRegistro(ServicioRegistro servicio, Registro registro) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		this.servicio = servicio;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JLabel getNombreDeUsuario() {
		if (nombreDeUsuario == null) {
			nombreDeUsuario = new JLabel("Nombre de Usuario");
			nombreDeUsuario.setBounds(75, 89, 216, 14);
		}
		return nombreDeUsuario;
	}

	private JLabel getLblInputName() {
		if (lblInputName == null) {
			lblInputName = new JLabel("Input Name");
			lblInputName.setBounds(75, 126, 154, 14);
		}
		return lblInputName;
	}

	private JLabel getLblContrasea() {
		if (lblContrasea == null) {
			lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setBounds(75, 162, 143, 14);
		}
		return lblContrasea;
	}

	private JLabel getLblNombreDeLa() {
		if (lblNombreDeLa == null) {
			lblNombreDeLa = new JLabel("Nombre de la plataforma");
			lblNombreDeLa.setBounds(75, 49, 143, 14);
		}
		return lblNombreDeLa;
	}

	private JTextField getTextFieldPlataforma() {
		if (textFieldPlataforma == null) {
			textFieldPlataforma = new JTextField();
			textFieldPlataforma.setBounds(228, 46, 183, 20);
			textFieldPlataforma.setColumns(10);
		}
		return textFieldPlataforma;
	}

	private JTextField getNombreUsuario() {
		if (nombreUsuario == null) {
			nombreUsuario = new JTextField();
			nombreUsuario.setBounds(228, 86, 183, 20);
			nombreUsuario.setColumns(10);
		}
		return nombreUsuario;
	}

	private JTextField getInputName() {
		if (inputName == null) {
			inputName = new JTextField();
			inputName.setBounds(228, 123, 183, 20);
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
			btnCancelar.setBounds(65, 205, 89, 23);
		}
		return btnCancelar;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setBounds(262, 205, 89, 23);
		}
		return btnAceptar;
	}

	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(228, 159, 183, 20);
		}
		return passwordField;
	}
}
