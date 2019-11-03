package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarRegistroGUI frame = new AgregarRegistroGUI();
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
	public AgregarRegistroGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 300);
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
	}
	private JTextField getNombreUsuario() {
		if (nombreUsuario == null) {
			nombreUsuario = new JTextField();
			nombreUsuario.setBounds(235, 117, 146, 20);
			nombreUsuario.setColumns(10);
		}
		return nombreUsuario;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(235, 148, 146, 20);
		}
		return passwordField;
	}
	private JTextField getPaginaWeb() {
		if (PaginaWeb == null) {
			PaginaWeb = new JTextField();
			PaginaWeb.setBounds(235, 86, 146, 20);
			PaginaWeb.setColumns(10);
		}
		return PaginaWeb;
	}
	private JButton getBtnAgregarRegistro() {
		if (btnAgregarRegistro == null) {
			btnAgregarRegistro = new JButton("Agregar Registro");
			btnAgregarRegistro.setBounds(235, 209, 146, 23);
		}
		return btnAgregarRegistro;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new registroGUI().setVisible(true);
					dispose();
				}
			});
			btnCancelar.setBounds(25, 209, 112, 23);
		}
		return btnCancelar;
	}
	private JLabel getLblNombreDeLa() {
		if (lblNombreDeLa == null) {
			lblNombreDeLa = new JLabel("Nombre o link de la plataforma :");
			lblNombreDeLa.setBounds(38, 92, 275, 14);
		}
		return lblNombreDeLa;
	}
	private JLabel getLblNombreDeUsuario() {
		if (lblNombreDeUsuario == null) {
			lblNombreDeUsuario = new JLabel("Nombre de usuario:");
			lblNombreDeUsuario.setBounds(38, 120, 136, 14);
		}
		return lblNombreDeUsuario;
	}
	private JLabel getLblContrasea() {
		if (lblContrasea == null) {
			lblContrasea = new JLabel("Contrase\u00F1a:");
			lblContrasea.setBounds(38, 151, 87, 14);
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
