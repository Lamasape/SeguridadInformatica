package igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import servicio.ServicioRegistro;
import utils.PeticionesWeb;
import utils.Utils;
import utils.UtilsContraseñaVerifications;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class ModificarContraseñaGUI extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JLabel lblEscribirContrasea;
	private JPasswordField passwordField;
	private JLabel lblEscribirlaOtraVez;
	private JPasswordField passwordField_1;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel label_1;
	private String labelMessage;
	private boolean primeraVez;
	ServicioRegistro servicio;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ModificarContraseñaGUI(String cadena, boolean primeraVez, ServicioRegistro servicio) {
		this.servicio=servicio;
		setBackground(Color.WHITE);
		setTitle("Gestor Contrase\u00F1as PUJ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarContraseñaGUI.class.getResource("/img/icon.png")));
		this.primeraVez=primeraVez;
		this.labelMessage=cadena;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLabel());
		contentPane.add(getLblEscribirContrasea());
		contentPane.add(getPasswordField());
		contentPane.add(getLblEscribirlaOtraVez());
		contentPane.add(getPasswordField_1());
		contentPane.add(getBtnAceptar());
		contentPane.add(getBtnCancelar());
		contentPane.add(getLabel_1());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if(!primeraVez)
				{
					PeticionesWeb.BorrarPortapapeles();		
					if (labelMessage.equals("Su contraseña ha expirado, por favor ingresar una contraseña nueva"))
					{
						InicioSesion frame=	new InicioSesion(servicio);
						frame.setVisible(true);
						frame.setResizable(false);
						frame.setLocationRelativeTo(null);
					}
					else
					{
						RegistroGUI frame=	new RegistroGUI(servicio);
						frame.setVisible(true);
						frame.setResizable(false);
						frame.setLocationRelativeTo(null);
					}
				}
			}
		}); 
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setBounds(169, 20, 0, 0);
		}
		return label;
	}
	private JLabel getLblEscribirContrasea() {
		if (lblEscribirContrasea == null) {
			lblEscribirContrasea = new JLabel("Escribir contrase\u00F1a nueva:");
			lblEscribirContrasea.setBounds(10, 56, 159, 33);
		}
		return lblEscribirContrasea;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(169, 62, 223, 20);
		}
		return passwordField;
	}
	private JLabel getLblEscribirlaOtraVez() {
		if (lblEscribirlaOtraVez == null) {
			lblEscribirlaOtraVez = new JLabel("Escribirla otra vez:");
			lblEscribirlaOtraVez.setBounds(10, 134, 125, 14);
		}
		return lblEscribirlaOtraVez;
	}
	private JPasswordField getPasswordField_1() {
		if (passwordField_1 == null) {
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(169, 131, 223, 20);
		}
		return passwordField_1;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {	
						String contra1=Utils.encriptar(getPasswordField().getText());
						String contra2=Utils.encriptar(getPasswordField_1().getText());
						if(!Utils.desencriptar(contra1).isEmpty() && !Utils.desencriptar(contra2).isEmpty())///Ver si alguno de los campos está vacio.
						{
							if(UtilsContraseñaVerifications.verSiEsLaMisma(contra1, contra2))///Ver si las dos contraseñas coinciden
							{
								if(UtilsContraseñaVerifications.verificarContrasenia(Utils.desencriptar(contra1).toCharArray()))///Ver estandares para ver si la contraseña es segura
								{	
									if (!primeraVez)
									{
										if(!servicio.leerUsuario().getContrasenaMaestra().equals(contra1))
										{
											servicio.modificarContrasenaMaestra(getPasswordField().getText());
											RegistroGUI frame=	new RegistroGUI(servicio);
											frame.setVisible(true);
											frame.setResizable(false);
											frame.setLocationRelativeTo(null);
											JOptionPane.showMessageDialog(new JFrame(), "Contraseña cambiada exitosamente.", "Exito!",JOptionPane.DEFAULT_OPTION);
											dispose();
										}
										else
										{
											JOptionPane.showMessageDialog(new JFrame(), "La contraseña debe ser distinta a la actual", "Error!",JOptionPane.ERROR_MESSAGE);
										}
									}
									else
									{
										servicio.crearContrasenaMaestra(getPasswordField().getText());
										RegistroGUI frame=	new RegistroGUI(servicio);
										frame.setVisible(true);
										frame.setResizable(false);
										frame.setLocationRelativeTo(null);
										JOptionPane.showMessageDialog(new JFrame(), "Contraseña cambiada exitosamente.", "Exito!",JOptionPane.DEFAULT_OPTION);
										dispose();
									}

								}
							}
							else
							{
								JOptionPane.showMessageDialog(new JFrame(), "Las contraseñas no coinciden... vuelvelo a intentar.", "Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(new JFrame(), "Debe escribir algo en los dos campos", "Error",JOptionPane.ERROR_MESSAGE);
						}

					} catch (Exception e) {
						System.out.println("error" + e.toString());
						e.printStackTrace();
					}

				}
			});
			btnAceptar.setBounds(243, 188, 89, 23);
		}
		return btnAceptar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!primeraVez)
					{
						if (labelMessage.equals("Su contraseña ha expirado, por favor ingresar una contraseña nueva"))
						{
							InicioSesion frame=	new InicioSesion(servicio);
							frame.setVisible(true);
							frame.setResizable(false);
							frame.setLocationRelativeTo(null);
						}
						else
						{
							RegistroGUI frame=	new RegistroGUI(servicio);
							frame.setVisible(true);
							frame.setResizable(false);
							frame.setLocationRelativeTo(null);
						}
					}
					dispose();
				}
			});
			btnCancelar.setBounds(106, 188, 89, 23);
		}
		return btnCancelar;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel(this.labelMessage);
			label_1.setBounds(10, 20, 399, 31);
		}
		return label_1;
	}
}
